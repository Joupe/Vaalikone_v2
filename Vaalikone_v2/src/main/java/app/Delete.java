package app;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import data.Question;
/**
 * Date: May 4-2022
 * Class for deleting questions from database
 * @author Sikli
 */
@WebServlet(name = "Delete", urlPatterns = { "/delete" })
public class Delete extends HttpServlet {
	private Dao dao;
	/**
	 *Method for database connection
	 */
	public void init() {
		dao = new Dao("jdbc:mysql://localhost:3306/vaalikone?useSSL=false", "sikli", "kukkuu");
	}

	@Override
	/**
	 *Get-method for deleting questions
	 *Deletes question from database using getParameter with id set in Questions.java
	 *Calls deleteQuestion method from Dao.java
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String id = request.getParameter("id");
		ArrayList<Question> list = null;
		if (dao.getConnection()) {
			list = dao.deleteQuestion(id);
		}
		request.setAttribute("questionlist", list);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/showkysmarits.jsp");
		rd.forward(request, response);
	}

}