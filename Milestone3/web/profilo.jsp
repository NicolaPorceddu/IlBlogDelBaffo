<%-- 
    Document   : profilo
    Created on : 4-mag-2018, 22.03.26
    Author     : Nicola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
    <!--Meta informazioni contenenti il titolo della pagina, codifica caratteri, importazione foglio
        di stile, tag, descrizione e autore.-->
    <head>
        <title>Il Blog del Baffo - Profilo</title>
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

        <!--Sezione centrale della pagina. Contiene i dati dell'utente loggato e un form che permette di modificare
            i propri dati.-->
        <main id="inputPage">
            <c:if test="${deleting!=true}">
                <!--A seguito della modifica dei dati, viene visualizzato un messaggio di successo.-->
                <c:if test="${salvataggio==true}">
                    <h3>I tuoi dati sono stati aggiornati con successo!</h3>
                </c:if>

                 <!--Viene visualizzata l'immagine dell'utente, nome, cognome, username, email e data di compleanno.-->
                <h2 class="corsivo">I tuoi dati</h2>
                <img class="imgProfilo" title="Immagine profilo" alt="Immagine profilo" src="${user.urlProfImg}" width="200" height="200">
                <p> ${user.nome} ${user.cognome} <br/> ${user.username} <br/> ${user.email} <br/> ${user.compleanno.toString()} </p> 

                <br/>
                <!--Viene richiamata la servlet per l'eliminazione del profilo.-->
                <form action="profilo.html?deleteRequest=1" method="post">
                    <button type="submit" id="nuovoArticoloButton">Elimina Profilo</button>
                </form>
                <br/><br/><br/>

                <!--Il form richiama la servlet Profilo.java, passando come parametro "save", per verificare il corretto
                    aggiornamento dei dati.-->
                <h2 class="corsivo">Modifica dati</h2>
                <form action="profilo.html?save=1" method="post">
                    <label for="titolo" class="primo">Nome</label>
                    <input type="text" name="nome" id="nome" class="grey" value="${user.nome}" />

                    <label for="cognome" class="successivi">Cognome</label>
                    <input type="text" name="cognome" id="cognome" class="grey" value="${user.cognome}">

                    <label for="username" class="successivi">Username</label>
                    <input type="text" name="username" id="username" class="grey" value="${user.username}">

                    <label for="email" class="successivi">Email</label>
                    <input type="text" name="email" id="email" class="grey" value="${user.email}">

                    <label for="password" class="successivi">Password</label>
                    <input type="text" name="password" id="password" class="grey" value="${user.password}">

                    <label for="immagine" class="successivi">Immagine Profilo</label>
                    <input type="url" name="immagine" id="immagine" class="grey" value="http://${user.urlProfImg}"/>

                    <label for="compleanno" class="successivi">Compleanno</label>
                    <input type="date" name="compleanno" id="compleanno" class="grey" value="${user.compleanno.toStringReverse()}">

                    <br/>
                    <br/>

                    <p class="lbl">Tipologia utente</p>
                    <p class="slim">
                        <label for="autore">Autore</label>
                        <input type="radio" name="tipo" id="autore" value="autore" <c:if test="${user.idTipo==1}">checked</c:if>>
                        <br>
                        <label for="lettore">Lettore</label>
                        <input type="radio" name="tipo" id="lettore" value="lettore" <c:if test="${user.idTipo==2}">checked</c:if>>
                        <br>
                    </p>

                    <br/>

                    <p class="lbl">Sesso</p>
                    <p class="slim">
                        <label for="uomo">Uomo</label>
                        <input type="radio" name="sesso" id="uomo" value="uomo" <c:if test="${user.idSesso==1}">checked="checked"</c:if>>
                        <br>
                        <label for="donna">Donna</label>
                        <input type="radio" name="sesso" id="donna" value="donna" <c:if test="${user.idSesso==2}">checked="checked"</c:if>>
                        <br>
                    </p>
                    <br/>
                    <!--Pulsante per il salvataggio delle informazioni.-->
                    <button type="submit">Salva</button>
                </form>
            </c:if>
                
            <!--Se si cerca di eliminare il profilo, viene visualizzato un form di conferma.-->
            <c:if test="${deleting==true}">
                    <h2 class="corsivo">Sei sicuro di voler eliminare il tuo profilo? I tuoi articoli verranno cancellati.</h2>
                    <br/>
                    <form action="profilo.html?delete=1" method="post">
                        <button type="submit" id="nuovoArticoloButton">SI</button>
                    </form>
                    <br/>
                    <form action="profilo.html" method="post">
                        <button type="submit" id="nuovoArticoloButton">NO</button>
                    </form>
            </c:if>
        </main>

        <!--Inclusione footer.jsp-->
        <jsp:include page="footer.jsp" />
    </body>
</html>
