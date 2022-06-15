import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercaoComParamentro {

	public static void main(String[] args) throws SQLException {

		// String nome = "Mouse'";
		// String descricacao = "Mouse sem fio); delete from Produto;";

		ConnectionFactory ConnectionFactory = new ConnectionFactory();
		try (Connection connection = ConnectionFactory.recuperarConexao()) {//usando o try() "try-with-resources" , não há a necessidade de fazer fechamento da conexão.

			try (PreparedStatement stm = connection.prepareStatement(
					"INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {

				adicionarVariavel("One Piece", "Manga", stm);
				adicionarVariavel("Black Butler", "Manga", stm);

				connection.commit();

				stm.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ROLLBACK EXECUTADO");
				connection.rollback();
			}
		}
	}

	private static void adicionarVariavel(String nome, String descricacao, PreparedStatement stm) throws SQLException {
		stm.setString(1, nome);
		stm.setString(2, descricacao);

		if (nome.equalsIgnoreCase("Radio")) {
			throw new RuntimeException("Não foi possível adicionar o produto!");
		}

		stm.execute();

		try (ResultSet rst = stm.getGeneratedKeys()) {
			while (rst.next()) {
				Integer id = rst.getInt(1);
				System.out.println("O id criado foi: " + id);
			}
		}
	}
}
