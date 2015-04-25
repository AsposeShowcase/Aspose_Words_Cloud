package com.aspose.cloud.sdk.appdemo.barcode_demo;

import java.util.List;

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
import com.aspose.cloud.sdk.barcode.BarCodeReadType;
import com.aspose.cloud.sdk.barcode.BarCodeReader;
import com.aspose.cloud.sdk.barcode.RecognizedBarCode;
import com.aspose.cloud.sdk.common.Product;
import com.aspose.cloud.sdk.common.AsposeApp;

public class BarcodeReaderReadFromLocalImage3 extends Activity {
	private EditText function_arg1;
	private EditText function_arg2;
	private Spinner function_arg3;
	private TextView result;
	private Button btnSubmit, btnBrowse;
	private String localImage;
	private String remoteFolder;
	private BarCodeReadType readType;
	protected List<RecognizedBarCode> response;
	protected static final int PATH = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BasicConfigurator.configure();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.barcode_reader_readfromlocalimage3);
		init();

		function_arg3
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int position, long id) {
						// TODO Auto-generated method stub
						readType = (BarCodeReadType) function_arg3
								.getItemAtPosition(position);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(BarcodeReaderReadFromLocalImage3.this,
								"Please Select Barcode Read Type",
								Toast.LENGTH_LONG).show();
					}
				});
		btnBrowse.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivityForResult(new Intent(
						BarcodeReaderReadFromLocalImage3.this,
						BrowseFileActivity.class), PATH);
			}
		});
		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (function_arg1.getText().length() == 0) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							BarcodeReaderReadFromLocalImage3.this);
					dialog.setTitle("Error");
					dialog.setMessage("Please Enter Require Fields");
					dialog.setNeutralButton("Ok", null);
					dialog.show();
				} else {
					localImage = function_arg1.getText().toString();
					remoteFolder = function_arg2.getText().toString();
					BarCodeReader obj = new BarCodeReader();
					if (response == null) {
						Toast.makeText(BarcodeReaderReadFromLocalImage3.this,
								"Server Response Null", Toast.LENGTH_LONG)
								.show();
						result.append("Oops..Something went wrong");
					} else {
						response = obj.readFromLocalImage(localImage,
								remoteFolder, readType);
						for (RecognizedBarCode item : response) {
							result.append(item.BarcodeValue());
						}
					}
				}
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (resultCode == RESULT_OK) {
			if (requestCode == PATH) {
				String path = data.getStringExtra("path");
				if (path == null) {
					Toast.makeText(BarcodeReaderReadFromLocalImage3.this,
							"Path Not Found", Toast.LENGTH_LONG).show();
				} else {
					function_arg1.setText(path);
				}
			}
		}

	}

	private void init() {
		function_arg1 = (EditText) findViewById(R.id.barcode_reader_readfromlocalimage_arg1);
		function_arg2 = (EditText) findViewById(R.id.barcode_reader_readfromlocalimage_arg2);
		function_arg3 = (Spinner) findViewById(R.id.barcode_reader_readfromlocalimage_arg3);
		function_arg3.setAdapter(new ArrayAdapter<BarCodeReadType>(this,
				android.R.layout.simple_list_item_1, BarCodeReadType.values()));
		result = (TextView) findViewById(R.id.barcode_reader_readfromlocalimage_result);
		btnSubmit = (Button) findViewById(R.id.btn_submit);
		btnBrowse = (Button) findViewById(R.id.btn_browse);
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
