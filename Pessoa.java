public class Pessoa {
	private String nome;
	private int identidade;
	//private Data nascimento;
	private int fone;
	private Endereco end;
	
	
	public Pessoa(String n, int i, int f, Endereco e) {
		nome = n;
		identidade = i;
		fone = f;
		end = e;
	}
	
	public String getNome(){
		return nome;
	}
	
	public int getId(){
		return identidade;
	}
	
	public int getFone(){
		return fone;
	}
	
	public Endereco getEndereco()
	{
		return end;
	}
	
	public String toString() {
		return "Nome: " + nome + "\nID: " + identidade + "    Fone: " + fone + "\nEndereco: " + end;
	}
}
