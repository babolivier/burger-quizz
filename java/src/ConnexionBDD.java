import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnexionBDD
{
	Connection connexionbdd;
	String nomBdd;
	GestionCategories gestionCategories;
	GestionReponses gestionReponses;
	GestionQuestions gestionQuestions;

	public ConnexionBDD(String nomBdd, int port, String ip, String login, String password)
	{
		this.nomBdd = nomBdd;

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
			System.exit(1);
		}

		try
		{
			String url = "jdbc:mysql://" + ip + ":" + port + "/" + nomBdd;
			connexionbdd = DriverManager.getConnection(url, login, password);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("Erreur de conexion à la base de données");
			System.exit(1);
		}
	}
}