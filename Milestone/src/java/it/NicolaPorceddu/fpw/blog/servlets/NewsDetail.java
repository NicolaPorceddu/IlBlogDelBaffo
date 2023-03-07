/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.NicolaPorceddu.fpw.blog.servlets;

import it.NicolaPorceddu.fpw.blog.models.Comment;
import it.NicolaPorceddu.fpw.blog.models.CommentFactory;
import it.NicolaPorceddu.fpw.blog.models.News;
import it.NicolaPorceddu.fpw.blog.models.NewsFactory;
import it.NicolaPorceddu.fpw.blog.models.User;
import it.NicolaPorceddu.fpw.blog.models.UserFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Nicola
 */
public class NewsDetail extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    /*La servlet gestisce l'accesso a notizia.jsp*/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()){
            /* TODO output your page here. You may use following sample code. */
            
            /*Viene ricercato e passato alla jsp l'articolo corrispondente all'id contenuto nella richiesta.*/
            NewsFactory nFactory = NewsFactory.getInstance();
            String id = request.getParameter("nid");
            int idNews = Integer.parseInt(id);
            News news = nFactory.getNewsById(idNews);
     
            request.setAttribute("news", news);
            
            /*Vengono ricercati e passati alla jsp i commenti relativi all'articolo*/
            CommentFactory cFactory = CommentFactory.getInstance();
            ArrayList<Comment> commentList = cFactory.getCommentsByIdNews(idNews);
            request.setAttribute("commentList", commentList);
            
            ArrayList<User> userList = UserFactory.getInstance().getAuthors();
            request.setAttribute("userList", userList);
            
            
            request.getRequestDispatcher("notizia.jsp").forward(request, response);
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
