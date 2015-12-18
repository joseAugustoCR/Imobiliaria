
public class Funcionario extends Pessoa implements Registra, GetInfoFuncionario{
	private float salario;
	private int codigo;
	
	Funcionario(String nome, int id, int fone, Endereco end, float sal, int cod) {
		super(nome, id, fone, end);
		salario = sal;
		codigo=cod;
	}
	Funcionario(String nome, int id, int fone, float sal, int cod) {
		super(nome, id, fone, new Endereco("aaa",50,"adsada"));
		salario = sal;
		codigo=cod;
	}
	Funcionario(String nome, int cod) {
		super(nome, 0, 0, new Endereco("0",0,"0"));
		salario = 0;
		codigo=cod;
	}
	Funcionario() {
		super(" ", 0, 0, new Endereco("0",0,"0"));
		salario = 0;
		codigo=0;
	}
	
	public String toString() {
		return super.toString() + "Salario: " + salario + "   Código:"+codigo+ "\n";
	}
	

	public float getSalario() {
		return salario;
	}
	
	public int getCodigo() {
		return codigo;
	}
	

	@Override
	public String stringToSave() 
	{
		Endereco end = super.getEndereco();
		String string = codigo+"|"+super.getNome()+"|"+super.getId()+"|"+end.stringToSave()+"|"+salario+"|"+ super.getFone() +"\n";
		return string;
	}
}
