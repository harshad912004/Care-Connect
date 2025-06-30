<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>Care Connect | Admin Panel</title>
	<script src="https://cdn.tailwindcss.com"></script>
	<script src="js/search.js"></script>
</head>

<body class="bg-white text-gray-800 font-sans">

	<!-- Top Contact Info -->
	<div class="bg-blue-100 text-right py-2 px-3 text-sm font-semibold">
		careconnect247@gmail.com | +91 0253-987654
	</div>

	<!-- Admin Navbar -->
	<nav class="bg-white shadow-md sticky top-0 z-50">
		<div class="container mx-auto py-3 px-4 flex flex-col md:flex-row justify-between items-center space-y-4 md:space-y-0">

			<!-- Left: Logo -->
			<div class="flex items-center space-x-4">
				<img src="images/Logo1.png" alt="Care Connect Logo" class="h-20" />
			</div>
	
			<!-- Center: Dropdown Navigation -->
			<div class="flex flex-wrap gap-4 text-lg font-semibold text-blue-700">
				<!-- Dashboard -->
				<div class="relative group">
					<a href="AdminServlet?action=dashboard" class="px-4 py-2 block hover:text-blue-500">DASHBOARD</a>
				</div>
				
				<!-- Doctors -->
				<div class="relative group">
					<button class="px-4 py-2 hover:text-blue-500">DOCTORS</button>
					<div class="absolute hidden group-hover:block bg-white border rounded-md shadow-lg mt-1 z-20">
						<a href="AdminServlet?action=doctors_list" class="block px-4 py-2 hover:bg-blue-50">All Doctors</a>
						<a href="AdminServlet?action=add_new_doctor" class="block px-4 py-2 hover:bg-blue-50">Add New Doctors</a>
					</div>
				</div>
				
				<!-- Patients -->
				<div class="relative group">
					<button class="px-4 py-2 hover:text-blue-500">PATIENTS</button>
					<div class="absolute hidden group-hover:block bg-white border rounded-md shadow-lg mt-1 z-20">
						<a href="AdminServlet?action=patients_list" class="block px-4 py-2 hover:bg-blue-50">All Patients</a>
					</div>
				</div>
				
				<!-- Appointments -->
				<div class="relative group">
					<a href="AdminServlet?action=appointments" class="px-4 py-2 block hover:text-blue-500">APPOINTMENTS</a>
				</div>
				
				<!-- Departments -->
				<div class="relative group">
					<a href="AdminServlet?action=departments" class="px-4 py-2 block hover:text-blue-500">DEPARTMENTS</a>
				</div>
			</div>
			
			<!-- Right: User Dropdown -->
			<div class="relative group text-right">
				<% String username = (String) session.getAttribute("user_name"); %>
				<button class="px-4 py-2 rounded-md bg-blue-600 text-white hover:bg-blue-700 transition font-semibold">
					<%= (username != null) ? username : "Admin Account" %>
				</button>
				<div class="absolute hidden group-hover:block bg-white right-0 mt-2 border rounded-md shadow-lg z-20 w-48 text-left">
					<a href="AdminServlet?action=update_profile" class="block px-4 py-2 hover:bg-blue-50 text-sm">Update Profile</a>
					<a href="AdminServlet?action=change_password" class="block px-4 py-2 hover:bg-blue-50 text-sm">Change Password</a>
					<a href="LogoutServlet" class="block px-4 py-2 text-red-600 hover:bg-red-50 text-sm">Logout</a>
				</div>
			</div>
		</div>
	</nav>
	
	<%-- Retrieve and display error messages (if any) --%>
	<% List<String> errors = (List<String>) request.getAttribute("errors"); %>
	<% if (errors != null && !errors.isEmpty()) { %>
	  		<ul class="error-messages">
	<% 		for (String error : errors) { %>
	      		<li style="color: red;"><%= error %></li>
	<% 		} %>
			</ul>
	<% 	} %>
	
	<%-- Retrieve and display Success messages (if any) --%>
	<% String success_msg = (String) request.getAttribute("successMessage"); %>
	<% if (success_msg != null && !success_msg.isEmpty()) { %>
        	<p style="color: green;"><%= success_msg %></p>
	<% } %>
</body>

</html>
