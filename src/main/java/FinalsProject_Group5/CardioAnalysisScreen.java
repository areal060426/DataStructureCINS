package FinalsProject_Group5;

import java.util.List;

public class CardioAnalysisScreen extends Screen {

	String jacksAnimal;
	String kneesAnimal;
	String climbersAnimal;
	String name;
	int age;
	double height;
	double weight;
	
	
	CardioAnalysisScreen(Session session){
		List<Result> results = session.getCardioResults();
		jacksAnimal = results.get(0).animal;
		kneesAnimal = results.get(1).animal;
		climbersAnimal = results.get(2).animal;
		this.name = session.person.getName() + " " +session.person.getLastName();
		this.height = session.person.getHeight();
		this.weight = session.person.getWeight();
		this.age = session.person.getAge();
	}

	void show() {
		clear();
		render();
		handleInput();
	}
	
	String getBMI(double weight, double height) {
		double bmi = weight / Math.pow(height, 2);
		String bmiStr = "%.2f - ".formatted(bmi);
		return bmiStr + getBmiCategory(bmi);
	}
	
	public static String getBmiCategory(double bmi) {
	        if (bmi < 18.5) {
	            return "Underweight";
	        } else if (bmi < 25.0) {
	            return "Normal weight";
	        } else if (bmi < 30.0) {
	            return "Overweight";
	        } else {
	            return "Obese";
	        }
	}

	void displayInfo() {
		System.out.print(RESET);
		font.Cyan();
		System.out.println("-".repeat(72));
		System.out.println(font.LightGreen() + "Name: " + font.White()+name);
		System.out.println(font.LightGreen() + "Age: "+ font.White()+age);
		System.out.println(font.LightGreen() + "Height: "+ font.White()+height + "m");
		System.out.println(font.LightGreen() + "Weight: "+ font.White()+weight + "kg");
		System.out.println(font.LightGreen() + "BMI: "+ font.White()+getBMI(weight, height));
		font.Cyan();
		System.out.println("-".repeat(72));
		System.out.print(RESET);
	}

	void render() {
		printHeader("OVERALL");

		String comment = """
				You've got the speed of a %s, the stamina
				of a %s, and the endurance of a %s!
				""".formatted(
						jacksAnimal.substring(0,jacksAnimal.indexOf(" ")),
						kneesAnimal.substring(0,kneesAnimal.indexOf(" ")),
						climbersAnimal.substring(0,climbersAnimal.indexOf(" ")));

		System.out.println(font.Red() + "SYSTEM NOTES:" + RESET);
		font.Yellow();
		System.out.println(comment+ RESET);
		displayInfo();
		printNote("JUMPING JACKS - " + jacksAnimal, animalArt.getAnimalAscii(jacksAnimal.substring(0,jacksAnimal.indexOf(" "))));
		printNote("HIGH KNEES - " + kneesAnimal, animalArt.getAnimalAscii(kneesAnimal.substring(0,kneesAnimal.indexOf(" "))));
		printNote("MOUNTAIN CLIMBERS - " + climbersAnimal, animalArt.getAnimalAscii(climbersAnimal.substring(0,climbersAnimal.indexOf(" "))));

		System.out.println(font.Cyan() + "-".repeat(72) + RESET);

		System.out.println(font.Red() + "OVERALL COMMENT:" + RESET);
		String overall = """
				A true beast never stops evolving!
				Keep training, stay consistent, and ARISE!
				""";
		font.Yellow();
		System.out.print(overall.indent(4) + RESET);

		System.out.println(font.Cyan() + "=".repeat(72) + RESET);
	}

	// waits for the user to press Enter, then the AssessmentRunner returns to the main menu
	void handleInput() {
		System.out.print(font.LightGreen() + "Press Enter to return to the main menu..." + font.White());
		input.nextLine();
		System.out.print(RESET);
	}

	void printNote(String title, String text) {
		System.out.println(font.LightGreen() + "[" + title + "]" + RESET);
		font.White();
		System.out.println(text.indent(4) + RESET);
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
