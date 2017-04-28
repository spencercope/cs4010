<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
    <head>
        <title>Update Notes ${thesessioncount}</title>
        <style>
            div.scrolling {
                background-color: #FFFFFF;
                width: 1100px;
                height: 400px;
                overflow: scroll;
            }
            ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
                background-color: #333;
            }
            li {
                float: right;
            }
            li a {
                display: block;
                color: white;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
            }
            li a:hover {
                background-color: #111;
            }
            .active{
                background-color: #1f1f1f;
            }
        </style>
    </head>

    <body>
        <c:set var="routeToServlet" value="${initParam.routeToServlet}"/>
        <c:set var="this_version" scope="page"  value="${theBean.version_id}" />
        <c:if test="${this_version == 0}">
            <c:set var="this_version"  value=""/><p>
        </c:if>
        <c:set var="sessionString" value='${requestScope["sessionString"]}' />
        <ul>
            <li><a href="${routeToServlet}/sessionServlet?toDo=logout&sessionString=${sessionString}">Logout</a></li>
            <li><a class="active">Update Notes</a></li>
            <li><a href="${routeToServlet}/testJStore.html" target="_blank">TestJStore</a></li>
        </ul>
        <center>
            <h1>
                Select a Version
            </h1>
           <h3>Your very own session string -> ${sessionString}</h3>
        </center>

        <hr/>
        <font size="+1">

       <form method="get" action="${routeToServlet}/sessionServlet" >
           Java Source: <input type="text" name="java_source" value="${theBean.file_name}">&nbsp;&nbsp;&nbsp;Version: <input type="text" name="version" value="${this_version}">
           <br>
           <input type="hidden" name="task" value="1">
           <input type="hidden" name="sessionString" value="${sessionString}">
           <input type="submit" value="Submit">
       </form>

       <hr/>

       <form method="get" action="${routeToServlet}/sessionServlet" target="_blank">
           <center>
               <h2>Notes: ${theBean.file_name} ${this_version}</h2>
           </center>

           <textarea rows="5" cols="100"  name="notes">
               ${theBean.notes}
           </textarea> <br>

           <input type="hidden" name="java_source" value="${theBean.file_name}">
           <input type="hidden" name="version" value="${theBean.version_id}">
           <input type="hidden" name="task" value="2">
           <input type="hidden" name="sessionString" value="${sessionString}">
           <input type="submit" value="Submit">
       </form>

       <hr/>

       <center> <h2>The file:</h2> </center>
       <div class="scrolling">
           <pre><font size="+1">
               <b>
                   ${theBean.this_version}
               </b>
           </font></pre>
       </div>
       </font>

    </body>
</html>