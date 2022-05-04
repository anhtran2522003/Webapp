package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modal.Account;
import modal.Cart;
import modal.Orders;
import modal.Product;
import modal.ProductOrders;

import java.util.ArrayList;
import java.util.Date;
import java.io.IOException;

import java.util.List;

import dao.OrdersDAO;

/**
 * Servlet implementation class PayController
 */
public class PayController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PayController() {
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
		progressRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		progressRequest(request, response);
	}

	protected void progressRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			HttpSession session = request.getSession(true);
			if (session.getAttribute("cart") == null) {
				response.sendRedirect("Home");
			}

			OrdersDAO dao = new OrdersDAO();
			// save data to ds 
			// get cart
			Cart cart = (Cart) session.getAttribute("cart");
			List<Product> items = cart.getItems();
			List<ProductOrders> lpo =  new ArrayList<>();
			for (Product p  : items) {
				lpo.add(new ProductOrders(1,p.getId(),  p.getNumber() , p.getName()));
			}
			double price = cart.getAmount();
			// get account 
			 Account acc = (Account) session.getAttribute("acc");
			 String email = null;
			 if (acc != null ) {
				 email = acc.getUsr();
			 }
			 //String usr, String pwd, int role, String name, String address, String phone, int check
			String username = request.getParameter("name");
			String discount = "";
			String address = request.getParameter("address");
			String phoneNumber = request.getParameter("phoneNumber");

			java.util.Date now = new java.util.Date();
			email = request.getParameter("email");;
			Orders order = new Orders(1, price, 0 , now , address, phoneNumber, lpo , email, now ,  discount);
			// int orderId, float price, int status, Date orderDate, String address, String phoneNumber,
//			List<ProductOrders> lp, String userMail, Date receiveDate, String discount
			dao.insertOrder(order, cart,email);
			// delete cart
			 session.removeAttribute("cart");
			// 
			response.sendRedirect("Home");
		} catch (Exception ex){
			response.getWriter().println(ex);
			ex.printStackTrace();
		}
		
	}

}
