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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nicola
 */
public class Utente extends HttpServlet {

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
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()){
            
            HttpSession session = request.getSession(false);
            
            /*Se la sessione non è presente, loggedIn vale false o non esiste, viene fatta una redirect
              al form di login.*/
            if((session == null) ||
              (session.getAttribute("loggedIn")!= null && !session.getAttribute("loggedIn").equals(true)) ||
              (session.getAttribute("loggedIn") == null)){
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            
            else{
                /*I dati dell'utente loggato vengono settati nella sessione e si richiama la servlet Notizie.java.*/
                int idUtente = (int) session.getAttribute("userId");
                User user = UserFactory.getUserById(idUtente);
                boolean author = false;

                session.setAttribute("user", user);

                if(user.getTipo() == User.TipoUtente.AUTORE)
                    author = true;

                session.setAttribute("author", author);

                request.getRequestDispatcher("notizie.html").forward(request, response);
            }
            
            request.getRequestDispatcher("notizie.html").forward(request, response);
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