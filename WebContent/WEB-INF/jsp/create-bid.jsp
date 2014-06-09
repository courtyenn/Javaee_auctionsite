<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, java.text.*, edu.neumont.csc280.models.*" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="Header.jsp"></jsp:include>
<body>
<jsp:include page="Navigation.jsp"/>
<div class="container">
<div class="row platform">

<div class="col-sm-6">
<c:choose>
<c:when test="${bid.title =='Default'}">
	<h1>Add an auction item!</h1>
</c:when>
<c:otherwise>
<h1><c:out value="${bid.title}" /> # <c:out value = "${bid.id}" /> </h1>
</c:otherwise>
</c:choose>
<form class="" method="POST" action="${pageContext.request.contextPath}${page2}">
<div class="input-group"><label for="title" >Name: </label><input id="title" class="form-control"  type="text" name="title" value="<c:out value="${bid.title}" ></c:out>"/></div>
<c:choose>
<c:when test="${bid.getCanEdit()}">
<div class="input-group"><label for="price" >Bid: </label><input id="price" class="form-control" type="number" min="0.00" step="1.00" name="price" value="${bid.getCurrentBid()}" /></div>
<div class="input-group"><label for="startDate" >Start Date: </label><input id="startDate" class="form-control" type="date" name="startDate" value="${bid.getFormDateStart()}" /></div>
<div class="input-group"><label for="endDate">End date:</label><input name="endDate" class="form-control" type="date" id="endDate" value="${bid.getFormDateEnd()}"/></div>
</c:when>
<c:otherwise>
<div class="input-group"><label for="price" >Bid: </label><input id="price" class="form-control" type="number" min="0.00" step="1.00" name="price" value="${bid.getCurrentBid()}" readonly/></div>
<div class="input-group"><label for="startDate" >Start Date: </label><input id="startDate" class="form-control" type="date" name="startDate" value="${bid.getFormDateStart()}" readonly/></div>
<div class="input-group"><label for="endDate">End date:</label><input name="endDate" class="form-control" type="date" id="endDate" value="${bid.getFormDateEnd()}" readonly/></div>
</c:otherwise>
</c:choose>

<div class="input-group"><label for="user-pic" >Picture (External URL): </label><input id="user-pic" class="form-control" type="text" name="pic" value="${bid.imageUrl}" /></div>
<div class="input-group"><label for="desc">Description (including HTML): </label><textarea name="desc" id="desc" class="form-control">${bid.getDesc()}"</textarea></div>
<div class="input-group"><input class="btn btn-success" type="submit" value="SUBMIT"/></div>
</form>
</div>
<div class="col-sm-6">
 <img class="img-responsive" src="${bid.getImageUrl()}" />
</div>
</div>
</div>

<jsp:include page="/WEB-INF/jsp/Scripts.jsp"></jsp:include>

</body>
</html>