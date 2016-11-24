<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>WebChat</title>
		<!-- Description, Keywords and Author -->
		<meta name="description" content="Your description">
		<meta name="keywords" content="Your,Keywords">
		<meta name="author" content="ResponsiveWebInc">
		
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		
		<!-- Styles -->
		<!-- Bootstrap CSS -->
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<!-- Font awesome CSS -->
		<link href="css/font-awesome.min.css" rel="stylesheet">		
		<!-- Custom CSS -->
		<link href="css/style-40.css" rel="stylesheet">
		
		<!-- Favicon -->
		<link rel="shortcut icon" href="#">
	</head>
	<!-- Body -->
	<body>

	
			<br />
			
			<br />		
	
			<!-- Form -->
			<form method="POST" action="SignUpServlet">
				<!-- Ui-40 -->
				<div class="ui-40">
					<div class="ui-head bg-lblue">
						<!-- Heading -->
						<h2>Sign Up</h2>
					</div>
					<!-- Ui-block -->
					<div class="ui-block bg-white">
						<!-- Icon -->
						<div class="ui-icon">
							<i class="fa fa-envelope lblue"></i>
						</div>
						<!-- Input box -->
						<div class="ui-input">
							<input type="email" name="email" class="form-control" placeholder="Email"
							<%if (request.getParameter("email") != null){ %>
								value="<%=request.getParameter("email") %>"
							<%} %>							
							required>
						</div>
					</div>
					<div class="clearfix"></div>
					<div class="ui-block bg-white">
						<div class="ui-icon">
							<i class="fa fa-user lblue"></i>
						</div>
						<div class="ui-input">
							<input type="text" name="name" class="form-control" placeholder="Your Name" 
							<%if (request.getParameter("name") != null){ %>
								value="<%=request.getParameter("name") %>"
							<%} %>
							required>
						</div>
					</div>
					<div class="clearfix"></div>
					<div class="ui-block bg-white">
						<div class="ui-icon">
							<i class="fa fa-unlock-alt lblue"></i>
						</div>
						<div class="ui-input">
							<input type="password" name="password" class="form-control" placeholder="Your Password" required>
						</div>
					</div>
					<div class="clearfix"></div>
					<div class="ui-block bg-white">
						<div class="ui-icon">
							<i class="fa fa-unlock-alt lblue"></i>
						</div>
						<div class="ui-input">
							<input type="password" name="passwordconf" class="form-control" placeholder="Confirm Password" required>
						</div>
					</div>
					<div class="clearfix"></div>

					<!-- Footer -->
					<div class="ui-foot bg-lblue">
						<!-- Buttons -->
						<button type="submit" class="ui-button">Sign Up</button> 
					</div>
					<div id="signin">
						<p style="font-size:16px;text-align:center;"><a href="http://env-7443164.j.dnr.kz/WebChat/index.jsp">Already have an account? Sign in</a></p>
					</div>
					<%if (request.getAttribute("errors") != null){ %>
					<div id="errors">
						<%for (String s: (List<String>) request.getAttribute("errors")){ %>
							<p style="color:red;font-size:16px;text-align:center;"><%=s %></p>
						<%} %>
					</div>
					<%} %>
				</div>
			</form>
			
		
		
		<!-- Bootstrap javascript links --->
		<!-- Jquery file -->
		<script src="js/jquery-2.1.1.min.js"></script>
		<!-- Bottstrap min js file -->
		<script src="js/bootstrap.min.js"></script>
		<!-- placeholder file -->
		<script src="js/placeholders.js"></script>
		<!-- Html file -->
		<script src="js/html5shiv.js"></script>
		<!-- Respond file-->
		<script src="js/respond.min.js"></script>
	
	</body>
</html>