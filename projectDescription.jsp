<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <title>Project Description</title>
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
        .my-bold{
            color: blue;
        }
        .line{
            display: inline;
        }
        .fixed-button{
            position: fixed;
            top: 10px;
            right: 10px;
        }
    </style>
</head>
<body>
    <c:set var="routeToServlet" value="${initParam.routeToServlet}"/>
    <div class="fixed-button">
        <a href="${routeToServlet}/sessionServlet"><button class="btn btn-primary">Go To Login Page</button></a>
        <a href="https://github.com/spencercope/cs4010" target="_blank"><button class="btn btn-primary">Source Code</button></a>
    </div>
    <div class="container">
        <center>
            <h1 class="jumbotron">
                Project Description
            </h1>
        </center>


        <div class="well" style="width: 80%; margin: 0 auto">

            <center>
                <div style="width: 60%; word-wrap: break-word">
                    <p>
                        This is a simple session tracking, database storing, inventory web application.
                        The app was created for Epoc Ink. This company is an online-based fabric printing store that has been in business since 2008.
                        The app allows users to login, create inventory records, edit the quantity, and delete the records.
                    </p>
                </div>
                <div style="width: 75%; word-wrap: break-word">
                    <h5>The project was created for a small local business, Epoc Ink.  Links to their work at the bottom. </h5>
                </div>

                <h4>
                    All source code is hosted on my <a href="https://github.com/spencercope/cs4010" target="_blank">GitHub page</a>.
                </h4>
                <br>
                <br>

            </center>

            <center>
                <div style="width: 75%;">
                    <h3>Technical Details</h3>
                </div>
            </center>
            <hr/>

            <div style="width: 75%;">
                <h4>Tech Stack</h4>
                <p style="word-wrap: break-word">
                    The project was created with <b>JSP</b> for the views and <b>Java Servlets</b> as the controllers.
                    The data model is a <b>SQLBean</b>, with the data store as <b>MariaDB</b>, all on the Hoare's Tomcat server.
                    The java server pages were also styled with Bootstrap and CSS.
                </p>
            </div>
            <br>
            <br>

            <center>
                <div style="width: 75%;">
                    <h3>The Semester Project Elements</h3>
                </div>
            </center>
            <hr/>
            <ol>
                <li>The <b>Remote Client</b> may be Browser-based or a Java Desktop Client.
                    <br>
                    &nbsp;<b class="my-bold">Browser-Based</b>
                    <br>
                </li>
                <li>The <b>Views</b> should mainly be JSPs. Some PHP or HTML supporting Pages will be acceptable when appropriate.
                    <br>
                    &nbsp;<b class="my-bold">JSP, with Bootstrap and CSS for styling</b>
                    <br>
                </li>
                <li>
                    The <b>Controller</b> must be Tomcat <span><span>Servlet</span></span>,supporting safe session-level access to multiple simultaneous clients. In particular, it must only allow
                    serial access to the DataStore.
                    <br>
                    &nbsp;<b class="my-bold">Tomcat Servlet</b>
                    <br>
                    &nbsp;<b class="my-bold">Simultaneous multi access</b>
                    <br>
                    &nbsp;<b class="my-bold">Serial Data Store access</b>
                    <br>
                </li>
                <li>The <b>Model</b> can be Java Beans or other JavaServer-based applications.
                    <br>
                    &nbsp;<b class="my-bold">SQL Bean</b>
                </li>
                <li>The <b>Data Store</b> may be <span><span>MySQL/MariaDB</span></span>, XML, other Server-based file types, or some combination of these.
                    <br>
                    &nbsp;<b class="my-bold">MariaDB</b>
                    <br>
                </li>
            </ol>
            <hr/>
            <br>
            <br>

            <center>
                <div style="width: 75%;">
                    <h3>The Overview</h3>
                </div>
            </center>
            <hr/>

            <h4>The Login Page</h4>
            <p>
                Initially, a request to the servlet is set when visiting
                <a href="${routeToServlet}/sessionServlet">hoare.cs.umsl.edu/servlet/j-cope/sessionServlet</a>
                No session is created until a user logs in.
                <br>
                Once login is clicked, the request is sent with a <b>task</b> parameter of 0.  The task parameter signals a
                <b>checkUser()</b> call to the <b>UserBean</b>.  If the user is in the the database
                and the correct password is entered, it is at this time that we create the session.
                <br>
                This initializes a random session string and creates the string array <b>this_session</b> as the user object.
                Also, on login, the Bean is created and we call <b>getAll()</b> from the <b>InventoryBean.java</b>.
                This returns an ArrayList to display in the Inventory Table.
            </p>
            <img src="assets/login.png" alt="Login"/>
            <br>
            <br>

            <h4>The User Array</h4>
            <p>
                Tailing the <b>session.log</b> file was extremely helpful in development.  Above, the array of sessions, <b>the_sessions</b>
                is printed out each time a request comes in.  Each user's designated <b>sessionString</b> is passed back and forth
                with each request to the <b>sessionServlet.java</b>.
                <ol>
                    <li>Session String</li>
                    <li>DateTime</li>
                    <li>User Name</li>
                    <li>
                        canEdit -> this variable is set to true for the first user to login.
                        &nbsp;All subsequent users are set to false until the editing user logs out,
                        &nbsp;in which case editing power is pass along to the next most recent user.
                    </li>
                </ol>
            </p>
            <img src="assets/user1.png" alt="User1"/>
            <br>
            <br>

            <h4>The Inventory Table</h4>
            <p>
                The view for the first user.  The editing user can add new items to the database, increment,
                decrement, and delete items from the table.  This is all controlled by passing the task parameter.
            </p>

            <center>
             <h2>Tasks</h2>
                <table class="table" style="max-width: 100%; max-height: 100%">
                    <thead>
                        <tr>
                            <th>Task</th>
                            <th></th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><p>0</p></td>
                            <td><i class="fa fa-arrow-right" aria-hidden="true"></i></td>
                            <td><p>Login and get all notes</p></td>
                        </tr>
                        <tr>
                            <td><p>1</p></td>
                            <td><i class="fa fa-arrow-right" aria-hidden="true"></i></td>
                            <td><p>Add new item</p></td>
                        </tr>
                        <tr>
                            <td><p>2</p></td>
                            <td><i class="fa fa-arrow-right" aria-hidden="true"></i></td>
                            <td><p>Delete item</p></td>
                        </tr>
                        <tr>
                            <td><p>3</p></td>
                            <td><i class="fa fa-arrow-right" aria-hidden="true"></i></td>
                            <td><p>Increment/Decrement quantity</p></td>
                        </tr>
                        <tr>
                            <td><p>4</p></td>
                            <td><i class="fa fa-arrow-right" aria-hidden="true"></i></td>
                            <td><p>Refresh database</p></td>
                        </tr>
                    </tbody>
                </table>
            </center>
            <img style="max-height: 100%; max-width: 100%;" src="assets/first%20user%20inventory.png">
            <br>
            <br>


            <h4>The Second User</h4>
            <p>
                A second user is added, and <b>canEdit</b> is set to false and parts of the UI are hidden.
                This is to avoid attending the goat rodeo.
            </p>
            <img src="assets/user1%20and%202.png" alt="User1and2"/>
            <img style="max-height: 100%; max-width: 100%;" src="assets/subsequent%20user%20inventory.png">
            <br>
            <br>

            <h4>The Third User</h4>
            <p>
                Subsequent users can be added and the just like the second user.
            </p>
            <img src="assets/3%20users.png" alt="ThreeUsers"/>
            <br>
            <br>


            <h4>On Logout</h4>
            <p>
                When the first user logs out, editing privileges are immediately passed to the
                next most recent user currently logged in.  The very next time the new editing user
                makes a call to the servlet, the access is passed and that user can edit the inventory.
            </p>
            <img src="assets/after%20user1%20logs%20out.png" alt="afterLogout"/>
            <br>
            <br>
        </div>
        <br>
        <br>
        <center>
            <h3>Epoc Ink Links</h3>
            <hr>
            <div>
                <a href="https://www.etsy.com/shop/EpocInk"><i class="fa fa-etsy fa-5" aria-hidden="true"></i></a>
                &nbsp;
                &nbsp;
                &nbsp;
                <a href="https://www.facebook.com/EpocInk/"><i class="fa fa-facebook-official fa-5" aria-hidden="true"></i></a>
            </div>
        </center>



        <hr/>
    </div>
    </body>
</html>