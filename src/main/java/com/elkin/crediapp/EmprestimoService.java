package com.elkin.crediapp;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;


    //Injeção de dependências via construtor
    public EmprestimoService(EmprestimoRepository emprestimoRepository, ClienteRepository clienteRepository) {

        this.emprestimoRepository = emprestimoRepository;
    }

    @Transactional(readOnly = true)
    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }

    @Transactional
    public Emprestimo criarEmprestimo(Emprestimo emprestimo) {
        // Exemplo de regra de negócio: garantir que o saldo devedor inicial seja o valor puro
        if (emprestimo.getSaldoDevedor() == null) {
            emprestimo.setSaldoDevedor(emprestimo.getValorTotalJuros());
        }
        return emprestimoRepository.save(emprestimo);
    }
}
