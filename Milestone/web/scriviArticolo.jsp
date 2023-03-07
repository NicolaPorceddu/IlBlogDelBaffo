<%-- 
    Document   : scriviArticolo
    Created on : 24-apr-2018, 9.30.39
    Author     : Studente
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
    <!--Meta informazioni contenenti il titolo della pagina, codifica caratteri, importazione foglio
        di stile, tag, descrizione e autore.-->
    <head>
        <title>Il Blog del Baffo - Nuovo articolo</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="style.css">
        <meta name="keywords" content="HTML, CSS, BLOG, FPW"> 
        <meta name="description" content="Blog di news e attualità">
        <meta name="author" content="Nicola Porceddu">
    </head>
    
    <body>
        <!--Inclusione aside.jsp-->
        <jsp:include page="header.jsp" />

        <!--Inclusione aside.jsp-->
        <jsp:include page="aside.jsp" />

        <!--Sezione centrale della pagina. Contiene un form per la scrittura/modifica di un articolo.
            Vi è un input per il titolo, per la data, per l'immagine, per la didascalia, per il corpo
            del testo e per la selezione delle categorie di appartenenza.-->
        <main id="inputPage">
            <!--A seguito della modifica/salvataggio di un articolo, viene visualizzato un messaggio di successo.-->
            <c:if test="${salvataggio==true}">
                <h3>L'articolo Numero ${idNews} è stato salvato con successo!</h3>
            </c:if>
            
            <h2 class="corsivo">Scrivi articolo</h2>

            <form action="scriviArticolo.html?nid=${idNews}&save=1" method="post">
                <label for="titolo" class="primo">Titolo</label>
                <input type="text" name="titolo" id="titolo" class="grey" value="${news.titolo}" />
                
                /*Modificando un articolo, la data di pubblicazione non può essere modificata*/
                <c:if test="${noDataModify!=true}">
                    <label for="data" class="successivi">Data</label>
                    <input type="date" name="data" id="data" class="grey" value="${data}">
                </c:if>
                    
                <label for="immagine" class="successivi">Immagine</label>
                <input type="url" name="immagine" id="immagine" class="grey" value="http://M1/${news.img}"/>
                
                <label for="didascalia" class="successivi">Didascalia</label>
                <input type="text" name="didascalia" id="didascalia" class="grey" value="${news.didascalia}"/>
                
                <label for="contenuto" class="successivi">Testo dell'articolo</label>
                <textarea rows="20" cols="80" name="contenuto" id="articolo" class="grey">${news.contenuto}</textarea>
                <br/><br/>
                <p class="lbl">Categoria</p>
                <p>
                    <input type="checkbox" name="categorie" value="Politica"
                           <c:if test="${categories[0]==1}">checked</c:if>>Politica
                    <br>
                    <input type="checkbox" name="categorie" value="Cronaca"
                           <c:if test="${categories[1]==1}">checked</c:if>>Cronaca
                    <br>
                    <input type="checkbox" name="categorie" value="Esteri"
                           <c:if test="${categories[2]==1}">checked</c:if>>Esteri
                    <br>
                    <input type="checkbox" name="categorie" value="Economia"
                           <c:if test="${categories[3]==1}">checked</c:if>>Economia
                    <br>
                    <input type="checkbox" name="categorie" value="Sport"
                           <c:if test="${categories[4]==1}">checked</c:if>>Sport
                    <br>
                    <input type="checkbox" name="categorie" value="Cultura"
                           <c:if test="${categories[5]==1}">checked</c:if>>Cultura
                    <br>
                </p>
                <br/>
                <!--Pulsante per il salvataggio dell'articolo.-->
                <button type="submit">Salva</button>
            </form>
        </main>

        <!--Inclusione footer.jsp-->
        <jsp:include page="footer.jsp" />
    </body>
</html>
