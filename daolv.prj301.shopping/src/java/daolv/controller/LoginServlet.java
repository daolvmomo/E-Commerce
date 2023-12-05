/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package daolv.controller;

import daolv.registration.RegistrationDAO;
import daolv.registration.RegistrationDTO;
import daolv.util.DBHelper;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final String SEARCH_PAGE = "search.jsp";
    private final String INVALID_PAGE = "invalid.html";

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
        PrintWriter out = response.getWriter();
        //1. get all parameters
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String url = INVALID_PAGE;

        try {
            //2. call DAO
            //2.1 new DAO object 
            RegistrationDAO dao = new RegistrationDAO();
            //2.2 call method of DAO 
//            boolean result = dao.checkLogin(username, password);
            RegistrationDTO result = dao.checkLogin(username, password);
            //3. process result
            if (result != null) {
                url = SEARCH_PAGE;
                //add cookie to responese
//                Cookie cookie = new Cookie(username, password);
//                cookie.setMaxAge(60 * 10);
//                response.addCookie(cookie);
                //set attribute for wellcomeUsername to print wellcome in search.jsp
                HttpSession session = request.getSession();//default is true: create new
                session.setAttribute("USER_INFO", result);
            }//end username and password are varified
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        } finally {
            //response.sendRedirect(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            //out.close();
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
