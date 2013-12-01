package com.jop.content.matcher;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@inheritDoc} Case of keywords is ignored while seeking.
 * 
 */
public class SimpleMatcher implements ContentMatcher {
	// TODO MJ envolve log4j
	// TODO MJ deal with multi word phrases
	private final Set<String> keywords = new LinkedHashSet<>();
	private String pattern;
	private boolean patternChanged;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ContentMatcher keyword(final String keyword) {
		if (keyword == null) {
			throw new NullPointerException("Can not add null keyword");
		}
		if (keyword.isEmpty()) {
			throw new IllegalArgumentException(
					"Keyword cannot be an empty string");
		}

		// save it in lower case to make sure there will not be duplicates
		keywords.add(keyword.toLowerCase(Locale.getDefault()));

		patternChanged = true;

		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean match(final String content) {
		final String pattern = preparePattern();
		final Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		final Matcher m = p.matcher(content);
		return m.find();
	}

	private String preparePattern() {
		if (pattern != null && !patternChanged) {
			return pattern;
		}

		final StringBuilder sb = new StringBuilder(".*(");
		for (final String s : keywords) {
			sb.append(s);
			sb.append("|");
		}
		sb.setLength(sb.length() - 1); // remove the last "|" (pipe) sign
		sb.append(").*");

		pattern = sb.toString();
		patternChanged = false;
		return pattern;
	}

}
