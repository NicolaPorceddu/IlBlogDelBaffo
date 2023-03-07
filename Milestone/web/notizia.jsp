<%-- 
    Document   : notiziaSingola
    Created on : 26-apr-2018, 21.36.25
    Author     : Nicola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
    <!--Meta informazioni contenenti il titolo della pagina, codifica caratteri, importazione foglio
        di stile, tag, descrizione e autore.-->
    <head>
        <title>${news.titolo}</title>
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

        <!--Sezione centrale della pagina. Contiene una notizia del blog in dettaglio: titolo, immagine, didascalia,
            categoria, data, autore e articolo. Alla fine della pagina vengono caricati i commenti relativi alla notizia.-->
        <main id="articolo">
            <h1 class="corsivo">${news.titolo}</h1>

            <h4>
                Articolo di
                <c:forEach var="categoria" items="${news.categorie}">
                    <a href="notizie.html?newstype=${news.getIdSingleCategory(categoria)}">${categoria}</a>,
                </c:forEach>
                     ${news.data} di <a href="notizie.html?authorid=${news.autore.id}">${news.autore.nome} ${news.autore.cognome}</a>
            </h4>

            <img class="imgArticolo" title="${news.didascalia}" alt="Immagine articolo" src="M1/${news.img}" width="600" height="337">
            <h5>${news.didascalia}</h5>

            <p>${news.contenuto}</p>
            
            <br/><br/><br/>
            
            <h2>Commenti</h2>
            <!--Se nessun commento è presente per la notizia, viene visualizzato un messaggio di errore.-->
            <c:if test="${commentList.size()==0}">
                <h2 class="corsivo">Nessun commento presente in questo articolo.</h2>
            </c:if>
                
            <c:forEach var="comment" items="${commentList}">
                <div class="anteprima">
                    <img title="Immagine del profilo" alt="Immagine del profilo" src="M1/${comment.utente.urlProfImg}" width="100" height="100">

                    <div class="left">
                        ${comment.utente.nome} ${comment.utente.cognome}:
                        <br/>
                        ${comment.commento}
                    </div>
                </div>
                <br/><br/><br/>
            </c:forEach>
                
            <br/><br/><br/>
        </main>

        <!--Inclusione footer.jsp-->
        <jsp:include page="footer.jsp" />
    </body>
</html>
