package visitor.example2;

public class Motorbike implements Vehicle{  
    private int engineCapacity;  
    private String brand;  
//creating constructor of the class  
    public Motorbike(int engineCapacity, String brand) {  
        this.engineCapacity = engineCapacity;  
        this.brand = brand;  
    }  
//generating getters and setters  
    public int getEngineCapacity() {  
        return engineCapacity;  
    }  
    public void setEngineCapacity(int engineCapacity) {  
        this.engineCapacity = engineCapacity;  
    }  
    public String getBrand() {  
        return brand;  
    }  
    public void setBrand(String brand) {  
        this.brand = brand;  
    }  
//overrides the accept() method of the Vehicle interface  
    @Override  
    public int accept(VehicleInspector vehicleInspector) {  
        return vehicleInspector.visit(this);  
    }  
}  
