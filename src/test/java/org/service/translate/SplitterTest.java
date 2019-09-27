package org.service.translate;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.service.translate.translate.split.SplitByNotAlphanumeric;
import org.service.translate.translate.split.Splitter;


public class SplitterTest {
	private final Splitter splitter = new SplitByNotAlphanumeric();

	@Test
	public void abc(){
		String englishWithDifferentDelimeters = "Lorem ipsum $ dolor -sit amet, consectetur \t 21 adipiscing elit";
		String model = "Lorem ipsum dolor sit amet consectetur 21 adipiscing elit";

		String splitted = splitter.split(englishWithDifferentDelimeters).toString();
		String splitted2 = Arrays.toString(model.split("\\s+"));

		Assert.assertEquals(splitted2, splitted);
	}
}