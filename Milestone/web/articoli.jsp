<%-- 
    Document   : articoli
    Created on : 24-apr-2018, 9.28.52
    Author     : Studente
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
    <!--Meta informazioni contenenti il titolo della pagina, codifica caratteri, importazione foglio
            di stile, tag, descrizione e autore.-->
    <head>        
        <title>Il Blog del Baffo - I miei articoli</title>
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

        <!--Sezione centrale della pagina. Contiene una tabella in cui vengono visualizzati gli
            articoli scritti dall'autore: data, titolo, modifica ed elimina.-->
        <main id="profilo">
            <!--Se l'utente loggato non è un autore, viene visualizzato un messaggio di errore.-->
            <c:if test="${author==false}">
                <h2 class="corsivo">La pagina è visibile solo agli autori.</h2>
            </c:if>
                
            <!--Se l'utente loggato è un autore, ma non vi sono suoi articoli salvati, viene visualizzato un
                messaggio di errore.-->
            <c:if test="${author==true && newsList.size()==0}">
                <h2 class="corsivo">Non è presente alcun articolo scritto da te.</h2>
            </c:if>
            
            <!--Se l'utente loggato è un autore e vi sono suoi articoli salvati, viene visualizzata una tabella
                contenente le news dell'utente loggato.-->
            <c:if test="${author==true && newsList.size()>0}">
                <h1 class="corsivo">I miei articoli</h1>

                <table>
                    <tr id="primaRiga">
                        <th>Data</th>
                        <th>Titolo</th>
                        <th>Modifica</th>
                        <th>Elimina</th>
                    </tr>
                    <!--Il forEach stampa dinamicamente tutte le news scritte dall'autore loggato, che vengono
                        passate dalla servlet Articles.java-->
                    <c:forEach var="news" items="${newsList}">
                    <tr>
                        <td>${news.data.toString()}</td>
                        <td>
                            <a href="notizia.html?nid=${news.id}">
                                ${news.titolo}
                            </a>
                        </td>
                        <!--Il pulsante di modifica articolo richiama la servlet NewArticle.java, passando come parametro
                            l'id della news che si trova nella riga corrispondente.-->
                        <td>
                            <a href="scriviArticolo.html?nid=${news.id}">
                                <img title="Pulsante modifica" alt="Pulsante modifica" src="M1/modifica.png" width="25" height="25">
                            </a>
                        </td>
                        <td>
                            <a href="#">
                                <img title="Pulsante elimina" alt="Pulsante elimina" src="M1/elimina.png" width="25" height="25">
                            </a>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
                <!--Il pulsante nuovo articolo richiama la servlet NewArticle.java, senza passare nessun id,
                    per la creazione di una nuova news.-->
                <form action="scriviArticolo.html" method="get">
                    <button type="submit" id="nuovoArticoloButton">Nuovo articolo</button>
                </form>
            </c:if>
        </main>

        <!--Inclusione footer.jsp-->
        <jsp:include page="footer.jsp" />
    </body>
</html>

