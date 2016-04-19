package br.univel.cliente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.dagostini.jshare.comun.Cliente;
import br.dagostini.jshare.comun.IServer;
import br.univel.centralizador.Centralizador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;
import java.util.Vector;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.awt.event.ActionEvent;

public class ConfigIniciar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPorta;
	private JComboBox comboIp;
	private Registry registry;
	private String host;
	Centralizador servidor;
	private JTextField txtnome;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConfigIniciar dialog = new ConfigIniciar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.configurar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Create the dialog.
	 */
	public ConfigIniciar() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{29, 0, 38, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblConfiguraeDeConexes = new JLabel("Configuraçõe de Conexão");
			lblConfiguraeDeConexes.setFont(new Font("Tahoma", Font.BOLD, 14));
			GridBagConstraints gbc_lblConfiguraeDeConexes = new GridBagConstraints();
			gbc_lblConfiguraeDeConexes.insets = new Insets(0, 0, 5, 5);
			gbc_lblConfiguraeDeConexes.gridx = 1;
			gbc_lblConfiguraeDeConexes.gridy = 0;
			contentPanel.add(lblConfiguraeDeConexes, gbc_lblConfiguraeDeConexes);
		}
		{
			JLabel lblNome = new JLabel("Nome:");
			GridBagConstraints gbc_lblNome = new GridBagConstraints();
			gbc_lblNome.insets = new Insets(0, 0, 5, 5);
			gbc_lblNome.gridx = 0;
			gbc_lblNome.gridy = 1;
			contentPanel.add(lblNome, gbc_lblNome);
		}
		{
			txtnome = new JTextField();
			GridBagConstraints gbc_txtnome = new GridBagConstraints();
			gbc_txtnome.insets = new Insets(0, 0, 5, 5);
			gbc_txtnome.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtnome.gridx = 1;
			gbc_txtnome.gridy = 1;
			contentPanel.add(txtnome, gbc_txtnome);
			txtnome.setColumns(10);
		}
		{
			JLabel lblIp = new JLabel("IP:");
			GridBagConstraints gbc_lblIp = new GridBagConstraints();
			gbc_lblIp.insets = new Insets(0, 0, 0, 5);
			gbc_lblIp.anchor = GridBagConstraints.EAST;
			gbc_lblIp.gridx = 0;
			gbc_lblIp.gridy = 2;
			contentPanel.add(lblIp, gbc_lblIp);
		}
		{
			comboIp = new JComboBox();
			GridBagConstraints gbc_comboIp = new GridBagConstraints();
			gbc_comboIp.insets = new Insets(0, 0, 0, 5);
			gbc_comboIp.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboIp.gridx = 1;
			gbc_comboIp.gridy = 2;
			contentPanel.add(comboIp, gbc_comboIp);
		}
		{
			JLabel lblPorta = new JLabel("Porta:");
			GridBagConstraints gbc_lblPorta = new GridBagConstraints();
			gbc_lblPorta.insets = new Insets(0, 0, 0, 5);
			gbc_lblPorta.anchor = GridBagConstraints.EAST;
			gbc_lblPorta.gridx = 2;
			gbc_lblPorta.gridy = 2;
			contentPanel.add(lblPorta, gbc_lblPorta);
		}
		{
			txtPorta = new JTextField();
			GridBagConstraints gbc_txtPorta = new GridBagConstraints();
			gbc_txtPorta.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtPorta.gridx = 3;
			gbc_txtPorta.gridy = 2;
			contentPanel.add(txtPorta, gbc_txtPorta);
			txtPorta.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Conectar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					 iniciarServico();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	protected void iniciarServico() {
		
		String strPorta = txtPorta.getText();
		
		if (!strPorta.matches("[0-9]+") || strPorta.length() > 5) {
			JOptionPane.showMessageDialog(this, "A porta deve ser um valor numérico de no máximo 5 dígitos!");
			return;
		}
		
		int intPorta = Integer.parseInt(strPorta);
		try {
			registry = LocateRegistry.getRegistry(host, intPorta);
			servidor = (Centralizador) registry.lookup(IServer.NOME_SERVICO);
			
			Cliente c = new Cliente();
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		
	}


	private void configurar() {
				
		List<String> listIp = new Centralizador().getIpDisponivel();
		comboIp.setModel(new DefaultComboBoxModel<String>(new Vector<String>(listIp)));
		comboIp.setSelectedIndex(0);
		
		
	}

}
