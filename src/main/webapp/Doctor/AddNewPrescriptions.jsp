<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, ServletCode.DataClasses.PatientListData"%>

<!DOCTYPE html>
<html lang="en">
<body>
    <%@ include file="DoctorHeader.jsp" %>    

	<div class="main_container">
		<div class="form-container">
			<form action="AddNewPrescriptionServlet" method="post" class="form-form">
				<h2>Add New Prescription</h2>
	
				<label for="date_of_prescription">Date<span style="color:red;">*</span></label>
				<input type="date" id="date_of_prescription" name="date_of_prescription" required>
				
				<label for="patient_name">Patient Name<span style="color:red;">*</span></label>
				<select id='patient_name' name='patient_name' required>
				<%
					List<PatientListData> dataList = (List<PatientListData>) request.getAttribute("dataList");
					if(dataList != null)
					{
						for(PatientListData data : dataList)
						{
				%>
							<option value=<%= data.getId() %>><%= data.getName() %></option>
				<% 
						}
					}
				%>
				</select>
				
				<label for="weight_of_patient">Weight(in Kg)<span style="color:red;">*</span></label>
				<input type="number" id="weight_of_patient" name="weight_of_patient" required>
				
				<label for="diagnosis">Diagnosis<span style="color:red;">*</span></label>
				<textarea id="diagnosis" name="diagnosis" placeholder="Enter Diagnosis" rows="3" required></textarea>
							
				<label for="medicines">Medicines<span style="color:red;">*</span></label>
				<table border='1' style="margin: 0 auto;">
					<tr id="headerRow">
						<th>Type</th>
						<th>Medicine</th>
						<th>Measure/Tablet</th>
						<th>Dosage</th>
						<th>Instruction</th>
						<th>Days</th>
						<th>Action</th>
					</tr>
					<tbody id="tableBody">
						<tr>
							<td width="15%">
								<select name="type[0]" class="form-control medtype" required>
									<option value="Tab">Tab</option>
									<option value="Cap">Cap</option>
									<option value="Syrup">Syrup</option>
								</select>
							</td>
							<td width="20%">
								<input type="text" name="medicine[0]" style="text-transform: capitalize;" placeholder="Medicine Name" value="" class="medname" required /></td>                          
							<td width="15%">
								<input type="text" name="measure[0]" placeholder="Eg. 1 cap/1 tab/30 ml" value="" class="measure" required/>
							   </td>
							   <td width="10%">
								<div>
									<div>
										<input type="checkbox" id="Morning" name="dosage[0][]" value="Morning" checked>
										<label for="Morning">M</label>
									</div>
									
									<div>
										<input type="checkbox" id="Afternoon" name="dosage[0][]" value="Afternoon">
										<label for="Afternoon">A</label>
									</div>
									
									<div>
										<input type="checkbox" id="Evening" name="dosage[0][]" value="Evening">
										<label for="Evening">E</label>
									</div>
								 </div>
							 </td>
							 <td width="20%">
								 <select name="instruction[0]" class="instruction" required>
									 <option value="After Meal">After Meal</option>
									 <option value="Before Meal">Before Meal</option>
								 </select>
							 </td>
							 <td width="8%">
								 <input type="number" name="days[0]" placeholder="Days" value="" class="days" required />
							 </td>
							 <td width="12%">
								 <button id="addButton">Add New</button>
							 </td>
						 </tr>
					</tbody>
				</table>    
				
				<label for="tests">Preferred Tests</label>
				<textarea id="tests" name="tests" placeholder="Enter tests" rows="3"></textarea>
				
				<label for="payment_type">Payment Type<span style="color:red;">*</span></label>
				<select id="payment_type" name="payment_type" required>
					<option value="Online Payment">Online Payment</option>
					<option value="Cash">Cash</option>
				</select>
				
				<label for="fees">Fees(in Rs.)<span style="color:red;">*</span></label>
				<input type="number" id="fees" name="fees" required>
				
				<label for="remarks">Remarks/Advice</label>
				<input type="text" id="remarks" name="remarks" placeholder="Enter remarks/advice">
				
				<label for="follow_up">Follow Up Date</label>
				<input type="date" id="follow_up" name="follow_up">
				
				<button type="submit">Save</button>
			</form>
		</div>
	</div>
    
    <script src="js/table_row_script.js"></script> 

    <script type="text/javascript">
    
		// for birth date to set current date as max date
		document.addEventListener("DOMContentLoaded", function() {
			var dateInput = document.getElementById("date_of_prescription");
			var dateInput1 = document.getElementById("follow_up");
		    
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
		    dateInput1.addEventListener('focus', function() {
		        // Call the fetchDoctors function with the selected department value
		        setCurrentDateTimeAsMin1(this.value);
		    });
		    dateInput1.addEventListener('keyup', function() {
		        // Call the fetchDoctors function with the selected department value
		        setCurrentDateTimeAsMin1(this.value);
		    });
		    dateInput1.addEventListener('click', function() {
		        // Call the fetchDoctors function with the selected department value
		        setCurrentDateTimeAsMin1(this.value);
		    });
		});

		// Function to set current date and time as max
		function setCurrentDateTimeAsMin() {			   
			const today = new Date().toISOString().slice(0, 10); // Get YYYY-MM-DD
			document.getElementById("date_of_prescription").setAttribute("max", today);
		}			 
		function setCurrentDateTimeAsMin1() {			   
			const today = new Date().toISOString().slice(0, 10); // Get YYYY-MM-DD
			document.getElementById("follow_up").setAttribute("min", today);
		}
    </script>
</body>
</html>