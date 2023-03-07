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
                    News news = NewsFactory.getNewsById(idNews);
                    String data = news.getData().toStringReverse();
                    int[] categories = news.getCategories();

                    request.setAttribute("news", news);
                    request.setAttribute("articolo", news.getContenuto().replace("<br/>", "\n"));
                    request.setAttribute("data", data);
                    request.setAttribute("idNews", idNews);
                    request.setAttribute("categories", categories);
                    request.setAttribute("noDataModify", true);
                }

                /*Se il parametro "nid" non esiste, significa che si sta creando un nuovo articolo. Viene specificato alla jsp di
                  mostrare il cmapo di inserimento della data.*/
                if(request.getParameter("nid") == null && request.getParameter("save") == null){
                    request.setAttribute("addDate", true);
                }

                /*Se il parametro "save" è diverso da null, si è effettuato un accesso alla servlet tramite il pulsante
                  di salvataggio dell'articolo*/
                if(request.getParameter("save") != null){
                    News news = new News();
                    int[] categories = new int[]{0,0,0,0,0,0};
                    String[] categorie;
                    String data;
                    String[] dataSplit;
                    int giorno = 0;
                    int mese = 0;
                    int anno = 0;
                    String id;
                    int idNews;
                    
                    /*Vengono acquisiti i dati dal form.*/
                    news.setTitolo(request.getParameter("titolo"));
                    news.setContenuto(request.getParameter("contenuto").replace("\n", "<br/>"));
                    news.setImg(request.getParameter("immagine").replace("http://", ""));
                    news.setDidascalia(request.getParameter("didascalia"));
                    
                    if(request.getParameter("data") != null){
                        data = request.getParameter("data");
                        dataSplit = data.split("-");
                        giorno = Integer.parseInt(dataSplit[2]);
                        mese = Integer.parseInt(dataSplit[1]);
                        anno = Integer.parseInt(dataSplit[0]);
                        news.setData(giorno, mese, anno);
                    }
                    
                    categorie = request.getParameterValues("categorie");
                    if(categorie == null){
                        request.setAttribute("news", news);
                        request.setAttribute("articolo", news.getContenuto().replace("<br/>", "\n"));
                        request.setAttribute("categorieError", true);
                    }
                        
                    else{
                        news.setCategorie(categorie);
                        categories = news.getCategories();
                        int IdAutore = (int)session.getAttribute("userId");
                        news.setAutore(UserFactory.getUserById(IdAutore));
                        
                        /*Nel caso si stia modificando una notizia già esistente, si cerca l'id corrispondente.*/
                        if(request.getParameter("nid") != null){
                           id = request.getParameter("nid");
                           idNews = Integer.parseInt(id);
                        }
                        /*Nel caso si stia creando una nuova notizia, si aggiunge al database le viene assegnato un nuovo id.*/
                        else{
                            NewsFactory.addNews((int)session.getAttribute("userId"));
                            idNews = NewsFactory.lastNewsAdded();
                        }
 
                        /*Vengono effettuate le modifiche.*/
                        if(request.getParameter("data") != null)
                            NewsFactory.modifyNews(idNews, news, news.getData());
                        else
                            NewsFactory.modifyNews(idNews, news);

                        news = NewsFactory.getNewsById(idNews);

                        /*Si rimandano alla jsp i nuovi dati, per la visualizzazione delle modifiche*/
                        request.setAttribute("news", news);
                        request.setAttribute("articolo", news.getContenuto().replace("<br/>", "\n"));
                        request.setAttribute("idNews", idNews);
                        request.setAttribute("categories", categories);
                        request.setAttribute("salvataggio", true);
                    }
                }

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