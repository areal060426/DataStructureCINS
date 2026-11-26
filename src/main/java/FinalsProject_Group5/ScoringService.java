package FinalsProject_Group5;

import java.util.HashMap;
import java.util.Map;

public class ScoringService {
	
	private final Map<String, Integer> baseTargets = new HashMap<>();
	Person person;
	String exercise;
	
	ScoringService(Person person, String exercise){
		baseTargets.put("PUSH_UPS", 30);
		baseTargets.put("SIT_UPS", 35);
		baseTargets.put("SQUATS", 40);
		baseTargets.put("JUMPING_JACKS", 50);
		baseTargets.put("HIGH_KNEES", 80);
		baseTargets.put("MOUNTAIN_CLIMBERS", 40);
		this.person = person;
		this.exercise = exercise;
	}
	
	double getScore(int reps) {
		double target = getTarget(exercise, person.getAge(), person.getSex());
		if (reps < 0 || target <= 0) {
			throw new IllegalArgumentException("Reps must be 0 or more and target must be above 0");
		}

		double score = (reps / target) * 100;
		return Math.min(score, 100);
	}
	
	double getTarget(String exercise, int age, String sex) {
		return baseTargets.get(exercise) * getAgeFactor(age) * getSexFactor(sex);
	}
	
	double getAgeFactor(int age) {
		if (age < 30) {
			return 1.0;
		}
		else if (age < 40) {
			return 0.9;
		}
		else if (age < 50) {
			return 0.8;
		}
		return 0.7;
	}
	
	double getSexFactor(String sex) {
		if (sex.trim().equalsIgnoreCase("M")) {
			return 1.0;
		}
		else if (sex.trim().equalsIgnoreCase("F")) {
			return 0.8;
		}
		return 0.9;
	}
	
	String getLevel(double score) {
		if (score < 36) {
			return "DEVELOPING";
		}
		else if (score < 66) {
			return "INTERMEDIATE";
		}
		return "ADVANCED";
	}
}
