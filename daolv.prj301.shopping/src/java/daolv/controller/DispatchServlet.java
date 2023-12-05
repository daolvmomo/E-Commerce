package daolv.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
    private final String CREATE_ACCOUNT_CONTROLLER ="CreateAccountServlet";
    private final String VIEW_CART_PAGE = "cart.jsp";

    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
    private final String START_UP_CONTROLLER = "StartUpServlet";
    private final String LOGOUT_CONTROLLER = "LogoutServlet";

    private final String ADD_ITEM_TO_CART_CONTROLLER = "AddItemToCartServlet";
    private final String REMOVE_ITEMS_FROM_CART_CONTROLLER = "RemoveItemsFromCartServlet";

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

        //1. which button dis user click?
        String button = request.getParameter("btAction");

        try {
            if (button == null) {//welcome file trigget
                url = START_UP_CONTROLLER;
            } else if (button.equals("Login")) {//user click login button
                url = LOGIN_CONTROLLER;
            } else if (button.equals("Search")) {//user click search button
                url = SEARCH_LASTNAME_CONTROLLER;
            } else if (button.equals("Delete")) {//user click delete button
                url = DELETE_ACCOUNT_CONTROLLER;
            } else if (button.equals("Update")) {//user click delete button
                url = UPDATE_ACCOUNT_CONTROLLER;
            } else if (button.equals("Logout")) {
                url = LOGOUT_CONTROLLER;
            } else if (button.equals("Create New Account")) {
                url = CREATE_ACCOUNT_CONTROLLER;
                
            } else if (button.equals("Add Book to Your Cart")) {
                url = ADD_ITEM_TO_CART_CONTROLLER;           
            } else if (button.equals("View Your Cart")) {
                url = VIEW_CART_PAGE;
            } else if (button.equals("Remove Selected Items")) {
                url = REMOVE_ITEMS_FROM_CART_CONTROLLER;
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
