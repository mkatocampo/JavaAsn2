<%-- 
   	Document   : salariesSummary
    Created on : Mar 24, 2022
    Author     : Maria Ocampo

    The view for the process salary
    Support
    -Create
    -Saving changes
    -Delete
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="edu.nbcc.model.*"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <title>Salary</title>
    </head>
    <body class="p-4">
        <%@include file="WEB-INF/jspf/navigation.jspf" %>
        <h1>Salary Summary</h1>
        	<%
        	Locale locale = new Locale("en", "CA");      
        	NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        	Salary deletedSalary = null;
        	    		Salary savedSalary = null;
        	    		Salary createdSalary = null;
        	        	    		
        	    			if (request.getAttribute("deletedSalary") != null){
   	        	    			deletedSalary = (Salary)request.getAttribute("deletedSalary");
   	        	    		}
        	        	    		
        	        		if (request.getAttribute("savedSalary") != null){
        	        	    			savedSalary = (Salary)request.getAttribute("savedSalary");
        	        		}
        	        		
        	        		if (request.getAttribute("createdSalary") != null){
        	        	    			createdSalary = (Salary)request.getAttribute("createdSalary");
        	        		}
        	        		
        	        		Salary salary = null;
        	        			
        	        		if (deletedSalary != null){
        	%>
			<h2><%=deletedSalary.getFirstName()%> Id: <%=deletedSalary.getId()%> has been deleted </h2>
			<%
			} else if (savedSalary != null || createdSalary != null){
				if (savedSalary != null){
					salary = savedSalary;
				}
				if (createdSalary != null){
					salary = createdSalary;
				}	
			%>
 
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
