package com.cb.Myapp;

import java.io.IOException;
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

public class Login extends HttpServlet {
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
		String UserName = req.getParameter("UserName");
		String Password = req.getParameter("Password");
		try {
		String query ="select * from student1 where un = ? and pwd = ?";
		pst = con.prepareStatement(query);
		pst.setString(1, UserName);
		pst.setString(2, Password);
		rst = pst.executeQuery();
		if(rst.next()== true) {
		RequestDispatcher rd = req.getRequestDispatcher("/Print");
		rd.forward(req, resp);
		}
		else {
	    RequestDispatcher dr = req.getRequestDispatcher("/incorrect.html");
	    dr.forward(req, resp);
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
