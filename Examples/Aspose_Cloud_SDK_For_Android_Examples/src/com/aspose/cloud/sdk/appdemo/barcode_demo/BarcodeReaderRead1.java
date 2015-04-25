package com.aspose.cloud.sdk.appdemo.barcode_demo;

import java.util.List;

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
import com.aspose.cloud.sdk.barcode.BarCodeReadType;
import com.aspose.cloud.sdk.barcode.BarCodeReader;
import com.aspose.cloud.sdk.barcode.RecognizedBarCode;
import com.aspose.cloud.sdk.common.Product;
import com.aspose.cloud.sdk.common.AsposeApp;

public class BarcodeReaderRead1 extends Activity {
	private TextView result;
	private Button btnSubmit;
	private String remoteImage;
	private String remoteFolder = "";
	private BarCodeReadType readType;
	private EditText function_arg1;
	private EditText function_arg2;
	private Spinner function_arg3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BasicConfigurator.configure();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.barcode_reader_read1);
		init();
		function_arg3
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						readType = (BarCodeReadType) function_arg3
								.getItemAtPosition(position);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(BarcodeReaderRead1.this,
								"Please Select A Barcode Read Type",
								Toast.LENGTH_LONG).show();
					}
				});
		btnSubmit.setOnClickListener(new View.OnClickListener() {

			private List<RecognizedBarCode> response;

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (function_arg1.getText().length() == 0) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							BarcodeReaderRead1.this);
					dialog.setTitle("Error");
					dialog.setMessage("Please Enter Require Fields");
					dialog.setNeutralButton("Ok", null);
					dialog.show();
				} else {
					remoteImage = function_arg1.getText().toString();
					remoteFolder = function_arg2.getText().toString();
					BarCodeReader obj = new BarCodeReader();
					response = obj.read(remoteImage, remoteFolder, readType);
					if (response == null) {
						Toast.makeText(BarcodeReaderRead1.this,
								"Server Response Null", Toast.LENGTH_LONG)
								.show();
						result.append("Oops..Something went wrong");
					} else {
						for (RecognizedBarCode item : response) {
							result.append(item.BarcodeValue());
						}
					}
				}
			}
		});
	}

	private void init() {
		function_arg1 = (EditText) findViewById(R.id.barcode_reader_read1_arg1);
		function_arg2 = (EditText) findViewById(R.id.barcode_reader_read1_arg2);
		function_arg3 = (Spinner) findViewById(R.id.barcode_reader_read1_arg3);
		function_arg3.setAdapter(new ArrayAdapter<BarCodeReadType>(this,
				android.R.layout.simple_list_item_1, BarCodeReadType.values()));
		result = (TextView) findViewById(R.id.barcode_reader_read1_result);
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
