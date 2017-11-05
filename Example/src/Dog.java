
public class Dog {
	
	private String colour = "blue";
	private int age;
	
	
	
	public Dog(String colour, int age) {
		super();
		this.colour = colour;
		this.age = age;
	}


	public void bark() {
		System.out.println("Bark!");
	}


	public String getColour() {
		return colour;
	}


	public void setColour(String colour) {
		this.colour = colour;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}
	
	
	

}
