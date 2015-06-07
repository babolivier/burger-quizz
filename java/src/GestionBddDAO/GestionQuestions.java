package GestionBddDAO;

import Modele.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GestionQuestions
{
	private Connection bdd;
	private ArrayList<Question> listeQuestions;

	public GestionQuestions(Connection bdd)
	{
		this.bdd = bdd;
		this.listeQuestions = new ArrayList<Question>();
	}

	public void readQuestions(String reponse1, String reponse2) throws SQLException
	{
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		String rq = "SELECT *" +
				" FROM questions" +
				" WHERE reponse1 = ? AND reponse2 = ?";

		try
		{
			preparedStatement = bdd.prepareStatement(rq);
			preparedStatement.setString(1, reponse1);
			preparedStatement.setString(2, reponse2);
			resultat = preparedStatement.executeQuery();

			listeQuestions.clear();

			while(resultat.next())
			{
				listeQuestions.add(new Question(resultat.getString("intitule"), resultat.getString("reponse1"), resultat.getString("reponse2"), resultat.getInt("num_reponse")));
			}
		}
		catch (SQLException e)
		{
			throw e;
		}
		finally
		{
			if(resultat != null)
			{
				resultat.close();
			}
			if(preparedStatement != null) {
				preparedStatement.close();
			}
		}
	}

	public void createQuestion(String intitule, String reponse1, String reponse2, int num_reponse) throws SQLException
	{
		PreparedStatement preparedStatement = null;
		String rq = "INSERT INTO questions(intitule, reponse1, reponse2, num_reponse)" +
				" VALUES(?, ?, ?, ?)";
		try
		{
			preparedStatement = bdd.prepareStatement(rq);
			preparedStatement.setString(1, intitule);
			preparedStatement.setString(2, reponse1);
			preparedStatement.setString(3, reponse2);
			preparedStatement.setInt(4, num_reponse);
			preparedStatement.executeUpdate();
		}
		catch (SQLException e)
		{
			throw e;
		}
		finally
		{
			if(preparedStatement != null)
			{
				preparedStatement.close();
			}
		}
	}

	public void deleteQuestion(String intitule, String reponse1, String reponse2) throws SQLException
	{
		PreparedStatement preparedStatement = null;
		String rq ="DELETE FROM questions" +
				" WHERE reponse1 = ? AND reponse2 = ? AND intitule = ?";
		try
		{
			preparedStatement = bdd.prepareStatement(rq);
			preparedStatement.setString(1, reponse1);
			preparedStatement.setString(2, reponse2);
			preparedStatement.setString(3, intitule);
			preparedStatement.executeUpdate();
		}
		catch (SQLException e)
		{
			throw e;
		}
		finally
		{
			if(preparedStatement != null)
			{
				preparedStatement.close();
			}
		}
	}

	public void updateQuestion(String oldIntitule, String newIntitule, String reponse1, String reponse2, int newNum_reponse) throws SQLException
	{
		PreparedStatement preparedStatement = null;
		String rq ="UPDATE questions" +
				" SET intitule = ?, num_reponse = ?" +
				" WHERE intitule = ? AND reponse1 = ? AND reponse2 = ?";
		try
		{
			preparedStatement = bdd.prepareStatement(rq);
			preparedStatement.setString(1, newIntitule);
			preparedStatement.setInt(2, newNum_reponse);
			preparedStatement.setString(3, oldIntitule);
			preparedStatement.setString(4, reponse1);
			preparedStatement.setString(5, reponse2);
			preparedStatement.executeUpdate();
		}
		catch (SQLException e)
		{
			throw e;
		}
		finally
		{
			if(preparedStatement != null)
			{
				preparedStatement.close();
			}
		}
	}

	public ArrayList<Question> getListeQuestions() {
		return listeQuestions;
	}
}