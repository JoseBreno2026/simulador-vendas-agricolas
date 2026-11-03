package agro;

public class Produto {
	String informacoesextras = "";
	private String nome;
    private int quantidade;
    private double preco;
    
    public Produto(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }
    
    public Produto() {
    }
    
    public void setnome(String novonome) {
    	this.nome = novonome;
    }
    
    public String getnome() {
    	return nome;
    }
    
    public void setquantidade(int novaquantidade) {
    	this.quantidade = novaquantidade;
    }
    
    public int getquantidade() {
    	return quantidade;
    }
    
    public void setpreco(double novopreco) {
    	this.preco = novopreco;
    }
    public double getpreco() {
    	return preco;
    }
    
    public String getextra() {

    	return this.informacoesextras;
    }
}