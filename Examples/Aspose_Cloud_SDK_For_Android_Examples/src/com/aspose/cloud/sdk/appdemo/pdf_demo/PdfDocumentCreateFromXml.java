package com.aspose.cloud.sdk.appdemo.pdf_demo;

import org.apache.log4j.BasicConfigurator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aspose.cloud.appdemo.R;
import com.aspose.cloud.sdk.appdemo.BrowseFileActivity;
import com.aspose.cloud.sdk.common.Product;
import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.pdf.Document;

public class PdfDocumentCreateFromXml extends Activity {
	protected static final int PATH1 = 1;
	protected static final int PATH2 = 2;
	private EditText constructor_arg1;
	private EditText function_arg1, function_arg2, function_arg3;
	private TextView result;
	private Button btnSubmit, btnBrowse1, btnBrowse2;
	protected String pdfFileName;
	protected String xsltFileName;
	protected String xmlFileName;
	protected boolean response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BasicConfigurator.configure();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pdf_document_createfromxml);
		init();
		btnBrowse1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivityForResult(
						new Intent(PdfDocumentCreateFromXml.this,
								BrowseFileActivity.class), PATH1);
			}
		});
		btnBrowse2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivityForResult(
						new Intent(PdfDocumentCreateFromXml.this,
								BrowseFileActivity.class), PATH2);
			}
		});
		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (function_arg1.getText().length() == 0
						|| function_arg2.getText().length() == 0
						|| function_arg3.getText().length() == 0
						|| constructor_arg1.getText().length() == 0) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							PdfDocumentCreateFromXml.this);
					dialog.setTitle("Error");
					dialog.setMessage("Please Enter Require Fields");
					dialog.setNeutralButton("Ok", null);
					dialog.show();
				} else {
					pdfFileName = function_arg1.getText().toString();
					xsltFileName = function_arg2.getText().toString();
					xmlFileName = function_arg3.getText().toString();
					Document obj = new Document(constructor_arg1.getText()
							.toString());
					response = obj.createFromXml(pdfFileName, xsltFileName,
							xmlFileName);
					result.setText(String.valueOf(response));

				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case PATH1:
			if (resultCode == RESULT_OK) {
				if (data != null) {
					function_arg2.setText(data.getStringExtra("path"));
				}
			}
			break;
		case PATH2:
			if (resultCode == RESULT_OK) {
				if (data != null) {
					function_arg3.setText(data.getStringExtra("path"));
				}
			}
			break;
		}
	}

	private void init() {
		constructor_arg1 = (EditText) findViewById(R.id.pdf_document_constructor_arg1);
		function_arg1 = (EditText) findViewById(R.id.pdf_document_createfromxml_arg1);
		function_arg2 = (EditText) findViewById(R.id.pdf_document_createfromxml_arg2);
		function_arg3 = (EditText) findViewById(R.id.pdf_document_createfromxml_arg3);
		result = (TextView) findViewById(R.id.txt_result);
		btnSubmit = (Button) findViewById(R.id.btn_submit);
		btnBrowse1 = (Button) findViewById(R.id.btn_browse1);
		btnBrowse2 = (Button) findViewById(R.id.btn_browse2);
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		String app_sid = sp.getString("app_sid", "");
		String app_key = sp.getString("app_key", "");
		if (app_sid.equals("") || app_key.equals("")) {
			Toast.makeText(this,
					"No App Key or AppSid Define. Please Define Them First",
					Toast.LENGTH_LONG).show();
			this.finish();
		} else {
			AsposeApp.setAppInfo(app_key, app_sid);
			Product.setBaseProductUri("http://api.aspose.com/v1.1");
		}
	}
}
