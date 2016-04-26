package br.univel.model;

import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

public abstract class Model extends AbstractTableModel {

	@Override
	public int getRowCount() {
		return 0;
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
		return null;
	}

}
