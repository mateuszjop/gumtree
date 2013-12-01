package com.jop.content.matcher;

/**
 * 
 * @author Mateusz Jop
 * @date Nov 30, 2013
 * 
 *       Searches content by set of keywords.
 * 
 */
public interface ContentMatcher {

	/**
	 * 
	 * @param keyword
	 *            string which will be matched to content.
	 * @return instance of this, thus the method can be used in one row.
	 */
	public ContentMatcher keyword(final String keyword);

	/**
	 * 
	 * @param content
	 *            against which keywords will be matched.
	 * @return true if any of keywords match the content
	 */
	public boolean match(String content);
}
