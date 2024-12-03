<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, ServletCode.DataClasses.AppointmentData"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<link rel="stylesheet" href="css/nav_layout.css">
    <link rel="stylesheet" href="css/main_body_layout.css">
    <link rel="stylesheet" href="css/form_layout.css">
</head>
<body>
    <%@ include file="PatientHeader.jsp" %>    
    <p class="container_heading">PAST APPOINTMENT</p>   
    
    <div class="search_div">
		<input type="text" id="searchInput" placeholder="Search...">
	</div><br>
    	
    	<table border='1' id="dataTable" class="data_tables">
			<tr style="font-size:18px;">
	    		<th>Sr No.</th>
	    		<th>Doctor Name</th>
	    		<th>Department</th>
	    		<th>Date & Time of Appointment</th>
	    		<th>Status</th>
    		</tr>
    		
    		<% 
	            // Retrieve the dataList from the request
	            List<AppointmentData> dataList = (List<AppointmentData>) request.getAttribute("dataList");
    			int tableRowNumber = 1;
	            if(dataList != null)
	            {
	                for(AppointmentData data : dataList)
	                {
	        %>
			            <tr>
				    		<td style="text-align: center;"><%= tableRowNumber++ %></td>
				    		<td style="text-transform: capitalize; text-align: center;"><%= data.getDoctor_name() %></td>
				    		<td style="text-align: center;"><%= data.getDepartment() %></td>
				    		<td style="text-align: center;"><%= data.getDate_time_appointment() %></td>
				    		<td style="text-align: center;"><%= data.getStatus_flag() %></td>
			    		</tr>
	        <% 
	                }
	            }
            %>            
    	</table>
    
    <script>
	    searchTable("dataTable", "searchInput");  // Call the search function
	</script>	
	
</body>
</html>