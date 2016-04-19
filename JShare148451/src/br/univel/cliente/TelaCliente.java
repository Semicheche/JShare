package br.univel.cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtpesquisa;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCliente frame = new TelaCliente();
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
	public TelaCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 358);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnConectar = new JMenu("Configurações");
		menuBar.add(mnConectar);
		
		JMenuItem mntmIniciar = new JMenuItem("Iniciar");
		mntmIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfigIniciar config = new ConfigIniciar();
				config.setVisible(true);
			}
		});
		mnConectar.add(mntmIniciar);
		
		JMenuItem mntmConectar = new JMenuItem("Conectar");
		mnConectar.add(mntmConectar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{31, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		txtpesquisa = new JTextField();
		GridBagConstraints gbc_txtpesquisa = new GridBagConstraints();
		gbc_txtpesquisa.gridwidth = 4;
		gbc_txtpesquisa.insets = new Insets(0, 0, 0, 5);
		gbc_txtpesquisa.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtpesquisa.gridx = 0;
		gbc_txtpesquisa.gridy = 0;
		panel.add(txtpesquisa, gbc_txtpesquisa);
		txtpesquisa.setColumns(10);
		
		JButton btnPerquisar = new JButton("Perquisar");
		GridBagConstraints gbc_btnPerquisar = new GridBagConstraints();
		gbc_btnPerquisar.gridx = 4;
		gbc_btnPerquisar.gridy = 0;
		panel.add(btnPerquisar, gbc_btnPerquisar);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

}
