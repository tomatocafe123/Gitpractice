package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoKadai1601;
import dto.DeleteDto;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
	     String iDStr =request.getParameter("id");
	     int id=Integer.parseInt(iDStr);
		
		
		DeleteDto del = new DeleteDto(id);
		
		int result = DaoKadai1601.deleteUser(del);
	
		if(result == 1) {
			String web = "WEB-INF/web/dsuccess.jsp";
			 RequestDispatcher dispatcher = request.getRequestDispatcher(web);
			 dispatcher.forward(request, response);
		} else {
			String web = "WEB-INF/web/dfail.jsp";
			 RequestDispatcher dispatcher = request.getRequestDispatcher(web);
		     dispatcher.forward(request, response);
		}
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
