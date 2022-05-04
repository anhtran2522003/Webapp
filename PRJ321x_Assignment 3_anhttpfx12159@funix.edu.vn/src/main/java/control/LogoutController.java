package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class LogoutController
 */
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charser=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		session.removeAttribute("acc");
		// delete cookie

		Cookie cookie = null;
		Cookie[] cookies = null;
		cookies = request.getCookies();

		if (cookies == null) {
			response.sendRedirect("login.jsp");
		}

		for (int i = 0; i < cookies.length; i++) {
			cookie = cookies[i];

			if ((cookie.getName()).equals("user")) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}

			if ((cookie.getName()).equals("password")) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}

		}

		response.sendRedirect("login.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
