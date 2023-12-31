package singleton;

public class EagerInitializedSingleton {

	private static final EagerInitializedSingleton instance 
										= new EagerInitializedSingleton();

	// private constructor to avoid client applications using the constructor
	private EagerInitializedSingleton() {
	}

	public static EagerInitializedSingleton getInstance() {
		return instance;
	}
}

//https://www.digitalocean.com/community/tutorials
///java-singleton-design-pattern-best-practices-examples#1-eager-initialization