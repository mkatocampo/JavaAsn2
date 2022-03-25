<%-- 
    Document   : salaries
    Created on : Mar 24, 2022
    Author     : Maria Ocampo

    The view to show all salaries
--%>

<%@page import="java.util.List"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="edu.nbcc.model.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="WEB-INF/jspf/header.jspf"%>
<title>Salary</title>
</head>
<body class="p-4">
	<%@include file="WEB-INF/jspf/navigation.jspf"%>
	<h2>Salaries</h2>
	<%
	Locale locale = new Locale("en", "CA");      
	NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
	
	if (request.getAttribute("vm") != null){
			List<Salary> vm = (List<Salary>)request.getAttribute("vm");
			if (vm.size() > 0){
	%>
			
			<table class="table table-striped">
	
				<tr>
					<th>Id</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Current Salary</th>
					<th>Next Year Salary</th>
				</tr>
			
				<%
							for (Salary salary:vm){
							%>
				<tr>
					<td><a href="salary/<%=salary.getId()%>"><%=salary.getId()%></td>
					<td><%=salary.getFirstName()%></td>
					<td><%=salary.getLastName()%></td>
					<td><%=currencyFormatter.format(salary.getCurrentSalary())%></td>
					<td><%=currencyFormatter.format(salary.getNextYearSalary())%></td>
				</tr>
				<%}%>
			</table>
			<%}
		}else{
		
	%>
	<h4>No Salaries</h4>
	<%} %>
</body>
</html>
