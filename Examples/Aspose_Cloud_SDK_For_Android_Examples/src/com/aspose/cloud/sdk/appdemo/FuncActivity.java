package com.aspose.cloud.sdk.appdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.aspose.cloud.sdk.appdemo.barcode_demo.BarcodeBuilderSave1;
import com.aspose.cloud.sdk.appdemo.barcode_demo.BarcodeBuilderSave2;
import com.aspose.cloud.sdk.appdemo.barcode_demo.BarcodeReaderRead1;
import com.aspose.cloud.sdk.appdemo.barcode_demo.BarcodeReaderRead2;
import com.aspose.cloud.sdk.appdemo.barcode_demo.BarcodeReaderReadFromLocalImage3;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsChartEditorAddChart;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsChartEditorDeleteChart;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsChartEditorGetBorder;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsChartEditorGetChartArea;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsChartEditorGetFillFormat;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsConverterAutoShapeToImage;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsConverterChartToImage;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsConverterConvertLocalFile;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsConverterOleObjectToImage;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsConverterPictureToImage;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsConverterSave;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsConverterWorksheetToImage;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsTextEditorFindText;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsTextEditorFindText2;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsTextEditorGetTextItems;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsTextEditorGetTextItems2;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsTextEditorReplaceText;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsTextEditorReplaceText2;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorkbookAddWorksheet;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorkbookClearModifyPassword;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorkbookCreateEmptyWorkbook;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorkbookCreateWorkbookFromSmartMarkerTemplate;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorkbookCreateWorkbookFromTemplate;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorkbookDecrpytWorkbook;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorkbookEncryptWorkbook;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorkbookGetDefaultStyle;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorkbookGetNamesCount;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorkbookGetProperties;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorkbookGetProperty;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorkbookGetWorksheetsCount;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorkbookMergeWorkbook;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorkbookProcessSmartMarker;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorkbookProtectWorkbook;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorkbookRemoveAllProperties;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorkbookRemoveProperty;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorkbookRemoveWorksheet;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorkbookSetModifyPassword;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorkbookSetProperty;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorkbookUnprotectWorkbook;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetCalculateFormula;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetDeleteRow;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetGetAutoShapeByIndex;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetGetAutoShapesCount;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetGetCell;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetGetCellStyle;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetGetCellsCount;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetGetCellsList;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetGetChartByIndex;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetGetChartsCount;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetGetColumn;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetGetColumnsList;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetGetComment;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetGetCommentsCount;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetGetHyperLinkByIndex;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetGetHyperlinksCount;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetGetMaxColumn;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetGetMaxRow;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetGetMergedCellByIndex;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetGetMergedCellsCount;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetGetOleObjectByIndex;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetGetOleObjectsCount;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetGetPictureByIndex;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetGetPicturesCount;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetGetRow;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetGetRowsCount;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetGetRowsList;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetGetValidationByIndex;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetGetValidationsCount;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetHideWorksheet;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetMoveWorksheet;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetSetCellStyle;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetSetCellValue;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetSortData;
import com.aspose.cloud.sdk.appdemo.cells_demo.CellsWorksheetUnhideWorksheet;
import com.aspose.cloud.sdk.appdemo.ocr_demo.OcrExtractorExtract1;
import com.aspose.cloud.sdk.appdemo.ocr_demo.OcrExtractorExtract2;
import com.aspose.cloud.sdk.appdemo.ocr_demo.OcrExtractorExtract3;
import com.aspose.cloud.sdk.appdemo.ocr_demo.OcrExtractorExtract4;
import com.aspose.cloud.sdk.appdemo.ocr_demo.OcrExtractorExtract5;
import com.aspose.cloud.sdk.appdemo.ocr_demo.OcrExtractorExtract6;
import com.aspose.cloud.sdk.appdemo.ocr_demo.OcrExtractorExtractTextFromUrl7;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfAnnotationEditorDownloadAttachment;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfAnnotationEditorGetAllAnnotations;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfAnnotationEditorGetAllAttachments;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfAnnotationEditorGetAllBookmarks;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfAnnotationEditorGetAllLinks;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfAnnotationEditorGetAnnotation;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfAnnotationEditorGetAnnotationsCount;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfAnnotationEditorGetAttachment;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfAnnotationEditorGetAttachmentsCount;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfAnnotationEditorGetBookmark;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfAnnotationEditorGetBookmarksCount;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfAnnotationEditorGetChildBookmark;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfAnnotationEditorGetChildBookmarksCount;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfAnnotationEditorGetLink;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfAnnotationEditorGetLinksCount;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfAnnotationEditorIsChildBookmark;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfConverterConvert;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfConverterConvertLocalFile1;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfConverterConvertLocalFile2;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfConverterGetImage1;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfConverterGetImage2;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfDocumentAddNewPage;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfDocumentAppendDocument;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfDocumentAppendDocument2;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfDocumentCreateEmptyPdf;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfDocumentCreateFromHtml;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfDocumentCreateFromXml;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfDocumentDeletePage;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfDocumentGetDocumentProperties;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfDocumentGetDocumentProperty;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfDocumentGetFormField;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfDocumentGetFormFieldCount;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfDocumentGetFormFields;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfDocumentGetPageCount;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfDocumentMergeDocuments;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfDocumentMovePage;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfDocumentRemoveAllProperties;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfDocumentReplaceImageUsingFile;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfDocumentReplaceImageUsingStream;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfDocumentSetDocumentProperty;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfExtractorGetImage1;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfExtractorGetImage2;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfExtractorGetImageCount;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfTextEditorGetFragmentCount;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfTextEditorGetSegmentCount;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfTextEditorGetText1;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfTextEditorGetText2;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfTextEditorGetTextFormat1;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfTextEditorGetTextFormat2;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfTextEditorGetTextItems1;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfTextEditorGetTextItems2;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfTextEditorGetTextItems3;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfTextEditorReplaceText1;
import com.aspose.cloud.sdk.appdemo.pdf_demo.PdfTextEditorReplaceText2;
import com.aspose.cloud.sdk.appdemo.slides_demo.SlidesDocumentGetAllTextItems;
import com.aspose.cloud.sdk.appdemo.slides_demo.SlidesDocumentGetAllTextItems2;
import com.aspose.cloud.sdk.appdemo.slides_demo.SlidesDocumentGetDocumentProperties;
import com.aspose.cloud.sdk.appdemo.slides_demo.SlidesDocumentGetDocumentPropertiesCount;
import com.aspose.cloud.sdk.appdemo.slides_demo.SlidesDocumentGetDocumentProperty;
import com.aspose.cloud.sdk.appdemo.slides_demo.SlidesDocumentGetSlideCount;
import com.aspose.cloud.sdk.appdemo.slides_demo.SlidesDocumentRemoveAllProperties;
import com.aspose.cloud.sdk.appdemo.slides_demo.SlidesDocumentReplaceText;
import com.aspose.cloud.sdk.appdemo.slides_demo.SlidesDocumentReplaceText2;
import com.aspose.cloud.sdk.appdemo.slides_demo.SlidesDocumentSaveAs;
import com.aspose.cloud.sdk.appdemo.slides_demo.SlidesDocumentSaveSlideAs;
import com.aspose.cloud.sdk.appdemo.slides_demo.SlidesDocumentSaveSlideAs2;
import com.aspose.cloud.sdk.appdemo.slides_demo.SlidesDocumentSetDocumentProperty;
import com.aspose.cloud.sdk.appdemo.slides_demo.SlidesExtractorGetImageCount;
import com.aspose.cloud.sdk.appdemo.slides_demo.SlidesExtractorGetImageCount2;
import com.aspose.cloud.sdk.appdemo.slides_demo.SlidesExtractorGetPresentationImages;
import com.aspose.cloud.sdk.appdemo.slides_demo.SlidesExtractorGetShapes;
import com.aspose.cloud.sdk.appdemo.slides_demo.SlidesExtractorGetShapesCount;
import com.aspose.cloud.sdk.appdemo.storage_demo.StorageFolderCreateFolder;
import com.aspose.cloud.sdk.appdemo.storage_demo.StorageFolderDeleteFile;
import com.aspose.cloud.sdk.appdemo.storage_demo.StorageFolderDeleteFolder;
import com.aspose.cloud.sdk.appdemo.storage_demo.StorageFolderFileExist;
import com.aspose.cloud.sdk.appdemo.storage_demo.StorageFolderGetDiscUsage;
import com.aspose.cloud.sdk.appdemo.storage_demo.StorageFolderGetFile;
import com.aspose.cloud.sdk.appdemo.storage_demo.StorageFolderGetFilesList;
import com.aspose.cloud.sdk.appdemo.storage_demo.StorageFolderUploadFile;
import com.aspose.cloud.sdk.appdemo.words_demo.WordConverterConvert1;
import com.aspose.cloud.sdk.appdemo.words_demo.WordConverterConvert2;
import com.aspose.cloud.sdk.appdemo.words_demo.WordConverterConvertLocalFile1;
import com.aspose.cloud.sdk.appdemo.words_demo.WordConverterConvertLocalFile2;
import com.aspose.cloud.sdk.appdemo.words_demo.WordDocumentAppendDocument;
import com.aspose.cloud.sdk.appdemo.words_demo.WordDocumentDeleteProperty;
import com.aspose.cloud.sdk.appdemo.words_demo.WordDocumentGetDocumentInfo;
import com.aspose.cloud.sdk.appdemo.words_demo.WordDocumentGetProperties;
import com.aspose.cloud.sdk.appdemo.words_demo.WordDocumentGetProperty;
import com.aspose.cloud.sdk.appdemo.words_demo.WordDocumentSetProperty;
import com.aspose.cloud.sdk.appdemo.words_demo.WordExtractorConvertDrawingObject;
import com.aspose.cloud.sdk.appdemo.words_demo.WordExtractorGetDrawingObject;
import com.aspose.cloud.sdk.appdemo.words_demo.WordExtractorGetDrawingObjects;
import com.aspose.cloud.sdk.appdemo.words_demo.WordExtractorGetImageData;
import com.aspose.cloud.sdk.appdemo.words_demo.WordExtractorGetOleData;
import com.aspose.cloud.sdk.appdemo.words_demo.WordExtractorGetText;
import com.aspose.cloud.sdk.appdemo.words_demo.WordFieldGetMailMergeFieldNames;
import com.aspose.cloud.sdk.appdemo.words_demo.WordFieldInsertPageNumber;
import com.aspose.cloud.sdk.appdemo.words_demo.WordMailMergeExecuteMailMerege;
import com.aspose.cloud.sdk.appdemo.words_demo.WordMailMergeExecuteMailMeregeWithRegions;
import com.aspose.cloud.sdk.appdemo.words_demo.WordMailMergeExecuteTemplate;
import com.aspose.cloud.appdemo.R;

