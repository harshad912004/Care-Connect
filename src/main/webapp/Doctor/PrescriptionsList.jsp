<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, ServletCode.DataClasses.PrescriptionData"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<link rel="stylesheet" href="css/nav_layout.css">
    <link rel="stylesheet" href="css/main_body_layout.css">
    <link rel="stylesheet" href="css/form_layout.css">
</head>
<body>
    <%@ include file="DoctorHeader.jsp" %>    

	<div class="main_container">
		<p class="container_heading">PRESCRIPTIONS</p>    
    
		<div class="search_div">
			<input type="text" id="searchInput" placeholder="Search...">
		</div><br>
			
		<table border='1' id="dataTable" class="data_tables">
			<tr style="font-size:18px;">
					<th>Sr No.</th>
					<th>Patient Name</th>
					<th>Date</th>
					<th>Diagnosis</th>
					<th>Fees</th>
					<th>Follow Up</th>
					<th>Action</th>
				</tr>
				
				<%
					List<PrescriptionData> dataList = (List<PrescriptionData>) request.getAttribute("dataList");
					int tableRowNumber = 1;
					if(dataList != null)
					{
						for(PrescriptionData data : dataList)
						{
				%>
							<tr>
								<td style="text-align: center;"><%= tableRowNumber++ %></td>
								<td style="text-transform: capitalize; text-align: center;"><%= data.getPatient_name() %></td>
								<td style="text-align: center;"><%= data.getDate() %></td>
								<td style="text-align: center;"><%= data.getDiagnosis() %></td>
								<td style="text-align: center;"><%= data.getFees() %></td>
								<td style="text-align: center;"><%= data.getFollow_up() %></td>
								<td>
									<a href="DoctorServlet?action=view_prescription&id=<%= data.getId() %>" class="view_prescription_button">View</a>
								</td>
							</tr>
				<%
						}
					}
				%>
			</table>
	</div>    
    <script>
	    searchTable("dataTable", "searchInput"); // Call the search function
	</script>	
</body>
</html>