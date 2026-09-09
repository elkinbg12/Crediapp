package com.elkin.crediapp;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "emprestimos")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal valorPuro;
    private BigDecimal taxaAplicada;
    private BigDecimal valorTotalJuros;
    private BigDecimal saldoDevedor;
    private int totalParcelas;
    private int parcelasPagas;
    private BigDecimal valorParcela;
    private LocalDate dataEmprestimo;

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
