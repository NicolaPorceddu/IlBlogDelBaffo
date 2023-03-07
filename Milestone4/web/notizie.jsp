<%-- 
    Document   : notizia
    Created on : 24-apr-2018, 9.15.59
    Author     : Studente
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
    <!--Meta informazioni contenenti il titolo della pagina, codifica caratteri, importazione foglio
        di stile, tag, descrizione e autore.-->
    <head>
        <title>Il Blog del Baffo - Notizie</title>
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

        <!--Sezione centrale della pagina. Contiene un elenco con anteprima delle notizie del blog, (o della
            categoria/autore selezionato).-->
        <main id="articolo">
            <!--Se non sono presenti notizie della categoria/autore scelto, viene visualizzato un messaggio di errore.-->
            <c:if test="${newsList.size() == 0}">
                <h2 class="corsivo">Nessun articolo corrispondente alla ricerca effettuata.</h2>
            </c:if>
                
            <!--Il forEach stampa l'elenco delle notizie passate dalla servlet: titolo, immagina, un'anteprima di
                articolo di 100 caratteri, categoria, data e autore.-->
            <c:forEach var="news" items="${newsList}">
                <a href="notizia.html?nid=${news.id}">
                    <h2 class="corsivo">${news.titolo}</h2>
                </a>

                <div class="anteprima">
                    <a href="notizia.html?nid=${news.id}">
                        <img class="imgAnteprima" title="${news.didascalia}" alt="Immagine articoloimg" src="${news.img}" width="600" height="337">
                    </a>
                    <div>
                        <p>${news.anteprimaContenuto}</p>
                        
                        Articolo di
                        <c:forEach var="categoria" items="${news.categorie}">
                            <a href="notizie.html?newstype=${news.getIdSingleCategory(categoria)}">${categoria}</a>,
                        </c:forEach>
                        ${news.data.toString()}
                        di <a href="notizie.html?authorid=${news.autore.id}">${news.autore.nome} ${news.autore.cognome}</a>
                    </div>
                </div>
                <br/><br/><br/>
            </c:forEach>
        </main>

        <!--Inclusione footer.jsp-->
        <jsp:include page="footer.jsp" />
    </body>
</html>
