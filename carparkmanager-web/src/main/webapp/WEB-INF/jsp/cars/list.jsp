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
                <c:if test="${not empty authEmployee && authEmployee.isAdmin()}">
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/cars/delete/${car.getId()}">
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

        <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/cars/create"> 
            <div class="form-group">
                <label class="control-label col-sm-2" for="vin">Vin:</label>
                <div class="col-sm-10">
                    <input type="vin" class="form-control" id="vin" placeholder="Enter vin">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="brand">Brand:</label>
                <div class="col-sm-10">
                    <input type="brand" class="form-control" id="brand" placeholder="Enter brand">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="type">Type:</label>
                <div class="col-sm-10">
                    <input type="type" class="form-control" id="type" placeholder="Enter type">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="engineType">Engine type:</label>
                <div class="col-sm-10">
                    <input type="engineType" class="form-control" id="engineType" placeholder="Enter engine type">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="productionYear">Production year:</label>
                <div class="col-sm-10">
                    <input type="productionYear" class="form-control" id="productionYear" placeholder="Enter brand">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="kmCount">Km count:</label>
                <div class="col-sm-10">
                    <input type="kmCount" class="form-control" id="kmCount" placeholder="Enter brand">
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