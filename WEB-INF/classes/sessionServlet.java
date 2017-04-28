import BH.*;
import mybeans.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.function.*;
import static java.util.Arrays.asList;
import java.text.DateFormat;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class lockThings {
    public static  ReentrantLock theLock = new ReentrantLock();
}

public class sessionServlet extends HttpServlet {
    private    List<String[]>   the_sessions;
    private    DateFormat df;

    public void init() throws ServletException  {
        the_sessions = new ArrayList<String[]>();
        df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        log("*****************************************************************");
        showSessionArray(the_sessions);//display all sessions

        String sessionString = getRandomString();
        log("generate sessionString is below");
        log(sessionString);

        Consumer <String> forwardTo = (s) ->ForwardTo(s,req,res);
        boolean newcomer = true;
        String[] this_session = new String[4];


        if(req.getParameter("sessionString") != null) {
            log("do we ever get in here");
            String tmpStr = req.getParameter("sessionString");
            log(tmpStr);
            for (String[] a_session : the_sessions) {
                if (a_session[0].equals(tmpStr)) {  //session exists
                    this_session = a_session.clone();
                    log("this_session : " + this_session[0] + " : " + this_session[2]);
                    break;
                }
            }
        }


        if(req.getParameter("task") != null ){//if task exists it's not the startSession page
            log("there is a task");
            int theTask = Integer.parseInt(req.getParameter("task"));

            String currentSessionString = req.getParameter("sessionString");
            log("currentSessionString : " + currentSessionString);

            if (the_sessions.size() == 10) {
                forwardTo.accept("noSessions.jsp");  //No Available Sessions
                return;
            }

            if(theTask == 0){
                String newName = req.getParameter("username");
<<<<<<< HEAD
                UserBean userBean = new UserBean();
                boolean userIsGood = userBean.checkUser(newName, req.getParameter("password"));

                if(userIsGood){
                    log("user is good");
                    String[] new_session = {sessionString, df.format(new Date()), newName, "false"};

                    if(the_sessions.size() < 1){
                        log("first session");
                        new_session[3] = "true"; //has edit access
                    }
                    showSessionArray(the_sessions);//display all sessions
                    the_sessions.add(new_session);//add the new session
                    showSessionArray(the_sessions);//display all sessions

                    this_session = new_session;
                }else{
                    forwardTo.accept("badLogin.jsp");
                }


=======
                String[] new_session = {sessionString, df.format(new Date()), newName, "false"};

                if(the_sessions.size() < 1){
                    log("first session");
                    new_session[3] = "true"; //has edit access
                }

                showSessionArray(the_sessions);//display all sessions
                the_sessions.add(new_session);//add the new session
                showSessionArray(the_sessions);//display all sessions

                this_session = new_session;
>>>>>>> b6feeffc5cba84d6fa77a04ba2ce39fcbbba651e
            }

            log("about to create bean");
            this_session[1] = df.format(new Date()); //reset time
            InventoryBean inventory = new InventoryBean();
            ArrayList<InventoryBean> allInventory = null;
            lockThings.theLock.lock();
            try {
                switch (req.getParameter("task").trim()){
                    case "0":
                        log("about to call getAll()");
                        allInventory = inventory.getAll();
                        break;
                    case "1":
                        log("about to call addNew()");
                        allInventory = inventory.addNew(req.getParameter("category"), req.getParameter("name"),
                                req.getParameter("quantity"), req.getParameter("size"),
                                req.getParameter("color"), req.getParameter("description"));
                        break;
                    case "2":
                        log("about to call deleteById()");
                        allInventory = inventory.deleteById(req.getParameter("id"));
                        break;
                    case "3":
                        log("about to call updateById()");
                        boolean increment = Boolean.parseBoolean(req.getParameter("increment"));
                        allInventory = inventory.updateById(req.getParameter("id"),
                                        req.getParameter("quantity"), increment);
                        break;
                    case "4":
                        log("about to call getAll() for a refresh on the db");
                        allInventory = inventory.getAll();
                        break;
                }
            } finally {
                 lockThings.theLock.unlock();
            }
            log("setting attribute sessionCount : " + the_sessions.size());
            req.setAttribute("sessionCount", the_sessions.size());
            req.setAttribute("allInventory", allInventory);
            req.setAttribute("dbCount", allInventory.size());
            req.setAttribute("sessionString", this_session[0]);

            req.setAttribute("canEdit", this_session[3]);

            forwardTo.accept("inventoryHome.jsp");
            return;
        }


        if((req.getParameter("toDo") != null) && (req.getParameter("toDo").equals("logout"))){//check if we want to end session
            String endSessStr = req.getParameter("sessionString");
            log("going to end the session : " + endSessStr);

            int endThisIndex = getSessionIndex(endSessStr, the_sessions);

            HttpSession session = req.getSession();

            showSessionArray(the_sessions);

//            if(the_sessions.size() > 1){
//                log("size is greater than one");
//                the_sessions.get(endThisIndex - 1);
//                showSessionArray(the_sessions);
//            }


            the_sessions.remove(endThisIndex);
            log("removed one");

            if(the_sessions.size() > 0){
                the_sessions.get(0)[3] = "true";//set the next most recent user to have the edit access
            }

            showSessionArray(the_sessions);//display all sessions
            req.setAttribute("sessionCount", the_sessions.size());//set the size
            session.invalidate();
            forwardTo.accept("onLogout.jsp");
            return;
        }

        Set<String> allParams = req.getParameterMap().keySet();
        if(allParams.size() < 1){
            log("no params");
            forwardTo.accept("login.jsp");
        }
    }//end doGet




//---------------------------------------------------------------helper functions

