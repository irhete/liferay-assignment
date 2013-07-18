<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Add customer</h1>

<portlet:defineObjects />

<portlet:renderURL var="addCustomerMethodURL">
	<portlet:param name="action" value="addCustomer"></portlet:param>
</portlet:renderURL>

<c:choose>
	<c:when test="${not empty errors}">
		<c:forEach items="${errors}" var="validationError">
			<p class="error">${validationError.code}</p>
	</c:forEach>
	</c:when>
	<c:when test="${not empty error}">
		<p class="error">${error}</p>
	</c:when>
	<c:when test="${not empty success}">
		<p class="success">${success}</p>
	</c:when>
</c:choose>

<form:form action="${addCustomerMethodURL}" method="post" modelAttribute="customer">
	<table id="addCustomerTable">
		<tr>
			<th>First name:</th>
			<td><input name="firstName" type="text" /></td>
		</tr>
		<tr>
			<th>Last name:</th>
			<td><input name="lastName" type="text" /></td>
		</tr>
		<tr>
			<th>Birth date:</th>
			<td><input name="unparsedBirthDate" type="date" /></td>
		</tr>
		<tr>
			<th>ID code:</th>
			<td><input name="IDcode" type="text" /></td>
		</tr>
		<tr>
			<th />
			<td><input type="submit" value="Add" /></td>
		</tr>
	</table>
</form:form>
