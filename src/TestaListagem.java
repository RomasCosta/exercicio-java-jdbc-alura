import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestaListagem {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory ConnectionFactory = new ConnectionFactory();
		Connection connection = ConnectionFactory.recuperarConexao();
		
		connection.setAutoCommit(false);
		
		PreparedStatement stm  = connection.prepareStatement("SELECT ID, NOME, DESCRICAO FROM PRODUTO");//Statement permite usar os comandos SQL no Java.
		//boolean resultado = stm.execute("SELECT ID, NOME, DESCRICACAO FROM PRODUTO");
		//System.out.println(resultado);
		stm.execute();
		
		ResultSet rst = stm.getResultSet();//vai pegar os resultados da tabela do mysql
		
		while(rst.next()) {//laço para imprimir os itens do nosso banco de dados. O .next serve para buscar todos os itens da lista.
			Integer id = rst.getInt("ID");
			System.out.println(id);
			String nome = rst.getString("NOME");
			System.out.println(nome);
			String descricao = rst.getString("DESCRICAO");
			System.out.println(descricao);
		}
		
		
		
		connection.close();// sempre fechar uma conexão com o banco de dados!
	}

}
