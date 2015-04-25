package com.aspose.cloud.sdk.appdemo.words_demo;

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
import com.aspose.cloud.sdk.storage.Folder;
import com.aspose.cloud.sdk.words.Converter;
import com.aspose.cloud.sdk.words.SaveFormat;

public class WordConverterConvertLocalFile2 extends Activity {
	protected static final int PATH = 1;
	private EditText constructor_arg1;
	private EditText function_arg1;
	private Spinner function_arg2;
	private TextView result;
	private Button btnSubmit, btnBrowse;
	protected InputStream inputStream;
	protected SaveFormat outputFormat;
	protected InputStream response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BasicConfigurator.configure();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.words_converter_convertlocalfile2);
		init();
		btnBrowse.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivityForResult(new Intent(
						WordConverterConvertLocalFile2.this,
						BrowseFileActivity.class), PATH);
			}
		});
		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (function_arg1.getText().length() == 0
						|| constructor_arg1.getText().length() == 0) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							WordConverterConvertLocalFile2.this);
					dialog.setTitle("Error");
					dialog.setMessage("Please Enter Require Fields");
					dialog.setNeutralButton("Ok", null);
					dialog.show();
				} else {
					File file = new File(function_arg1.getText().toString());
					try {
						inputStream = new FileInputStream(file);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						Toast.makeText(WordConverterConvertLocalFile2.this,
								"Error : " + e.getMessage(), Toast.LENGTH_LONG)
								.show();
						e.printStackTrace();
					}
					Converter obj = new Converter(constructor_arg1.getText()
							.toString());
					response = obj.convertLocalFile(inputStream, outputFormat);
					if (response == null) {
						Toast.makeText(WordConverterConvertLocalFile2.this,
								"Server Response Null", Toast.LENGTH_LONG)
								.show();
						result.append("Error Occred While Performing this task");
					} else {
						boolean flag = Folder.saveStreamToFile(function_arg1
								.getText().toString(), inputStream);
						if (flag) {
							result.append("Document Converted and Downloaded in 'AsposeConvertedFiles' folder in to your SDCard");
						} else {
							result.append("Oops.. Something went wrong");
						}
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
				if (data != null) {
					function_arg1.setText(data.getStringExtra("path"));
				}
			}
		}
	}

	private void init() {
		constructor_arg1 = (EditText) findViewById(R.id.words_converter_constructor_arg1);
		function_arg1 = (EditText) findViewById(R.id.words_converter_convertlocalfile2_arg1);
		function_arg2 = (Spinner) findViewById(R.id.words_converter_convertlocalfile2_arg2);
		function_arg2.setAdapter(new ArrayAdapter<SaveFormat>(this,
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
