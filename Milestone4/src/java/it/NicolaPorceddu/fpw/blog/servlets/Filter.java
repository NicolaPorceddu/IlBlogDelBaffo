/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.NicolaPorceddu.fpw.blog.servlets;

import it.NicolaPorceddu.fpw.blog.models.News;
import it.NicolaPorceddu.fpw.blog.models.User;
import it.NicolaPorceddu.fpw.blog.models.UserFactory;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nicola
 */
public class Filter extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    /*La servlet gestisce l'accesso a filter.json, in caso di debba effettuare una ricerca. Lo script richiama
      la servlet passando come parametro una stringa inserita dall'utente nella barra di ricerca e vengono
      ricercate le categorie degli articoli e i nomi e cognomi degli utenti.*/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String command = request.getParameter("cmd");
        
        if (command != null){
            if (command.equals("search")){
                String toSearch = request.getParameter("q");
                ArrayList<User> usersFound;
                ArrayList<String> categoriesFound;
                
                /*Nel caso in cui la barra di ricerca fosse vuota, viene passato un array contenente tutte le
                  categorie e tutti gli utenti.*/
                if(toSearch.equals("")){
                    usersFound = UserFactory.getAuthors();
                    categoriesFound = News.searchCategory();
                }
                /*Nel caso si inserisca una stringa, viene caricato un array contenente gli utenti e le categorie
                  che contengono la sequenza di caratteri, o nessuno se non avviene nessun match.*/
                else{
                    usersFound = UserFactory.searchAuthors(toSearch);
                    categoriesFound = News.searchCategory(toSearch);
                }
                
                request.setAttribute("userList", usersFound);
                request.setAttribute("categoryList", categoriesFound);
                
                response.setContentType("application/json");
                response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
                response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
                
                request.getRequestDispatcher("authorsCategoriesFound.jsp").forward(request, response);
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
