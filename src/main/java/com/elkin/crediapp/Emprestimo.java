package com.elkin.crediapp;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "emprestimos")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "O valor puro do empréstimo é obrigatório")
    @Column(nullable = false)
    private BigDecimal valorPuro;

    @NotNull
    @Column(nullable = false)
    private BigDecimal taxaAplicada;

    @NotNull(message = "O valor total de juros é obrigatório")
    @Column(nullable = false)
    private BigDecimal valorTotalJuros;

    @NotNull(message = "O saldo devedor é obrigatório")
    @Column(nullable = false)
    private BigDecimal saldoDevedor;

    @NotNull(message = "O total de parcelas é obrigatório")
    @Column(nullable = false)
    private int totalParcelas;


    private int parcelasPagas;

    @NotNull(message = "O valor da parcela é obrigatório")
    @Column(nullable = false)
    private BigDecimal valorParcela;

    @NotNull(message = "A data do empréstimo é obrigatória")
    private LocalDate dataEmprestimo;

    @NotNull(message = "O empréstimo deve estar vinculado a um cliente")
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Emprestimo () {}

    public Emprestimo (Integer id, BigDecimal valorPuro,
                       BigDecimal taxaAplicada, BigDecimal valorTotalJuros, BigDecimal saldoDevedor, int totalParcelas, int parcelasPagas, BigDecimal valorParcela, LocalDate dataEmprestimo, Cliente cliente) {

        this.id = id;
        this.valorPuro = valorPuro;
        this.taxaAplicada = taxaAplicada;
        this.valorTotalJuros = valorTotalJuros;
        this.saldoDevedor = saldoDevedor;
        this.totalParcelas = totalParcelas;
        this.parcelasPagas = parcelasPagas;
        this.valorParcela = valorParcela;
        this.dataEmprestimo = dataEmprestimo;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValorPuro() {
        return valorPuro;
    }

    public void setValorPuro(BigDecimal valorPuro) {
        this.valorPuro = valorPuro;
    }

    public BigDecimal getTaxaAplicada() {
        return taxaAplicada;
    }

    public void setTaxaAplicada(BigDecimal taxaAplicada) {
        this.taxaAplicada = taxaAplicada;
    }

    public BigDecimal getValorTotalJuros() {
        return valorTotalJuros;
    }

    public void setValorTotalJuros(BigDecimal valorTotalJuros) {
        this.valorTotalJuros = valorTotalJuros;
    }

    public BigDecimal getSaldoDevedor() {
        return saldoDevedor;
    }

    public void setSaldoDevedor(BigDecimal saldoDevedor) {
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

    public BigDecimal getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela (BigDecimal valorParcela) {
        this.valorParcela = valorParcela;
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
