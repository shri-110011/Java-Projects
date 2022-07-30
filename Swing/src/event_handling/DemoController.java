package event_handling;

public class DemoController {

	ChatConsole cc = new ChatConsole();
	
	public DemoController() {
		cc.setMessageListener( new MessageListener() {
			public void messageSent(String msg) {
				System.out.println("Inside DemoController class: msg: "+msg);
			}
		});
		cc.sendMessage();
	}
	
	public static void main(String[] args) {
		new DemoController();
	}
	
}
