package com.aspose.cloud.sdk.slides;

import com.aspose.cloud.sdk.slides.api.Shapes;
import com.aspose.cloud.sdk.slides.model.ShapeResponse.ShapeData;
import com.aspose.cloud.sdk.slides.model.ShapesResponse.ShapeList;

import junit.framework.TestCase;

public class ShapesTest extends TestCase {

	public ShapesTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testExtractShapesFromASlide() throws Exception {
		ShapeList shapeList = Shapes.extractShapesFromASlide("Effective_presentation.ppt", 1);
		assertNotNull("Failed to extract shapes from a particular slide", shapeList);
	}
	
	public void testGetAParticularShapeFromTheSlide() throws Exception {
		ShapeData shape = Shapes.getAParticularShapeFromTheSlide("Effective_presentation.ppt", 1, 1);
		assertNotNull("Failed to get a particular shape from the slide", shape);
	}
}
