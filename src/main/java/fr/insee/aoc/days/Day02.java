package fr.insee.aoc.days;

import fr.insee.aoc.utils.DayException;

import static fr.insee.aoc.utils.Days.streamOfLines;

public class Day02 implements Day {

	@Override
	public String part1(String input, Object... params) {
		int score = streamOfLines(input).mapToInt(Shape::scoreOfLine).sum();
		return String.valueOf(score);
	}

	@Override
	public String part2(String input, Object... params) {
		int score = streamOfLines(input).mapToInt(Result::scoreOfLine).sum();
		return String.valueOf(score);
	}

	private enum Shape {
		ROCK(1), PAPER(2), SCISSORS(3);

		final int value;

		Shape(int value) {
			this.value = value;
		}

		static Shape from(char c) {
			return switch (c) {
				case 'A', 'X' -> Shape.ROCK;
				case 'B', 'Y' -> Shape.PAPER;
				case 'C', 'Z' -> Shape.SCISSORS;
				default -> throw new DayException();
			};
		}

		static int scoreOfLine(String line) {
			Shape theirs = Shape.from(line.charAt(0));
			Shape ours = Shape.from(line.charAt(2));
			return ours.scoreAgainst(theirs);
		}

		Result resultAgainst(Shape other) {
			return switch (this) {
				case ROCK -> switch (other) {
					case ROCK -> Result.DRAW;
					case PAPER -> Result.LOSE;
					case SCISSORS -> Result.WIN;
				};
				case PAPER -> switch (other) {
					case ROCK -> Result.WIN;
					case PAPER -> Result.DRAW;
					case SCISSORS -> Result.LOSE;
				};
				case SCISSORS -> switch (other) {
					case ROCK -> Result.LOSE;
					case PAPER -> Result.WIN;
					case SCISSORS -> Result.DRAW;
				};
			};
		}

		int scoreAgainst(Shape other) {
			return this.value + resultAgainst(other).value;
		}
	}

	private enum Result {
		LOSE(0), DRAW(3), WIN(6);

		private int value;

		Result(int value) {
			this.value = value;
		}

		static Result from(char c) {
			return switch (c) {
				case 'X' -> LOSE;
				case 'Y' -> DRAW;
				case 'Z' -> WIN;
				default -> throw new DayException();
			};
		}

		Shape forShape(Shape theirs) {
			return switch (this) {
				case LOSE -> switch (theirs) {
					case ROCK -> Shape.SCISSORS;
					case PAPER -> Shape.ROCK;
					case SCISSORS -> Shape.PAPER;
				};
				case DRAW -> theirs;
				case WIN -> switch (theirs) {
					case ROCK -> Shape.PAPER;
					case PAPER -> Shape.SCISSORS;
					case SCISSORS -> Shape.ROCK;
				};
			};
		}

		static int scoreOfLine(String line) {
			Shape theirs = Shape.from(line.charAt(0));
			Result result = Result.from(line.charAt(2));
			return result.value + result.forShape(theirs).value;
		}
	}
}
