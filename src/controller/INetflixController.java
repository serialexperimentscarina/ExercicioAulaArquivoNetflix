package controller;

public interface INetflixController {
	
	public void arquivoMajorGenre() throws Exception;
	public void arquivoPremiereYear() throws Exception;
	public void geraTabela() throws Exception;
	public void exibePorEstrelas(int estrela) throws Exception ;
}
