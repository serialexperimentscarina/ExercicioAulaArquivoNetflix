package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import br.com.serialexperimentscarina.filaobject.FilaObject;
import model.Serie;

public class NetflixController implements INetflixController {

	public NetflixController() {
		super();
	}

	@Override
	public void arquivoMajorGenre() throws Exception {

	}

	@Override
	public void arquivoPremiereYear() throws Exception {
		
	}

	@Override
	public void arquivoImdbRating() {
		// Para próxima aula
	}

	@Override
	public void exibePorEstrelas() {
		// Para próxima aula
	}
	
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
				if (vetLinha[coluna].equals(parametro)) {
					if (!renewedOnly || vetLinha[6].equals("Renewed")) {
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
				}
				linha = bufferReader.readLine();
			}
			bufferReader.close();
			InStrReader.close();
			fInStr.close();
		}
		return series;
	}

}
