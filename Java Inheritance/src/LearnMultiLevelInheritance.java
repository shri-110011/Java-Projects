
class BigCat{
	public void eat() {
		System.out.println("BigCat is eating!");
	}
}

class Lion extends BigCat{
	public void sound() {
		System.out.println("Roar Roar!");
	}
}

class LionCub extends Lion{
	public void sound() {
		System.out.println("Weep Weep!");
	}
}

public class LearnMultiLevelInheritance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Lion obj1 = new Lion();
		LionCub obj2 = new LionCub();
		obj1.sound();
		obj2.sound();

	}

}
