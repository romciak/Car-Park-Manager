<%-- 
    Document   : home
    Created on : 18.12.2017, 21:28:04
    Author     : Jakub Juřena
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
    <jsp:attribute name="title"><f:message key="home.title"/></jsp:attribute>
    <jsp:attribute name="list">

         <h2><f:message key="home.welcome"/><h2>

    </jsp:attribute>
</my:pagetemplate>
