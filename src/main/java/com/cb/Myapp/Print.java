package com.cb.Myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Print extends HttpServlet {
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
			String s = "select * from student1";
			Statement stmt = con.createStatement();
			rst = stmt.executeQuery(s);
			pen.println("<table border = 4> \r\n"
					+ "<tr>\r\n"
					+ "<th>id</th>\r\n"
					+ "<th>name</th>\r\n"
					+ "<th>un</th>\r\n"
					+ "<th>pwd</th>\r\n"
					+ "<th>10th mark</th>\r\n"
					+ "<th>12th mark</th>\r\n"
					+ "<th>cgpa</th>\r\n"
					+ "</tr>\r\n"
				);
			while(rst.next()) {
			int id = rst.getInt(1);
			String name = rst.getString(2);
			String un = rst.getString(3);
			String pwd = rst.getString(4);
			String tenth = rst.getString(5);
			String twelfth =rst.getString(6);
			String cgpa = rst.getString(7);
			pen.println("<tr>\r\n"
					+ "<td>" + id + "</td>\r\n"
					+ "<td>" + name + "</td>\r\n"
					+ "<td>" + un + "</td>\r\n"
					+ "<td>" + pwd + "</td>\r\n"
					+ "<td>" + tenth + "</td>\r\n"
					+ "<td>" + twelfth + "</td>\r\n"
					+ "<td>" + cgpa + "</td>\r\n"
					+ "</tr>");
			}
			pen.println("</table>");
			pen.println("<h1>Company Details :</h1>");
			RequestDispatcher rd = req.getRequestDispatcher("/Drive");
			rd.include(req, resp);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
