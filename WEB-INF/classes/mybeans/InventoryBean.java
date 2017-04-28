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

public class InventoryBean implements java.io.Serializable {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/cs4010";
    static final String USER = "cs4010";
    static final String PASS = "cs4010";

    private int id = 0;
    private String creationDate = "";
    private String lastEdit = "";
    private String category = "";
    private String name = "";
    private int quantity = 0;
    private String size = "";
    private String color = "";
    private String description = "";


    public InventoryBean() {
    }

    public ArrayList<InventoryBean> addNew(String category, String name, String quantity, String size, String color, String description){
        log("from addNew() in the bean");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt = conn.createStatement();
            String query = "INSERT INTO sjc_inventory (category, name, quantity, size, color, description) " +
                            "VALUES ('"+category+"','"+name+"','"+quantity+"','"+size+"','"+color+"','"+description+"');";
            log(query);
            stmt.executeUpdate(query);
            stmt.close();
            conn.close();
        }catch(Exception e){
            log(e.getMessage());
            return new ArrayList<InventoryBean>();
        }
        return getAll();
    }

    public ArrayList<InventoryBean> deleteById(String id){
        log("from deleteById() in the bean");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt = conn.createStatement();
            String query = "DELETE FROM sjc_inventory WHERE id = " + id + ";";

            log(query);
            stmt.executeUpdate(query);
            stmt.close();
            conn.close();
        }catch(Exception e) {
            log(e.getMessage());
            return new ArrayList<InventoryBean>();
        }
        return getAll();
    }

    public ArrayList<InventoryBean> updateById(String id, String q, Boolean increment){
        log("from updateById() in the bean");
        int theInt = Integer.parseInt(q);
        if(increment){
            theInt++;
        }else{
            theInt--;
        }
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt = conn.createStatement();
            String query = "UPDATE sjc_inventory SET quantity = " + theInt + " WHERE id = " + id + ";";

            log(query);
            stmt.executeUpdate(query);
            stmt.close();
            conn.close();
        }catch(Exception e) {
            log(e.getMessage());
            return new ArrayList<InventoryBean>();
        }
        return getAll();
    }

    public ArrayList<InventoryBean> getAll(){
        ArrayList<InventoryBean> beans = new ArrayList<InventoryBean>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt = conn.createStatement();
            String query="SELECT id, category, name, quantity, size, color, description from sjc_inventory;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                InventoryBean bean = new InventoryBean();
                bean.setId(rs.getInt("id"));
                bean.setCategory(rs.getString("category"));
                bean.setName(rs.getString("name"));
                bean.setQuantity(rs.getInt("quantity"));
                bean.setSize(rs.getString("size"));
                bean.setColor(rs.getString("color"));
                bean.setDescription(rs.getString("description"));
                beans.add(bean);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            return new ArrayList<InventoryBean>();
        }
        return beans;
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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(String lastEdit) {
        this.lastEdit = lastEdit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

