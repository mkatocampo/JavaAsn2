<%-- 
    Document   : salaries
    Created on : Mar 24, 2022
    Author     : Maria Ocampo

    The view to support retrieval of salary or creation of a new salary
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="edu.nbcc.model.*"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="WEB-INF/jspf/header.jspf"%>
<title>Next Year Salary Predictor</title>
</head>
<body class="p-4">
	<%@include file="WEB-INF/jspf/navigation.jspf"%>
	<%
		if (request.getAttribute("vm") != null){
				SalaryModel vm = (SalaryModel)request.getAttribute("vm");
		%>
	
	<form method="POST" action="save">
	
	<div class="form-group container"
			style="margin: 20px; border: solid 2px lightgrey; padding-right: 80px; padding-left: 80px; padding-top: 20px; padding-bottom: 20px">
			<h1 style="text-align: center; color: grey">Next Year Salary
				Predictor</h1>
			<div class="row">
				<div class="col-4">
					<label>First Name</label><br> <label>Last Name</label><br>
					<label>Current Salary</label><br>
				</div>

				<div class="col-8">
		
					<!-- Display details in view mode -->
					<%
					if (vm.getSalary() != null && vm.getSalary().getId() != 0){
					%>
						<label>Salary Id:</label> ${vm.salary.id}
						<input type="hidden" value='${vm.salary.id}' name="id" />
					<%
					}
					%>
		
					<input type="text" name="firstName" class="form-control" value='${vm.salary.firstName}' />
					
					<input type="text" name="lastName" class="form-control" value='${vm.salary.lastName}' />
					
					<input type="text" name="currentSalary" class="form-control" value='${vm.salary.currentSalary != 0 ? vm.salary.currentSalary:"" }' />
					
					<input type="hidden" name="nextYearSalary" class="form-control" value='${vm.salary.nextYearSalary != 0 ? vm.salary.nextYearSalary:"" }' />
		
					<!-- Decide on what buttons to render. When updating, show Save and Delete, create show Create -->
					<%
					if (vm.getSalary() != null && vm.getSalary().getId() > 0) {
					%>
					
					<input class="btn btn-primary" type="submit" value="Delete"
						name="action" /> 
					
					<input class="btn btn-primary" type="submit"
						value="Save" name="action" />
					<%}else{%>
					<input class="btn btn-primary" type="submit" value="Create"
						name="action" />
					<%}%>
					
				</div>
			</div>
		</div>
	</form>
	
	<%}%>
	
	<!--Set up errors here -->
	<%if (request.getAttribute("error") != null){ 
		ErrorModel em = (ErrorModel)request.getAttribute("error");
		if (em.getErrors() != null && em.getErrors().size() > 0){
	%>
	<ul class="alert alert-danger">
		<%for (String err:em.getErrors()){ %>
		<li><%=err%></li>
		<%} %>
	</ul>
	<%}
	}%>


</body>
</html>
