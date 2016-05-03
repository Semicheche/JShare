package br.univel.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ManipuladorDeArquivos {
	
	public void escreva(File file, byte[] dados) {
		try {
			Files.write(Paths.get(file.getPath()), dados, StandardOpenOption.CREATE);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public byte[] leia(Object arq) {
		
		Path path = Paths.get(((File) arq).getPath());
		try {
			byte[] dados = Files.readAllBytes(path);
			return dados;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
