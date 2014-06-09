<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, edu.neumont.csc280.models.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/jsp/Header.jsp" />
    <body>
    
    
       <jsp:include page="Navigation.jsp"/>

      
   <div class="container">
   <h1>${bid.title} # ${bid.id}</h1>
   <div class="row platform">

        <div class="col-sm-6">
        <form id="bid-form" method="POST" action="${pageContext.request.contextPath}/item/${bid.id}/bid">
        <dl class="product-details">
          <dt>Description: </dt>
        <dd>${bid.getDesc()}</dd>
            <dt>Current Bid:</dt>
            <dd><span class="currencySpan">$</span><span id="current-bid">${bid.getCurrentBid()}</span></dd>
            <dt>Time Left: </dt>
            <dd><span id="time">${time}</span> seconds</dd>
            
            <dt>Start Time:</dt>
            <dd><span><input type="date" name="startDate" value="${bid.getFormDateStart()}" disabled /></span></dd>
            
            <dt>End Time:</dt>
            <dd><input type="date" name="endDate" value ="${bid.getFormDateEnd()}" disabled /></dd>
            <dt><label for="place-bid">Amount to bid:</label></dt>
			<dd>
			<span class="currencySpan">$</span>
			<input id="place-bid" type="text" class="currencyInput" name="money" />
			</dd>
			<dd><input type="hidden" name="id" value="${bid.id}" /></dd>
            <dd><input id="hidden-time" type="hidden" name="time" value="${bid.getTime()}" /></dd>
            <dd>
                <input id="submit" class="btn btn-success" type="submit" value="Place a bid"/>
            </dd>
        </dl>
        </form>

        <a id="edit" href="${pageContext.request.contextPath}/item/${bid.id}/edit" class="btn btn-default dwn-btn">Edit</a>

        <p id="error"></p>
        </div>	
           <div class="col-sm-6">
        <img class="img-responsive" src="${bid.getImageUrl()}" />
        </div>
    </div>
        
   </div>
        <jsp:include page="/WEB-INF/jsp/Scripts.jsp"></jsp:include>
        
    </body>
</html>
