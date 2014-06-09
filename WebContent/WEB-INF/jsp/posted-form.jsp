<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, edu.neumont.csc280.models.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>Posted Form</title>
<link href="<c:out value="${pageContext.request.contextPath}"></c:out>/css/bootstrap.min.css" rel="stylesheet"/>
<link href="<c:out value="${pageContext.request.contextPath}"></c:out>/css/styles.css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="Navigation.jsp"/>
      <div class="container">
      <div class="row platform">
<h1>Your bid was successfully posted!</h1>
<a href="<c:out value="${pageContext.request.contextPath}"></c:out>/item/<c:out value="${id}"></c:out>" >Click here to see your bid</a>
</div>
</div>
        <script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"></c:out>/js/jquery.js"> </script>
       <script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"></c:out>/js/custom.js"> </script>
        <script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"></c:out>/js/bootstrap.js"> </script>
</body>
</html>