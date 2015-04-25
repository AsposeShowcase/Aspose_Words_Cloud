package com.aspose.cloud.sdk.appdemo.words_demo;

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
import com.aspose.cloud.sdk.words.MailMerge;
import com.aspose.cloud.sdk.words.SaveFormat;

public class WordMailMergeExecuteMailMeregeWithRegions extends Activity {
	private EditText function_arg1, function_arg2, function_arg4;
	private Spinner function_arg3;
	private TextView result;
	private Button btnSubmit;
	protected String fileName;
	protected String strXML;
	protected SaveFormat saveformat;
	protected String outputPath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BasicConfigurator.configure();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.words_mailmerge_executemailmeregewithregions);
		init();
		function_arg3
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int position, long id) {
						// TODO Auto-generated method stub
						saveformat = (SaveFormat) function_arg3
								.getItemAtPosition(position);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(
								WordMailMergeExecuteMailMeregeWithRegions.this,
								"Please select a save format",
								Toast.LENGTH_LONG).show();
					}
				});

		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (function_arg1.getText().length() == 0
						|| function_arg2.getText().length() == 0
						|| function_arg4.getText().length() == 0) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							WordMailMergeExecuteMailMeregeWithRegions.this);
					dialog.setTitle("Error");
					dialog.setMessage("Please Enter Require Fields");
					dialog.setNeutralButton("Ok", null);
					dialog.show();
				} else {
					fileName = function_arg1.getText().toString();
					strXML = function_arg2.getText().toString();
					outputPath = function_arg4.getText().toString();
					MailMerge obj = new MailMerge();
					obj.executeMailMeregewithRegions(fileName, strXML,
							saveformat, outputPath);
					result.append("True");
				}
			}
		});
	}

	private void init() {
		result = (TextView) findViewById(R.id.txt_result);
		btnSubmit = (Button) findViewById(R.id.btn_submit);
		function_arg1 = (EditText) findViewById(R.id.words_mailmerge_executemailmeregewithregions_arg1);
		function_arg2 = (EditText) findViewById(R.id.words_mailmerge_executemailmeregewithregions_arg2);
		function_arg4 = (EditText) findViewById(R.id.words_mailmerge_executemailmeregewithregions_arg4);
		function_arg3 = (Spinner) findViewById(R.id.words_mailmerge_executemailmeregewithregions_arg3);
		function_arg3.setAdapter(new ArrayAdapter<SaveFormat>(this,
				android.R.layout.simple_list_item_1, SaveFormat.values()));
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
