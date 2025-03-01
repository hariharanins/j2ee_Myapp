package com.cb.Myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Drive extends HttpServlet {
	Connection con = null;
	String url = "jdbc:mysql://localhost:3306/j2ee_project1";
	String Username = "root";
	String Password ="Hari@14122003";
	PreparedStatement pst = null;
	ResultSet rst = null;
	public void init() throws ServletException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,Username,Password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doGet(HttpServletRequest req , HttpServletResponse resp) {
		try {
			PrintWriter pen = resp.getWriter();
			String UserName = req.getParameter("UserName");
			String Password = req.getParameter("Password");
			String query ="select * from student1 where un = ? and pwd = ?";
			pst=con.prepareStatement(query);
			pst.setString(1, UserName);
			pst.setString(2,Password);
			rst=pst.executeQuery();
			rst.next();
			String s1 = rst.getString(5);
			String s2 = rst.getString(6);
			String s3 = rst.getString(7);
			String s = "SELECT * FROM drive WHERE '10th mark' <= ? AND '12th mark' <= ? AND  cgpa <= ?";
			PreparedStatement ps = con.prepareStatement(s);
			ps.setString(1,s1);
			ps.setString(2,s2);
			ps.setString(3,s3);			
			ResultSet rst1 = ps.executeQuery();
			pen.println("<table border = 4> \r\n"
					+ "<tr>\r\n"
					+ "<th>s_id</th>\r\n"
					+ "<th>company</th>\r\n"
					+ "<th>10th mark</th>\r\n"
					+ "<th>12th mark</th>\r\n"
					+ "<th>cgpa</th>\r\n"
					+ "<th>salary</th>\r\n"
					+ "</tr>\r\n"
				);
		    while(rst1.next()) {
		    	int s_id = rst1.getInt(1);
		    	String company = rst1.getString(2);
		    	String tenth = rst1.getString(3);
		    	String twelfth = rst1.getString(4);
		    	String cgpa = rst1.getString(5);
		    	String salary = rst1.getString(6);
		    	pen.println("<tr>\r\n"
		    			+ "<td>" + s_id + "</td>\r\n"
		    			+ "<td>" + company + "</td>\r\n"
		    			+ "<td>" + tenth + "</td>\r\n"
		    			+ "<td>" + twelfth + "</td>\r\n"
		    			+ "<td>" + cgpa + "</td>\r\n"
		    			+ "<td>" + salary + "</td>\r\n"
		    			+ "</tr>");
		    }
			pen.println("</table>");
			pen.println("<h3>Welcome " + rst.getString(2) + "!!.. The Possible Drive you can attend are listed as your 10th mark is " + rst.getString(5) + ", 12th mark is " + rst.getString(6) + " and cgpa is " + rst.getString(7) +"</h3>");
			req.setAttribute("Username", rst.getString(2));
			RequestDispatcher rd = req.getRequestDispatcher("/sendSMS");
			rd.include(req, resp);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
