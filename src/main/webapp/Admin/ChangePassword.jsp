<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="AdminHeader.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8" />
	<title>Change Password | Care Connect Admin</title>
	<script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-blue-50 font-sans text-gray-800">

	<section class="flex justify-center py-12 px-4">
		<div class="bg-white p-8 rounded-xl shadow-md w-full max-w-lg border border-blue-300">
			<h2 class="text-2xl font-bold text-blue-700 text-center mb-6">Change Password</h2>

			<form action="AdminServlet?action=save_new_password" method="post" class="space-y-6" id="passwordForm">
		        <!-- Current Password -->
		        <div>
					<label for="currentPassword" class="block mb-1 font-medium text-gray-700">Current Password</label>
		          	<input type="password" id="currentPassword" name="currentPassword" required class="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-400 shadow-sm" />
		        </div>
		
		        <!-- New Password -->
		        <div>
					<label for="newPassword" class="block mb-1 font-medium text-gray-700">New Password</label>
					<input type="password" id="newPassword" name="newPassword" required class="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-400 shadow-sm" />
		        </div>
		
		        <!-- Confirm New Password -->
		        <div>
					<label for="confirmPassword" class="block mb-1 font-medium text-gray-700">Confirm New Password</label>
					<input type="password" id="confirmPassword" name="confirmPassword" required class="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-400 shadow-sm" />
		        </div>

				<p id="errorMessage" style="color: red;"></p>
				
		        <!-- Submit -->
		        <div>
					<button type="submit" class="w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 transition font-semibold">
						Update Password
					</button>
		        </div>
			</form>
		</div>
	</section>
	
	<script>
		const passwordForm = document.getElementById("passwordForm");
		const newPassword = document.getElementById("newPassword");
		const confirmPassword = document.getElementById("confirmPassword");
		const errorMessage = document.getElementById("errorMessage");
		const submitButton = document.querySelector("button[type='submit']");
	
		function checkPasswords()
		{
			if (newPassword.value !== confirmPassword.value)
			{
				errorMessage.innerText = "Password do not match to New Password";
				submitButton.disabled = true;  // Disable submit button if mismatch
			}
			else
			{
				errorMessage.innerText = "";
				submitButton.disabled = false; // Enable submit button if match
			}
		}
	
		confirmPassword.addEventListener("keyup", checkPasswords);
	</script>

</body>

</html>
