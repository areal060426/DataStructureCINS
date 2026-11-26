package FinalsProject_Group5;

public class MountainClimbersScreen extends ExerciseScreen {

	static final int MAX_REPS = 200;

	int reps;
	Session session;
	
	MountainClimbersScreen(Session session){
		this.session = session;
	}

	// placeholder results (design only), replace with the real scoring later
	int target = 40;
	int score;
	String animal;
	String level;

	void show() {
		clear();
		render();
		handleInput();
		
		runTimer("MOUNTAIN CLIMBERS");

		clear();
		reps = askReps();
		
		ScoringService scoring = new ScoringService(session.person, "MOUNTAIN_CLIMBERS");
		score = (int) scoring.getScore(reps);
		animal = new AnimalMapper().getAnimal("MOUNTAIN_CLIMBERS", score);
		level = scoring.getLevel(score);
		session.addResult(new Result("MOUNTAIN_CLIMBERS", "CARDIO", reps, score, animal, level));

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
		printHeader("MOUNTAIN CLIMBERS", "Duration: 1 MINUTE");

		System.out.println("\n" + font.Red() + "INSTRUCTIONS:" + RESET);
		String instructions = 
				"""
				Start in a plank position with your hands supported
				on the floor. Alternately bring your right and
				left knees toward your chest. Do this quickly and 
				continuously for one minute. Each time a knee is
				brought toward the chest counts as one repetition.
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
		System.out.println("\nEnter the number of mountain climbers you completed." + RESET);
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
		printHeader("MOUNTAIN CLIMBERS RESULTS", "");

		String pad = " ".repeat(25);
		System.out.println("\n" + pad + font.Cyan() + "Repetitions   : " + font.White() + reps + " / " + target + RESET);
		System.out.println(pad + font.Cyan() + "Score         : " + font.Yellow() + score + "%" + RESET);
		System.out.println(pad + font.Cyan() + "Animal Form   : " + font.LightGreen() + animal + RESET);
		System.out.print(pad + font.Cyan() + "Fitness Level : ");
		printLevel();

		System.out.println("\n" + pad + font.Cyan() + "[1]" + font.White() + " Continue to see the result");
		System.out.println(pad + font.Cyan() + "[2]" + font.White() + " Back to Main Menu\n" + RESET);
	}

	// returns true when the user leaves this screen, false when the screen must be drawn again
	boolean handleResultInput() {
		System.out.print(font.LightGreen() + "Enter: " + font.White());
		String choice = input.nextLine().trim();

		switch(choice) {
		case "1" -> {
			return true;
		}
		case "2" -> {
			stopped = true;
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
