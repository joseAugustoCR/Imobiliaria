


public class Casa extends Imovel implements Registra, GetInfoImovel{
	private int dormitorios, banheiros, garagem;
	
	Casa (float area, Endereco e,float valor, int cod, int d, int b, int g){
		super(area, e, valor, cod);
		dormitorios = d;
		banheiros = b;
		garagem = g;
	}
	
	Casa (float area, Endereco e, int cod, float valor, int d, int b){
		super(area, e, valor, cod);
		dormitorios = d;
		banheiros = b;
		garagem = 0;
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
		String imprime =  "CASA      Endereco: " + getEndereco()+"   -Dormitorios: " + dormitorios + "   Banheiros: " + banheiros + "   Vagas de Garagem: " + garagem;  
		imprime += "\nArea: " + getArea() +"m²"+"   Valor: "+super.getValor()+"\n\n";
		return imprime;
	}




	@Override
	public String stringToSave() {
		Endereco end = super.getEndereco();
		String string = super.getCodigo()+"|"+super.getArea()+"|"+end.stringToSave()+"|"+super.getValor()+"|"+dormitorios+"|"+banheiros+"|"+garagem+"\n";
		return string;
	}
}
