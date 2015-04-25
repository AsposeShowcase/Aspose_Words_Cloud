package com.aspose.cloud.sdk.words;

import junit.framework.TestCase;
import com.aspose.cloud.sdk.words.api.Bookmark;
import com.aspose.cloud.sdk.words.model.GetBookmarkResponse.BookmarksEnvelop;
import com.aspose.cloud.sdk.words.model.GetASpecificBookmarkResponse.BookmarkEnvelop;

public class BookmarkTestCase extends TestCase {

	public BookmarkTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testGetAllBookmarksFromAWordDocument() throws Exception {
		BookmarksEnvelop bookmarks = Bookmark.getAllBookmarksFromAWordDocument("myworddocument.docx");
		assertNotNull("Failed to get all bookmarks from a Word document", bookmarks);
	}

	public void testGetASpecificBookmarkFromAWord() throws Exception {
		BookmarkEnvelop bookmark = Bookmark.getASpecificBookmarkFromAWord("myworddocument.docx", "IntensityBookmark");
		assertNotNull("Failed to get a specific bookmark from a Word document", bookmark);
	}
	
	public void testUpdateBookmarkTextOfAWordDocument() throws Exception {
		BookmarkEnvelop bookmark = Bookmark.updateBookmarkTextOfAWordDocument("myworddocument.docx", "IntensityBookmark", "Laser pointers make a potent signaling tool, even in daylight, and are able to produce a bright signal for potential search and rescue vehicles using an inexpensive, small and lightweight device of the type that could be routinely carried in an emergency kit.");
		assertNotNull("Failed to update bookmark text of a Word document", bookmark);
	}
}
