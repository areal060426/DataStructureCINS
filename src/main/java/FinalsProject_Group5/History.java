package FinalsProject_Group5;

import java.util.ArrayList;
import java.util.List;

public class History extends Screen {

	
	static final List<Session> sessions = new ArrayList<>();

	static void add(Session session) {
		sessions.add(session);
	}

	void show() {
		clear();
		render();
		handleInput();
	}

	void render() {
		printHeader("HISTORY");

		int attempt = 0;
		for (Session session : sessions) {
			// skip attempts where the user did not finish any exercise
			if (session.getResults().isEmpty()) {
				continue;
			}
			attempt++;

			String name = session.person.getName() + " " + session.person.getLastName();
			System.out.println("\n" + font.Yellow() + "ATTEMPT " + attempt + " - " + name + RESET);

			for (Result result : session.getResults()) {
				String exercise = result.exercise.replace("_", " ");
				font.White();
				System.out.println(String.format("  %-18s %3d reps  %4d%%  %-26s %s",
						exercise, result.reps, Math.round(result.score), result.animal, result.level) + RESET);
			}
		}

		if (attempt == 0) {
			font.White();
			System.out.println("\n" + " ".repeat(14) + "No attempts yet. Finish an exercise first." + RESET);
		}

		System.out.println("\n" + font.Cyan() + "=".repeat(72) + RESET);
	}

	void handleInput() {
		System.out.print(font.LightGreen() + "Press Enter to return to the main menu..." + font.White());
		input.nextLine();
		System.out.print(RESET);
	}

	void printHeader(String title) {
		int margin = (72 - title.length())/2;

		System.out.println(font.Cyan() + "=".repeat(72));
		font.Green();
		font.Bold();
		System.out.println(" ".repeat(margin) + title + RESET);
		System.out.println(font.Cyan() + "=".repeat(72) + RESET);
	}

}