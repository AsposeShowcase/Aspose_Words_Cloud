package com.aspose.cloud.sdk.appdemo.storage_demo;

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
import com.aspose.cloud.sdk.storage.Folder;

public class StorageFolderDeleteFolder extends Activity {
	private EditText function_arg1;
	private TextView result;
	private Button btnSubmit;

	private String folderName;
	protected boolean response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BasicConfigurator.configure();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.storage_folder_deletefolder);
		init();
		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (function_arg1.getText().length() == 0) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							StorageFolderDeleteFolder.this);
					dialog.setTitle("Error");
					dialog.setMessage("Please Enter Require Fields");
					dialog.setNeutralButton("Ok", null);
					dialog.show();
				} else {
					folderName = function_arg1.getText().toString();
					Folder obj = new Folder();
					try {
						response = obj.deleteFolder(folderName);
						if (response) {
							result.append("Folder Deleted Successfully");
						} else {
							result.append("Oops..Something went wrong");
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						Toast.makeText(StorageFolderDeleteFolder.this,
								"Error: " + e.getMessage(), Toast.LENGTH_LONG)
								.show();
						result.append("Error Occur while performing this task");
						e.printStackTrace();
					}
				}
			}
		});
	}

	private void init() {
		function_arg1 = (EditText) findViewById(R.id.storage_folder_deletefolder_arg1);
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
