<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
<head>
  <title><c:out value="${title}"/></title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <jsp:invoke fragment="head"/>
  <style>
      
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      border-radius: 0;
    }
    
    /* Add a gray background color, some padding to the footer and position */
    footer {
      background-color: #f2f2f2;
      padding: 25px;
      position: absolute;
      bottom: 0px;
      left: 0px;
      width:100%;
    }
    
    /* because of footer */
    body {
      min-height: 100vh;
    }
    
    /* table text align */
    table {
      text-align: left;
    }
  </style>
</head>
<body>

    
<!--

    NAVBAR

-->
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <my:a href="/" class="navbar-brand"><f:message key="carparkManager"/></my:a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
          
        <!--
            show to logged users
        -->
        <c:if test="${not empty authEmployee}">
            <li><my:a href="/car" class="navbar-brand"><f:message key="cars"/></my:a></li>
            <li><my:a href="/drive" class="navbar-brand"><f:message key="drives"/></my:a></li>
            <li><my:a href="/service-check" class="navbar-brand"><f:message key="serviceChecks"/></my:a></li>

            <!--
                show to admins
            -->
            <c:if test="${authEmployee.isAdmin()}">
                <li><my:a href="/employee" class="navbar-brand"><f:message key="employees"/></my:a></li>
            </c:if>
        </c:if>
        
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <!--
            if not logged in
        -->
        <c:if test="${empty authEmployee}">

          <li>
              <a href="${pageContext.request.contextPath}/auth/login"><span class="glyphicon glyphicon-log-in"></span></a>
          </li>

        </c:if>   
          <!--
              ELSE
          -->
        <c:if test="${not empty authEmployee}"> 
          <li>
            <a href="${pageContext.request.contextPath}/auth/logout">Logout ${authEmloyee.getEmail()}</a>
          </li>
        </c:if>
      </ul>
    </div>
  </div>
</nav>
  



<!--

    LIST of entities

-->

<div class="container bg-3 text-center">
  <jsp:invoke fragment="body"/>   
</div>


<!--

    FOOTER

-->
<footer class="container-fluid text-center">
  <p>Design from Bootstrap template</p>
</footer>

</body>
</html>
