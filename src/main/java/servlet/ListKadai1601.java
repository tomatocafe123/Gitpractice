package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoKadai1601;
import dto.DtoKadai1601;

/**
 * Servlet implementation class ListKadai1601
 */
@WebServlet("/ListKadai1601")
public class ListKadai1601 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListKadai1601() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<DtoKadai1601> All = DaoKadai1601.SelectAllUser();
		
		request.setAttribute("selectAll", All);
		
		
		String web ="WEB-INF/web/selectAll.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(web);
         dispatcher.forward(request, response);	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
