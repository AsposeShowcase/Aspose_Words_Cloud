package com.aspose.cloud.sdk.appdemo.pdf_demo;

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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aspose.cloud.appdemo.R;
import com.aspose.cloud.sdk.appdemo.BrowseFileActivity;
import com.aspose.cloud.sdk.common.Product;
import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.pdf.Converter;
import com.aspose.cloud.sdk.pdf.SaveFormat;

public class PdfConverterConvertLocalFile1 extends Activity {
	protected static final int PATH = 1;
	private EditText constructor_arg1;
	private EditText function_arg1, function_arg2;
	private Spinner function_arg3;
	private TextView result;
	private Button btnSubmit, btnBrowse;
	protected String inputPath;
	protected String outputPath;
	protected SaveFormat outputFormat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BasicConfigurator.configure();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pdf_converter_convertlocalfile1);
		init();
		function_arg3
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
						outputFormat = (SaveFormat) function_arg3
								.getItemAtPosition(position);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(PdfConverterConvertLocalFile1.this,
								"Please Select A Image Format",
								Toast.LENGTH_LONG).show();

					}
				});
		btnBrowse.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivityForResult(new Intent(
						PdfConverterConvertLocalFile1.this,
						BrowseFileActivity.class), PATH);
			}
		});
		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (function_arg1.getText().length() == 0
						|| function_arg2.getText().length() == 0
						|| constructor_arg1.getText().length() == 0) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							PdfConverterConvertLocalFile1.this);
					dialog.setTitle("Error");
					dialog.setMessage("Please Enter Require Fields");
					dialog.setNeutralButton("Ok", null);
					dialog.show();
				} else {
					inputPath = function_arg1.getText().toString();
					outputPath = function_arg2.getText().toString();
					Converter obj = new Converter(constructor_arg1.getText()
							.toString());
					try {
						obj.convertLocalFile(inputPath, outputPath,
								outputFormat);
						result.append("file has converted and downloaded in 'AsposeConvertedFiles' folder in your SDCard");
					} catch (Exception e) {
						result.append("Oops..Something went wrong");
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
		constructor_arg1 = (EditText) findViewById(R.id.pdf_converter_constructor_arg1);
		function_arg1 = (EditText) findViewById(R.id.pdf_converter_convertlocalfile1_arg1);
		function_arg2 = (EditText) findViewById(R.id.pdf_converter_convertlocalfile1_arg2);
		function_arg3 = (Spinner) findViewById(R.id.pdf_converter_convertlocalfile1_arg3);
		function_arg3.setAdapter(new ArrayAdapter<SaveFormat>(this,
				android.R.layout.simple_list_item_1, SaveFormat.values()));
		result = (TextView) findViewById(R.id.txt_result);
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
