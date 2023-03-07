/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*La funzione si occupa di creare l'HTML dinamico della lista di autori.*/
function createAutori(user){
    /*Il codice viene eseguito solo se l'elemento dell'array che viene passato contiene un campo id, ed è dunque
      un autore.*/
    if(user.id){
        var id = user.id;
        var href = "notizie.html?authorid=" + String(id);
        var img = String(user.img);
        var nome = String(user.nome + " " + user.cognome);
        var linkId = $("<a>").attr("href", href);
        var linkImg = $("<img>").attr("title", "Avatar").attr("alt", "Immagine del profilo").attr("src", img).attr("width", "25").attr("height", "25");   

        return $("<li>").append(linkId.append(linkImg).append(nome));
    }
}

/*La funzione si occupa di creare l'HTML dinamico della lista delle categorie.*/
function createCategorie(category){
    /*Il codice viene eseguito solo se l'elemento dell'array che viene passato contiene una stringa categoria, ed
      è dunque una categoria.*/
    if(category.categoria){
        var id;
        var href = "notizie.html?newstype=";
        var link;
        var cate = String(category.categoria);

        switch(cate){
            case "Politica":
                id = String(1);
                break;
            case "Cronaca":
                id = String(2);
                break;
            case "Esteri":
                id = String(3);
                break;
            case "Economia":
                id = String(4);
                break;
            case "Sport":
                id = String(5);
                break;
            case "Cultura":
                id = String(6);
                break;
            case "Tutte":
                return $("<li>").append($("<a>").attr("href", "notizie.html").html("Tutte"));
        }

        link = $("<a>").attr("href", href + id);

        return $("<li>").append(link.html(cate));
    }
}

/*In caso di successo nella ricerca, le due liste (categorie e autori) vengono svuotate e riempite con l'array
  contenente le categorie e gli autori trovati.*/
function stateSuccess(data){
    var listCategorie = $("#listCategorie");
    var listAutori = $("#listAutori");
    
    $(listCategorie).empty();
    $(listAutori).empty();
    
    for (var instance in data)
        $(listCategorie).append(createCategorie(data[instance]));
    
    for (var instance in data)
        $(listAutori).append(createAutori(data[instance]));
}

function stateFailure(data, state){
    console.log(state);
}

/*La funzione viene richiamata quando viene scritto un carattere nella barra di ricerca. Viene caricata la servlet
  che si occupa di effettuare la ricerca delle categorie e dei nomi e cognomi degli utenti (NON case sensitive) 
  che contengono la sequenza di caratteri inserita nella barra di ricerca.*/
$(document).ready(function (){
    $("#ricerca").keyup(function(event){
        $.ajax({
            url: "filter.json",
            data:{
                cmd: "search",
                q: event.target.value
            },
            dataType: 'json',
            success: function(data, state){stateSuccess(data);},
            error: function (data, state) {stateFailure(data, state);}
        });  
    });
});