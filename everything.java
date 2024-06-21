Give me a very simple example of custom annotation in java

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // Annotation applicable to methods
@Retention(RetentionPolicy.RUNTIME) // Annotation retained at runtime
public @interface LogExecutionTime {
}

public class MyClass {

    @LogExecutionTime
    public void performTask() {
        // Method logic goes here
        System.out.println("Task performed.");
    }

    public static void main(String[] args) {
        MyClass obj = new MyClass();
        obj.performTask(); // This method will be annotated and logged for execution time
    }
}

import java.lang.reflect.Method;

public class MyAspect {

    public void logExecutionTime(Object obj) {
        Class<?> clazz = obj.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(LogExecutionTime.class)) {
                long startTime = System.currentTimeMillis();
                try {
                    method.invoke(obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                long endTime = System.currentTimeMillis();
                System.out.println("Method " + method.getName() + " executed in " + (endTime - startTime) + "ms");
            }
        }
    }

    public static void main(String[] args) {
        MyClass obj = new MyClass();
        MyAspect aspect = new MyAspect();
        aspect.logExecutionTime(obj); // This will log execution time for annotated methods
    }
}

<mat-card class="example-card">
  <mat-card-header>
    <div mat-card-avatar class="example-header-image"></div>
    <mat-card-title>John Doe</mat-card-title>
    <mat-card-subtitle>Exam Details</mat-card-subtitle>
  </mat-card-header>
  <img mat-card-image src="https://via.placeholder.com/150" alt="Photo of John Doe">
  <mat-card-content>
    <p>
      <strong>Roll Number:</strong> 123456789<br>
      <strong>Form Number:</strong> MAT202312345<br>
      <strong>Date of Birth:</strong> 01-Jan-1995<br>
      <strong>Gender:</strong> Male<br>
      <strong>Test Date:</strong> 25-Jun-2024<br>
      <strong>Test Time:</strong> 10:00 AM - 12:30 PM<br>
      <strong>Reporting Time:</strong> 9:00 AM<br>
      <strong>Test Mode:</strong> Computer-Based Test (CBT)<br>
      <strong>Test Center Address:</strong> ABC Institute, 1234 Elm Street, Springfield, IL
    </p>
  </mat-card-content>
  <mat-card-actions>
    <button mat-button>Print</button>
    <button mat-button>Download</button>
  </mat-card-actions>
</mat-card>

.example-card {
  max-width: 400px;
  margin: 20px auto;
}

.example-header-image {
  background-image: url('https://via.placeholder.com/40');
  background-size: cover;
}