<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, ServletCode.DataClasses.DepartmentData"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<link rel="stylesheet" href="css/nav_layout.css">
    <link rel="stylesheet" href="css/main_body_layout.css">
    <link rel="stylesheet" href="css/form_layout.css">
    <link rel="stylesheet" href="css/navigation_layout.css">
</head>
<body>
    <%@ include file="AdminHeader.jsp" %><br>  
	
	<div class="main_container">
		<div class="form-container" id="form_container">
	    	<form action="AddNewDepartmentServlet" method="post" class="form-form">
	            <label for="dept_name">Enter New Department</label>
	            <input class="name_input_departments_form" type="text" id="dept_name" name="dept_name" required>
	            <button type="submit">Save</button>
	        </form>
	    </div><br>

		<div class="search_div">
			<input type="text" id="searchInput" placeholder="Search...">
		</div><br>
	    	
    		<table border='1' id="dataTable" class="data_tables">
			<tr style="font-size:18px;">
		    		<th>Sr No.</th>
		    		<th>Name</th>
		    		<th>Total Doctors</th>
	    	</tr>	    		
    		<% 
	            List<DepartmentData> dataList = (List<DepartmentData>) request.getAttribute("dataList");
    			int tableRowNumber = 1;
	            if(dataList != null)
	            {
	                for(DepartmentData data : dataList)
	                {
	        %>
			            <tr>
				    		<td style="text-align: center;"><%= tableRowNumber++ %></td>
				    		<td style="text-transform: capitalize; text-align: center;"><%= data.getName() %></td>
				    		<td style="text-align: center;"><%= data.getTotal_doctors() %></td>
			    		</tr>
	        <% 
	                }
	            }
            %>	            
    	</table>  
	</div>

	<script>
		searchTable("dataTable", "searchInput");  // Call the search function
	</script>

</body>
</html>