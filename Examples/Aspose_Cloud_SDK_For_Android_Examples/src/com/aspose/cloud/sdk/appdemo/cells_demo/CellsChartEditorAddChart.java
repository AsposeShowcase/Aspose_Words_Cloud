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
import com.aspose.cloud.sdk.cells.ChartEditor;
import com.aspose.cloud.sdk.cells.ChartType;
import com.aspose.cloud.sdk.common.Product;
import com.aspose.cloud.sdk.common.AsposeApp;

public class CellsChartEditorAddChart extends Activity {
	private EditText constructor_arg1, constructor_arg2;
	private Spinner function_arg1;
	private EditText function_arg2, function_arg3, function_arg4,
			function_arg5;
	private TextView result;
	private Button btnSubmit;
	protected ChartType chartType;
	protected int upperLeftRow;
	protected int upperLeftColumn;
	protected int lowerRightRow;
	protected int lowerRightColumn;
	protected boolean response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BasicConfigurator.configure();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cells_charteditor_addchart);
		init();

		function_arg1
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
						chartType = (ChartType) function_arg1
								.getItemAtPosition(position);

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(CellsChartEditorAddChart.this,
								"Please Select One Chart Type",
								Toast.LENGTH_LONG).show();
					}
				});

		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				try {
					if (function_arg2.getText().length() == 0
							|| function_arg3.getText().length() == 0
							|| function_arg4.getText().length() == 0
							|| function_arg5.getText().length() == 0
							|| constructor_arg1.getText().length() == 0
							|| constructor_arg2.getText().length() == 0) {
						AlertDialog.Builder dialog = new AlertDialog.Builder(
								CellsChartEditorAddChart.this);
						dialog.setTitle("Error");
						dialog.setMessage("Please Enter Require Fields");
						dialog.setNeutralButton("Ok", null);
						dialog.show();
					} else {
						ChartEditor obj = new ChartEditor(constructor_arg1
								.getText().toString(), constructor_arg2
								.getText().toString());
						upperLeftRow = Integer.parseInt(function_arg2.getText()
								.toString());
						upperLeftColumn = Integer.parseInt(function_arg3
								.getText().toString());
						lowerRightRow = Integer.parseInt(function_arg4
								.getText().toString());
						lowerRightColumn = Integer.parseInt(function_arg5
								.getText().toString());

						response = obj.addChart(chartType, upperLeftRow,
								upperLeftColumn, lowerRightRow,
								lowerRightColumn);
						if (response) {
							result.append("Chart Added in the specified worksheet");
						} else {
							result.append("Oops.. Something went wrong");
						}

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Toast.makeText(CellsChartEditorAddChart.this,
							e.getMessage(), Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
			}
		});
	}

	private void init() {
		function_arg1 = (Spinner) findViewById(R.id.cells_charteditor_addchart_arg1);
		function_arg1.setAdapter(new ArrayAdapter<ChartType>(this,
				android.R.layout.simple_list_item_1, ChartType.values()));
		function_arg2 = (EditText) findViewById(R.id.cells_charteditor_addchart_arg2);
		function_arg3 = (EditText) findViewById(R.id.cells_charteditor_addchart_arg3);
		function_arg4 = (EditText) findViewById(R.id.cells_charteditor_addchart_arg4);
		function_arg5 = (EditText) findViewById(R.id.cells_charteditor_addchart_arg5);
		constructor_arg1 = (EditText) findViewById(R.id.cells_charteditor_constructor_arg1);
		constructor_arg2 = (EditText) findViewById(R.id.cells_charteditor_constructor_arg2);
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
