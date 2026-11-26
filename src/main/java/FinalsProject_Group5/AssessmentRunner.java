package FinalsProject_Group5;

import java.util.ArrayDeque;
import java.util.Queue;

public class AssessmentRunner {

	boolean includeStrength;
	boolean includeCardio;
	
	Session session;

	// choice is the menu number from SelectAssessmentScreen: 1 strength, 2 cardio, 3 both
	AssessmentRunner(String choice, Session session) {
		this.session = session;
		switch(choice) {
		case "1" -> includeStrength = true;
		case "2" -> includeCardio = true;
		case "3" -> {
			includeStrength = true;
			includeCardio = true;
		}
		default -> throw new IllegalArgumentException("Invalid assessment choice: " + choice);
		}
	}

	void start() {
		if (includeStrength) {
			if (!runExercises(strengthQueue())) {
				return;
			}
			showStrengthAnalysis();
		}

		if (includeCardio) {
			if (!runExercises(cardioQueue())) {
				return;
			}
			showCardioAnalysis();
		}
	}

	// the order of the exercises is decided here
	Queue<ExerciseScreen> strengthQueue() {
		Queue<ExerciseScreen> queue = new ArrayDeque<>();
		queue.add(new PushUpScreen(session));
		queue.add(new SitUpScreen(session));
		queue.add(new SquatScreen(session));
		return queue;
	}

	Queue<ExerciseScreen> cardioQueue() {
		Queue<ExerciseScreen> queue = new ArrayDeque<>();
		queue.add(new JumpingJacksScreen(session));
		queue.add(new HighKneesScreen(session));
		queue.add(new MountainClimbersScreen(session));
		return queue;
	}

	// shows the exercises one by one, returns false if the user stopped the workout
	boolean runExercises(Queue<ExerciseScreen> queue) {
		while(!queue.isEmpty()) {
			ExerciseScreen screen = queue.poll();
			screen.show();

			if (screen.stopped) {
				queue.clear();
				return false;
			}
		}
		return true;
	}

	void showStrengthAnalysis() {
		new StrengthAnalysisScreen(session).show();
	}

	void showCardioAnalysis() {
		new CardioAnalysisScreen(session).show();
	}

}
