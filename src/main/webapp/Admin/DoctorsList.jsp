<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, ServletCode.DataClasses.DoctorListData"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<link rel="stylesheet" href="css/nav_layout.css">
    <link rel="stylesheet" href="css/main_body_layout.css">
    <link rel="stylesheet" href="css/form_layout.css">
</head>
<body>
    <%@ include file="AdminHeader.jsp" %>    
	<p class="container_heading">DOCTORS</p>	
	<div class="main_container">		
		<div class="search_div">
			<input type="text" id="searchInput" placeholder="Search...">
		</div><br>			
		<table border='1' id="dataTable" class="data_tables">
			<tr style="font-size:18px;">
				<th>Sr No.</th>
				<th>Name</th>
				<th>Email</th>
				<th>Phone Number</th>
				<th>Gender</th>
				<th>Date of Birth</th>
				<th>Address</th>
				<th>Specialization</th>
				<th>Degree</th>
				<th>Experience(in Years)</th>
			</tr>
			<%
				List<DoctorListData> dataList = (List<DoctorListData>) request.getAttribute("dataList");
				int tableRowNumber = 1;
				if(dataList != null)
				{
					for(DoctorListData data : dataList)
					{
			%>
						<tr>
							<td style="text-align: center;"><%= tableRowNumber++ %></td>
							<td style="text-transform: capitalize; text-align: center;"><%= data.getName() %></td>
							<td style="text-align: center;"><%= data.getEmailID() %></td>
							<td style="text-align: center;"><%= data.getPhone_number() %></td>
							<td style="text-align: center;"><%= data.getGender() %></td>
							<td style="text-align: center;"><%= data.getDate_of_birth() %></td>
							<td style="text-align: center;"><%= data.getAddress() %></td>
							<td style="text-align: center;"><%= data.getDept_name() %></td>
							<td style="text-align: center;"><%= data.getDegree() %></td>
							<td style="text-align: center;"><%= data.getYear_of_experience() %></td>
						</tr>
			<%
					}
				}
			%>
		</table>
	</div>    
    <script>
	    searchTable("dataTable", "searchInput");
	</script>
</body>
</html>