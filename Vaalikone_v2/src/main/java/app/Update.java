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
 * Class for updating questions in database
 * @author Sikli
 */
@WebServlet(name = "Update", urlPatterns = { "/update" })
public class Update extends HttpServlet {
	private Dao dao;
	/**
	 *Method for database connection
	 */
	public void init() {
		dao = new Dao("jdbc:mysql://localhost:3306/vaalikone?useSSL=false", "sikli", "kukkuu");
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("index.html");
	}
	/**
	 *Post-method for updating questions column in database
	 *Calls updateQuestions method from Dao.java
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String id = request.getParameter("id");
		String question = request.getParameter("question");

		Question q = new Question(id, question);

		ArrayList<Question> list = null;
		if (dao.getConnection()) {
			list = dao.updateQuestions(q);
		}
		request.setAttribute("questionlist", list);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/showkysmarits.jsp");
		rd.forward(request, response);

	}
}