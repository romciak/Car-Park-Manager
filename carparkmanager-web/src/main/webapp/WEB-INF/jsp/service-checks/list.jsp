<%-- 
    Document   : service-checks
    Created on : 20.12.2017, 22:20:33
    Author     : Jakub Juřena <445319>
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
<jsp:attribute name="title"><f:message key="serviceCheck.title"/></jsp:attribute>
<jsp:attribute name="body">


  <div class="table-responsive">          
  <table class="table table-hover">
    <thead>
      <tr>
        <th><f:message key="serviceCheck.from"/></th>
        <th><f:message key="serviceCheck.to"/></th>
        <th><f:message key="serviceCheck.done"/></th>
        <th><f:message key="serviceCheck.doneWhen"/></th>
        <th><f:message key="serviceCheck.carVin"/></th>
      </tr>
    </thead>
    <tbody>
        <c:forEach items="${serviceChecks}" var="serviceCheck">
            <tr>
                <td><c:out value="${serviceCheck.getIntervalFrom()}"/></td>
                <td><c:out value="${serviceCheck.getIntervalTo()}"/></td>
                <td><c:out value="${serviceCheck.isDone()}"/></td>
                <td><c:out value="${serviceCheck.getDoneWhen()}"/></td>
                <td><c:out value="${serviceCheck.getCar().getVin()}"/></td>
            </tr>
        </c:forEach>
    </tbody>
  </table>
  </div>


</jsp:attribute>
</my:pagetemplate>
