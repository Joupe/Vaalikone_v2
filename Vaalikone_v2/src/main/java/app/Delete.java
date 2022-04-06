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

@WebServlet(name = "Delete", urlPatterns = { "/delete" })
public class Delete extends HttpServlet {
	private Dao dao;

	public void init() {
		dao = new Dao("jdbc:mysql://localhost:3306/vaalikone?useSSL=false", "sikli", "kukkuu");
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String id = request.getParameter("question_id");
		ArrayList<Question> list = null;
		if (dao.getConnection()) {
			list = dao.deleteQuestion(id);
		}
		request.setAttribute("questionlist", list);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/showquestions.jsp");
		rd.forward(request, response);
	}
	
}