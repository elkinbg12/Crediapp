import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO {
    
    public void cadastrarEmprestimo(Emprestimo emprestimo) {

        String sql = "INSERT INTO Emprestimos (valor_puro, taxa_aplicada, valor_total_juros, saldo_devedor, total_parcelas, parcelas_pagas, data_emprestimo, cliente_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

       try (Connection conn = ConexaoBanco.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

           stmt.setDouble(1, emprestimo.getValorPuro());
           stmt.setDouble(2, emprestimo.getTaxaAplicada());
           stmt.setDouble(3, emprestimo.getValorTotalJuros());
           stmt.setDouble(4, emprestimo.getSaldoDevedor());
           stmt.setInt(5, emprestimo.getTotalParcelas());
           stmt.setInt(6, emprestimo.getParcelasPagas());
           stmt.setDate(7, java.sql.Date.valueOf(emprestimo.getDataEmprestimo()));
           stmt.setInt(8, emprestimo.getCliente().getId());
           
           stmt.executeUpdate();
           System.out.println("Empréstimo registrado com sucesso no banco!");
        } catch (SQLException e) {
            System.out.println("Erro ao registrar empréstimo: " + e.getMessage());
        }
    }

    public List<Emprestimo> listarEmprestimos() {

        String sql = "SELECT e.*, c.nome, c.cpf, c.telefone FROM emprestimos e INNER JOIN clientes c ON e.cliente_id = c.id";
        List<Emprestimo> lista = new ArrayList<>();

        try (Connection conn = ConexaoBanco.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs =  stmt.executeQuery();) {

                while (rs.next()) {
                    Emprestimo e = new Emprestimo();

                    e.setId(rs.getInt("id"));
                    e.setValorPuro(rs.getDouble("valor_puro"));
                    e.setTaxaAplicada(rs.getDouble("taxa_aplicada"));
                    e.setValorTotalJuros(rs.getDouble("valor_total_juros"));
                    e.setSaldoDevedor(rs.getDouble("saldo_devedor"));
                    e.setTotalParcelas(rs.getInt("total_parcelas"));
                    e.setParcelasPagas(rs.getInt("parcelas_pagas"));

                    if (rs.getDate("data_emprestimo") != null) {
                        e.setDataEmprestimo(rs.getDate("data_emprestimo").toLocalDate());
                    }
                    
                    Cliente clienteDono = new Cliente();
                    clienteDono.setId(rs.getInt("cliente_id"));
                    clienteDono.setNome(rs.getString("nome"));
                    clienteDono.setCpf(rs.getString("cpf"));
                    clienteDono.setTelefone(rs.getString("telefone"));

                    e.setCliente(clienteDono);

                    lista.add(e);

                }
            } catch (SQLException e) {
                System.out.println("Erro ao listar Empréstimos" + e.getMessage());
            }

            return lista;
    }

    public void registrarPagamento(int emprestimoId, double novoSaldo, int novasParcelasPagas) {

        String sql = "UPDATE emprestimos SET saldo_devedor = ?, parcelas_pagas = ? WHERE id = ?";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setDouble(1, novoSaldo);
                stmt.setDouble(2, novasParcelasPagas);
                stmt.setInt(3, emprestimoId);

                stmt.executeUpdate();
                System.out.println("Pagamento registrado com sucesso no banco!\n");
             } catch (SQLException e) {

                System.out.println("Erro ao registrar o pagamento: " + e.getMessage());
             }
    }

    public Emprestimo buscarPorId (int id) {

        String sql = "SELECT * FROM emprestimos WHERE id = ?";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);) {

                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {

                    if (rs.next()) {

                        Emprestimo e = new Emprestimo();
                        e.setId(rs.getInt("id"));
                        e.setSaldoDevedor(rs.getDouble("saldo_devedor"));
                        e.setParcelasPagas(rs.getInt("parcelas_pagas"));
                        e.setValorTotalJuros(rs.getDouble("valor_total_juros"));
                        e.setTotalParcelas(rs.getInt("total_parcelas"));

                        return e;
                    }
                }
             } catch (SQLException e) {
                System.out.println("Erro ao buscar Empréstimo: " + e.getMessage());
             }
             return null;
    }
}
