package com.aspose.cloud.sdk.appdemo.pdf_demo;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aspose.cloud.appdemo.R;
import com.aspose.cloud.sdk.appdemo.BrowseFileActivity;
import com.aspose.cloud.sdk.common.Product;
import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.pdf.Document;

public class PdfDocumentReplaceImageUsingStream extends Activity {
	protected static final int PATH = 1;
	private EditText constructor_arg1;
	private EditText function_arg1, function_arg2, function_arg3;
	private TextView result;
	private Button btnSubmit, btnBrowse;
	protected int pageNumber;
	protected int imageIndex;
	protected InputStream imageStream;
	protected boolean response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BasicConfigurator.configure();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pdf_document_replaceimageusingstream);
		init();
		btnBrowse.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivityForResult(new Intent(
						PdfDocumentReplaceImageUsingStream.this,
						BrowseFileActivity.class), PATH);
			}
		});
		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (function_arg1.getText().length() == 0
						|| function_arg2.getText().length() == 0
						|| function_arg3.getText().length() == 0
						|| constructor_arg1.getText().length() == 0) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							PdfDocumentReplaceImageUsingStream.this);
					dialog.setTitle("Error");
					dialog.setMessage("Please Enter Require Fields");
					dialog.setNeutralButton("Ok", null);
					dialog.show();
				} else {
					pageNumber = Integer.parseInt(function_arg1.getText()
							.toString());
					imageIndex = Integer.parseInt(function_arg2.getText()
							.toString());
					try {
						imageStream = new FileInputStream(new File(
								function_arg3.getText().toString()));
						Document obj = new Document(constructor_arg1.getText()
								.toString());
						response = obj.replaceImageUsingStream(pageNumber,
								imageIndex, imageStream);
						if (response) {
							result.append("Image Replaced Successfully");
						} else {
							result.append("Oops..Something went wrong");
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						Toast.makeText(PdfDocumentReplaceImageUsingStream.this,
								"Error Occur While Performing this task",
								Toast.LENGTH_LONG).show();
						e.printStackTrace();
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
					String path = data.getStringExtra("path");
					function_arg3.setText(path);
				}
			}
		}
	}

	private void init() {
		constructor_arg1 = (EditText) findViewById(R.id.pdf_document_constructor_arg1);
		function_arg1 = (EditText) findViewById(R.id.pdf_document_replaceimageusingstream_arg1);
		function_arg2 = (EditText) findViewById(R.id.pdf_document_replaceimageusingstream_arg2);
		function_arg3 = (EditText) findViewById(R.id.pdf_document_replaceimageusingstream_arg3);
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
