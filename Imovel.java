
public class Imovel implements Registra {
	private float area;
	private Endereco end;
	private float valor;
	private int codigo;
	
	Imovel (float a, Endereco e, float v, int cod){
		area =a;
		end = e;
		valor = v;
		codigo =cod;
	}
	public float getValor(){
		return valor;
	}
	
	public float getArea(){
		return area;
	}
	
	
	public int getCodigo(){
		return codigo;
	}
	
	public Endereco getEndereco()
	{
		return end;
	}
	@Override
	public String stringToSave() {
		String string = codigo+"|"+area+"|"+end.stringToSave()+"|"+valor+"\n";
		return string;
	}
	
	
	
}