public class FuncActivity extends Activity {
	private ListView funcList;

	private static final String BARCODE_READER = "BarCodeReader";
	private static final String BARCODE_BUILDER = "BarCodeBuilder";

	private static final String CELLS_CHARTEDITOR = "ChartEditor";
	private static final String CELLS_CONVERTER = "Converter";
	private static final String CELLS_TEXTEDITOR = "TextEditor";
	private static final String CELLS_WORKBOOK = "Workbook";
	private static final String CELLS_WORKSHEET = "Worksheet";

	private static final String OCR_EXTRACTOR = "Extractor";

	private static final String PDF_ANNOTATIONEDITOR = "AnnotationEditor";
	private static final String PDF_CONVERTER = "Converter";
	private static final String PDF_DOCUMENT = "Document";
	private static final String PDF_EXTRACTOR = "Extractor";
	private static final String PDF_TEXTEDITOR = "TextEditor";

	private static final String SLIDE_DOCUMENT = "Document";
	private static final String SLIDE_EXTRACTOR = "Extractor";

	private static final String STORAGE_FOLDER = "Folder";

	private static final String WORD_CONVERTER = "Converter";
	private static final String WORD_DOCUMENT = "Document";
	private static final String WORD_EXTRACTOR = "Extractor";
	private static final String WORD_FIELD = "Field";
	private static final String WORD_MAILMERGE = "MailMerge";

