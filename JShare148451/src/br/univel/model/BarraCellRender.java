package br.univel.model;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class BarraCellRender extends JProgressBar implements TableCellRenderer {

	public BarraCellRender() {
		setStringPainted(true);
		setBackground(Color.WHITE);
		setForeground(Color.BLUE);
		setBorder(null);
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		return this;
	}

}
