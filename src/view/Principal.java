package view;

import javax.swing.JOptionPane;

import controller.NetflixController;

public class Principal {

	// Classe principal que chame e execute os m�todos da classe NetflixController.
	public static void main(String[] args) {
		try {
			NetflixController netflixCtrl = new NetflixController();
			String opc;
			
			do {
				opc = JOptionPane.showInputDialog("Menu : \n1 - Gerar arquivos por g�nero; \n2 - Gerar arquivos por ano; \n3 - Lista por estrela; \n4 - Encerrar");
				if (opc == null) {
					JOptionPane.showMessageDialog(null, "Encerrando.");
					System.exit(0);;
				} 
				switch (opc) {
					case "1":
						netflixCtrl.arquivoMajorGenre();
						JOptionPane.showMessageDialog(null, "Arquivos gerados.");
						break;
					case "2":
						netflixCtrl.arquivoPremiereYear();
						JOptionPane.showMessageDialog(null, "Arquivos gerados.");
						break;
					case "3":
						int estrelas;
						do {
							estrelas = Integer.parseInt(JOptionPane.showInputDialog("Digite o número de estrelas: "));
							if (estrelas < 0 || estrelas > 6) {
								JOptionPane.showMessageDialog(null, "Por favor tente novamente com uma valor entre 0-6", "Erro", JOptionPane.ERROR_MESSAGE);
							}
						} while (estrelas < 0 || estrelas > 6);
						netflixCtrl.exibePorEstrelas(estrelas);
						break;
					case "4":
						JOptionPane.showMessageDialog(null, "Encerrando execu��o.");
						break;
					default: 
						JOptionPane.showMessageDialog(null, "Op��o inv�lida!");
						break;
					}
			} while (!opc.equals("4"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro, encerrando execu��o.");
			e.printStackTrace();
		}
	}

}
