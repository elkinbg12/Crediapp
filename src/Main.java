import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in); 
		List<Clientes> listaTemporaria = new ArrayList<>();
		
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
			
			Clientes c = new Clientes(0, nome, cpf, telefone, bairro,
					rua, numero, complemento);
			
			listaTemporaria.add(c);
			
			System.out.println("\nDeseja cadastrar mais um cliente? (S/N)");
			continuar = teclado.nextLine();
		}
		
		System.out.println("\nTotal de clientes na lista: " + listaTemporaria.size());
		System.out.println("Enviando todos para o MySQL de uma vez só...");
		
		ClienteDAO clienteDao = new ClienteDAO();
		
		for (Clientes clienteDaVez : listaTemporaria) {
			
			System.out.println("Salvando no banco: " + clienteDaVez.getNome());
			clienteDao.cadastrarCliente(clienteDaVez);
		}
		
		System.out.println("\nProcesso finalizado com sucesso!");
		teclado.close();
		
	}

}
