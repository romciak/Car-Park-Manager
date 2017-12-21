<%-- 
    Document   : login
    Created on : 18.12.2017, 21:15:07
    Author     : Jakub Jurena
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
<jsp:attribute name="title"><f:message key="login.title"/></jsp:attribute>
<jsp:attribute name="body">


    <form method="POST" action="${pageContext.request.contextPath}/auth/login">
        <div class="form-group">
            <h4><fmt:message key="login.title" /></h4>
        </div>

        <div class="form-group">
            <label for="email"><f:message key="email"/>:</label>
            <input id="email" type="text" name="email" placeholder="example: user@gmail.com" required class="form-control"/>
        </div>
            
        <div class="form-group">
            <label for="password"><f:message key="login.password"/>:</label>
            <input id="password" type="password" name="password" placeholder="Password" required class="form-control"/>
        </div>
        
        <button class="btn btn-lg btn-primary" type="submit" ><f:message key="login.button"/></button>
    </form>


</jsp:attribute>
</my:pagetemplate>