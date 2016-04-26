package br.univel.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import br.dagostini.jshare.comum.pojos.Download;

public abstract class Model extends AbstractTableModel {

	ArrayList<Download> lista = new ArrayList<>();
	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}
	
	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Origem";
		case 1:
			return "IP:porta";
		case 2:
			return "Progesso";
		case 3:
			return "Arquivo";

		default:
			return"Erro";
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		switch (columnIndex) {
		case 0:
			return lista.get(rowIndex).getOrigem();
		case 1:
			return lista.get(rowIndex).getIp();
		case 2:
			return lista.get(rowIndex).getPorgresso();
		case 3:
			return lista.get(rowIndex).getDestino();

		default:
			return"Erro";
		}
	}

}
