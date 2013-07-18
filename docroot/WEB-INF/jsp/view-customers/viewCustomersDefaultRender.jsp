<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>Customers</h1>
<table id="viewCustomersTable">
	<tr>
		<th>No</th>
		<th>First name</th>
		<th>Last name</th>
		<th>Birth date</th>
		<th>ID code</th>
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