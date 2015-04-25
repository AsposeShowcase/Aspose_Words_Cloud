package com.aspose.cloud.sdk.cells.api;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import android.net.Uri;

import com.aspose.cloud.sdk.cells.model.ChartTitleResource;
import com.aspose.cloud.sdk.cells.model.ChartTypeEnum;
import com.aspose.cloud.sdk.cells.model.ChartsResponse;
import com.aspose.cloud.sdk.cells.model.FillFormatResponse;
import com.aspose.cloud.sdk.cells.model.LegendResponse;
import com.aspose.cloud.sdk.cells.model.ValidFormatsOfWorksheet;
import com.aspose.cloud.sdk.cells.model.ChartAreaResponse;
import com.aspose.cloud.sdk.cells.model.ChartAreaResponse.ChartAreaResult;
import com.aspose.cloud.sdk.cells.model.ChartResponse;
import com.aspose.cloud.sdk.cells.model.ChartResponse.ChartResult;
import com.aspose.cloud.sdk.cells.model.ChartTitleResource.ChartTitleData;
import com.aspose.cloud.sdk.cells.model.ChartsResponse.ChartsResult;
import com.aspose.cloud.sdk.cells.model.FillFormatResponse.FillFormatResult;
import com.aspose.cloud.sdk.cells.model.LegendResponse.LegendData;
import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Charts --- Using this class you can Add a chart in a worksheet, Get a chart from a worksheet, Convert a chart to image, 
 * Delete charts from a worksheet, Get chart area from a worksheet, Get fill format of a chart area from a worksheet, 
 * Show/Hide chart legend in a worksheet, Get and update chart legend in a worksheet, Set, update and delete chart title in 
 * excel worksheet
 * @author   M. Sohail Ismail
 */
public class Charts {
	
	private static final String CELLS_URI = AsposeApp.BASE_PRODUCT_URI + "/cells/";
	
	private String fileName;
	private String worksheetName;
	
	public Charts(String fileName, String worksheetName) {
		this.fileName = Uri.encode(fileName);
		this.worksheetName = Uri.encode(worksheetName);
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void setWorksheetName(String worksheetName) {
		this.worksheetName = worksheetName;
	}
	
	/**
	 * Add a chart in a worksheet
	 * @param chartType Chart type. Please see class ChartTypeEnum for possible values
	 * @param upperLeftRow Upper left corner row index
	 * @param upperLeftColumn Upper left corner column index
	 * @param lowerRightRow Lower right corner row index
	 * @param lowerRightColumn Lower right corner column index
	 * @param area Specifies values from which to plot the data series
	 * @param isVertical Specifies whether to plot the series from a range of cell values by row or by column
	 * @param categoryData Gets or sets the range of category Axis values. It can be a range of cells (such as, "d1:e10")
	 * @param isAutoGetSerialName Specifies whether auto update serial name
	 * @param chartTitle Chart title
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains hypertext references to chart
	*/ 
	public ChartsResult addAChartInAWorksheet(ChartTypeEnum chartType, int upperLeftRow, int upperLeftColumn, int lowerRightRow,
			int lowerRightColumn, String area, boolean isVertical, String categoryData, boolean isAutoGetSerialName, String chartTitle) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		ChartsResult charts = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName +
				"/charts?chartType=" + chartType + "&upperLeftRow=" + upperLeftRow + "&upperLeftColumn=" + upperLeftColumn + 
				"&lowerRightRow=" + lowerRightRow + "&lowerRightColumn=" + lowerRightColumn + "&area=" + Uri.encode(area) + 
				"&isVertical=" + isVertical + "&categoryData=" + Uri.encode(categoryData) + "&isAutoGetSerialName=" + isAutoGetSerialName + "&title=" + Uri.encode(chartTitle);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "PUT");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		ChartsResponse chartsResponse = gson.fromJson(responseJSONString, ChartsResponse.class);
		if (chartsResponse.getCode().equals("200") && chartsResponse.getStatus().equals("OK")) {
			charts = chartsResponse.charts;
		}
		
