<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List"%>

<!DOCTYPE html>
<html lang="eng">
	<head>
		<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Care Connect | Parent Registration Page</title>
		<link rel="stylesheet" href="css/navigation_layout.css">
	</head>
	<body>
		<!-- navigation bar -->
	    <nav class="navigation_bar">
	        <div class="navigation_buttons">
	            <img class="navigation_image" src="images/Logo1.png" alt="Care Connect Logo">
	            <ul class="ul_navigation_buttons">
	                <li class="li_navigation_buttons">
						<a href="index.html" class="a_navigation_buttons">HOME</a></li>
	            </ul>
	        </div>
	    </nav>
	    
	    <!-- registration form -->
	    <div class="patient_register_main_form">
	    	<form class="patient_register_form" action="PatientRegistrationServlet" method="post">
	    		<h1 class="h1_patient_register_form">Patient Registration Form</h1>
	    		
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
				
				<label class="label_patient_register_form" for="username">Your Name:</label>
	            <input class="name_input_patient_register_form" type="text" id="username" name="username" placeholder="Enter full name" pattern="[a-zA-Z ]+" required>
	
	            <label class="label_patient_register_form" for="email">Your Email:</label>
	            <input class="input_patient_register_form" type="email" id="email" name="email" placeholder="Enter valid email address" required>
	
	            <label class="label_patient_register_form" for="phone">Your Phone Number:</label>
	            <input class="input_patient_register_form" type="tel" id="phone" name="phone" placeholder="Enter 10 digit phone number" maxlength="10" pattern="[1-9]{1}[0-9]{9}" required>
	
	            <label class="label_patient_register_form" for="password">Your Password:</label>
	            <input class="input_patient_register_form" type="password" id="password" name="password" placeholder="Enter password" required>
	
	            <label class="label_patient_register_form" for="address">Your Address:</label>
	            <input class="input_patient_register_form" type="text" id="address" name="address" placeholder="Enter address" required>
	
	            <label class="label_patient_register_form" for="dob">Your Date of Birth:</label>
	            <input class="input_patient_register_form" type="date" id="dob" name="dob" required>
	
	            <label class="label_patient_register_form" for="gender">Select Your Gender:</label>
	            <select class="input_patient_register_form" id="gender" name="gender" required>
	                <option value="Male">Male</option>
	                <option value="Female">Female</option>
	            </select>
	
	            <label class="label_patient_register_form" for="blood-group">Select Your Blood Group:</label>
	            <select class="input_patient_register_form" id="blood-group" name="blood_group" required>
	                <option value="A+">A+</option>
	                <option value="A-">A-</option>
	                <option value="B+">B+</option>
	                <option value="B-">B-</option>
	                <option value="AB+">AB+</option>
	                <option value="AB-">AB-</option>
	                <option value="O+">O+</option>
	                <option value="O-">O-</option>
	            </select>
	            
	            <button class="button_patient_register_form" type="submit">Register Now</button>
	    	</form>
	    </div>
	    
	    <script type="text/javascript">
		    // for birth date to set current date as max date
		    document.addEventListener("DOMContentLoaded", function() {
		        var dateInput = document.getElementById("dob");
		        dateInput.addEventListener('focus', function() {
		            setCurrentDateTimeAsMin(this.value);
		        });
		        dateInput.addEventListener('keyup', function() {
		            setCurrentDateTimeAsMin(this.value);
		        });
		        dateInput.addEventListener('click', function() {
		            setCurrentDateTimeAsMin(this.value);
		        });
		    });

			// Function to set current date and time as min
			function setCurrentDateTimeAsMin() {			   
				const today = new Date().toISOString().slice(0, 10); // Get YYYY-MM-DD
				document.getElementById("dob").setAttribute("max", today);
			}
    	</script>
	</body>
</html>