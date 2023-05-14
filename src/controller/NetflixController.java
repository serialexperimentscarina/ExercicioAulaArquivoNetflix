package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import br.com.serialexperimentscarina.filaobject.FilaObject;
import model.Serie;

public class NetflixController implements INetflixController {

	TabelaController tabela;
	
	public NetflixController() throws Exception {
		tabela = new TabelaController();
		geraTabela();
	}

	
	// M�todo que gere Filas de Objetos por �major_genre� e, o
	// percurso de cada fila gere um novo arquivo CSV (Formado s� com os dados constante
	// no objeto) com o nome do �major_genre� que gerou a fila
	@Override
	public void arquivoMajorGenre() throws Exception {
		String[] majorGenres = {"Animation", "Comedy", "Docu-Series", "Drama", "Family Animation", "Family Live Action", "Foreign Language", "Marvel", "Reality", "Talk Show"};
		for (String genre : majorGenres) {
			FilaObject series = geraFila(0, genre, false);
			geraArquivo(series, genre);
		}
	}

	// M�todo que gere Filas de Objetos por �premiere_year� e, o
	// percurso de cada fila gere um novo arquivo CSV (Formado s� com os dados constante
	// no objeto) com o nome do �premiere_year� que gerou a fila
	// (A lista deve conter apenas as s�ries com �status� renewed)	
	@Override
	public void arquivoPremiereYear() throws Exception {
		for (int i = 2013; i <= 2017; i++) {
			FilaObject series = geraFila(4, String.valueOf(i), true);
			geraArquivo(series, String.valueOf(i));	
		}
	}

	
	// Método que gere uma tabela de espalhamento para classificar as séries por estrelas. Séries com
	// “imdb_ra�ng” entre 0 – 15 (0 estrelas), entre 16 – 30 (1 estrela), entre 31 – 45 (2
	// estrelas), entre 46 – 60 (3 estrelas), entre 61 – 75 (4 estrelas), entre 76 – 90 (5 estrelas)
	// e entre 91 – 100 (6 estrelas)
	@Override
	public void geraTabela() throws Exception {
		File netflixSeries = new File("C:\\TEMP\\netflixSeries.txt");
		
		if(netflixSeries.exists() && netflixSeries.isFile()) {
			FileInputStream fInStr = new FileInputStream(netflixSeries);
			InputStreamReader InStrReader = new InputStreamReader(fInStr);
			BufferedReader bufferReader = new BufferedReader(InStrReader);
			String linha = bufferReader.readLine();
			linha = bufferReader.readLine();
			
			while (linha != null) {
				String[] vetLinha = linha.split(";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
				Serie serie = new Serie();
				serie.major_genre = vetLinha[0];
				serie.title = vetLinha[1];
				serie.subgenre = vetLinha[2];
				serie.premiere_year = Integer.parseInt(vetLinha[4]);
				serie.episodes = vetLinha[10] + " episodes";
				serie.status = vetLinha[6];
				serie.imdb_rating = Integer.parseInt(vetLinha[11]);
				tabela.adiciona(serie);
				linha = bufferReader.readLine();
			}
			bufferReader.close();
			InStrReader.close();
			fInStr.close();
		}
	}

	// Método que permita buscar na tabela de espalhamento, as séries pela quan�dade de estrelas e exiba,
	// no console, todas as séries que estejam classificadas para aquela estrela
	@Override
	public void exibePorEstrelas(int estrela) throws Exception {
		tabela.lista(estrela);
	}
	
	//M�todo privado que leia o arquivo, linha a linha (Desconsiderando o
	//cabe�alho), monte um objeto serie com as informa��es da linha, e adicione em
	//uma Fila de Objetos.
	private FilaObject geraFila(int coluna, String parametro, boolean renewedOnly) throws Exception {
		File netflixSeries = new File("C:\\TEMP\\netflixSeries.txt");
		FilaObject series = new FilaObject();
		
		if(netflixSeries.exists() && netflixSeries.isFile()) {
			FileInputStream fInStr = new FileInputStream(netflixSeries);
			InputStreamReader InStrReader = new InputStreamReader(fInStr);
			BufferedReader bufferReader = new BufferedReader(InStrReader);
			String linha = bufferReader.readLine();
			linha = bufferReader.readLine();
			
			while (linha != null) {
				String[] vetLinha = linha.split(";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
				if (vetLinha[coluna].equals(parametro) && (!renewedOnly || vetLinha[6].equals("Renewed"))) {
					Serie serie = new Serie();
					serie.major_genre = vetLinha[0];
					serie.title = vetLinha[1];
					serie.subgenre = vetLinha[2];
					serie.premiere_year = Integer.parseInt(vetLinha[4]);
					serie.episodes = vetLinha[10] + " episodes";
					serie.status = vetLinha[6];
					serie.imdb_rating = Integer.parseInt(vetLinha[11]);
					series.insert(serie);
				}
				linha = bufferReader.readLine();
			}
			bufferReader.close();
			InStrReader.close();
			fInStr.close();
		}
		return series;
	}
	
	private void geraArquivo(FilaObject fila, String nome) throws Exception {
		File arquivo = new File("C:\\TEMP", (nome + ".csv"));
		
		StringBuffer buffer = new StringBuffer();
		FileWriter fWriter = new FileWriter(arquivo);
		PrintWriter pWriter = new PrintWriter(fWriter);
		
		while (!fila.isEmpty()) {
			Serie serie = (Serie) fila.remove();
			buffer.append(serie.toString());
		}
		pWriter.write(buffer.toString());
		pWriter.flush();
		pWriter.close();
		fWriter.close();
	}

}
