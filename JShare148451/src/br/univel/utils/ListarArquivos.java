package br.univel.utils;

import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.BadLocationException;

import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comum.pojos.Diretorio;

public class ListarArquivos {
	
	private List<Arquivo> listaArquivos = new ArrayList<>();

	public List<Arquivo> listarArquivos() {

		File dirStart = new File("C:\\Upload");

		List<Diretorio> listaDiretorios = new ArrayList<>();
		for (File file : dirStart.listFiles()) {
			if (file.isFile()) {
				Arquivo arq = new Arquivo();
				arq.setNome(file.getName());
				arq.setTamanho(file.length());
				listaArquivos.add(arq);
			} else {
				Diretorio dir = new Diretorio();
				dir.setNome(file.getName());
				listaDiretorios.add(dir);
			}
		}

		return listaArquivos;
	}
	
// public void pesquisa(String string){
//	try {
//
//		String nome = string;
//
//		Object listArquivos;
//
//		for (List<Arquivo> list : listArquivos) {
//			String dadoBuscado = nome;
//			String nomedoArquivo = "";
//			for (int i = 0; i < list.size(); i++) {
//				nomedoArquivo = list.get(i).getNome();
//				if (string.getText().isEmpty())
//					return;
//				if (nome.equals(nomedoArquivo.substring(0, nome.length())))
//					string.getDocument().insertString(string.getCaretPosition(),
//							nomedoArquivo.toString().substring(nome.length(), nomedoArquivo.length()),
//							null);
//
//			}
//
//		}
//
//	} catch (RemoteException e) {
//		e.printStackTrace();
//	} catch (BadLocationException e) {
//		e.printStackTrace();
//	} 
// }
}
