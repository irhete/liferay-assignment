<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1>Add customer</h1>

<portlet:defineObjects />

<portlet:renderURL var="addCustomerMethodURL">
	<portlet:param name="action" value="addCustomer"></portlet:param>
</portlet:renderURL>
<form:errors path="customer" />
<c:if test="${not empty success}">
	<p class="success">${success}</p>
</c:if>

<form:form action="${addCustomerMethodURL}" method="post"
	commandName="customer">
	<table id="addCustomerTable">
		<tr>
			<th><spring:message code="firstname.text"/>:</th>
			<td><input name="firstName" type="text" /></td>
			<td><form:errors path="firstName"
					cssClass="error" /></td>
		</tr>
		<tr>
			<th><spring:message code="lastname.text"/>:</th>
			<td><input name="lastName" type="text" /></td>
			<td><form:errors path="lastName"
					cssClass="error" /></td>
		</tr>
		<tr>
			<th><spring:message code="birthdate.text"/>:</th>
			<td><input name="birthDate" type="date" /></td>
			<td><form:errors path="birthDate"
					cssClass="error" /></td>
		</tr>
		<tr>
			<th><spring:message code="id.code.text"/>:</th>
			<td><input name="IDcode" type="text" /></td>
		</tr>
		<tr>
			<th />
			<td><input type="submit"
				value="<spring:message code="add.text"/>" /></td>
		</tr>
	</table>
</form:form>
