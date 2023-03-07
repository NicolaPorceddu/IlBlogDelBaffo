/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.NicolaPorceddu.fpw.blog.models;

import java.util.ArrayList;

/**
 *
 * @author Nicola
 */

/*La classe CommentFactory permette di creare e visualizzare un array in cui vengono salvati i commenti presenti
  all'interno del blog. Presenta come attributi: un attributo statico CommentFactory instance, in cui si crea 
  e visualizza l'array, e un array di oggetti Comment che contiene i commenti.*/
public class CommentFactory{
    private static CommentFactory instance;
    private ArrayList<Comment> commentList = new ArrayList<>();
    
    /*Il costruttore della factory crea un array contenente i commenti presenti nel blog.*/
    private CommentFactory(){
        UserFactory userFactory = UserFactory.getInstance();
        
        Comment comment1 = new Comment();
        comment1.setId(0);
        comment1.setIdNews(0);
        comment1.setCommento("Alcune volte si è proprio convinti che una porta vada aperta in un senso, invece non è così.");
        comment1.setUtente(userFactory.getUserById(3));
        
        Comment comment2 = new Comment();
        comment2.setId(1);
        comment2.setIdNews(2);
        comment2.setCommento("Non sono bravi a dividersi ma sono bravi a rubare!.");
        comment2.setUtente(userFactory.getUserById(4));
        
        Comment comment3 = new Comment();
        comment3.setId(2);
        comment3.setIdNews(0);
        comment3.setCommento("Nel dubbio è sempre meglio aspettare che qualcuno la apra prima di te.");
        comment3.setUtente(userFactory.getUserById(5));
        
        commentList.add(comment1);
        commentList.add(comment2);
        commentList.add(comment3);
    }
    
    public static CommentFactory getInstance(){
        if (instance == null)
            instance = new CommentFactory();
        return instance;
    }
    
    public ArrayList<Comment> getComments(){
        return commentList;
    }
    
    /*Il metodo permette di restituire il commento dell'id scelto.*/
    public Comment getCommentById(int id){
        for (Comment comment : commentList)
            if (comment.getId() == id)
                return comment;
        
        return null;
    }
    
    /*Il metodo permette di restituire un array di commenti della notizia relativa all'id della notizia scelta.*/
    public ArrayList<Comment> getCommentsByIdNews(int id){
        ArrayList<Comment> listToReturn = new ArrayList<>();
        
        for (Comment comment : commentList)
            if (comment.getIdNews() == id)
                listToReturn.add(comment);
            
        return listToReturn;
    }
}