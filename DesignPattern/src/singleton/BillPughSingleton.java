package singleton;

public class BillPughSingleton {

    private BillPughSingleton(){}

    private static class SingletonHelper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}

/*
Notice the private inner static class that 
contains the instance of the singleton class. 
 the singleton class is loaded, 
 SingletonHelper class is not loaded into memory 
 and only when someone calls the getInstance() method, 
 this class gets loaded and creates the singleton class instance. 
 This is the most widely used approach for the singleton class 
 as it doesn’t require synchronization.
*/