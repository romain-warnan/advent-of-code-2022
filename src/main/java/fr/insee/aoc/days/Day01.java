package fr.insee.aoc.days;

import fr.insee.aoc.utils.BlocCollector;
import fr.insee.aoc.utils.DayException;

import java.util.Arrays;
import java.util.Comparator;

import static fr.insee.aoc.utils.Days.streamOfLines;

public class Day01 implements Day {

	@Override
	public String part1(String input, Object... params) {
		//@formatter:off
		int maxCalories = streamOfLines(input)
			.collect(new BlocCollector(" "))
			.stream()
			.map(Calories::from)
			.mapToInt(Calories::totalOfCalories)
			.max()
			.orElseThrow(DayException::new);
		//@formatter:on
		return String.valueOf(maxCalories);
	}

	@Override
	public String part2(String input, Object... params) {
		//@formatter:off
		int maxCalories = streamOfLines(input)
			.collect(new BlocCollector(" "))
			.stream()
			.map(Calories::from)
			.mapToInt(Calories::totalOfCalories)
			.boxed()
			.sorted(Comparator.reverseOrder())
			.mapToInt(Integer::intValue)
			.limit(3)
			.sum();
		//@formatter:on
		return String.valueOf(maxCalories);
	}

	private static class Calories {
		private int[] calories;

		private Calories() {}

		public static Calories from(String string) {
			Calories instance = new Calories();
			instance.calories = Arrays.stream(string.split(" ")).mapToInt(Integer::parseInt).toArray();
			return instance;
		}

		public int totalOfCalories() {
			return Arrays.stream(calories).sum();
		}
	}
}
