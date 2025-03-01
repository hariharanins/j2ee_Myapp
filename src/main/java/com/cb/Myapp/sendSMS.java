package com.cb.Myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
//import com.mysql.cj.protocol.Message;
public class sendSMS extends HttpServlet {
	 private static final String ACCOUNT_SID = "ACf8a46b24b229ad5b51295b723167c4c9"; 
	 private static final String AUTH_TOKEN = "731ce240160df9a7b79fcaa66eb95243"; 
	 private static final String TWILIO_PHONE_NUMBER = "+16012283181"; 
	 private static final String CUSTOMER_PHONE_NUMBER = "+917010242425";
	 protected void doGet(HttpServletRequest req , HttpServletResponse resp) {
		// PrintWriter pen = resp.getWriter();
		 String name = (String) req.getAttribute("Username");
		 String msgtext = "Element has been inserted by " + name;
		 resp.setContentType("text/html");
		 try {
			 Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
			 Message message = Message.creator(
	                    new PhoneNumber(CUSTOMER_PHONE_NUMBER),
	                    new PhoneNumber(TWILIO_PHONE_NUMBER), 
	                    msgtext)
	                    .create();
			 resp.getWriter().write("<h2>Order Placed Successfully!</h2>");
	            resp.getWriter().write("<p>SMS Sent to " + CUSTOMER_PHONE_NUMBER + ": \"" + msgtext + "\"</p>");
	            resp.getWriter().write("<a href='http://192.168.1.4:8082/Myapp/Welcome.html'>Back to Home</a>");
		 }
		 catch(Exception e) {
			 try {
				resp.getWriter().write("<h2>Error Sending SMS!</h2><p>" + e.getMessage() + "</p>");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		 }
	 }
	}


