<%-- 
    Document   : drives
    Created on : 20.12.2017, 22:20:16
    Author     : Jakub Juřena <445319>
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
<jsp:attribute name="title"><f:message key="drives.title"/></jsp:attribute>
<jsp:attribute name="body">


  <div class="table-responsive">          
  <table class="table table-hover">
    <thead>
      <tr>
        <th><f:message key="drive.from"/></th>
        <th><f:message key="drive.to"/></th>
        <th><f:message key="drive.km"/></th>
        <th><f:message key="drive.carVin"/></th>
        <th><f:message key="drive.employeeEmail"/></th>
      </tr>
    </thead>
    <tbody>
        <c:forEach items="${drives}" var="drive">
            <tr>
                <td><c:out value="${drive.getTimeFrom()}"/></td>
                <td><c:out value="${drive.getTimeTo()}"/></td>
                <td><c:out value="${drive.getKm()}"/></td>
                <td><c:out value="${drive.getCar().getVin()}"/></td>
                <td><c:out value="${drive.getEmployee().getEmail()}"/></td>
            </tr>
        </c:forEach>
    </tbody>
  </table>
  </div>


</jsp:attribute>
</my:pagetemplate>
