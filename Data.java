
public class Data implements Registra {

	private int dia, mes, ano;
	
	public int getDia() {
		return dia;
	}

	public int getMes() {
		return mes;
	}

	public int getAno() {
		return ano;
	}
	
	public void setAno(int ano) {
		this.ano = ano;
	}

	public Data() {
		dia = 0;
		mes = 0;
		ano = 0;
	}
	
	public Data(int dia, int mes, int ano) {
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	}
	
	public Data(Data data) {
		this.dia = data.getDia();
		this.mes = data.getMes();
		this.ano = data.getAno();
	}
	
	public String toString() {
		return dia + "/" + mes + "/" + ano;
	}
	
	public String stringToSave() {
		return dia + "|" + mes + "|" + ano;
	}
}
