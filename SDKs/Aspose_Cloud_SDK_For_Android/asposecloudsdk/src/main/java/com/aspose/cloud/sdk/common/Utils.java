
package com.aspose.cloud.sdk.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.Environment;
import android.util.Log;

public class Utils {
	
	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
	private static final String TAG = "Utils";
	
	/**
	 * Computes RFC 2104-compliant HMAC signature. * @param data The data to be
	 * signed.
	 * 
	 * @param key
	 *            The signing key.
	 * @return The Base64-encoded RFC 2104-compliant HMAC signature.
	 * @throws java.security.SignatureException
	 *             when signature generation fails
	 */
	public static String sign(String unsignedURL) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
		
		StringBuilder mUnsignedURL = new StringBuilder(unsignedURL);
		
		//Remove any trailing '/' character
		if(mUnsignedURL.charAt(mUnsignedURL.length()-1) == '/') {
			mUnsignedURL.deleteCharAt(mUnsignedURL.length()-1);
		}
		
		if(mUnsignedURL.indexOf("?") == -1) {
			mUnsignedURL.append("?");
		} else {
			mUnsignedURL.append("&");
		}
		
		//Add appSID parameter to the query string
		mUnsignedURL.append("appSID=" + AsposeApp.APP_SID);
		
		//Calculate a hash of the current URL
		Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
		SecretKeySpec key = new SecretKeySpec((AsposeApp.APP_KEY).getBytes("UTF-8"), mac.getAlgorithm());
		mac.init(key);
	
		byte[] bytes = mac.doFinal(mUnsignedURL.toString().getBytes("UTF-8"));
	
		String urlHash = new String(Base64.encodeBase64(bytes));
		
		//Remove any trailing '=' characters
		if(urlHash.endsWith("=")) {
			urlHash = urlHash.substring(0, urlHash.length()-1);
		}
		
		//URL-encode generated string
		urlHash = URLEncoder.encode(urlHash, "utf-8");
		
		//Add the encoded value to the current URL as a signature parameter
		mUnsignedURL.append("&signature=" + urlHash);
		
