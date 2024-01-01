package visitor.example2;

public class Car implements Vehicle {
	private String color;
	private int manufactureDate;

//creating a constructor of the class  
	public Car(String color, int manufactureDate) {
		this.color = color;
		this.manufactureDate = manufactureDate;
	}

//creating getters and setters  
	public String getColor() {
		return color;
	}

	public void setColor(String color) {

		this.color = color;
	}

	public int getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(int manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

//overrides the accept() method of the Vehicle interface  
	@Override
	public int accept(VehicleInspector vehicleInspector) {
		return vehicleInspector.visit(this);
	}
}
