


public class Apartamento extends Imovel implements Registra, GetInfoImovel{
	private String nome;
	private int dormitorios, banheiros, garagem;
	
	Apartamento (float area, Endereco e,float valor,int cod, String n, int d, int b, int g){
		super(area, e, valor, cod);
		nome = n;
		dormitorios = d;
		banheiros = b;
		garagem = g;
	}
	
	Apartamento (float area,float valor, int cod, Endereco e, String n, int d, int b){
		super(area, e, valor, cod);
		nome = n;
		dormitorios = d;
		banheiros = b;
		garagem = 0;
	}
	
	public String getNome(){
		return nome;
	}
	
	public int getDormitorios(){
		return dormitorios;
	}
	
	public int getBanheiros(){
		return banheiros;
	}
	
	public int getGaragem(){
		return garagem;
	}
	
	public String toString() {
		String imprime = "APTO    Edificio: " + nome + " -  Endereco: " + getEndereco();
		imprime +=  "Dormitorios: " + dormitorios + "  Banheiros: " + banheiros + "  Vagas de Garagem: " + garagem;  
		imprime += "\nArea: " + getArea() +"m²\n\n";
		return imprime;
	}

	@Override
	public String stringToSave() {
		Endereco end = super.getEndereco();
		String string = super.getCodigo()+"|"+super.getArea()+"|"+end.stringToSave()+"|"+super.getValor()+"|"+dormitorios+"|"+banheiros+"|"+garagem+"|"+nome+"\n";
		return string;
	}
}
