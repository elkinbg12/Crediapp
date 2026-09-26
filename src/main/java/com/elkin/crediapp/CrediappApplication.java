package com.elkin.crediapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class CrediappApplication {

    public static void main(String[] args) {

        // Inicializa o contêiner do Spring, injeta dependências e testa a conexão com o banco
        SpringApplication.run(CrediappApplication.class, args);

    }

    @Bean
    public CommandLineRunner executarTestes(ClienteService clienteService, EmprestimoService emprestimoService) {
        return args -> {
            System.out.println("\n==================================");
            System.out.println(" INICIANDO TESTES DA CAMADA SERVICE ");
            System.out.println("\n==================================");

            //1. Criar e salvar um novo cliente
            Cliente novoCliente = new Cliente();
            novoCliente.setNome("Alex Cacarriaca");
            novoCliente.setCpf("91205576576");
            novoCliente.setTelefone("69 993231122");
            novoCliente.setBairro("Centro");
            novoCliente.setRua("Rua das Rosas");
            novoCliente.setNumero("3562");
            novoCliente.setComplemento("----");

            //Salva no banco via Service
            Cliente clienteSalvo = clienteService.salvar(novoCliente);
            System.out.println("Cliente cadastrado com sucesso! ID: " + clienteSalvo.getId() + " | NOME: " + clienteSalvo.getNome());

            //2. Criar e salvar um Empréstimo associado a esse Cliente
            Emprestimo novoEmprestimo = new Emprestimo();
            novoEmprestimo.setCliente(clienteSalvo);
            novoEmprestimo.setValorPuro(new BigDecimal("1500.00"));
            novoEmprestimo.setTaxaAplicada(new BigDecimal("20.00"));
            novoEmprestimo.setValorTotalJuros(new BigDecimal("1800.00"));
            novoEmprestimo.setSaldoDevedor(new BigDecimal("1800.00"));
            novoEmprestimo.setTotalParcelas(20);
            novoEmprestimo.setParcelasPagas(0);
            novoEmprestimo.setValorParcela(new BigDecimal("90.00"));
            novoEmprestimo.setDataEmprestimo(LocalDate.now());

            Emprestimo emprestimoSalvo = emprestimoService.criarEmprestimo(novoEmprestimo);
            System.out.println("-> Empréstimo Cadastrado! ID: " + emprestimoSalvo.getId()
            + " Valor: R$ " + emprestimoSalvo.getValorPuro() + " Saldo Devedor: R$ "
            + emprestimoSalvo.getSaldoDevedor());

            System.out.println("\n--- Lista de Clientes no banco ---");
            List<Cliente> clientes = clienteService.listarTodos();
            for (Cliente c : clientes) {
                System.out.println("ID: " + c.getId() + " Nome: " + c.getNome() + " CPF: " + c.getCpf());
            }

            System.out.println("\n==================================");
            System.out.println("TESTES CONCLUÍDOS COM SUCESSO ");
            System.out.println("\n==================================");


        };
    }

}
