package com.aspose.cloud.sdk.appdemo.pdf_demo;

import org.apache.log4j.BasicConfigurator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aspose.cloud.appdemo.R;
import com.aspose.cloud.sdk.common.Product;
import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.pdf.TextEditor;

public class PdfTextEditorReplaceText1 extends Activity {
	private EditText constructor_arg1;
	private EditText function_arg1, function_arg2;
	private CheckBox function_arg3;
	private TextView result;
	private Button btnSubmit;
	protected String newText;
	protected String oldText;
	protected int response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BasicConfigurator.configure();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pdf_texteditor_replacetext1);
		init();
		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (function_arg1.getText().length() == 0
						|| function_arg2.getText().length() == 0
						|| constructor_arg1.getText().length() == 0) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							PdfTextEditorReplaceText1.this);
					dialog.setTitle("Error");
					dialog.setMessage("Please Enter Require Fields");
					dialog.setNeutralButton("Ok", null);
					dialog.show();
				} else {
					oldText = function_arg1.getText().toString();
					newText = function_arg2.getText().toString();
					TextEditor obj = new TextEditor(constructor_arg1.getText()
							.toString());
					response = obj.replaceText(oldText, newText,
							function_arg3.isChecked());
					if (response > 0) {
						result.append(response + " Match Founds and Replaced");
					} else {
						result.append("No Match Found");
					}
				}
			}
		});
	}

	private void init() {
		constructor_arg1 = (EditText) findViewById(R.id.pdf_texteditor_constructor_arg1);
		function_arg1 = (EditText) findViewById(R.id.pdf_texteditor_replacetext1_arg1);
		function_arg2 = (EditText) findViewById(R.id.pdf_texteditor_replacetext1_arg2);
		function_arg3 = (CheckBox) findViewById(R.id.pdf_texteditor_replacetext1_arg3);
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
