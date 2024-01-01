package visitor.example2;

public class Van implements Vehicle{  
    private int storageCapacity;  
    private int numberOfDoors;  
//creating a constructor of the class  
    public Van(int storageCapacity, int numberOfDoors) {  
        this.storageCapacity = storageCapacity;  
        this.numberOfDoors = numberOfDoors;  
    }  
//generating getters and setters  
    public int getStorageCapacity() {  
        return storageCapacity;  
    }  
    public void setStorageCapacity(int storageCapacity) {  
        this.storageCapacity = storageCapacity;  
    }  
    public int getNumberOfDoors() {  
        return numberOfDoors;  
    }  
    public void setNumberOfDoors(int numberOfDoors) {  
        this.numberOfDoors = numberOfDoors;  
    }  
//overrides the accept() method of the Vehicle interface  
    @Override  
    public int accept(VehicleInspector vehicleInspector) {  
        return vehicleInspector.visit(this);  
    }  
}  
