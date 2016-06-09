package Interface;

import Interface.ui.text.UIUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import Interface.commands.ConferenceAllocateCommand;
import Interface.commands.ConferenceReviewsResultCommand;
import Interface.commands.ReviewPaperCommand;
import Interface.ui.TextManager;
import domain.Database;

public class ChairHelperUI implements ChairHelperInterface {
	public static final String EXIT_CODE = "E";
	protected UIUtils uiUtils = UIUtils.INSTANCE;

	protected Map<String, ChairHelperInterface> actions;

	public ChairHelperUI(Database database) {
		this.actions = new LinkedHashMap<>();
		this.addAction("A", new ReviewPaperCommand(uiUtils, database));
		this.addAction("S", new ConferenceReviewsResultCommand(uiUtils, database));
		this.addAction("D", new ConferenceAllocateCommand(uiUtils, database));
	} 

	public void createAndShowUI() {
		String commandKey = null;
		TextManager textManager = uiUtils.getTextManager();
		
		do {
			System.out.println();
			System.out.print(getMenu(textManager));
			commandKey = uiUtils.readString(null).toUpperCase();
			ChairHelperInterface command = actions.get(commandKey);
			if(command != null) {
				try {
					command.execute();			
				} catch (Exception e) {
					uiUtils.handleUnexceptedError(e);
				}
			}
			else if (!EXIT_CODE.equals(commandKey)){
				System.out.print(invalidOption(textManager, commandKey));				
			}
		} while(!EXIT_CODE.equals(commandKey));
		
		System.out.print(textManager.getText("message.exit"));
	}
	
	@Override
	public void execute() {
		createAndShowUI();
	}

	protected String getMenu(TextManager textManager) {
		StringBuffer sb = new StringBuffer();
		sb.append(textManager.getText("application.title")).append("\n");
		sb.append(textManager.getText("message.options", EXIT_CODE, false)).append(":\n");
		for (String key : actions.keySet()) {
			ChairHelperInterface action = actions.get(key);
			sb.append(key).append(" - ").append(textManager.getText(action.getClass()
					.getSimpleName())).append("\n");
		}
		sb.append(textManager.getText("message.option.choose")).append(": ");


		return sb.toString();
	}
	
	protected void addAction(String code, ChairHelperInterface action) {
		this.actions.put(code, action);
	}
	
	protected String invalidOption(TextManager textManager, String commandKey) {
		StringBuffer sb = new StringBuffer();
		sb.append(textManager.getText("message.invalidOption", commandKey));
		
		return sb.toString();
	}

}
