package com.aspose.cloud.sdk.appdemo.barcode_demo;

import java.io.InputStream;

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
import com.aspose.cloud.sdk.barcode.ImageFormat;
import com.aspose.cloud.sdk.common.Product;
import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.storage.Folder;

public class BarcodeBuilderSave2 extends Activity {
	private EditText constructor_arg1;
	private Spinner constructor_arg2;
	private EditText other_arg1;
	private Spinner function_arg1;
	private TextView result;
	private Button btnSubmit;
	private ImageFormat imageFormat;
	private InputStream response;
	protected BarCodeType barcode_type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BasicConfigurator.configure();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.barcode_builder_save2);
		init();

		function_arg1
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View view,
							int position, long arg3) {
						// TODO Auto-generated method stub
						imageFormat = (ImageFormat) function_arg1
								.getItemAtPosition(position);

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(BarcodeBuilderSave2.this,
								"Please Select A Image Format",
								Toast.LENGTH_LONG).show();
					}
				});
		constructor_arg2
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
						barcode_type = (BarCodeType) constructor_arg2
								.getItemAtPosition(position);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(BarcodeBuilderSave2.this,
								"Please Select A Barcode Type",
								Toast.LENGTH_LONG).show();
					}
				});
		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (constructor_arg1.getText().length() == 0
						|| other_arg1.getText().length() == 0) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							BarcodeBuilderSave2.this);
					dialog.setTitle("Error");
					dialog.setMessage("Please Enter Require Fields");
					dialog.setNeutralButton("Ok", null);
					dialog.show();
				} else {
					BarCodeBuilder obj = new BarCodeBuilder(constructor_arg1
							.getText().toString(), barcode_type);
					response = obj.save(imageFormat);
					boolean flag = false;
					if (response == null) {
						result.append("Oops..Something went wrong");
					} else {
						flag = Folder.saveStreamToFile(other_arg1.getText()
								.toString(), response);
					}
					if (flag) {
						Toast.makeText(BarcodeBuilderSave2.this,
								"File Downloaded on your SDCard",
								Toast.LENGTH_LONG).show();
						result.setText("File Is Saved In Folder 'AsposeCovertedFiles' in your SDCard with name 'myAndroidbarCode.jpeg' ");
					} else {
						result.append("Oops...Something went wrong");
					}
				}
			}
		});

	}

	private void init() {
		constructor_arg1 = (EditText) findViewById(R.id.barcode_builder_constructor_arg1);
		constructor_arg2 = (Spinner) findViewById(R.id.barcode_builder_constructor_arg2);
		constructor_arg2.setAdapter(new ArrayAdapter<BarCodeType>(this,
				android.R.layout.simple_list_item_1, BarCodeType.values()));
		function_arg1 = (Spinner) findViewById(R.id.barcode_builder_save2_arg1);
		function_arg1.setAdapter(new ArrayAdapter<ImageFormat>(this,
				android.R.layout.simple_list_item_1, ImageFormat.values()));
		other_arg1 = (EditText) findViewById(R.id.barcode_builder_other_arg1);
		result = (TextView) findViewById(R.id.barcode_builder_save2_result);
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
			Product.setBaseProductUri("http://api.Aspose.com/v1.0");
		}
	}
}
