package FinalsProject_Group5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AnimalMapper {
	
	private final double[] cutoffs = {0, 18, 36, 51, 66, 83};
	
	private final Map<String, String[]> ladders = new HashMap<>();

	AnimalMapper() {
		ladders.put("PUSH_UPS", new String[] {
				"MOUSE ARMS", "MONKEY ARMS", "CHIMPANZEE ARMS", "ORANGUTAN ARMS", "BEAR ARMS", "GORILLA ARMS"});
		ladders.put("SIT_UPS", new String[] {
				"JELLYFISH CORE", "TORTOISE CORE", "CAT CORE", "WOLF CORE", "TIGER CORE", "CROCODILE CORE"});
		ladders.put("SQUATS", new String[] {
				"SLOTH LEGS", "CHICKEN LEGS", "DEER LEGS", "KANGAROO LEGS", "GREYHOUND LEGS", "CHEETAH LEGS"});
		ladders.put("JUMPING_JACKS", new String[] {
				"SNAIL ENDURANCE", "FROG ENDURANCE", "RABBIT ENDURANCE", "ZEBRA ENDURANCE", "GAZELLE ENDURANCE", "STALLION ENDURANCE"});
		ladders.put("HIGH_KNEES", new String[] {
				"PANDA STAMINA", "PENGUIN STAMINA", "DOG STAMINA", "FOX STAMINA", "WOLF STAMINA", "HUSKY STAMINA"});
		ladders.put("MOUNTAIN_CLIMBERS", new String[] {
				"SLOTH ENDURANCE", "KOALA ENDURANCE", "SQUIRREL ENDURANCE", "BOBCAT ENDURANCE", "MOUNTAIN_GOAT ENDURANCE", "SNOW_LEOPARD ENDURANCE"});
	}
	
	String getAnimal(String exercise, double score) {
		String[] names = ladders.get(exercise);

		int i = Arrays.binarySearch(cutoffs, score);
		int tier = (i >= 0) ? i : -i - 2;
		tier = Math.max(0, tier);

		return names[tier];
	}
	
}
