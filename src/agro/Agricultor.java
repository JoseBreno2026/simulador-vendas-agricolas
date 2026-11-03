package agro;

public class Agricultor {
   String nome;
   int idade;
   String empresa;
   String cnpj;
   String login;
   String senha;
   
public Agricultor(String nome, String login, String senha, String empresa, String cnpj, int idade) {
	this.nome = nome;
	this.idade = idade;
	this.empresa = empresa;
	this.cnpj = cnpj;
	this.login = login;
	this.senha = senha;
}

public Agricultor() {
	
}

public String getnome() {
	return this.nome;
}

public int getidade() {
	return this.idade;
}

public String getempresa() {
	return this.empresa;
}

public String getcnpj() {
	return this.cnpj;
}

public String getlogin() {
	return this.login;
}

public String getsenha() {
	return this.senha;
 }
}
