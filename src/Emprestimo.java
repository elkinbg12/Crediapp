import java.time.LocalDate;

public class Emprestimo {

    private int id;
    private double valorPuro;
    private double taxaAplicada;
    private double valorTotalJuros;
    private double saldoDevedor;
    private int totalParcelas;
    private int parcelasPagas;
    private LocalDate dataEmprestimo;
    private Cliente cliente;

    public Emprestimo () {}

    public Emprestimo (int id, double valorPuro, double taxaAplicada, double valorTotalJuros, double saldoDevedor, int totalParcelas, int parcelasPagas, LocalDate dataEmprestimo, Cliente cliente) {

        this.id = id;
        this.valorPuro = valorPuro;
        this.taxaAplicada = taxaAplicada;
        this.valorTotalJuros = valorTotalJuros;
        this.saldoDevedor = saldoDevedor;
        this.totalParcelas = totalParcelas;
        this.parcelasPagas = parcelasPagas;
        this.dataEmprestimo = dataEmprestimo;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValorPuro() {
        return valorPuro;
    }

    public void setValorPuro(double valorPuro) {
        this.valorPuro = valorPuro;
    }

    public double getTaxaAplicada() {
        return taxaAplicada;
    }

    public void setTaxaAplicada(double taxaAplicada) {
        this.taxaAplicada = taxaAplicada;
    }

    public double getValorTotalJuros() {
        return valorTotalJuros;
    }

    public void setValorTotalJuros(double valorTotalJuros) {
        this.valorTotalJuros = valorTotalJuros;
    }

    public double getSaldoDevedor() {
        return saldoDevedor;
    }

    public void setSaldoDevedor(double saldoDevedor) {
        this.saldoDevedor = saldoDevedor;
    }

    public int getTotalParcelas() {
        return totalParcelas;
    }

    public void setTotalParcelas(int totalParcelas) {
        this.totalParcelas = totalParcelas;
    }

    public int getParcelasPagas() {
        return parcelasPagas;
    }

    public void setParcelasPagas(int parcelasPagas) {
        this.parcelasPagas = parcelasPagas;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
}
