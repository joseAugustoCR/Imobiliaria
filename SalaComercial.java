
public class SalaComercial extends Imovel implements Registra, GetInfoImovel{
	private int  banheiros, garagem;

	SalaComercial (float area, Endereco e,float valor, int cod, int b, int g){
		super(area, e, valor, cod);
		banheiros = b;
		garagem = g;
	}
	
	public int getBanheiros(){
		return banheiros;
	}
	
	public int getGaragem(){
		return garagem;
	}
	
	public String toString() {
		String imprime = " "+ super.getArea(); 
		imprime +=  "   Banheiros: " + banheiros + "   Vagas de Garagem: " + garagem;  
		imprime += "\nArea: " + getArea() +"m²\n\n";
		return imprime;
	}

	@Override
	public String stringToSave() {
		Endereco end = super.getEndereco();
		String string = super.getCodigo()+"|"+super.getArea()+"|"+end.stringToSave()+"|"+super.getValor()+"|"+banheiros+"|"+garagem+"\n";
		return string;
	}
}
