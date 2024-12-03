<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, ServletCode.DataClasses.PatientListData"%>

<!DOCTYPE html>
<html lang="en">
<body>
    <%@ include file="AdminHeader.jsp" %>
	<div class="main_container">
		<p class="container_heading">PATIENTS</p>
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
				<th>Blood Group</th>
			</tr>
			
			<% 
				List<PatientListData> dataList = (List<PatientListData>) request.getAttribute("dataList");
				int tableRowNumber = 1;
				if(dataList != null)
				{
					for(PatientListData data : dataList)
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
							<td style="text-align: center;"><%= data.getBlood_group() %></td>
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