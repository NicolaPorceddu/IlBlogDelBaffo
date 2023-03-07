/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.NicolaPorceddu.fpw.blog.models;

/**
 *
 * @author Nicola
 */
/*La classe Comment permette di creare degli oggetti rappresentanti i commenti di un articolo.
  Presenta come attributi un id personale, un id corrispondente all'id della notizia a cui è collegato il
  commento, il contenuto del commento e l'utente che lo ha scritto.*/
public class Comment{
    private int id;
    private int idNews;
    private String commento;
    private User utente;
    
    /*Getter e Setter degli attributi della classe.*/
    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    public User getUtente() {
        return utente;
    }

    public void setUtente(User utente) {
        this.utente = utente;
    }

    public int getIdNews() {
        return idNews;
    }

    public void setIdNews(int idNews) {
        this.idNews = idNews;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
