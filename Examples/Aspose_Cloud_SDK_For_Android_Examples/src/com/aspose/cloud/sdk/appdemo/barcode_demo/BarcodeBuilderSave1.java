package com.aspose.cloud.sdk.appdemo.barcode_demo;

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
import com.aspose.cloud.sdk.barcode.BarCodeBuilder;
import com.aspose.cloud.sdk.barcode.BarCodeType;
import com.aspose.cloud.sdk.barcode.GenerationResponse;
import com.aspose.cloud.sdk.barcode.ImageFormat;
import com.aspose.cloud.sdk.barcode.SaveLocation;
import com.aspose.cloud.sdk.common.Product;
import com.aspose.cloud.sdk.common.AsposeApp;

public class BarcodeBuilderSave1 extends Activity {
	private Spinner function_arg1;
	private EditText function_arg2;
	private EditText constructor_arg1;
	private Spinner function_arg3;
	private Spinner constructor_arg2;
	private TextView result;
	private Button btnSubmit;
	private SaveLocation saveLocation;
	private String outputPath = "";
	private ImageFormat imageFormat;
	private GenerationResponse response;
	protected BarCodeType barcodeType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BasicConfigurator.configure();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.barcode_builder_save1);
		init();
		function_arg1
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
						saveLocation = (SaveLocation) function_arg1
								.getItemAtPosition(position);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(BarcodeBuilderSave1.this,
								"Please Select Save Location",
								Toast.LENGTH_LONG).show();
					}
				});

		function_arg3
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
						imageFormat = (ImageFormat) function_arg3
								.getItemAtPosition(position);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(BarcodeBuilderSave1.this,
								"Please Select Image Format", Toast.LENGTH_LONG)
								.show();
					}
				});
		constructor_arg2
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
						barcodeType = (BarCodeType) constructor_arg2
								.getItemAtPosition(position);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(BarcodeBuilderSave1.this,
								"Please Select Barcode Type", Toast.LENGTH_LONG)
								.show();
					}
				});
		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (function_arg2.getText().length() == 0
						|| constructor_arg1.getText().length() == 0) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							BarcodeBuilderSave1.this);
					dialog.setTitle("Error");
					dialog.setMessage("Please Enter Require Fields");
					dialog.setNeutralButton("Ok", null);
					dialog.show();
				} else {
					outputPath = function_arg2.getText().toString();
					BarCodeBuilder obj1 = new BarCodeBuilder(constructor_arg1
							.getText().toString(), barcodeType);
					response = obj1.save(saveLocation, outputPath, imageFormat);
					if (response == null) {
						Toast.makeText(BarcodeBuilderSave1.this,
								"Server Response Null", Toast.LENGTH_LONG)
								.show();
						result.append("Oopss..Something went wrong");
					} else {
						if (response.getStatus().equals("OK")) {
							result.append("File Is Saved In Folder 'AsposeCovertedFiles' in your SDCard with name '"
									+ function_arg2.getText() + "'");
						}

					}

				}
			}
		});

	}

	private void init() {
		function_arg1 = (Spinner) findViewById(R.id.barcode_builder_save1_arg1);
		function_arg1.setAdapter(new ArrayAdapter<SaveLocation>(this,
				android.R.layout.simple_list_item_1, SaveLocation.values()));
		function_arg2 = (EditText) findViewById(R.id.barcode_builder_save1_arg2);
		function_arg3 = (Spinner) findViewById(R.id.barcode_builder_save1_arg3);
		function_arg3.setAdapter(new ArrayAdapter<ImageFormat>(this,
				android.R.layout.simple_list_item_1, ImageFormat.values()));
		constructor_arg1 = (EditText) findViewById(R.id.barcode_builder_constructor_arg1);
		result = (TextView) findViewById(R.id.txt_result);
		constructor_arg2 = (Spinner) findViewById(R.id.barcode_builder_constructor_arg2);
		constructor_arg2.setAdapter(new ArrayAdapter<BarCodeType>(this,
				android.R.layout.simple_list_item_1, BarCodeType.values()));
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
