// https://github.com/RahulGrover12/
// Rahul Grover
package HMS;

import java.sql.*;

public class DBConnection {
    private static final String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String user = "root";
    private static final String pass = "rahul123";
//    private java.sql.Connection connection;
    Connection conn;
    public DBConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public java.sql.Connection getConnection(){
        return conn;
    }
}
