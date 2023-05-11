package view;

import javax.swing.JOptionPane;

import controller.NetflixController;

public class Principal {

	public static void main(String[] args) {
		NetflixController netflixCtrl = new NetflixController();
		String opc;
		
		try {
			do {
				opc = JOptionPane.showInputDialog("Menu : \n1 - Gerar arquivos por gênero; \n2 - Gerar arquivos por ano; \n3 - Encerrar");
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
						JOptionPane.showMessageDialog(null, "Encerrando execução.");
						break;
					default: 
						JOptionPane.showMessageDialog(null, "Opção inválida!");
						break;
					}
			} while (!opc.equals("3"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro, encerrando execução.");
			e.printStackTrace();
		}
	}

}
