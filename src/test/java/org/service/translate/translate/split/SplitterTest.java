package org.service.translate.translate.split;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;


public class SplitterTest {
	private final Splitter splitter = new SplitByNotAlphanumeric();

	@Test
	public void shouldSplitEnglishSentenceWithDelimiters(){
		String withDelimiters = "Lorem ipsum $ dolor -sit amet, consectetur \t 21 adipiscing elit";
		String model = "Lorem ipsum dolor sit amet consectetur 21 adipiscing elit";

		String actual = splitter.split(withDelimiters).toString();
		String expected = Arrays.toString(model.split("\\s+"));

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void shouldSplitRussianSentenceWithDelimiters(){
		String withDelimiters = "съешь / ещё 12 *этих  мягких () французских булок, + да \n выпей чаю.";
		String model = "съешь ещё 12 этих мягких французских булок да выпей чаю";

		String actual = splitter.split(withDelimiters).toString();
		String expected = Arrays.toString(model.split("\\s+"));

		Assert.assertEquals(expected, actual);
	}
}