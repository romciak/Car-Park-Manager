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
                <c:if test="${not empty authEmployee && authEmployee.isAdmin()}">
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/employees/delete/${employee.getId()}">
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

        <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/employees/create"> 
            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Name:</label>
                <div class="col-sm-10">
                    <input type="name" class="form-control" id="name" placeholder="Enter name">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="surname">Surname:</label>
                <div class="col-sm-10">
                    <input type="surname" class="form-control" id="brand" placeholder="Enter surname">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="birthday">Birthday:</label>
                <div class="col-sm-10">
                    <input type="birthday" class="form-control" id="birthday" placeholder="Enter birthday">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="role">Role:</label>
                <div class="col-sm-10">
                    <input type="role" class="form-control" id="role" placeholder="Enter role (e.g. ADMINISTRATOR)">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="email">Email:</label>
                <div class="col-sm-10">
                    <input type="email" class="form-control" id="email" placeholder="Email">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="classification">Classification</label>
                <div class="col-sm-10">
                    <input type="classification" class="form-control" id="classification" placeholder="Enter classification for employee">
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
