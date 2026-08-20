import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Locale;

public class Main {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		teclado.useLocale(Locale.US);
		ClienteDAO clienteDao = new ClienteDAO();
        int opcao = -1; 
		
		do {
			System.out.println("=== MENU PRINCIPAL ===");
			System.out.println("1- Cadastrar novo Cliente");
			System.out.println("2- Listar Clientes do banco");
			System.out.println("3- Lançar Empréstimo para Cliente");
			System.out.println("4- Listar Empréstimos do banco");
			System.out.println("5- Registrar Pagamento de Empréstimo");
			System.out.println("6- Buscar cliente por cpf");
			System.out.println("7- Excluir cliente do sistema");
			System.out.println("0- Sair do programa.");

			opcao = teclado.nextInt();
			teclado.nextLine();

			switch (opcao) {
                case 1 -> {
                    List<Cliente> listaTemporaria = new ArrayList<>();
	
					System.out.println("--- SISTEMA DE CADASTRO EM LOTE ---");
		
					String continuar = "S";
		
					while (continuar.equalsIgnoreCase("S")) {
			
						System.out.println("\nDigite os dados do novo cliente: ");
			
						System.out.println("Nome: ");
						String nome = teclado.nextLine();
			
						System.out.println("CPF: ");
						String cpf = teclado.nextLine();
			

						System.out.println("Telefone: ");
						String telefone = teclado.nextLine();
			

						System.out.println("Bairro: ");
						String bairro = teclado.nextLine();
			

						System.out.println("Rua: ");
						String rua = teclado.nextLine();
			

						System.out.println("Número: ");
						String numero = teclado.nextLine();
			

						System.out.println("complemento: ");
						String complemento = teclado.nextLine();
			
						Cliente c = new Cliente(0, nome, cpf, telefone, bairro,
						rua, numero, complemento);
			
						listaTemporaria.add(c);
			
						System.out.println("\nDeseja cadastrar mais um cliente? (S/N)");
						continuar = teclado.nextLine();
					}
		
					System.out.println("\nTotal de clientes na lista: " + listaTemporaria.size());
					System.out.println("Enviando todos para o MySQL de uma vez só...");
		
		
					for (Cliente clienteDaVez : listaTemporaria) {
			
						System.out.println("Salvando no banco: " + clienteDaVez.getNome());
						clienteDao.cadastrarCliente(clienteDaVez);
					}
		
					System.out.println("\nProcesso finalizado com sucesso!");
				}
				case 2 -> {

					System.out.println("=== Consultando Clientes ===");

					List<Cliente> todosClientes = clienteDao.listarClientes();

					System.out.println("Clientes encontrados no banco: " + todosClientes.size() + "\n");

					for(Cliente c: todosClientes) {

						System.out.println("ID: " + c.getId());
						System.out.println("Nome: " + c.getNome());
						System.out.println("CPF: " + c.getCpf());
						System.out.println("Telefone: " + c.getTelefone());
						System.out.println("Endereço: " + c.getRua() + ", N° " + c.getNumero() + " - " + c.getBairro());
						System.out.println("----------------------------------------------\n");
					}
				}
				case 3 -> {
                    Cliente clienteDono = new Cliente();

                    System.out.println("=== LANÇAR NOVO EMPRÉSTIMO ===");

					System.out.println("Digite o ID do Cliente dono do empréstimo: ");
                    int clienteId = teclado.nextInt();
					clienteDono.setId(clienteId); 
					
					System.out.println("Digite o valor solicitado (Puro): R$ ");
					double valorPuro = teclado.nextDouble();

					System.out.println("Digite o total de parcelas: ");
					int totalParcelas = teclado.nextInt();
					teclado.nextLine();

					double taxaJuros = 0.10;
					double valorTotalJuros = valorPuro + (valorPuro * taxaJuros);
					double saldoDevedor = valorTotalJuros;
					int parcelasPagas = 0;
					double valorParcela = valorTotalJuros / totalParcelas;
					java.time.LocalDate dataHoje = java.time.LocalDate.now();

					Emprestimo novoEmprestimo = new Emprestimo(0, valorPuro, taxaJuros * 100, valorTotalJuros, saldoDevedor, totalParcelas, parcelasPagas, valorParcela, dataHoje, clienteDono);

					EmprestimoDAO emprestimoDao = new EmprestimoDAO();
					emprestimoDao.cadastrarEmprestimo(novoEmprestimo);

				}
				case 4 -> {
					System.out.println("=== LISTAGEM DE EMPRÉSTIMOS ===");

					EmprestimoDAO emprestimoDao = new EmprestimoDAO();

					List<Emprestimo> todosEmprestimos = emprestimoDao.listarEmprestimos();

					System.out.println("Empréstimos encontrados: " + todosEmprestimos.size() + "\n");

					for (Emprestimo e : todosEmprestimos) {

						System.out.println("ID do Empréstimo: " + e.getId());
						System.out.println("ID do Cliente Dono: " + e.getCliente().getId());
						System.out.println("Valor solicitado (Puro): R$ " + e.getValorPuro());
						System.out.println("Taxa Aplicada: " + e.getTaxaAplicada());
						System.out.println("Total a pagar (com Juros): R$ " + e.getValorTotalJuros());
						System.out.println("Saldo Devedor Atual: R$ " + e.getSaldoDevedor());
						System.out.println("Parcelas: " + e.getParcelasPagas() + " pagas de "+ e.getTotalParcelas());
						System.out.println("Data do Contrato: " + e.getDataEmprestimo());
						System.out.println("------------------------------------------");
					}
				}
				case 5 -> {
					System.out.println("=== REGISTRAR PAGAMENTO ===");

					System.out.println("Digite o ID do Empréstimo: ");
					int empId = teclado.nextInt();
					teclado.nextLine();

					EmprestimoDAO empDao = new EmprestimoDAO();

					Emprestimo empAtual = empDao.buscarPorId(empId);

					if (empAtual != null) {

						System.out.println("Empréstimo encontrado!");
						System.out.println("Saldo devedor atual: R$ " + empAtual.getSaldoDevedor());
						System.out.println("Parcelas pagas: " + empAtual.getParcelasPagas() + " de " + empAtual.getTotalParcelas());

						System.out.println("\nDigite o valor do pagamento: R$ ");
						double valorPago = teclado.nextDouble();
						teclado.nextLine();

						if (valorPago <= 0) {
							System.out.println("Erro: O valor do pagamento deve ser maior que zero.\n");
						} else {

						double novoSaldo = empAtual.getSaldoDevedor() - valorPago;
						if (novoSaldo < 0) novoSaldo = 0;

						double totalJaPago = empAtual.getValorTotalJuros() - novoSaldo;

						int novasParcelasPagas = (int) (totalJaPago / empAtual.getValorParcela());

						if (novasParcelasPagas > empAtual.getTotalParcelas()) {
							novasParcelasPagas = empAtual.getTotalParcelas();
						} 

						empDao.registrarPagamento(empId, novoSaldo, novasParcelasPagas);

						System.out.printf("Pagamento de R$ %.2f registrado!\n", valorPago);
                        System.out.printf("Novo Saldo Devedor: R$ %.2f\n", novoSaldo);
						System.out.println("Parcelas totalmente quitadas até agora: " + novasParcelasPagas + "\n");
					}  
						
					
				} else {
					System.out.println("Empréstimo com ID: " + empId + " não foi encontrado no sistema.\n");
				 }
			    }
				case 6 -> {
					System.out.println("=== BUSCAR CLIENTE POR CPF ===");

					System.out.println("Digite o CPF desejado (somente números ou formatado): ");
					String cpfBusca = teclado.nextLine();

					Cliente clienteEncontrado = clienteDao.buscaPorCpf(cpfBusca);

					if (clienteEncontrado != null) {

						System.out.println("\n=========================================\n");
					    System.out.println("	FICHA CADASTRAL DO CLIENTE        ");
						System.out.println("\n=========================================");
						System.out.println("ID do Sistema: " + clienteEncontrado.getId());
						System.out.println("Nome Completo: " + clienteEncontrado.getNome());
						System.out.println("CPF: " + clienteEncontrado.getCpf());
						System.out.println("Celular/Tel: " + clienteEncontrado.getTelefone());
						System.out.println("------------------------------------------");
						System.out.println("ENDEREÇO:");
						System.out.println("Rua: " + clienteEncontrado.getRua());
						System.out.println("Bairro: " + clienteEncontrado.getBairro());
						System.out.println("Complemento: " + clienteEncontrado.getComplemento() + "\n");
					} else {
						System.out.println("Nenhum Cliente cadatrado com CPF: " + cpfBusca + "\n");
					}
				}
				case 7 -> {
					System.out.println("=== EXCLUIR CLIENTE ===");

					System.out.println("Digite o ID do cliente que deseja remover: ");
					int idCliente = teclado.nextInt();
					teclado.nextLine();

					EmprestimoDAO empDaoChecagem = new EmprestimoDAO();
					boolean temVinculo = empDaoChecagem.clientePossuiEmprestimo(idCliente);

					if (temVinculo) {
						System.out.println("\nErro de Segurança: Não é possivel deletar este cliente.");
						System.out.println("Motivo: O cliente possui empréstimos ativos no histórico.");
						System.out.println("Dica: Liquide ou remova os empréstimos antes de excluir o cadastro do cliente.\n");
					} else {
						System.out.println("Tem certeza que deseja excluir permanentemente este cliente? (S/N): ");
						String confirmacao = teclado.nextLine();

						if (confirmacao.equalsIgnoreCase("S")) {
							clienteDao.deletarCliente(idCliente);
						} else {
							System.out.println("Operação de exclusão cancelada pelo usuário.\n");
						}
					}

				}
				case 0 -> System.out.println("Encerrando o Programa. Até mais!");
				default -> System.out.println("Opção inválida! tente novamente.");   

			}
		} while (opcao != 0);
		
    teclado.close(); 
		
		
		
	}

}
