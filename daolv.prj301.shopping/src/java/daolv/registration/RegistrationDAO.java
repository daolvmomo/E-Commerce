/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daolv.registration;

import daolv.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author admin
 */
public class RegistrationDAO implements Serializable {

//    public boolean checkLogin(String username, String password)
//            throws SQLException, /*ClassNotFoundException*/ NamingException {
    public RegistrationDTO checkLogin(String username, String password)
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        // dong tu 1 2 3 thi phai close 3 2 1
        Connection con = null;//1
        PreparedStatement stm = null;//2
        ResultSet rs = null;//3

//        boolean result = false;
        RegistrationDTO result = null;

        try {
            //1. create connection 
            con = DBHelper.createConnection();

            if (con != null) {
                //2. create SQLstring khi xuong hang phai cach 1 khoang trang 
                String sql = "Select lastname, isAdmin "
                        + "From Registration "
                        + "Where username = ? "
                        + "And password = ?";
                //3. create Statement Obj
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. excute Query
                rs = stm.executeQuery();
                //5. process
                if (rs.next()) {
//                    result = true;
                    //mapping
                    //5.1 get data from sesult set
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //5.2 set data to dto
                    result = new RegistrationDTO(username, null, fullName, role);
                }// end username and password are existed
            }// end connection is available 
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }

    public void searchLastname(String searchValue)
            throws SQLException, /*ClassNotFoundException*/ NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        //boolean result = false;

        try {
            //1. create connection 
            con = DBHelper.createConnection();
            if (con != null) {
                //2. create SQLstring khi xuong hang phai cach 1 khoang trang 
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname Like ?";
                //3. create Statement Obj
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. excute Query
                rs = stm.executeQuery();
                //5. process
                while (rs.next()) {
                    //5.1 get data from rs
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //5.2 set data info DTO
                    RegistrationDTO dto = new RegistrationDTO(username, password, fullName, role);
                    //5.3 add DTO int to List
                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }// end accounts had NOT existed
                    this.accounts.add(dto);
                }// end username and password are existed
            }// end connection is available 

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteAccount(String username)
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. create connection 
            con = DBHelper.createConnection();
            if (con != null) {
                //2. create SQLstring khi xuong hang phai cach 1 khoang trang 
                String sql = "Delete From Registration "
                        + "Where username = ?";
                //3. create Statement Obj
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. excute Query
                int effectRows = stm.executeUpdate();
                //5. process
                if (effectRows > 0) {
                    result = true;
                }
                // delete existing account 
            }// end connection is available 

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public boolean updateAccount(String username, String password, boolean role)
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. create connection 
            con = DBHelper.createConnection();
            if (con != null) {
                //2. create SQLstring khi xuong hang phai cach 1 khoang trang 
                String sql = "Update Registration "
                        + "set password = ?, isAdmin = ?  "
                        + "Where username = ?";

                //3. create Statement Obj
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
                //4. excute Query
                int effectRows = stm.executeUpdate();
                //5. process
                if (effectRows > 0) {
                    result = true;
                }
                // delete existing account 
            }// end connection is available 

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public boolean creaeAccount(RegistrationDTO account)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. create connection 
            con = DBHelper.createConnection();
            if (con != null) {
                //2. create SQLstring khi xuong hang phai cach 1 khoang trang 
                String sql = "Insert Into Registration("
                        + "username, password, lastname, isAdmin"
                        + ") Values("
                        + "?, ?, ?, ?"
                        + ")";
                //3. create Statement Obj
                stm = con.prepareStatement(sql);
                stm.setString(1, account.getUsername());
                stm.setString(2, account.getPassword());
                stm.setString(3, account.getFullName());
                stm.setBoolean(4, account.isRole());
                //4. excute Query
                int effectRows = stm.executeUpdate();
                //5. process
                if (effectRows > 0) {
                    result = true;
                }
                // delete existing account 
            }// end connection is available 

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
