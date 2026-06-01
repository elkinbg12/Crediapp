import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {

	public void cadastrarCliente(Clientes cliente) {
		
		String sql = "INSERT INTO clientes(nome, cpf, telefone, bairro, rua, numero, complemento) VALUES (?,?,?,?,?,?,?)";
		
		try (Connection conn = ConexaoBanco.conectar();
			 PreparedStatement stmt = conn.prepareStatement(sql)){
			
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getTelefone());
			stmt.setString(4, cliente.getBairro());
			stmt.setString(5, cliente.getRua());
			stmt.setString(6, cliente.getNumero());
			stmt.setString(7, cliente.getComplemento());
			
			stmt.executeUpdate();
			System.out.println("Cliente cadastrado com sucesso no banco!");
		} catch (SQLException e) {
			
			System.out.println("Erro ao tentar cadastrar cliente: " + e.getMessage());
		}
	}
}
