package br.dagostini.jshare.comum.pojos;

public class Configure {
	private static Configure conf;
	static String destinoUpload;
	static String destinoDownload;
	public boolean checked;
	
	private Configure(){
		
	}
	
	public String getDestinoUpload() {
		return destinoUpload;
	}
	public void setDestinoUpload(String destnoUpload) {
		this.destinoUpload = destnoUpload;
	}
	public String getDestinoDownload() {
		return destinoDownload;
	}
	public void setDestinoDownload(String destnoDownload) {
		this.destinoDownload = destnoDownload;
	}
	
	public static Configure getInstance(){
		if(conf == null)
			conf = new Configure();
			
		return conf;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
	return null;
	}
	
}
