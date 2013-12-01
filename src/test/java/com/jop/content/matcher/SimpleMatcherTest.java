package com.jop.content.matcher;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SimpleMatcherTest {

	private SimpleMatcher sm;

	@Before
	public void setUp() {
		sm = new SimpleMatcher();
	}

	private final String content = "Dummy content with keyword łąśćżźóęń";
	private final String keyword = "łąśćżźóęń";

	// POSITIVE TESTS

	@Test
	public void findKeyword() {
		// given
		sm.keyword(keyword);

		// when
		final boolean result = sm.match(content);

		// then
		assertTrue(result);
	}

	@Test
	void findKeywordWithCaseInsensitive() {
		// given
		final String caseInsensitiveKeyword = "dummy";
		sm.keyword(caseInsensitiveKeyword);

		// when
		final boolean result = sm.match(content);

		// then
		assertTrue(result);
	}

	@Test
	public void findByTwoKeywordsOneBad() {
		// given
		final String bad = "bad";

		sm.keyword(bad);
		sm.keyword(keyword);

		// when
		final boolean result = sm.match(content);

		// then
		assertTrue(result);
	}

	@Test
	public void testMultiKeyword() {
		// given
		final String multiKeyword = "with keyword";

		sm.keyword(multiKeyword);

		// when
		final boolean result = sm.match(content);

		// then
		assertTrue(result);
	}

	// NEGATIVE TESTS

	@Test(expected = NullPointerException.class)
	public void testNullKeywordArgument() {
		sm.keyword(null);
	}

	@Test(expected = NullPointerException.class)
	public void testNullContentArgument() {
		sm.match(null);
	}

	@Test(expected = AssertionError.class)
	public void testEmptyKeywordArgument() {
		sm.keyword("");
	}

	@Test(expected = AssertionError.class)
	public void matchAgainstEmptyContent() {
		sm.match("");
	}

	@Test(expected = IllegalStateException.class)
	public void matchWhenNoKeywordSet() {
		sm.match("Dummy content");
	}

}
