package agro;

public class Cliente {
     public String nome,cpf,endereco,login,senha;
	 private int idade;
	 
	 public Cliente(String nome,String cpf,String login,String senha,String endereco, int idade) {
		 this.nome = nome;
		 this.idade = idade;
		 this.cpf = cpf;
		 this.login = login;
		 this.senha = senha;
		 this.endereco = endereco;
	 }
	 
	 public Cliente () {
		 
	 }
	 
	 public String getnome() {
		 return this.nome;
	 }
	 
	 public String getcpf() {
		 return this.cpf;
	 }
	 
	 public String getlogin() {
		 return this.login;
	 }
	 
	 public String getsenha() {
		 return this.senha;
	 }
	 
	 public String getendereco() {
		 return this.endereco;
	 }
	 
	 public int getidade() {
		 return this.idade;
	 }
}