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
import com.aspose.cloud.sdk.pdf.ConvertImageFormat;
import com.aspose.cloud.sdk.pdf.Converter;
import com.aspose.cloud.sdk.pdf.SaveLocation;

public class PdfConverterGetImage2 extends Activity {
	private EditText constructor_arg1;
	private EditText function_arg1, function_arg2;
	private Spinner function_arg3, function_arg4;
	private TextView result;
	private Button btnSubmit;
	protected String outputPath;
	protected int pageNumber;
	protected ConvertImageFormat imageFormat;
	protected SaveLocation saveLocation;
	protected boolean response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BasicConfigurator.configure();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pdf_converter_getimage2);
		init();
		function_arg3
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
						imageFormat = (ConvertImageFormat) function_arg3
								.getItemAtPosition(position);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(PdfConverterGetImage2.this,
								"Please Select A Image Format",
								Toast.LENGTH_LONG).show();

					}
				});

		function_arg4
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
						saveLocation = (SaveLocation) function_arg4
								.getItemAtPosition(position);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(PdfConverterGetImage2.this,
								"Please Select A Save Location",
								Toast.LENGTH_LONG).show();
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
							PdfConverterGetImage2.this);
					dialog.setTitle("Error");
					dialog.setMessage("Please Enter Require Fields");
					dialog.setNeutralButton("Ok", null);
					dialog.show();
				} else {
					outputPath = function_arg1.getText().toString();
					pageNumber = Integer.parseInt(function_arg2.getText()
							.toString());
					Converter obj = new Converter(constructor_arg1.getText()
							.toString());
					response = obj.getImage(outputPath, pageNumber,
							imageFormat, saveLocation);
					if (response) {
						result.append("Image is converted and downloaded in 'AsposeConvertedFiles' folder in SDCard");
					} else {
						result.append("Oops..Something went wrong");
					}
				}
			}
		});
	}

	private void init() {
		constructor_arg1 = (EditText) findViewById(R.id.pdf_converter_constructor_arg1);
		function_arg1 = (EditText) findViewById(R.id.pdf_converter_getimage2_arg1);
		function_arg2 = (EditText) findViewById(R.id.pdf_converter_getimage2_arg2);
		function_arg3 = (Spinner) findViewById(R.id.pdf_converter_getimage2_arg3);
		function_arg3.setAdapter(new ArrayAdapter<ConvertImageFormat>(this,
				android.R.layout.simple_list_item_1, ConvertImageFormat
						.values()));
		function_arg4 = (Spinner) findViewById(R.id.pdf_converter_getimage2_arg4);
		function_arg4.setAdapter(new ArrayAdapter<SaveLocation>(this,
				android.R.layout.simple_list_item_1, SaveLocation.values()));
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
