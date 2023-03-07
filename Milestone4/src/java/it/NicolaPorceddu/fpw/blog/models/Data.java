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

/*La classe Data permette di memorizzare una data. Presenta come attributi tre interi per giorno, mese e anno.*/
public class Data{
    private int giorno;
    private int mese;
    private int anno;
    
    /*Costruttore della classe.*/
    public Data(int giorno, int mese, int anno){
        this.giorno = giorno;
        this.mese = mese;
        this.anno = anno;
    }

    /*Getter e Setter degli attriburi della classe.*/
    public int getGiorno(){
        return giorno;
    }
    
    public void setGiorno(int giorno){
        this.giorno = giorno;
    }

    public int getMese(){
        return mese;
    }
    
    public void setMese(int mese){
        this.mese = mese;
    }

    public int getAnno(){
        return anno;
    }
    
    public void setAnno(int anno){
        this.anno = anno;
    }
    
    /*Il metodo toString restituisce una stringa nel formato GG-MM-AAAA*/
    @Override
    public String toString(){
        String Giorno, Mese, data;
        
        if(this.giorno < 10)
            Giorno = "0" + this.giorno;
        else
            Giorno = String.valueOf(this.giorno);
        if(this.mese < 10)
            Mese = "0" + this.mese;
        else
            Mese = String.valueOf(this.mese);
        
        data = String.valueOf(Giorno) + "-" + String.valueOf(Mese) + "-" + this.anno;
        
        return data;
    }
    
    /*Il metodo toString restituisce una stringa nel formato AAAA-MM-GG*/
    public String toStringReverse(){
        String Giorno, Mese, data;
        
        if(this.giorno < 10)
            Giorno = "0" + this.giorno;
        else
            Giorno = String.valueOf(this.giorno);
        if(this.mese < 10)
            Mese = "0" + this.mese;
        else
            Mese = String.valueOf(this.mese);
        
        data = this.anno + "-" + String.valueOf(Mese) + "-" + String.valueOf(Giorno);
        
        return data;
    }
}
