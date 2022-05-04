package controller.product;
//this package contains the controller of the functions that operate on the Product table
// controller of the function that displays product information in the data source
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modal.Cart;
import modal.Product;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import dao.ListProductDAO;

/**
 * Servlet implementation class ListController
 */
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			HttpSession session = (HttpSession) request.getSession(true);
			String id = request.getParameter("id");
			String action = request.getParameter("action");
			if (action != null && action.equalsIgnoreCase("add")) {
				if (session.getAttribute("cart") == null) {
					session.setAttribute("cart", new Cart());
				}
				int id = Integer.parseInt(id);
				Product p = new ListProductDAO().getProduct("" + id);
				Cart c = (Cart) session.getAttribute("action");
				c.add(new Product(p.getId(), p.getName(), p.getDecription(), p.getPrice(), p.getSrc(), p.getType(),
						p.getBrand(), 1));
			} else if (action != null && action.equalsIgnoreCase("delete")) {
				int id = Integer.parseInt(id);
				Cart c = (Cart) session.getAttribute("cart");
				c.remove(id);
			}

			response.sendRedirect("cart.jsp");

		} catch (Exception ex) {
			response.getWriter().println(ex);
		}

	}
}
