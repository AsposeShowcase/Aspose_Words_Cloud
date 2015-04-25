package com.aspose.cloud.sdk.appdemo.words_demo;

import org.apache.log4j.BasicConfigurator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aspose.cloud.appdemo.R;
import com.aspose.cloud.sdk.common.Product;
import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.words.Document;

public class WordDocumentAppendDocument extends Activity {
	private EditText constructor_arg1;
	private EditText function_arg1, function_arg2, function_arg3;
	private TextView result;
	private Button btnSubmit;

	protected Boolean response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BasicConfigurator.configure();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.words_document_appenddocument);
		init();
		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (function_arg1.getText().length() == 0
						|| function_arg2.getText().length() == 0
						|| constructor_arg1.getText().length() == 0) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							WordDocumentAppendDocument.this);
					dialog.setTitle("Error");
					dialog.setMessage("Please Enter Require Fields");
					dialog.setNeutralButton("Ok", null);
					dialog.show();
				} else {
					String delimiter = ",";
					String[] appendDocs;
					String data = function_arg1.getText().toString();
					appendDocs = data.split(delimiter);
					String[] importFormatModes;
					String data2 = function_arg2.getText().toString();
					importFormatModes = data2.split(delimiter);
					String folder = function_arg3.getText().toString();
					Document obj = new Document("TestDocument.docx");
					response = obj.appendDocument(appendDocs,
							importFormatModes, folder);
					if (response) {
						result.append("Documents Appended Successfully");
					} else {
						result.append("Oops..Something went wrong");
					}

				}
			}
		});
	}

	private void init() {
		constructor_arg1 = (EditText) findViewById(R.id.words_document_constructor_arg1);
		function_arg1 = (EditText) findViewById(R.id.words_document_appenddocuments_arg1);
		function_arg2 = (EditText) findViewById(R.id.words_document_appenddocuments_arg2);
		function_arg3 = (EditText) findViewById(R.id.words_document_appenddocuments_arg3);
		result = (TextView) findViewById(R.id.txt_result);
		btnSubmit = (Button) findViewById(R.id.btn_submit);
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
