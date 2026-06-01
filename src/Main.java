
public class Main {

	public static void main(String[] args) {
		
		Clientes novoCliente = new Clientes(
				0, "João da silva", "123.456.789-00",
				"(11) 99999-8888", "Centro", "Rua das Flores",
				"150", "Ap 42");
		
		ClienteDAO clienteDao = new ClienteDAO();
		
		System.out.println("Iniciando o cadastro do cliente...");
		clienteDao.cadastrarCliente(novoCliente);
		System.out.println("Processo finalizado!");

	}

}
