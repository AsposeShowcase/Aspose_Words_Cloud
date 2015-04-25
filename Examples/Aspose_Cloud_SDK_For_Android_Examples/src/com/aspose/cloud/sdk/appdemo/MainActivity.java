package com.aspose.cloud.sdk.appdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.aspose.cloud.appdemo.R;

public class MainActivity extends Activity {
	private ListView packageList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		checkProductApp();
		init();
		packageList
					.setOnItemClickListener(new AdapterView.OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int position, long arg3) {
							// TODO Auto-generated method stub
							startActivity(new Intent(MainActivity.this,
									ClassActivity.class).putExtra("class_num",
									position));
						}
					});
		}
	

	private void init() {
		packageList = (ListView) findViewById(R.id.package_list);
		packageList.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				getResources().getStringArray(R.array.all_package_names)));
		disableStrickPolicy();
	}

	private void checkProductApp() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		String app_key = sp.getString("app_key", "");
		String app_sid = sp.getString("app_sid", "");
		if (app_key.equals("") && app_sid.equals("")) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(this)
					.setMessage(
							"AppKey And AppSID are not defined. Please Define it first so that you can use this app")
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									// TODO Auto-generated method stub
									startActivity(new Intent(MainActivity.this,
											Settings.class));
								}
							})
					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									// TODO Auto-generated method stub
									MainActivity.this.finish();
								}
							}).setCancelable(false);

			dialog.show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflator = getMenuInflater();
		inflator.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() > 0) {
			startActivity(new Intent(MainActivity.this, Settings.class));
			return true;
		}
		return false;

	}

	private void disableStrickPolicy() {
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
	}
}
