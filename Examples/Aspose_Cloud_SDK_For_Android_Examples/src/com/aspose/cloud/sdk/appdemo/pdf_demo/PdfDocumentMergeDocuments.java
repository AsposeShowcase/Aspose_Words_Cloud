package com.aspose.cloud.sdk.appdemo.pdf_demo;

import org.apache.log4j.BasicConfigurator;

import android.app.Activity;
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
import com.aspose.cloud.sdk.pdf.Document;

public class PdfDocumentMergeDocuments extends Activity {
	private EditText arg1, arg2, arg3, arg4;
	private TextView result;
	private Button btnSubmit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		BasicConfigurator.configure();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pdf_document_getpagecount);
		init();
		btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Document obj = new Document("mergeoutput.pdf");
				
			}
		});
	}

	private void init() {
		arg1 = (EditText) findViewById(R.id.pdf_document_appenddocument2_arg1);
		arg2 = (EditText) findViewById(R.id.pdf_document_appenddocument2_arg2);
		arg3 = (EditText) findViewById(R.id.pdf_document_appenddocument2_arg3);
		arg4 = (EditText) findViewById(R.id.pdf_document_appenddocument2_arg4);
		result = (TextView) findViewById(R.id.txt_result);
		btnSubmit = (Button) findViewById(R.id.btn_submit);
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		String app_sid = sp.getString("app_sid", "");
		String app_key = sp.getString("app_key", "");
		if(app_sid.equals("") || app_key.equals("")){
			Toast.makeText(this, "No App Key or AppSid Define. Please Define Them First", Toast.LENGTH_LONG).show();
			this.finish();
		}else{
			AsposeApp.setAppInfo(app_key, app_sid);
			Product.setBaseProductUri("http://api.aspose.com/v1.1");
		}
	}
}
