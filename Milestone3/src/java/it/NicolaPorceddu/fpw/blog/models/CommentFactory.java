/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.NicolaPorceddu.fpw.blog.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicola
 */

/*La classe CommentFactory permette di creare e visualizzare un array in cui vengono salvati i commenti presenti
  all'interno del blog. Presenta come attributi: un attributo statico CommentFactory instance, in cui si crea 
  e visualizza l'array, e un array di oggetti Comment che contiene i commenti.*/
public class CommentFactory{
    
    /*Il metodo restituisce tutti i commenti contenuti nel database.*/
    public ArrayList<Comment> getComments(){
        ArrayList<Comment> commentsFromDB = new ArrayList<>();

        try{
            Connection conn = DbManager.getInstance().getDbConnection();
            Statement stmt = conn.createStatement();
            String sql = "select * from comments";
            ResultSet set = stmt.executeQuery(sql);

            while(set.next()){
                Comment commentToAdd = new Comment();
                commentToAdd.setId(set.getInt("idComment"));
                commentToAdd.setCommento(set.getString("commento"));
                commentToAdd.setUtente(UserFactory.getUserById(set.getInt("autore")));
                commentToAdd.setIdNews(set.getInt("idNotizia"));
                
                commentsFromDB.add(commentToAdd);
            }

            stmt.close();
            conn.close();
            
        }catch(SQLException ex){
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return commentsFromDB;
    }
    
    /*Il metodo permette di restituire il commento contenuto nel database, associato all'id scelto.*/
    public Comment getCommentById(int id){
        try{
            Connection conn = DbManager.getInstance().getDbConnection();
            String sql = "select * from comments where idComment = ?";
            Comment commentToReturn = new Comment();

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet set = stmt.executeQuery();

            if (set.next()){ //Se ho trovato l'utente
                commentToReturn.setId(set.getInt("idComment"));
                commentToReturn.setCommento(set.getString("commento"));
                commentToReturn.setUtente(UserFactory.getUserById(set.getInt("autore")));
                commentToReturn.setIdNews(set.getInt("idNotizia"));
            }

            stmt.close();
            conn.close();

            return commentToReturn;
            
        }catch(SQLException ex){
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    /*Il metodo permette di restituire un array di commenti contenuti nel database, relativi alla notizia con id scelto.*/
    public static ArrayList<Comment> getCommentsByIdNews(int id){
        ArrayList<Comment> listToReturn = new ArrayList<>();

        try{
            Connection conn = DbManager.getInstance().getDbConnection();
            String sql = "select * from comments where idNotizia = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet set = stmt.executeQuery();

            while(set.next()){
                Comment commentToAdd = new Comment();
                commentToAdd.setId(set.getInt("idComment"));
                commentToAdd.setCommento(set.getString("commento"));
                commentToAdd.setUtente(UserFactory.getUserById(set.getInt("autore")));
                commentToAdd.setIdNews(set.getInt("idNotizia"));
                
                listToReturn.add(commentToAdd);
            }

            stmt.close();
            conn.close();
        }catch(SQLException ex){
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listToReturn;
    }
    
    /*Il metodo elimina i commenti dal database, relativi alla notizia con id scelto.*/
    public static void deleteCommentsByNews(int idNews){
        try{
            Connection conn = DbManager.getInstance().getDbConnection();
            conn.setAutoCommit(false);
            String sql = "delete from comments where idNews = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, idNews);

            try{
                String operazioneRischiosa = "";
            }catch(Exception e){
                
                conn.rollback();
                return;
            }
            
            int rows = stmt.executeUpdate();
            
            if (rows == 1)
                System.out.println("Insert ok!");
            
            conn.commit();
            stmt.close();
            conn.close();
            
        }catch(SQLException ex){
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*Il metodo elimina i commenti dal database, relativi all'autore con id scelto.*/
    public static void deleteCommentsByAuthor(int idAuthor){
        try{
            Connection conn = DbManager.getInstance().getDbConnection();
            conn.setAutoCommit(false);
            String sql = "delete from comments where autore = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, idAuthor);

            try{
                String operazioneRischiosa = "";
            }catch(Exception e){
                
                conn.rollback();
                return;
            }
            
            int rows = stmt.executeUpdate();
            
            if (rows == 1)
                System.out.println("Insert ok!");
            
            conn.commit();
            stmt.close();
            conn.close();
            
        }catch(SQLException ex){
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}