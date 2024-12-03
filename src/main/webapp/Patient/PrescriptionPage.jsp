<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, ServletCode.DataClasses.MedicinesData, ServletCode.DataClasses.PrescriptionData"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<script src="https://cdn.jsdelivr.net/npm/chart.js@3.9.1/dist/chart.min.js"></script>
</head>
<body>
    <%@ include file="PatientHeader.jsp" %>    
    
	<div id="patientappointmentData">	

		<!-- Basic Data -->
		<% 
			PrescriptionData data = (PrescriptionData) request.getAttribute("prescriptionData");
		%>
		
		<button style="margin-left:5%;">
			<a href="PatientServlet?action=print_prescription&id=<%= data.getId() %>" target="_blank">
				Print</a>
		</button><br><br>
		
		<div style="border: 2px; border-radius: 5px; padding: 10px; text-align: left; margin: auto; width:90%;">			
				
			<b>Date: </b><%= data.getDate() %><br>
			<b><p style="text-transform: capitalize;">Patient Name: </b><%= data.getPatient_name() %></p>
			<b>Weight: </b><%= data.getWeight() %><br><br>
			<b>Diagnosis: </b><%= data.getDiagnosis() %><br><br>
			<b>Preferred Tests: </b><%= (data.getTests() != null) ? data.getTests() : "-" %><br><br>
		
			<!-- Medicines -->
			<b>Medicines: </b>
			<table border='1' class="data_tables" style="width:100%;">
				<tr style="font-weight: bold; text-transform: capitalize; text-align: center;">
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
	    	<b>Payment Type: </b><%= data.getPayment_type() %><br><br>
	    	<b>Remarks: </b><%= (data.getRemarks() != null) ? data.getRemarks() : "-" %><br><br>
	    	<b>Follow Up: </b><%= (data.getFollow_up() != null) ? data.getFollow_up() : "-" %><br>
		</div>
	</div>
    
</body>
</html>