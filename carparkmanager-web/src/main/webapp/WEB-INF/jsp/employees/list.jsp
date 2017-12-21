<%-- 
    Document   : employees
    Created on : 20.12.2017, 22:20:26
    Author     : Jakub Juřena <445319>
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
<jsp:attribute name="title"><f:message key="employee.title"/></jsp:attribute>
<jsp:attribute name="body">


  <div class="table-responsive">          
  <table class="table table-hover">
    <thead>
      <tr>
        <th><f:message key="employee.name"/></th>
        <th><f:message key="employee.birthDate"/></th>
        <th><f:message key="employee.classification"/></th>
        <th><f:message key="employee.email"/></th>
        <th><f:message key="employee.userRole"/></th>
      </tr>
    </thead>
    <tbody>
        <c:forEach items="${employees}" var="employee">
            <tr>
                <td><c:out value="${employee.getFirstname()} ${employee.getSurname()}"/></td>
                <td><c:out value="${employee.getBirthDate()}"/></td>
                <td><c:out value="${employee.getClassification()}"/></td>
                <td><c:out value="${employee.getEmail()}"/></td>
                <td><c:out value="${employee.getUserRole()}"/></td>
            </tr>
        </c:forEach>
    </tbody>
  </table>
  </div>


</jsp:attribute>
</my:pagetemplate>
