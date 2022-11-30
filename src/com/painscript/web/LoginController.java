package com.painscript.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.painscript.dao.UserDao;



@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao loginDao;

	public void init() {
		loginDao = new UserDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("login-success.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			authenticate(request, response);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	private void authenticate(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserDao user = new UserDao();
		
		String emailId = request.getParameter("emailId");
		System.out.println("hello");

		if (user.validateEmail(emailId)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login-success");
			dispatcher.forward(request, response);
			System.out.println("HI");
			response.sendRedirect("login-success.jsp");
		}else {
			throw new Exception("Login not successful..");
		}
	}
}
