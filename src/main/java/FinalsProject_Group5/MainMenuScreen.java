package FinalsProject_Group5;

public class MainMenuScreen extends Screen{
	
	void show() {
		while(true) {
			clear();
			render();
			handleInput();
		}
	}
	
	void render() {
	    String[] art = {
	        "    _          _                 _____ _ _",
	        "   / \\   _ __ (_)_ __ ___   __ _|  ___(_) |_",
	        "  / _ \\ | '_ \\| | '_ ` _ \\ / _` | |_  | | __|",
	        " / ___ \\| | | | | | | | | | (_| |  _| | | |_",
	        "/_/   \\_\\_| |_|_|_| |_| |_|\\__,_|_|   |_|\\__|"
	    };

	    int inner = 70;
	    int width = 0;
	    for (String s : art) width = Math.max(width, s.length());
	    int margin = (inner - width) / 2;

	    String edge = font.Cyan() + "+" + "-".repeat(inner) + "+" + RESET;

	    System.out.println(edge);
	    for (String s : art) {
	        String padded = " ".repeat(margin) + s + " ".repeat(inner - margin - s.length());
	        System.out.println(font.Cyan() + "|" + font.Yellow() + padded + font.Cyan() + "|" + RESET);
	    }
	    System.out.println(edge);
	    System.out.println(font.Red() + "**********************" + font.LightGreen() + " FITNESS ASSESSMENT SYSTEM "
	            + font.Red() + "***********************" + RESET);
	    
	    
	    System.out.println("\n" + " ".repeat(25) + font.Cyan() + "[1]" + font.White() +" Start Assessment");
	    System.out.println(" ".repeat(25) + font.Cyan() + "[2]" + font.White() +" History");
	    System.out.println(" ".repeat(25) + font.Cyan() + "[3]" + font.White() +" Exit\n");
	}
	
	void handleInput() {
		System.out.print(font.LightGreen() + "Enter your choice: " + font.White());
	    String choice = input.nextLine().trim();
	    
	    switch(choice) {
	    case "1" -> new PersonalInfoGather().show();
	    case "2" -> new History().show();
	    case "3" -> {
	    	clear();
	    	System.exit(0);
	    }
	    default -> {
	    	System.out.println(font.Red() + "Invalid choice. Please enter 1, 2, or 3." + RESET);
	    	System.out.print("Press Enter to continue...");
            input.nextLine();
	    }
	    }
	}
	
}
