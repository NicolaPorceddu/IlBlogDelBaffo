<%-- 
    Document   : header
    Created on : 24-apr-2018, 14.25.57
    Author     : Nicola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/search.js"></script>
    <div id="sezLogo">
        <img class="flexImg" title="Logo" alt="Logo del Blog del Baffo" src="M1/logo.png">
        <!--Nel caso in cui si visualizzi il sito senza effettuare il login, il menù di navigazione permette
            di visualizzare unicamente la sezione Notizie e quella Login.-->
        <c:if test="${loggedIn!=true}">
            <a href="notizie.html">
                <div class="sezLink link" id="sezLink2">
                    Notizie
                </div>
            </a>
            <a href="login.html">
                <div class="sezLink link" id="sezLink1">
                    Login
                </div>
            </a>
        </c:if>

        <!--Nel caso in cui si visualizzi il sito dopo aver effettuato il login, il menù di navigazione permette
            di visualizzare le sezioni Notizie, Profilo, I miei Articoli e una sezione che presenta il nome
            dell'utente loggato e un pulsante di logout..-->
        <c:if test="${loggedIn==true}">
              <a href="notizie.html">
                <div class="sezLink link" id="sezLink4">
                    Notizie
                </div>
            </a>
              
            <a href="profilo.html">
                <div class="sezLink link" id="sezLink3">
                    Profilo
                </div>
            </a>
            
            <a href="articoli.html">
                <div class="sezLink link" id="sezLink2">
                    I miei<br class="show"/> articoli
                </div>
            </a>

            <!--Per effettuare il logout, viene richiamata la servlet Login.java, passando come parametro "logout".-->
            <div class="sezLink" id="sezLink1">
                Ciao, <br class="show"/> ${user.nome}!
                <form action="login.html?logout=1" method="post">
                    <button type="submit" id="logoutButton">Logout</button>
                </form>
            </div> 
        </c:if>
    </div>
</header>
