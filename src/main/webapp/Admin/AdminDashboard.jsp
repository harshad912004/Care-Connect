<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List"%>
<%@ include file="AdminHeader.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8" />
	<title>Admin Dashboard</title>
	<script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-blue-50 font-sans text-gray-800">

	<!-- Dashboard Heading -->
	<section class="px-6 py-10">
		<h1 class="text-3xl font-bold text-blue-700 mb-6 text-center">Welcome to Dashboard</h1>

		<!-- Cards Section -->
		<div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6 max-w-6xl mx-auto">

			<!-- Total Doctors -->
			<a href="AdminServlet?action=doctors_list" class="block">
				<div class="bg-white hover:bg-blue-50 border border-blue-200 p-6 rounded-xl shadow hover:shadow-md transition text-center flex flex-col items-center space-y-4 border border-blue-300">
					<label class="text-lg font-semibold text-gray-700">Total Doctors: <%= request.getAttribute("totalDoctors") %></label>
					<img src="images/doctor_icon.png" alt="Doctor Icon" class="w-20 h-20 object-contain" />
				</div>
			</a>
			
			<!-- Total Patients -->
			<a href="AdminServlet?action=patients_list" class="block">
				<div class="bg-white hover:bg-blue-50 border border-blue-200 p-6 rounded-xl shadow hover:shadow-md transition text-center flex flex-col items-center space-y-4 border border-blue-300">
			    	<label class="text-lg font-semibold text-gray-700">Total Patients: <%= request.getAttribute("totalPatients") %></label>
			    	<img src="images/patient_icon.png" alt="Patient Icon" class="w-20 h-20 object-contain" />
			  	</div>
			</a>
			
			<!-- Appointments Today -->
			<a href="AdminServlet?action=appointments" class="block">
				<div class="bg-white hover:bg-blue-50 border border-blue-200 p-6 rounded-xl shadow hover:shadow-md transition text-center flex flex-col items-center space-y-4 border border-blue-300">
				    <label class="text-lg font-semibold text-gray-700">Appointments: <%= request.getAttribute("totalAppointments") %></label>
				    <img src="images/appointment_icon.png" alt="Appointment Icon" class="w-20 h-20 object-contain" />
			  	</div>
			</a>
			
			<!-- Departments -->
			<a href="AdminServlet?action=departments" class="block">
				<div class="bg-white hover:bg-blue-50 border border-blue-200 p-6 rounded-xl shadow hover:shadow-md transition text-center flex flex-col items-center space-y-4 border border-blue-300">
				    <label class="text-lg font-semibold text-gray-700">Departments: <%= request.getAttribute("totalDepartments") %></label>
				    <img src="images/departments_icon.png" alt="Department Icon" class="w-20 h-20 object-contain" />
			  	</div>
			</a>
			
			<!-- Reports Pending -->
			<div class="bg-white hover:bg-blue-50 border border-blue-200 p-6 rounded-xl shadow hover:shadow-md transition text-center flex flex-col items-center space-y-4 border border-blue-300">
				<label class="text-lg font-semibold text-gray-700">Total Staffs: 35</label>
				<img src="images/staff_icon.png" alt="Staff Icon" class="w-20 h-20 object-contain" />
			</div>
		</div>
	</section>

</body>

</html>
