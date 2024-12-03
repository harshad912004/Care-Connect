<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge" name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Care Connect | Patient Logged In</title>
    <link rel="stylesheet" href="css/nav_layout.css">
    <link rel="stylesheet" href="css/main_body_layout.css">
    <link rel="stylesheet" href="css/form_layout.css">
	<script src="js/search.js"></script>
</head>
<body>
	<div class="left-panel">
		<nav>
			<img src="images/Logo1.png" alt="Care Connect Hospital Logo">
			<div class="username">
				<% String username = (String) session.getAttribute("user_name"); %>
				<button class="username_button">
					<%= (username != null) ? username : "Patient Account" %>
				</button>
				<div class="username-content">
					<a href="PatientServlet?action=update_profile">Update Profile</a>
					<a href="PatientServlet?action=change_password">Change Password</a>
					<a href="LogoutServlet">Logout</a>
				</div>
			</div>
		</nav>
        
        <div class="dropbtn">
            <div class="dropdown">
                <button class="dropbtn">
                	<a href="PatientServlet?action=dashboard">DASHBOARD</a>
                </button>
            </div>            
            <div class="dropdown">
                <button class="dropbtn">APPOINTMENT</button>
                <div class="dropdown-content">
                    <a href="PatientServlet?action=book_appointment">Book Appointment</a>
                    <a href="PatientServlet?action=requested_appointments">Newly Requested Appointment</a>
                    <a href="PatientServlet?action=past_appointments">Past Appointments</a>
                </div>
            </div>
            <div class="dropdown">
                <button class="dropbtn">
                	<a href="PatientServlet?action=prescriptions_list">PRESCRIPTIONS</a>
                </button>
            </div>   
        </div>
    </div><br>
    
    <%-- Retrieve and display error messages (if any) --%>
	<% List<String> errors = (List<String>) request.getAttribute("errors"); %>
	<%
		if (errors != null && !errors.isEmpty())
		{
	%>
	  		<ul class="error-messages">
	<%
			for (String error : errors)
			{
	%>
	      		<li style="color: red;"><%= error %></li>
	<%
			}
	%>
			</ul>
	<%
		}
	%>
	
	<%-- Retrieve and display Success messages (if any) --%>
	<% String success_msg = (String) request.getAttribute("successMessage"); %>
	<%
		if (success_msg != null && !success_msg.isEmpty())
		{
	%>
        	<p style="color: green;"><%= success_msg %></p>
	<%
		}
	%>

</body>
</html>