/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daolv.controller;

import daolv.registration.RegistrationDAO;
import daolv.registration.RegistrationDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
@WebServlet(name = "StartUpServlet", urlPatterns = {"/StartUpServlet"})
public class StartUpServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
    private final String RESULT_SEARCH_PAGE = "search.jsp";

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
        String url = LOGIN_PAGE;
        try {
            //1. get all cookies
            Cookie[] cookies = request.getCookies();
            Cookie latestCookie = null;

            if (cookies != null) {
                //2.get username and password from the newest cookies 
                latestCookie = cookies[cookies.length - 1];
                String username = latestCookie.getName();
                String password = latestCookie.getValue();
                //3. call DAO
                //3.1 new DAO
                RegistrationDAO dao = new RegistrationDAO();
                //3.2 call method of DAO
//                boolean result = dao.checkLogin(username, password);
                RegistrationDTO result = dao.checkLogin(username, password);
                //4. process result
                if (result != null) {
                    url = RESULT_SEARCH_PAGE;
                } else {
                    if (cookies.length >= 2) {
                        //test second last cookie to make sure that shit cookie not appear again
                        latestCookie = cookies[cookies.length - 2];
                        username = latestCookie.getName();
                        password = latestCookie.getValue();
//                        result = dao.checkLogin(username, password);
                        result = dao.checkLogin(username, password);
                        if (result != null) {
                            url = RESULT_SEARCH_PAGE;
                        }
                    }
                }
                //set attribute for wellcomeUsername to print wellcome in search.jsp
                HttpSession session = request.getSession();
                session.setAttribute("wellcomeUsername", username);
//user is authenticated
            }//end cookies has exist
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            response.sendRedirect(url);
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
