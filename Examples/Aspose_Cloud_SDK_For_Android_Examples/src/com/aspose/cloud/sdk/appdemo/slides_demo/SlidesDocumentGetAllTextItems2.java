package com.aspose.cloud.sdk.appdemo.slides_demo;

import java.util.List;

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
import com.aspose.cloud.sdk.slides.Document;
import com.aspose.cloud.sdk.slides.TextItem;

public class SlidesDocumentGetAllTextItems2 extends Activity {
	private EditText constructor_arg1;
	private EditText function_arg1;
	private CheckBox function_arg2;
	private TextView result;
	private Button btnSubmit;

	private int slideNumber;
	protected List<TextItem> response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BasicConfigurator.configure();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slides_document_getalltextitems2);
		init();
		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (function_arg1.getText().length() == 0
						|| constructor_arg1.getText().length() == 0) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							SlidesDocumentGetAllTextItems2.this);
					dialog.setTitle("Error");
					dialog.setMessage("Please Enter Require Fields");
					dialog.setNeutralButton("Ok", null);
					dialog.show();
				} else {
					slideNumber = Integer.parseInt(function_arg1.getText()
							.toString());
					Document obj = new Document(constructor_arg1.getText()
							.toString());
					response = obj.getAllTextItems(slideNumber,
							function_arg2.isChecked());
					if (response == null) {
						Toast.makeText(SlidesDocumentGetAllTextItems2.this,
								"Server Response Null", Toast.LENGTH_LONG)
								.show();
						result.append("Oops..Something went wrong");
					} else {
						result.append(" \n List: \n");
						for (TextItem item : response) {
							result.append(item.getText() + " \n");
						}
					}
				}
			}
		});
	}

	private void init() {
		constructor_arg1 = (EditText) findViewById(R.id.slides_document_constructor_arg1);
		function_arg1 = (EditText) findViewById(R.id.slides_document_getalltextitems2_arg1);
		function_arg2 = (CheckBox) findViewById(R.id.slides_document_getalltextitems2_arg2);
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
