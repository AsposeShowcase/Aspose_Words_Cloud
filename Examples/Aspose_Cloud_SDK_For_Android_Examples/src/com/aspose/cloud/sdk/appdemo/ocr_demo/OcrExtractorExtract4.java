package com.aspose.cloud.sdk.appdemo.ocr_demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.log4j.BasicConfigurator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aspose.cloud.appdemo.R;
import com.aspose.cloud.sdk.appdemo.BrowseFileActivity;
import com.aspose.cloud.sdk.common.Product;
import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.ocr.Extractor;
import com.aspose.cloud.sdk.ocr.LanguageName;
import com.aspose.cloud.sdk.ocr.OCRResponse;

public class OcrExtractorExtract4 extends Activity {
	protected static final int PATH = 1;
	private EditText function_arg1;
	private Spinner function_arg2;
	private CheckBox function_arg3;
	private TextView result;
	private Button btnSubmit, btnBrowse;
	InputStream read;
	private String inputStream;
	private LanguageName language;
	protected OCRResponse response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BasicConfigurator.configure();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ocr_extractor_extract4);
		init();

		function_arg2
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						// TODO Auto-generated method stub
						language = (LanguageName) function_arg2
								.getItemAtPosition(position);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(OcrExtractorExtract4.this,
								"Please Select a Languge", Toast.LENGTH_LONG)
								.show();
					}
				});
		btnBrowse.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivityForResult(new Intent(OcrExtractorExtract4.this,
						BrowseFileActivity.class), PATH);
			}
		});
		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (function_arg1.getText().length() == 0) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							OcrExtractorExtract4.this);
					dialog.setTitle("Error");
					dialog.setMessage("Please Enter Require Fields");
					dialog.setNeutralButton("Ok", null);
					dialog.show();
				} else {
					inputStream = function_arg1.getText().toString();
					File file = new File(inputStream);
					try {
						read = new FileInputStream(file);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						result.append("Oops..Something went wrong");

					}

					Extractor obj = new Extractor();
					response = obj.extractText(read, language,
							function_arg3.isChecked());
					if (response == null) {
						Toast.makeText(OcrExtractorExtract4.this,
								"Server Response Null", Toast.LENGTH_LONG)
								.show();
						result.append("Oops..Something went wrong");
					} else {
						result.append(response.getText().toString());
					}
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == PATH) {
			if (resultCode == RESULT_OK) {
				String path = data.getStringExtra("path");
				if (path != null) {
					function_arg1.setText(path);
				}
			}
		}
	}

	private void init() {
		function_arg1 = (EditText) findViewById(R.id.ocr_extractor_extract4_arg1);
		function_arg2 = (Spinner) findViewById(R.id.ocr_extractor_extract4_arg2);
		function_arg2.setAdapter(new ArrayAdapter<LanguageName>(this,
				android.R.layout.simple_list_item_1, LanguageName.values()));
		function_arg3 = (CheckBox) findViewById(R.id.ocr_extractor_extract4_arg3);
		result = (TextView) findViewById(R.id.ocr_extractor_extract4_result);
		btnBrowse = (Button) findViewById(R.id.btn_browse);
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
