package com.aspose.cloud.sdk.slides;

import com.aspose.cloud.sdk.slides.model.ValidSlidesFormats;
import com.aspose.cloud.sdk.slides.api.Slides;
import com.aspose.cloud.sdk.slides.model.ColorSchemeResponse.ColorScheme;
import com.aspose.cloud.sdk.slides.model.FontSchemeResponse.FontScheme;
import com.aspose.cloud.sdk.slides.model.PlaceholderResponse.PlaceholderResult;
import com.aspose.cloud.sdk.slides.model.PowerPointSlideBackgroundResponse.BackgroundResult;
import com.aspose.cloud.sdk.slides.model.SlideCommentsResponse.SlideComments;
import com.aspose.cloud.sdk.slides.model.SlidesResponse.SlidesResult;

import junit.framework.TestCase;

import java.io.File;

public class SlidesTest extends TestCase {

	public SlidesTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testSaveAPowerPointSlideAsImageWithDefaultSize() throws Exception {
		String localFilePath = Slides.saveAPowerPointSlideAsImageWithDefaultSize("Sales.pptx", 1, ValidSlidesFormats.png, "Credits");
		File file = new File(localFilePath);
		assertEquals("Failed to save a powerpoint slide as image with default size", true, file.exists());
	}

	public void testSaveAPowerPointSlideAsImageWithSpecifiedSize() throws Exception {
		String localFilePath = Slides.saveAPowerPointSlideAsImageWithSpecifiedSize("Sales.pptx", 1, ValidSlidesFormats.png, 
				800, 600, "Credits");
		File file = new File(localFilePath);
		assertEquals("Failed to save a powerpoint slide as image with specified size", true, file.exists());
	}
	
	public void testAddANewSlideInAPowerPointPresentation() throws Exception {
		SlidesResult slides = Slides.addANewSlideInAPowerPointPresentation("Effective_presentation.ppt");
		assertNotNull("Failed to add a new slide in a powerpoint presentation", slides);
	}
	
	public void testAddANewSlideToASpecifiedPositionInAPowerPointPresentation() throws Exception {
		SlidesResult slides = Slides.addANewSlideToASpecifiedPositionInAPowerPointPresentation("Effective_presentation.ppt", 4);
		assertNotNull("Failed to add a new slide to a specified position in a powerpoint presentation", slides);
	}
	
	public void testCopySlidesInAPowerPointPresentation() throws Exception {
		SlidesResult slides = Slides.copySlidesInAPowerPointPresentation("Effective_presentation.ppt", 3, 2);
		assertNotNull("Failed to copy slides in a powerpoint presentation", slides);
	}
	
	public void testChangePositionOfSlidesInAPowerPointPresentation() throws Exception {
		SlidesResult slides = Slides.changePositionOfSlidesInAPowerPointPresentation("Effective_presentation.ppt", 3, 1);
		assertNotNull("Failed to change position of slides in a powerpoint presentation", slides);
	}
	
	public void testDeleteAllSlidesFromAPowerPointPresentation() throws Exception {
		boolean isAllSlidesDeletedSuccessfully = Slides.deleteAllSlidesFromAPowerPointPresentation("Effective_presentation.ppt");
		assertEquals("Failed to delete all slides from a powerpoint presentation", true, isAllSlidesDeletedSuccessfully);
	}
	
	public void testDeleteASlideFromAPowerPointPresentation() throws Exception {
		boolean isASpecificSlidesDeletedSuccessfully = Slides.deleteASlideFromAPowerPointPresentation("Effective_presentation.ppt", 1);
		assertEquals("Failed to delete a specific slide from a powerpoint presentation", true, isASpecificSlidesDeletedSuccessfully);
	}
	
	public void testDeleteBackgroundOfAPowerPointSlide() throws Exception {
		boolean isSlideBackgroundDeletedSuccessfully = Slides.deleteBackgroundOfAPowerPointSlide("Effective_presentation.ppt", 1);
		assertEquals("Failed to delete background of a powerpoint slide", true, isSlideBackgroundDeletedSuccessfully);
	}
	
	public void testGetPowerPointDocumentSlideCount() throws Exception {
		int slideCount = Slides.getPowerPointDocumentSlideCount("Effective_presentation.ppt");
		assertEquals("Failed to get powerpoint document slide count", true, slideCount >= 0);
	}
	
	public void testGetPlaceholderFromAPowerPointSlide() throws Exception {
		PlaceholderResult placeholder = Slides.getPlaceholderFromAPowerPointSlide("Effective_presentation.ppt", 1, 0);
		assertNotNull("Failed to get placeholder from a powerpoint slide", placeholder);
	}
	
	public void testGetPlaceholderCountFromAPowerPointSlide() throws Exception {
		int placeholdersCount = Slides.getPlaceholderCountFromAPowerPointSlide("Effective_presentation.ppt", 1);
		assertEquals("Failed to get placeholder count from a powerpoint slide", true, placeholdersCount >= 0);
	}
	
	public void testGetFontSchemeOfAPowerPointSlide() throws Exception {
		FontScheme fontScheme = Slides.getFontSchemeOfAPowerPointSlide("Effective_presentation.ppt", 1);
		assertNotNull("Failed to get font scheme of a particular slide", fontScheme);
	}
	
	public void testGetColorSchemeOfAPowerPointSlide() throws Exception {
		ColorScheme colorScheme = Slides.getColorSchemeOfAPowerPointSlide("Effective_presentation.ppt", 1);
		assertNotNull("Failed to get color scheme of a particular slide", colorScheme);
	}
	
	public void testGetBackgroundOfAPowerPointSlide() throws Exception {
		BackgroundResult background = Slides.getBackgroundOfAPowerPointSlide("Effective_presentation.ppt", 1);
		assertNotNull("Failed to get background information of a particular slide", background);
	}
	
	public void testGetCommentsOfAPowerPointSlide() throws Exception {
		SlideComments slideComments = Slides.getCommentsOfAPowerPointSlide("Effective_presentation.ppt", 1);
		assertNotNull("Failed to get comments of a powerpoint slide", slideComments);
	}
}
