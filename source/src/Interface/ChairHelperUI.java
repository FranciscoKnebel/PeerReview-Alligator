package Interface;

public class ChairHelperUI implements ChairHelperInterface {
	
	private int op; //mudança
	private Database database;
	
	public ChairHelperUI(Database database) {
		this.database = database;
		createAndShowUI();
	}

	public void createAndShowUI() {

		requestInfo();
		askCommand();
		
		switch (op) {
			case 1: ReviewPaperCommand command123 = new ReviewPaperCommand( Database ); //duvida
					
			case 2: ReviewPaperCommand command123 = new ReviewPaperCommand( Database ); //duvida// case2
			case 3: ReviewPaperCommand command123 = new ReviewPaperCommand( Database ); //duvida// case2
		
		command123.execute();

	}

	private int requestInfo() {
		
		System.out.println("O que você deseja fazer?\n");
		System.out.println("(1)Atribuir Notas\n(2)Seleção de Artigos\n(3)Alocação de Artigos a Membros do Comitê\n");
		
		
	}

	private void askCommand() { //mudança
		
		Scanner scanner = new Scanner(System.in);
		op = scanner.nextInt(); // ask command
		
	}

}
