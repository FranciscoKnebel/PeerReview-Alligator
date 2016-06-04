package Interface;

import domain.Database;

public class ChairHelper {
	
	public void main(String args){
		Database database = new Database();
		ChairHelperUI chairHelperUI = new ChairHelperUI(database);
		chairHelperUI.createAndShowUI();
	}

}
