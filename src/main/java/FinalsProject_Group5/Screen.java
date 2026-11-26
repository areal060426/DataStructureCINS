package FinalsProject_Group5;

import java.util.Scanner;

public abstract class Screen {
	
	static Scanner input = new Scanner(System.in);
	static AnsiFont font = new AnsiFont();
	static AnimalAsciiArt animalArt = new AnimalAsciiArt();
	
	final String RESET  = "\u001B[0m";
	
	void clear() {
		System.out.print("\u001B[H\u001B[2J\u001B[3J");
		System.out.flush();
		System.out.print(RESET);
	}
	
	abstract void show();
	abstract void render();

}
