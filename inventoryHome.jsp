<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
    <head>
        <title>Inventory</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            div.scrolling {
                background-color: #FFFFFF;
                width: 1100px;
                height: 400px;
                overflow: scroll;
            }
            body{
                background-color: #eeeeee;
            }
            table { width:250px;table-layout:fixed; }
            td { overflow:hidden;white-space:nowrap;  }
        </style>
    </head>
    <body>
        <c:set var="routeToServlet" value="${initParam.routeToServlet}"/>
        <c:set var="sessionString" value='${requestScope["sessionString"]}' />
        <div class="container">
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="#">Epoc Ink &nbsp;<i class="fa fa-paper-plane" aria-hidden="true"></i></a>
                    </div>
                    <ul class="nav navbar-nav" style="float: right;">
                        <li class="active"><a href="#">Home</a></li>
                        <li><a href="${routeToServlet}/sessionServlet?toDo=logout&sessionString=${sessionString}">Logout</a></li>
                    </ul>
                </div>
            </nav>
            <center>
                <h1 class="jumbotron">
                    Inventory Home
                </h1>
                <div class="well" style="width: 50%;">
                    <h3>Your very own session string -> ${sessionString}</h3>
                    <h4>Current sessions -> ${sessionCount}</h4>
                </div>
            </center>
            <div id="addNew" style="display: none; position: absolute;">
                <div class="form-inline">
                    <div class="form-group">
                        <form action="${routeToServlet}/sessionServlet" method="get">
                            <select name="category" class="form-control">
                                <option>Shirt</option>
                                <option>Shorts</option>
                                <option>Pants</option>
                            </select>
                            <input type="text" name="name" placeholder="Name" class="form-control">
                            <input type="number" name="quantity" placeholder="Quantity" class="form-control">
                            <select name="size" class="form-control">
                                <option>Small</option>
                                <option>Medium</option>
                                <option>Large</option>
                            </select>
                            <input type="text" name="color" placeholder="Color" class="form-control">
                            <input type="text" name="description" placeholder="Description" class="form-control">
                            <input type="hidden" name="task" value="1">
                            <input type="hidden" name="sessionString" value="${sessionString}">
                            <input type="submit" value="Submit" class="btn btn-primary">
                        </form>
                    </div>
                </div>
            </div>
            <hr/>
            <hr/>
            <hr/>
            <div class="scrolling">
                <div class="container">
                    <div class="row">
                        <h2 style="display: inline-block; vertical-align: middle; padding-left: 20px;">All Database Items (${dbCount})</h2>
                        <c:if test="${canEdit != 'true'}">
                            <i class="fa fa-lock" aria-hidden="true"></i>
                        </c:if>
                        <c:if test="${canEdit == 'true'}">
                            <span style="display: inline-block; vertical-align: middle; padding-left: 620px">
                                <button class="btn btn-primary" id="addBtn" onclick="showHide()">Add New</button>
                            </span>
                        </c:if>
                    </div>
                    <p>Refresh Database <a href="${routeToServlet}/sessionServlet?task=4&sessionString=${sessionString}"><i class="fa fa-refresh" aria-hidden="true"></i></a></p>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Category</th>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Size</th>
                                <th>Color</th>
                                <th>Description</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="inventoryItem" items="${allInventory}">
                            <tr>
                                <td width="5%"><c:out value="${inventoryItem.id}"/></td>
                                <td width="10%"><c:out value="${inventoryItem.category}"/></td>
                                <td width="10%"><c:out value="${inventoryItem.name}"/></td>
                                <td width="10%"><c:out value="${inventoryItem.quantity}"/>
                                    <c:if test="${canEdit == 'true'}">
                                        <a href="${routeToServlet}/sessionServlet?task=3&increment=true&sessionString=${sessionString}&id=${inventoryItem.id}&quantity=${inventoryItem.quantity}">
                                            <i class="fa fa-arrow-up fa-2" style="color: green" aria-hidden="true"></i>
                                        </a>
                                        <a href="${routeToServlet}/sessionServlet?task=3&increment=false&sessionString=${sessionString}&id=${inventoryItem.id}&quantity=${inventoryItem.quantity}">
                                            <i class="fa fa-arrow-down fa-2" style="color: darkred" aria-hidden="true"></i>
                                        </a>
                                    </c:if>
                                </td>
                                <td width="10%"><c:out value="${inventoryItem.size}"/></td>
                                <td width="10%"><c:out value="${inventoryItem.color}"/></td>
                                <td width="25%"><c:out value="${inventoryItem.description}"/></td>
                                <c:if test="${canEdit == 'true'}">
                                    <td width="5%">
                                        <a class="btn btn-danger"
                                           href="${routeToServlet}/sessionServlet?task=2&sessionString=${sessionString}&id=${inventoryItem.id}">
                                            <i class="fa fa-trash-o fa-lg"></i>
                                        </a>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>

    <script>
        function showHide() {
            var x = document.getElementById('addNew');
            var y = document.getElementById('addBtn');
            if (x.style.display === 'none') {
                x.style.display = 'block';
                y.innerHTML = 'Nvm';
            } else {
                x.style.display = 'none';
                y.innerHTML = 'Add New';
            }
        }
    </script>
</html>