import java.sql.Connection;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Tentando conectar ao banco...");
		
		Connection conexao = ConnectionFactory.getConnection();
		
		if (conexao != null) {
			System.out.println("Conexão realizada com sucesso.");
		}

	}

}
