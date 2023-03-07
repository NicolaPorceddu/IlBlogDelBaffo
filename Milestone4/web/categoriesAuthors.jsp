<%-- 
    Document   : categoriesAuthors
    Created on : 5-mag-2018, 23.24.58
    Author     : Nicola
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

        <!--La pagina permette di visualizzare un elenco delle categorie o degli autori (a seconda del parametro
            che viene passato alla servlet CategoriesAuthors.java. La pagina viene richiamata solo nella
            visualizzazione a colonna singola, premendo il link Categorie o Autori, presente nella navbar.-->
        <main id="articolo">
            <c:if test="${category==true}">
                <h3>
                    <a href="notizie.html?newstype=1">Politica</a>
                    <br/>
                    <a href="notizie.html?newstype=2">Cronaca</a>
                    <br/>
                    <a href="notizie.html?newstype=3">Esteri</a>
                    <br/>
                    <a href="notizie.html?newstype=4">Economia</a>
                    <br/>
                    <a href="notizie.html?newstype=5">Sport</a>
                    <br/>
                    <a href="notizie.html?newstype=6">Cultura</a>
                    <br/>
                    <a href="notizie.html?newstype=0">Tutto</a>
                </h3>
            </c:if>
            
            <c:if test="${author==true}">
                <h3>
                    <c:forEach var="user" items="${userList}">
                        <a href="notizie.html?authorid=${user.id}">
                            <img title="Avatar" alt="Immagine del profilo" src="M1/${user.urlProfImg}" width="25" height="25">${user.nome} ${user.cognome}
                        </a>
                        <br/>
                    </c:forEach>
                </h3>
            </c:if>
        </main>

        <!--Inclusione footer.jsp-->
        <jsp:include page="footer.jsp" />
    </body>
</html>

