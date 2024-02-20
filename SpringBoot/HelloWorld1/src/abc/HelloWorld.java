package abc;

public class HelloWorld {
	public static void main(String[] args) {
		int a=0;
		int b=8;
		System.out.print(a+b);
		Employee peter = new Employee("Peter", 10);
		peter.setName("Peter M");
		//ArrayList 
		
	}

}
//oop : object oriented programming
//oop : inheritance, encapsulation, 
class Employee{
	private String name;
	
	public Employee(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		//if ()
		this.name = name;
	}
	private int age;
	
}

class Manager extends Employee{

	public Manager(String name, int age) {
		super(name, age);
		// TODO Auto-generated constructor stub
	}
	
	private String skill;
}
