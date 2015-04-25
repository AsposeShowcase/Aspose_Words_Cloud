package com.aspose.cloud.sdk.appdemo.cells_demo;

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
import com.aspose.cloud.sdk.cells.Converter;
import com.aspose.cloud.sdk.cells.ImageFormat;
import com.aspose.cloud.sdk.common.Product;
import com.aspose.cloud.sdk.common.AsposeApp;

public class CellsConverterWorksheetToImage extends Activity {
	private EditText constructor_arg1, constructor_arg2;
	private EditText function_arg1;
	private Spinner function_arg2;
	private TextView result;
	private Button btnSubmit;
	protected String outputFileName;
	protected ImageFormat outputformat;
	protected boolean response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BasicConfigurator.configure();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cells_converter_worksheettoimage);
		init();

		function_arg2
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> prent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						outputformat = (ImageFormat) function_arg2
								.getItemAtPosition(position);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(CellsConverterWorksheetToImage.this,
								"Please Selecte An Image Format",
								Toast.LENGTH_LONG).show();
					}
				});

		btnSubmit.setOnClickListener(new View.OnClickListener() {

			private ImageFormat outputFormat;

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (function_arg1.getText().length() == 0
						|| constructor_arg1.getText().length() == 0
						|| constructor_arg2.getText().length() == 0) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							CellsConverterWorksheetToImage.this);
					dialog.setTitle("Error");
					dialog.setMessage("Please Enter Require Fields");
					dialog.setNeutralButton("Ok", null);
					dialog.show();
				} else {
					outputFileName = function_arg1.getText().toString();
					Converter obj = new Converter(constructor_arg1.getText()
							.toString(), constructor_arg2.getText().toString());
					response = obj.worksheetToImage(outputFileName,
							outputFormat);
					if (response) {
						result.append("Worksheet converted and downloaded in 'AsposeConvertedFiles' folder in SDCard");
					} else {
						result.append("Oops.. Something went wrong");
					}
				}
			}
		});

	}

	private void init() {
		constructor_arg1 = (EditText) findViewById(R.id.cells_converter_constructor_arg1);
		constructor_arg2 = (EditText) findViewById(R.id.cells_converter_constructor_arg2);
		function_arg1 = (EditText) findViewById(R.id.cells_converter_worksheettoimage_arg1);
		function_arg2 = (Spinner) findViewById(R.id.cells_converter_worksheettoimage_arg2);
		function_arg2.setAdapter(new ArrayAdapter<ImageFormat>(
				CellsConverterWorksheetToImage.this,
				android.R.layout.simple_list_item_1, ImageFormat.values()));
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
