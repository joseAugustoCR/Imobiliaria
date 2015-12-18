
public class ImovelAlugado extends Imovel implements Registra{
	private Data dataAlugado, dataVencimento;
	private Cliente cliente;
	
	ImovelAlugado(float a, Endereco e, float v, int cod, Data data, Cliente c) {
		super(a,e,v,cod);
		dataAlugado=data;
		cliente =c;
		if (dataAlugado.getMes() <12)
		{
			dataVencimento = new Data(dataAlugado.getDia(), dataAlugado.getMes()+1, dataAlugado.getAno());	
		}
		else
		{
			dataVencimento = new Data(dataAlugado.getDia(), 1, dataAlugado.getAno()+1);	
		}
	}
	
	public Data ajustaDataVencimento()
	{
		if (dataVencimento.getMes() <12)
		{
			dataVencimento = new Data(dataAlugado.getDia(), dataAlugado.getMes()+1, dataAlugado.getAno());	
		}
		else
		{
			dataVencimento = new Data(dataAlugado.getDia(), 1, dataAlugado.getAno()+1);	
		}
		return dataVencimento;
	}
	
	public Cliente getCliente()
	{
		return cliente;
	}
	
	public Data getDataVencimento()
	{
		return dataVencimento;
	}
	
	@Override
	public String stringToSave() {
		String string = super.getCodigo()+"|"+super.getArea()+"|"+super.getEndereco().stringToSave()+"|"+super.getValor()+"|"+dataAlugado.stringToSave()+
				"|" +cliente.getCodigo()+"\n";
		return string;
	}

}
