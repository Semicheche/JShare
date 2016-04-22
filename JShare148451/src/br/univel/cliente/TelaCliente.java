package br.univel.cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;

import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comum.pojos.Diretorio;
import br.dagostini.jshare.comun.Cliente;
import br.dagostini.jshare.comun.IServer;
import br.univel.centralizador.Centralizador;

import javax.swing.JTextField;
import java.awt.FlowLayout;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.PopupMenu;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Choice;
import javax.swing.JSpinner;
import javax.swing.ImageIcon;

public class TelaCliente extends JFrame implements IServer {

	private JPanel contentPane;
	private JTextField txtpesquisa;
	private JTable table;
	private IServer servidor;
	private Registry registry;
	private JButton btnPerquisar;
	private JLabel lblIp;
	private JTextField txtPorta;
	private JComboBox comboip;
	private JLabel lblPorta;
	private JButton btnConectar;
	private JButton btnDesconectar;
	private JTextField txtnome;
	private JLabel lblNome;
	Cliente c = new Cliente();
	private Collection<List<Arquivo>> listArquivos;
	private JButton btnBaixarArquivo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCliente frame = new TelaCliente();
					frame.setVisible(true);
					frame.configurar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 42, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		lblNome = new JLabel("Nome");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		panel.add(lblNome, gbc_lblNome);

		txtnome = new JTextField();
		txtnome.setText("Luciano");
		GridBagConstraints gbc_txtnome = new GridBagConstraints();
		gbc_txtnome.insets = new Insets(0, 0, 5, 5);
		gbc_txtnome.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtnome.gridx = 1;
		gbc_txtnome.gridy = 0;
		panel.add(txtnome, gbc_txtnome);
		txtnome.setColumns(10);

		lblIp = new JLabel("IP:");
		GridBagConstraints gbc_lblIp = new GridBagConstraints();
		gbc_lblIp.anchor = GridBagConstraints.EAST;
		gbc_lblIp.insets = new Insets(0, 0, 5, 5);
		gbc_lblIp.gridx = 0;
		gbc_lblIp.gridy = 1;
		panel.add(lblIp, gbc_lblIp);

		comboip = new JComboBox();
		GridBagConstraints gbc_comboip = new GridBagConstraints();
		gbc_comboip.insets = new Insets(0, 0, 5, 5);
		gbc_comboip.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboip.gridx = 1;
		gbc_comboip.gridy = 1;
		panel.add(comboip, gbc_comboip);

		lblPorta = new JLabel("Porta:");
		GridBagConstraints gbc_lblPorta = new GridBagConstraints();
		gbc_lblPorta.insets = new Insets(0, 0, 5, 5);
		gbc_lblPorta.anchor = GridBagConstraints.EAST;
		gbc_lblPorta.gridx = 2;
		gbc_lblPorta.gridy = 1;
		panel.add(lblPorta, gbc_lblPorta);

		txtPorta = new JTextField();
		txtPorta.setText("1818");
		GridBagConstraints gbc_txtPorta = new GridBagConstraints();
		gbc_txtPorta.insets = new Insets(0, 0, 5, 5);
		gbc_txtPorta.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPorta.gridx = 3;
		gbc_txtPorta.gridy = 1;
		panel.add(txtPorta, gbc_txtPorta);
		txtPorta.setColumns(10);

		btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarServico();
			}
		});
		GridBagConstraints gbc_btnConectar = new GridBagConstraints();
		gbc_btnConectar.insets = new Insets(0, 0, 5, 5);
		gbc_btnConectar.gridx = 4;
		gbc_btnConectar.gridy = 1;
		panel.add(btnConectar, gbc_btnConectar);

		btnDesconectar = new JButton("Desconectar");
		btnDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desconectar();
			}
		});
		btnDesconectar.setEnabled(false);
		GridBagConstraints gbc_btnDesconectar = new GridBagConstraints();
		gbc_btnDesconectar.insets = new Insets(0, 0, 5, 0);
		gbc_btnDesconectar.gridx = 5;
		gbc_btnDesconectar.gridy = 1;
		panel.add(btnDesconectar, gbc_btnDesconectar);

		txtpesquisa = new JTextField();
		txtpesquisa.addKeyListener(new KeyAdapter() {
			
		});
		txtpesquisa.setEnabled(false);
		txtpesquisa.setEditable(false);
		GridBagConstraints gbc_txtpesquisa = new GridBagConstraints();
		gbc_txtpesquisa.gridwidth = 5;
		gbc_txtpesquisa.insets = new Insets(0, 0, 5, 5);
		gbc_txtpesquisa.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtpesquisa.gridx = 0;
		gbc_txtpesquisa.gridy = 2;
		panel.add(txtpesquisa, gbc_txtpesquisa);
		txtpesquisa.setColumns(10);

		btnPerquisar = new JButton("Pesquisar");
		btnPerquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisa();
			}
		});
		GridBagConstraints gbc_btnPerquisar = new GridBagConstraints();
		gbc_btnPerquisar.insets = new Insets(0, 0, 5, 0);
		gbc_btnPerquisar.gridx = 5;
		gbc_btnPerquisar.gridy = 2;
		panel.add(btnPerquisar, gbc_btnPerquisar);
		
		btnBaixarArquivo = new JButton("Baixar Arquivo");
		btnBaixarArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnBaixarArquivo = new GridBagConstraints();
		gbc_btnBaixarArquivo.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBaixarArquivo.gridwidth = 2;
		gbc_btnBaixarArquivo.insets = new Insets(0, 0, 5, 0);
		gbc_btnBaixarArquivo.gridx = 4;
		gbc_btnBaixarArquivo.gridy = 3;
		panel.add(btnBaixarArquivo, gbc_btnBaixarArquivo);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);
	}

	protected void pesquisa() {
	
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
	
					String nome = txtpesquisa.getDocument().getText(0, txtpesquisa.getDocument().getLength());
			
					listArquivos = servidor.procurarArquivo(nome).values();
					
					for (List<Arquivo> list : listArquivos) {
						String dadoBuscado = nome;
						String nomedoArquivo = "";
						for (int i = 0; i < list.size(); i++) {
							nomedoArquivo = list.get(i).getNome();
							if(txtpesquisa.getText().isEmpty())
								return;
							if (nome.equals(nomedoArquivo.substring(0, nome.length()))) 
								txtpesquisa.getDocument().insertString(txtpesquisa.getCaretPosition(), nomedoArquivo.toString().substring(nome.length(), nomedoArquivo.length()) , null);
								
						
						}
							
					}
						
			

					
					
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (BadLocationException e) {
					e.printStackTrace();
				}// TODO Auto-generated method stub
				
			}
		}).start();
		
		
	}	

	protected void desconectar() {
		try {

			if (servidor != null) {
				servidor.desconectar(c);
				finalbotoes();
				UnicastRemoteObject.unexportObject(this, true);

				servidor = null;

			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	protected void configurar() {

		btnPerquisar.setEnabled(false);

		List<String> listIp = new Centralizador().getIpDisponivel();
		comboip.setModel(new DefaultComboBoxModel<String>(new Vector<String>(listIp)));
		comboip.setSelectedIndex(0);
		

	}

	protected List<Arquivo> listarArquivos() {

		File dirStart = new File("C:\\Upload");

		List<Arquivo> listaArquivos = new ArrayList<>();
		List<Diretorio> listaDiretorios = new ArrayList<>();
		for (File file : dirStart.listFiles()) {
			if (file.isFile()) {
				Arquivo arq = new Arquivo();
				arq.setNome(file.getName());
				arq.setTamanho(file.length());
				listaArquivos.add(arq);
			} else {
				Diretorio dir = new Diretorio();
				dir.setNome(file.getName());
				listaDiretorios.add(dir);
			}
		}

//		System.out.println("Diretórios");
//		for (Diretorio dir : listaDiretorios) {
//			System.out.println("\t" + dir.getNome());
//		}
//
//		System.out.println("Arquivos");
//		for (Arquivo arq : listaArquivos) {
//			System.out.println("\t" + arq.getTamanho() + "\t" + arq.getNome());
//		}
		return listaArquivos;
	}

	protected void iniciarServico() {

		String strPorta = txtPorta.getText();
		String host = comboip.getSelectedItem().toString();
		String nome = txtnome.getText();
		int intPorta = Integer.parseInt(strPorta);

		if (!strPorta.matches("[0-9]+") || strPorta.length() > 5) {
			JOptionPane.showMessageDialog(this, "A porta deve ser um valor numérico de no máximo 5 dígitos!");
			return;
		}

		try {
			registry = LocateRegistry.getRegistry(host, intPorta);
			servidor = (IServer) registry.lookup(IServer.NOME_SERVICO);

			c.setIp(host);
			c.setPorta(intPorta);
			c.setNome(nome);

			servidor.registrarCliente(c);
			servidor.publicarListaArquivos(c, listarArquivos());
			

			iniciobotoes();
			

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	private void iniciobotoes() {
		txtnome.setEnabled(false);
		txtPorta.setEnabled(false);
		comboip.setEnabled(false);
		btnDesconectar.setEnabled(true);
		btnPerquisar.setEnabled(true);
		txtpesquisa.setEnabled(true);
		txtpesquisa.setEditable(true);
		btnConectar.setEnabled(false);
	}

	private void finalbotoes() {
		txtnome.setEnabled(true);
		txtPorta.setEnabled(true);
		comboip.setEnabled(true);
		btnDesconectar.setEnabled(false);
		btnPerquisar.setEnabled(false);
		txtpesquisa.setEnabled(false);
		txtpesquisa.setEditable(false);
		btnConectar.setEnabled(true);
	}
	
	

	@Override
	public void registrarCliente(Cliente c) throws RemoteException {

	}

	@Override
	public void publicarListaArquivos(Cliente c, List<Arquivo> lista) throws RemoteException {

	}

	@Override
	public Map<Cliente, List<Arquivo>> procurarArquivo(String nome) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] baixarArquivo(Arquivo arq) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void desconectar(Cliente c) throws RemoteException {
		// TODO Auto-generated method stub

	}

}
