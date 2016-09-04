package sample.bank.servlets;
import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.bank.data.*;
import sample.bank.bean.*;

import sample.bank.bean.CustomerBean;

public class Bank extends HttpServlet implements Servlet {
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Bank() {
		
		super();
		
	}
	

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
	}
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest arg0, HttpServletResponse arg1)
	 */
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(arg0, arg1);
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest arg0, HttpServletResponse arg1)
	 */
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String userId, password;
		String action = arg0.getParameter("action");
		Dispatcher dispatch = new Dispatcher();
	
		Customers customers = (Customers) getServletContext().getAttribute("customers");
	
		
		if (action.equals("signup"))
			action = "signup";
		
		if (action.equals("login")) {
			ValidUser validUser = new ValidUser(arg0);
			
			action = validUser.validate()?"mypage":"first";
			
		}
		
		if (action.equals("openAccount")) {
			Validator validator = new Validator(arg0);
			
			action = validator.validate()?"success":"signup";
		}
		
		
	
		
		
		dispatch.forward(action, arg0, arg1);
		
	}

}