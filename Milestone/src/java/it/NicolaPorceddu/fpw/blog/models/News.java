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

/*La classe News permette di creare degli oggetti rappresentanti gli articoli del blog.
  Presenta come attributi un id personale, un titolo, il contenuto dell'articolo, l'anteprima di 100 caratteri
  dell'articolo, l'url dell'immagine dell'articolo, la didascalia dell'immagine, un array contenente le categorie
  dell'articolo, l'utente che lo ha scritto e la data di pubblicazione.*/
public class News{
    private int id;
    private String titolo;
    private String contenuto;
    private String anteprimaContenuto;
    private String img;
    private String didascalia;
    private ArrayList<String> categorie;
    private User autore;
    private Data data;

    /*Costruttore della classe.*/
    public News(){
        this.id = 0;
        this.titolo = "";
        this.contenuto= "";
        this.anteprimaContenuto= "";
        this.img= "";
        this.didascalia= "";
        this.categorie = new ArrayList<>();
        this.autore = new User();
        this.data = new Data(0,0,0);
    }

    /*Getter e Setter degli attributi della classe.*/
    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getTitolo(){
        return this.titolo;
    }

    public void setTitolo(String titolo){
        this.titolo = titolo;
    }

    public String getContenuto(){
        return this.contenuto;
    }

    public void setContenuto(String content){
        this.contenuto = content;
    }

    public String getAnteprimaContenuto(){
        return this.anteprimaContenuto;
    }

    public void setAnteprimaContenuto(){
        /*Se l'articolo ha meno più di 100 caratteri, l'anteprima viene ridotta a 100, altrimenti sarà uguale
          all'articolo.*/
        if(this.contenuto.length() > 100)
            this.anteprimaContenuto = this.contenuto.substring(0, 99) + "...";
        else
            this.anteprimaContenuto = this.contenuto;
        
        this.anteprimaContenuto = this.anteprimaContenuto.replace("<br/><br/>", "");
    }

    public String getImg(){
        return this.img;
    }

    public void setImg(String img){
        this.img = img;
    }
    
    public String getDidascalia(){
        return this.didascalia;
    }

    public void setDidascalia(String didascalia){
        this.didascalia = didascalia;
    }
    
    public String[] getCategorie(){
        String[] stringArrayToReturn = new String[this.categorie.size()];
        
        stringArrayToReturn = this.categorie.toArray(stringArrayToReturn);
        
        return stringArrayToReturn;
    }

    public void setCategorie(String[] categorie){
        for(String string : categorie)
            this.categorie.add(string);
    }
    
    /*Il metodo restituisce l'id corrispondente alla categoria.*/
    public int getIdSingleCategory(String categoria){
        switch(categoria){
            case "Politica":
                return 1;
            case "Cronaca":
                return 2;
            case "Esteri":
                return 3;
            case "Economia":
                return 4;
            case "Sport":
                return 5;
            case "Cultura":
                return 6;
        }
        return 0;
    }
    
    /*Il metodo restituisce un array di interi di dimensione 6 (pari al numero delle categorie). Ogni cella
      presenta il numero 1, se l'articolo appartiene a quella/e categoria/e, altrimenti 0.*/
    public int[] getCategories(){
        int[] categorieArray = new int[6];
                
        for(String string : this.categorie){
            switch (string) {
                case "Politica":
                    categorieArray[0]=1;
                    break;
                case "Cronaca":
                    categorieArray[1]=1;
                    break;
                case "Esteri":
                    categorieArray[2]=1;
                    break;
                case "Economia":
                    categorieArray[3]=1;
                    break;
                case "Sport":
                    categorieArray[4]=1;
                    break;
                case "Cultura":
                    categorieArray[5]=1;
                    break;
            }
        }
        
        return categorieArray;
    }

    public User getAutore(){
        return this.autore;
    }

    public void setAutore(User autore){
        this.autore = autore;
    }
    
    public int getGiorno(){
        return this.data.getGiorno();
    }
    
    public int getMese(){
        return this.data.getMese();
    }
    
    public int getAnno(){
        return this.data.getAnno();
    }
    
    public Data getData(){
        return this.data;
    }

    public void setData(int giorno, int mese, int anno) {
        this.data.setGiorno(giorno);
        this.data.setMese(mese);
        this.data.setAnno(anno);
    }
}
