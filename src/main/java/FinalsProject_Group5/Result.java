package FinalsProject_Group5;

public class Result {

	// exercise is the same key that ScoringService and AnimalMapper use, ex. "PUSH_UPS"
	final String exercise;
	// type is either "STRENGTH" or "CARDIO", the Session filters with it
	final String type;
	final int reps;
	final double score;
	final String animal;
	final String level;

	Result(String exercise, String type, int reps, double score, String animal, String level) {
		this.exercise = exercise;
		this.type = type;
		this.reps = reps;
		this.score = score;
		this.animal = animal;
		this.level = level;
	}

}
