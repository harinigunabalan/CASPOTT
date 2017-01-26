package com.sap.cloudwave.caspott;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalculatorServlet
 */
@WebServlet("/CalculatorServlet")
public class CalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String n1, n2;   
    Calculator c1;
    int result = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculatorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		n1 = request.getParameter("number1");
		n2 = request.getParameter("number2");
		c1 = new Calculator(Integer.parseInt(n1),Integer.parseInt(n2));
		
		String add = request.getParameter("add");
		String sub = request.getParameter("sub");
		String mul = request.getParameter("mul");
		String div = request.getParameter("div");
		
		if(add.equals("Add")){
			result = c1.add();
		}else if (sub.equals("Sub")) {
			result = c1.sub();
		}else if (mul.equals("Multiply")) {
			result = c1.mul();
		}else if (div.equals("Divide")) {
			result = c1.div();
		}
		
		response.getWriter().println("Result: " + result);
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().println("GET");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.getWriter().println("POST");
		System.out.println("POST");
	}

}