		return charts;
	}
	
	/**
	 * Get a chart from a worksheet
	 
	 
	 * @param chartIndex Chart Index
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains hypertext references to chart
	*/ 
	public ChartResult getChartFromAWorksheet(int chartIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		ChartResult chart = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName +
				"/charts/" + chartIndex;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		ChartResponse chartResponse = gson.fromJson(responseJSONString, ChartResponse.class);
		if (chartResponse.getCode().equals("200") && chartResponse.getStatus().equals("OK")) {
			chart = chartResponse.chart;
		}
		
		return chart;
	}
	
	/**
	 * Convert a chart to image
	 * @param chartIndex Chart Index
	 * @param designatedFormat Chart will be converted to this format
	 * @param outputFileName Converted file will save on device with this name
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Path to converted file saved on device
	*/ 
	public String convertChartToImage(int chartIndex, ValidFormatsOfWorksheet designatedFormat, String outputFileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		String localFilePath = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		if(designatedFormat == null) {
			throw new IllegalArgumentException("Designated format cannot be null");
		}
		
		if(outputFileName == null || outputFileName.length() == 0) {
			throw new IllegalArgumentException("Output file name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/charts/" + chartIndex + 
				"?format=" + designatedFormat;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		//Save the stream in response to the disk
		localFilePath = Utils.saveStreamToFile(responseStream, outputFileName);
		
		return localFilePath;
	}
	
	/**
	 * Delete all charts from a worksheet
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether all charts deleted successfully from a worksheet
	*/ 
	public boolean deleteAllChartsFromAWorksheet() throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isAllChartsDeletedSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/charts";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "DELETE");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isAllChartsDeletedSuccessfully = true;
		}
		
		return isAllChartsDeletedSuccessfully;
	}
	
	/**
	 * Delete a chart from a worksheet
	 * @param chartIndex Chart Index
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether a charts deleted successfully from a worksheet
	*/ 
	public boolean deleteAChartFromAWorksheet(int chartIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isChartDeletedSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/charts/" + chartIndex;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "DELETE");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isChartDeletedSuccessfully = true;
		}
		
		return isChartDeletedSuccessfully;
	}
	
	/**
	 * Get chart area from a worksheet
	 * @param chartIndex Chart Index
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains chart area attributes
	*/ 
	public ChartAreaResult getChartAreaFromAWorksheet(int chartIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		ChartAreaResult chartArea = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName +
				"/charts/" + chartIndex + "/chartArea";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		ChartAreaResponse chartAreaResponse = gson.fromJson(responseJSONString, ChartAreaResponse.class);
		if (chartAreaResponse.getCode().equals("200") && chartAreaResponse.getStatus().equals("OK")) {
			chartArea = chartAreaResponse.chartArea;
		}
		
		return chartArea;
	}
	
	/**
	 * Get fill format of a chart area from a worksheet
	 * @param chartIndex Chart Index
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains attributes of fill format of a chart area
	*/ 
	public FillFormatResult getFillFormatOfAChartAreaFromAWorksheet(int chartIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		FillFormatResult fillFormat = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName +
				"/charts/" + chartIndex + "/chartArea/fillFormat";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		FillFormatResponse fillFormatResponse = gson.fromJson(responseJSONString, FillFormatResponse.class);
		if (fillFormatResponse.getCode().equals("200") && fillFormatResponse.getStatus().equals("OK")) {
			fillFormat = fillFormatResponse.fillFormat;
		}
		
		return fillFormat;
	}
	
	/**
	 * Show chart legend in a worksheet
	 * @param chartIndex Chart Index
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether chart legend shown successfully
	*/ 
	public boolean showChartLegendInAWorksheet(int chartIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isChartLegendShowedSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName +
				"/charts/" + chartIndex + "/legend";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "PUT");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isChartLegendShowedSuccessfully = true;
		}
		
		return isChartLegendShowedSuccessfully;
	}
	
	/**
	 * Hide chart legend in a worksheet
	 * @param chartIndex Chart Index
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether chart legend hidden successfully
	*/ 
	public boolean hideChartLegendInAWorksheet(int chartIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isChartLegendHideSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName +
				"/charts/" + chartIndex + "/legend";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "DELETE");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isChartLegendHideSuccessfully = true;
		}
		
		return isChartLegendHideSuccessfully;
	}
	
	/**
	 * Get chart legend from a worksheet
	 * @param chartIndex Chart Index
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains attributes of chart legend
	*/ 
	public LegendData getChartLegendFromAWorksheet(int chartIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		LegendData legend = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName +
				"/charts/" + chartIndex + "/legend";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		LegendResponse legendResponse = gson.fromJson(responseJSONString, LegendResponse.class);
		if (legendResponse.getCode().equals("200") && legendResponse.getStatus().equals("OK")) {
			legend = legendResponse.legend;
		}
		
		return legend;
	}
	
	/**
	 * Update chart legend in a worksheet
	 * @param chartIndex Chart Index
	 * @param legendRequest Request for legend
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains updated attributes of chart legend
	*/
	public LegendData updateChartLegendInAWorksheet(int chartIndex, LegendResponse legendRequest) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		LegendData legend = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		if(legendRequest == null) {
			throw new IllegalArgumentException("Legend request cannot be null");
		}
		
		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String requestJSONString = gson.toJson(legendRequest, LegendResponse.class);
        
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName +
				"/charts/" + chartIndex + "/legend";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "POST", requestJSONString);
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		LegendResponse legendResponse = gson.fromJson(responseJSONString, LegendResponse.class);
		if (legendResponse.getCode().equals("200") && legendResponse.getStatus().equals("OK")) {
			legend = legendResponse.legend;
		}
		
		return legend;
	}
	
	/**
	 * Update chart title in excel worksheet
	 * @param chartIndex Chart Index
	 * @param xmlChartTitleRequest Request for title
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains updated attributes of chart title
	*/
	public ChartTitleData updateChartTitleInExcelWorksheet(int chartIndex, String xmlChartTitleRequest) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		ChartTitleData chartTitleData = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
        
		if(xmlChartTitleRequest == null) {
			throw new IllegalArgumentException("Chart title request cannot be null");
		}
        
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName +
				"/charts/" + chartIndex + "/title";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "POST", xmlChartTitleRequest, "xml");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		ChartTitleResource chartTitleResponse = gson.fromJson(responseJSONString, ChartTitleResource.class);
		if (chartTitleResponse.getCode().equals("200") && chartTitleResponse.getStatus().equals("OK")) {
			chartTitleData = chartTitleResponse.chartTitleData;
		}
		
		return chartTitleData;
	}
	
	
	/**
	 * Delete chart title in a worksheet
	 * @param chartIndex Chart Index
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether chart title deleted successfully
	*/
	public boolean deleteChartTitleInAWorksheet(int chartIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isChartTitleDeletedSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
        
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName +
				"/charts/" + chartIndex + "/title";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "DELETE");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isChartTitleDeletedSuccessfully = true;
		}
		
		return isChartTitleDeletedSuccessfully;
	}
}
