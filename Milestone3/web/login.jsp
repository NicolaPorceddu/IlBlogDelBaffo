<%-- 
    Document   : index
    Created on : 23-apr-2018, 10.25.37
    Author     : Nicola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
    <!--Meta informazioni contenenti il titolo della pagina, codifica caratteri, importazione foglio
        di stile, tag, descrizione e autore.-->
    <head>       
        <title>Il Blog del Baffo</title>
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

        <!--Sezione centrale della pagina. Contiene un form per effettuare il login. Viene richiesto un
            username, una password e vi è un pulsante di accesso.-->
        <main id="sezLogin">
            <!--Cercando di accedere a sezioni a cui non è permesso l'accesso, si viene reindirizzati alla
                pagina di login con un messaggio di errore.-->
            <c:if test="${error==true}">
                <h4>Devi effettuare il login come autore per poter visualizzare la sezione.</h4>
            </c:if>

            <img class="logo" title="Logo" alt="Logo del Blog del Baffo" src="M1/logo2.png">  
            
            <div id="login">
                <!--Vengono inseriti i dati dell'utente che vuole registrarsi-->
                <form action="login.html" method="post">
                    <!--Il campo Nome Utente vengiene inserito attraverso input testuale breve-->
                    <label for="nome" class="primo">Username/Email</label>
                    <input type="text" name="nome" id="nome" />
                    <!--La password viene inserita con un input testuale apposito per le password-->    
                    <label for="pswd" class="successivi">Password</label>
                    <input type="password" name="pswd" id="pswd" />
                    <br/>
                    <c:if test="${invalidData!=true}">
                        <br/>
                    </c:if>
                    <!--Se la servlet passa alla jsp il parametro "invalidData", significa che l'utente
                        ha inserito dei dati di login errati, e verrà visualizzato un messaggio di errore.-->
                    <c:if test="${invalidData==true}">
                        <p class="errore">
                            Username e/o password errati.
                        </p>
                    </c:if>
                    <!--Pulsante per il salvataggio delle informazioni-->
                    <button type="submit" id="loginButton">Accedi</button>
                </form>
            </div>
        </main>

        <!--Inclusione footer.jsp-->
        <jsp:include page="footer.jsp" />
    </body>
</html>
