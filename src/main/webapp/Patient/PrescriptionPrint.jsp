<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, ServletCode.DataClasses.MedicinesData, ServletCode.DataClasses.PrescriptionData, ServletCode.DataClasses.DoctorListData"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<style>
	.data_tables {
		margin: 0 auto;
	    border: 2px solid black;
	    border-radius: 5px;
	    width: 100%;
	   }
	</style>
</head>
<body>
    	
	<div class="main_container">
		
		<div id="patientappointmentData">
		
			<!-- Basic Data -->
			<% 
				PrescriptionData data = (PrescriptionData) request.getAttribute("prescriptionData");
				DoctorListData doctorData = (DoctorListData) request.getAttribute("doctorData");
				String doctorname = doctorData.getName();
				String doctorphone = doctorData.getPhone_number();
				String doctoremail = doctorData.getEmailID();
				String doctor_degree = doctorData.getDegree();
				String doctor_dept = doctorData.getDept_name();
			%>
				
			<div>
				<div style="display: flex; justify-content: space-between;">
					<img style="width: 33%; margin-left: 5px;" src="images/Logo1.png" alt="Care Connect Hospital Logo">
						<ul style="list-style: none; margin-right: 5px;">
							<p><b><%= (doctorname != null) ? doctorname : "Care Connect" %></b><br>
							<%= (doctor_degree != null && doctor_dept != null) ? doctor_degree +", "+doctor_dept : "Nashik, Maharashtra, IN" %><br>
							<%= (doctorphone != null) ? doctorphone : "+91 98765 43210" %><br>
							<%= (doctoremail != null) ? doctoremail : "careconnect247@gmail.com" %></p>
						</ul>
				</div>
					<hr style="border: 1px solid blue; margin: 10px 0;"><br>
					
					<b>Date: </b><%= data.getDate() %><br>
					<b><p style="text-transform: capitalize;">Patient Name: </b><%= data.getPatient_name() %></p>
					<b>Weight: </b><%= data.getWeight() %><br><br>
					<b>Diagnosis: </b><%= data.getDiagnosis() %><br><br>
					<b>Preferred Tests: </b><%= (data.getTests() != null) ? data.getTests() : "-" %><br><br>
					
					<!-- Medicines -->
					<b>Medicines: </b>
					<table border='1' class="data_tables" style="width:100%;">
						<tr id="headerRow" style="text-align: center;">
							<th>Sr No.</th>
							<th>Medicine</th>
							<th>Type</th>
							<th>Measure</th>
							<th>Dosage</th>
							<th>Instruction</th>
							<th>Days</th>
						</tr>
						
						<%
							// Retrieve the dataList from the request
							List<MedicinesData> dataList = (List<MedicinesData>) request.getAttribute("dataList");
							int tableRowNumber = 1;
							if(dataList != null)
							{
								for(MedicinesData item : dataList)
								{
						%>
									<tr>
										<td style="text-align: center;"><%= tableRowNumber++ %></td>
										<td style="text-transform: capitalize; text-align: center;"><%= item.getMedicine_name() %></td>
										<td style="text-align: center;"><%= item.getMed_type() %></td>
										<td style="text-align: center;"><%= item.getMeasure() %></td>
										<td style="text-align: center;"><%= item.getDosage() %></td>
										<td style="text-align: center;"><%= item.getInstruction() %></td>
										<td style="text-align: center;"><%= item.getDays() %></td>
									</tr>
						<%
								}
							}
						%>
					</table>
					
					<!-- Fees -->
					<br><b>Fees: </b><%= data.getFees() %><br><br>
					<b>Remarks: </b><%= (data.getRemarks() != null) ? data.getRemarks() : "-" %><br><br>
					<b>Follow Up: </b><%= (data.getFollow_up() != null) ? data.getFollow_up() : "-" %><br>
				</div>
		</div>
	</div>
    
    
    <script>
		window.onload = function() {
		  window.print();
		};
	</script>
</body>
</html>