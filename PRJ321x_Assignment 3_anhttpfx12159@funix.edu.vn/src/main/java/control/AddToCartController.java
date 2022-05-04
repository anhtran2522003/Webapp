package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modal.Cart;
import modal.Product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.oracle.wls.shaded.org.apache.xpath.functions.FuncSubstring;

import dao.ListProductDAO;

/**
 * Servlet implementation class AddToCartController
 */
public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToCartController() {
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
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		try {
			HttpSession session = (HttpSession) request.getSession(true);
			String idd = request.getParameter("id");
			String action = request.getParameter("action");
			String quantities = request.getParameter("quantity");
			if (action != null && action.equalsIgnoreCase("add")) {
				System.out.println("action add");
				if (session.getAttribute("cart") == null) {
					session.setAttribute("cart", new Cart());
				}
				int id = Integer.parseInt(idd);
				int quantity = Integer.parseInt(quantities);
				Product p = new ListProductDAO().getProduct("" + id);
				Cart c = (Cart) session.getAttribute("cart");
				c.add(new Product(p.getId(), p.getName(), p.getDecription(), p.getPrice(), p.getSrc(), p.getType(),
						p.getBrand(), quantity));
//				                   int id, String name, String decription, float price, String src, String type, String brand, int number
				response.sendRedirect("cart.jsp");
			} else if (action != null && action.equalsIgnoreCase("delete")) {
				int id = Integer.parseInt(idd);
				Cart c = (Cart) session.getAttribute("cart");
				c.remove(id);
			}

			request.getRequestDispatcher("product.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e + "");
			// TODO: handle exception
		}
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println(" to cart controller");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession(true);
		String idd = request.getParameter("id");
		String action = request.getParameter("action");
		String quantities = request.getParameter("quantity");
		System.out.println("id=" + idd + " " + "action=" +  action + " " + "quantities=" +  quantities);
		if (action != null) {
			switch (action) {
			case "addToCart":
				System.out.println("action add");
				if (session.getAttribute("cart") == null) {
					session.setAttribute("cart", new Cart());
					System.out.println("khong co cart");
				}
				int id = Integer.parseInt(idd);
				int quantity = Integer.parseInt(quantities);
				Product p = new ListProductDAO().getProduct("" + id);
				Cart c = (Cart) session.getAttribute("cart");
				c.add(new Product(p.getId(), p.getName(), p.getDecription(), p.getPrice(), p.getSrc(), p.getType(),
						p.getBrand(), quantity));
//						                   int id, String name, String decription, float price, String src, String type, String brand, int number
				response.sendRedirect("./InformationProduct?ProductID=" + idd);
				break;
			case "add":
				System.out.println("action add");
				if (session.getAttribute("cart") == null) {
					session.setAttribute("cart", new Cart());
					System.out.println("khong co cart");
				}
				int id1 = Integer.parseInt(idd);
				int quantity1 = Integer.parseInt(quantities);
				Product p1 = new ListProductDAO().getProduct("" + id1);
				Cart c1 = (Cart) session.getAttribute("cart");
				c1.add(new Product(p1.getId(), p1.getName(), p1.getDecription(), p1.getPrice(), p1.getSrc(),
						p1.getType(), p1.getBrand(), quantity1));
//						                   int id, String name, String decription, float price, String src, String type, String brand, int number
				response.sendRedirect("cart.jsp");
				break;
			case "delete":
				int id2 = Integer.parseInt(idd);
				Cart c2 = (Cart) session.getAttribute("cart");
				c2.remove(id2);
				break;
			case "update":
				if (session.getAttribute("cart") == null) {
					response.sendRedirect("cart.jsp");
				} else {
					Cart c3 = (Cart) session.getAttribute("cart");
					List<Product> lp = c3.getItems();
					for (Product pro : lp) {
						String idProduct = "P" + pro.getId();
						quantities = request.getParameter(idProduct);
						int quantity3 = Integer.parseInt(quantities);
						if (quantity3 == 0) {
							lp.remove(pro);
							
						break;
						} else {
							pro.setNumber(quantity3);
						}

						System.out.println(idProduct);
					}
				}
				
				response.sendRedirect("cart.jsp");

				break;

			default:
				response.sendRedirect("home.jsp");
			}
		} else {
			response.sendRedirect("home.jsp");
		}
		
//			if (action != null && action.equalsIgnoreCase("addToCart")) {
//				System.out.println("action add");
//				if (session.getAttribute("cart")== null ) {
//					session.setAttribute("cart", new Cart());
//					System.out.println("khong co cart");
//				}
//				int id = Integer.parseInt(idd);
//				int quantity = Integer.parseInt(quantities);
//				Product p = new ListProductDAO().getProduct("" + id);
//				Cart c = (Cart) session.getAttribute("cart");
//				c.add(new Product(p.getId(),p.getName(), p.getDecription(),p.getPrice(),p.getSrc(), p.getType(),p.getBrand(), quantity));
////				                   int id, String name, String decription, float price, String src, String type, String brand, int number
//				response.sendRedirect("./InformationProduct?ProductID=" + idd);
//			} 
//			else if (action != null && action.equalsIgnoreCase("add")) {
//				System.out.println("action add");
//				if (session.getAttribute("cart")== null ) {
//					session.setAttribute("cart", new Cart());
//					System.out.println("khong co cart");
//				}
//				int id = Integer.parseInt(idd);
//				int quantity = Integer.parseInt(quantities);
//				Product p = new ListProductDAO().getProduct("" + id);
//				Cart c = (Cart) session.getAttribute("cart");
//				c.add(new Product(p.getId(),p.getName(), p.getDecription(),p.getPrice(),p.getSrc(), p.getType(),p.getBrand(), quantity));
////				                   int id, String name, String decription, float price, String src, String type, String brand, int number
//				response.sendRedirect("cart.jsp");
//			} else if (action != null && action.equalsIgnoreCase("delete")) {
//				int id = Integer.parseInt(idd);
//				Cart c = (Cart) session.getAttribute("cart");
//				c.remove(id);
//			}

	}
}
