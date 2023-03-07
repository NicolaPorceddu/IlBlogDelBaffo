/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.NicolaPorceddu.fpw.blog.servlets;

import it.NicolaPorceddu.fpw.blog.models.User;
import it.NicolaPorceddu.fpw.blog.models.UserFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nicola
 */
public class Login extends HttpServlet{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    /*La servlet gestisce l'accesso a login.jsp, in caso si debba effettuare un login, o a notizie.jsp,
      in caso ci sia già un utente loggato.*/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()){
            /* TODO output your page here. You may use following sample code. */
            
            HttpSession session = request.getSession();
            
            ArrayList<User> userList = UserFactory.getInstance().getAuthors();
            request.setAttribute("userList", userList);
            
            /*Se il parametro "logout" è diverso da null, la servlet annulla la sessione corrente e richiama
              login.jsp*/
            if(request.getParameter("logout") != null){
                session.invalidate();
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
                
            /*In caso di utente già loggato, la servlet richiama notizie.html*/
            if (session.getAttribute("loggedIn") != null && session.getAttribute("loggedIn").equals(true)){
                response.sendRedirect("notizie.html");
                return;
            }
            
            /*In caso di utente non loggato, e si accede alla servlet tramite il form di login.jsp,
              vengono acquisiti email/username e password*/
            else{
                String name = request.getParameter("nome");
                String password = request.getParameter("pswd");
                UserFactory factory = UserFactory.getInstance();
                
                /*Se email e password sono verificati, si richiama la servlet Utente.java*/
                if (name != null && password != null && factory.loginEmail(name, password)){
                    //email e password esistono e sono validi
                    int userId = factory.getUserByEmail(name).getId();
                    session.setAttribute("userId", userId);
                    session.setAttribute("loggedIn", true);
                    
                    request.getRequestDispatcher("Utente").forward(request, response);
                    return;
                }
                /*Se username e password sono verificati, si richiama la servlet Utente.java*/
                else if (name != null && password != null && factory.loginUsername(name, password)){
                    //username e password esistono e sono validi
                    int userId = factory.getUserByUsername(name).getId();
                    session.setAttribute("userId", userId);
                    session.setAttribute("loggedIn", true);
                    
                    request.getRequestDispatcher("Utente").forward(request, response);
                    return;
                }
                
                /*Se i dati inseriti non sono corretti, viene richiamata la servlet Utente.java che rimanderà
                  al form di login, con comunicazione di un messaggio di errore.*/
                else if(name!= null && password != null){
                    //ho sbagliato facendo il login
                    request.setAttribute("invalidData", true);
                    request.getRequestDispatcher("Utente").forward(request, response);
                }
            }
            
            /*Se si richiama la servlet, senza utilizzare il form di login, si visualizza login.jsp*/
            request.getRequestDispatcher("login.jsp").forward(request, response);
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