/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daolv.util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author admin
 */
public class DBHelper {

    public static Connection createConnection()
            throws /*ClassNotFoundException*/ NamingException, SQLException {
        //1. get current context
        Context currentContext = new InitialContext();
        //lookup tomcat context
        Context tomcatContext = (Context) currentContext.lookup("java:comp/env");
        //3. look up our datasource
        DataSource ds = (DataSource) tomcatContext.lookup("SE1708DS");
        //4. open connection
        Connection con = ds.getConnection();
        
        return con;
//        //1. load drive
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. create connecton String url
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=master";
//        //3. open connections
//        Connection con = DriverManager.getConnection(url,"sa","123456");
//        
//        return con;
    }

}
