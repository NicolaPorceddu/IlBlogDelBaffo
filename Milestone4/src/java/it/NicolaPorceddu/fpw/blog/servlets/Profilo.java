/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.NicolaPorceddu.fpw.blog.servlets;

import it.NicolaPorceddu.fpw.blog.models.CommentFactory;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nicola
 */
public class Profilo extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            
            HttpSession session = request.getSession(false);
            request.setCharacterEncoding("UTF-8");
            
            /*Caricamento array di autori.*/
            ArrayList<User> userList = UserFactory.getAuthors();
            request.setAttribute("userList", userList);
            
            /*Se non è presente una sessione, o non vi è un utente loggato, si viene reindirizzati alla
              pagina di login, con un messaggio di errore.*/
            if(session == null || session.getAttribute("userId") == null){
                request.setAttribute("error", true);
                request.getRequestDispatcher("login.html").forward(request, response);
            }
            
            /*Se si cerca di eliminare il profilo, si viene rimandati al form di conferma.*/
            else if(request.getParameter("deleteRequest")!=null){
                request.setAttribute("deleting", true);
            }
            
            /*Se si conferma l'eliminazione, si elimina l'utente dal database, e insieme a lui i commenti e le notizie scritte da lui.
              si efettua il logout e si viene rimandati al form di login.*/
            else if(request.getParameter("delete")!=null){
                int id = (int)session.getAttribute("userId");
                
                CommentFactory.deleteCommentsByAuthor(id);
                NewsFactory.deleteNewsByAuthor(id);
                UserFactory.deleteUser(id);
                
                request.getRequestDispatcher("login.html?logout=1").forward(request, response);
            }

            else{
                int id = (int) session.getAttribute("userId");
                User user = UserFactory.getUserById(id);
                
                /*Se il parametro "save" è diverso da null, si è raggiunta la servlet dal form di modifica dei dati.*/
                if(request.getParameter("save") != null){
                    request.setAttribute("salvataggio", true);

                    /*Vengono acquisiti i nuovi dati dell'utente.*/
                    String nome = request.getParameter("nome");
                    String cognome = request.getParameter("cognome");
                    String username = request.getParameter("username");
                    String img = request.getParameter("immagine");
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
                    String compleanno = request.getParameter("compleanno");
                    String[] compleannoSplit = compleanno.split("-");
                    int giorno = Integer.parseInt(compleannoSplit[2]);
                    int mese = Integer.parseInt(compleannoSplit[1]);
                    int anno = Integer.parseInt(compleannoSplit[0]);

                    /*I dati dell'utente vengono aggiornati*/
                    user.setNome(nome);
                    user.setCognome(cognome);
                    user.setUsername(username);
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setUrlProfImg(img.replace("http://", ""));
                    user.setCompleanno(giorno, mese, anno);

                    if(request.getParameter("tipo").equals("autore"))
                        user.setTipo(User.TipoUtente.AUTORE);
                    else
                        user.setTipo(User.TipoUtente.LETTORE);

                    if(request.getParameter("sesso").equals("uomo"))
                        user.setSesso(User.SessoUtente.UOMO);
                    else
                        user.setSesso(User.SessoUtente.DONNA);

                    user.setIdTipo();
                    user.setIdSesso();
                }

                UserFactory.modifyUser(id, user);
                
                request.setAttribute("user", user);
            }
            
            request.getRequestDispatcher("profilo.jsp").forward(request, response);
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
