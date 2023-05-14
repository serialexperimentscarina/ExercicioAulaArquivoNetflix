package controller;

import model.Serie;

public interface ITabelaController {
	
	public void adiciona(Serie serie) throws Exception;
	public void lista(int estrela) throws Exception;

}
