<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<body>    
	<%@ include file="PatientHeader.jsp" %>	
	<p class="container_heading">DASHBOARD</p>
    <div class="box_container_list" style="width: 80%;">
        <a href="PatientServlet?action=requested_appointments">
        	<div class="box_container">
	            <label class="box_container_label">
	            	Total Requested Appointments: <%= request.getAttribute("totalRequestedAppointments") %>
	            </label>
	            <img src="images/appointment_icon.png" alt="Hospital Logo" style="width:50%;">
        	</div>
        </a>
        <a href="PatientServlet?action=past_appointments">
        	<div class="box_container">
	            <label class="box_container_label">
	            	Total Past Appointments: <%= request.getAttribute("totalPastAppointments") %>
	            </label><br>
	            <img src="images/appointment_icon.png" alt="Hospital Logo" style="width:50%;">
	        </div>
	    </a>
	    <a href="PatientServlet?action=prescriptions_list">
        	<div class="box_container">
	            <label class="box_container_label"> Prescription Lists </label><br>
	            <img src="images/prescription_icon.png" alt="Hospital Logo" style="width:50%;">
	        </div>
	    </a>
	</div>
</body>
</html>