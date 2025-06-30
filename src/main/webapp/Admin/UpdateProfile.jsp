<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, ServletCode.DataClasses.LoggedInUserData"%>
<%@ include file="AdminHeader.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8" />
	<title>Update Profile | Admin Panel</title>
	<script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-blue-50 font-sans text-gray-800">

	<section class="flex justify-center py-12 px-4">
		<div class="bg-white p-8 rounded-xl shadow-md w-full max-w-lg border border-blue-300">
	 		<h2 class="text-2xl font-bold text-blue-700 text-center mb-6">Update Profile</h2>
	
			<!-- Optional Message -->
			<% String message = (String) request.getAttribute("message"); 
				if (message != null) { %>
				<div class="mb-4 px-4 py-2 text-sm text-green-700 bg-green-100 border border-green-300 rounded">
					<%= message %>
				</div>
			<% } %>
	
			<form action="AdminServlet?action=save_profile" method="post" class="space-y-6">
				<% LoggedInUserData userData = (LoggedInUserData) request.getAttribute("user_data"); %>
				
				<!-- Full Name -->
				<div>
					<label for="username" class="block mb-1 font-medium text-gray-700">Full Name</label>
				  	<input type="text" id="user_name" name="user_name" value="<%= userData.getName() %>" placeholder="Enter full name" pattern="[a-zA-Z ]+" style="text-transform: capitalize;" required class="w-full px-4 py-2 border border-blue-200 rounded-lg focus:ring-2 focus:ring-blue-400 shadow-sm" />
				</div>
	
		        <!-- Email -->
		        <div>
		          	<label for="email" class="block mb-1 font-medium text-gray-700">Email</label>
		          	<input type="email" id="user_email" name="user_email" value="<%= userData.getEmailID() %>" placeholder="Enter valid email address" required class="w-full px-4 py-2 border border-blue-200 rounded-lg focus:ring-2 focus:ring-blue-400 shadow-sm" />
		        </div>
		
		        <!-- Phone -->
		        <div>
		          	<label for="phone" class="block mb-1 font-medium text-gray-700">Phone Number</label>
		          	<input type="text" id="user_phone" name="user_phone" value="<%= userData.getPhone_number() %>" placeholder="Enter 10 digit phone number" maxlength="10" pattern="[1-9]{1}[0-9]{9}" required class="w-full px-4 py-2 border border-blue-200 rounded-lg focus:ring-2 focus:ring-blue-400 shadow-sm" />
		        </div>
		        
		        <input type="hidden" id="user_id" name="user_id" value="<%= userData.getId() %>">
		        
		        <!-- Submit -->
		        <div>
		          	<button type="submit" class="w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 transition font-semibold">
		            	Update Profile
		          	</button>
		        </div>
			</form>
		</div>
	</section>

</body>

</html>
