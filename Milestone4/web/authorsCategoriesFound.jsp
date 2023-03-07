<%-- 
    Document   : authorsFound
    Created on : 4-giu-2018, 18.42.21
    Author     : Nicola
--%>

<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--La seguente sezione json acquisisce gli array, passati dalla servlet, contenenti le categorie e gli autori che
    contengono la sequenza di caratteri inserita dall'autore nella barra di ricerca. I quali a sua volta verranno
    mandati a search.js per la stampa delle liste.--%>

<json:array>
    <c:forEach var="category" items="${categoryList}">
        <json:object>
            <json:property name="categoria" value="${category}"/>
        </json:object>
    </c:forEach>
    <c:forEach var="user" items="${userList}">
        <json:object>
            <json:property name="id" value="${user.id}"/>
            <json:property name="img" value="${user.urlProfImg}"/>
            <json:property name="nome" value="${user.nome}"/>
            <json:property name="cognome" value="${user.cognome}"/>
        </json:object>
    </c:forEach>
</json:array>
