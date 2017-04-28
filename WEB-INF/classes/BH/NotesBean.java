package BH;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Arrays.asList;
import java.sql.*; 

public class NotesBean implements java.io.Serializable {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/cs4010";
    static final String USER = "cs4010";
    static final String PASS = "cs4010";
    private int java_store_id=-1;
    private String file_name="";  
    private int    version_id=0; 
    private String save_time="";
    private String this_version="";
    private String notes="";     


    public NotesBean() {
    }

    public String getFile_name(){
        return file_name;
    }
    public String getThis_version(){
        return this_version;
    }
    public String getNotes(){
        return notes;
    }

    public int getVersion_id(){
        return version_id;
    }
    public int getJava_store_id(){
        return java_store_id;
    }


    public void setAll(String f, int v){
        try {
            version_id=-2;
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt = conn.createStatement();
            String this_query=" SELECT * from java_store WHERE  file_name='"+f+"' AND version_id="+v+";"; 
            ResultSet rs = stmt.executeQuery(this_query);
            while (rs.next()) {
                java_store_id=rs.getInt("java_store_id");  
                file_name=rs.getString("file_name");  
                version_id=rs.getInt("version_id");    
                save_time = rs.getString("save_time") ;     
                this_version =Bytes_Hex.HexString2String(rs.getString("this_version"));   
                notes=Bytes_Hex.HexString2String(rs.getString("notes"));           
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {

        }
        return ;
    }
    public void setNotes(String n,String f,int v){
        notes=n;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt = conn.createStatement();
         //     System.out.println("here: "+Bytes_Hex.String2HexString(n));
            String this_query="UPDATE java_store SET notes='"+Bytes_Hex.String2HexString(n)+"' WHERE  file_name='"+f+"' AND version_id="+v+";";  
       //     System.out.println(this_query);
            stmt.executeUpdate(this_query);
            stmt.close();
            conn.close();
        } catch (Exception e) {

        }
        return ;
    }
}
/*
MariaDB [cs4010]> describe java_store;
+---------------+--------------+------+-----+-------------------+-----------------------------+
| Field         | Type         | Null | Key | Default           | Extra                       |
+---------------+--------------+------+-----+-------------------+-----------------------------+
| java_store_id | int(11)      | NO   | PRI | NULL              | auto_increment              |
| file_name     | varchar(256) | NO   |     | NULL              |                             |
| version_id    | int(11)      | NO   |     | NULL              |                             |
| save_time     | timestamp    | NO   |     | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
| this_version  | text         | NO   |     | NULL              |                             |
| notes         | varchar(512) | YES  |     | NULL              |                             |
+---------------+--------------+------+-----+-------------------+-----------------------------+
    public void setFile_name(String s){
        file_name=s;
    }
    public void  setThis_version(String s){
        this_version=s;
    } 
    public void  setNotes(String s){
       notes=s;
    }

    public void  setVersion_id(){
        version_id=s;
    } 

*/


