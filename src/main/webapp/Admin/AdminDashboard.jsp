 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List"%>

<!DOCTYPE html>
<html lang="en">
<body>	
    <%@ include file="AdminHeader.jsp" %>
    <div class="main_container">
        <p class="container_heading">DASHBOARD</p>
        <div class="box_container_list" style="width: 80%;">
            <a href="AdminServlet?action=doctors_list">
                <div class="box_container">
                    <label class="box_container_label">
                        Total Doctors: <%= request.getAttribute("totalDoctors") %></label>
                    <img src="images/doctor_icon.png" alt="Hospital Logo" style="width:50%;">
                </div>
            </a>
            
            <a href="AdminServlet?action=patients_list">
                <div class="box_container">
                    <label class="box_container_label">
                        Total Patients: <%= request.getAttribute("totalPatients") %></label>
                    <img src="images/patient_icon.png" alt="Hospital Logo" style="width: 50%;">
                </div>
            </a>
            
            <a href="AdminServlet?action=appointments">
                <div class="box_container">
                    <label class="box_container_label">
                        Total Appointments: <%= request.getAttribute("totalAppointments") %></label>
                    <img src="images/appointment_icon.png" alt="Hospital Logo" style="width: 50%;">
                </div>
            </a>
        </div>

        <div class="box_container_list" style="width: 55%;">        
            <a href="AdminServlet?action=departments">
                <div class="box_container">
                    <label class="box_container_label">
                        Total Departments: <%= request.getAttribute("totalDepartments") %></label>
                    <img src="images/departments_icon.png" alt="Hospital Logo" style="width: 50%;">
                </div>
            </a>
            
            <div class="box_container">
                <label class="box_container_label">
                    Total Staffs: 35</label>
                <img src="images/staff_icon.png" alt="Hospital Logo" style="width: 50%;">
            </div>
        </div>
    </div>
</body>
</html>