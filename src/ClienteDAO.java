import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class ClienteDAO {

	public void cadastrarCliente(Cliente cliente) {
		
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

	public List<Cliente> listarClientes() {

		String sql = "SELECT * FROM clientes";
		List<Cliente> lista = new ArrayList<>();

		try (Connection conn = ConexaoBanco.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {

             while (rs.next()) {
				Cliente cliente = new Cliente();

				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setBairro(rs.getString("bairro"));
				cliente.setRua(rs.getString("rua"));
                cliente.setNumero(rs.getString("numero"));
				cliente.setComplemento(rs.getString("complemento"));
				
				lista.add(cliente);
			 }        
			 } catch (SQLException e) {
				System.out.println("Erro ao listar clientes: " + e.getMessage());
			 }

			 return lista;
	}

	public Cliente buscaPorCpf(String cpf) {

		String sql = "SELECT * FROM clientes WHERE cpf = ?";

		try (Connection conn = ConexaoBanco.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql);) {

				stmt.setString(1, cpf);

				try (ResultSet rs = stmt.executeQuery()) {

					if (rs.next()) {

						Cliente c = new Cliente();

						c.setId(rs.getInt("id"));
						c.setNome(rs.getString("nome"));
						c.setCpf(rs.getString("cpf"));
						c.setTelefone(rs.getString("telefone"));
						c.setRua(rs.getString("rua"));
						c.setNumero(rs.getString("numero"));
						c.setBairro(rs.getString("bairro"));
						c.setComplemento(rs.getString("complemento"));

						return c;

					}
				}
			 } catch (SQLException e) {

				System.out.println("Erro ao buscar Cliente por CPF: " +  e.getMessage());
				
			 }
			 return null;
	}
}
