import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
           stmt.setInt(8, emprestimo.getClienteId());
           
           stmt.executeUpdate();
           System.out.println("Empréstimo registrado com sucesso no banco!");
        } catch (SQLException e) {
            System.out.println("Erro ao registrar empréstimo: " + e.getMessage());
        }
    }
}
