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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aspose.cloud.appdemo.R;
import com.aspose.cloud.sdk.common.Product;
import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.slides.Extractor;
import com.aspose.cloud.sdk.slides.Shape;

public class SlidesExtractorGetShapes extends Activity {
	private EditText constructor_arg1;
	private EditText function_arg1;
	private TextView result;
	private Button btnSubmit;
	protected int slideNumber;
	protected List<Shape> response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BasicConfigurator.configure();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slides_extractor_getshapes);
		init();
		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (function_arg1.getText().length() == 0
						|| constructor_arg1.getText().length() == 0) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							SlidesExtractorGetShapes.this);
					dialog.setTitle("Error");
					dialog.setMessage("Please Enter Require Fields");
					dialog.setNeutralButton("Ok", null);
					dialog.show();
				} else {
					slideNumber = Integer.parseInt(function_arg1.getText()
							.toString());
					Extractor obj = new Extractor(constructor_arg1.getText()
							.toString());
					response = obj.getShapes(slideNumber);
					if (response != null) {
						result.setText("\n List: \n");
						for (Shape item : response) {
							result.append("Shape Name " + item.getName()
									+ " \n");
						}
					} else {
						result.append("Oops..Something went wrong");
					}
				}
			}
		});
	}

	private void init() {
		constructor_arg1 = (EditText) findViewById(R.id.slides_extractor_constructor_arg1);
		function_arg1 = (EditText) findViewById(R.id.slides_extractor_getshapes_arg1);
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
