package com.aspose.cloud.sdk.appdemo.cells_demo;

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
import com.aspose.cloud.sdk.cells.EncryptionType;
import com.aspose.cloud.sdk.cells.Workbook;
import com.aspose.cloud.sdk.common.Product;
import com.aspose.cloud.sdk.common.AsposeApp;

public class CellsWorkbookEncryptWorkbook extends Activity {
	private EditText constructor_arg1;
	private EditText function_arg2, function_arg3;
	private Spinner function_arg1;
	private TextView result;
	private Button btnSubmit;
	protected EncryptionType encryptionType;
	protected String password;
	protected int keyLength;
	protected boolean response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BasicConfigurator.configure();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cells_workbook_encryptworkbook);
		init();
		function_arg1
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
						encryptionType = (EncryptionType) function_arg1
								.getItemAtPosition(position);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(CellsWorkbookEncryptWorkbook.this,
								"Please Select An Encryption Type",
								Toast.LENGTH_LONG).show();
					}
				});
		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (function_arg2.getText().length() == 0
						|| function_arg3.getText().length() == 0
						|| constructor_arg1.getText().length() == 0) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							CellsWorkbookEncryptWorkbook.this);
					dialog.setTitle("Error");
					dialog.setMessage("Please Enter Require Fields");
					dialog.setNeutralButton("Ok", null);
					dialog.show();
				} else {
					password = function_arg2.getText().toString();
					keyLength = Integer.parseInt(function_arg3.getText()
							.toString());
					Workbook obj = new Workbook(constructor_arg1.getText()
							.toString());
					response = obj.encryptWorkbook(encryptionType, password,
							keyLength);
					if (response) {
						result.append("Your Workbook is ecrypted successfully");
					} else {
						result.append("Oops.. Something went wrong.");
					}
				}
			}
		});
	}

	private void init() {
		constructor_arg1 = (EditText) findViewById(R.id.cells_workbook_constructor_arg1);
		function_arg1 = (Spinner) findViewById(R.id.cells_workbook_encryptworkbook_arg1);
		function_arg1.setAdapter(new ArrayAdapter<EncryptionType>(this,
				android.R.layout.simple_list_item_1, EncryptionType.values()));
		function_arg2 = (EditText) findViewById(R.id.cells_workbook_encryptworkbook_arg2);
		function_arg3 = (EditText) findViewById(R.id.cells_workbook_encryptworkbook_arg3);
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
