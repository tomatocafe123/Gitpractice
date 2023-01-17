package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoKadai1601;
import dto.DtoKadai1601;
import util.GenerateHashedPw;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     request.getParameter("UTF-8");
     
     String mail=request.getParameter("mail");
     String pw=request.getParameter("pw");
     
     String salt=DaoKadai1601.getSalt(mail);
     
     if(salt==null) {
    	 String web="WEB-INF/web/login.jsp?error=1";
    	 RequestDispatcher dispatcher = request.getRequestDispatcher(web);
		dispatcher.forward(request, response);
	  return;
     }
     
	  String hashedPw=GenerateHashedPw.getSafetyPassword(pw,salt);
	  DtoKadai1601 account=DaoKadai1601.login(mail, hashedPw);
	  
	  if(account == null) {
			String view = "WEB-INF/view/sample-login.jsp?error=1";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		} else {
			String view = "WEB-INF/view/sample-menu.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
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
