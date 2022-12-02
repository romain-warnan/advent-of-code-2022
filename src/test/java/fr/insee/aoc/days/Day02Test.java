package fr.insee.aoc.days;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day02Test  {
    
	private final Day day = new Day02();
	
	@Test
	public void case1_0() {
		Assertions.assertEquals("15", day.part1("src/test/resources/02-0.txt"));
	}

	@Test
	public void case2_0() {
		Assertions.assertEquals("12", day.part2("src/test/resources/02-0.txt"));
	}

	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/02.txt");
		System.out.printf("%s.1: %s%n", day.getClass().getSimpleName(), answer);
		Assertions.assertEquals("14163", answer);
	}

	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/02.txt");
		System.out.printf("%s.2: %s%n", day.getClass().getSimpleName(), answer);
		Assertions.assertEquals("12091", answer);
	}
}
