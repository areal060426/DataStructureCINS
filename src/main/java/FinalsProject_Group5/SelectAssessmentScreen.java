package FinalsProject_Group5;

public class SelectAssessmentScreen extends Screen{
	
	final String name;
	Session session;
	
	SelectAssessmentScreen(Session session){
		this.name = session.person.getName();
		this.session = session;
	}
	
	void show() {
		boolean done = false;
		while(!done) {
			clear();
			render();
			done = handleInput();
		}
	}

	void render() {
		String title = "SELECT ASSESSMENT";
		int margin = (72 - title.length())/2;

		System.out.println(font.Cyan() + "=".repeat(72));
		font.Green();
		font.Bold();
		System.out.println(" ".repeat(margin) + title + RESET);
		System.out.println(font.Cyan() + "=".repeat(72));
		
		font.Yellow();
		String greetings = "Welcome, " + name;
		centerText(greetings);
		
		font.White();
		String question = "What fitness assessment would you like to perform?";
		centerText(question);
		
		String [] options = new String[3];
		
		options[0] =
				RESET + font.Cyan() + "[1]" +font.White() + font.Bold()+ " STRENGTH TEST\n" +
				"""
				Measure your strength and muscular
				endurance in your upper
				body, core, and lower body.
				""";
		options[1] = 
				RESET + font.Cyan() + "[2]" +font.White() + font.Bold()+ " CARDIOVASCULAR ENDURANCE TEST\n" +
				"""
				Measure your stamina and ability
				to sustain physical activities.
				""";
		options[2] = 
				RESET + font.Cyan() + "[3]" +font.White() + font.Bold()+ " BOTH ASSESSMENTS\n" + 
				"""
				Complete both fitness assessments
				to get your full BEAST PROFILE!
				""";
		
		System.out.println("\n" + options[0].indent(20));
		System.out.println(options[1].indent(20));
		System.out.println(options[2].indent(20));
	} 
	
	boolean handleInput() {
		font.LightGreen();
		System.out.print("\nEnter your choice: ");
		font.White();
		String choice = input.nextLine();
		switch(choice) {
		case "1" -> choice = "1";
		case "2" -> choice = "2";
		case "3" -> System.out.println("place holder");
		default ->  {
			System.out.println(RESET + font.Red() + "Invalid choice. Please enter 1, 2, or 3." + RESET);
			System.out.print("Press Enter to continue...");
			input.nextLine();
			return false;
		}
		}
		
		new AssessmentRunner(choice, session).start();
		return true;
	}
	
	void centerText(String text) {
		int margin = (72 - text.length())/2;
		System.out.println(" ".repeat(margin) + text);
	}
	
}
