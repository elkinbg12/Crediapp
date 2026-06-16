import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		ClienteDAO clienteDao = new ClienteDAO();
        int opcao = -1; 
		
		do {
			System.out.println("=== MENU PRINCIPAL ===");
			System.out.println("1- Cadastrar novo Cliente");
			System.out.println("2- Listar Clientes do banco");
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

					System.out.println("=== Consultando CLientes ===");

					List<Cliente> todosClientes = clienteDao.listarClientes();

					System.out.println("Clientes encontrados no banco: " + todosClientes.size() + "\n");

					for(Cliente c: todosClientes) {

						System.out.println("ID: " + c.getId());
						System.out.println("Nome: " + c.getNome());
						System.out.println("CPF: " + c.getCpf());
						System.out.println("Telefone: " + c.getTelefone());
						System.out.println("Endereço: " + c.getRua() + ", N° " + c.getNumero() + " - " + c.getBairro());
						System.out.println("----------------------------------------------");
					}
				}
				case 0 -> System.out.println("Encerrando o Programa. Até mais!");
				default -> System.out.println("Opção inválida! tente novamente.");   

			}
		} while (opcao != 0);
		
    teclado.close(); 
		
		
		
	}

}
