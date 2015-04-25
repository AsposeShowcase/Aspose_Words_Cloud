package com.aspose.cloud.sdk.appdemo.ocr_demo;

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
import com.aspose.cloud.sdk.ocr.Extractor;
import com.aspose.cloud.sdk.ocr.OCRResponse;

public class OcrExtractorExtract1 extends Activity {
	private EditText function_arg1;
	private EditText function_arg2;
	private TextView result;
	private Button btnSubmit;
	private String imageFileName;
	private String remoteFolder;
	protected OCRResponse response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BasicConfigurator.configure();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ocr_extractor_extract1);
		init();
		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (function_arg1.getText().length() == 0) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							OcrExtractorExtract1.this);
					dialog.setTitle("Error");
					dialog.setMessage("Please Enter Require Fields");
					dialog.setNeutralButton("Ok", null);
					dialog.show();
				} else {
					imageFileName = function_arg1.getText().toString();
					remoteFolder = function_arg2.getText().toString();
					Extractor obj = new Extractor();
					response = obj.extractText(imageFileName, remoteFolder);
					if (response == null) {
						Toast.makeText(OcrExtractorExtract1.this,
								"Server Response Null", Toast.LENGTH_LONG)
								.show();
						result.append("Oops... Something went wrong");
					} else {
						result.append(response.getText().toString());
					}
				}
			}
		});
	}

	private void init() {
		function_arg1 = (EditText) findViewById(R.id.ocr_extractor_extract1_arg1);
		function_arg2 = (EditText) findViewById(R.id.ocr_extractor_extract1_arg2);
		result = (TextView) findViewById(R.id.ocr_extractor_extract1_result);
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
