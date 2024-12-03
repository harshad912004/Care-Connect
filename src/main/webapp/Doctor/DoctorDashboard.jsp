<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, ServletCode.DataClasses.AppointmentData"%>

<!DOCTYPE html>
<html lang="en">
<body>
    <%@ include file="DoctorHeader.jsp" %>    
    <div class="main_container">
        <p class="container_heading">DASHBOARD</p>
        <div class="box_container_list" style="width: 80%;">
            <a href="DoctorServlet?action=patients_list">
                <div class="box_container">
                    <label class="box_container_label">
                        Total Patients: <%= request.getAttribute("totalPatients") %><br><br></label>
                    <img src="images/patient_icon.png" alt="Hospital Logo" style="width:50%;">
                </div>
            </a>           
            <a href="DoctorServlet?action=appointment_requests">
                <div class="box_container">
                    <label class="box_container_label">
                        Total Appointment Requests: <%= request.getAttribute("totalAppointmentRequests") %></label>
                    <img src="images/appointment_icon.png" alt="Hospital Logo" style="width:50%;">
                </div>
            </a>            
            <a href="DoctorServlet?action=upcoming_appointments">
                <div class="box_container">
	                <label class="box_container_label">
	                    Total Upcoming Appointments: <%= request.getAttribute("totalUpcomingAppointments") %></label>
	                <img src="images/appointment_icon.png" alt="Hospital Logo" style="width:50%;">
                </div>
            </a>
        </div>        
        <div class="box_container_list" style="width: 55%;">    
            <a href="DoctorServlet?action=past_appointments">
                <div class="box_container">
	                <label class="box_container_label">
	                    Total Past Appointments: <%= request.getAttribute("totalPastAppointments") %></label>
	                <img src="images/appointment_icon.png" alt="Hospital Logo" style="width:50%;">
                </div>
            </a>
            <a href="DoctorServlet?action=prescriptions_list">
                <div class="box_container">
	                <label class="box_container_label">
	                    Total Prescriptions: <%= request.getAttribute("totalPrescriptions") %></label>
	                <img src="images/prescription_icon.png" alt="Hospital Logo" style="width:50%;">
                </div>
            </a>
        </div>
    </div>    
</body>
</html>