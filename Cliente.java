
public class Cliente extends Pessoa implements Registra{
	private int codigo;
	
	Cliente(String nome, int id, int fone, Endereco end, int nCad){
		super(nome, id, fone, end);
		codigo = nCad;
	}
	
	public int getCodigo (){
		return codigo;
	}
	
	public String toString() {
		return super.toString() + "Numero do Cadastro: " + codigo + "\n";
	}
	
	public String stringToSave() 
	{
		Endereco end = super.getEndereco();
		String string = codigo+"|"+super.getNome()+"|"+super.getId()+"|"+end.stringToSave()+"|"+ super.getFone() +"\n";
		return string;
	}
}
