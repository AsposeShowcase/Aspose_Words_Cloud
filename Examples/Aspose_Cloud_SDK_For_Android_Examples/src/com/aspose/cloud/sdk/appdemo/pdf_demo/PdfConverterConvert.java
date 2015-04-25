package com.aspose.cloud.sdk.appdemo.pdf_demo;

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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aspose.cloud.appdemo.R;
import com.aspose.cloud.sdk.common.Product;
import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.pdf.Converter;
import com.aspose.cloud.sdk.pdf.SaveFormat;

public class PdfConverterConvert extends Activity {
	private EditText constructor_arg1;
	private EditText function_arg1;
	private Spinner function_arg2;
	private TextView result;
	private Button btnSubmit;
	protected SaveFormat saveFormat;
	protected String outputPath;
	protected boolean response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BasicConfigurator.configure();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pdf_converter_convert);
		init();
		function_arg2
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
						saveFormat = (SaveFormat) function_arg2
								.getItemAtPosition(position);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(PdfConverterConvert.this,
								"Please Select A Image Format",
								Toast.LENGTH_LONG).show();

					}
				});

		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (function_arg1.getText().length() == 0) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							PdfConverterConvert.this);
					dialog.setTitle("Error");
					dialog.setMessage("Please Enter Require Fields");
					dialog.setNeutralButton("Ok", null);
					dialog.show();
				} else {
					outputPath = function_arg1.getText().toString();
					Converter obj = new Converter(constructor_arg1.getText()
							.toString());
					response = obj.convert(outputPath, saveFormat);
					if (response) {
						result.append("File converted and downloaded in 'AsposeConvertedFiles' Folder in SDCard");
					} else {
						result.append("Oops.. Something went wrong");
					}
				}
			}
		});
	}

	private void init() {
		constructor_arg1 = (EditText) findViewById(R.id.pdf_converter_constructor_arg1);
		function_arg1 = (EditText) findViewById(R.id.pdf_converter_convert_arg1);
		function_arg2 = (Spinner) findViewById(R.id.pdf_converter_convert_arg2);
		function_arg2.setAdapter(new ArrayAdapter<SaveFormat>(this,
				android.R.layout.simple_list_item_1, SaveFormat.values()));
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
