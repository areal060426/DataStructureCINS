package FinalsProject_Group5;

import java.util.ArrayList;
import java.util.List;

public class Session {

	final Person person;
	private final List<Result> results = new ArrayList<>();

	Session(Person person) {
		this.person = person;
	}

	// called by an exercise screen after it computes the result
	void addResult(Result result) {
		results.add(result);
	}

	List<Result> getResults() {
		return new ArrayList<>(results);
	}

	List<Result> getStrengthResults() {
		return filter("STRENGTH");
	}

	List<Result> getCardioResults() {
		return filter("CARDIO");
	}

	private List<Result> filter(String type) {
		List<Result> found = new ArrayList<>();
		for (Result result : results) {
			if (result.type.equals(type)) {
				found.add(result);
			}
		}
		return found;
	}

}
