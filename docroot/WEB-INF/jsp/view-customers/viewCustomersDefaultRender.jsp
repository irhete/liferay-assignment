<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<h1><spring:message code="customers.text" text="Customers"/></h1>
<table id="viewCustomersTable">
	<tr>
		<th><spring:message code="number.text" text='No'/></th>
		<th><spring:message code="firstname.text" text='First name'/></th>
		<th><spring:message code="lastname.text" text='Last name'/></th>
		<th><spring:message code="birthdate.text" text='Birth name'/></th>
		<th><spring:message code="id.code.text" text='ID code'/></th>
	</tr>
	<c:forEach items="${customers}" var="customer" varStatus="i">
		<tr>
			<td>${i.index+1}</td>
			<td>${customer.firstName}</td>
			<td>${customer.lastName}</td>
			<td>${customer.birthDate}</td>
			<td>${customer.IDcode}</td>
		</tr>
	</c:forEach>
</table>