	private String flag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.func_layout);
		init();
		funcList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				getFuncView(flag, position);
			}
		});
	}

	private void init() {
		funcList = (ListView) findViewById(R.id.func_list);
		Bundle getData = getIntent().getExtras();
		String class_name = getData.getString("class_name");
		String func_name = getData.getString("func_name");
		if (class_name.equals("barcode")) {
			if (func_name.equals(BARCODE_BUILDER)) {
				funcList.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1,
						android.R.id.text1, getResources().getStringArray(
								R.array.barcode_builder_func)));
				flag = "barcode_builder";
			}
			if (func_name.equals(BARCODE_READER)) {
				funcList.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1,
						android.R.id.text1, getResources().getStringArray(
								R.array.barcode_reader_func)));
				flag = "barcode_reader";
			}
		}
		if (class_name.equals("cell")) {
			if (func_name.equals(CELLS_CHARTEDITOR)) {
				funcList.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1,
						android.R.id.text1, getResources().getStringArray(
								R.array.cells_charteditor_func)));
				flag = "cells_charteditor";
			}
			if (func_name.equals(CELLS_CONVERTER)) {
				funcList.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1,
						android.R.id.text1, getResources().getStringArray(
								R.array.cells_converter_func)));
				flag = "cells_converter";
			}
			if (func_name.equals(CELLS_TEXTEDITOR)) {
				funcList.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1,
						android.R.id.text1, getResources().getStringArray(
								R.array.cells_texteditor_func)));
				flag = "cells_texteditor";
			}
			if (func_name.equals(CELLS_WORKBOOK)) {
				funcList.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1,
						android.R.id.text1, getResources().getStringArray(
								R.array.cells_workbook_func)));
				flag = "cells_workbook";
			}
			if (func_name.equals(CELLS_WORKSHEET)) {
				funcList.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1,
						android.R.id.text1, getResources().getStringArray(
								R.array.cells_worksheet_func)));
				flag = "cells_worksheet";
			}
		}
		if (class_name.equals("ocr")) {
			if (func_name.equals(OCR_EXTRACTOR)) {
				funcList.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1,
						android.R.id.text1, getResources().getStringArray(
								R.array.ocr_extractor_func)));
				flag = "ocr_extractor";
			}
		}
		if (class_name.equals("pdf")) {
			if (func_name.equals(PDF_ANNOTATIONEDITOR)) {
				funcList.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1,
						android.R.id.text1, getResources().getStringArray(
								R.array.pdf_annotationeditor_func)));
				flag = "pdf_annotationeditor";
			}
			if (func_name.equals(PDF_CONVERTER)) {
				funcList.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1,
						android.R.id.text1, getResources().getStringArray(
								R.array.pdf_converter_func)));
				flag = "pdf_converter";
			}
			if (func_name.equals(PDF_DOCUMENT)) {
				funcList.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1,
						android.R.id.text1, getResources().getStringArray(
								R.array.pdf_document_func)));
				flag = "pdf_document";
			}
			if (func_name.equals(PDF_EXTRACTOR)) {
				funcList.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1,
						android.R.id.text1, getResources().getStringArray(
								R.array.pdf_extractor_func)));
				flag = "pdf_extractor";
			}
			if (func_name.equals(PDF_TEXTEDITOR)) {
				funcList.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1,
						android.R.id.text1, getResources().getStringArray(
								R.array.pdf_texteditor_func)));
				flag = "pdf_texteditor";
			}
		}
		if (class_name.equals("slide")) {
			if (func_name.equals(SLIDE_DOCUMENT)) {
				funcList.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1,
						android.R.id.text1, getResources().getStringArray(
								R.array.slides_document_func)));
				flag = "slide_document";
			}

			if (func_name.equals(SLIDE_EXTRACTOR)) {
				funcList.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1,
						android.R.id.text1, getResources().getStringArray(
								R.array.slides_extractor_func)));
				flag = "slide_extractor";
			}
		}

		if (class_name.equals("word")) {
			if (func_name.equals(WORD_CONVERTER)) {
				funcList.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1,
						android.R.id.text1, getResources().getStringArray(
								R.array.words_converter_func)));
				flag = "word_converter";
			}
			if (func_name.equals(WORD_DOCUMENT)) {
				funcList.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1,
						android.R.id.text1, getResources().getStringArray(
								R.array.words_document_func)));
				flag = "word_document";
			}
			if (func_name.equals(WORD_EXTRACTOR)) {
				funcList.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1,
						android.R.id.text1, getResources().getStringArray(
								R.array.words_extractor_func)));
				flag = "word_extractor";
			}
			if (func_name.equals(WORD_FIELD)) {
				funcList.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1,
						android.R.id.text1, getResources().getStringArray(
								R.array.words_field_func)));
				flag = "word_field";
			}
			if (func_name.equals(WORD_MAILMERGE)) {
				funcList.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1,
						android.R.id.text1, getResources().getStringArray(
								R.array.words_mailmerege_func)));
				flag = "word_mailmerge";
			}
		}
		if (class_name.equals("storage")) {
			if (func_name.equals(STORAGE_FOLDER)) {
				funcList.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1,
						android.R.id.text1, getResources().getStringArray(
								R.array.storage_folder_func)));
				flag = "storage_folder";
			}
		}
	}

	private void getFuncView(String flag, int position) {
		position += 1;
		if (flag.equals("barcode_builder")) {
			switch (position) {
			case 1:
				startActivity(new Intent(FuncActivity.this,
						BarcodeBuilderSave1.class));
				break;
			case 2:
				startActivity(new Intent(FuncActivity.this,
						BarcodeBuilderSave2.class));
				break;
			}
		}
		if (flag.equals("barcode_reader")) {
			switch (position) {
			case 1:
				startActivity(new Intent(FuncActivity.this,
						BarcodeReaderRead1.class));
				break;
			case 2:
				startActivity(new Intent(FuncActivity.this,
						BarcodeReaderRead2.class));
				break;
			case 3:
				startActivity(new Intent(FuncActivity.this,
						BarcodeReaderReadFromLocalImage3.class));
			}
		}

		if (flag.equals("ocr_extractor")) {
			switch (position) {
			case 1:
				startActivity(new Intent(FuncActivity.this,
						OcrExtractorExtract1.class));
				break;
			case 2:
				startActivity(new Intent(FuncActivity.this,
						OcrExtractorExtract2.class));
				break;
			case 3:
				startActivity(new Intent(FuncActivity.this,
						OcrExtractorExtract3.class));
				break;
			case 4:
				startActivity(new Intent(FuncActivity.this,
						OcrExtractorExtract4.class));
				break;
			case 5:
				startActivity(new Intent(FuncActivity.this,
						OcrExtractorExtract5.class));
				break;
			case 6:
				startActivity(new Intent(FuncActivity.this,
						OcrExtractorExtract6.class));
				break;
			case 7:
				startActivity(new Intent(FuncActivity.this,
						OcrExtractorExtractTextFromUrl7.class));
				break;
			}

		}

		if (flag.equals("storage_folder")) {
			switch (position) {
			case 1:
				startActivity(new Intent(FuncActivity.this,
						StorageFolderCreateFolder.class));
				break;
			case 2:
				startActivity(new Intent(FuncActivity.this,
						StorageFolderDeleteFile.class));
				break;
			case 3:
				startActivity(new Intent(FuncActivity.this,
						StorageFolderDeleteFolder.class));
				break;
			case 4:
				startActivity(new Intent(FuncActivity.this,
						StorageFolderFileExist.class));
				break;
			case 5:
				startActivity(new Intent(FuncActivity.this,
						StorageFolderGetDiscUsage.class));
				break;
			case 6:
				startActivity(new Intent(FuncActivity.this,
						StorageFolderGetFile.class));
				break;
			case 7:
				startActivity(new Intent(FuncActivity.this,
						StorageFolderGetFilesList.class));
				break;
			case 8:
				startActivity(new Intent(FuncActivity.this,
						StorageFolderUploadFile.class));
				break;
			}

		}
		if (flag.equals("slide_document")) {
			switch (position) {
			case 1:
				startActivity(new Intent(FuncActivity.this,
						SlidesDocumentGetAllTextItems.class));
				break;
			case 2:
				startActivity(new Intent(FuncActivity.this,
						SlidesDocumentGetAllTextItems2.class));
				break;
			case 3:
				startActivity(new Intent(FuncActivity.this,
						SlidesDocumentGetDocumentProperties.class));
				break;
			case 4:
				startActivity(new Intent(FuncActivity.this,
						SlidesDocumentGetDocumentPropertiesCount.class));
				break;
			case 5:
				startActivity(new Intent(FuncActivity.this,
						SlidesDocumentGetDocumentProperty.class));
				break;
			case 6:
				startActivity(new Intent(FuncActivity.this,
						SlidesDocumentGetSlideCount.class));
				break;
			case 7:
				startActivity(new Intent(FuncActivity.this,
						SlidesDocumentRemoveAllProperties.class));
				break;
			case 8:
				startActivity(new Intent(FuncActivity.this,
						SlidesDocumentReplaceText.class));
				break;
			case 9:
				startActivity(new Intent(FuncActivity.this,
						SlidesDocumentReplaceText2.class));
				break;
			case 10:
				startActivity(new Intent(FuncActivity.this,
						SlidesDocumentSaveAs.class));
				break;
			case 11:
				startActivity(new Intent(FuncActivity.this,
						SlidesDocumentSaveSlideAs.class));
				break;
			case 12:
				startActivity(new Intent(FuncActivity.this,
						SlidesDocumentSaveSlideAs2.class));
				break;
			case 13:
				startActivity(new Intent(FuncActivity.this,
						SlidesDocumentSetDocumentProperty.class));
				break;
			}
		}
		if (flag.equals("slide_extractor")) {
			switch (position) {
			case 1:
				startActivity(new Intent(FuncActivity.this,
						SlidesExtractorGetImageCount.class));
				break;
			case 2:
				startActivity(new Intent(FuncActivity.this,
						SlidesExtractorGetImageCount2.class));
				break;
			case 3:
				startActivity(new Intent(FuncActivity.this,
						SlidesExtractorGetPresentationImages.class));
				break;
			case 4:
				startActivity(new Intent(FuncActivity.this,
						SlidesExtractorGetShapes.class));
				break;
			case 5:
				startActivity(new Intent(FuncActivity.this,
						SlidesExtractorGetShapesCount.class));
				break;
			}
		}

		if (flag.equals("word_converter")) {
			switch (position) {
			case 1:
				startActivity(new Intent(FuncActivity.this,
						WordConverterConvert1.class));
				break;
			case 2:
				startActivity(new Intent(FuncActivity.this,
						WordConverterConvert2.class));
				break;
			case 3:
				startActivity(new Intent(FuncActivity.this,
						WordConverterConvertLocalFile1.class));
				break;
			case 4:
				startActivity(new Intent(FuncActivity.this,
						WordConverterConvertLocalFile2.class));
				break;
			}
		}

		if (flag.equals("word_document")) {
			switch (position) {
			case 1:
				startActivity(new Intent(FuncActivity.this,
						WordDocumentAppendDocument.class));
				break;
			case 2:
				startActivity(new Intent(FuncActivity.this,
						WordDocumentDeleteProperty.class));
				break;
			case 3:
				startActivity(new Intent(FuncActivity.this,
						WordDocumentGetDocumentInfo.class));
				break;
			case 4:
				startActivity(new Intent(FuncActivity.this,
						WordDocumentGetProperties.class));
				break;
			case 5:
				startActivity(new Intent(FuncActivity.this,
						WordDocumentGetProperty.class));
				break;
			case 6:
				startActivity(new Intent(FuncActivity.this,
						WordDocumentSetProperty.class));
				break;
			}
		}
		if (flag.equals("word_extractor")) {
			switch (position) {
			case 1:
				startActivity(new Intent(FuncActivity.this,
						WordExtractorConvertDrawingObject.class));
				break;
			case 2:
				startActivity(new Intent(FuncActivity.this,
						WordExtractorGetDrawingObject.class));
				break;
			case 3:
				startActivity(new Intent(FuncActivity.this,
						WordExtractorGetDrawingObjects.class));
				break;
			case 4:
				startActivity(new Intent(FuncActivity.this,
						WordExtractorGetImageData.class));
				break;
			case 5:
				startActivity(new Intent(FuncActivity.this,
						WordExtractorGetOleData.class));
				break;
			case 6:
				startActivity(new Intent(FuncActivity.this,
						WordExtractorGetText.class));
				break;
			}
		}
		if (flag.equals("word_field")) {
			switch (position) {
			case 1:
				startActivity(new Intent(FuncActivity.this,
						WordFieldGetMailMergeFieldNames.class));
				break;
			case 2:
				startActivity(new Intent(FuncActivity.this,
						WordFieldInsertPageNumber.class));
				break;
			}
		}
		if (flag.equals("word_mailmerge")) {
			switch (position) {
			case 1:
				startActivity(new Intent(FuncActivity.this,
						WordMailMergeExecuteMailMerege.class));
				break;
			case 2:
				startActivity(new Intent(FuncActivity.this,
						WordMailMergeExecuteMailMeregeWithRegions.class));
				break;
			case 3:
				startActivity(new Intent(FuncActivity.this,
						WordMailMergeExecuteTemplate.class));
				break;
			}
		}

		if (flag.equals("pdf_annotationeditor")) {
			switch (position) {
			case 1:
				startActivity(new Intent(FuncActivity.this,
						PdfAnnotationEditorDownloadAttachment.class));
				break;
			case 2:
				startActivity(new Intent(FuncActivity.this,
						PdfAnnotationEditorGetAllAnnotations.class));
				break;
			case 3:
				startActivity(new Intent(FuncActivity.this,
						PdfAnnotationEditorGetAllAttachments.class));
				break;
			case 4:
				startActivity(new Intent(FuncActivity.this,
						PdfAnnotationEditorGetAllBookmarks.class));
				break;
			case 5:
				startActivity(new Intent(FuncActivity.this,
						PdfAnnotationEditorGetAllLinks.class));
				break;
			case 6:
				startActivity(new Intent(FuncActivity.this,
						PdfAnnotationEditorGetAnnotation.class));
				break;
			case 7:
				startActivity(new Intent(FuncActivity.this,
						PdfAnnotationEditorGetAnnotationsCount.class));
				break;
			case 8:
				startActivity(new Intent(FuncActivity.this,
						PdfAnnotationEditorGetAttachment.class));
				break;
			case 9:
				startActivity(new Intent(FuncActivity.this,
						PdfAnnotationEditorGetAttachmentsCount.class));
				break;
			case 10:
				startActivity(new Intent(FuncActivity.this,
						PdfAnnotationEditorGetBookmark.class));
				break;
			case 11:
				startActivity(new Intent(FuncActivity.this,
						PdfAnnotationEditorGetBookmarksCount.class));
				break;
			case 12:
				startActivity(new Intent(FuncActivity.this,
						PdfAnnotationEditorGetChildBookmark.class));
				break;
			case 13:
				startActivity(new Intent(FuncActivity.this,
						PdfAnnotationEditorGetChildBookmarksCount.class));
				break;
			case 14:
				startActivity(new Intent(FuncActivity.this,
						PdfAnnotationEditorGetLink.class));
				break;
			case 15:
				startActivity(new Intent(FuncActivity.this,
						PdfAnnotationEditorGetLinksCount.class));
				break;
			case 16:
				startActivity(new Intent(FuncActivity.this,
						PdfAnnotationEditorIsChildBookmark.class));
				break;
			}
		}
		if (flag.equals("pdf_converter")) {
			switch (position) {
			case 1:
				startActivity(new Intent(FuncActivity.this,
						PdfConverterConvert.class));
				break;
			case 2:
				startActivity(new Intent(FuncActivity.this,
						PdfConverterConvertLocalFile1.class));
				break;
			case 3:
				startActivity(new Intent(FuncActivity.this,
						PdfConverterConvertLocalFile2.class));
				break;
			case 4:
				startActivity(new Intent(FuncActivity.this,
						PdfConverterGetImage1.class));
				break;
			case 5:
				startActivity(new Intent(FuncActivity.this,
						PdfConverterGetImage2.class));
				break;
			}
		}

		if (flag.equals("pdf_extractor")) {
			switch (position) {
			case 1:
				startActivity(new Intent(FuncActivity.this,
						PdfExtractorGetImage1.class));
				break;
			case 2:
				startActivity(new Intent(FuncActivity.this,
						PdfExtractorGetImage2.class));
				break;
			case 3:
				startActivity(new Intent(FuncActivity.this,
						PdfExtractorGetImageCount.class));
				break;
			}
		}

		if (flag.equals("pdf_texteditor")) {
			switch (position) {
			case 1:
				startActivity(new Intent(FuncActivity.this,
						PdfTextEditorGetFragmentCount.class));
				break;
			case 2:
				startActivity(new Intent(FuncActivity.this,
						PdfTextEditorGetSegmentCount.class));
				break;
			case 3:
				startActivity(new Intent(FuncActivity.this,
						PdfTextEditorGetText1.class));
				break;
			case 4:
				startActivity(new Intent(FuncActivity.this,
						PdfTextEditorGetText2.class));
				break;
			case 5:
				startActivity(new Intent(FuncActivity.this,
						PdfTextEditorGetTextFormat1.class));
				break;
			case 6:
				startActivity(new Intent(FuncActivity.this,
						PdfTextEditorGetTextFormat2.class));
				break;
			case 7:
				startActivity(new Intent(FuncActivity.this,
						PdfTextEditorGetTextItems1.class));
				break;
			case 8:
				startActivity(new Intent(FuncActivity.this,
						PdfTextEditorGetTextItems2.class));
				break;
			case 9:
				startActivity(new Intent(FuncActivity.this,
						PdfTextEditorGetTextItems3.class));
				break;
			case 10:
				startActivity(new Intent(FuncActivity.this,
						PdfTextEditorReplaceText1.class));
				break;
			case 11:
				startActivity(new Intent(FuncActivity.this,
						PdfTextEditorReplaceText2.class));
				break;
			}
		}
		if (flag.equals("pdf_document")) {
			switch (position) {
			case 1:
				startActivity(new Intent(FuncActivity.this,
						PdfDocumentAddNewPage.class));
				break;
			case 2:
				startActivity(new Intent(FuncActivity.this,
						PdfDocumentAppendDocument.class));
				break;
			case 3:
				startActivity(new Intent(FuncActivity.this,
						PdfDocumentAppendDocument2.class));
				break;
			case 4:
				startActivity(new Intent(FuncActivity.this,
						PdfDocumentCreateEmptyPdf.class));
				break;
			case 5:
				startActivity(new Intent(FuncActivity.this,
						PdfDocumentCreateFromHtml.class));
				break;
			case 6:
				startActivity(new Intent(FuncActivity.this,
						PdfDocumentCreateFromXml.class));
				break;
			case 7:
				startActivity(new Intent(FuncActivity.this,
						PdfDocumentDeletePage.class));
				break;
			case 8:
				startActivity(new Intent(FuncActivity.this,
						PdfDocumentGetDocumentProperties.class));
				break;
			case 9:
				startActivity(new Intent(FuncActivity.this,
						PdfDocumentGetDocumentProperty.class));
				break;
			case 10:
				startActivity(new Intent(FuncActivity.this,
						PdfDocumentGetFormField.class));
				break;
			case 11:
				startActivity(new Intent(FuncActivity.this,
						PdfDocumentGetFormFieldCount.class));
				break;
			case 12:
				startActivity(new Intent(FuncActivity.this,
						PdfDocumentGetFormFields.class));
				break;
			case 13:
				startActivity(new Intent(FuncActivity.this,
						PdfDocumentGetPageCount.class));
				break;
			case 14:
				startActivity(new Intent(FuncActivity.this,
						PdfDocumentMergeDocuments.class));
				break;
			case 15:
				startActivity(new Intent(FuncActivity.this,
						PdfDocumentMovePage.class));
				break;
			case 16:
				startActivity(new Intent(FuncActivity.this,
						PdfDocumentRemoveAllProperties.class));
				break;
			case 17:
				startActivity(new Intent(FuncActivity.this,
						PdfDocumentReplaceImageUsingFile.class));
				break;
			case 18:
				startActivity(new Intent(FuncActivity.this,
						PdfDocumentReplaceImageUsingStream.class));
				break;
			case 19:
				startActivity(new Intent(FuncActivity.this,
						PdfDocumentSetDocumentProperty.class));
				break;
			}
		}
		if (flag.equals("cells_charteditor")) {
			switch (position) {
			case 1:
				startActivity(new Intent(FuncActivity.this,
						CellsChartEditorAddChart.class));
				break;
			case 2:
				startActivity(new Intent(FuncActivity.this,
						CellsChartEditorDeleteChart.class));
				break;
			case 3:
				startActivity(new Intent(FuncActivity.this,
						CellsChartEditorGetBorder.class));
				break;
			case 4:
				startActivity(new Intent(FuncActivity.this,
						CellsChartEditorGetChartArea.class));
				break;
			case 5:
				startActivity(new Intent(FuncActivity.this,
						CellsChartEditorGetFillFormat.class));
				break;
			}

		}

		if (flag.equals("cells_converter")) {
			switch (position) {
			case 1:
				startActivity(new Intent(FuncActivity.this,
						CellsConverterAutoShapeToImage.class));
				break;
			case 2:
				startActivity(new Intent(FuncActivity.this,
						CellsConverterChartToImage.class));
				break;
			case 3:
				startActivity(new Intent(FuncActivity.this,
						CellsConverterConvertLocalFile.class));
				break;
			case 4:
				startActivity(new Intent(FuncActivity.this,
						CellsConverterOleObjectToImage.class));
				break;
			case 5:
				startActivity(new Intent(FuncActivity.this,
						CellsConverterPictureToImage.class));
				break;
			case 6:
				startActivity(new Intent(FuncActivity.this,
						CellsConverterSave.class));
				break;
			case 7:
				startActivity(new Intent(FuncActivity.this,
						CellsConverterWorksheetToImage.class));
				break;

			}
		}
		if (flag.equals("cells_texteditor")) {
			switch (position) {
			case 1:
				startActivity(new Intent(FuncActivity.this,
						CellsTextEditorFindText.class));
				break;
			case 2:
				startActivity(new Intent(FuncActivity.this,
						CellsTextEditorFindText2.class));
				break;
			case 3:
				startActivity(new Intent(FuncActivity.this,
						CellsTextEditorGetTextItems.class));
				break;
			case 4:
				startActivity(new Intent(FuncActivity.this,
						CellsTextEditorGetTextItems2.class));
				break;
			case 5:
				startActivity(new Intent(FuncActivity.this,
						CellsTextEditorReplaceText.class));
				break;
			case 6:
				startActivity(new Intent(FuncActivity.this,
						CellsTextEditorReplaceText2.class));
				break;
			}
		}

		if (flag.equals("cells_workbook")) {
			switch (position) {
			case 1:
				startActivity(new Intent(FuncActivity.this,
						CellsWorkbookAddWorksheet.class));
				break;
			case 2:
				startActivity(new Intent(FuncActivity.this,
						CellsWorkbookClearModifyPassword.class));
				break;
			case 3:
				startActivity(new Intent(FuncActivity.this,
						CellsWorkbookCreateEmptyWorkbook.class));
				break;
			case 4:
				startActivity(new Intent(
						FuncActivity.this,
						CellsWorkbookCreateWorkbookFromSmartMarkerTemplate.class));
				break;
			case 5:
				startActivity(new Intent(FuncActivity.this,
						CellsWorkbookCreateWorkbookFromTemplate.class));
				break;
			case 6:
				startActivity(new Intent(FuncActivity.this,
						CellsWorkbookDecrpytWorkbook.class));
				break;
			case 7:
				startActivity(new Intent(FuncActivity.this,
						CellsWorkbookEncryptWorkbook.class));
				break;
			case 8:
				startActivity(new Intent(FuncActivity.this,
						CellsWorkbookGetDefaultStyle.class));
				break;
			case 9:
				startActivity(new Intent(FuncActivity.this,
						CellsWorkbookGetNamesCount.class));
				break;
			case 10:
				startActivity(new Intent(FuncActivity.this,
						CellsWorkbookGetProperties.class));
				break;
			case 11:
				startActivity(new Intent(FuncActivity.this,
						CellsWorkbookGetProperty.class));
				break;
			case 12:
				startActivity(new Intent(FuncActivity.this,
						CellsWorkbookGetWorksheetsCount.class));
				break;
			case 13:
				startActivity(new Intent(FuncActivity.this,
						CellsWorkbookMergeWorkbook.class));
				break;
			case 14:
				startActivity(new Intent(FuncActivity.this,
						CellsWorkbookProcessSmartMarker.class));
				break;
			case 15:
				startActivity(new Intent(FuncActivity.this,
						CellsWorkbookProtectWorkbook.class));
				break;
			case 16:
				startActivity(new Intent(FuncActivity.this,
						CellsWorkbookRemoveAllProperties.class));
				break;
			case 17:
				startActivity(new Intent(FuncActivity.this,
						CellsWorkbookRemoveProperty.class));
				break;
			case 18:
				startActivity(new Intent(FuncActivity.this,
						CellsWorkbookRemoveWorksheet.class));
				break;
			case 19:
				startActivity(new Intent(FuncActivity.this,
						CellsWorkbookSetModifyPassword.class));
				break;
			case 20:
				startActivity(new Intent(FuncActivity.this,
						CellsWorkbookSetProperty.class));
				break;
			case 21:
				startActivity(new Intent(FuncActivity.this,
						CellsWorkbookUnprotectWorkbook.class));
				break;
			}
		}
		if (flag.equals("cells_worksheet")) {
			switch (position) {
			case 1:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetCalculateFormula.class));
				break;
			case 2:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetDeleteRow.class));
				break;
			case 3:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetGetAutoShapeByIndex.class));
				break;
			case 4:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetGetAutoShapesCount.class));
				break;
			case 5:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetGetCell.class));
				break;
			case 6:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetGetCellsCount.class));
				break;
			case 7:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetGetCellsList.class));
				break;
			case 8:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetGetCellStyle.class));
				break;
			case 9:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetGetChartByIndex.class));
				break;
			case 10:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetGetChartsCount.class));
				break;
			case 11:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetGetColumn.class));
				break;
			case 12:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetGetColumnsList.class));
				break;
			case 13:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetGetComment.class));
				break;
			case 14:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetGetCommentsCount.class));
				break;
			case 15:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetGetHyperLinkByIndex.class));
				break;
			case 16:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetGetHyperlinksCount.class));
				break;
			case 17:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetGetMaxColumn.class));
				break;
			case 18:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetGetMaxRow.class));
				break;
			case 19:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetGetMergedCellByIndex.class));
				break;
			case 20:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetGetMergedCellsCount.class));
				break;
			case 21:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetGetOleObjectByIndex.class));
				break;
			case 22:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetGetOleObjectsCount.class));
				break;
			case 23:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetGetPictureByIndex.class));
				break;
			case 24:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetGetPicturesCount.class));
				break;
			case 25:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetGetRow.class));
				break;
			case 26:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetGetRowsCount.class));
				break;
			case 27:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetGetRowsList.class));
				break;
			case 28:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetGetValidationByIndex.class));
				break;
			case 29:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetGetValidationsCount.class));
				break;
			case 30:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetHideWorksheet.class));
				break;
			case 31:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetMoveWorksheet.class));
				break;
			case 32:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetSetCellStyle.class));
				break;
			case 33:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetSetCellValue.class));
				break;
			case 34:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetSortData.class));
				break;
			case 35:
				startActivity(new Intent(FuncActivity.this,
						CellsWorksheetUnhideWorksheet.class));
				break;
			}
		}
		if (flag.equals("")) {
			Toast.makeText(this, "Work in Progress", Toast.LENGTH_LONG).show();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflator = getMenuInflater();
		inflator.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() > 0) {
			startActivity(new Intent(FuncActivity.this, Settings.class));
			return true;
		}
		return false;

	}
}
