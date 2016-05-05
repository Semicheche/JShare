package br.dagostini.jshare.comum.pojos;

import java.awt.Component;

public class Download {
	
	private String nome;
	private String origem;
	private String destino;
	private float porgresso;
	private String ip;
	private Arquivo arquivo;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public float getPorgresso() {
		return porgresso;
	}
	public void setPorgresso(float porgresso) {
		this.porgresso = porgresso;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Arquivo getArquivo() {
		return arquivo;
	}
	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

}
