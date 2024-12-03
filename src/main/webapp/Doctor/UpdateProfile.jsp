<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, ServletCode.DataClasses.LoggedInUserData"%>

<!DOCTYPE html>
<html lang="en">
<body>
    <%@ include file="DoctorHeader.jsp" %><br>
	<div class="main_container">
		<div class="form-container">
			<form action="DoctorServlet?action=save_profile" method="post" class="form-form">
				<% LoggedInUserData userData = (LoggedInUserData) request.getAttribute("user_data"); %>	            
				<h2>Update Profile</h2>
				
				<label for="username">Full Name<span style="color:red;">*</span></label>
				<input type="text" id="user_name" name="user_name" value="<%= userData.getName() %>" placeholder="Enter full name" pattern="[a-zA-Z ]+" style="text-transform: capitalize;" required>
	
				<label for="email">Email Address<span style="color:red;">*</span></label>
				<input type="email" id="user_email" name="user_email" value="<%= userData.getEmailID() %>" placeholder="Enter valid email address" required>
	
				<label for="phone">Phone Number<span style="color:red;">*</span></label>
				<input type="tel" id="user_phone" name="user_phone" value="<%= userData.getPhone_number() %>" placeholder="Enter 10 digit phone number" maxlength="10" pattern="[1-9]{1}[0-9]{9}" required>
				
				<label for="username">Address<span style="color:red;">*</span></label>
				<input type="text" id="user_address" name="user_address" value="<%= userData.getAddress() %>" placeholder="Enter address" required>
				
				<label for="dob">Date of Birth<span style="color:red;">*</span></label>
				<input type="date" id="user_dob" name="user_dob" value="<%= userData.getDate_of_birth() %>" required>
				
				<label for="gender">Gender<span style="color:red;">*</span></label>
				<select id="gender" name="user_gender" required>
				<%
					List<String> genders = (List<String>) request.getAttribute("genders");
					String selectedValue = userData.getGender();
					for (String option : genders)
					{
						String selected = option.equals(selectedValue) ? "selected" : "";
				%>
							  <option value="<%= option %>" <%= selected %>><%= option %></option>
				<%
					  }
				%>
				</select>
				
				<label for="year_of_experience">Experience(in Years)<span style="color:red;">*</span></label>
				<input type="number" id="year_of_experience" name="user_year_of_experience" value="<%= userData.getYear_of_experience() %>" required>
				
				<input type="hidden" id="main_record_id" name="main_record_id" value="<%= userData.getId() %>">
				<input type="hidden" id="info_record_id" name="info_record_id" value="<%= userData.getInfo_record_id() %>">
				
				<button type="submit">Update</button>
			</form>
		</div>
	</div>
    
    <script type="text/javascript">
	    // for birth date to set current date as max date
	    document.addEventListener("DOMContentLoaded", function() {
	        var dateInput = document.getElementById("user_dob");
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
			document.getElementById("user_dob").setAttribute("max", today);
		}
    </script>    
</body>
</html>