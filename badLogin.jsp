<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
    <head>
        <title>Bad Login</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
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
                    That was not a very good job logging in.
                </h1>
                <div class="well" style="width: 50%;">
                    <a href="${routeToServlet}/sessionServlet"><button class="btn btn-primary">Try Again</button></a>
                </div>
            </center>
            <hr/>
        </div>
    </body>
</html>