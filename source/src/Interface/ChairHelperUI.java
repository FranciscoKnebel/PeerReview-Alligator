package Interface;

public class ChairHelperUI implements ChairHelperInterface {
	
	private int op; //mudan�a
	private Database database;
	
	public ChairHelperUI(Database database) {
		this.database = database;
	}

	public void createAndShowUI() {

		requestInfo();
		askCommand();
		
		switch (op) {
			case 1: ReviewPaperCommand comando123 = new ReviewPaperCommand( Database ); //duvida
					
			case 2: // case2
			case 3: // c
		}

	}

	private int requestInfo() {
		
		System.out.println("O que voc� deseja fazer?\n");
		System.out.println("(1)Atribuir Notas\n(2)Sele��o de Artigos\n(3)Aloca��o de Artigos a Membros do Comit�\n");
		
		
	}

	private void askCommand() { //mudan�a
		
		Scanner scanner = new Scanner(System.in);
		op = scanner.nextInt(); // ask command
		
	}

}