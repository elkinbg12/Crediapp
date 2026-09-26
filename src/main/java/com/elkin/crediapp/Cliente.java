package com.elkin.crediapp;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "O nome é Obrigatório")
	@Column(nullable = false)
	private String nome;

	@NotBlank(message = "O CPF é obrigatório")
	@Size(min = 11, max = 11, message = "O CPF deve conter exatamente 11 dígitos")
	@Column(nullable = false, unique = true)
	private String cpf;

	@NotBlank(message = "O telefone é obrigatório")
	@Column(nullable = false)
	private String telefone;

	@NotBlank(message = "O bairro é obrigatório")
	@Column(nullable = false)
	private String bairro;

	@NotBlank(message = "A rua é obrigatória")
	@Column(nullable = false)
	private String rua;

	@NotBlank(message = "O número é obrigatório")
	@Column(nullable = false)
	private String numero;

	private String complemento;
	
	public Cliente() {}
	
	Cliente(Integer id, String nome, String cpf, String telefone, String bairro,
			String rua, String numero, String complemento){
		
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
	}

	public int getId() {
		return id;
	}
	
	public void setId(Integer id) {
		
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	
	
	
}