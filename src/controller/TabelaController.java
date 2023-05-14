package controller;

import br.com.serialexperimentscarina.listaobject.ListaObject;
import model.Serie;

public class TabelaController implements ITabelaController{

	ListaObject[] tabelaDeEspalhamento;
	
	public TabelaController() {
		tabelaDeEspalhamento = new ListaObject[7];
		
		for (int i = 0; i < 7; i++) {
			tabelaDeEspalhamento[i] = new ListaObject();
		}
	}

	@Override
	public void adiciona(Serie serie) throws Exception {
		int hash = serie.hashCode();
		ListaObject l = tabelaDeEspalhamento[hash];
		
		l.addFirst(serie);
	}

	@Override
	public void lista(int estrela) throws Exception {
		ListaObject l = tabelaDeEspalhamento[estrela];
		int tamanho = l.size();
		
		for (int i = 0; i < tamanho; i++) {
			System.out.println(l.get(i));
		}
		
	}

}
