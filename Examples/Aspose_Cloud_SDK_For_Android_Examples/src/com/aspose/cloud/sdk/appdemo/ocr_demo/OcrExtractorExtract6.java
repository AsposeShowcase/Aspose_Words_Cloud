package com.aspose.cloud.sdk.appdemo.ocr_demo;

import org.apache.log4j.BasicConfigurator;

import android.app.Activity;
import android.app.AlertDialog;
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
import com.aspose.cloud.sdk.common.Product;
import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.ocr.Extractor;
import com.aspose.cloud.sdk.ocr.LanguageName;
import com.aspose.cloud.sdk.ocr.OCRResponse;

public class OcrExtractorExtract6 extends Activity {
	private EditText function_arg1, function_arg4, function_arg5,
			function_arg6, function_arg7, function_arg8;
	private Spinner function_arg2;
	private CheckBox function_arg3;
	private TextView result;
	private Button btnSubmit;

	private String fileName = "";
	private LanguageName language;
	private String remoteFolder = "";
	private int x = 0;
	private int y = 0;
	private int width = 0;
	private int height = 0;
	protected OCRResponse response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BasicConfigurator.configure();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ocr_extractor_extract6);
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
						Toast.makeText(OcrExtractorExtract6.this,
								"Please Select a Langugae", Toast.LENGTH_LONG)
								.show();
					}
				});

		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (function_arg1.getText().length() == 0
						|| function_arg4.getText().length() == 0
						|| function_arg5.getText().length() == 0
						|| function_arg6.getText().length() == 0
						|| function_arg7.getText().length() == 0) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							OcrExtractorExtract6.this);
					dialog.setTitle("Error");
					dialog.setMessage("Please Enter Require Fields");
					dialog.setNeutralButton("Ok", null);
					dialog.show();
				} else {
					fileName = function_arg1.getText().toString();
					x = Integer.parseInt(function_arg4.getText().toString());
					y = Integer.parseInt(function_arg5.getText().toString());
					width = Integer
							.parseInt(function_arg6.getText().toString());
					height = Integer.parseInt(function_arg7.getText()
							.toString());
					remoteFolder = function_arg8.getText().toString();
					Extractor obj = new Extractor();
					response = obj.ExtractText(fileName, language,
							function_arg3.isChecked(), x, y, width, height,
							remoteFolder);
					if (response == null) {
						Toast.makeText(OcrExtractorExtract6.this,
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

	private void init() {
		function_arg1 = (EditText) findViewById(R.id.ocr_extractor_extract6_arg1);
		function_arg2 = (Spinner) findViewById(R.id.ocr_extractor_extract6_arg2);
		function_arg2.setAdapter(new ArrayAdapter<LanguageName>(this,
				android.R.layout.simple_list_item_1, LanguageName.values()));
		function_arg3 = (CheckBox) findViewById(R.id.ocr_extractor_extract6_arg3);
		function_arg4 = (EditText) findViewById(R.id.ocr_extractor_extract6_arg4);
		function_arg5 = (EditText) findViewById(R.id.ocr_extractor_extract6_arg5);
		function_arg6 = (EditText) findViewById(R.id.ocr_extractor_extract6_arg6);
		function_arg7 = (EditText) findViewById(R.id.ocr_extractor_extract6_arg7);
		function_arg8 = (EditText) findViewById(R.id.ocr_extractor_extract6_arg8);
		result = (TextView) findViewById(R.id.ocr_extractor_extract6_result);
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
