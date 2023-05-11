package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import br.com.serialexperimentscarina.filaobject.FilaObject;
import model.Serie;

public class NetflixController implements INetflixController {

	public NetflixController() {
		super();
	}

	
	// Método que gere Filas de Objetos por “major_genre” e, o
	// percurso de cada fila gere um novo arquivo CSV (Formado só com os dados constante
	// no objeto) com o nome do “major_genre” que gerou a fila
	@Override
	public void arquivoMajorGenre() throws Exception {
		String[] majorGenres = {"Animation", "Comedy", "Docu-Series", "Drama", "Family Animation", "Family Live Action", "Foreign Language", "Marvel", "Reality", "Talk Show"};
		for (String genre : majorGenres) {
			FilaObject series = geraFila(0, genre, false);
			geraArquivo(series, genre);
		}
	}

	// Método que gere Filas de Objetos por “premiere_year” e, o
	// percurso de cada fila gere um novo arquivo CSV (Formado só com os dados constante
	// no objeto) com o nome do “premiere_year” que gerou a fila
	// (A lista deve conter apenas as séries com “status” renewed)	
	@Override
	public void arquivoPremiereYear() throws Exception {
		for (int i = 2013; i <= 2017; i++) {
			FilaObject series = geraFila(4, String.valueOf(i), true);
			geraArquivo(series, String.valueOf(i));	
		}
	}

	@Override
	public void arquivoImdbRating() {
		// Para próxima aula
	}

	@Override
	public void exibePorEstrelas() {
		// Para próxima aula
	}
	
	//Método privado que leia o arquivo, linha a linha (Desconsiderando o
	//cabeçalho), monte um objeto serie com as informações da linha, e adicione em
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
