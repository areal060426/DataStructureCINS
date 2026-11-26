package FinalsProject_Group5;

public class AnsiFont {
	
	private final String cyan = "\u001B[36m"; // cyan
	private final String yellow   = "\u001B[33m"; // yellow
	private final String red = "\u001B[31m";
	private final String green = "\u001B[32m";
	private final String white = "\u001B[97m";
	private final String lightGreen = "\u001B[92m";
	private final String bold   = "\u001B[1m";
	
	String Cyan() {
		System.out.print(cyan);
		return cyan;
	}
	String Yellow() {
		System.out.print(yellow);
		return yellow;
	}
	String Red() {
		System.out.print(red);
		return red;
	}
	
	String Green() {
		System.out.print(green);
		return green;
	}
	String White() {
		System.out.print(white);
		return white;
	}
	String LightGreen() {
		System.out.print(lightGreen);
		return lightGreen;
	}
	String Bold() {
		System.out.print(bold);
		return bold;
	}
}
