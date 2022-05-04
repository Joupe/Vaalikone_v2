package dao;

import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data.Candidates;
import data.Question;

import java.sql.Connection;
/**
 * Date: May 4-2022
 * Class for communicating with servlets, databases and classes
 * @author Sikli
 */

public class Dao {
	private String url;
	private String user;
	private String pass;
	private Connection conn;

	/**
	 * Constructor for database connection
	 * @param url url of the database used
	 * @param user username for database connection
	 * @param pass password for database connection
	 */
	public Dao(String url, String user, String pass) {
		this.url = url;
		this.user = user;
		this.pass = pass;
	}

	/**
	 * Method for connecting to database
	 * @return Returns true if connection is valid
	 */
	public boolean getConnection() {
		try {
			if (conn == null || conn.isClosed()) {
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
					throw new SQLException(e);
				}
				conn = DriverManager.getConnection(url, user, pass);
			}
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Method for reading all questions from database table
	 * @return returns a list of questions from database
	 */
	public ArrayList<Question> readAllQuestions() {
		ArrayList<Question> list = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet RS = stmt.executeQuery("select * from questions");
			while (RS.next()) {
				Question q = new Question();
				q.setId(RS.getInt("question_id"));
				q.setQuestion(RS.getString("question"));
				list.add(q);
			}
			return list;
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Method used to update a specific question from database
	 * @param q Parameter for the updated question
	 * @return returns an updated list of questions from database
	 */
	public ArrayList<Question> updateQuestions(Question q) {
		try {
			String sql = "update questions set question=? where question_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, q.getQuestion());
			pstmt.setInt(2, q.getId());
			pstmt.executeUpdate();
			return readAllQuestions();
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Method for deleting a specific question from database
	 * @param id Parameter for question id to delete
	 * @return Returns updated list of questions after deletion
	 */
	public ArrayList<Question> deleteQuestion(String id) {
		try {
			String sql = "delete from questions where question_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			return readAllQuestions();
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Method for reading a specific question from database
	 * @param id Parameter for question id to read
	 * @return Returns the question from database
	 */
	public Question readQuestion(String id) {
		Question q = null;
		try {
			String sql = "select * from questions where question_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet RS = pstmt.executeQuery();
			while (RS.next()) {
				q = new Question();
				q.setId(RS.getInt("question_id"));
				q.setQuestion(RS.getString("question"));
			}
			return q;
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Method for adding a question to database
	 * @param kysmari Parameter for the question itself
	 * @return Returns the updated question table from database
	 */
	public ArrayList<Question> addQuestion(String kysmari) {
		Question que = null;
		try {
			String sql = "insert into questions (question) values ('" + kysmari + "')";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			return readAllQuestions();
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		}
		return null;
	}

	/**
	 * Method for reading all candidates from database
	 * @return Returns a list of all the candidates in database
	 */
	public ArrayList<Candidates> readAllCandidates() {
		ArrayList<Candidates> list = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet RS = stmt.executeQuery("select * from candidates");
			while (RS.next()) {
				Candidates c = new Candidates();
				c.setId(RS.getInt("candidate_id"));
				c.setSurname(RS.getString("surname"));
				c.setFirstname(RS.getString("first_name"));
				c.setCandNumb(RS.getString("cand_no"));
				c.setAge(RS.getString("age"));
				c.setHometown(RS.getString("hometown"));
				c.setParty(RS.getString("party"));
				c.setProfession(RS.getString("profession"));
				c.setDescription(RS.getString("descr"));
				list.add(c);
			}
			return list;
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Method for reading a specific candidate from database
	 * @param id Parameter for candidates id read from database
	 * @return Returns the specific candidate from database
	 */
	public Candidates readCandidate(String id) {
		Candidates c = null;
		try {
			String sql = "select * from candidates where candidate_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet RS = pstmt.executeQuery();
			while (RS.next()) {
				c = new Candidates();
				c.setId(RS.getInt("candidate_id"));
				c.setSurname(RS.getString("surname"));
				c.setFirstname(RS.getString("first_name"));
				c.setCandNumb(RS.getString("cand_no"));
				c.setAge(RS.getString("age"));
				c.setHometown(RS.getString("hometown"));
				c.setParty(RS.getString("party"));
				c.setProfession(RS.getString("profession"));
				c.setDescription(RS.getString("descr"));
			}
			return c;
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Method for deleting a candidate from database
	 * @param id Parameter for candidate id to delete
	 * @return Returns updated candidate list after deletion
	 */
	public ArrayList<Candidates> deleteCandidate(String id) {
		try {
			String sql = "delete from candidates where candidate_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			return readAllCandidates();
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Method for updating a candidate from database
	 * @param c Parameter for the updated candidate
	 * @return Returns updated candidate list from database
	 */
	public ArrayList<Candidates> updateCandidates(Candidates c) {
		try {
			String sql = "update candidates set surname=?, first_name=?, cand_no=?, age=?, hometown=?, party=?, profession=?, descr=? where candidate_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getSurname());
			pstmt.setString(2, c.getFirstname());
			pstmt.setInt(3, c.getCandNumb());
			pstmt.setInt(4, c.getAge());
			pstmt.setString(5, c.getHometown());
			pstmt.setString(6, c.getParty());
			pstmt.setString(7, c.getProfession());
			pstmt.setString(8, c.getDescription());
			pstmt.setInt(9, c.getId());
			pstmt.executeUpdate();
			return readAllCandidates();
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Method for adding a candidate to the database
	 * @param cSurname Parameter for candidate surname
	 * @param cFirstname Parameter for candidate first name
	 * @param cCandnumb Parameter for candidate number
	 * @param cAge Parameter for candidate age
	 * @param cHometown Parameter for candidate hometown
	 * @param cParty Parameter for candidate party
	 * @param cProfession Parameter for candidate profession
	 * @param cDescription Parameter for candidate description
	 * @return Returns the updated candidate list
	 */
	public ArrayList<Candidates> addCandidate(String cSurname, String cFirstname, int cCandnumb, int cAge,
			String cHometown, String cParty, String cProfession, String cDescription) {
//		Candidates cand = null;
		try {
			String sql = "insert into candidates(surname, first_name, cand_no, age, hometown, party, profession, descr) values ('" + cSurname + "', '" + cFirstname + "', '" + cCandnumb + "', '" + cAge + "', '" + cHometown + "', '" + cParty + "', '" + cProfession + "', '" + cDescription + "')";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			return readAllCandidates();
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		}
		return null;
	}
}

