package com.aspose.cloud.sdk.appdemo.pdf_demo;

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
import com.aspose.cloud.sdk.pdf.AnnotationEditor;
import com.aspose.cloud.sdk.pdf.Bookmark;

public class PdfAnnotationEditorGetBookmark extends Activity {
	private EditText constructor_arg1;
	private EditText function_arg1;
	private TextView result;
	private Button btnSubmit;
	protected int bookmarkIndex;
	protected Bookmark response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BasicConfigurator.configure();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pdf_annotationeditor_getbookmark);
		init();

		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (function_arg1.getText().length() == 0
						|| constructor_arg1.getText().length() == 0) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							PdfAnnotationEditorGetBookmark.this);
					dialog.setTitle("Error");
					dialog.setMessage("Please Enter Require Fields");
					dialog.setNeutralButton("Ok", null);
					dialog.show();
				} else {
					bookmarkIndex = Integer.parseInt(function_arg1.getText()
							.toString());
					AnnotationEditor obj = new AnnotationEditor(
							constructor_arg1.getText().toString());
					response = obj.getBookmark(bookmarkIndex);
					if (response == null) {
						Toast.makeText(PdfAnnotationEditorGetBookmark.this,
								"Server Response Null", Toast.LENGTH_LONG)
								.show();
						result.append("Oops.. Something went wrong");
					} else {
						result.append("\n Bookmark Title - "
								+ response.getTitle());
					}
				}
			}
		});
	}

	private void init() {
		constructor_arg1 = (EditText) findViewById(R.id.pdf_annotationeditor_constructor_arg1);
		function_arg1 = (EditText) findViewById(R.id.pdf_annotationeditor_getbookmark_arg1);
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
