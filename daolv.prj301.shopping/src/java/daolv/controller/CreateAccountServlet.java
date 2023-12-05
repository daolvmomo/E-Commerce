/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daolv.controller;

import daolv.registration.RegistrationCreateError;
import daolv.registration.RegistrationDAO;
import daolv.registration.RegistrationDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {

    private final String USER_LENGTH_ERROR
            = "Username is required typing from 6 to 30 charactoers";
    private final String PASSWORD_LENGTH_ERROR
            = "Password is required typing from 6 to 20 charactoers";
    private final String CONFIRM_PASSWORD_ERROR
            = "Confirm not match with passwod";
    private final String FULLNAME_LENGTH_ERROR
            = "Fullname is required typing from 2 to 50 charactoers";
    private final String USER_EXIST_ERROR
            = "Fullname is required typing from 2 to 50 charactoers";
    
    private final String CREATE_ACCOUNT_PAGE = "createAccount.jsp";
    private final String LOGIN_PAGE = "login.html";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //1. get all parameter
        String userName = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullName");
        
        RegistrationCreateError errors = new RegistrationCreateError();
        boolean foundError = false;

        String url = CREATE_ACCOUNT_PAGE;

        try {
            //2.verify all error
            if (userName.trim().length() < 6
                    || userName.trim().length() > 30) {
                foundError = true;
                errors.setUsernameLengthErr(USER_LENGTH_ERROR);
            }

            if (password.trim().length() < 6
                    || password.trim().length() > 20) {
                foundError = true;
                errors.setPasswordLengthErr(PASSWORD_LENGTH_ERROR);
            } else if (!confirm.trim().equals(password.trim())) {
                foundError = true;
                errors.setConfirmNotMatched(CONFIRM_PASSWORD_ERROR);
            }

            if (fullName.trim().length() < 6
                    || fullName.trim().length() > 30) {
                foundError = true;
                errors.setFullNameLengthErr(FULLNAME_LENGTH_ERROR);
            }
            if (foundError) {//errors occur
                //cache to attribute
                request.setAttribute("CREATE_ERROR", errors);
            } else {
                //3.call DAO
                //3.1 new DAO
                RegistrationDAO dao = new RegistrationDAO();
                //3.2 call DAO method
                RegistrationDTO dto = new RegistrationDTO(userName, password, fullName, false);
                boolean result = dao.creaeAccount(dto);
                //4.process result
                if (result) {
                    url = LOGIN_PAGE;
                }//insert action is seccessfully
            }
        } catch (NamingException ex) {
            log("CreateAccountServlet _ Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            String errMsg = ex.getMessage();
            log("CreateAccountServlet _ SQL: " + errMsg);
            if (errMsg.contains("duplicate")) {
                errors.setUsernameIsExisted(userName + USER_EXIST_ERROR);
                request.setAttribute("CREATE_ERROR", errors);
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
