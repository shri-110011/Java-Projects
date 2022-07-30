package LambdaBasics;

public class Greeter {
	
	public void greet(Greeting greeting) {
		greeting .perform();
	}

	public static void main(String[] args) {
		Greeter greeter = new Greeter();

		Greeting helloWorldGreeting = new HelloWorldGreeting();
		greeter.greet(helloWorldGreeting);
		
		Greeting innerGreeting = new Greeting() {
			
			@Override
			public void perform() {
				System.out.println("Inside Annonymous inner class: Hello World!");
				
			}
		};
		greeter.greet(innerGreeting);
		
		Greeting lambdaGreeting = () -> System.out.println("Inside Lamda expression: Hello World!"); 
		greeter.greet(lambdaGreeting);
		
		greeter.greet(()->{System.out.println("Inside inline Lamda expression: Hello World!");});
		
	}

}
