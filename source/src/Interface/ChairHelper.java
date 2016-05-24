package Interface;

public interface ChairHelper {

	public void main(String args){
		Database database = new Database();
		ChairHelperUI chairHelperUI = new ChairHelperUI(database);
		
	}
}
