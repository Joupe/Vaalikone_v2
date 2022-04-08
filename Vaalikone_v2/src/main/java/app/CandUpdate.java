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


@WebServlet(
    name = "CandUpdate",
    urlPatterns = {"/candupdate"}
)
public class CandUpdate extends HttpServlet {
	private Dao dao;
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone?useSSL=false", "sikli", "kukkuu");
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	     throws IOException {
		response.sendRedirect("index.html");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	     throws IOException, ServletException {

		String id=request.getParameter("candidate_id");
		String surname=request.getParameter("surname");
		String firstname=request.getParameter("first_name");
		String candNumb=request.getParameter("cand_no");
		String age=request.getParameter("age");
		String hometown=request.getParameter("hometown");
		String party=request.getParameter("party");
		String profession=request.getParameter("profession");
		String description=request.getParameter("descr");
		Candidates c=new Candidates(id, surname, firstname, candNumb, age, hometown, party, profession, description);
		
		ArrayList<Candidates> list=null;
		if (dao.getConnection()) {
			list=dao.updateCandidates(c);
		}
		request.setAttribute("candidatelist", list);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/managecandidates.jsp");
		rd.forward(request, response);
		
	}
}