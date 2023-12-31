package singleton;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class SingletonSerializedTest {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        SerializedSingleton instanceOne = SerializedSingleton.getInstance();
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream(
                "filename.ser"));
        out.writeObject(instanceOne);
        out.close();

        // deserialize from file to object
        ObjectInput in = new ObjectInputStream(new FileInputStream(
                "filename.ser"));
        SerializedSingleton instanceTwo = (SerializedSingleton) in.readObject();
        in.close();

        System.out.println("instanceOne hashCode="+instanceOne.hashCode());
        System.out.println("instanceTwo hashCode="+instanceTwo.hashCode());

    }

}

//https://www.baeldung.com/java-serialization-readobject-vs-readresolve

/*
readObject() is an existing method in ObjectInputStream class. 
At the time of deserialization readObject() method internally checks 
whether the object that is being deserialized has readResolve() method implemented. 
If readResolve() method exists then it will be invoked

A sample readResolve() implementation would look like this

protected Object readResolve() {
  return INSTANCE:
}
So, the intent of writing readResolve() method is to ensure 
that the same object that lives in JVM is returned 
instead of creating new object during deserialization.
*/