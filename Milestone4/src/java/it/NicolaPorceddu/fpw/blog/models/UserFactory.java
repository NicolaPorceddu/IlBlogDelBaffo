/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.NicolaPorceddu.fpw.blog.models;

import it.NicolaPorceddu.fpw.blog.models.User.SessoUtente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import it.NicolaPorceddu.fpw.blog.models.User.TipoUtente;
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
/*La classe NewsFactory permette di creare e visualizzare un array in cui vengono salvati gli utenti presenti
  all'interno del blog. Presenta come attributi: un attributo statico UserFactory instance, in cui si crea 
  e visualizza l'array, e un array di oggetti User che contiene gli utenti.*/
public class UserFactory{  
    /*Il metodo restituisce un array di tutti gli articoli salvati nel database.*/
    public ArrayList<User> getUsers(){
        ArrayList<User> usersFromDB = new ArrayList<>();

        try{
            Connection conn = DbManager.getInstance().getDbConnection();
            Statement stmt = conn.createStatement();
            String sql = "select * from users";
            ResultSet set = stmt.executeQuery(sql);

            while(set.next()){
                User userToAdd = new User();
                userToAdd.setId(set.getInt("idUser"));
                userToAdd.setNome(set.getString("nome"));
                userToAdd.setCognome(set.getString("cognome"));
                userToAdd.setUsername(set.getString("username"));
                userToAdd.setEmail(set.getString("email"));
                userToAdd.setPassword(set.getString("password"));
                userToAdd.setUrlProfImg(set.getString("urlProfImg"));
                if(set.getInt("tipo") == 1)
                    userToAdd.setTipo(TipoUtente.AUTORE);
                else
                    userToAdd.setTipo(TipoUtente.LETTORE);
                if(set.getInt("SESSO") == 1)
                    userToAdd.setSesso(SessoUtente.UOMO);
                else
                    userToAdd.setSesso(SessoUtente.DONNA);

                userToAdd.setIdTipo();
                userToAdd.setIdSesso();
                userToAdd.setCompleanno(set.getInt("giorno"), set.getInt("mese"), set.getInt("anno"));

                usersFromDB.add(userToAdd);
            }

            stmt.close();
            conn.close();
        }catch(SQLException ex){
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usersFromDB;
    }

    /*Il metodo restituisce l'utente contenuto nel database con l'id scelto.*/
    public static User getUserById(int id){
        try{
            Connection conn = DbManager.getInstance().getDbConnection();
            String sql = "select * from users where idUser = ?";
            User userToReturn = new User();

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet set = stmt.executeQuery();

            if (set.next()){ //Se ho trovato l'utente
                userToReturn.setId(set.getInt("idUser"));
                userToReturn.setNome(set.getString("nome"));
                userToReturn.setCognome(set.getString("cognome"));
                userToReturn.setUsername(set.getString("username"));
                userToReturn.setEmail(set.getString("email"));
                userToReturn.setPassword(set.getString("password"));
                userToReturn.setUrlProfImg(set.getString("urlProfImg"));
                if(set.getInt("tipo") == 1)
                    userToReturn.setTipo(TipoUtente.AUTORE);
                else
                    userToReturn.setTipo(TipoUtente.LETTORE);
                if(set.getInt("SESSO") == 1)
                    userToReturn.setSesso(SessoUtente.UOMO);
                else
                    userToReturn.setSesso(SessoUtente.DONNA);

                userToReturn.setIdTipo();
                userToReturn.setIdSesso();
                userToReturn.setCompleanno(set.getInt("giorno"), set.getInt("mese"), set.getInt("anno"));
            }

            stmt.close();
            conn.close();

            return userToReturn;
            
        }catch(SQLException ex){
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /*Il metodo verifica il corretto inserimento di email e password durante il login.*/
    public static boolean loginEmail(String email, String password){
        try {
            Connection conn = DbManager.getInstance().getDbConnection();
            String sql = "select * from users where email = ? and password = ?";
            Boolean loggedIn = false;

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet set = stmt.executeQuery();

            loggedIn = set.next();

            stmt.close();
            conn.close();

            return loggedIn;

        } catch (SQLException ex) {
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    /*Il metodo verifica il corretto inserimento di username e password durante il login.*/
    public static boolean loginUsername(String username, String password){
        try {
            Connection conn = DbManager.getInstance().getDbConnection();
            String sql = "select * from users where username = ? and password = ?";
            Boolean loggedIn = false;

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet set = stmt.executeQuery();

            loggedIn = set.next();

            stmt.close();
            conn.close();

            return loggedIn;

        } catch (SQLException ex) {
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    /*Il metodo restituisce l'utente contenuto nel database, corrispondente all'email scelta.*/
    public static User getUserByEmail(String email){
        try {
            Connection conn = DbManager.getInstance().getDbConnection();
            String sql = "select * from users where email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet set = stmt.executeQuery();

            User userToReturn = new User();
            
            if(set.next()){
                userToReturn.setId(set.getInt("idUser"));
                userToReturn.setNome(set.getString("nome"));
                userToReturn.setCognome(set.getString("cognome"));
                userToReturn.setUsername(set.getString("username"));
                userToReturn.setEmail(set.getString("email"));
                userToReturn.setPassword(set.getString("password"));
                userToReturn.setUrlProfImg(set.getString("urlProfImg"));
                if(set.getInt("tipo") == 1)
                    userToReturn.setTipo(TipoUtente.AUTORE);
                else
                    userToReturn.setTipo(TipoUtente.LETTORE);
                if(set.getInt("SESSO") == 1)
                    userToReturn.setSesso(SessoUtente.UOMO);
                else
                    userToReturn.setSesso(SessoUtente.DONNA);

                userToReturn.setIdTipo();
                userToReturn.setIdSesso();
                userToReturn.setCompleanno(set.getInt("giorno"), set.getInt("mese"), set.getInt("anno"));
            }

            stmt.close();
            conn.close();

            return userToReturn;
        }catch(SQLException ex){
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /*Il metodo restituisce l'utente contenuto nel database, corrispondente all'username scelto.*/
    public static User getUserByUsername(String username){
        try {
            Connection conn = DbManager.getInstance().getDbConnection();
            String sql = "select * from users where username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet set = stmt.executeQuery();

            User userToReturn = new User();
            
            if(set.next()){
                userToReturn.setId(set.getInt("idUser"));
                userToReturn.setNome(set.getString("nome"));
                userToReturn.setCognome(set.getString("cognome"));
                userToReturn.setUsername(set.getString("username"));
                userToReturn.setEmail(set.getString("email"));
                userToReturn.setPassword(set.getString("password"));
                userToReturn.setUrlProfImg(set.getString("urlProfImg"));
                if(set.getInt("tipo") == 1)
                    userToReturn.setTipo(TipoUtente.AUTORE);
                else
                    userToReturn.setTipo(TipoUtente.LETTORE);
                if(set.getInt("SESSO") == 1)
                    userToReturn.setSesso(SessoUtente.UOMO);
                else
                    userToReturn.setSesso(SessoUtente.DONNA);

                userToReturn.setIdTipo();
                userToReturn.setIdSesso();
                userToReturn.setCompleanno(set.getInt("giorno"), set.getInt("mese"), set.getInt("anno"));
            }

            stmt.close();
            conn.close();

            return userToReturn;
        }catch(SQLException ex){
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

     /*Il metodo restituisce un array contenente gli utenti contenuti nel database, registrati come autori.*/
    public static ArrayList<User> getAuthors(){
        ArrayList<User> listToReturn = new ArrayList<>();

        try{
            Connection conn = DbManager.getInstance().getDbConnection();
            String sql = "select * from users where tipo = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, 1);
            ResultSet set = stmt.executeQuery();

            while(set.next()){
                User userToAdd = new User();
                userToAdd.setId(set.getInt("idUser"));
                userToAdd.setNome(set.getString("nome"));
                userToAdd.setCognome(set.getString("cognome"));
                userToAdd.setUsername(set.getString("username"));
                userToAdd.setEmail(set.getString("email"));
                userToAdd.setPassword(set.getString("password"));
                userToAdd.setUrlProfImg(set.getString("urlProfImg"));
                if(set.getInt("tipo") == 1)
                    userToAdd.setTipo(TipoUtente.AUTORE);
                else
                    userToAdd.setTipo(TipoUtente.LETTORE);
                if(set.getInt("SESSO") == 1)
                    userToAdd.setSesso(SessoUtente.UOMO);
                else
                    userToAdd.setSesso(SessoUtente.DONNA);

                userToAdd.setIdTipo();
                userToAdd.setIdSesso();
                userToAdd.setCompleanno(set.getInt("giorno"), set.getInt("mese"), set.getInt("anno"));

                listToReturn.add(userToAdd);
            }
            
            stmt.close();
            conn.close();
        }catch(SQLException ex){
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listToReturn;
    }
    
    /*Il metodo elimina un utente dal database.*/
    public static void deleteUser(int idUser){
        try{
            Connection conn = DbManager.getInstance().getDbConnection();
            conn.setAutoCommit(false);
            String sql = "delete from users where idUser = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, idUser);

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
    
    /*Il metodo modifica i dati di un utente contenuto nel database.*/
    public static void modifyUser(int idUser, User user){
        try{
            Connection conn = DbManager.getInstance().getDbConnection();
            conn.setAutoCommit(false);
            String sql = "update users set nome = ?, cognome = ?, username = ?, email = ?, password = ?, urlProfImg = ?, "
                    + "tipo = ?, sesso = ?, giorno = ?, mese = ?, anno = ? where idUser = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getCognome());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPassword());
            stmt.setString(6, user.getUrlProfImg());
            stmt.setInt(7, user.getIdTipo());
            stmt.setInt(8, user.getIdSesso());
            stmt.setInt(9, user.getGiorno());
            stmt.setInt(10, user.getMese());
            stmt.setInt(11, user.getAnno());
            stmt.setInt(12, idUser);

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
    
    /*Il metodo ricerca gli utenti i cui nomi o cognomi contengono la sequenza di caratteri inseriti dall'utente
      nella barra di ricerca.*/
    public static ArrayList<User> searchAuthors(String toSearch){
        ArrayList<User> listToReturn = new ArrayList<>();
        
        try{
            Connection conn = DbManager.getInstance().getDbConnection();
            String sql = "select * from users where tipo = 1 and (LOWER(nome) like ? or LOWER(cognome) like ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, "%" + toSearch.toLowerCase() + "%");
            stmt.setString(2, "%" + toSearch.toLowerCase() + "%");
            
            ResultSet set = stmt.executeQuery();

            while(set.next()){
                User userToAdd = new User();
                userToAdd.setId(set.getInt("idUser"));
                userToAdd.setNome(set.getString("nome"));
                userToAdd.setCognome(set.getString("cognome"));
                userToAdd.setUsername(set.getString("username"));
                userToAdd.setEmail(set.getString("email"));
                userToAdd.setPassword(set.getString("password"));
                userToAdd.setUrlProfImg(set.getString("urlProfImg"));
                if(set.getInt("tipo") == 1)
                    userToAdd.setTipo(TipoUtente.AUTORE);
                else
                    userToAdd.setTipo(TipoUtente.LETTORE);
                if(set.getInt("SESSO") == 1)
                    userToAdd.setSesso(SessoUtente.UOMO);
                else
                    userToAdd.setSesso(SessoUtente.DONNA);

                userToAdd.setIdTipo();
                userToAdd.setIdSesso();
                userToAdd.setCompleanno(set.getInt("giorno"), set.getInt("mese"), set.getInt("anno"));

                listToReturn.add(userToAdd);
            }
            
            stmt.close();
            conn.close();
        }catch(SQLException ex){
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listToReturn;
    }
}

