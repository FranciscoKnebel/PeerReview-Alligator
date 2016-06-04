package Interface;

import Interface.ui.text.UIUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import Interface.ui.TextManager;
import domain.Database;

public class ChairHelperUI implements ChairHelperInterface {
	public static final String EXIT_CODE = "E";

	protected Map<String, AbstractChairHelperCommand> actions;

	public ChairHelperUI(Database database) {
		this.actions = new LinkedHashMap<>();
		this.addAction("A", new ConferenceAllocateCommand(database));
		this.addAction("S", new ReviewPaperCommand(database));
		this.addAction("D", new ConferenceReviewsResultCommand(database));
		
		createAndShowUI();
	}

	public void createAndShowUI() {
		UIUtils uiUtils = UIUtils.INSTANCE;
		String commandKey = null;
		
		do {
			System.out.println();
			System.out.print(getMenu(uiUtils.getTextManager()));
			commandKey = uiUtils.readString(null);
			AbstractChairHelperCommand command = actions.get(commandKey);
			if(command != null) {
				try {
					command.execute();					
				} catch (Exception e) {
					uiUtils.handleUnexceptedError(e);
				}
			}
		} while(!EXIT_CODE.equals(commandKey));
		
	}

	protected String getMenu(TextManager textManager) {
		StringBuffer sb = new StringBuffer();
		sb.append(textManager.getText("message.options", EXIT_CODE, false)).append(":\n");
		for (String key : actions.keySet()) {
			AbstractChairHelperCommand action = actions.get(key);
			sb.append(key).append(" - ").append(textManager.getText(action.getClass()
					.getSimpleName())).append("\n");
		}
		sb.append(textManager.getText("message.choose.option")).append(": ");

		return sb.toString();
	}
	
	protected void addAction(String code, AbstractChairHelperCommand action) {
		this.actions.put(code, action);
	}

}
