<%-- 
    Document   : car
    Created on : 19.12.2017, 21:14:55
    Author     : Jakub Juřena
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
<jsp:attribute name="title"><f:message key="car.title"/></jsp:attribute>
<jsp:attribute name="body">


    <div class="table-responsive">          
  <table class="table table-hover">
    <thead>
      <tr>
        <th><f:message key="car.vin"/></th>
        <th><f:message key="car.brand"/></th>
        <th><f:message key="car.type"/></th>
        <th><f:message key="car.engineType"/></th>
        <th><f:message key="car.productionYear"/></th>
        <th><f:message key="car.kmCount"/></th>
      </tr>
    </thead>
    <tbody>
        <c:forEach items="${cars}" var="car">
            <tr>
                <td><c:out value="${car.getVin()}"/></td>
                <td><c:out value="${car.getBrand()}"/></td>
                <td><c:out value="${car.getType()}"/></td>
                <td><c:out value="${car.getEngineType()}"/></td>
                <td><c:out value="${car.getProductionYear()}"/></td>
                <td><c:out value="${car.getKmCount()}"/></td>
            </tr>
        </c:forEach>
    </tbody>
  </table>
  </div>


</jsp:attribute>
</my:pagetemplate>