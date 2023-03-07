/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.NicolaPorceddu.fpw.blog.models;

import it.NicolaPorceddu.fpw.blog.models.User.TipoUtente;
import java.util.ArrayList;

/**
 *
 * @author Nicola
 */
/*La classe NewsFactory permette di creare e visualizzare un array in cui vengono salvati gli utenti presenti
  all'interno del blog. Presenta come attributi: un attributo statico UserFactory instance, in cui si crea 
  e visualizza l'array, e un array di oggetti User che contiene gli utenti.*/
public class UserFactory{
    private static UserFactory instance;
    private ArrayList<User> userList = new ArrayList<>();
    
    /*Il costruttore della factory crea un array contenente gli utenti presenti nel blog.*/
    private UserFactory(){
        User user1 = new User();
        user1.setId(0);
        user1.setNome("Nicola");
        user1.setCognome("Porceddu");
        user1.setUsername("nporceddu");
        user1.setEmail("nporceddu@gmail.it");
        user1.setPassword("pswdnicola");
        user1.setUrlProfImg("avatarM.png");
        user1.setTipo(User.TipoUtente.AUTORE);
        user1.setSesso(User.SessoUtente.UOMO);
        user1.setIdTipo();
        user1.setIdSesso();
        user1.setCompleanno(7, 6, 1996);
        
        
        User user2 = new User();
        user2.setId(1);
        user2.setNome("Federico");
        user2.setCognome("Piredda");
        user2.setUsername("fpiredda");
        user2.setEmail("fpiredda@gmail.com");
        user2.setPassword("pswdfederico");
        user2.setUrlProfImg("avatarM.png");
        user2.setTipo(User.TipoUtente.AUTORE);
        user2.setSesso(User.SessoUtente.UOMO);
        user2.setIdTipo();
        user2.setIdSesso();
        user2.setCompleanno(28, 1, 1995);
        
        User user3 = new User();
        user3.setId(2);
        user3.setNome("Francesca");
        user3.setCognome("Bacci");
        user3.setUsername("fbacci");
        user3.setEmail("fbacci@gmail.com");
        user3.setPassword("pswdfrancesca");
        user3.setUrlProfImg("avatarF.png");
        user3.setTipo(User.TipoUtente.AUTORE);
        user3.setSesso(User.SessoUtente.DONNA);
        user3.setIdTipo();
        user3.setIdSesso();
        user3.setCompleanno(28, 7, 1993);
        
        User user4 = new User();
        user4.setId(3);
        user4.setNome("Giorgia");
        user4.setCognome("Masala");
        user4.setUsername("gmasala");
        user4.setEmail("gmasala@gmail.com");
        user4.setPassword("pswdgiorgia");
        user4.setUrlProfImg("avatarF.png");
        user4.setTipo(User.TipoUtente.LETTORE);
        user4.setSesso(User.SessoUtente.DONNA);
        user4.setIdTipo();
        user4.setIdSesso();
        user4.setCompleanno(25, 5, 1996);
        
        User user5 = new User();
        user5.setId(4);
        user5.setNome("Lorenzo");
        user5.setCognome("Carta");
        user5.setUsername("lcarta");
        user5.setEmail("lcarta@gmail.com");
        user5.setPassword("pswdlorenzo");
        user5.setUrlProfImg("avatarM.png");
        user5.setTipo(User.TipoUtente.LETTORE);
        user5.setSesso(User.SessoUtente.UOMO);
        user5.setIdTipo();
        user5.setIdSesso();
        user5.setCompleanno(5, 2, 1991);
        
        User user6 = new User();
        user6.setId(5);
        user6.setNome("Mauro");
        user6.setCognome("Stochino");
        user6.setUsername("mstochino");
        user6.setEmail("mstochino@gmail.com");
        user6.setPassword("pswdmauro");
        user6.setUrlProfImg("avatarM.png");
        user6.setTipo(User.TipoUtente.LETTORE);
        user6.setSesso(User.SessoUtente.UOMO);
        user6.setIdTipo();
        user6.setIdSesso();
        user6.setCompleanno(6, 1, 1993);
        
        User user7 = new User();
        user7.setId(6);
        user7.setNome("Serena");
        user7.setCognome("Sanna");
        user7.setUsername("ssanna");
        user7.setEmail("ssanna@gmail.com");
        user7.setPassword("pswdserena");
        user7.setUrlProfImg("avatarF.png");
        user7.setTipo(User.TipoUtente.AUTORE);
        user7.setSesso(User.SessoUtente.DONNA);
        user7.setIdTipo();
        user7.setIdSesso();
        user7.setCompleanno(12, 8, 1991);
        
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        userList.add(user6);
        userList.add(user7);
    }
    
    public static UserFactory getInstance(){
        if (instance == null)
            instance = new UserFactory();
        return instance;
    }
    
    /*Il metodo restituisce un array di tutti gli articoli salvati.*/
    public ArrayList<User> getUsers(){
        return userList;
    }
    
    /*Il metodo restituisce l'utente con l'id scelto.*/
    public User getUserById(int id){
        for (User user : userList)
            if (user.getId() == id)
                return user;
        
        return null;
    }
    
    /*Il metodo verifica il corretto inserimento di email e password.*/
    public boolean loginEmail(String email, String password){
        for(User user : userList)
            if (user.getEmail().equals(email) && user.getPassword().equals(password))
                return true;
        
        return false;
    }
    
    /*Il metodo verifica il corretto inserimento di username e password.*/
    public boolean loginUsername(String username, String password){
        for(User user : userList)
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
                return true;
        
        return false;
    }
    
    /*Il metodo restituisce l'utente corrispondente all'email scelta.*/
    public User getUserByEmail(String email){
        for (User user: userList)
            if (user.getEmail().equals(email))
                return user;
        
        return null;
    }

    /*Il metodo restituisce l'utente corrispondente all'username scelto.*/
    public User getUserByUsername(String username){
        for (User user: userList)
            if (user.getUsername().equals(username))
                return user;
        
        return null;
    }
    
     /*Il metodo restituisce un array contenente gli utenti registrati come autori.*/
    public ArrayList<User> getAuthors(){
        ArrayList<User> listToReturn = new ArrayList<>();
        
        for (User user : userList)
            if ((user.getTipo()).equals(TipoUtente.AUTORE))
                listToReturn.add(user);
        
        return listToReturn;
    }
}

