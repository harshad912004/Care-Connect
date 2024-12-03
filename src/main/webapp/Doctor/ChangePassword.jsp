<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<body>

    <%@ include file="DoctorHeader.jsp" %><br>

	<div class="main_container">
		<div class="form-container" id="form_container">
	    	<form action="DoctorServlet?action=save_new_password" method="post" class="form-form" id="passwordForm">
	            <label for="old_password">Old Password<span style="color:red;">*</span></label>
	            <input type="password" id="old_password" name="old_password" required><br>
	            
	            <label for="new_password">New Password<span style="color:red;">*</span></label>
	            <input type="password" id="new_password" name="new_password" required><br>
	            
	            <label for="cnew_password">Confirm New Password<span style="color:red;">*</span></label>
	            <input type="password" id="cnew_password" name="cnew_password" required onkeyup="checkPasswords()">
  				<p id="errorMessage" style="color: red;"></p><br>
	            
	            <button type="submit">Save</button>
	        </form>
	    </div>	  
	</div>

		<script>
		    const passwordForm = document.getElementById("passwordForm");
		    const newPassword = document.getElementById("new_password");
		    const confirmPassword = document.getElementById("cnew_password");
		    const errorMessage = document.getElementById("errorMessage");
		    const submitButton = document.querySelector("button[type='submit']"); // Get submit button
		
		    function checkPasswords() {
				if (newPassword.value !== confirmPassword.value) {
					errorMessage.innerText = "Password do not match to New Password";
					submitButton.disabled = true;  // Disable submit button if mismatch
				}
				else {
					errorMessage.innerText = "";
					submitButton.disabled = false; // Enable submit button if match
				}
			}
		    
		    confirmPassword.addEventListener("keyup", checkPasswords);  // Call check on keyup
		</script>
  
</body>
</html>