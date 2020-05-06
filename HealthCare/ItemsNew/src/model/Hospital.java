package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Hospital {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalsnew?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertHospital(String regid, String name, String address, String contact, String email, String username, String password)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			// create a prepared statement    
			String query = " insert into hospital(`hospitalID`,`hospitalRegId`,`hospitalName`,`hospitalAddress`,`contactNo`,`email`,`userName`,`password`)" + " values (?, ?, ?, ?, ?, ?, ?, ?)"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, 0);    
			preparedStmt.setString(2, regid);    
			preparedStmt.setString(3, name);    
			preparedStmt.setString(4, address);   
			preparedStmt.setString(5, contact);
			preparedStmt.setString(6, email);    
			preparedStmt.setString(7, username);   
			preparedStmt.setString(8, password);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newHospitals = readHospitals(); 
			output =  "{\"status\":\"success\", \"data\": \"" +        
							newHospitals + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the hospital.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	} 
	
	public String readHospitals()  
	{   
		String output = ""; 
	
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border=\'1\'><tr><th>Registration ID</th><th>Hospital Name</th><th>Address</th><th>Contact Number</th><th>Email Address</th><th>Username</th><th>Password</th><th>Update</th><th>Remove</th></tr>"; 
	 
			String query = "select * from hospital";    
			Statement stmt = con.createStatement();    
			ResultSet rs = stmt.executeQuery(query); 
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String hospitalID = Integer.toString(rs.getInt("hospitalID"));     
				String hospitalRegId = rs.getString("hospitalRegId");     
				String hospitalName = rs.getString("hospitalName");     
				String hospitalAddress = rs.getString("hospitalAddress");   
				String contactNo = rs.getString("contactNo");     
				String email = rs.getString("email");     
				String userName = rs.getString("userName"); 
				String password = rs.getString("password"); 
	 
				// Add into the html table 
				output += "<tr><td><input id=\'hidHospitalIDUpdate\' name=\'hidHospitalIDUpdate\' type=\'hidden\' value=\'" + hospitalID + "'>" 
						+ hospitalRegId + "</td>";    
				output += "<td>" + hospitalName + "</td>";     
				output += "<td>" + hospitalAddress + "</td>";     
				output += "<td>" + contactNo + "</td>"; 
				output += "<td>" + email + "</td>";     
				output += "<td>" + userName + "</td>";     
				output += "<td>" + password + "</td>"; 
				
				// buttons     
//				output += "<td><input name=\'btnUpdate\' type=\'button\' value=\'Update\' class=\' btnUpdate btn btn-secondary\'></td>"
//						//+ "<td><form method=\"post\" action=\"items.jsp\">      "
//						+ "<input name=\'btnRemove\' type=\'submit\' value=\'Remove\' class=\'btn btn-danger\'> "
//						+ "<input name=\"hidItemIDDelete\" type=\"hidden\" value=\"" + itemID + "\">" + "</form></td></tr>"; 
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
							+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-hospitalid='" + hospitalID + "'>" + "</td></tr>"; 
			} 
	 
			con.close(); 
	 
			// Complete the html table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the hospitals.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	 
	
	public String updateHospital(String ID, String regid, String name, String address, String contact, String email, String username, String password)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			String query = "UPDATE hospital SET hospitalRegId=?,hospitalName=?,hospitalAddress=?,contactNo=?,email=?,userName=?,password=? WHERE hospitalID=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, regid);    
			preparedStmt.setString(2, name);    
			preparedStmt.setString(3, address);   
			preparedStmt.setString(4, contact);
			preparedStmt.setString(5, email);    
			preparedStmt.setString(6, username);   
			preparedStmt.setString(7, password);   
			preparedStmt.setInt(8, Integer.parseInt(ID)); 
	 
			// execute the statement    
			preparedStmt.execute();     
			con.close(); 
	 
			String newHospitals = readHospitals();    
			output = "{\"status\":\"success\", \"data\": \"" +        
					newHospitals + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the hospital.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	} 
	
	public String deleteHospital(String hospitalID)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for deleting."; } 
	 
			// create a prepared statement    
			String query = "delete from hospital where hospitalID=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(hospitalID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newHospitals = readHospitals();    
			output = "{\"status\":\"success\", \"data\": \"" +       
					newHospitals + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the hospital.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	 
	 
}
