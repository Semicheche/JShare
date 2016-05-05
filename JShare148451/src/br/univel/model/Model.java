package br.univel.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.table.AbstractTableModel;

import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comum.pojos.Download;
import br.dagostini.jshare.comun.Cliente;

public class Model extends AbstractTableModel {
	private int linhas = 0;
	private Object[][] matriz;
	private Map mapa;
	
	public Model() {
	}
	public Model(Map<Cliente, List<Arquivo>> mapa) {
		pesquisa(mapa);
		System.out.println(matriz);
		System.out.println(mapa.size());
		fireTableDataChanged();
	}
	
	@Override
	public int getRowCount() {
		return 0;
	}

	@Override
	public int getColumnCount() {
		return 5;
	}
	
	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Nome Cliente";
		case 1:
			return "IP:porta";
		case 2:
			return "Progesso";
		case 3:
			return "Nome Arquivo";
		case 4:
			return "Tamanho";

		default:
			return"Erro";
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		fireTableDataChanged();
		return matriz[rowIndex][columnIndex];
		}
	
	public void pesquisa(Map<Cliente, List<Arquivo>> mapa){
		
		this.mapa = mapa;
		
		for (Entry<Cliente, List<Arquivo>> e : mapa.entrySet()) {
			linhas += e.getValue().size();
			fireTableDataChanged();
		}
		
		matriz = (Object[][]) new Object[linhas][5];
		
		int linha = 0;
		
		for (Entry<Cliente, List<Arquivo>> e : mapa.entrySet()){
			for (Arquivo arq : e.getValue()) {
				matriz[linha][0] = e.getKey().getNome();
				matriz[linha][1] = e.getKey().getIp()+ e.getKey().getPorta();
				matriz[linha][2] = e.getValue().get(linha).getNomeArquivo();
				matriz[linha][3] = e.getValue().get(linha).getTamanho();			
				System.out.println(matriz[linha][2]);
			}
		}
		fireTableDataChanged();
		
		
	}
	
	
}
