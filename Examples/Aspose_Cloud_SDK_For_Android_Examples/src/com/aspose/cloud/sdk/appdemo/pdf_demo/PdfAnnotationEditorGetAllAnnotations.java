package com.aspose.cloud.sdk.appdemo.pdf_demo;

import java.security.SignatureException;
import java.util.List;

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
import com.aspose.cloud.sdk.pdf.Annotation;
import com.aspose.cloud.sdk.pdf.AnnotationEditor;

public class PdfAnnotationEditorGetAllAnnotations extends Activity {
	private EditText constructor_arg1;
	private EditText function_arg1;
	private TextView result;
	private Button btnSubmit;
	protected int pageNumber;
	protected List<Annotation> response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BasicConfigurator.configure();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pdf_annotationeditor_getallannotations);
		init();

		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (function_arg1.getText().length() == 0
						|| constructor_arg1.getText().length() == 0) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							PdfAnnotationEditorGetAllAnnotations.this);
					dialog.setTitle("Error");
					dialog.setMessage("Please Enter Require Fields");
					dialog.setNeutralButton("Ok", null);
					dialog.show();
				} else {
					pageNumber = Integer.parseInt(function_arg1.getText()
							.toString());
					AnnotationEditor obj = new AnnotationEditor(
							constructor_arg1.getText().toString());
					try {
						response = obj.getAllAnnotations(pageNumber);
						if (response == null) {
							Toast.makeText(
									PdfAnnotationEditorGetAllAnnotations.this,
									"Server Response Null", Toast.LENGTH_LONG)
									.show();
							result.append("No Annotation Found");
						} else {
							result.append("\n List : \n");
							for (Annotation item : response) {
								result.append("Annotation Title - "
										+ item.getTitle() + " \n");
							}
						}
					} catch (SignatureException e) {
						// TODO Auto-generated catch block
						Toast.makeText(
								PdfAnnotationEditorGetAllAnnotations.this,
								"Error: " + e.getMessage(), Toast.LENGTH_LONG)
								.show();
						result.append("Oops.. Something went wrong");
						e.printStackTrace();
					}
				}
			}
		});
	}

	private void init() {
		constructor_arg1 = (EditText) findViewById(R.id.pdf_annotationeditor_constructor_arg1);
		function_arg1 = (EditText) findViewById(R.id.pdf_annotationeditor_getallannotations_arg1);
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
