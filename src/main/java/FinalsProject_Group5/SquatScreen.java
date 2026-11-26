package FinalsProject_Group5;

public class SquatScreen extends ExerciseScreen {

	static final int MAX_REPS = 200;

	int reps;
	
	Session session;
	
	SquatScreen(Session session){
		this.session = session;
	}

	int score;
	String animal;
	String level;

	void show() {
		clear();
		render();
		handleInput();
		
		runTimer("SQUAT");

		clear();
		reps = askReps();
		
		ScoringService scoring = new ScoringService(session.person, "SQUATS");
		score = (int) scoring.getScore(reps);
		animal = new AnimalMapper().getAnimal("SQUATS", score);
		level = scoring.getLevel(score);
		session.addResult(new Result("SQUATS", "STRENGTH", reps, score, animal, level));

		clear();
		renderProcessing();
		pause(1500);

		boolean done = false;
		while(!done) {
			clear();
			renderResult();
			done = handleResultInput();
		}
	}

	// instruction screen
	void render() {
		printHeader("SQUATS", "Duration: 1 MINUTE");

		System.out.println("\n" + font.Red() + "INSTRUCTIONS:" + RESET);
		String instructions = """
				Perform as many squats as you can
				within one minute. Make sure
				to maintain proper exercise form.
				""";
		font.White();
		System.out.println(instructions.indent(4) + RESET);

		System.out.println(font.Cyan() + "-".repeat(72) + RESET);
		font.LightGreen();
		centerText("[ PRESS ENTER WHEN YOU ARE READY ]");
		System.out.print(RESET);
	}

	// waits for the user to press Enter
	void handleInput() {
		input.nextLine();
	}

	void renderTestCompleted() {
		printHeader("TEST COMPLETED!", "");
		font.White();
		System.out.println("\nEnter the number of squats you completed." + RESET);
	}

	int askReps() {
		while(true) {
			renderTestCompleted();
			
			System.out.print(font.LightGreen() + "Number of Repetitions: " + font.White());
			String raw = input.nextLine().trim();

			try {
				int value = Integer.parseInt(raw);
				if (value >= 0 && value <= MAX_REPS) {
					System.out.print(RESET);
					return value;
				}
			}
			catch(NumberFormatException e) {
				// falls through to the error message below
			}
			System.out.println(font.Red() + "Invalid input. Enter a whole number from 0 to " + MAX_REPS + "." + RESET);
			font.Red();
			System.out.print("Press Enter to try again...");
			input.nextLine();
			clear();
		}
	}

	void renderProcessing() {
		printHeader("PROCESSING RESULT...", "");
	}

	void renderResult() {
		printHeader("LOWER BODY RESULTS", "");

		String pad = " ".repeat(25);
		System.out.println("\n" + pad + font.Cyan() + "Repetitions   : " + font.White() + reps + RESET);
		System.out.println(pad + font.Cyan() + "Score         : " + font.Yellow() + score + "%" + RESET);
		System.out.println(pad + font.Cyan() + "Animal Form   : " + font.LightGreen() + animal + RESET);
		System.out.print(pad + font.Cyan() + "Fitness Level : ");
		printLevel();

		System.out.println("\n" + pad + font.Cyan() + "[1]" + font.White() + " Continue to see the result\n" + RESET);
	}

	// returns true when the user leaves this screen, false when the screen must be drawn again
	boolean handleResultInput() {
		System.out.print(font.LightGreen() + "Enter: " + font.White());
		String choice = input.nextLine().trim();

		switch(choice) {
		case "1" -> {
			return true;
		}
		default -> {
			System.out.println(font.Red() + "Invalid choice. Please enter 1 or 2." + RESET);
			System.out.print("Press Enter to continue...");
			input.nextLine();
			return false;
		}
		}
	}

	void printLevel() {
		switch(level) {
		case "ADVANCED" -> font.Green();
		case "INTERMEDIATE" -> font.Yellow();
		default -> font.Red();
		}
		System.out.println(level + RESET);
	}

	void printHeader(String title, String subtitle) {
		int margin = (72 - title.length())/2;

		System.out.println(font.Cyan() + "=".repeat(72));
		font.Green();
		font.Bold();
		System.out.println(" ".repeat(margin) + title + RESET);
		if (!subtitle.isEmpty()) {
			font.Yellow();
			centerText(subtitle);
			System.out.print(RESET);
		}
		System.out.println(font.Cyan() + "=".repeat(72) + RESET);
	}

	void centerText(String text) {
		int margin = (72 - text.length())/2;
		System.out.println(" ".repeat(margin) + text);
	}

	void pause(int millis) {
		try {
			Thread.sleep(millis);
		}
		catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
