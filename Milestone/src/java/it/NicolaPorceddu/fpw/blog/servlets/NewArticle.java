/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.NicolaPorceddu.fpw.blog.servlets;

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
@WebServlet(name = "NewArticle", urlPatterns = {"/scriviArticolo.html"})
public class NewArticle extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    /*La servlet gestisce l'accesso a scriviArticolo.jsp*/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()){
            /* TODO output your page here. You may use following sample code. */
            
            HttpSession session = request.getSession(false);
            
            /*Se non è presente una sessione, o non vi è un utente loggato, si viene reindirizzati alla
              pagina di login, con un messaggio di errore.*/
            if(session == null || session.getAttribute("userId") == null){
                request.setAttribute("error", true);
                request.getRequestDispatcher("login.html").forward(request, response);
            }
            
            else{
                /*Se l'utente loggato non è un autore, si visualizza un messaggio di errore.*/
                boolean author = (boolean)session.getAttribute("author");
                request.setAttribute("author", author);
                if(author == false){
                    request.getRequestDispatcher("articoli.jsp").forward(request, response);
                }

                /*Se il parametro "nid" è diverso da null, si sta richiamado la servlet per modificare un articolo
                  già esistente, quindi il form di modifica conterrà tutte ciò che l'articolo presenta al suo interno.*/
                if(request.getParameter("nid") != null && request.getParameter("save") == null){
                    String id = request.getParameter("nid");
                    int idNews = Integer.parseInt(id);
                    NewsFactory nFactory = NewsFactory.getInstance();
                    News news = nFactory.getNewsById(idNews);
                    String data = news.getData().toStringReverse();
                    int[] categories = news.getCategories();

                    request.setAttribute("news", news);
                    request.setAttribute("data", data);
                    request.setAttribute("idNews", idNews);
                    request.setAttribute("categories", categories);
                    request.setAttribute("noDataModify", true);
                }

                /*Se il parametro "nid" non esiste, significa che si sta creando un nuovo articolo, di conseguenza
                  verrà creato un nuovo id per la news (in questo esempio uguale a 3, poichè nella factory sono 
                  presenti 3 articoli).*/
                if(request.getParameter("nid") == null && request.getParameter("save") == null){
                    int idNews = 4;

                    request.setAttribute("idNews", idNews);
                }

                /*Se il parametro "save" è diverso da null, si è effettuato un accesso alla servlet tramite il pulsante
                  di salvataggio dell'articolo*/
                if(request.getParameter("save") != null){
                    News news;

                    /*Vengono acquisiti i dati dal form.*/
                    String titolo = request.getParameter("titolo");
                    String img = request.getParameter("immagine");
                    String didascalia = request.getParameter("didascalia");
                    String contenuto = request.getParameter("contenuto");
                    String[] categorie = request.getParameterValues("categorie");
                    String data = request.getParameter("data");
                    String[] dataSplit = data.split("-");
                    int giorno = Integer.parseInt(dataSplit[2]);
                    int mese = Integer.parseInt(dataSplit[1]);
                    int anno = Integer.parseInt(dataSplit[0]);

                    /*Si ricerca l'id della news che si è scritta/modificata nel form*/
                    String id = request.getParameter("nid");
                    int idNews = Integer.parseInt(id);

                    /*Se l'id corrisponde a 3, si tratta della news appena creata che non è presente nella factory,
                      quindi viene creata ora in modo fittizio.*/
                    if(idNews == 3){
                        news = new News();
                        idNews = 3;
                        news.setId(idNews);  
                    }
                    /*In caso normale si ricerca una news con l'id corrispondente all'interno della factory.*/
                    else{
                        news = NewsFactory.getInstance().getNewsById(idNews);
                    }

                    /*Attraverso i setter vengono effettuate le modifiche.*/
                    news.setTitolo(titolo);
                    news.setDidascalia(didascalia);
                    news.setImg(img.replace("http://M1/", ""));
                    news.setContenuto(contenuto);
                    news.setCategorie(categorie);
                    int[] categories = news.getCategories();

                    /*Si rimandano alla jsp i nuovi dati, per la visualizzazione delle modifiche*/
                    request.setAttribute("news", news);
                    request.setAttribute("data", data);
                    request.setAttribute("idNews", idNews);
                    request.setAttribute("categories", categories);
                    request.setAttribute("salvataggio", true);
                }

                ArrayList<User> userList = UserFactory.getInstance().getAuthors();
                request.setAttribute("userList", userList);

                request.getRequestDispatcher("scriviArticolo.jsp").forward(request, response);
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