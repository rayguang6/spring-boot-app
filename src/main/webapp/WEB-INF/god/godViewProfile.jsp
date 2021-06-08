

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<h1>God View Profile</h1>


<c:forEach var="student" items="${students}">

	<c:url var="profilePhoto" value="/profilephoto/${student.id}" />
	<c:url var="profileLink" value="/profile/${student.id}" />
	<c:url var="chatviewLink" value="/chatview/${student.id}" />


	<div class="row person-row">
		<div class="col-md-12">

			<div class="results-details">
			
				<div class="student basic profile">
					<a href="${profileLink}"><c:out value="${student.username}" /></a>
					
					<c:out value="Rep: ${student.reputation}"></c:out>
					<c:out value="${student.divingrate}"></c:out>
				</div>


				<div class="lunch">
				
					
					
				</div>
				
			</div>

		</div>
	</div>

</c:forEach>

