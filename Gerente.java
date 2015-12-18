
public class Gerente extends Funcionario implements Registra, GetInfoFuncionario{
	private Data promocaoAGerente;
	
	public Gerente (String nome, int id, int fone, Endereco end, float sal, int cod, Data promo) {
		super(nome, id, fone, end, sal,cod);
		promocaoAGerente = promo;
	}
	
	public Data getDataPromocao() {
		return promocaoAGerente;
	}

	public String toString() {
		return super.toString() + " Data de Promocao: " + promocaoAGerente + "\n";
	}

	@Override
	public String stringToSave() 
	{
		Endereco end = super.getEndereco();
		String string = getCodigo()+"|"+getNome()+"|"+getId()+"|"+end.stringToSave()+"|"+getSalario()+"|"+ getFone() +"|"+promocaoAGerente.stringToSave()+"\n";
		return string;
	}
}
