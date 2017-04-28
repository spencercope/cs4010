<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
    <head>
        <title>Start Session  ${sessionCount}</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            body {
                background: #eee !important;
            }

            .wrapper {
                margin-top: 80px;
                margin-bottom: 80px;
            }

            .form-signin {
                max-width: 380px;
                padding: 15px 35px 45px;
                margin: 0 auto;
                background-color: #fff;
                border: 1px solid rgba(0, 0, 0, 0.1);
            }

            .form-control {
                position: relative;
                font-size: 16px;
                height: auto;
                padding: 10px;
            }

            input[type="text"] {
                margin-bottom: 10px;
                border-bottom-left-radius: 0;
                border-bottom-right-radius: 0;
            }

            input[type="password"] {
                margin-bottom: 20px;
                border-top-left-radius: 0;
                border-top-right-radius: 0;
            }
        </style>
    </head>
    <body>
    <c:set var="routeToServlet" value="${initParam.routeToServlet}"/>
        <center class="container">
           <h1 class="jumbotron">Epoc Ink &nbsp &nbsp<i class="fa fa-paper-plane" aria-hidden="true"></i></h1>
           <hr/>
           <div class="wrapper">
               <form class="form-signin" method="get" action="${routeToServlet}/sessionServlet">
                   <h2 class="form-signin-heading">Welcome</h2>
                   <input type="text" class="form-control" name="username" placeholder="Username"/>
                   <input type="password" class="form-control" name="password" placeholder="Password"/>
                   <input type="hidden" name="task" value="0">
                   <button type="submit" class="btn btn-primary" value="Login">Login</button>
               </form>
               </div>
        </center>
<<<<<<< HEAD
        <br>
        <br>
        <br>
        <center>
            <a href="${routeToServlet}/projectDescription.jsp"><button class="btn btn-primary">Project Description</button></a>
        </center>
=======
>>>>>>> b6feeffc5cba84d6fa77a04ba2ce39fcbbba651e
    </body>
</html>