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
 * Servlet implementation class HomeController
 */
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          		response.setContentType("text/html;charset=UTF-8");
          		//
          		// get data from dao
          		ListProductDAO dao = new ListProductDAO();
          		List<Product>  listHotDealSection = dao.getListHotDealSection();
          		// set data to jsp
          		request.setAttribute("listHotDealSection", listHotDealSection);
          		
          		//
          		// get data from dao
          		List<Product>  listNewProduct = dao.getListNewProduct();
          		// set data to jsp
          		request.setAttribute("listNewProduct", listNewProduct);
          		
          		//
          		// get data from dao
          		List<Product>  listTopSelling = dao.getListTopSelling();
          		// set data to jsp
          		request.setAttribute("listTopSelling", listTopSelling);
          		
          		//
          		// get data from dao
          		List<Product>  listTop_3_Selling = dao.getListTop_3_Selling();
          		// set data to jsp
          		request.setAttribute("listTop_3_Selling", listTop_3_Selling);
          		
          		request.getRequestDispatcher("home.jsp").forward(request, response);
          		
	}

}
