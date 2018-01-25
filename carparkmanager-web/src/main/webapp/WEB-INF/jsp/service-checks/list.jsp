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
                <c:if test="${not empty authEmployee}">
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/service-checks/delete/${serviceCheck.getId()}">
                            <button type="submit" class="btn btn-danger"><f:message key="delete"/></button>
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </tbody>
  </table>
  </div>

      <c:if test="${not empty authEmployee && authEmployee.isAdmin()}">

        <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/service-checks/create"> 
            <div class="form-group">
                <label class="control-label col-sm-2" for="intervalFrom">Interval from:</label>
                <div class="col-sm-10">
                    <input type="intervalFrom" class="form-control" id="intervalFrom" placeholder="Enter intervalFrom">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="intervalTo">Interval to:</label>
                <div class="col-sm-10">
                    <input type="intervalTo" class="form-control" id="intervalTo" placeholder="Enter intervalTo">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="done">Done:</label>
                <div class="col-sm-10">
                    <input type="done" class="form-control" id="done" placeholder="Enter done (true/false)">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="doneWhen">Done when:</label>
                <div class="col-sm-10">
                    <input type="doneWhen" class="form-control" id="doneWhen" placeholder="Enter done when">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="car">Car:</label>
                <div class="col-sm-10">
                    <input type="car" class="form-control" id="car" placeholder="Enter car">
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
