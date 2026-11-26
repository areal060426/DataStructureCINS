package FinalsProject_Group5;

public class Person {
	
	private final String firstName;
	private final String lastName;
	private final int age;
	private final String sex;
	private double height;
	private double weight;
	
	Person(String firstName, String lastName, String sex,  int age, double height, double weight){
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}
	
	String getName() {
		return firstName;
	}
	
	String getLastName() {
		return lastName;
	}
	
	String getSex() {
		return sex;
	}
	
	int getAge() {
		return age;
	}
	
	double getHeight() {
		return height;
	}
	
	double getWeight() {
		return weight;
	}
	
}
