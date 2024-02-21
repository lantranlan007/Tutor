package com.example.basic.all;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class StudentService {
	public Student getStudentById(int id) {
		if (id==0) {
			return getDummyData().get(0);
		}
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