		return mUnsignedURL.toString();
	}

	public static String uploadFileBinary(File localFile, String uploadUrl, String strHttpCommand) throws IOException {
		
		URL url = new URL(uploadUrl);
		// Returns the contents of the file in a byte array
		byte[] buf = FileUtils.readFileToByteArray(localFile);
		HttpURLConnection m_connection;
		m_connection = (HttpURLConnection) url.openConnection();
		//String parameters = "data=some_post_data";
		m_connection.setDoOutput(true);
		m_connection.setRequestMethod("PUT");
		m_connection.setRequestProperty("Accept", "text/json");
		m_connection.setRequestProperty("Content-Type", "MultiPart/Form-Data");
		//byte bytes[] = parameters.getBytes();
		m_connection.setRequestProperty("Content-length", "" + buf.length);
		
		//Track Android SDK Usage
		m_connection.setRequestProperty("x-saaspose-client", "AndroidSDK/v1.0");
		
		m_connection.connect();
		OutputStream out = m_connection.getOutputStream();
		out.write(buf);
		out.flush();

		InputStream response = m_connection.getInputStream();
		String res = streamToString(response);

		return res;
	}
	
	public static InputStream processCommand(String strURI, String strHttpCommand, String strContent) throws IOException {

		URL url = new URL(strURI);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		
		httpCon.setRequestMethod(strHttpCommand);
		
		httpCon.setRequestProperty("Accept", "application/json");
		httpCon.setRequestProperty("Content-Type", "application/json");
		
		//Track Android SDK Usage
		httpCon.setRequestProperty("x-saaspose-client", "AndroidSDK/v1.0");
				
		httpCon.setDoOutput(true);
		httpCon.setFixedLengthStreamingMode(strContent.length());
		
		OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
		out.write(strContent);
		out.close();
		
		return httpCon.getInputStream();
	}

	public static InputStream processDeleteCommandWithBody(String strURI, String strContent) throws IOException {
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpEntity entity = new StringEntity(strContent);
		HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(strURI);
		httpDelete.addHeader("Accept", "application/json");
		httpDelete.addHeader("Content-Type", "application/json");
		//Track Android SDK Usage
		httpDelete.addHeader("x-saaspose-client", "AndroidSDK/v1.0");
		
		httpDelete.setEntity(entity);
		
		HttpResponse httpResponse = httpclient.execute(httpDelete);
		
		Log.i(TAG, httpResponse.getStatusLine().getReasonPhrase() + " SC: " + httpResponse.getStatusLine().getStatusCode());
	    HttpEntity httpEntity = httpResponse.getEntity();
	    return httpEntity.getContent();	
	}
	
	public static InputStream processCommand(String strURI, String strHttpCommand, InputStream fileStream) throws IOException {
		
		byte[] bytes = IOUtils.toByteArray(fileStream);
		URL url = new URL(strURI);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestProperty("Content-Type", "multipart/form-data");
		
		//Track Android SDK Usage
		httpCon.setRequestProperty("x-saaspose-client", "AndroidSDK/v1.0");
		
		httpCon.setRequestMethod(strHttpCommand);
		httpCon.setFixedLengthStreamingMode(bytes.length);

		OutputStream out = httpCon.getOutputStream();
		out.write(bytes);
		out.flush();
		out.close();
		
		String responseMessage = httpCon.getResponseMessage();
		Log.i(TAG, responseMessage);
		
		return httpCon.getInputStream();
	}
	
	public static InputStream processCommand(String strURI, String strHttpCommand)  throws IOException{

		URL address = new URL(strURI);
		HttpURLConnection httpCon = (HttpURLConnection) address
				.openConnection();
		//httpCon.setDoOutput(true);
		httpCon.setRequestProperty("Content-Type", "application/json");
		httpCon.setRequestProperty("Accept", "application/json");
		//Track Android SDK Usage
		httpCon.setRequestProperty("x-saaspose-client", "AndroidSDK/v1.0");
		
		httpCon.setRequestMethod(strHttpCommand);
		if (strHttpCommand.equals("PUT") || strHttpCommand.equals("POST"))
			httpCon.setFixedLengthStreamingMode(0);
		String responseMessage = httpCon.getResponseMessage();
		Log.i(TAG, responseMessage);
		return httpCon.getInputStream();
	}

	public static InputStream processCommand(String strURI,
			String strHttpCommand, String strContent, String ContentType) throws IOException {
		
		byte[] arr = strContent.getBytes("UTF-8");
		URL address = new URL(strURI);
		HttpURLConnection httpCon = (HttpURLConnection) address
				.openConnection();
		//httpCon.setDoOutput(true);

		if (ContentType.toLowerCase(Locale.US).equalsIgnoreCase("xml"))
			httpCon.setRequestProperty("Content-Type", "application/xml");
		else
			httpCon.setRequestProperty("Content-Type", "application/json");
		
		httpCon.setRequestProperty("Accept", "application/json");
		//Track Android SDK Usage
		httpCon.setRequestProperty("x-saaspose-client", "AndroidSDK/v1.0");
		
		httpCon.setRequestMethod(strHttpCommand);

		httpCon.setFixedLengthStreamingMode(arr.length);

		OutputStream out = httpCon.getOutputStream();
		out.write(arr);
		out.flush();

		String responseMessage = httpCon.getResponseMessage();
		Log.i(TAG, responseMessage);
		
		return httpCon.getInputStream();
	}
	
	public static String streamToString(InputStream stream) {
		try {
			// read it with BufferedReader
			java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(stream));

			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			br.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/* Checks if external storage is available for read and write */
	public static boolean isExternalStorageWritable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}

	public static String saveStreamToFile(InputStream inputStream, String fileName) throws IOException {
		String filePath = null;
		
		if(isExternalStorageWritable()) {
			File sdCard = Environment.getExternalStorageDirectory();
			File dir = new File (sdCard.getAbsolutePath() + "/AsposeFiles");
			if (!dir.exists()) { 
				if(!dir.mkdirs()) {
					Log.e(TAG, "Directory not created");
					return null;
				}
		    }
			
			File file = new File(dir, fileName);
			if(file.length() != 0) {
		    	//delete old file
		    	file.delete();
		    	file.createNewFile();
		    }
			
			FileOutputStream fos = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = inputStream.read(buffer)) != -1) {
            	fos.write(buffer, 0, len1);
            }
            fos.flush();
            fos.close();
            
            filePath = file.getAbsolutePath();
		} 
		
		return filePath;
	}
	
}
