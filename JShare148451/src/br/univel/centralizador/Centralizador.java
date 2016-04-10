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
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;

public class Centralizador extends JFrame implements IServer{

	private JPanel contentPane;
	private JTextField txtip;
	private JTextField txtporta;
	private JTextArea txtarea;
	private JButton btnIniciarServico;
	private JButton btnPararServico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Centralizador frame = new Centralizador();
					frame.setVisible(true);
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
		setBounds(100, 100, 549, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{40, 159, 44, 93, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblIp = new JLabel("IP:");
		lblIp.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblIp = new GridBagConstraints();
		gbc_lblIp.insets = new Insets(0, 0, 0, 5);
		gbc_lblIp.anchor = GridBagConstraints.EAST;
		gbc_lblIp.gridx = 0;
		gbc_lblIp.gridy = 0;
		panel.add(lblIp, gbc_lblIp);
		
		txtip = new JTextField();
		GridBagConstraints gbc_txtip = new GridBagConstraints();
		gbc_txtip.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtip.insets = new Insets(0, 0, 0, 5);
		gbc_txtip.gridx = 1;
		gbc_txtip.gridy = 0;
		panel.add(txtip, gbc_txtip);
		txtip.setColumns(10);
		
		JLabel lblPorta = new JLabel("Porta:");
		lblPorta.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblPorta = new GridBagConstraints();
		gbc_lblPorta.anchor = GridBagConstraints.EAST;
		gbc_lblPorta.insets = new Insets(0, 0, 0, 5);
		gbc_lblPorta.gridx = 2;
		gbc_lblPorta.gridy = 0;
		panel.add(lblPorta, gbc_lblPorta);
		
		txtporta = new JTextField();
		GridBagConstraints gbc_txtporta = new GridBagConstraints();
		gbc_txtporta.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtporta.insets = new Insets(0, 0, 0, 5);
		gbc_txtporta.gridx = 3;
		gbc_txtporta.gridy = 0;
		panel.add(txtporta, gbc_txtporta);
		txtporta.setColumns(10);
		
		btnIniciarServico = new JButton("Iniciar Serviço");
		GridBagConstraints gbc_btnIniciarServico = new GridBagConstraints();
		gbc_btnIniciarServico.insets = new Insets(0, 0, 0, 5);
		gbc_btnIniciarServico.gridx = 4;
		gbc_btnIniciarServico.gridy = 0;
		panel.add(btnIniciarServico, gbc_btnIniciarServico);
		
		btnPararServico = new JButton("Parar Serviço");
		GridBagConstraints gbc_btnPararServico = new GridBagConstraints();
		gbc_btnPararServico.gridx = 5;
		gbc_btnPararServico.gridy = 0;
		panel.add(btnPararServico, gbc_btnPararServico);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		txtarea = new JTextArea();
		txtarea.setFont(new Font("Monospaced", Font.PLAIN, 13));
		txtarea.setBackground(Color.WHITE);
		scrollPane.setViewportView(txtarea);
	}
	
	
/**===========================================================================
 *
 * implemetação dos metodos da Interface
 * 
 * ===========================================================================
 */
	
	@Override
	public void registrarCliente(Cliente c) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void publicarListaArquivos(Cliente c, List<Arquivo> lista) throws RemoteException {
		// TODO Auto-generated method stub
		
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
