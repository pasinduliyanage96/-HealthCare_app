<%@ page import="model.Hospital"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/hospitals.js"></script> 
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1 class="m-3">HOSPITAL MANAGEMENT</h1>
				<form id="formHospital" name="formHospital" method="post" action="hospitals.jsp">  
					Hospital Registration ID
					<input id="hospitalRegId" name="hospitalRegId" type="text" placeholder="Enter Hospital Registration ID" class="form-control form-control-sm">  
					<br> Hospital Name
					<input id="hospitalName" name="hospitalName" type="text" placeholder="Enter Hospital Name" class="form-control form-control-sm">  
					<br> Hospital Address
					<input id="hospitalAddress" name="hospitalAddress" type="text" placeholder="No:   / ,        ,       ." class="form-control form-control-sm">  
					<br> Hospital Contact Number 
					<input id="contactNo" name="contactNo" type="text" placeholder="07xxxxxxxx" class="form-control form-control-sm">  
					<br> Hospital Email Address
					<input id="email" name="email" type="text" placeholder=" xxxxxx@gmail.com" class="form-control form-control-sm">  
					<br> Username
					<input id="userName" name="userName" type="text" class="form-control form-control-sm">  
					<br> Password  
					<input id="password" name="password" type="text" class="form-control form-control-sm">  
					<br>
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					<input type="hidden" id="hidHospitalIDSave" name="hidHospitalIDSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success">
					<%
						out.print(session.getAttribute("statusMsg"));
					%>
				</div>
				<div id="alertError" class="alert alert-danger"></div>
				
				<br>
				<div id="divHospitalsGrid">
					<%
						Hospital hospitalObj = new Hospital();
						out.print(hospitalObj.readHospitals());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>