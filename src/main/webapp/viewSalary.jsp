<%-- 
    Document   : salaries
    Created on : Mar 24, 2022
    Author     : Maria Ocampo

    The view to support retrieval of salary or creation of a new salary
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="edu.nbcc.model.*"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="WEB-INF/jspf/header.jspf"%>
<title>Next Year Salary Predictor</title>
</head>
<body class="p-4">
	<%@include file="WEB-INF/jspf/navigation.jspf"%>
        <h1>Find By Last Name</h1>
        
        	<form>
				<input type="text" name="lastName" class="form-control"/></br>
				<input class="btn btn-primary" type="submit" value="Get Salary"/>
 			</form>
 			
        	<%
        	Locale locale = new Locale("en", "CA");      
        	NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        	
        	if (request.getAttribute("vm") != null){
    			Salary salary = (Salary)request.getAttribute("vm");
   			
			%>
			</br>
    	    <table class="table table-striped">                   
                    <tr>
                        <th>
                            Id
                        </th>
                        <th>
                            First Name
                        </th> 
                        <th>
                            Last Name
                        </th> 
                        <th>
                            Current Salary
                        </th>
                        <th>
                            Next Year Salary
                        </th>
                    </tr>
                    <tr>
                        <td><%=salary.getId()%></td>
                        <td><%=salary.getFirstName()%></td>
                        <td><%=salary.getLastName()%></td>
						<td><%=currencyFormatter.format(salary.getCurrentSalary())%></td>
						<td><%=currencyFormatter.format(salary.getNextYearSalary())%></td>
                    </tr>
                </table>
    	   <%}else{ %>
    	   <a href="${pageContext.request.contextPath}/salaries">salaries</a>
      <%}%>
       
</body>
</html>