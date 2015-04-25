package com.aspose.cloud.sdk.appdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.aspose.cloud.appdemo.R;

public class ClassActivity extends Activity {
	private ListView classList;
	private String className;
	private static final int BARCODE = 0;
	private static final int CELLS = 1;
	private static final int OCR = 2;
	private static final int PDF = 3;
	private static final int SLIDES = 4;
	private static final int STORAGE = 5;
	private static final int WORDS = 6;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.class_layout);
		init();
		classList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				Bundle data = new Bundle();
				data.putString("class_name", className);
				data.putString("func_name",
						classList.getItemAtPosition(position).toString());
				startActivity(new Intent(ClassActivity.this, FuncActivity.class)
						.putExtras(data));

			}
		});
	}

	private void init() {
		classList = (ListView) findViewById(R.id.class_list);
		int id = getIntent().getIntExtra("class_num", 0);
		switch (id) {
		case BARCODE:
			classList
					.setAdapter(new ArrayAdapter<String>(this,
							android.R.layout.simple_list_item_1,
							android.R.id.text1, getResources().getStringArray(
									R.array.barcode_class_names)));
			className = "barcode";
			break;
		case CELLS:
			classList.setAdapter(new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, android.R.id.text1,
					getResources().getStringArray(R.array.cells_class_names)));
			className = "cell";
			break;
		case OCR:
			classList.setAdapter(new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, android.R.id.text1,
					getResources().getStringArray(R.array.ocr_class_names)));
			className = "ocr";
			break;
		case PDF:
			classList.setAdapter(new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, android.R.id.text1,
					getResources().getStringArray(R.array.pdf_class_names)));
			className = "pdf";
			break;
		case SLIDES:
			classList.setAdapter(new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, android.R.id.text1,
					getResources().getStringArray(R.array.slides_class_names)));
			className = "slide";
			break;
		case STORAGE:
			classList
					.setAdapter(new ArrayAdapter<String>(this,
							android.R.layout.simple_list_item_1,
							android.R.id.text1, getResources().getStringArray(
									R.array.storage_class_names)));
			className = "storage";
			break;
		case WORDS:
			classList.setAdapter(new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, android.R.id.text1,
					getResources().getStringArray(R.array.words_class_names)));
			className = "word";
			break;
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
			startActivity(new Intent(ClassActivity.this, Settings.class));
			return true;
		}
		return false;

	}
}
