package com.example.basic.all;

import java.util.ArrayList;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

@Service
@EnableCaching
public class StudentService {
	
	@Cacheable("student")
	public Student getStudentById(int id) {
		if (id==0) {
			System.out.println("Hit DB get id 0");
			return getDummyData().get(0);
		}
		System.out.println("Hit DB get id 1");
		return getDummyData().get(1);
	}
	private ArrayList<Student> getDummyData() {
		ArrayList<Student> dummyData = new ArrayList<>();
		Student dat = new Student(0, "Dat");
		Student manh = new Student(1, "Manh");
		dummyData.add(dat);
		dummyData.add(manh);
		return dummyData;
	}
}
