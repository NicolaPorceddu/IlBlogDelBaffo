/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.NicolaPorceddu.fpw.blog.servlets;

import it.NicolaPorceddu.fpw.blog.models.CommentFactory;
import it.NicolaPorceddu.fpw.blog.models.News;
import it.NicolaPorceddu.fpw.blog.models.NewsFactory;
import it.NicolaPorceddu.fpw.blog.models.User;
import it.NicolaPorceddu.fpw.blog.models.UserFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nicola
 */
@WebServlet(name = "Articles", urlPatterns = {"/articoli.html"})
public class Articles extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    /*La servlet gestisce l'accesso a articoli.jsp*/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            HttpSession session = request.getSession(false);
            
            /*Caricamento array di autori.*/
            ArrayList<User> userList = UserFactory.getAuthors();
            request.setAttribute("userList", userList);
                
            /*Se non è presente una sessione, o non vi è un utente loggato, si viene reindirizzati alla
              pagina di login, con un messaggio di errore.*/
            if(session == null || session.getAttribute("userId") == null){
                request.setAttribute("error", true);
                request.getRequestDispatcher("login.html").forward(request, response);
            }
            
            /*Se si cerca di eliminare un articolo, si viene reindirizzati al form di conferma.*/
            else if(request.getParameter("deleteRequest")!=null){
                String id = request.getParameter("nid");
                int idNewsDelete = Integer.parseInt(id);
                
                request.setAttribute("idNewsDelete", idNewsDelete);
                request.setAttribute("deleting", true);
                request.getRequestDispatcher("articoli.jsp").forward(request, response);
            }
            
            else{
                /*Se l'utente loggato non è un autore, si visualizza un messaggio di errore.*/
                boolean author = (boolean)session.getAttribute("author");
                request.setAttribute("author", author);
                if(author == false){
                    request.getRequestDispatcher("articoli.jsp").forward(request, response);
                }

                /*Se viene confermata l'eliminazione di un articolo, viene cancellato dal database e insieme ad esso anche i commenti
                  associati.*/
                if(request.getParameter("delete")!=null){
                    String id = request.getParameter("nid");
                    int idNewsDelete = Integer.parseInt(id);

                    CommentFactory.deleteCommentsByNews(idNewsDelete);
                    NewsFactory.deleteNews(idNewsDelete);

                    request.setAttribute("idNewsDeleted", idNewsDelete);
                    request.setAttribute("deleted", true);
                }
                
                /*Se l'utente è loggato, ed è un autore, si crea un array contenente gli articoli da lui scritti,
                  da mandare alla jsp.*/
                int idUser = (int)session.getAttribute("userId");
                User user = UserFactory.getUserById(idUser);

                ArrayList<News> newsList = NewsFactory.getNewsByAuthor(user);
                request.setAttribute("newsList", newsList);

                request.getRequestDispatcher("articoli.jsp").forward(request, response);
            }
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
