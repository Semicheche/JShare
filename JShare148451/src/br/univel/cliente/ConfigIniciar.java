package br.univel.cliente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.dagostini.jshare.comum.pojos.Configure;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class ConfigIniciar extends JDialog {

	/**
	 * @author luciano
	 */
	private static final long serialVersionUID = 1L;
	
	private static final ArrayList<Configure> list = new ArrayList<>();
	private final JPanel contentPanel = new JPanel();
	private Registry registry;
	private String host;
	Remote servidor;
	private JTextField txtupload;
	private JTextField txtdonwload;
	private JCheckBox chckbxAlterarAPasta;
	private String upload;
	private String download;
	private JLabel lblDownload;
	private JLabel lblUpload;


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
		gbl_contentPanel.columnWidths = new int[] { 29, 0, 38, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblConfiguraeDeConexes = new JLabel("Configuraçõe de Download");
			lblConfiguraeDeConexes.setFont(new Font("Tahoma", Font.BOLD, 14));
			GridBagConstraints gbc_lblConfiguraeDeConexes = new GridBagConstraints();
			gbc_lblConfiguraeDeConexes.insets = new Insets(0, 0, 5, 5);
			gbc_lblConfiguraeDeConexes.gridx = 1;
			gbc_lblConfiguraeDeConexes.gridy = 0;
			contentPanel.add(lblConfiguraeDeConexes, gbc_lblConfiguraeDeConexes);
		}
		{
			chckbxAlterarAPasta = new JCheckBox("Alterar a pastas de destino");
			chckbxAlterarAPasta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ablilitarEdicaoPastas();
				}
			});
			GridBagConstraints gbc_chckbxAlterarAPasta = new GridBagConstraints();
			gbc_chckbxAlterarAPasta.insets = new Insets(0, 0, 5, 5);
			gbc_chckbxAlterarAPasta.gridx = 1;
			gbc_chckbxAlterarAPasta.gridy = 1;
			contentPanel.add(chckbxAlterarAPasta, gbc_chckbxAlterarAPasta);
		}
		{
			JLabel lblNewLabel = new JLabel("Default:");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 2;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			lblUpload = new JLabel("C:\\\\JShare\\\\Upload");
			GridBagConstraints gbc_lblUpload = new GridBagConstraints();
			gbc_lblUpload.anchor = GridBagConstraints.WEST;
			gbc_lblUpload.insets = new Insets(0, 0, 5, 5);
			gbc_lblUpload.gridx = 1;
			gbc_lblUpload.gridy = 2;
			contentPanel.add(lblUpload, gbc_lblUpload);
		}
		{
			JLabel lblNome = new JLabel("Pasta de Upload");
			GridBagConstraints gbc_lblNome = new GridBagConstraints();
			gbc_lblNome.insets = new Insets(0, 0, 5, 5);
			gbc_lblNome.gridx = 0;
			gbc_lblNome.gridy = 3;
			contentPanel.add(lblNome, gbc_lblNome);
		}
		{
			txtupload = new JTextField("");
			txtupload.setEnabled(false);
			txtupload.setEditable(false);
			GridBagConstraints gbc_txtupload = new GridBagConstraints();
			gbc_txtupload.gridwidth = 3;
			gbc_txtupload.insets = new Insets(0, 0, 5, 0);
			gbc_txtupload.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtupload.gridx = 1;
			gbc_txtupload.gridy = 3;
			contentPanel.add(txtupload, gbc_txtupload);
			txtupload.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Default:");
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_2.gridx = 0;
			gbc_lblNewLabel_2.gridy = 4;
			contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		}
		{
			lblDownload = new JLabel("C:\\\\JShare\\\\Download");
			GridBagConstraints gbc_lblDownload = new GridBagConstraints();
			gbc_lblDownload.anchor = GridBagConstraints.WEST;
			gbc_lblDownload.insets = new Insets(0, 0, 5, 5);
			gbc_lblDownload.gridx = 1;
			gbc_lblDownload.gridy = 4;
			contentPanel.add(lblDownload, gbc_lblDownload);
		}
		{
			JLabel lblPastaDeDownload = new JLabel("Pasta de Download");
			GridBagConstraints gbc_lblPastaDeDownload = new GridBagConstraints();
			gbc_lblPastaDeDownload.insets = new Insets(0, 0, 5, 5);
			gbc_lblPastaDeDownload.gridx = 0;
			gbc_lblPastaDeDownload.gridy = 5;
			contentPanel.add(lblPastaDeDownload, gbc_lblPastaDeDownload);
		}
		{
			txtdonwload = new JTextField();
			txtdonwload.setEditable(false);
			txtdonwload.setEnabled(false);
			GridBagConstraints gbc_txtdonwload = new GridBagConstraints();
			gbc_txtdonwload.gridwidth = 3;
			gbc_txtdonwload.insets = new Insets(0, 0, 5, 0);
			gbc_txtdonwload.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtdonwload.gridx = 1;
			gbc_txtdonwload.gridy = 5;
			contentPanel.add(txtdonwload, gbc_txtdonwload);
			txtdonwload.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aplicar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						aplicarConfiguracoes();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
		
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	
		configurar();
	}

	private void configurar() {
	
		upload = lblUpload.getText();	
		download = lblDownload.getText();
		txtdonwload.setText(download);
		
	}

	public void aplicarConfiguracoes() {
				
		Configure conf = Configure.getInstance();
		
		
		download = txtdonwload.getText();
		upload = txtupload.getText();	
		
		conf.setDestinoUpload(upload);
		conf.setDestinoDownload(download);
	
		
		ablilitarEdicaoPastas();
		
	}

	protected void ablilitarEdicaoPastas() {
		if(chckbxAlterarAPasta.isSelected()){
			txtdonwload.setEditable(true);
			txtdonwload.setEnabled(true);
			txtupload.setEditable(true);
			txtupload.setEnabled(true);
		}else{
			txtdonwload.setEditable(false);
			txtdonwload.setEnabled(false);
			txtupload.setEditable(false);
			txtupload.setEnabled(false);
		}
		
	}

}
