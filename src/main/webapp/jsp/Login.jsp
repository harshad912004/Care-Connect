<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List"%>

<!DOCTYPE html>
<html lang="eng">
	<head>
		<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Care Connect | Login Page</title>
		<link rel="stylesheet" href="css/navigation_layout.css">
	</head>
	<body>
		<!-- Navigation bar -->
		<nav class="navigation_bar">
	        <div class="navigation_buttons">
	            <img class="navigation_image" src="images/Logo1.png" alt="Care Connect Logo">
	            <ul class="ul_navigation_buttons">
	                <li class="li_navigation_buttons">
						<a href="index.html" class="a_navigation_buttons">HOME</a></li>
	            </ul>
	        </div>
	    </nav>
	    
	    <!--login message for users-->
	    <h1 class="h1_welcome_message">Welcome, Please login to proceed!</h1>
	    
	    <div class="login_form">
	    	<form action="LoginServlet?action=login" method="post" class="main_login_form">
	    		<br><%--Retrive & display error messages (if any) --%>
	    		<% 	List<String> errors = (List<String>) request.getAttribute("errors");	%>
	    		<%	if(errors != null && !errors.isEmpty())
	    			{
	    		%>
	    				<ul class="error-messages">
	    		<%		
	    				for(String error : errors)
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
				  		<li style="color: green;"><%= success_msg %></li>
				<%
					}
				%>
				<br>
				
				<label class="label_login_form" for="email">Your Email:</label>
	            <input class="input_login_form" type="email" id="email" name="email" required>
	
	            <label class="label_login_form" for="password">Your Password:</label>
	            <input class="input_login_form" type="password" id="password" name="password" style="margin-bottom: 15px;" required>
	
	            <button class="button_login_form" type="submit">Login</button>
	            
	            <label class="register" for="text" style="margin-bottom: 5px;">
	            	Don't have an account? <a href="LoginServlet?action=patient_register">CLICK</a> here to register.
	            </label>
	    	</form>
	    </div>
	</body>
</html>