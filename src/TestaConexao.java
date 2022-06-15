import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory ConnectionFactory = new ConnectionFactory();
		Connection connection = ConnectionFactory.recuperarConexao();
		
		System.out.println("Fechando conexão!");
		
		connection.close();//sempre fechar uma conexão com o banco de dados!
		
		System.out.println("Fechou!");
	}

}
