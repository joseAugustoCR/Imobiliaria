import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Classe para tratar de questões de arquivamento de informações
 * @author José Augusto
 *
 */
public class Arquivos {
	private HashSet<Funcionario> funcionarios = new HashSet<Funcionario>();
	private HashSet<Gerente> gerentes = new HashSet<Gerente>();
	private HashSet<Cliente> clientes = new HashSet<Cliente>();
	private HashSet<Apartamento> apartamentos= new HashSet<Apartamento>();
	private HashSet<Casa> casas= new HashSet<Casa>();
	private HashSet<SalaComercial> salas = new HashSet<SalaComercial>();
	private HashSet<ImovelAlugado> alugados = new HashSet<ImovelAlugado>();


	/**
	 * Escreve em arquivo de texto
	 * @param arquivo
	 * @param string
	 * @param adicionaAoArquivo
	 * @throws IOException
	 */
	private void escreveNoArquivo(String arquivo, String string, boolean adicionaAoArquivo) throws IOException //private
	{
		BufferedWriter writer = null;

		try
		{	
			writer = new BufferedWriter( new FileWriter(arquivo, adicionaAoArquivo)); // acrescenta valores ao arquivo
			writer.write(string);	
			writer.close( );
		}
		catch ( IOException e)
		{
		}
	}

