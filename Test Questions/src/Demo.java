import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

enum FoodType {
	CARNIVOROUS,
	HERBIVOROUS,
	OMNIVOROUS
}

interface LivingBeing {
	void eats(String food);
}

interface Animal extends LivingBeing {
	void sound();
}

abstract class Mammals implements Animal {
	private FoodType foodType;
	String sound;
	List<String> feedsOn;
	
	public Mammals() {
		
	}
	
	public Mammals(FoodType foodType, String sound) {
		feedsOn = new ArrayList<String>();
		this.sound = sound;
		this.foodType = foodType;
	}
}

class Dog extends Mammals {
	String breed;
	
	public Dog(FoodType foodType, String sound) {
		super(foodType, sound);
	}

	@Override
	public void eats(String food) {
		System.out.println("Inside Dog: eats()");
		feedsOn.add(food);
	}

	@Override
	public void sound() {
		System.out.println("Sound: "+sound);
	}
	
}



public class Demo {
	
	public static void main(String[] args) {
		
//		String[] colors = {"red", "green", "blue", "purple"};
//		
//		List<Character> collection  = Arrays.stream(colors).map(color-> color.charAt(0))
//		.collect(Collectors.toCollection(ArrayList::new));
//		
//		collection.add(2, 'y');
//		System.out.println(collection);
		
//		------------------------------------------------
		
//		Map<String,  String> map = new HashMap<>();
//		
//		map.put("colors", "Red, Green, Blue, Purple");
//		
//		List<String[]> list;
//		String[] colors = null;
//		colors = map.entrySet().stream().filter(entrySet-> entrySet.getKey().equalsIgnoreCase("Colors"))
//		.map(entrySet-> entrySet.getValue().replaceAll(" ", "").split(","))
//		.map(colorsArray -> Arrays.stream(colorsArray))
//		.toArray(String[]::new);
//		.flatMap(colorsArray-> Stream.of(colorsArray))/
//		.toArray(size -> new String[size]);
//		.findFirst().orElse(new String[0]);
//		.toArray(String[]::new);
		
//		System.out.println(Arrays.toString(colors));
//		
//		String[] fruits = {"Apple", "Mango", "Banana", "Pineapple"};
//		Stream<String> strm1 = Arrays.stream(fruits);
//		strm1.forEach(System.out::println);
//		
//		Stream<String> strm2 = Stream.of(fruits);
//		strm2.forEach(System.out::println);
//		System.out.println();
//		
//		int[] nums = {5, 2, 7, 1};
//		IntStream strm3 = Arrays.stream(nums);
//		strm3.forEach(System.out::println);
//		
//		Stream<int[]> strm4 = Stream.of(nums);
//		strm4.flatMapToInt(Arrays::stream)
//		.forEach(System.out::println);
		
		
		
//		System.out.println(Arrays.toString(list.get(0)));
		
//		------------------------------------------------
		
//		Dog dog = new Dog(FoodType.OMNIVOROUS, "Bark");
//		System.out.println(dog.feedsOn);
//		System.out.println(dog.sound);
//		dog.eats("Bread");
//		System.out.println(dog.feedsOn);
//		dog.eats("Milk");
//		System.out.println(dog.feedsOn);
//		System.out.println(dog.foodType);
//		
//		LivingBeing animal = new Dog(FoodType.OMNIVOROUS, "Bark");
//		animal.eats("Milk");
		
		List<Integer> list = new ArrayList<>();
		list.add(91);
		list.add(103);
		list.add(86);
//		List<Integer> unmodifiableList = List.of(1,2,3);
//		System.out.println(unmodifiableList.get(1));
//		unmodifiableList.add(5);
		
//		List<Integer> immutableList = List.copyOf(list);
//		List<Integer> immutableList = Collections.unmodifiableList(new ArrayList<>(list));
//		System.out.println(immutableList);
//		immutableList.add(2);
//		System.out.println(list);
//		list.add(2);
//		System.out.println(immutableList);
//		System.out.println(list);
		
		int[] a = {1, 2, 3};
		int[] b = a.clone();
		System.out.println(Arrays.toString(a));
		System.out.println(Arrays.toString(b));
		a[2] = 5;
		System.out.println(Arrays.toString(a));
		System.out.println(Arrays.toString(b));
		
		System.out.println("-------------------------");
		
		int[] c = Arrays.copyOf(a, 2);
		System.out.println(Arrays.toString(a));
		System.out.println(Arrays.toString(c));
		
		a[2] = 12;
		System.out.println(Arrays.toString(a));
		System.out.println(Arrays.toString(c));
		
		System.out.println("-------------------------");
		
		System.arraycopy(a, 0, c, 1, 1);
		
		System.out.println(Arrays.toString(a));
		
		System.out.println(Arrays.toString(c));
		
	}

}
