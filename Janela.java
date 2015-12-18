import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

/**
 * Classe para a interface gráfica e extração de informações relacionadas a mesma
 * @author José Augusto
 *
 */
public class Janela extends JFrame implements ActionListener , KeyListener{

	private static final long serialVersionUID = 1L;
	private JFrame janelaLogin, janelaCadastraFuncionario, janelaCadastraImovel,janelaPesquisa, janelaProcuraImoveis,janelaPromove, janelaRemoveImovel,
	janelaDemiteFuncionario, janelaAluga, janelaCadastraCliente, janelaPagarAluguel;
	private JButton okLogin, botaoSair, botaoCadastraFuncionario, botaoCancelaCadFun, botaoConfirmaCadFun, botaoCadastraImovel,
	botaoConfirmaCadImovel, botaoCancelaCadImovel, botaoPesquisaFuncionarios,botaoVoltar, botaoPesquisaImoveis, botaoPromoveFuncionario,
	botaoPagarAluguel, botaoVoltarProcura,botaoOkProcuraImoveis, botaoCancelaPromove, botaoOkPesquisaFuncionario, botaoOkPromove, botaoDemiteFuncionario,
	botaoRemoveImovel, botaoVoltarRemove, botaoOkTipoRemove, botaoOkRemove, botaoOkFuncionarioDemite, botaoCancelaDemite, botaoAlugaImovel, botaoOkAluga,
	botaoCancelaAluga, botaoCadastraCliente, botaoCancelaCadCliente,botaoConfirmaCadCliente, botaoProcuraCliente, botaoOkCodigoPagamento, botaoCancelaPagamento,
	botaoOkPagamento;
	private JPanel painelLogin, painelBotoesProcuraImoveis, painelBotoes;
	private JComboBox comboTipo, comboProcura,comboTipoRemove;
	private JTextField campoEdificio, campoDormitorios, campoArea, campoValor, campoRua, campoNum, campoBairro, campoBanheiros, campoGaragem,
	campoNome, campoSalario, campoFone, campoNovoSalario, campoDataAluga;
	private boolean loginAccepted;
	private JFormattedTextField campoID, campoCodigoFuncionario, campoData,campoCodigoImovel, campoCodigoCliente;
	private MaskFormatter mascaraFuncionario, mascaraImovel, mascaraCliente;
	private JTable table;
	private String codigoImovelAlugar;
	private Arquivos arquivos = new Arquivos();