	/**
	 * Salva apartamento na lista e no arquivo
	 * @param i
	 */
	public void salvaApartamento(Apartamento i)
	{
		apartamentos.add(i);
		String string=i.stringToSave();
		try {
			escreveNoArquivo("Banco de Dados/Imoveis Disponiveis/Apartamentos.txt", string, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Salva casa na lista e arquivo
	 * @param i
	 */
	public void salvaCasa(Casa i)
	{
		casas.add(i);
		try {
			escreveNoArquivo("Banco de Dados/Imoveis Disponiveis/Casas.txt", i.stringToSave(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Salva sala comercial na lista e arquivo
	 * @param i
	 */
	public void salvaSalaComercial(SalaComercial i)
	{
		salas.add(i);
		try {
			escreveNoArquivo("Banco de Dados/Imoveis Disponiveis/Salas.txt", i.stringToSave(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Salva funcionário na lista e arquivo
	 * @param f
	 */
	public void salvaFuncionario(Funcionario f)
	{
		funcionarios.add(f);	
		String string=f.stringToSave();
		try {
			escreveNoArquivo("Banco de Dados/Funcionarios/Funcionarios.txt", (string), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Salva cliente na lista e arquivo
	 * @param f
	 */
	public void salvaCliente(Cliente f)
	{
		clientes.add(f);	
		String string=f.stringToSave();
		try {
			escreveNoArquivo("Banco de Dados/Clientes/Clientes.txt", (string), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Salva gerente na lista e arquivo
	 * @param g
	 */
	public void salvaGerente(Gerente g)
	{
		gerentes.add(g);	
		String string=g.stringToSave();
		try {
			escreveNoArquivo("Banco de Dados/Funcionarios/Gerentes.txt", (string), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Carrega os dados relacionados aos clientes e imóveis
	 */
	public void carregaDados()
	{
		carregaApartamentos();
		carregaClientes();
		carregaCasas();
		carregaSalas();
		carregaAlugados();
	}

	/**
	 * Carrega funcionários e gerentes
	 */
	public void carregaDadosFuncionarios()
	{
		carregaFuncionarios();
		carregaGerentes();
	}

	/**
	 * Carrega apartamentos
	 */
	private void carregaApartamentos()
	{
		Scanner scanner = null;
		try 
		{
			scanner = new Scanner(new FileReader("Banco de Dados/Imoveis Disponiveis/Apartamentos.txt")).useDelimiter("\\||\\n");
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		while (scanner.hasNext()) 
		{
			String nome, rua, bairro;
			float valor, area;
			int num, cod, dormitorios, banheiros, garagem;

			cod =Integer.parseInt( scanner.next());
			area = Float.parseFloat(scanner.next());
			rua = scanner.next();
			num = Integer.parseInt(scanner.next());
			bairro = scanner.next();
			valor = Float.parseFloat(scanner.next());
			dormitorios = Integer.parseInt( scanner.next());
			banheiros = Integer.parseInt( scanner.next());
			garagem = Integer.parseInt( scanner.next());
			nome = scanner.next();

			Apartamento a = new Apartamento (area, new Endereco(rua,num,bairro), valor, cod, nome, dormitorios, banheiros, garagem);
			apartamentos.add(a);	
		}
	}

	/**
	 * Carrega casas
	 */
	private void carregaCasas()
	{
		Scanner scanner = null;
		try 
		{
			scanner = new Scanner(new FileReader("Banco de Dados/Imoveis Disponiveis/Casas.txt")).useDelimiter("\\||\\n");
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		while (scanner.hasNext()) 
		{
			String rua, bairro;
			float valor, area;
			int num, cod, dormitorios, banheiros, garagem;

			cod =Integer.parseInt( scanner.next());
			area = Float.parseFloat(scanner.next());
			rua = scanner.next();
			num = Integer.parseInt(scanner.next());
			bairro = scanner.next();
			valor = Float.parseFloat(scanner.next());
			dormitorios = Integer.parseInt( scanner.next());
			banheiros = Integer.parseInt( scanner.next());
			garagem = Integer.parseInt( scanner.next());

			Casa a = new Casa (area, new Endereco(rua,num,bairro), valor, cod,  dormitorios, banheiros, garagem);
			casas.add(a);	
		}
	}

	/**
	 * Carrega salas
	 */
	private void carregaSalas()
	{
		Scanner scanner = null;
		try 
		{
			scanner = new Scanner(new FileReader("Banco de Dados/Imoveis Disponiveis/Salas.txt")).useDelimiter("\\||\\n");
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		while (scanner.hasNext()) 
		{
			String rua, bairro;
			float valor, area;
			int num, cod, banheiros, garagem;

			cod =Integer.parseInt( scanner.next());
			area = Float.parseFloat(scanner.next());
			rua = scanner.next();
			num = Integer.parseInt(scanner.next());
			bairro = scanner.next();
			valor = Float.parseFloat(scanner.next());
			banheiros = Integer.parseInt( scanner.next());
			garagem = Integer.parseInt( scanner.next());

			SalaComercial a = new SalaComercial (area, new Endereco(rua,num,bairro), valor, cod,  banheiros, garagem);
			salas.add(a);	
		}
	}

	/**
	 * Carrega imóveis alugados
	 */
	private void carregaAlugados()
	{
		Scanner scanner = null;
		try 
		{
			scanner = new Scanner(new FileReader("Banco de Dados/Imoveis Alugados/Alugados.txt")).useDelimiter("\\||\\n");
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		while (scanner.hasNext()) 
		{
			String rua, bairro,codCliente;
			float valor, area;
			int num, cod, dia, mes, ano;

			cod =Integer.parseInt( scanner.next());
			area = Float.parseFloat(scanner.next());
			rua = scanner.next();
			num = Integer.parseInt(scanner.next());
			bairro = scanner.next();
			valor = Float.parseFloat(scanner.next());
			dia=Integer.parseInt(scanner.next());
			mes=Integer.parseInt(scanner.next());
			ano=Integer.parseInt(scanner.next());

			codCliente = scanner.next();

			ImovelAlugado a = new ImovelAlugado (area, new Endereco(rua,num,bairro), valor, cod, new Data(dia, mes, ano), getCliente(codCliente));
			alugados.add(a);	
		}
	}

	/**
	 * Carrega funcionários
	 */
	private void carregaFuncionarios()
	{
		Scanner scanner = null;

		try 
		{
			scanner = new Scanner(new FileReader("Banco de Dados/Funcionarios/Funcionarios.txt")).useDelimiter("\\||\\n");
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		while (scanner.hasNext()) 
		{
			String nome, rua, bairro;
			float salario;
			int num, cod, id, fone;

			cod =Integer.parseInt( scanner.next());
			nome = scanner.next();
			id = Integer.parseInt( scanner.next());
			rua = scanner.next();
			num = Integer.parseInt(scanner.next());
			bairro = scanner.next();
			salario = Float.parseFloat(scanner.next());
			fone = Integer.parseInt( scanner.next());

			Funcionario f = new Funcionario (nome, id, fone, new Endereco(rua,num,bairro), salario, cod);
			funcionarios.add(f);	
		}
	}

	/**
	 * Carrega gerentes
	 */
	private void carregaGerentes()
	{
		Scanner scanner = null;

		try 
		{
			scanner = new Scanner(new FileReader("Banco de Dados/Funcionarios/Gerentes.txt")).useDelimiter("\\||\\n");
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		while (scanner.hasNext()) 
		{
			String nome, rua, bairro;
			float salario;
			int num, cod, id, fone, dia, mes, ano;

			cod =Integer.parseInt( scanner.next());
			nome = scanner.next();
			id = Integer.parseInt( scanner.next());
			rua = scanner.next();
			num = Integer.parseInt(scanner.next());
			bairro = scanner.next();
			salario = Float.parseFloat(scanner.next());
			fone = Integer.parseInt( scanner.next());
			dia = Integer.parseInt( scanner.next());
			mes = Integer.parseInt( scanner.next());
			ano = Integer.parseInt( scanner.next());

			Gerente f = new Gerente (nome, id, fone, new Endereco(rua,num,bairro), salario, cod, new Data(dia,mes, ano));
			gerentes.add(f);	
		}
	}

	/**
	 * Carrega clientes
	 */
	private void carregaClientes()
	{
		Scanner scanner = null;

		try 
		{
			scanner = new Scanner(new FileReader("Banco de Dados/Clientes/Clientes.txt")).useDelimiter("\\||\\n");
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		while (scanner.hasNext()) 
		{
			String nome, rua, bairro;
			int num, cod, id, fone;

			cod =Integer.parseInt( scanner.next());
			nome = scanner.next();
			id = Integer.parseInt( scanner.next());
			rua = scanner.next();
			num = Integer.parseInt(scanner.next());
			bairro = scanner.next();
			fone = Integer.parseInt( scanner.next());

			Cliente f = new Cliente (nome, id, fone, new Endereco(rua,num,bairro),  cod);
			clientes.add(f);	
		}
	}

	// Funções para remoção de dados
	public void removeFuncionario(Funcionario f)
	{
		funcionarios.remove(f);
		atualizaFuncionarios();
	}

	public void removeApartamento(Apartamento i)
	{
		apartamentos.remove(i);
		atualizaApartamentos();
	}

	public void removeCasa(Casa i)
	{
		casas.remove(i);
		atualizaCasas();
	}

	public void removeSala(SalaComercial i)
	{
		salas.remove(i);
		atualizaSalas();
	}


	//Funções para atualização de dados nos arquivos:
	private void atualizaFuncionarios()
	{
		if (funcionarios.size()!=0)
		{
			for (Funcionario p : funcionarios)
			{
				try {
					escreveNoArquivo("Banco de Dados/Funcionarios/Funcionarios.txt", (p.stringToSave()), false);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		else
		{
			try {
				escreveNoArquivo("Banco de Dados/Funcionarios/Funcionarios.txt", "", false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void atualizaApartamentos()
	{
		if (apartamentos.size()!=0)
		{
			for (Apartamento p : apartamentos)
			{
				try {
					escreveNoArquivo("Banco de Dados/Imoveis Disponiveis/Apartamentos.txt", (p.stringToSave()), false);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		else
		{
			try {
				escreveNoArquivo("Banco de Dados/Imoveis Disponiveis/Apartamentos.txt", "", false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void atualizaCasas()
	{
		if (casas.size()!=0)
		{
			for (Casa p : casas)
			{
				try {
					escreveNoArquivo("Banco de Dados/Imoveis Disponiveis/Casas.txt", (p.stringToSave()), false);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		else
		{
			try {
				escreveNoArquivo("Banco de Dados/Imoveis Disponiveis/Casas.txt", "", false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void atualizaSalas()
	{
		if (salas.size()!=0)
		{
			for (SalaComercial p : salas)
			{
				try {
					escreveNoArquivo("Banco de Dados/Imoveis Disponiveis/Salas.txt", (p.stringToSave()), false);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		else
		{
			try {
				escreveNoArquivo("Banco de Dados/Imoveis Disponiveis/Salas.txt", "", false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Promove funcionário
	 * @param f
	 * @param data
	 * @param salario
	 */
	public void promoveFuncionario (Funcionario f, Data data, int salario) //Promove Funcionario
	{

		Gerente g = new Gerente (f.getNome(), f.getId(), f.getFone(), f.getEndereco(), salario, f.getCodigo(), data);
		funcionarios.remove(f);
		salvaGerente(g);
		atualizaFuncionarios();
	}

	//Funções de Get objetos:	
	public Funcionario getFuncionario(String codigo) //Retorna funcionário, se existir
	{
		Iterator<Funcionario> it = funcionarios.iterator();
		while(it.hasNext())
		{
			Funcionario f = (Funcionario)it.next();
			if (String.valueOf(f.getCodigo()).equals(codigo))
			{
				return f;
			}
		}
		return null;	
	}

	public Funcionario getGerente(String codigo) //Retorna funcionário, se existir
	{
		Iterator<Gerente> it = gerentes.iterator();
		while(it.hasNext())
		{
			Gerente f = (Gerente)it.next();
			if (String.valueOf(f.getCodigo()).equals(codigo))
			{
				return f;
			}
		}
		return null;	
	}

	public Cliente getCliente(String codigo)
	{
		Iterator<Cliente> it = clientes.iterator();
		while(it.hasNext())
		{
			Cliente f = (Cliente)it.next();
			if (String.valueOf(f.getCodigo()).equals(codigo))
			{
				return f;
			}
		}
		return null;	
	}

	public Casa getCasa(String codigo)
	{
		Iterator<Casa> it = casas.iterator();
		while(it.hasNext())
		{
			Casa f = (Casa)it.next();
			if (String.valueOf(f.getCodigo()).equals(codigo))
			{
				return f;
			}
		}
		return null;	
	}

	public Apartamento getApartamento(String codigo)
	{
		Iterator<Apartamento> it = apartamentos.iterator();
		while(it.hasNext())
		{
			Apartamento f = (Apartamento)it.next();
			if (String.valueOf(f.getCodigo()).equals(codigo))
			{
				return f;
			}
		}
		return null;	
	}

	public ImovelAlugado getImovelAlugado(String codigo)
	{
		Iterator<ImovelAlugado> it = alugados.iterator();
		while(it.hasNext())
		{
			ImovelAlugado f = (ImovelAlugado)it.next();
			if (String.valueOf(f.getCodigo()).equals(codigo))
			{
				return f;
			}
		}
		return null;	
	}

	public SalaComercial getSala(String codigo)
	{
		Iterator<SalaComercial> it = salas.iterator();
		while(it.hasNext())
		{
			SalaComercial f = (SalaComercial)it.next();
			if (String.valueOf(f.getCodigo()).equals(codigo))
			{
				return f;
			}
		}
		return null;	
	}

	public HashSet<Funcionario> getFuncionarios(){
		return funcionarios;
	}
	public HashSet<Cliente> getClientes(){
		return clientes;
	}
	public HashSet<Casa> getCasas(){
		return casas;
	}
	public HashSet<Apartamento> getApartamentos(){
		return apartamentos;
	}
	public HashSet<SalaComercial> getSalas(){
		return salas;
	}

	/**
	 * Torna imóvel em imóvel alugado
	 * @param i
	 */
	 public void tornaAlugado(ImovelAlugado i)
	{
		alugados.add(i);
		String string= i.stringToSave();
		System.out.println(string);
		try {
			escreveNoArquivo("Banco de Dados/Imoveis Alugados/Alugados.txt", string, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}







}
