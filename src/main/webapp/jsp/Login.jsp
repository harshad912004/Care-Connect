<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>Care Connect | Login</title>
	<script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-blue-50 font-sans text-gray-800">

	<!-- Top Contact Info -->
	<div class="bg-blue-100 text-right py-2 px-3 text-sm font-semibold">
		careconnect247@gmail.com | +91 0253-987654
	</div>

	<!-- Navbar -->
	<nav class="bg-white shadow-md sticky top-0 z-10">
		<div class="container mx-auto py-2 flex justify-between items-center">
			<img src="images/Logo1.png" alt="Care Connect Logo" class="h-24" />
			<ul class="flex space-x-6 text-blue-700 font-semibold">
				<li><a href="index.html" class="hover:text-blue-500 text-2xl">Home</a></li>
				<li><a href="about.html" class="hover:text-blue-500 text-2xl">About Us</a></li>
			</ul>
		</div>
	</nav>

	<!-- Login Form Section -->
	<section class="flex items-center justify-center py-20">
		<div class="bg-white rounded-xl shadow-xl p-8 w-full max-w-md">
			<h2 class="text-3xl font-bold text-blue-700 text-center mb-6">User Login</h2>

			<!-- Login form -->
			<form action="LoginServlet?action=login" method="post" class="space-y-6">
				<!-- Email -->
				<div>
					<label for="email" class="block mb-1 font-medium text-gray-700">Email</label>
					<input type="email" id="email" name="email" required class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400 shadow-sm" />
				</div>

				<!-- Password -->
				<div>
					<label for="password" class="block mb-1 font-medium text-gray-700">Password</label>
					<input type="password" id="password" name="password" required class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400 shadow-sm" />
				</div>

				<!-- Submit -->
				<div>
					<button type="submit" class="w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 transition font-semibold">Login</button>
				</div>
			</form>

			<!-- Optional: Error message (if needed) -->
			<% String error=request.getParameter("error"); if (error !=null) { %>
				<p class="mt-4 text-sm text-red-500 text-center">
					<%= error %>
				</p>
				<% } %>

				<!-- Optional: Sign-up link -->
				<p class="mt-6 text-sm text-center text-gray-600">
					Don't have an account?
					<a href="jsp/PatientRegister.jsp" class="text-blue-600 hover:underline font-semibold">Sign up here</a>
				</p>
		</div>
	</section>
	
</body>

</html>