    public int getSessionIndex(String str, List<String[]> the_sessions){
        int i = 0;
        int returnThis = -1;
        for (String [] sess : the_sessions){
            log(sess[0]);
            log(str);
            if(sess[0].equals(str)){
                log("equal");
                returnThis = i;
            }
            i++;
        }
        return returnThis;
    }

    public void showSessionArray(List<String[]> the_sessions){
        for (String [] sess : the_sessions){
            log("  -------------------------------------------------------------");
            log("  |" + sess[0] + "                                                 |");
            log("  |" + sess[1] + "                        |");
            log("  |" + sess[2] + "                                                 |");
            log("  |" + sess[3] + "                                                 |");
            log("  -------------------------------------------------------------");
        }
        log("___end and size is " + the_sessions.size());
    }

    boolean tooLong(String now,String then){
        //Check amount of time that passed
        return false;
    }
    boolean checkPW(String name,String password){
        //Check password against name
        return false;
    }

    public void log(String s){
        FileWriter fileWriter = null;
        try {
            String content =s+" at :"+new Date(System.currentTimeMillis()).toString()+"\n";
            File theLogFile = new File("/var/lib/tomcat/webapps/j-cope/session.log");
            fileWriter = new FileWriter(theLogFile,true);
            fileWriter.write(content);
        } catch (IOException ex) {
        } finally {
            try {
                fileWriter.close();
            } catch (IOException ex) {

            }
        }
    }

    void ForwardTo(String s,HttpServletRequest req, HttpServletResponse res)
    {
        RequestDispatcher rd = req.getRequestDispatcher(s);
        try {
            rd.forward(req, res);
        } catch (IOException|ServletException is) {
            log(" req from " + s + " not forwarded at ");
            try {
                throw is;
            } catch (Exception e) {
            }
        }
    }

    public void destroy(){
        log("The instance was destroyed");
    }


    public String getRandomString(){
        byte[] randbyte=new byte[10];
        Random rand  = new Random(System.currentTimeMillis());
        for (int idx = 0; idx <10; ++idx) {
            int randomInt = rand.nextInt(26); //0<=randomInt<26
            //System.out.println(randomInt);
            randbyte[idx]=(byte)(randomInt+65);
        } try {
            String rs=new String(randbyte, "UTF-8");
            //System.out.println(rs);
            return rs;
        } catch (Exception e) {
            //System.out.println("bad string");
            return "bad";
        }
    }

//    public void logHttp(HttpServletRequest request){
//        Enumeration headerNames = request.getAttributeNames();
//        while(headerNames.hasMoreElements()) {
//            log(headerNames.nextElement());
//        }
//    }

}
