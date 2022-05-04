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
 * Servlet implementation class InformationProductController
 */
public class InformationProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InformationProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          		response.setContentType("text/html;charset=UTF-8");
          		String ProductID = request.getParameter("ProductID");
          		//
          		// get data from dao
          		ListProductDAO dao = new ListProductDAO();
          		Product p = dao.getProduct(ProductID);
          		// set data to jsp
          		request.setAttribute("Product", p);
          		
          		//
          		// get data from dao
          		List<Product> relatedProduct = dao.getRelatedProductProduct(ProductID);
          		// set data to jsp
          		request.setAttribute("relatedProduct", relatedProduct);
          		
          		
          		request.getRequestDispatcher("product.jsp").forward(request, response);
    
          		
	}
}
