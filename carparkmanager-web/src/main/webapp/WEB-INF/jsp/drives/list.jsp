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
                <c:if test="${not empty authEmployee}">
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/drives/delete/${drive.getId()}">
                            <button type="submit" class="btn btn-danger"><f:message key="delete"/></button>
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </tbody>
  </table>
  </div>
  
  <c:if test="${not empty authEmployee}">

        <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/drives/create"> 
            <div class="form-group">
                <label class="control-label col-sm-2" for="timeFrom">Time from:</label>
                <div class="col-sm-10">
                    <input type="timeFrom" class="form-control" id="timeFrom" placeholder="Enter time from">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="timeTo">Time to:</label>
                <div class="col-sm-10">
                    <input type="timeTo" class="form-control" id="timeTo" placeholder="Enter time to">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="km">Km:</label>
                <div class="col-sm-10">
                    <input type="km" class="form-control" id="km" placeholder="Enter km">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="car">Car:</label>
                <div class="col-sm-10">
                    <input type="car" class="form-control" id="car" placeholder="Enter car">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="employee">Employee:</label>
                <div class="col-sm-10">
                    <input type="employee" class="form-control" id="employee" placeholder="Enter employee">
                </div>
            </div>
            <div class="form-group"> 
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-succes col-sm-12"><f:message key="create"/></button>
                </div>
            </div>
        </form>

    </c:if>
</jsp:attribute>
</my:pagetemplate>
