package com.yash.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/regForm")
public class Register extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter out = resp.getWriter();

		String myname = req.getParameter("name");
		String myemail = req.getParameter("email");
		String mypassword = req.getParameter("password");
		String mygender = req.getParameter("gender");
		String mycity = req.getParameter("city");

		System.out.println(myname + myemail + mypassword + mygender + mycity);

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/register_login","root","root");

			PreparedStatement ps = con.prepareStatement("insert into register values(?,?,?,?,?)");
			ps.setString(1, myname);
			ps.setString(2, myemail);
			ps.setString(3, mypassword);
			ps.setString(4, mygender);
			ps.setString(5, mycity);

			int count = ps.executeUpdate();
			if (count > 0) {
				resp.setContentType("text/html");
				out.print("User Regiserted Successfully");
				RequestDispatcher rDispatcher = req.getRequestDispatcher("/regiser.jsp");
				rDispatcher.include(req, resp);

			} else {
				resp.setContentType("text/html");
				out.print("User Not Regiserted Successfully");
				RequestDispatcher rDispatcher = req.getRequestDispatcher("/regiser.jsp");
				rDispatcher.include(req, resp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			resp.setContentType("text/html");
			out.print("error is there");
			RequestDispatcher rDispatcher = req.getRequestDispatcher("/regiser.jsp");
			rDispatcher.include(req, resp);
		}

	}

}
