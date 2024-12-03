<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, ServletCode.DataClasses.DepartmentData"%>

<!DOCTYPE html>
<html lang="en">
<body>
    <%@ include file="AdminHeader.jsp" %><br>

	<div class="main_container">
		<div class="form-container">
			<form action="AddNewDoctorServlet" method="post" class="form-form">
				<h2>Add New Doctor</h2>
				
				<label for="username">Doctor Name<span style="color:red;">*</span></label>
				<input type="text" id="doctor_name" name="doctor_name" placeholder="Enter full name" pattern="[a-zA-Z ]+" style="text-transform: capitalize;" required>
	
				<label for="email">Email Address<span style="color:red;">*</span></label>
				<input type="email" id="email" name="email" placeholder="Enter valid email address" required>
	
				<label for="phone">Phone Number<span style="color:red;">*</span></label>
				<input type="tel" id="phone" name="phone" placeholder="Enter 10 digit phone number" maxlength="10" pattern="[1-9]{1}[0-9]{9}" required>
				
				<label for="new_password">Password<span style="color:red;">*</span></label>
				<input type="password" id="password" name="password" placeholder="Enter password" required>
	
				<label for="specialization">Specialization<span style="color:red;">*</span></label>
				<select id='department' name='department' required>
				<%		List<DepartmentData> dataList = (List<DepartmentData>) request.getAttribute("dataList");
						if(dataList != null)
						{
							for(DepartmentData data : dataList)
							{
				%>
								<option value=<%= data.getId() %>><%= data.getName() %></option>
				<% 
							}
						}
				%>
				</select>
				
				<label for="degree">Degree<span style="color:red;">*</span></label>
				<select id="degree" name="degree" required>
					<option value="MBBS">MBBS</option>
					<option value="BDS">BDS</option>
					<option value="MD">MD</option>
					<option value="BAMS">BAMS</option>
					<option value="ABIM">ABIM</option>
					<option value="ABPN">ABPN</option>
					<option value="ABP">ABP</option>
					<option value="DM">DM</option>
					<option value="MS">MS</option>
				</select>
	
				<label for="address">Address<span style="color:red;">*</span></label>
				<input type="text" id="address" name="address" placeholder="Enter address" required>
	
				<label for="dob">Date of Birth<span style="color:red;">*</span></label>
				<input type="date" id="dob" name="dob" required>
				
				<label for="gender">Gender<span style="color:red;">*</span></label>
				<select id="gender" name="gender" required>
					<option value="Male">Male</option>
					<option value="Female">Female</option>
				</select>
				
				<label for="year_of_experience">Experience(in Years)<span style="color:red;">*</span></label>
				<input type="number" id="year_of_experience" name="year_of_experience" required>
	
				<button type="submit">Add</button>
			</form>
		</div>
	</div>
	    
    <script type="text/javascript">
    
		    // for birth date to set current date as max date
		    document.addEventListener("DOMContentLoaded", function() {
		        var dateInput = document.getElementById("dob");
		        
		        dateInput.addEventListener('focus', function() {
		            // Call the fetchDoctors function with the selected department value
		            setCurrentDateTimeAsMin(this.value);
		        });
		        dateInput.addEventListener('keyup', function() {
		            // Call the fetchDoctors function with the selected department value
		            setCurrentDateTimeAsMin(this.value);
		        });
		        dateInput.addEventListener('click', function() {
		            // Call the fetchDoctors function with the selected department value
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