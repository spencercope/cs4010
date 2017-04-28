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
        </style>
    </head>
    <body>
        <c:set var="routeToServlet" value="${initParam.routeToServlet}"/>
        <div class="container">
            <center>
                <h1 class="jumbotron">
                    Hey, we should do this again some time. &nbsp; <i class="fa fa-handshake-o fa-6" aria-hidden="true"></i>
                </h1>
                <div class="well" style="width: 50%;">
                    <h3 style="padding-bottom: 20px;">How about now?</h3>
                    <a href="${routeToServlet}/sessionServlet"><button class="btn btn-primary">Totally</button></a>
                </div>
            </center>
            <hr/>
        </div>
    </body>
</html>