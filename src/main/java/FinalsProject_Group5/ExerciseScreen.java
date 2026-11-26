package FinalsProject_Group5;

public abstract class ExerciseScreen extends Screen {
	
	static final int DURATION = 3;

	// set to true when the user chooses Stop workout, the AssessmentRunner checks it
	boolean stopped = false;
	
	// counts down from 60 to 0, the screen is redrawn every second
	void runTimer(String title) {
		for (int seconds = DURATION; seconds >= 0; seconds--) {
			clear();
			renderTimer(title, seconds);

			if (seconds > 0) {
				waitOneSecond();
			}
		}
		input.nextLine();
	}

	void renderTimer(String title, int seconds) {
		String time = String.format("%02d:%02d", seconds / 60, seconds % 60);
		String message = (seconds > 0) ? "GO! Keep your form clean." : "TIME'S UP!";

		System.out.println(font.Cyan() + "=".repeat(72));
		font.Green();
		font.Bold();
		System.out.println(" ".repeat((72 - title.length())/2) + title + RESET);
		System.out.println(font.Cyan() + "=".repeat(72) + RESET);

		font.Yellow();
		font.Bold();
		System.out.println("\n" + " ".repeat((72 - time.length())/2) + time + RESET);

		font.White();
		System.out.println("\n" + " ".repeat((72 - message.length())/2) + message + RESET);

		System.out.println("\n" + font.Cyan() + "=".repeat(72) + RESET);
	}

	// stops the program for one second
	void waitOneSecond() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
