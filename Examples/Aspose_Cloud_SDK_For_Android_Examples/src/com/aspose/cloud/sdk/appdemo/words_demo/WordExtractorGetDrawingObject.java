package com.aspose.cloud.sdk.appdemo.words_demo;

import org.apache.log4j.BasicConfigurator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aspose.cloud.appdemo.R;
import com.aspose.cloud.sdk.common.Product;
import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.words.Extractor;

public class WordExtractorGetDrawingObject extends Activity {
	private EditText function_arg1, function_arg2;
	private TextView result;
	private Button btnSubmit;
	protected String outputPath;
	protected String strURI;
	protected boolean response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BasicConfigurator.configure();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.words_extractor_getdrawingobject);
		init();

		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (function_arg1.getText().length() == 0
						|| function_arg2.getText().length() == 0) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							WordExtractorGetDrawingObject.this);
					dialog.setTitle("Error");
					dialog.setMessage("Please Enter Require Fields");
					dialog.setNeutralButton("Ok", null);
					dialog.show();
				} else {
					strURI = function_arg1.getText().toString();
					outputPath = function_arg2.getText().toString();
					Extractor obj = new Extractor();
					response = obj.getDrawingObject(strURI, outputPath);
					if (response) {
						result.append("Drawing image downloaded in 'AsposeConvertedFiles' folder in SDCard");
					} else {
						result.append("Oops..Something went wrong");
					}
				}
			}
		});
	}

	private void init() {
		result = (TextView) findViewById(R.id.txt_result);
		function_arg1 = (EditText) findViewById(R.id.word_extractor_getdrawingobject_arg1);
		function_arg2 = (EditText) findViewById(R.id.word_extractor_getdrawingobject_arg2);
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
