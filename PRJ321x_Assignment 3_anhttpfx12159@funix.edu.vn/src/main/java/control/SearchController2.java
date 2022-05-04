package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modal.Product;

import java.io.IOException;
import java.util.List;

import dao.ListProductDAO;

/**
 * Servlet implementation class SearchController2
 */
public class SearchController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchController2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String txtSearch = request.getParameter("txtSearch");
		
  		ListProductDAO dao = new ListProductDAO();
  		List<Product>  SearchProduct = dao.search(txtSearch);
  		if (SearchProduct.size() == 0) {
//  			System.out.println("Khong co gia tri tra ve");
  			request.setAttribute("error", "NO RESULT");
  		}
  		// set data to jsp
  		request.setAttribute("SearchProduct", SearchProduct);
  		
  		//
  		List<Product>  listTopSelling = dao.getListTopSelling();
  		// set data to jsp
  		request.setAttribute("listTopSelling", listTopSelling);
  		request.setAttribute("txtSearch", txtSearch);
  		
  		request.getRequestDispatcher("search.jsp").forward(request, response);
 
		

	}
}
