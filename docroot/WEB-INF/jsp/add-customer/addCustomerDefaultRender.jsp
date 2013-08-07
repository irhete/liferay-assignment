<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1><spring:message code="add.customer.text" text='Add Customer'/></h1>

<portlet:defineObjects />

<portlet:renderURL var="addCustomerMethodURL">
	<portlet:param name="action" value="addCustomer"></portlet:param>
</portlet:renderURL>

<c:if test="${not empty success}">
	<p class="success">${success}</p>
</c:if>

<form:errors path="customer" cssClass="error" />
<form:form action="${addCustomerMethodURL}" method="post"
	commandName="customer">
	<table id="addCustomerTable">
		<tr>
			<th><spring:message code="firstname.text" text='First name'/>:</th>
			<td><input name="firstName" type="text" /></td>
			<td><form:errors path="firstName"
					cssClass="error" /></td>
		</tr>
		<tr>
			<th><spring:message code="lastname.text" text='Last name'/>:</th>
			<td><input name="lastName" type="text" /></td>
			<td><form:errors path="lastName"
					cssClass="error" /></td>
		</tr>
		<tr>
			<th><spring:message code="birthdate.text" text='Birth date'/>:</th>
			<td><input name="birthDate" type="date" /></td>
			<td><form:errors path="birthDate"
					cssClass="error" /></td>
		</tr>
		<tr>
			<th><spring:message code="id.code.text" text='ID code'/>:</th>
			<td><input name="IDcode" type="text" /></td>
		</tr>
		<tr>
			<td><input type="submit" value="<spring:message code="add.text" text='Add'/>" /></td>
		</tr>
	</table>
</form:form>
