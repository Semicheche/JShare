package br.dagostini.jshare.comum.pojos;

public class Download {
	
	private String origem;
	private String destino;
	private float porgresso;
	private String ip;
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

}
