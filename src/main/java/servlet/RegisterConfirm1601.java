package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.DtoKadai1601;

/**
 * Servlet implementation class RegisterConfirm1601
 */
@WebServlet("/RegisterConfirm1601")
public class RegisterConfirm1601 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterConfirm1601() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
		
        String idStr=request.getParameter("id");
        int id=Integer.parseInt(idStr);
        
		String name = request.getParameter("name");
		
		String ageStr=request.getParameter("age");
		int age=Integer.parseInt(ageStr);
		
		String genderStr=request.getParameter("gender");
	    int gen=Integer.parseInt(genderStr);
	    String gender=gen==0 ? "男":"女";
	    
		String tel=request.getParameter("tel");
		String mail = request.getParameter("email");
		String pw = request.getParameter("pw");
		
		DtoKadai1601 account = new DtoKadai1601(id, name,age,gender,tel, mail, null, pw, null);
		
		HttpSession session = request.getSession();
		session.setAttribute("input_data", account);
		
		
		
		String web = "WEB-INF/web/confirm.jsp";
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
