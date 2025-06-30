<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Care Connect | Patient Registration</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-blue-50 font-sans text-gray-800">

  <!-- Top Contact Info -->
  <div class="bg-blue-100 text-right py-2 px-3 text-sm font-semibold">
    careconnect247@gmail.com | +91 0253-987654
  </div>

  <!-- Navbar -->
  <nav class="bg-white shadow-md sticky top-0 z-10">
    <div class="container mx-auto py-2 px-4 flex justify-between items-center">
      <img src="../images/Logo1.png" alt="Care Connect Logo" class="h-20" />
      <ul class="flex space-x-6 text-blue-700 font-semibold text-xl">
        <li><a href="../index.html" class="hover:text-blue-500">Home</a></li>
        <li><a href="../about.html" class="hover:text-blue-500">About</a></li>
      </ul>
    </div>
  </nav>

  <!-- Registration Form -->
  <section class="flex justify-center py-12 px-4">
    <div class="bg-white p-8 rounded-xl shadow-lg w-full max-w-2xl">
      <h2 class="text-3xl font-bold text-blue-700 text-center mb-6">Patient Registration</h2>

      <form action="RegisterServlet" method="post" class="space-y-6">
        <!-- Name -->
        <div>
          <label for="name" class="block mb-1 font-medium text-gray-700">Full Name</label>
          <input type="text" id="name" name="name" required
            class="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-400 shadow-sm" />
        </div>

        <!-- Age -->
        <div>
          <label for="age" class="block mb-1 font-medium text-gray-700">Age</label>
          <input type="number" id="age" name="age" required min="0"
            class="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-400 shadow-sm" />
        </div>

        <!-- Gender -->
        <div>
          <label for="gender" class="block mb-1 font-medium text-gray-700">Gender</label>
          <select id="gender" name="gender" required
            class="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-400 shadow-sm">
            <option value="" disabled selected>Select Gender</option>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
            <option value="Other">Other</option>
          </select>
        </div>

        <!-- Email -->
        <div>
          <label for="email" class="block mb-1 font-medium text-gray-700">Email</label>
          <input type="email" id="email" name="email" required
            class="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-400 shadow-sm" />
        </div>

        <!-- Password -->
        <div>
          <label for="password" class="block mb-1 font-medium text-gray-700">Password</label>
          <input type="password" id="password" name="password" required
            class="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-400 shadow-sm" />
        </div>

        <!-- Confirm Password -->
        <div>
          <label for="confirmPassword" class="block mb-1 font-medium text-gray-700">Confirm Password</label>
          <input type="password" id="confirmPassword" name="confirmPassword" required
            class="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-400 shadow-sm" />
        </div>

        <!-- Submit -->
        <div>
          <button type="submit"
            class="w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 transition font-semibold">
            Register
          </button>
        </div>
      </form>

      <!-- Optional: Login redirect -->
      <p class="mt-6 text-sm text-center text-gray-600">
        Already have an account?
        <a href="../LoginServlet?action=loginForm" class="text-blue-600 hover:underline font-semibold">Login here</a>
      </p>
    </div>
  </section>

</body>

</html>
