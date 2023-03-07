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
/*La classe USer permette di creare degli oggetti rappresentanti gli utenti del blog.
  Presenta come attributi un id personale, un nome, un cognome, un username, un'email, una password, l'url
  dell'immagine del profilo, il tipo di utente e il sesso (con relativo valore espresso in interi) e la
  data di nascita.*/
public class User{
    /*Le enumerazioni permettono di differenziare autori e scrittori, uomini e donne.*/
    public enum TipoUtente{AUTORE, LETTORE};
    public enum SessoUtente{UOMO, DONNA};
    
    private int id;
    private String nome;
    private String cognome;
    private String username;
    private String email;
    private String password;
    private String urlProfImg;
    private TipoUtente tipo;
    private int idTipo;
    private SessoUtente sesso;
    private int idSesso;
    private Data compleanno;
        
    /*Costruttore della classe.*/
    public User(){
        this.id = 0;
        this.nome = "";
        this.cognome = "";
        this.username = "";
        this.email = "";
        this.password = "";
        this.urlProfImg = "";
        this.compleanno = new Data(0,0,0);
    }
        
    /*Getter e Setter degli attributi della classe.*/
    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCognome(){
        return this.cognome;
    }

    public void setCognome(String cognome){
        this.cognome = cognome;
    }
    
    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getUrlProfImg(){
        return this.urlProfImg;
    }

    public void setUrlProfImg(String urlProfImg){
        this.urlProfImg = urlProfImg;
    }

    public TipoUtente getTipo(){
        return this.tipo;
    }

    public void setTipo(TipoUtente tipo){
        this.tipo = tipo;
    }

    public int getIdTipo(){
        return this.idTipo;
    }

    public void setIdTipo(){
        if(this.tipo.equals(TipoUtente.AUTORE))
            this.idTipo = 1;
        else
            this.idTipo = 2;
    }

    public SessoUtente getSesso(){
        return this.sesso;
    }
    
    public int getIdSesso(){
        return this.idSesso;
    }

    public void setIdSesso(){
        if(this.sesso.equals(SessoUtente.UOMO))
            this.idSesso = 1;
        else
            this.idSesso = 2;
    }

    public void setSesso(SessoUtente sesso){
        this.sesso = sesso;
    }
    
    public boolean equals(User otherUser){
        return this.id == otherUser.getId();
    }

    public int getGiorno(){
        return this.compleanno.getGiorno();
    }
    
    public int getMese(){
        return this.compleanno.getMese();
    }
    
    public int getAnno(){
        return this.compleanno.getAnno();
    }
    
    public Data getCompleanno(){
        return this.compleanno;
    }

    public void setCompleanno(int giorno, int mese, int anno) {
        this.compleanno.setGiorno(giorno);
        this.compleanno.setMese(mese);
        this.compleanno.setAnno(anno);
    }
}
