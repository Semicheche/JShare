package br.univel.centralizador;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comun.Cliente;
import br.dagostini.jshare.comun.IServer;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.rmi.NoSuchObjectException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.Map.Entry;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Centralizador extends JFrame implements IServer {

	private JPanel contentPane;
	private JTextField txtporta;
	private JTextArea txtarea;
	private JButton btnIniciarServico;
	private JButton btnPararServico;
	private JComboBox comboip;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Centralizador frame = new Centralizador();
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
	public Centralizador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 40, 159, 44, 93, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblIp = new JLabel("IP:");
		lblIp.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblIp = new GridBagConstraints();
		gbc_lblIp.insets = new Insets(0, 0, 0, 5);
		gbc_lblIp.anchor = GridBagConstraints.EAST;
		gbc_lblIp.gridx = 0;
		gbc_lblIp.gridy = 0;
		panel.add(lblIp, gbc_lblIp);

		comboip = new JComboBox();
		GridBagConstraints gbc_comboip = new GridBagConstraints();
		gbc_comboip.insets = new Insets(0, 0, 0, 5);
		gbc_comboip.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboip.gridx = 1;
		gbc_comboip.gridy = 0;
		panel.add(comboip, gbc_comboip);

		JLabel lblPorta = new JLabel("Porta:");
		lblPorta.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblPorta = new GridBagConstraints();
		gbc_lblPorta.anchor = GridBagConstraints.EAST;
		gbc_lblPorta.insets = new Insets(0, 0, 0, 5);
		gbc_lblPorta.gridx = 2;
		gbc_lblPorta.gridy = 0;
		panel.add(lblPorta, gbc_lblPorta);

		txtporta = new JTextField();
		txtporta.setText("1818");
		GridBagConstraints gbc_txtporta = new GridBagConstraints();
		gbc_txtporta.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtporta.insets = new Insets(0, 0, 0, 5);
		gbc_txtporta.gridx = 3;
		gbc_txtporta.gridy = 0;
		panel.add(txtporta, gbc_txtporta);
		txtporta.setColumns(10);

		btnIniciarServico = new JButton("Iniciar Serviço");
		btnIniciarServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarServico();
			}
		});
		GridBagConstraints gbc_btnIniciarServico = new GridBagConstraints();
		gbc_btnIniciarServico.insets = new Insets(0, 0, 0, 5);
		gbc_btnIniciarServico.gridx = 4;
		gbc_btnIniciarServico.gridy = 0;
		panel.add(btnIniciarServico, gbc_btnIniciarServico);

		btnPararServico = new JButton("Parar Serviço");
		btnPararServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pararServico();
			}
		});
		GridBagConstraints gbc_btnPararServico = new GridBagConstraints();
		gbc_btnPararServico.gridx = 5;
		gbc_btnPararServico.gridy = 0;
		panel.add(btnPararServico, gbc_btnPararServico);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		txtarea = new JTextArea();
		txtarea.setForeground(new Color(51, 204, 0));
		txtarea.setFont(new Font("Monospaced", Font.PLAIN, 13));
		txtarea.setBackground(Color.BLACK);
		scrollPane.setViewportView(txtarea);
	}
	
	/**
	 * =======================================
	 * 
	 * Metodos implementados
	 * 
	 * =======================================
	 */
	
	private Remote servidor;
	private Registry registry;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy H:mm:ss:SSS");
	private Map<Cliente, List<Arquivo>> arquivosDosClientes = new HashMap<>();
	
	
	
	protected void configurar() {

		btnIniciarServico.setEnabled(true);

		List<String> lista = getIpDisponivel();
		comboip.setModel(new DefaultComboBoxModel<String>(new Vector<String>(lista)));
		comboip.setSelectedIndex(0);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				fecharTodosClientes();
			}
			
		});
	
	}
	public void iniciarServico(){
		
		String porta = txtporta.getText().trim();
		
		
		if(!porta.matches("[0-9]+") || porta.length() > 5 ){
			JOptionPane.showMessageDialog(this, "A porta deve ser um valor numérico de no máximo 5 dígitos!");
			return;
		}
		int intPorta = Integer.parseInt(porta);
		if(intPorta < 1024  || intPorta > 65535){
			JOptionPane.showMessageDialog(this, "A porta deve estar entre 1024 e 65535");
			return;
		}
		
		
		try {
			
			servidor = UnicastRemoteObject.exportObject(this, 0);
			registry = LocateRegistry.createRegistry(intPorta);
			registry.rebind(NOME_SERVICO, servidor);
			
			mostrar("Serivço iniciado.");
			
			comboip.setEnabled(false);
			txtporta.setEnabled(false);
			btnIniciarServico.setEnabled(false);
			
			btnPararServico.setEnabled(true);
			
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(this, "Erro criando registro, verifique se a porta já não está sendo usada.");
			e.printStackTrace();
		}
		
		
		
	}
	
	public void pararServico(){
		mostrar("SERVIDOR PARANDO O SERVIÇO.");
		
	//	fecharTodosCLientes();
		
		try {
			UnicastRemoteObject.unexportObject(this, true);
			UnicastRemoteObject.unexportObject(registry, true);
		} catch (NoSuchObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		comboip.setEnabled(true);
		txtporta.setEnabled(true);
		btnIniciarServico.setEnabled(true);
		
		btnPararServico.setEnabled(false);
		
		mostrar("Serviço Encerrado.");
		
	}

	private void mostrar(String string) {
		txtarea.append(sdf.format(new Date()));
		txtarea.append(" -> ");
		txtarea.append(string);
		txtarea.append("\n");
		
	}

	protected void fecharTodosClientes() {
		mostrar("DESCONECTANDO TODOS OS CLIENTES.");
		
	}

	public List<String> getIpDisponivel() {

		List<String> listIp = new ArrayList<String>();

		try {
			Enumeration<NetworkInterface> iface = NetworkInterface.getNetworkInterfaces();

			while (iface.hasMoreElements()) {
				NetworkInterface netifc = iface.nextElement();
				if (netifc.isUp()) {
					Enumeration<InetAddress> addresses = netifc.getInetAddresses();
					while (addresses.hasMoreElements()) {

						InetAddress addr = addresses.nextElement();

						String ip = addr.getHostAddress();

						if (ip.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}")) {
							listIp.add(ip);
						}
					}
				}

			}
		} catch (SocketException e) {
			e.printStackTrace();
		}

		return listIp;
	}

	/**
	 * =========================================================================
	 *
	 * implemetação dos metodos da Interface
	 * 
	 * =========================================================================
	 */

	@Override
	public void registrarCliente(Cliente c) throws RemoteException {
		mostrar(c.getNome()+" => Se Registrou com o IP:"+c.getIp() +":"+c.getPorta());		

	}

	@Override
	public void publicarListaArquivos(Cliente c, List<Arquivo> lista) throws RemoteException {
		mostrar(c.getNome() + " => Publicou " + lista.size() + " Arquivo(s): " + lista +"\n" );
		arquivosDosClientes.put(c, lista);
	}

	@Override
	public Map<Cliente, List<Arquivo>> procurarArquivo(String nome) throws RemoteException {
		
		return arquivosDosClientes;
	}

	@Override
	public byte[] baixarArquivo(Arquivo arq) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void desconectar(Cliente c) throws RemoteException {
		mostrar(c.getNome()+ " => Desconectou-se");

	}

}
