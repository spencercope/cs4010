package mybeans;
import BH.*;
import java.util.Iterator;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Arrays.asList;
import java.sql.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.io.*;
import java.util.function.*;
import java.text.DateFormat;

public class UserBean implements java.io.Serializable {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/cs4010";
    static final String USER = "cs4010";
    static final String PASS = "cs4010";

    private int id = 0;
    private String userName = "";
    private String password = "";


    public UserBean() {
    }


    public boolean checkUser(String name, String pass){
        log("incoming name : " + name);
        log("incoming pass : " + pass);
        ArrayList<UserBean> beans = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt = conn.createStatement();
            String query="SELECT id, userName, password from sjc_users;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                UserBean bean = new UserBean();
                bean.setId(rs.getInt("id"));
                bean.setUserName(rs.getString("userName"));
                bean.setPassword(rs.getString("password"));
                beans.add(bean);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            return false;
        }

        log("user's table size : " + beans.size());

        for(int i = 0; i < beans.size(); i++){
            log(beans.get(i).getUserName());
            log(beans.get(i).getPassword());
            if(beans.get(i).getUserName().equals(name)){
                log("name is equal to something in db");
                if(beans.get(i).getPassword().equals(pass)){
                    return true;
                }
            }
        }
        return false;

    }


    public void log(String s) {
        FileWriter fileWriter = null;
        try {
            String content = s + " at :" + new Date(System.currentTimeMillis()).toString() + "\n";
            File theLogFile = new File("/var/lib/tomcat/webapps/j-cope/session.log");
            fileWriter = new FileWriter(theLogFile, true);
            fileWriter.write(content);
        } catch (IOException ex) {
        } finally {
            try {
                fileWriter.close();
            } catch (IOException ex) {

            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

