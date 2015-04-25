package com.aspose.cloud.sdk.cells;

import java.io.File;

import com.aspose.cloud.sdk.cells.model.ChartTypeEnum;
import com.aspose.cloud.sdk.cells.api.Charts;
import com.aspose.cloud.sdk.cells.model.ChartAreaResponse.ChartAreaResult;
import com.aspose.cloud.sdk.cells.model.ChartResponse.ChartResult;
import com.aspose.cloud.sdk.cells.model.ChartTitleResource.ChartTitleData;
import com.aspose.cloud.sdk.cells.model.ChartsResponse.ChartsResult;
import com.aspose.cloud.sdk.cells.model.ColorModel;
import com.aspose.cloud.sdk.cells.model.FillFormatResponse.FillFormatResult;
import com.aspose.cloud.sdk.cells.model.FontModel;
import com.aspose.cloud.sdk.cells.model.LegendPositionEnum;
import com.aspose.cloud.sdk.cells.model.LegendResponse;
import com.aspose.cloud.sdk.cells.model.LegendResponse.LegendData;
import com.aspose.cloud.sdk.cells.model.ValidFormatsOfWorksheet;

import junit.framework.TestCase;

public class ChartsTestCase extends TestCase {

	private Charts charts;
	
	public ChartsTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		charts = new Charts("myworkbook.xlsx", "Sheet1");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testAddAChartInAWorksheet() throws Exception {
		ChartsResult chartsResult = charts.addAChartInAWorksheet(ChartTypeEnum.Bar, 11, 1, 31, 11, "A1:B10", true, "A1:A10", false, "Sales State");
		assertNotNull("Failed to add a chart in a worksheet", chartsResult);
	}
	
	public void testGetChartFromAWorksheet() throws Exception {
		ChartResult chartResult = charts.getChartFromAWorksheet(0);
		assertNotNull("Failed to get chart from a worksheet", chartResult);
	}
	
	public void testConvertChartToImage() throws Exception {
		String localFilePath = charts.convertChartToImage(0, ValidFormatsOfWorksheet.png, "salesChart.png");
		File file = new File(localFilePath);
		assertEquals("Failed to convert chart to image", true, file.exists());
	}
	
	public void testDeleteAllChartsFromAWorksheet() throws Exception {
		boolean isAllChartsDeletedSuccessfully = charts.deleteAllChartsFromAWorksheet();
		assertEquals("Failed to delete all charts from a worksheet", true, isAllChartsDeletedSuccessfully);
	}
	
	public void testDeleteAChartFromAWorksheet() throws Exception {
		boolean isChartDeletedSuccessfully = charts.deleteAChartFromAWorksheet(0);
		assertEquals("Failed to delete a chart from a worksheet", true, isChartDeletedSuccessfully);
	}
	
	public void testGetChartAreaFromAWorksheet() throws Exception {
		ChartAreaResult chartArea = charts.getChartAreaFromAWorksheet(0);
		assertNotNull("Failed to get chart area from a worksheet", chartArea);
	}
	
	public void testGetFillFormatOfAChartAreaFromAWorksheet() throws Exception {
		FillFormatResult fillFormat = charts.getFillFormatOfAChartAreaFromAWorksheet(0);
		assertNotNull("Failed to get fill format of a chart area from a worksheet", fillFormat);
	}
	
	public void testShowChartLegendInAWorksheet() throws Exception {
		boolean isChartLegendShowedSuccessfully = charts.showChartLegendInAWorksheet(0);
		assertEquals("Failed to show chart legend in a worksheet", true, isChartLegendShowedSuccessfully);
	}
	
	public void testHideChartLegendInAWorksheet() throws Exception {
		boolean isChartLegendHideSuccessfully = charts.hideChartLegendInAWorksheet(0);
		assertEquals("Failed to hide chart legend in a worksheet", true, isChartLegendHideSuccessfully);
	}
	
	public void testGetChartLegendFromAWorksheet() throws Exception {
		LegendData legend = charts.getChartLegendFromAWorksheet(0);
		assertNotNull("Failed to get chart legend from a worksheet", legend);
	}
	
	public void testUpdateChartLegendInAWorksheet() throws Exception {
		
		LegendResponse legendRequest = new LegendResponse();
		LegendData legendData = legendRequest.new LegendData();
		legendRequest.legend = legendData;
		
		legendData.Position = LegendPositionEnum.Bottom;
		FontModel font = new FontModel();
		ColorModel color = new ColorModel();
		color.A = 0;
		color.R = 255;
		color.G = 0;
		color.B = 0;
		font.Color = color;
		font.DoubleSize = 10;
		font.IsBold = true;
		font.IsItalic = true;
		font.IsStrikeout = true;
		font.IsSubscript = false;
		font.IsSuperscript = false;
		font.Name = "Arial";
		font.Size = 10;
		font.Underline = "None";
		legendData.Font = font;
		legendData.Shadow = true;
		
		LegendData legendResult = charts.updateChartLegendInAWorksheet(0, legendRequest);
		assertNotNull("Failed to update chart legend in a worksheet", legendResult);
	}
	
	public void testUpdateChartTitleInExcelWorksheet() throws Exception {
		String xmlChartTitleRequest = "<Title>" +
										"<Text>New Sales Chart</Text>" +
									  "</Title>";
		
		ChartTitleData chartTitleData = charts.updateChartTitleInExcelWorksheet(0, xmlChartTitleRequest);
		assertNotNull("Failed to update chart title in excel worksheet", chartTitleData);
	}
	
	public void testDeleteChartTitleInAWorksheet() throws Exception {
		boolean isChartTitleDeletedSuccessfully = charts.deleteChartTitleInAWorksheet(0);
		assertEquals("Failed to delete chart title in a worksheet", true, isChartTitleDeletedSuccessfully);
	}
}
