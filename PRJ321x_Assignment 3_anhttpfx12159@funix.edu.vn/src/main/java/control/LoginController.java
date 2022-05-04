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
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Cookie cookie = null;
		Cookie[] cookies = null;
		cookies = request.getCookies();

		if (cookies != null) {
			out.print("cookie khac null");
			String user = "";
			String password = "";
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if ((cookie.getName()).equals("user")) {
					user = cookie.getValue();
				}
				if ((cookie.getName()).equals("password")) {
					password = cookie.getValue();
				}
				System.out.print("dang doc cookie");
			}
			if (user == "" || password == "") {
				request.getRequestDispatcher("login.jsp").forward(request, response);
				System.out.print("khong  luu cookie");
			} else {
				System.out.print("co  luu cookie");
				System.out.print(user + " " + password);
				AccountDAO a = new AccountDAO();
				Account acc = a.search(user);
				if (acc == null) {
					System.out.println("acc = null");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} else if (password.equals(acc.getPwd())) {
					System.out.println("acc != null");
					System.out.println(acc.getUsr() + " " + acc.getAddress() + " " + acc.getAddress());
					if (acc.getRole() == 1) {
						request.getRequestDispatcher("admin/index.jsp").forward(request, response);
					} else {
						response.sendRedirect("login.jsp");
					}

				} else {
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			}
		} else {
			System.out.print("cookie = null");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

		System.out.print("login controller page");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		progressRequest(request, response);
	}

	protected void progressRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charser=UTF-8");
		request.setCharacterEncoding("utf-8");

		try {

			request.getSession(true).invalidate();
			// make sure that email is valid
			String regexMail = "^[A-Z0-9_a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
			String regex = "[a-zA-Z0-9_!@#$%^&*]+";
			// collect data from a login form
			String user = request.getParameter("username");
			String password = request.getParameter("password");

			HttpSession session = request.getSession(true);
			if (!password.matches(regex) || !user.matches(regexMail)) {
				session.setAttribute("error", "Invalid syntax");
				response.sendRedirect("login.jsp");
			} else {
				// read information of account gmail from db
				AccountDAO a = new AccountDAO();
				Account acc = a.search(user);

				// check account - use validate code in assignment 1 to valid user
				if (acc != null && acc.getPwd().equals(password) && acc.getUsr().equalsIgnoreCase(user)) {
					// set session

					session.setAttribute("acc", acc);

					String rememberMe = (String) request.getParameter("rememberMe");
					if (rememberMe != null) {
						Cookie cookieUser = new Cookie("user", acc.getUsr());
						Cookie cookiePass = new Cookie("password", acc.getPwd());
						cookieUser.setMaxAge(5000);
						cookiePass.setMaxAge(5000);
						response.addCookie(cookieUser);
						response.addCookie(cookiePass);

					}
					if (acc.getRole() == 1) {
						response.sendRedirect("admin/index.jsp");
					} else {
						response.sendRedirect("Login");
					}

				} else {
					session.setAttribute("user", user);
					session.setAttribute("password", password);
					session.setAttribute("error", "Wrong username or password!");
					response.sendRedirect("login.jsp");
				}
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
