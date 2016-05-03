package br.univel.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.dagostini.jshare.comum.pojos.Download;

public class Model extends AbstractTableModel {

	List<Object> lista;
	
	public Model(){
		
	}
	
	public Model(List list) {
		lista = list;
	}
	
	@Override
	public int getRowCount() {
		return 1;
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
		case 4:
			return "Opção";

		default:
			return"Erro";
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		switch (columnIndex) {
		case 0:
			return "";
		case 1:
			return "";
		case 2:
			return "";
		case 3:
			return "";
		case 4:
			return "";

		default:
			return"Erro";
		}
	}

}
