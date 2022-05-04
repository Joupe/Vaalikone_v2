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
import data.Candidates;

/**
 * Date: May 4-2022
 * Class for deleting candidates from database
 * @author Sikli
 */

@WebServlet(name = "CandDelete", urlPatterns = { "/canddelete" })
public class CandDelete extends HttpServlet {
	private Dao dao;

	/**
	 *Method for database connection
	 */
	public void init() {
		
		dao = new Dao("jdbc:mysql://localhost:3306/vaalikone?useSSL=false", "sikli", "kukkuu");
	}

	/**
	 *Get-method for deleting candidate
	 *Deletes candidate from database using getParameter with id set in Candidates.java
	 *Calls deleteCandidate method from Dao.java
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String id = request.getParameter("id");
		ArrayList<Candidates> list = null;
		if (dao.getConnection()) {
			list = dao.deleteCandidate(id);
		}
		request.setAttribute("candidatelist", list);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/managecandidates.jsp");
		rd.forward(request, response);
	}

}