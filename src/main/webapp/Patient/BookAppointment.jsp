<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  import="java.util.List, java.util.Date, ServletCode.DataClasses.DepartmentData, ServletCode.FetchDoctorsBasedOnDepartments" %>

<!DOCTYPE html>
<html lang="en">
<body>
    <%@ include file="PatientHeader.jsp" %>    
    
    <!-- book appointment form -->
    <div class="form-container" id="form_container">    
        <form action="BookAppointmentFormSubmitServlet" method="post" class="form-form">
        	<label for="department">Select Department<span style="color:red;">*</span></label>
        	<select id='department' name='department' required>
	        	<option value="0">Select Department</option>
	        	<% 
		            List<DepartmentData> dataList = (List<DepartmentData>) request.getAttribute("dataList");
		            if(dataList != null)
		            {
		                for(DepartmentData data : dataList)
		                {
	            %>
	                		<option value=<%= data.getId() %> style="text-transform: capitalize;">
	                			<%= data.getName() %>
	                		</option>
	            <% 
		                }
		            }
	            %>
        	</select><br>
        	
        	<div id="doctorsList"></div>
        	
        	<br>
        	
            <label for="date">Appointment Date & Time<span style="color:red;">*</span></label>
            
            <input type="datetime-local" id="date" name="date" required>
            <button type="submit">Book Appointment</button>
        </form>        
    </div>
    
    <script type="text/javascript">    
		    document.addEventListener("DOMContentLoaded", function()
		    {
		        // Find the department select element
		        var departmentSelect = document.getElementById('department');
		
		        // Add an event listener for changes to the select element
		        departmentSelect.addEventListener('change', function()
		        {
		            // Call the fetchDoctors function with the selected department value
		            fetchDoctors(this.value);
		        });
		    });    
		    function fetchDoctors()
		    {	
		    	var departmentValue = document.getElementById('department').value;
		    	console.log(departmentValue);
		    	fetch('FetchDoctorsBasedOnDepartments?dept_id='+departmentValue).then(response => response.text()).then(data =>
		        {
		            document.getElementById('doctorsList').innerHTML = data;
		        })
		        .catch(error => console.error('Error fetching department data:', error));
		    }		    
		    
		    // for appointment date time to set current date as min date time
		    document.addEventListener("DOMContentLoaded", function()
		    {
		        var dateInput = document.getElementById("date");
		        
		        dateInput.addEventListener('focus', function()
		        {
		            // Call the fetchDoctors function with the selected department value
		            setCurrentDateTimeAsMin(this.value);
		        });
		        dateInput.addEventListener('keyup', function()
		        {
		            // Call the fetchDoctors function with the selected department value
		            setCurrentDateTimeAsMin(this.value);
		        });
		        dateInput.addEventListener('click', function()
		        {
		            // Call the fetchDoctors function with the selected department value
		            setCurrentDateTimeAsMin(this.value);
		        });
		    });

			 // Function to set current date and time as min
			 function setCurrentDateTimeAsMin()
			 {
				const today = new Date().toISOString().slice(0, 16); // Get YYYY-MM-DD
				document.getElementById("date").setAttribute("min", today);
			 }			 
    </script>
    
</body>
</html>