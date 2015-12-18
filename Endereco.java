


public class Endereco implements Registra {
	private String rua, bairro;
	private int num;
	
	Endereco (String r, int n, String b)
	{
		rua = r;
		num = n;
		bairro = b;
		
	}

	Endereco ()
	{
		rua = " ";
		num = 0;
		bairro = " ";
		
	}
	
	public String toString(){
		return "Rua "+rua + ", " + num + ". Bairro " + bairro +".\n";
	}


	@Override
	public String stringToSave() {
		String string = rua+"|"+num+"|"+bairro;
		return string;
	}
}
