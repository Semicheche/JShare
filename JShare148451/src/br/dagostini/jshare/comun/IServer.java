package br.dagostini.jshare.comun;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import br.dagostini.jshare.comum.pojos.Arquivo;

public interface IServer extends Remote {

	public static final String NOME_SERVICO = "JShare";

	/**
	 * Recebe informações de um novo cliente.
	 * 
	 * @param c
	 * @throws RemoteException
	 */
	public void registrarCliente(Cliente c) throws RemoteException;

	/**
	 * Recebe a lista de arquivos disponíveis no cliente.
	 * 
	 * @param c
	 * @param lista
	 * @throws RemoteException
	 */
	public void publicarListaArquivos(Cliente c, List<Arquivo> lista)
			throws RemoteException;

	/**
	 * Usado quando um cliente deseja procurar um arquivo pelo nome, o
	 * servidor lê todos os arquivos publicados e retorna uma mapa contendo
	 * os resultados em cada cliente.
	 * 
	 * @param nome
	 * @return
	 * @throws RemoteException
	 */
	public Map<Cliente, List<Arquivo>> procurarArquivo(String nome)
			throws RemoteException;

	/**
	 * Recebe informações do arquivo e retorna o arquivo em formato
	 * de array de bytes. 
	 * 
	 * @param arq
	 * @return
	 * @throws RemoteException
	 */
	public byte[] baixarArquivo(Arquivo arq) throws RemoteException;

	/**
	 * Desconecta o cliente, tornando também indisponível seus arquivos
	 * para as buscas. 
	 * 
	 * @param c
	 * @throws RemoteException
	 */
	public void desconectar(Cliente c) throws RemoteException;

}
