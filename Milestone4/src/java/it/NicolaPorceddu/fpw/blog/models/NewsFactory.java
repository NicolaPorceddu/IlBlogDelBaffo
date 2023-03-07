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
/*La classe NewsFactory permette di creare e visualizzare un array in cui vengono salvati gli articoli presenti
  all'interno del blog. Presenta come attributi: un attributo statico NewsFactory instance, in cui si crea 
  e visualizza l'array, e un array di oggetti News che contiene gli articoli.*/
public class NewsFactory{
    
    /*Il metodo restituisce un array di tutti gli articoli contenuti nel database.*/
    public static ArrayList<News> getNews(){
        ArrayList<News> newsFromDB = new ArrayList<>();

        try{
            Connection conn = DbManager.getInstance().getDbConnection();
            Statement stmt = conn.createStatement();
            String sql = "select * from news";
            ResultSet set = stmt.executeQuery(sql);

            /*Viene salvata una news, e aggiunta all'array, per ogni riga trovata nel database.*/
            while(set.next()){
                News newsToAdd = new News();
                newsToAdd.setId(set.getInt("idNews"));
                newsToAdd.setTitolo(set.getString("titolo"));
                newsToAdd.setContenuto(set.getString("contenuto"));
                newsToAdd.setAnteprimaContenuto();
                newsToAdd.setImg(set.getString("img"));
                newsToAdd.setDidascalia(set.getString("didascalia"));
                newsToAdd.setCategorie(new int[]{set.getInt("catPolitica"), set.getInt("catCronaca"), set.getInt("catEsteri"),
                    set.getInt("catEconomia"), set.getInt("catSport"), set.getInt("catCultura")});
                newsToAdd.setAutore(UserFactory.getUserById(set.getInt("autore")));
                newsToAdd.setData(set.getInt("giorno"), set.getInt("mese"), set.getInt("anno"));
                
                newsFromDB.add(newsToAdd);
            }
            
            stmt.close();
            conn.close();
        }catch(SQLException ex){
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return newsFromDB;
    }
    
    /*Il metodo restituisce l'articolo contenuto nel database con id scelto.*/
    public static News getNewsById(int id){
        try{
            Connection conn = DbManager.getInstance().getDbConnection();
            String sql = "select * from news where idNews = ?";
            News newsToReturn = new News();

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet set = stmt.executeQuery();

            if (set.next()){ //Se ho trovato l'utente
                newsToReturn.setId(set.getInt("idNews"));
                newsToReturn.setTitolo(set.getString("titolo"));
                newsToReturn.setContenuto(set.getString("contenuto"));
                newsToReturn.setAnteprimaContenuto();
                newsToReturn.setImg(set.getString("img"));
                newsToReturn.setDidascalia(set.getString("didascalia"));
                newsToReturn.setCategorie(new int[]{set.getInt("catPolitica"), set.getInt("catCronaca"), set.getInt("catEsteri"),
                    set.getInt("catEconomia"), set.getInt("catSport"), set.getInt("catCultura")});
                newsToReturn.setAutore(UserFactory.getUserById(set.getInt("autore")));
                newsToReturn.setData(set.getInt("giorno"), set.getInt("mese"), set.getInt("anno"));
            }

            stmt.close();
            conn.close();

            return newsToReturn;
            
        }catch(SQLException ex){
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    /*Il metodo restituisce un array contenente gli articoli contenuti nel database scritti dall'autore scelto.*/
    public static ArrayList<News> getNewsByAuthor(User author){
        ArrayList<News> listToReturn = new ArrayList<>();
        
        try{
            Connection conn = DbManager.getInstance().getDbConnection();
            String sql = "select * from news where autore = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, author.getId());
            ResultSet set = stmt.executeQuery();

            while(set.next()){
                News newsToAdd = new News();
                newsToAdd.setId(set.getInt("idNews"));
                newsToAdd.setTitolo(set.getString("titolo"));
                newsToAdd.setContenuto(set.getString("contenuto"));
                newsToAdd.setAnteprimaContenuto();
                newsToAdd.setImg(set.getString("img"));
                newsToAdd.setDidascalia(set.getString("didascalia"));
                newsToAdd.setCategorie(new int[]{set.getInt("catPolitica"), set.getInt("catCronaca"), set.getInt("catEsteri"),
                    set.getInt("catEconomia"), set.getInt("catSport"), set.getInt("catCultura")});
                newsToAdd.setAutore(UserFactory.getUserById(set.getInt("autore")));
                newsToAdd.setData(set.getInt("giorno"), set.getInt("mese"), set.getInt("anno"));
                
                listToReturn.add(newsToAdd);
            }

            stmt.close();
            conn.close();
        }catch(SQLException ex){
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return listToReturn;
    }
    
    /*Il metodo restituisce un array contenente gli articoli contenuti nel database della categoria scelta.*/
    public static ArrayList<News> getNewsByCategory(int category){
        ArrayList<News> listToReturn = new ArrayList<>();
        
        try{
            Connection conn = DbManager.getInstance().getDbConnection();
            String sql = "";

            switch(category){
                case 1:
                    sql = "select * from news where catPolitica = 1 ";
                    break;
                case 2:
                    sql = "select * from news where catCronaca = 1 ";
                    break;
                case 3:
                    sql = "select * from news where catEsteri = 1 ";
                    break;
                case 4:
                    sql = "select * from news where catEconomia = 1 ";
                    break;
                case 5:
                    sql = "select * from news where catSport = 1 ";
                    break;
                case 6:
                    sql = "select * from news where catCultura = 1 ";
                    break;
            }

            Statement stmt = conn.prepareStatement(sql);
            ResultSet set = stmt.executeQuery(sql);
            
            while(set.next()){
                News newsToAdd = new News();
                newsToAdd.setId(set.getInt("idNews"));
                newsToAdd.setTitolo(set.getString("titolo"));
                newsToAdd.setContenuto(set.getString("contenuto"));
                newsToAdd.setAnteprimaContenuto();
                newsToAdd.setImg(set.getString("img"));
                newsToAdd.setDidascalia(set.getString("didascalia"));
                newsToAdd.setCategorie(new int[]{set.getInt("catPolitica"), set.getInt("catCronaca"), set.getInt("catEsteri"),
                    set.getInt("catEconomia"), set.getInt("catSport"), set.getInt("catCultura")});
                newsToAdd.setAutore(UserFactory.getUserById(set.getInt("autore")));
                newsToAdd.setData(set.getInt("giorno"), set.getInt("mese"), set.getInt("anno"));
                
                listToReturn.add(newsToAdd);
            }
        }catch(SQLException ex){
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listToReturn;
    }
    
    /*Il metodo crea un nuovo articolo nel database.*/
    public static void addNews(int idAutore){
        try{
            Connection conn = DbManager.getInstance().getDbConnection();
            conn.setAutoCommit(false);
            String sql = "insert into news (titolo, contenuto, img, didascalia, catPolitica, catCronaca, catEsteri, "
                    + "catEconomia, catSport, catCultura, giorno, mese, anno, autore) "
                    + "values (default, default, default, default, default, default, default, default, default, default, "
                    + "default, default, default, ?)";
            
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, idAutore);
            
            try{
                String operazioneRischiosa = "";
            }catch(Exception e){
                
                conn.rollback();
                return;
            }
            
            int rows = stmt.executeUpdate();
            
            if (rows == 1){
                System.out.println("Insert ok!");
            }
            
            conn.commit();
            stmt.close();
            conn.close();
            
        }catch(SQLException ex){
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /*Il metodo modifica un articolo già esistente nel database.*/
    public static void modifyNews(int idNews, News news){
        try{
            Connection conn = DbManager.getInstance().getDbConnection();
            conn.setAutoCommit(false);
            String sql = "update news set titolo = ?, contenuto = ?, img = ?, didascalia = ?, catPolitica = ?, catCronaca = ?, "
                    + "catEsteri = ?, catEconomia = ?, catSport = ?, catCultura = ? "
                    + "where idNews = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, news.getTitolo());
            stmt.setString(2, news.getContenuto());
            stmt.setString(3, news.getImg());
            stmt.setString(4, news.getDidascalia());
            stmt.setInt(5, news.getCategories()[0]);
            stmt.setInt(6, news.getCategories()[1]);
            stmt.setInt(7, news.getCategories()[2]);
            stmt.setInt(8, news.getCategories()[3]);
            stmt.setInt(9, news.getCategories()[4]);
            stmt.setInt(10, news.getCategories()[5]);
            stmt.setInt(11, idNews);

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
    
    /*Il metodo modifica un articolo nel database appena creato.*/
    public static void modifyNews(int idNews, News news, Data data){
        try{
            Connection conn = DbManager.getInstance().getDbConnection();
            conn.setAutoCommit(false);
            String sql = "update news set titolo = ?, contenuto = ?, img = ?, didascalia = ?, catPolitica = ?, catCronaca = ?, "
                    + "catEsteri = ?, catEconomia = ?, catSport = ?, catCultura = ?, giorno = ?, mese = ?, anno = ? "
                    + "where idNews = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, news.getTitolo());
            stmt.setString(2, news.getContenuto());
            stmt.setString(3, news.getImg());
            stmt.setString(4, news.getDidascalia());
            stmt.setInt(5, news.getCategories()[0]);
            stmt.setInt(6, news.getCategories()[1]);
            stmt.setInt(7, news.getCategories()[2]);
            stmt.setInt(8, news.getCategories()[3]);
            stmt.setInt(9, news.getCategories()[4]);
            stmt.setInt(10, news.getCategories()[5]);
            stmt.setInt(11, data.getGiorno());
            stmt.setInt(12, data.getMese());
            stmt.setInt(13, data.getAnno());
            stmt.setInt(14, idNews);

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
    
    /*Il metodo restituisce l'id dell'ultimo articolo presente nel database.*/
    public static int lastNewsAdded(){
        int id = 0;
        
        try{
            Connection conn = DbManager.getInstance().getDbConnection();
            Statement stmt = conn.createStatement();
            String sql = "select * from news order by idNews desc limit 1";
            ResultSet set = stmt.executeQuery(sql);

            if(set.next())
                id = set.getInt("idNews");
            
            return id;
            
        }catch(SQLException ex){
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }
    
    /*Il metodo elimina un articolo dal database.*/
    public static void deleteNews(int idNews){
        try{
            Connection conn = DbManager.getInstance().getDbConnection();
            conn.setAutoCommit(false);
            String sql = "delete from news where idNews = ?";
            
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
    
    /*Il metodo elimina gli articolo contenuti nel database dell'autore scelto.*/
    public static void deleteNewsByAuthor(int idAuthor){
        try{
            Connection conn = DbManager.getInstance().getDbConnection();
            conn.setAutoCommit(false);
            String sql = "delete from news where autore = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, idAuthor);

            /*Il seguente pezzo di codice è implementato
            in questo modo per fini didattici: è una implementazione
            di transazione
            */
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
