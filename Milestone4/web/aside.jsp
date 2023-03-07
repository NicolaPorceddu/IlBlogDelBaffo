<%-- 
    Document   : aside
    Created on : 23-apr-2018, 10.27.35
    Author     : Nicola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--Sezione contenente una barra di ricerca, una lista puntata contenente le categorie
    degli articoli, una lista puntata contenente gli autori del blog. Le liste diventano
    non visibili sotto gli 800px, diventando dei link.-->
<aside>
    <label for="ricerca">Cerca</label>
    <input type="text" name="ricerca" id="ricerca"/>

    <!--Ogni categoria richiama la servlet Notizie.java, passando come parametro un id della categoria
        scelta, visualizzando le notizie della categoria selezionata.
        La sezione "Tutte" permette di visualizzare tutte le news salvate.-->
    <a href="searchContent.html?categories=1" class="show" id="categorie">Categorie</a>
    <h3 class="hidden">Categorie</h3>
    <ul class="hidden" id="listCategorie">
        <li><a href="notizie.html?newstype=1">Politica</a></li>
        <li><a href="notizie.html?newstype=2">Cronaca</a></li>
        <li><a href="notizie.html?newstype=3">Esteri</a></li>
        <li><a href="notizie.html?newstype=4">Economia</a></li>
        <li><a href="notizie.html?newstype=5">Sport</a></li>
        <li><a href="notizie.html?newstype=6">Cultura</a></li>
        <li><a href="notizie.html">Tutte</a></li>
    </ul>

    <!--Viene caricata una lista non ordinata degli autori presenti all'interno del blog.
        Ogni autore richiama la servlet Notizie.java, passando come parametro un id dell'autore
        scelto, visualizzando le notizie scritte dall'autore selezionato.-->
    <a href="searchContent.html?authors=1" class="show" id="autori">Autori</a>
    <h3 class="hidden">Autori</h3>
    <ul class="hidden" id="listAutori">
        <c:forEach var="user" items="${userList}">
            <li>
                <a href="notizie.html?authorid=${user.id}">
                    <img title="Avatar" alt="Immagine del profilo" src="${user.urlProfImg}" width="25" height="25">${user.nome} ${user.cognome}
                </a>
            </li>
        </c:forEach>
    </ul>
</aside>