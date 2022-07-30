package event_handling;

public class ChatConsole {
	
	private String msg = "Hello";
	private MessageListener ml;
	
	public String getMessage() {
		return msg;
	}
	
	public void setMessageListener(MessageListener ml) {
		this.ml = ml;
	}
	
	public void sendMessage() {
		ml.messageSent(getMessage());
	}

}
