package br.univel.utils;

import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.crypto.AEADBadTagException;
import javax.swing.text.BadLocationException;

import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comum.pojos.Configure;
import br.dagostini.jshare.comum.pojos.Diretorio;
import br.dagostini.jshare.comun.Cliente;
import br.univel.cliente.ConfigIniciar;

public class ListarArquivos {
	
	private List<Arquivo> listaArquivos = new ArrayList<>();

	public List<Arquivo> listarArquivos() {
		
		
		File dirStart = new File(Configure.getInstance().getDestinoUpload());
		
		List<Diretorio> listaDiretorios = new ArrayList<>();
		for (File file : dirStart.listFiles()) {
			if (file.isFile()) {
				Arquivo arq = new Arquivo();
				arq.setNomeArquivo(file.getName());
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
	
	public File pegarArquivo(String nome){
		return new File("C:\\Upload\\"+ nome);
	}
	
}
