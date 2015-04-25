import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.net.URI;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

// Saaspose Common imports
import com.saaspose.common.Product;
import com.saaspose.common.SaasposeApp;

// Saaspose barcode imports
import com.saaspose.barcode.BarCodeBuilder;
import com.saaspose.barcode.BarCodeType;
import com.saaspose.barcode.GenerationResponse;
import com.saaspose.barcode.ImageFormat;
import com.saaspose.barcode.SaveLocation;

import com.saaspose.words.*;

public class HelloWorld extends HttpServlet 
{

	static Connection con = null;
	static Statement st = null;
	static ResultSet rs = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	PrintWriter out = resp.getWriter();
    	out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
                "Transitional//EN\">\n" +
				"<HTML>\n" +
				"<HEAD><TITLE>Saaspose Heroku Examples for Java</TITLE></HEAD>\n" +
				"<BODY>\n");
    	out.println(showMenu());
    	
    	if (req.getParameter("demo") != null)
    	{
    		if (req.getParameter("demo").equals("barcode") == true)
	    	{
	    		showBarcode(resp);
	    	}
	    	else if (req.getParameter("demo").equals("word") == true)
			{
	    		convertDoc(resp);
			}
	    	else if (req.getParameter("demo").equals("cell") == true)
			{
	    		convertExcel(resp);
			}
	    	else if (req.getParameter("demo").equals("slides") == true)
			{
	    		convertPresentation(resp);
			}
	    	else if (req.getParameter("demo").equals("pdf") == true)
			{
	    		convertPdf(resp);
			}
    	}
    	else
    	{
    		out.println("</BODY></HTML>");
    	}
    }
    
    private static void showBarcode(HttpServletResponse resp)
    {
    	PrintWriter out = null;
    	try
    	{
    		out = resp.getWriter();
    		
    		// Create the barcode and save to local disk
    		//specify product URI
    		Product.setBaseProductUri(System.getenv("SAASPOSE-COM_URL"));

    		//specify App Key and App SID
    		SaasposeApp.setAppKey(System.getenv("SAASPOSE-COM_APPKEY"));
    		SaasposeApp.setAppSID(System.getenv("SAASPOSE-COM_APPSID"));
    		
    		// Create an instance of BarCodeBuilder class
    		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    		Date date = new Date();
    		String codetext = "Heroku test -  " + dateFormat.format(date);
    		BarCodeBuilder builder = new BarCodeBuilder(codetext, BarCodeType.Pdf417);
    		// Save to server
    		GenerationResponse response = builder.Save(
    				SaveLocation.Local, "barcode.png", ImageFormat.PNG);
			
			//out.println("Created the file.</br>");
	    	
			// Send to browser
			resp.reset();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition",
			"attachment;filename=barcode.png");
			
			//out.println("Header setting.</br>");
			
			OutputStream outstream = resp.getOutputStream();
	    	FileInputStream in = new FileInputStream("barcode.png");
	    	byte[] buffer = new byte[4096];
	    	int length;
	    	while ((length = in.read(buffer)) > 0){
	    		outstream.write(buffer, 0, length);
	    	}
	    	in.close();
	    	outstream.flush();
	    	
	    	//out.println("Sent to browser.</br>");
    	}
    	catch(Exception ex)
    	{
    		out.println("Error sending file to browser: " + ex.getStackTrace());
    	}

    }
    
    private static void convertDoc(HttpServletResponse resp)
    {
    	PrintWriter out = null;
    	try
    	{
    		out = resp.getWriter();
    		
    		//specify product URI
    		Product.setBaseProductUri(System.getenv("SAASPOSE-COM_URL"));

    		//specify App Key and App SID
    		SaasposeApp.setAppKey(System.getenv("SAASPOSE-COM_APPKEY"));
    		SaasposeApp.setAppSID(System.getenv("SAASPOSE-COM_APPSID"));
    		
    		// Load a document that is stored on Saaspose Storage
    		Converter docConverter = new Converter("Dinner Invitation.docx");
    		// Convert it to PDF and save it locally
    		docConverter.Convert("Dinner-Invitation.pdf", SaveFormat.Pdf);
			
			//out.println("Created the file.</br>");
	    	
			// Send to browser
			resp.reset();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition",
			"attachment;filename=Dinner-Invitation.pdf");
			
			//out.println("Header setting.</br>");
			
			OutputStream outstream = resp.getOutputStream();
	    	FileInputStream in = new FileInputStream("Dinner-Invitation.pdf");
	    	byte[] buffer = new byte[4096];
	    	int length;
	    	while ((length = in.read(buffer)) > 0){
	    		outstream.write(buffer, 0, length);
	    	}
	    	in.close();
	    	outstream.flush();
	    	
	    	//out.println("Sent to browser.</br>");
    	}
    	catch(Exception ex)
    	{
    		out.println("Error sending file to browser: " + ex.getStackTrace());
    	}
    }
    
    private static void convertExcel(HttpServletResponse resp)
    {
    	PrintWriter out = null;
    	try
    	{
    		out = resp.getWriter();
    		
    		//specify product URI
    		Product.setBaseProductUri(System.getenv("SAASPOSE-COM_URL"));

    		//specify App Key and App SID
    		SaasposeApp.setAppKey(System.getenv("SAASPOSE-COM_APPKEY"));
    		SaasposeApp.setAppSID(System.getenv("SAASPOSE-COM_APPSID"));
    		
    		// Load a document that is stored on Saaspose Storage
    		com.saaspose.cells.Converter docConverter = new com.saaspose.cells.Converter("ProductList.xls");
    		// Convert it to PDF and save it locally
    		docConverter.Save("ProductList.pdf", com.saaspose.cells.SaveFormat.PDF);
			
			//out.println("Created the file.</br>");
	    	
			// Send to browser
			resp.reset();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition",
			"attachment;filename=ProductList.pdf");
			
			//out.println("Header setting.</br>");
			
			OutputStream outstream = resp.getOutputStream();
	    	FileInputStream in = new FileInputStream("ProductList.pdf");
	    	byte[] buffer = new byte[4096];
	    	int length;
	    	while ((length = in.read(buffer)) > 0){
	    		outstream.write(buffer, 0, length);
	    	}
	    	in.close();
	    	outstream.flush();
	    	
	    	//out.println("Sent to browser.</br>");
    	}
    	catch(Exception ex)
    	{
    		out.println("Error sending file to browser: " + ex.getStackTrace());
    	}
    }
    
    private static void convertPresentation(HttpServletResponse resp)
    {
    	PrintWriter out = null;
    	try
    	{
    		out = resp.getWriter();
    		
    		//specify product URI
    		Product.setBaseProductUri(System.getenv("SAASPOSE-COM_URL"));

    		//specify App Key and App SID
    		SaasposeApp.setAppKey(System.getenv("SAASPOSE-COM_APPKEY"));
    		SaasposeApp.setAppSID(System.getenv("SAASPOSE-COM_APPSID"));
    		
    		// Load a document that is stored on Saaspose Storage
    		com.saaspose.slides.Document document = new com.saaspose.slides.Document("Saaspose.pptx");
    		// Convert it to PDF and save it locally
    		document.SaveAs("Saaspose.pdf", com.saaspose.slides.SaveFormat.PDF);
			
			//out.println("Created the file.</br>");
	    	
			// Send to browser
			resp.reset();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition",
			"attachment;filename=Saaspose.pdf");
			
			//out.println("Header setting.</br>");
			
			OutputStream outstream = resp.getOutputStream();
	    	FileInputStream in = new FileInputStream("Saaspose.pdf");
	    	byte[] buffer = new byte[4096];
	    	int length;
	    	while ((length = in.read(buffer)) > 0){
	    		outstream.write(buffer, 0, length);
	    	}
	    	in.close();
	    	outstream.flush();
	    	
	    	//out.println("Sent to browser.</br>");
    	}
    	catch(Exception ex)
    	{
    		out.println("Error sending file to browser: " + ex.getStackTrace());
    	}
    }
    
    private static void convertPdf(HttpServletResponse resp)
    {
    	PrintWriter out = null;
    	try
    	{
    		out = resp.getWriter();
    		
    		//specify product URI
    		Product.setBaseProductUri(System.getenv("SAASPOSE-COM_URL"));

    		//specify App Key and App SID
    		SaasposeApp.setAppKey(System.getenv("SAASPOSE-COM_APPKEY"));
    		SaasposeApp.setAppSID(System.getenv("SAASPOSE-COM_APPSID"));
    		
    		// Load a document that is stored on Saaspose Storage
    		com.saaspose.pdf.Converter converter = new com.saaspose.pdf.Converter("Dinner-Invitation.pdf");
    		// Convert it to TIFF and save it locally
    		converter.Convert("saaspose.tiff", com.saaspose.pdf.SaveFormat.TIFF);
			
			//out.println("Created the file.</br>");
	    	
			// Send to browser
			resp.reset();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition",
			"attachment;filename=saaspose.tiff");
			
			//out.println("Header setting.</br>");
			
			OutputStream outstream = resp.getOutputStream();
	    	FileInputStream in = new FileInputStream("saaspose.tiff");
	    	byte[] buffer = new byte[4096];
	    	int length;
	    	while ((length = in.read(buffer)) > 0){
	    		outstream.write(buffer, 0, length);
	    	}
	    	in.close();
	    	outstream.flush();
	    	
	    	//out.println("Sent to browser.</br>");
    	}
    	catch(Exception ex)
    	{
    		out.println("Error sending file to browser: " + ex.getStackTrace());
    	}
    }
    
    private static String showMenu()
    {
    	String result = "===========================================</br>" +
    			"Saaspose Heroku Examples for Java</br>===========================================</br>" +
    			"1. <a href='?demo=barcode'>Saaspose.Barcode demo</a> - Create a PDF417 2D barcode with current date and time in codetext.</br>" +
    			"2. <a href='?demo=word'>Saaspose.Words demo</a> - Convert a document (DOCX) to PDF format.</br>" +
    			"3. <a href='?demo=cell'>Saaspose.Cells demo</a> - Convert a Spreadsheet (XLS) to PDF format.</br>" +
    			"4. <a href='?demo=slides'>Saaspose.Slides demo</a> - Convert a Presentation (PPTX) to PDF format.</br>" +
    			"5. <a href='?demo=pdf'>Saaspose.Pdf demo</a> - Convert a PDF document to TIFF format.</br>";
    	return result;
    }
    
    private static String testSaasposeBarcodeCreate()
    {
    	String result = "</br></br>========================================================</br>" +
			"3. Test Saaspose addon using the SDK</br>" +
			"========================================================</br>";
    	result += "Getting the URI, App key and App SSID from Heroku config variables....</br>";
    	String uri = uriBuilder();
    	
    	//specify product URI
		Product.setBaseProductUri(System.getenv("SAASPOSE-COM_URL"));

		//specify App Key and App SID
		SaasposeApp.setAppKey(System.getenv("SAASPOSE-COM_APPKEY"));
		SaasposeApp.setAppSID(System.getenv("SAASPOSE-COM_APPSID"));
		
		result += ("Sending request to Saaspose for creating barcode....");
		
		// Create an instance of BarCodeBuilder class
		BarCodeBuilder builder = new BarCodeBuilder("heroku test", BarCodeType.Pdf417);
		// Save to server
		GenerationResponse response = builder.Save(
				SaveLocation.Server, "heroku test.png", ImageFormat.PNG);
		// Check the response, if it is OK, then barcode is saved successfully
		result += ("Save to server. Response from Saaspose: " + response.getStatus());
    			
    	return result;
    }
    
    private static String uriBuilder()
    {
    	String baseUri="", appKey="", appSid="";
    	// Get the 3 parameters from config variables
    	baseUri = System.getenv("SAASPOSE-COM_URL");
    	appKey = System.getenv("SAASPOSE-COM_APPKEY");
    	appSid = System.getenv("SAASPOSE-COM_APPSID");
    	
    	return baseUri + "/barcode/generate?text=test123" + "" +
			"&type=pdf417" + 
			"&format=png" + 
			"&appsid=" + appSid +
			"&appkey=" + appKey;
    }
    
    public static void main(String[] args) throws Exception{
        Server server = new Server(Integer.valueOf(System.getenv("PORT")));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new HelloWorld()),"/*");
        server.start();
        server.join();   
    }

	public static String testConnection()
	{
		String result = "========================================================</br>" +
			"2. Testing Heroku Postgres Dev Addon</br>" +
			"========================================================</br>";
		result += "Trying to connect....</br>";
		
		try 
		{
			URI dbUri = new URI(System.getenv("HEROKU_POSTGRESQL_BLUE_URL"));
			String username = dbUri.getUserInfo().split(":")[0];
		    String password = dbUri.getUserInfo().split(":")[1];
		    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
//		result += "URL: " + dbUrl + "\n";
			con = DriverManager.getConnection(dbUrl, username, password);
			result += "Connected.</br>";
//            con = DriverManager.getConnection(url, username, password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * from phonebook");
            result += "Fetching the records from table....</br>";
            result += ("Phone -- First Name -- Last Name -- Address</br>");
            result += ("========================================================</br>");
            while (rs.next()) {
                result += (rs.getString(1) + " -- " + rs.getString(2) +
                		" -- " + rs.getString(3) + " -- " + rs.getString(4) + "</br>");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            result += "Error: " + ex.getMessage() + "</br>";

        } 
	catch(Exception ex) {
		result += "Error: " + ex.getMessage() + "</br>";
	} finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
                result += "Connection closed.</br>";
            } catch (SQLException ex) {
            	System.err.println(ex.getMessage());
            	result += "Error: " + ex.getMessage() + "</br>";
            }
        }
		
		return result;
	}
}
