package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modal.Account;

import java.io.IOException;
import java.io.PrintWriter;

import dao.AccountDAO;

/**
 * Servlet implementation class SignupController
 */
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("register.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charser=UTF-8");
		request.setCharacterEncoding("utf-8");

		try {

			request.getSession(true).invalidate();
			// make sure that email is valid
			String regexMail = "^[A-Z0-9_a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
			String regex = "[a-zA-Z0-9_!@#$%^&*]+";
			// collect data from a login form
			String name = request.getParameter("name");
			String user = request.getParameter("username");
			String password = request.getParameter("password");
			String re_password = request.getParameter("re_password");
			Account acc = new Account(user,password,0,name,"","",0);
			HttpSession session = request.getSession(true);
			session.setAttribute("acc", acc);
			if (!password.matches(regex) || !user.matches(regexMail)) {
				session.setAttribute("error1", "Invalid syntax");
				response.sendRedirect("register.jsp");
			}  else if (!password.equals(re_password) ) {
				
				session.setAttribute("error1", "Re_password is incorrect!");
				response.sendRedirect("register.jsp");
				
			} else {
				// read information of account gmail from db
				AccountDAO a = new AccountDAO();
				// check  email exit or not
				if (a.checkAccountExits(user)) {
					session.setAttribute("error1", "The email was existed!");
					response.sendRedirect("register.jsp");
				} else {
					
					a.signup(acc);
					String rememberMe = (String) request.getParameter("rememberMe");
					if (rememberMe != null) {
						Cookie cookieUser = new Cookie("user", acc.getUsr());
						Cookie cookiePass = new Cookie("password", acc.getPwd());
						cookieUser.setMaxAge(5000);
						cookiePass.setMaxAge(5000);
						response.addCookie(cookieUser);
						response.addCookie(cookiePass);

					}
					
					response.sendRedirect("Home");
				}

//				// check account - use validate code in assignment 1 to valid user
//				if (acc != null && acc.getPwd().equals(password) && acc.getUsr().equalsIgnoreCase(user)) {
//					// set session
//
//					session.setAttribute("acc", acc);
//
//					String rememberMe = (String) request.getParameter("rememberMe");
//					if (rememberMe != null) {
//						Cookie cookieUser = new Cookie("user", acc.getUsr());
//						Cookie cookiePass = new Cookie("password", acc.getPwd());
//						cookieUser.setMaxAge(5000);
//						cookiePass.setMaxAge(5000);
//						response.addCookie(cookieUser);
//						response.addCookie(cookiePass);
//
//					}
//					if (acc.getRole() == 1) {
//						response.sendRedirect("admin/index.jsp");
//					} else {
//						response.sendRedirect("Home");
//					}
//
//				} else {
//					session.setAttribute("user", user);
//					session.setAttribute("password", password);
//					session.setAttribute("error", "Wrong username or password!");
//					response.sendRedirect("login.jsp");
//				}
			}
		} catch (NullPointerException e) {
			PrintWriter printWrite = response.getWriter();
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);

		} catch (Exception ex) {
			response.getWriter().println(ex);
		}
		
	}
}