	/**
	 * Cria tela de Login
	 */
	public void telaLogin()
	{
		arquivos.carregaDadosFuncionarios();
		
		janelaLogin = new JFrame("Login");
		janelaLogin.setLayout(new GridLayout(2, 1, 30, 10));
		janelaLogin.setPreferredSize(new Dimension(500, 100));	
		janelaLogin.pack();
		janelaLogin.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaLogin.setLocationRelativeTo(null);

		//Painel de login
		painelLogin = new JPanel(new GridLayout(1, 0));
		JLabel label = new JLabel("   Código do Funcionário: ", JLabel.LEFT);

		//Cria máscaras para os códigos (4 digitos)
		mascaraFuncionario = null;
		try {
			mascaraFuncionario = new MaskFormatter("1###"); //Código do funcionário inicia em 1
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		mascaraImovel = null;
		try {
			mascaraImovel = new MaskFormatter("2###"); //Código do imóvel inicia em 2
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		mascaraCliente = null;
		try {
			mascaraCliente = new MaskFormatter("3###"); //Código do cliente inicia em 3
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		mascaraCliente.setPlaceholderCharacter('_');
		mascaraFuncionario.setPlaceholderCharacter('_');
		mascaraImovel.setPlaceholderCharacter('_');

		campoCodigoFuncionario = new JFormattedTextField(mascaraFuncionario);
		painelLogin.add(label);
		painelLogin.add(campoCodigoFuncionario);

		// Painel de botões
		JPanel painelBotoes = new JPanel(new GridLayout());  
		okLogin = new JButton("Ok");		// Ok
		okLogin.addActionListener(this);
		botaoSair = new JButton("Sair"); // Sair
		botaoSair.addActionListener(this);
		painelBotoes.add(okLogin);
		painelBotoes.add(botaoSair);

		janelaLogin.add(painelLogin);
		janelaLogin.add (painelBotoes);
		janelaLogin.pack();
		janelaLogin.setVisible(true);
	}
	/**
	 * Cria tela principal do programa
	 */
	private void telaPrincipal()
	{
		janelaLogin.dispose();
		arquivos.carregaDados();
		
		JFrame janelaPrincipal = new JFrame("Imobiliária");
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.CENTER);
		janelaPrincipal.setLayout(layout);
		if (arquivos.getGerente(campoCodigoFuncionario.getText())!=null)
		{
			janelaPrincipal.setPreferredSize(new Dimension(846, 275));
		}
		else
		{
			janelaPrincipal.setPreferredSize(new Dimension(610, 275));
		}
		janelaPrincipal.pack();
		janelaPrincipal.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaPrincipal.setLocationRelativeTo(null);

		//Painel de Imóveis
		JPanel painelImoveis = new JPanel(new GridLayout(7,0));
		JLabel imoveis = new JLabel("                      Imóveis");
		botaoCadastraImovel = new JButton("Cadastrar Novo Imóvel");
		botaoCadastraImovel.addActionListener(this);
		botaoPesquisaImoveis = new JButton("Procurar Imóveis");
		botaoPesquisaImoveis.addActionListener(this);
		botaoRemoveImovel = new JButton("Remover Imóvel");
		botaoRemoveImovel.addActionListener(this);	
		painelImoveis.add(imoveis, BorderLayout.NORTH );
		painelImoveis.add(botaoCadastraImovel, BorderLayout.SOUTH);
		painelImoveis.add(botaoPesquisaImoveis);
		painelImoveis.add(botaoRemoveImovel);

		// Painel de serviços
		JPanel painelServicos = new JPanel(new GridLayout(7,0));
		JLabel labelServicos = new JLabel("                 Serviços");
		painelServicos.add(labelServicos, BorderLayout.NORTH );	
		botaoPagarAluguel = new JButton("Pagamento de Aluguel");
		botaoPagarAluguel.addActionListener(this);
		painelServicos.add(botaoPagarAluguel, BorderLayout.NORTH);

		// Painel do gerente
		JPanel painelGerente = new JPanel(new GridLayout(7,0));
		JLabel labelGerente = new JLabel("                  Gerente");
		painelGerente.add(labelGerente, BorderLayout.NORTH );		
		botaoCadastraFuncionario = new JButton("Cadastrar Novo Funcionário");
		botaoCadastraFuncionario.addActionListener(this);
		botaoPesquisaFuncionarios = new JButton("Procurar Funcionários");
		botaoPesquisaFuncionarios.addActionListener(this);
		botaoPromoveFuncionario = new JButton("Promover Funcionário");
		botaoPromoveFuncionario.addActionListener(this);
		botaoDemiteFuncionario = new JButton("Demitir Funcionário");
		botaoDemiteFuncionario.addActionListener(this);
		painelGerente.add(botaoCadastraFuncionario, BorderLayout.SOUTH);
		painelGerente.add(botaoPromoveFuncionario, BorderLayout.SOUTH );
		painelGerente.add(botaoPesquisaFuncionarios, BorderLayout.SOUTH);
		painelGerente.add(botaoDemiteFuncionario, BorderLayout.SOUTH);

		//Painel do cliente
		JPanel painelCliente = new JPanel(new GridLayout(7,0));
		JLabel labelCliente = new JLabel("                  Cliente");
		painelCliente.add(labelCliente, BorderLayout.NORTH );		
		botaoCadastraCliente = new JButton("Cadastrar Novo Cliente");
		botaoCadastraCliente.addActionListener(this);
		botaoProcuraCliente = new JButton("Procurar Cliente");
		botaoProcuraCliente.addActionListener(this);
		painelCliente.add(botaoCadastraCliente, BorderLayout.SOUTH);
		painelCliente.add(botaoProcuraCliente, BorderLayout.SOUTH);

		//Painel botões
		JPanel painelBotoes = new JPanel(new GridLayout(1,0));  
		botaoSair = new JButton("Sair");  
		botaoSair.addActionListener(this);
		painelBotoes.add(botaoSair, BorderLayout.WEST);


		janelaPrincipal.add(painelImoveis);
		janelaPrincipal.add(painelCliente);
		janelaPrincipal.add(painelServicos);
		if (arquivos.getGerente(campoCodigoFuncionario.getText())!=null) //se for gerente, tem acesso a mais funções
		{
			janelaPrincipal.add(painelGerente);
		}
		janelaPrincipal.add(painelBotoes);
		janelaPrincipal.setVisible(true);
	}

	/**
	 * Cria tela de cadastro para o funcionário
	 */
	private void telaCadastroFuncionario()
	{
		janelaCadastraFuncionario = new JFrame("Cadastro de Funcionário");
		janelaCadastraFuncionario.setLayout(new GridLayout(9, 1, 30, 10));
		janelaCadastraFuncionario.setPreferredSize(new Dimension(500, 410));
		janelaCadastraFuncionario.pack();
		janelaCadastraFuncionario.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaCadastraFuncionario.setLocationRelativeTo(null);

		//Nome
		JPanel painelNome = new JPanel(new GridLayout(1, 0));
		JLabel labelNome = new JLabel("Nome Completo: ", JLabel.LEFT);
		campoNome = new JTextField();
		painelNome.add(labelNome);
		painelNome.add(campoNome);

		//ID
		JPanel painelID = new JPanel(new GridLayout(1, 0));
		JLabel labelID = new JLabel("CPF: ", JLabel.LEFT);
		MaskFormatter mascaraID = null;
		try {
			mascaraID = new MaskFormatter("########");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		campoID = new JFormattedTextField(mascaraID);
		campoID.addKeyListener(this);
		painelID.add(labelID);
		painelID.add(campoID);

		//Fone
		JPanel painelFone = new JPanel(new GridLayout(1, 0));
		JLabel labelFone = new JLabel("Telefone: ", JLabel.LEFT);
		campoFone = new JTextField();
		campoFone.addKeyListener(this);
		painelFone.add(labelFone);
		painelFone.add(campoFone);

		//Rua
		JPanel painelRua = new JPanel(new GridLayout(1, 0));
		JLabel labelRua = new JLabel("Rua: ", JLabel.LEFT);
		campoRua = new JTextField();
		painelRua.add(labelRua);
		painelRua.add(campoRua);

		//Num
		JPanel painelNum = new JPanel(new GridLayout(1, 0));
		JLabel labelNum = new JLabel("Num: ", JLabel.LEFT);
		campoNum = new JTextField();
		campoNum.addKeyListener(this);
		painelNum.add(labelNum);
		painelNum.add(campoNum);

		//Bairro
		JPanel painelBairro = new JPanel(new GridLayout(1, 0));
		JLabel labelBairro = new JLabel("Bairro: ", JLabel.LEFT);
		campoBairro = new JTextField();
		painelBairro.add(labelBairro);
		painelBairro.add(campoBairro);

		//Salario
		JPanel painelSalario = new JPanel(new GridLayout(1, 0));
		JLabel labelSalario = new JLabel("Salario: ", JLabel.LEFT);
		campoSalario = new JTextField();
		campoSalario.addKeyListener(this);
		painelSalario.add(labelSalario);
		painelSalario.add(campoSalario);

		//Codigo
		JPanel painelCodigo = new JPanel(new GridLayout(1, 0));
		JLabel labelCodigo = new JLabel("Código: ", JLabel.LEFT);
		campoCodigoFuncionario = new JFormattedTextField(mascaraFuncionario);
		campoCodigoFuncionario.addKeyListener(this);
		painelCodigo.add(labelCodigo);
		painelCodigo.add(campoCodigoFuncionario);

		//Botões
		JPanel painelBotoes = new JPanel(new GridLayout(1, 0, 5, 5));  
		botaoConfirmaCadFun = new JButton("Confirmar");  
		botaoCancelaCadFun = new JButton("Cancelar");  
		painelBotoes.add(botaoConfirmaCadFun);
		painelBotoes.add(botaoCancelaCadFun);

		botaoCancelaCadFun.addActionListener(this);
		botaoConfirmaCadFun.addActionListener(this);

		janelaCadastraFuncionario.add(painelNome);
		janelaCadastraFuncionario.add(painelID);
		janelaCadastraFuncionario.add(painelFone);
		janelaCadastraFuncionario.add(painelRua);
		janelaCadastraFuncionario.add(painelNum);
		janelaCadastraFuncionario.add(painelBairro);
		janelaCadastraFuncionario.add(painelSalario);
		janelaCadastraFuncionario.add(painelCodigo);
		janelaCadastraFuncionario.add(painelBotoes);
		janelaCadastraFuncionario.setVisible(true);     
	}
	/**
	 * Cria tela de cadastro de imóvel
	 */
	private void telaCadastroImovel()
	{
		janelaCadastraImovel = new JFrame("Cadastro de Imóvel");
		janelaCadastraImovel.setLayout(new GridLayout(12, 1, 30, 10));
		janelaCadastraImovel.setPreferredSize(new Dimension(600, 500));
		janelaCadastraImovel.pack();
		janelaCadastraImovel.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaCadastraImovel.setLocationRelativeTo(null);	

		//Edificio
		JPanel painelEdificio = new JPanel(new GridLayout(1, 0));
		JLabel labelEdificio = new JLabel("Edifício: ", JLabel.LEFT);
		campoEdificio = new JTextField();
		painelEdificio.add(labelEdificio);
		painelEdificio.add(campoEdificio);

		//Area
		JPanel painelArea = new JPanel(new GridLayout(1, 0));
		JLabel labelArea = new JLabel("Área: ", JLabel.LEFT);
		campoArea = new JTextField();
		campoArea.addKeyListener(this);
		painelArea.add(labelArea);
		painelArea.add(campoArea);

		//Rua
		JPanel painelRua = new JPanel(new GridLayout(1, 0));
		JLabel labelRua = new JLabel("Rua: ", JLabel.LEFT);
		campoRua = new JTextField();
		painelRua.add(labelRua);
		painelRua.add(campoRua);

		//Num
		JPanel painelNum = new JPanel(new GridLayout(1, 0));
		JLabel labelNum = new JLabel("Num: ", JLabel.LEFT);
		campoNum = new JTextField();
		campoNum.addKeyListener(this);
		painelNum.add(labelNum);
		painelNum.add(campoNum);

		//Bairro
		JPanel painelBairro = new JPanel(new GridLayout(1, 0));
		JLabel labelBairro = new JLabel("Bairro: ", JLabel.LEFT);
		campoBairro = new JTextField();
		painelBairro.add(labelBairro);
		painelBairro.add(campoBairro);

		//Valor
		JPanel painelValor = new JPanel(new GridLayout(1, 0));
		JLabel labelValor = new JLabel("Valor: ", JLabel.LEFT);
		campoValor = new JTextField();
		campoValor.addKeyListener(this);
		painelValor.add(labelValor);
		painelValor.add(campoValor);

		//Codigo
		JPanel painelCodigo = new JPanel(new GridLayout(1, 0));
		JLabel labelCodigo = new JLabel("Código: ", JLabel.LEFT);
		campoCodigoImovel = new JFormattedTextField(mascaraImovel);
		campoCodigoImovel.addKeyListener(this);
		painelCodigo.add(labelCodigo);
		painelCodigo.add(campoCodigoImovel);

		//Dormitorios
		JPanel painelDormitorios = new JPanel(new GridLayout(1, 0));
		JLabel labelDormitorios = new JLabel("Dormitórios: ", JLabel.LEFT);
		campoDormitorios = new JTextField();
		campoDormitorios.addKeyListener(this);
		painelDormitorios.add(labelDormitorios);
		painelDormitorios.add(campoDormitorios);

		//Banheiros
		JPanel painelBanheiros = new JPanel(new GridLayout(1, 0));
		JLabel labelBanheiros = new JLabel("Banheiros: ", JLabel.LEFT);
		campoBanheiros = new JTextField();
		campoBanheiros.addKeyListener(this);
		painelBanheiros.add(labelBanheiros);
		painelBanheiros.add(campoBanheiros);

		//Garagem
		JPanel painelGaragem = new JPanel(new GridLayout(1, 0));
		JLabel labelGaragem = new JLabel("Vagas de Garagem: ", JLabel.LEFT);
		campoGaragem = new JTextField();
		campoGaragem.addKeyListener(this);
		painelGaragem.add(labelGaragem);
		painelGaragem.add(campoGaragem);

		// Tipo
		JPanel painelTipo = new JPanel(new GridLayout(1, 0));
		JLabel labelTipo = new JLabel("Selecione o tipo de Imóvel: ", JLabel.LEFT);
		comboTipo = new JComboBox();
		comboTipo.addItem("Apartamento");
		comboTipo.addItem("Casa");
		comboTipo.addItem("Sala Comercial");
		comboTipo.addActionListener(this);
		painelTipo.add(labelTipo);
		painelTipo.add(comboTipo);

		//Botões
		JPanel painelBotoes = new JPanel(new GridLayout(1, 0, 5, 5));  
		botaoConfirmaCadImovel = new JButton("Confirmar");  
		botaoCancelaCadImovel = new JButton("Cancelar");  
		painelBotoes.add(botaoConfirmaCadImovel);
		painelBotoes.add(botaoCancelaCadImovel); 
		botaoCancelaCadImovel.addActionListener(this);
		botaoConfirmaCadImovel.addActionListener(this);

		janelaCadastraImovel.add(painelTipo);
		janelaCadastraImovel.add(painelEdificio);
		janelaCadastraImovel.add(painelArea);
		janelaCadastraImovel.add(painelDormitorios);
		janelaCadastraImovel.add(painelBanheiros);
		janelaCadastraImovel.add(painelGaragem);
		janelaCadastraImovel.add(painelRua);
		janelaCadastraImovel.add(painelNum);
		janelaCadastraImovel.add(painelBairro);
		janelaCadastraImovel.add(painelCodigo);
		janelaCadastraImovel.add(painelValor);
		janelaCadastraImovel.add(painelBotoes);
		janelaCadastraImovel.setVisible(true);     
	}
	/**
	 * Cria tela de cadastro do cliente
	 */
	private void telaCadastroCliente()
	{
		janelaCadastraCliente = new JFrame("Cadastro de Cliente");
		janelaCadastraCliente.setLayout(new GridLayout(8, 1, 30, 10));
		janelaCadastraCliente.setPreferredSize(new Dimension(500, 410));
		janelaCadastraCliente.pack();
		janelaCadastraCliente.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaCadastraCliente.setLocationRelativeTo(null);

		//Nome
		JPanel painelNome = new JPanel(new GridLayout(1, 0));
		JLabel labelNome = new JLabel("Nome Completo: ", JLabel.LEFT);
		campoNome = new JTextField();
		painelNome.add(labelNome);
		painelNome.add(campoNome);

		//ID
		JPanel painelID = new JPanel(new GridLayout(1, 0));
		JLabel labelID = new JLabel("CPF: ", JLabel.LEFT);
		MaskFormatter mascaraID = null;
		try {
			mascaraID = new MaskFormatter("########");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		campoID = new JFormattedTextField(mascaraID);
		campoID.addKeyListener(this);
		painelID.add(labelID);
		painelID.add(campoID);

		//Fone
		JPanel painelFone = new JPanel(new GridLayout(1, 0));
		JLabel labelFone = new JLabel("Telefone: ", JLabel.LEFT);
		campoFone = new JTextField();
		campoFone.addKeyListener(this);
		painelFone.add(labelFone);
		painelFone.add(campoFone);

		//Rua
		JPanel painelRua = new JPanel(new GridLayout(1, 0));
		JLabel labelRua = new JLabel("Rua: ", JLabel.LEFT);
		campoRua = new JTextField();
		painelRua.add(labelRua);
		painelRua.add(campoRua);

		//Num
		JPanel painelNum = new JPanel(new GridLayout(1, 0));
		JLabel labelNum = new JLabel("Num: ", JLabel.LEFT);
		campoNum = new JTextField();
		campoNum.addKeyListener(this);
		painelNum.add(labelNum);
		painelNum.add(campoNum);

		//Bairro
		JPanel painelBairro = new JPanel(new GridLayout(1, 0));
		JLabel labelBairro = new JLabel("Bairro: ", JLabel.LEFT);
		campoBairro = new JTextField();
		painelBairro.add(labelBairro);
		painelBairro.add(campoBairro);

		//Codigo
		JPanel painelCodigo = new JPanel(new GridLayout(1, 0));
		JLabel labelCodigo = new JLabel("Código: ", JLabel.LEFT);
		campoCodigoCliente = new JFormattedTextField(mascaraCliente);
		campoCodigoCliente.addKeyListener(this);
		painelCodigo.add(labelCodigo);
		painelCodigo.add(campoCodigoCliente);

		//Botões
		JPanel painelBotoes = new JPanel(new GridLayout(1, 0, 5, 5));  
		botaoConfirmaCadCliente= new JButton("Confirmar");  
		botaoCancelaCadCliente = new JButton("Cancelar");  
		painelBotoes.add(botaoConfirmaCadCliente);
		painelBotoes.add(botaoCancelaCadCliente);
		botaoCancelaCadCliente.addActionListener(this);
		botaoConfirmaCadCliente.addActionListener(this);

		janelaCadastraCliente.add(painelNome);
		janelaCadastraCliente.add(painelID);
		janelaCadastraCliente.add(painelFone);
		janelaCadastraCliente.add(painelRua);
		janelaCadastraCliente.add(painelNum);
		janelaCadastraCliente.add(painelBairro);
		janelaCadastraCliente.add(painelCodigo);
		janelaCadastraCliente.add(painelBotoes);
		janelaCadastraCliente.setVisible(true);     
	}

	/**
	 * Exibe funcionários cadastrados
	 */
	private  void telaTableFuncionarios()
	{
		HashSet<Funcionario> list = arquivos.getFuncionarios();

		janelaPesquisa = new JFrame("Pesquisa: Funcionários Cadastrados");
		janelaPesquisa.setPreferredSize(new Dimension(800, 500));
		janelaPesquisa.pack();
		janelaPesquisa.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaPesquisa.setLocationRelativeTo(null);
		JPanel painelPesquisa = new JPanel(new BorderLayout());

		//Tabela 
		JTable table = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		table.setModel(model);
		model.setColumnIdentifiers(new String[] {"Nome","Código", "CPF", "Fone",  "Salário", "Endereço"});

		for (Funcionario p : list) // Aficiona funcionarios
		{
			model.addRow(new String[] {p.getNome(), String.valueOf(p.getCodigo()),String.valueOf(p.getId()),String.valueOf(p.getFone()), String.valueOf(p.getSalario()),
					String.valueOf(p.getEndereco())   });
		}
		JScrollPane tableContainer = new JScrollPane(table);
		painelPesquisa.add(tableContainer, BorderLayout.CENTER);

		//Botoes
		JPanel painelBotoes = new JPanel();  
		botaoVoltar = new JButton("Sair"); // Sair
		botaoVoltar.addActionListener(this);
		painelBotoes.add(botaoVoltar, BorderLayout.WEST);

		janelaPesquisa.getContentPane().add(painelPesquisa);
		janelaPesquisa.add(painelBotoes, BorderLayout.SOUTH);
		janelaPesquisa.pack();
		janelaPesquisa.setVisible(true);
	}

	/**
	 * Exibe clientes cadastrados
	 */
	private  void telaTableClientes()
	{
		HashSet<Cliente> list = arquivos.getClientes();

		janelaPesquisa = new JFrame("Pesquisa: Clientes Cadastrados");
		janelaPesquisa.setPreferredSize(new Dimension(800, 500));
		janelaPesquisa.pack();
		janelaPesquisa.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaPesquisa.setLocationRelativeTo(null);
		JPanel painelPesquisa = new JPanel(new BorderLayout());

		//Tabela 
		JTable table = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		table.setModel(model);
		model.setColumnIdentifiers(new String[] {"Nome","Código", "CPF", "Fone", "Endereço"});

		for (Cliente p : list) // Aficiona funcionarios
		{
			model.addRow(new String[] {p.getNome(), String.valueOf(p.getCodigo()),String.valueOf(p.getId()),String.valueOf(p.getFone()),
					String.valueOf(p.getEndereco())   });
		}
		JScrollPane tableContainer = new JScrollPane(table);
		painelPesquisa.add(tableContainer, BorderLayout.CENTER);

		//Botoes
		JPanel painelBotoes = new JPanel();  
		botaoVoltar = new JButton("Sair"); // Sair
		botaoVoltar.addActionListener(this);
		painelBotoes.add(botaoVoltar, BorderLayout.WEST);

		janelaPesquisa.getContentPane().add(painelPesquisa);
		janelaPesquisa.add(painelBotoes, BorderLayout.SOUTH);
		janelaPesquisa.pack();
		janelaPesquisa.setVisible(true);
	}
	/**
	 * Tela para seleção do tipo de imóvel para procurar
	 */
	private void telaProcuraImoveis()
	{
		janelaProcuraImoveis = new JFrame("Pesquisa: Imóveis Disponíveis");
		janelaProcuraImoveis.setLayout(new GridLayout(2, 1, 30, 10));
		janelaProcuraImoveis.setPreferredSize(new Dimension(345,120));
		janelaProcuraImoveis.pack();
		janelaProcuraImoveis.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaProcuraImoveis.setLocationRelativeTo(null);

		//Seleciona Tipo de Imovel
		JPanel painelTipoProcura = new JPanel(new GridLayout(1, 0));
		JLabel labelTipo = new JLabel("Selecione o tipo de Imóvel: ", JLabel.LEFT);
		comboProcura = new JComboBox();
		comboProcura.addItem("Apartamento");
		comboProcura.addItem("Casa");
		comboProcura.addItem("Sala Comercial");
		comboProcura.addActionListener(this);
		painelTipoProcura.add(labelTipo);
		painelTipoProcura.add(comboProcura);	

		//Botões
		painelBotoesProcuraImoveis = new JPanel(new GridLayout(1, 0));  
		botaoVoltarProcura = new JButton("Sair"); // Voltar
		botaoVoltarProcura.addActionListener(this);
		botaoOkProcuraImoveis = new JButton("Ok"); // Ok
		botaoOkProcuraImoveis.addActionListener(this);
		painelBotoesProcuraImoveis.add(botaoVoltarProcura);
		painelBotoesProcuraImoveis.add(botaoOkProcuraImoveis);

		janelaProcuraImoveis.getContentPane().add(painelTipoProcura);
		janelaProcuraImoveis.add(painelBotoesProcuraImoveis, BorderLayout.SOUTH);
		janelaProcuraImoveis.pack();
		janelaProcuraImoveis.setVisible(true);
	}

	/**
	 * Exibe casas cadastradas
	 */
	private  void telaTableCasas()
	{
		HashSet<Casa> list = arquivos.getCasas();

		janelaProcuraImoveis.dispose();
		janelaProcuraImoveis = new JFrame("Pesquisa: Casas Disponíveis");
		janelaProcuraImoveis.setPreferredSize(new Dimension(800, 500));
		janelaProcuraImoveis.pack();
		janelaProcuraImoveis.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaProcuraImoveis.setLocationRelativeTo(null);
		JPanel painelPesquisa = new JPanel(new BorderLayout());

		//Tabela de casas
		table = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		table.setModel(model);
		model.setColumnIdentifiers(new String[] {"Código", "Área", "Dormitórios", "Banheiros", "Garagem","Valor","Endereço"});

		for (Casa p : list)
		{
			model.addRow(new String[] {String.valueOf(p.getCodigo()), String.valueOf(p.getArea()),String.valueOf(p.getDormitorios()),String.valueOf(p.getBanheiros()), 
					String.valueOf(p.getGaragem()),	String.valueOf(p.getValor()),String.valueOf(p.getEndereco())   });
		}
		JScrollPane tableContainer = new JScrollPane(table);
		painelPesquisa.add(tableContainer, BorderLayout.CENTER);

		botaoAlugaImovel = new JButton("Alugar");
		botaoAlugaImovel.addActionListener(this);

		janelaProcuraImoveis.getContentPane().add(painelPesquisa);
		painelBotoesProcuraImoveis.remove(botaoOkProcuraImoveis);
		painelBotoesProcuraImoveis.add(botaoAlugaImovel);
		janelaProcuraImoveis.add(painelBotoesProcuraImoveis, BorderLayout.SOUTH);

		janelaProcuraImoveis.pack();
		janelaProcuraImoveis.setVisible(true);
	}

	/**
	 * Exibe apartamentos cadastrados
	 */
	private  void telaTableApartamentos()
	{
		HashSet<Apartamento> list = arquivos.getApartamentos();

		janelaProcuraImoveis.dispose();
		janelaProcuraImoveis = new JFrame("Pesquisa: Apartamentos Disponíveis");
		janelaProcuraImoveis.setPreferredSize(new Dimension(800, 500));
		janelaProcuraImoveis.pack();
		janelaProcuraImoveis.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaProcuraImoveis.setLocationRelativeTo(null);
		JPanel painelPesquisa = new JPanel(new BorderLayout());

		//Tabela de apartamentos
		table = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		table.setModel(model);
		model.setColumnIdentifiers(new String[] {"Código", "Edifício","Área", "Dormitórios", "Banheiros", "Garagem","Valor","Endereço"});

		for (Apartamento p : list)
		{
			model.addRow(new String[] {String.valueOf(p.getCodigo()),p.getNome(), String.valueOf(p.getArea()),String.valueOf(p.getDormitorios()),String.valueOf(p.getBanheiros()), 
					String.valueOf(p.getGaragem()),	String.valueOf(p.getValor()),String.valueOf(p.getEndereco())   });
		}
		JScrollPane tableContainer = new JScrollPane(table);
		painelPesquisa.add(tableContainer, BorderLayout.CENTER);

		botaoAlugaImovel = new JButton("Alugar");
		botaoAlugaImovel.addActionListener(this);

		janelaProcuraImoveis.getContentPane().add(painelPesquisa);
		painelBotoesProcuraImoveis.remove(botaoOkProcuraImoveis);
		painelBotoesProcuraImoveis.add(botaoAlugaImovel);

		janelaProcuraImoveis.add(painelBotoesProcuraImoveis, BorderLayout.SOUTH);
		janelaProcuraImoveis.pack();
		janelaProcuraImoveis.setVisible(true);
	}

	/**
	 * Exibe salas comerciais cadastradas
	 */
	private  void telaTableSalas()
	{
		HashSet<SalaComercial> list = arquivos.getSalas();

		janelaProcuraImoveis.dispose();
		janelaProcuraImoveis = new JFrame("Pesquisa: Salas Disponíveis");
		janelaProcuraImoveis.setPreferredSize(new Dimension(800, 500));
		janelaProcuraImoveis.pack();
		janelaProcuraImoveis.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaProcuraImoveis.setLocationRelativeTo(null);
		JPanel painelPesquisa = new JPanel(new BorderLayout());

		//Tabela de salas
		table = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		table.setModel(model);
		model.setColumnIdentifiers(new String[] {"Código", "Área", "Banheiros", "Garagem","Valor","Endereço"});

		for (SalaComercial p : list)
		{
			model.addRow(new String[] {String.valueOf(p.getCodigo()), String.valueOf(p.getArea()),String.valueOf(p.getBanheiros()), 
					String.valueOf(p.getGaragem()),	String.valueOf(p.getValor()),String.valueOf(p.getEndereco())   });
		}
		JScrollPane tableContainer = new JScrollPane(table);
		painelPesquisa.add(tableContainer, BorderLayout.CENTER);

		botaoAlugaImovel = new JButton("Alugar");
		botaoAlugaImovel.addActionListener(this);

		janelaProcuraImoveis.getContentPane().add(painelPesquisa);
		painelBotoesProcuraImoveis.remove(botaoOkProcuraImoveis);
		painelBotoesProcuraImoveis.add(botaoAlugaImovel);
		janelaProcuraImoveis.add(painelBotoesProcuraImoveis, BorderLayout.SOUTH);
		janelaProcuraImoveis.pack();
		janelaProcuraImoveis.setVisible(true);
	}

	/**
	 * Pesquisa funcionário para promoção
	 */
	private  void telaPesquisaFuncionario()
	{
		janelaPromove = new JFrame("Promover Funcionário");
		janelaPromove.setLayout(new GridLayout(2, 1, 30, 10));
		janelaPromove.setPreferredSize(new Dimension(500, 120));
		janelaPromove.pack();
		janelaPromove.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaPromove.setLocationRelativeTo(null);	

		// Painel de botões
		painelBotoes = new JPanel(new GridLayout());  
		botaoOkPesquisaFuncionario = new JButton("Ok");		// Ok
		botaoOkPesquisaFuncionario.addActionListener(this);
		botaoCancelaPromove = new JButton("Cancelar"); // Sair
		botaoCancelaPromove.addActionListener(this);
		painelBotoes.add(botaoOkPesquisaFuncionario);
		painelBotoes.add(botaoCancelaPromove);

		janelaPromove.add(painelLogin);
		janelaPromove.add (painelBotoes);
		janelaPromove.pack();
		janelaPromove.setVisible(true);
	}

	/**
	 * Tela para a promoção de um funcionário
	 */
	private void telaPromoveFuncionario()
	{
		janelaPromove.dispose();
		janelaPromove = new JFrame("Promover Funcionário");
		janelaPromove.setLayout(new GridLayout(4, 1, 30, 10));
		janelaPromove.setPreferredSize(new Dimension(400, 210));	
		janelaPromove.pack();
		janelaPromove.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaPromove.setLocationRelativeTo(null);

		String codFuncionario = campoCodigoFuncionario.getText();
		JLabel label = new JLabel("   Promover Funcionario: "+arquivos.getFuncionario(codFuncionario).getNome(), JLabel.LEFT);

		//Painel Data
		JPanel painelData = new JPanel(new GridLayout());
		JLabel labelData = new JLabel("   Data de promoção: ", JLabel.LEFT);
		MaskFormatter mascara = null;
		try {
			mascara = new MaskFormatter("##/##/####");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		//mascara.setPlaceholderCharacter('_');
		campoData = new JFormattedTextField(mascara);
		painelData.add(labelData);
		painelData.add(campoData);

		//Novo Salario
		JPanel painelSalario = new JPanel(new GridLayout());
		JLabel labelSalario = new JLabel(" Novo Salario: ", JLabel.LEFT);
		campoNovoSalario = new JTextField();
		campoNovoSalario.addKeyListener(this);
		painelSalario.add(labelSalario);
		painelSalario.add(campoNovoSalario);

		// Painel de botões
		JPanel painelBotoes = new JPanel(new GridLayout());  
		botaoOkPromove = new JButton("Ok");		// Ok
		botaoOkPromove.addActionListener(this);
		botaoCancelaPromove = new JButton("Cancelar"); // Sair
		botaoCancelaPromove.addActionListener(this);
		painelBotoes.add(botaoOkPromove);
		painelBotoes.add(botaoCancelaPromove);

		janelaPromove.add (label);
		janelaPromove.add (painelData);
		janelaPromove.add (painelSalario);
		janelaPromove.add(painelBotoes);
		janelaPromove.pack();
		janelaPromove.setVisible(true);
	}

	/**
	 * Tela para escolha do tipo de imóvel a remover
	 */
	private void telaTipoRemoveImovel()
	{
		janelaRemoveImovel = new JFrame("Remover Imóvel do cadastro");
		janelaRemoveImovel.setLayout(new GridLayout(2, 1, 30, 10));
		janelaRemoveImovel.setPreferredSize(new Dimension(420,120));
		janelaRemoveImovel.pack();
		janelaRemoveImovel.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaRemoveImovel.setLocationRelativeTo(null);

		//Tipo de Imovel
		JPanel painelTipoRemove = new JPanel(new GridLayout(1, 0));
		JLabel labelTipo = new JLabel("Selecione o tipo de Imóvel: ", JLabel.LEFT);
		comboTipoRemove = new JComboBox();
		comboTipoRemove.addItem("Apartamento");
		comboTipoRemove.addItem("Casa");
		comboTipoRemove.addItem("Sala Comercial");
		comboTipoRemove.addActionListener(this);
		painelTipoRemove.add(labelTipo);
		painelTipoRemove.add(comboTipoRemove);	

		//Botoes
		JPanel painelBotoesRemoveImovel = new JPanel(new GridLayout(1, 0));  
		botaoVoltarRemove = new JButton("Sair"); // Voltar
		botaoVoltarRemove.addActionListener(this);
		botaoOkTipoRemove = new JButton("Ok"); // Ok
		botaoOkTipoRemove.addActionListener(this);
		painelBotoesRemoveImovel.add(botaoVoltarRemove);
		painelBotoesRemoveImovel.add(botaoOkTipoRemove);

		janelaRemoveImovel.add(painelTipoRemove);
		janelaRemoveImovel.add(painelBotoesRemoveImovel, BorderLayout.SOUTH);
		janelaRemoveImovel.pack();
		janelaRemoveImovel.setVisible(true);
	}

	/**
	 * Tela para remoção de um imóvel
	 */
	private void telaRemoveImovel()
	{
		janelaRemoveImovel = new JFrame("Remover Imóvel do cadastro");
		janelaRemoveImovel.setLayout(new GridLayout(2, 1, 30, 10));
		janelaRemoveImovel.setPreferredSize(new Dimension(420,120));
		janelaRemoveImovel.pack();
		janelaRemoveImovel.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaRemoveImovel.setLocationRelativeTo(null);

		//Código
		JPanel painelCodigo = new JPanel(new GridLayout(1, 0));
		JLabel labelCodigo = new JLabel("Código do Imóvel: ", JLabel.LEFT);
		campoCodigoImovel = new JFormattedTextField(mascaraImovel);
		campoCodigoImovel.addKeyListener(this);
		painelCodigo.add(labelCodigo);
		painelCodigo.add(campoCodigoImovel);

		//Botões
		JPanel painelBotoesRemoveImovel = new JPanel(new GridLayout(1, 0));  
		botaoVoltarRemove = new JButton("Sair"); // Voltar
		botaoVoltarRemove.addActionListener(this);
		botaoOkRemove = new JButton("Ok"); // Ok
		botaoOkRemove.addActionListener(this);
		painelBotoesRemoveImovel.add(botaoVoltarRemove);
		painelBotoesRemoveImovel.add(botaoOkRemove);

		janelaRemoveImovel.add(painelCodigo);
		janelaRemoveImovel.add(painelBotoesRemoveImovel, BorderLayout.SOUTH);
		janelaRemoveImovel.pack();
		janelaRemoveImovel.setVisible(true);
	}

	/**
	 * Tela para demissão de funcionário
	 */
	private void telaDemiteFuncionario()
	{
		janelaDemiteFuncionario = new JFrame("Demitir Funcionário");
		janelaDemiteFuncionario.setLayout(new GridLayout(2, 1, 30, 10));
		janelaDemiteFuncionario.setPreferredSize(new Dimension(420, 120));	
		janelaDemiteFuncionario.pack();
		janelaDemiteFuncionario.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaDemiteFuncionario.setLocationRelativeTo(null);	

		// Painel de botões
		JPanel botoesDemite = new JPanel(new GridLayout());  
		botaoOkFuncionarioDemite = new JButton("Ok");		// Ok
		botaoOkFuncionarioDemite.addActionListener(this);
		botaoCancelaDemite= new JButton("Cancelar"); // Sair
		botaoCancelaDemite.addActionListener(this);
		botoesDemite.add(botaoOkFuncionarioDemite);
		botoesDemite.add(botaoCancelaDemite);

		janelaDemiteFuncionario.add(painelLogin);
		janelaDemiteFuncionario.add (botoesDemite);
		janelaDemiteFuncionario.pack();
		janelaDemiteFuncionario.setVisible(true);
	}

	/**
	 * Tela para alugar um imóvel
	 */
	private void telaAlugaImovel()
	{
		janelaAluga = new JFrame("Alugar Imóvel");
		janelaAluga.setLayout(new GridLayout(3, 1, 30, 10));
		janelaAluga.setPreferredSize(new Dimension(500, 150));	
		janelaAluga.pack();
		janelaAluga.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaAluga.setLocationRelativeTo(null);

		//Painel Data
		JPanel painelData = new JPanel(new GridLayout());
		JLabel labelData = new JLabel("   Data: ", JLabel.LEFT);
		MaskFormatter mascara = null;
		try {
			mascara = new MaskFormatter("##/##/####");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		//mascara.setPlaceholderCharacter('_');
		campoDataAluga = new JFormattedTextField(mascara);
		painelData.add(labelData);
		painelData.add(campoDataAluga);

		//Codigo
		JPanel painelCodigo = new JPanel(new GridLayout(1, 0));
		JLabel labelCodigo = new JLabel("Código do Cliente: ", JLabel.LEFT);
		campoCodigoCliente = new JFormattedTextField(mascaraCliente);
		campoCodigoCliente.addKeyListener(this);
		painelCodigo.add(labelCodigo);
		painelCodigo.add(campoCodigoCliente);

		// Painel de botões
		JPanel painelBotoes = new JPanel(new GridLayout());  
		botaoOkAluga = new JButton("Ok");		// Ok
		botaoOkAluga.addActionListener(this);
		botaoCancelaAluga = new JButton("Cancelar"); // Sair
		botaoCancelaAluga.addActionListener(this);
		painelBotoes.add(botaoOkAluga);
		painelBotoes.add(botaoCancelaAluga);

		janelaAluga.add(painelCodigo);
		janelaAluga.add (painelData);
		janelaAluga.add(painelBotoes);
		janelaAluga.pack();
		janelaAluga.setVisible(true);
	}

	/**
	 * Tela para inserção do código para pagamento
	 */
	private void telaCodigoPagarAluguel()
	{
		janelaPagarAluguel = new JFrame("Pagar Aluguel");
		janelaPagarAluguel.setLayout(new GridLayout(2, 1, 30, 10));
		janelaPagarAluguel.setPreferredSize(new Dimension(400, 100));	
		janelaPagarAluguel.pack();
		janelaPagarAluguel.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaPagarAluguel.setLocationRelativeTo(null);

		//Codigo
		JPanel painelCodigo = new JPanel(new GridLayout(1, 0));
		JLabel labelCodigo = new JLabel("Código do imóvel: ", JLabel.LEFT);
		campoCodigoImovel = new JFormattedTextField(mascaraImovel);
		campoCodigoImovel.addKeyListener(this);
		painelCodigo.add(labelCodigo);
		painelCodigo.add(campoCodigoImovel);

		// Painel de botões
		JPanel painelBotoes = new JPanel(new GridLayout());  
		botaoOkCodigoPagamento = new JButton("Ok");		// Ok
		botaoOkCodigoPagamento.addActionListener(this);
		botaoCancelaPagamento = new JButton("Cancelar"); // Sair
		botaoCancelaPagamento.addActionListener(this);
		painelBotoes.add(botaoOkCodigoPagamento);
		painelBotoes.add(botaoCancelaPagamento);

		janelaPagarAluguel.add(painelCodigo);
		janelaPagarAluguel.add(painelBotoes);
		janelaPagarAluguel.pack();
		janelaPagarAluguel.setVisible(true);
	}

	/**
	 * Tela com informações sobre o pagamento de aluguel
	 */
	private void telaPagarAluguel(){
		janelaPagarAluguel.dispose();
		janelaPagarAluguel = new JFrame("Pagar Aluguel");
		janelaPagarAluguel.setLayout(new GridLayout(5, 1, 30, 10));
		janelaPagarAluguel.setPreferredSize(new Dimension(420, 230));	
		janelaPagarAluguel.pack();
		janelaPagarAluguel.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaPagarAluguel.setLocationRelativeTo(null);

		//Painel Imovel
		JPanel painelImovel = new JPanel(new GridLayout(1,0));
		JLabel labelImovel = new JLabel("Código do imóvel: ", JLabel.LEFT);
		JTextArea imovel = new JTextArea(String.valueOf(arquivos.getImovelAlugado(campoCodigoImovel.getText()).getCodigo()));
		imovel.setEditable(false);
		painelImovel.add(labelImovel);
		painelImovel.add(imovel);

		//Painel Cliente
		JPanel painelCliente = new JPanel(new GridLayout(1,0));
		JLabel labelCliente = new JLabel("Cliente: ", JLabel.LEFT);
		JTextArea cliente = new JTextArea(arquivos.getImovelAlugado(campoCodigoImovel.getText()).getCliente().getNome());
		cliente.setEditable(false);
		painelCliente.add(labelCliente);
		painelCliente.add(cliente);

		//Painel Valor
		JPanel painelValor = new JPanel(new GridLayout(1,0));
		JLabel labelValor = new JLabel("Valor do aluguel: ", JLabel.LEFT);
		JTextArea valor = new JTextArea(String.valueOf(arquivos.getImovelAlugado(campoCodigoImovel.getText()).getValor()));
		valor.setEditable(false);
		painelValor.add(labelValor);
		painelValor.add(valor);

		//Painel Data de vencimento
		JPanel painelVencimento = new JPanel(new GridLayout(1,0));
		JLabel labelVencimento = new JLabel("Data de vencimento: ", JLabel.LEFT);
		JTextArea vencimento = new JTextArea(String.valueOf(arquivos.getImovelAlugado(campoCodigoImovel.getText()).getDataVencimento()));
		vencimento.setEditable(false);
		painelVencimento.add(labelVencimento);
		painelVencimento.add(vencimento);

		// Painel de botões
		JPanel painelBotoes = new JPanel(new GridLayout());  
		botaoOkPagamento = new JButton("Confirmar Pagamento");		// Ok
		botaoOkPagamento.addActionListener(this);
		botaoCancelaPagamento = new JButton("Cancelar"); // Sair
		botaoCancelaPagamento.addActionListener(this);
		painelBotoes.add(botaoCancelaPagamento);
		painelBotoes.add(botaoOkPagamento);

		janelaPagarAluguel.add(painelImovel);
		janelaPagarAluguel.add(painelCliente);
		janelaPagarAluguel.add(painelValor);
		janelaPagarAluguel.add(painelVencimento);
		janelaPagarAluguel.add(painelBotoes);
		janelaPagarAluguel.setVisible(true);
	}

	/**
	 * Evento dos botões
	 */
	public void actionPerformed(ActionEvent e)
	{
		if( e.getSource() == okLogin)
		{
			verificaFuncionario();
		}
		if( e.getSource() == botaoSair)
		{
			System.exit(0);
		}
		if( e.getSource() == botaoCadastraFuncionario)
		{
			this.telaCadastroFuncionario();
		}
		if( e.getSource() == botaoCancelaCadFun)
		{
			janelaCadastraFuncionario.dispose();
		}
		if( e.getSource() == botaoConfirmaCadFun)
		{
			try {
				decodificaFuncionario();
				JOptionPane.showMessageDialog(null, "Funcionário cadastrado !");
			} catch (InformacoesIncompletasException e1) {
				JOptionPane.showMessageDialog(null, "Complete todos os dados !");
			} catch (CodigoEmUsoException e2){
				JOptionPane.showMessageDialog(null, "Código indisponível!\nInsira outro código");
			}		
		}
		if( e.getSource() == botaoCadastraImovel)
		{
			this.telaCadastroImovel();
		}
		if( e.getSource() == comboTipo)
		{
			System.out.println("combotipo");
			if (comboTipo.getSelectedItem().equals("Casa"))
			{
				campoEdificio.setEditable(false);		
			}
			else if(comboTipo.getSelectedItem().equals("Sala Comercial"))
			{
				campoEdificio.setEditable(false);
				campoDormitorios.setEditable(false);		
			}
			else
			{
				campoEdificio.setEditable(true);
				campoDormitorios.setEditable(true);
			}	
		}
		if( e.getSource() == botaoCancelaCadImovel)
		{
			janelaCadastraImovel.dispose();
		}
		if( e.getSource() == botaoConfirmaCadImovel)
		{
			try {
				decodificaImovel();
				JOptionPane.showMessageDialog(null, "Imóvel Cadastrado !");
			} catch (InformacoesIncompletasException e1) {
				JOptionPane.showMessageDialog(null, "Complete todos os dados !");
			} catch (CodigoEmUsoException e2){
				JOptionPane.showMessageDialog(null, "Código indisponível!\nInsira outro código");
			}
		}
		if( e.getSource() == botaoPesquisaFuncionarios)
		{
			telaTableFuncionarios();
		}
		if( e.getSource() == botaoVoltar)
		{
			janelaPesquisa.dispose();
		}
		if ( e.getSource() == botaoPesquisaImoveis)
		{
			telaProcuraImoveis();
		}
		if ( e.getSource() == botaoOkProcuraImoveis)
		{
			if (comboProcura.getSelectedItem().equals("Casa"))
			{
				telaTableCasas();	
			}
			else if(comboProcura.getSelectedItem().equals("Sala Comercial"))
			{
				telaTableSalas();
			}
			else
			{
				telaTableApartamentos();
			}	
		}
		if ( e.getSource() == botaoVoltarProcura)
		{
			janelaProcuraImoveis.dispose();
		}
		if ( e.getSource() == botaoPromoveFuncionario)
		{
			telaPesquisaFuncionario();
		}
		if ( e.getSource() == botaoOkPesquisaFuncionario)
		{
			if (arquivos.getFuncionario(campoCodigoFuncionario.getText())!=null)
			{
				telaPromoveFuncionario();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Funcionário não Encontrado");
			}
		}
		if ( e.getSource() == botaoCancelaPromove)
		{
			janelaPromove.dispose();
		}
		if ( e.getSource() == botaoOkPromove)
		{
			try {
				decodificaDadosPromove();
			} catch (InformacoesIncompletasException e1) {
				JOptionPane.showMessageDialog(null, "Complete todos os dados !");
			}
		}
		if ( e.getSource() == botaoRemoveImovel)
		{
			telaTipoRemoveImovel();
		}
		if ( e.getSource() == botaoOkTipoRemove)
		{
			janelaRemoveImovel.dispose();
			telaRemoveImovel();
		}
		if ( e.getSource() == botaoVoltarRemove)
		{
			janelaRemoveImovel.dispose();
		}
		if ( e.getSource() == botaoOkRemove)
		{
			if (comboTipoRemove.getSelectedItem().equals("Casa"))
			{
				if (arquivos.getCasa(campoCodigoImovel.getText())!=null)
				{
					arquivos.removeCasa(arquivos.getCasa(campoCodigoImovel.getText()));
					JOptionPane.showMessageDialog(null, "Imóvel excluído do cadastro !");
					janelaRemoveImovel.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Imóvel não encontrado !");
				}
			}
			else if(comboTipoRemove.getSelectedItem().equals("Sala Comercial"))
			{
				if (arquivos.getSala(campoCodigoImovel.getText())!=null)
				{
					arquivos.removeSala(arquivos.getSala(campoCodigoImovel.getText()));
					JOptionPane.showMessageDialog(null, "Imóvel excluído do cadastro !");
					janelaRemoveImovel.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Imóvel não encontrado !");
				}
			}
			else
			{
				if (arquivos.getApartamento(campoCodigoImovel.getText())!=null)
				{
					arquivos.removeApartamento(arquivos.getApartamento(campoCodigoImovel.getText()));
					JOptionPane.showMessageDialog(null, "Imóvel excluído do cadastro !");
					janelaRemoveImovel.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Imóvel não encontrado !");
				}
			}
		}
		if ( e.getSource() == botaoDemiteFuncionario)
		{
			telaDemiteFuncionario();
		}
		if ( e.getSource() == botaoCancelaDemite)
		{
			janelaDemiteFuncionario.dispose();
		}
		if ( e.getSource() == botaoOkFuncionarioDemite)
		{
			if (arquivos.getFuncionario(campoCodigoFuncionario.getText())!=null)
			{
				arquivos.removeFuncionario(arquivos.getFuncionario(campoCodigoFuncionario.getText()));
				JOptionPane.showMessageDialog(null, "Funcionário demitido !");
				janelaDemiteFuncionario.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Funcionário não encontrado !");
			}
		}
		if (e.getSource() == botaoAlugaImovel)
		{
			if (table.getSize()==null)
			{
				JOptionPane.showMessageDialog(null, "Não há imóveis para alugar !");
			}
			else
			{
				int linha = table.getSelectedRow();
				Object a = table.getValueAt(linha, 0);	
				codigoImovelAlugar = String.valueOf(a);
				telaAlugaImovel();
			}
		}
		if (e.getSource() == botaoOkAluga)
		{
			try {
				alugaImovel();
			} catch (InformacoesIncompletasException e1) {
				JOptionPane.showMessageDialog(null, "Complete todos os dados !");
			}
		}
		if (e.getSource() == botaoCancelaAluga)
		{
			janelaAluga.dispose();
		}
		if (e.getSource() == botaoCadastraCliente)
		{
			telaCadastroCliente();
		}
		if (e.getSource() == botaoConfirmaCadCliente)
		{
			try {
				decodificaCliente();
				JOptionPane.showMessageDialog(null, "Cliente cadastrado !");
			} catch (InformacoesIncompletasException e1) {
				JOptionPane.showMessageDialog(null, "Complete todos os dados !");
			} catch (CodigoEmUsoException e2) {
				JOptionPane.showMessageDialog(null, "Código indisponível!\nInsira outro código");
			}
		}
		if (e.getSource() == botaoCancelaCadCliente)
		{
			janelaCadastraCliente.dispose();
		}
		if (e.getSource() == botaoProcuraCliente)
		{
			telaTableClientes();
		}
		if (e.getSource() == botaoPagarAluguel)
		{
			telaCodigoPagarAluguel();
		}
		if (e.getSource() == botaoCancelaPagamento)
		{
			janelaPagarAluguel.dispose();
		}
		if(e.getSource() == botaoOkCodigoPagamento)
		{
			if (arquivos.getImovelAlugado(campoCodigoImovel.getText())!=null)
			{
				telaPagarAluguel();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Imóvel não encontrado nos Imóveis Alugados !");
			}
		}
		if(e.getSource() == botaoOkPagamento)
		{
			arquivos.getImovelAlugado(campoCodigoImovel.getText()).ajustaDataVencimento(); // Ajusta data de vencimento
			JOptionPane.showMessageDialog(null, "Pagamento Confirmado !");
			janelaPagarAluguel.dispose();
		}
	}

	/**
	 * Evento dos campos de texto
	 */
	public void keyTyped(KeyEvent ev)
	{
		String caracteres="0987654321";
		if(!caracteres.contains(ev.getKeyChar()+"")) //Permite apenas números 
		{
			ev.consume();
		} 
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	}
	@Override
	public void keyReleased(KeyEvent arg0) {	
	}
	public boolean getLoginAccepted()
	{
		return loginAccepted;
	}

	/**
	 * Verifica código de funcionário no login
	 */
	private void verificaFuncionario()
	{
		if ( (arquivos.getFuncionario(campoCodigoFuncionario.getText())==null) && (arquivos.getGerente(campoCodigoFuncionario.getText())==null) )
		{
			JOptionPane.showMessageDialog(null, "Funcionário não encontrado !");
		}
		else
		{
			telaPrincipal();
		}
	}

	/**
	 * Decodifica informações de cadastro de um imóvel
	 * @throws InformacoesIncompletasException
	 * @throws CodigoEmUsoException
	 */
	private void decodificaImovel() throws InformacoesIncompletasException, CodigoEmUsoException
	{
		String nome, rua, bairro;
		int cod, num, banheiros, dormitorios, garagem;
		float valor, area;

		if ((campoArea.getText().trim().equals(""))||(campoRua.getText().trim().equals(""))||(campoCodigoImovel.getText().trim().equals(""))||(campoBairro.getText().trim().equals("")) 
				||(campoNum.getText().trim().equals(""))||(campoValor.getText().trim().equals(""))||(campoGaragem.getText().trim().equals(""))||(campoBanheiros.getText().trim().equals("")))
		{
			throw new InformacoesIncompletasException("Informações Incompletas");
		}
		if ( (arquivos.getCasa(campoCodigoImovel.getText())!=null)||(arquivos.getApartamento(campoCodigoImovel.getText())!=null)|| 
				(arquivos.getSala(campoCodigoImovel.getText())!=null) )
		{
			throw new CodigoEmUsoException("Código já está em uso");
		}
		try {
			Integer.parseInt(campoCodigoImovel.getText());
		} catch (NumberFormatException e) {
			throw new InformacoesIncompletasException("Informações Incompletas");
		}

		area = Float.parseFloat(campoArea.getText());
		rua = campoRua.getText();
		bairro = campoBairro.getText();
		cod =  Integer.parseInt(campoCodigoImovel.getText());
		garagem =  Integer.parseInt(campoGaragem.getText());
		num =  Integer.parseInt(campoNum.getText());
		valor = Float.parseFloat(campoValor.getText());
		banheiros =  Integer.parseInt(campoBanheiros.getText());

		if (comboTipo.getSelectedItem().equals("Sala Comercial"))
		{
			SalaComercial i = new SalaComercial(area, new Endereco(rua, num, bairro), valor, cod, banheiros, garagem);
			arquivos.salvaSalaComercial(i);
			janelaCadastraImovel.dispose();
		}
		else
		{
			if ( (campoDormitorios.getText().trim().equals("")))
			{
				throw new InformacoesIncompletasException("Informações Incompletas");
			}
			else
			{	
				dormitorios =  Integer.parseInt(campoDormitorios.getText());
				if (comboTipo.getSelectedItem().equals("Casa"))
				{
					Casa i = new Casa(area, new Endereco(rua,num,bairro),valor,cod, dormitorios, banheiros, garagem);
					arquivos.salvaCasa(i);
					janelaCadastraImovel.dispose();	
				}
				else if (comboTipo.getSelectedItem().equals("Apartamento"))
				{
					if ( campoEdificio.getText().trim().equals(""))
					{
						throw new InformacoesIncompletasException("Informações Incompletas");
					}
					else
					{
						nome = campoEdificio.getText();
						Apartamento i = new Apartamento(area, new Endereco(rua,num,bairro),valor,cod,nome, dormitorios, banheiros, garagem);
						arquivos.salvaApartamento(i);		
						janelaCadastraImovel.dispose();
					}
				}	
			}
		}
	}

	/**
	 * Decodifica informações de cadastro de funcionário
	 * @throws InformacoesIncompletasException
	 * @throws CodigoEmUsoException
	 */
	private void decodificaFuncionario() throws InformacoesIncompletasException, CodigoEmUsoException
	{
		String nome, rua, bairro;
		int cod, id, num, fone;
		float salario;

		if (arquivos.getFuncionario(campoCodigoFuncionario.getText())!=null)
		{
			throw new CodigoEmUsoException("Código já está em uso");
		}
		if ((campoNome.getText().trim().equals(""))||(campoRua.getText().trim().equals(""))||(campoCodigoFuncionario.getText().trim().equals(""))||(campoBairro.getText().trim().equals("")) 
				||(campoNum.getText().trim().equals(""))||(campoSalario.getText().trim().equals(""))||(campoID.getText().trim().equals(""))||(campoFone.getText().trim().equals("")) )
		{
			throw new InformacoesIncompletasException("Informações Incompletas");
		}

		try {
			Integer.parseInt(campoCodigoFuncionario.getText());
		} catch (NumberFormatException e) {
			throw new InformacoesIncompletasException("Informações Incompletas");
		}

		cod =  Integer.parseInt(campoCodigoFuncionario.getText());
		salario = Float.parseFloat(campoSalario.getText());
		nome = campoNome.getText();
		bairro = campoBairro.getText();
		rua = campoRua.getText();
		id =  Integer.parseInt(campoID.getText());
		num =  Integer.parseInt(campoNum.getText());
		fone =  Integer.parseInt(campoFone.getText());

		Funcionario a = new Funcionario (nome,id,fone, new Endereco(rua,num,bairro),salario,cod);
		arquivos.salvaFuncionario(a);	
		janelaCadastraFuncionario.dispose();
	}

	/**
	 * Decodifica informações de cadastro do cliente
	 * @throws InformacoesIncompletasException
	 * @throws CodigoEmUsoException
	 */
	private void decodificaCliente() throws InformacoesIncompletasException, CodigoEmUsoException
	{
		String nome, rua, bairro;
		int cod, id, num, fone;

		if (arquivos.getCliente(campoCodigoCliente.getText())!=null)
		{
			throw new CodigoEmUsoException("Código já está em uso");
		}

		try {
			Integer.parseInt(campoCodigoCliente.getText());
		} catch (Exception e1) {
			throw new InformacoesIncompletasException("Insira um código válido(4 digitos)");
		}

		if ((campoNome.getText().trim().equals(""))||(campoRua.getText().trim().equals(""))||(campoCodigoCliente.getText().trim().equals(""))||(campoBairro.getText().trim().equals("")) 
				||(campoNum.getText().trim().equals(""))||(campoID.getText().trim().equals(""))||(campoFone.getText().trim().equals("")) )
		{
			throw new InformacoesIncompletasException("Informações Incompletas");
		}
		cod =  Integer.parseInt(campoCodigoCliente.getText());
		nome = campoNome.getText();
		bairro = campoBairro.getText();
		rua = campoRua.getText();
		id =  Integer.parseInt(campoID.getText());
		num =  Integer.parseInt(campoNum.getText());
		fone =  Integer.parseInt(campoFone.getText());

		Cliente a = new Cliente (nome,id,fone, new Endereco(rua,num,bairro),cod);
		arquivos.salvaCliente(a);	
		janelaCadastraCliente.dispose();
	}

	/**
	 * Decodifica informações para promover funcionário
	 * @throws InformacoesIncompletasException
	 */
	private void decodificaDadosPromove() throws InformacoesIncompletasException
	{
		if ((campoData.getText().equals("  /  /    "))|| (campoNovoSalario.getText().trim().equals("")))
		{
			throw new InformacoesIncompletasException("Informações Incompletas");
		}

		String dia = "", mes="", ano="";
		dia = campoData.getText().substring(0, campoData.getText().indexOf("/"));
		mes = campoData.getText().substring(3, 5);
		ano = campoData.getText().substring(6, 10);

		Data data = new Data(Integer.parseInt(dia), Integer.parseInt(mes), Integer.parseInt(ano));
		int salario = Integer.parseInt(campoNovoSalario.getText());
		arquivos.promoveFuncionario(arquivos.getFuncionario(campoCodigoFuncionario.getText()), data, salario);
		JOptionPane.showMessageDialog(null, "Funcionário promovido a Gerente !");
		janelaPromove.dispose();

	}

	/**
	 * Aluguel de imóvel
	 * @throws InformacoesIncompletasException
	 */
	private void alugaImovel() throws InformacoesIncompletasException
	{
		String dia = "", mes="", ano="";

		dia = campoDataAluga.getText().substring(0, campoDataAluga.getText().indexOf("/"));
		mes = campoDataAluga.getText().substring(3, 5);
		ano = campoDataAluga.getText().substring(6, 10);

		if (dia.equals("  ")||mes.equals("  ")||ano.equals("    "))
		{
			throw new InformacoesIncompletasException("informações incompletas");
		}

		try {
			Integer.parseInt(campoCodigoCliente.getText());
		} catch (Exception e1) {
			throw new InformacoesIncompletasException("Insira um código válido(4 digitos)");
		}

		System.out.println(arquivos.getCliente(campoCodigoCliente.getText())); /// TÁ RETORNANDO NULL AQUI
		//CLIENTE TEM CODIGO 3, E TA CADASTRADO UM COM CODIGO 1
		//CONDIÇÃO PARA VERIFICAR CODIGO DO CLIENTE AO ALUGA

		if (arquivos.getApartamento(codigoImovelAlugar)!=null)
		{
			Apartamento i = arquivos.getApartamento(codigoImovelAlugar);
			ImovelAlugado ia = new ImovelAlugado(i.getArea(), i.getEndereco(), i.getValor(), i.getCodigo(), new Data(Integer.parseInt(dia), Integer.parseInt(mes), 
					Integer.parseInt(ano)), arquivos.getCliente(campoCodigoCliente.getText()));
			arquivos.tornaAlugado(ia);
			arquivos.removeApartamento(i);

		}
		else if (arquivos.getCasa(codigoImovelAlugar)!=null)
		{
			Casa i = arquivos.getCasa(codigoImovelAlugar);
			ImovelAlugado ia = new ImovelAlugado(i.getArea(), i.getEndereco(), i.getValor(), i.getCodigo(), new Data(Integer.parseInt(dia), Integer.parseInt(mes), 
					Integer.parseInt(ano)), arquivos.getCliente(campoCodigoCliente.getText()));
			arquivos.tornaAlugado(ia);
			arquivos.removeCasa(i);
		}
		else if (arquivos.getSala(codigoImovelAlugar)!=null)
		{
			SalaComercial i = arquivos.getSala(codigoImovelAlugar);
			ImovelAlugado ia = new ImovelAlugado(i.getArea(), i.getEndereco(), i.getValor(), i.getCodigo(), new Data(Integer.parseInt(dia), Integer.parseInt(mes), 
					Integer.parseInt(ano)), arquivos.getCliente(campoCodigoCliente.getText()));
			arquivos.tornaAlugado(ia);
			arquivos.removeSala(i);
		}
		JOptionPane.showMessageDialog(null, "Imóvel Alugado !");
		janelaAluga.dispose();
	}

}
