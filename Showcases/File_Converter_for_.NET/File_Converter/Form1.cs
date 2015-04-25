using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.IO;
using Aspose.Cloud.Common;
using System.Diagnostics;

namespace File_Converter
{
    public partial class Form1 : Form
    {
        string fileName = "";
        string fileExtension = "";
        string outputName = "";
       
        //Dictonaries to List Conversion Formats
        Dictionary<string, Aspose.Cloud.Cells.SaveFormat> excelFormats = new Dictionary<string, Aspose.Cloud.Cells.SaveFormat> { { "CSV File", Aspose.Cloud.Cells.SaveFormat.CSV }, { "XLSX File", Aspose.Cloud.Cells.SaveFormat.XLSX },
                                                    { "XLS File", Aspose.Cloud.Cells.SaveFormat.XLS },{ "PDF File", Aspose.Cloud.Cells.SaveFormat.PDF }, 
                                                    { "XPS File", Aspose.Cloud.Cells.SaveFormat.XPS },{ "Tiff File", Aspose.Cloud.Cells.SaveFormat.TIFF } };

        Dictionary<string, Aspose.Cloud.Slides.SaveFormat> presentationFormats = new Dictionary<string, Aspose.Cloud.Slides.SaveFormat> { { "PDF File", Aspose.Cloud.Slides.SaveFormat.PDF }, 
                                                    { "XPS File", Aspose.Cloud.Slides.SaveFormat.XPS },{ "Tiff File", Aspose.Cloud.Slides.SaveFormat.TIFF }, { "PPTX File", Aspose.Cloud.Slides.SaveFormat.PPTX } };

        Dictionary<string, Aspose.Cloud.Words.SaveFormat> wordsFormats = new Dictionary<string, Aspose.Cloud.Words.SaveFormat> { { "PDF File", Aspose.Cloud.Words.SaveFormat.Pdf }, 
                                                    { "XPS File", Aspose.Cloud.Words.SaveFormat.xps },{ "Tiff File", Aspose.Cloud.Words.SaveFormat.tiff }, { "SVG File", Aspose.Cloud.Words.SaveFormat.svg },
                                                    { "DOC File", Aspose.Cloud.Words.SaveFormat.Doc }, { "DOCX File", Aspose.Cloud.Words.SaveFormat.Docx }};

        Dictionary<string, Aspose.Cloud.Pdf.SaveFormat> pdfFormats = new Dictionary<string, Aspose.Cloud.Pdf.SaveFormat> { { "DOC File", Aspose.Cloud.Pdf.SaveFormat.DOC }, 
                                                    { "XPS File", Aspose.Cloud.Pdf.SaveFormat.XPS },{ "Tiff File", Aspose.Cloud.Pdf.SaveFormat.TIFF }, { "PDF/A File", Aspose.Cloud.Pdf.SaveFormat.pdfa1a },
                                                    { "Pdf/A1B File", Aspose.Cloud.Pdf.SaveFormat.pdfa1b }, { "HTML File", Aspose.Cloud.Pdf.SaveFormat.HTML }};


        public Form1()
        {
            InitializeComponent();

            // Add a link to the LinkLabel.
            LinkLabel.Link link = new LinkLabel.Link();
            link.LinkData = "https://cloud.aspose.com";
            linkLabel1.Links.Add(link);

            //specify product URI
            Product.BaseProductUri = "http://api.aspose.com/v1.1";

            //Set Application SID and Application Key
            AsposeApp.AppSID = txtSID.Text;
            AsposeApp.AppKey = txtKey.Text;

        }

        private void button1_Click(object sender, EventArgs e)
        {
            //Open File Dialog
            DialogResult result = openFileDialog1.ShowDialog(); 
            
            //Check for selection result
            if (result == DialogResult.OK) 
            {
                fileName = openFileDialog1.FileName;
                fileExtension = Path.GetExtension(openFileDialog1.FileName);
                txtInputFile.Text = fileName;

                FillConvertCombo(fileExtension);
            }
           
            
        }

        /// <summary>
        /// Fill Combo box with conversion format values
        /// </summary>
        /// <param name="fileExtension"></param>
        private void FillConvertCombo(string fileExtension)
        {
            switch (fileExtension)
            {
                case ".xls":
                case ".xlsx":
                    comboBox1.DataSource = new BindingSource(excelFormats, null);
                    comboBox1.DisplayMember = "Key";
                    comboBox1.ValueMember = "Value";

                    break;
                case ".doc":
                case ".docx":
                    comboBox1.DataSource = new BindingSource(wordsFormats, null);
                    comboBox1.DisplayMember = "Key";
                    comboBox1.ValueMember = "Value";
                    break;
                case ".ppt":
                case ".pptx":
                    comboBox1.DataSource = new BindingSource(presentationFormats, null);
                    comboBox1.DisplayMember = "Key";
                    comboBox1.ValueMember = "Value";
                    break;
                case ".pdf":
                    comboBox1.DataSource = new BindingSource(pdfFormats, null);
                    comboBox1.DisplayMember = "Key";
                    comboBox1.ValueMember = "Value";
                    break;

            }
        }       

        /// <summary>
        /// Convert Button
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void button2_Click(object sender, EventArgs e)
        {

            // When user clicks button, show the dialog.
            DialogResult result = saveFileDialog1.ShowDialog();

            if (result == DialogResult.OK) // Test result.
            {               
                outputName = saveFileDialog1.FileName;
                
            }
            try
            {
                switch (fileExtension)
                {
                    case ".xls":
                    case ".xlsx":
                        ExcelFile(fileName, comboBox1.Text);
                        break;
                    case ".doc":
                    case ".docx":
                        WordFile(fileName, comboBox1.Text);
                        break;
                    case ".ppt":
                    case ".pptx":
                        PresentationFile(fileName, comboBox1.Text);
                        break;
                    case ".pdf":
                        PdfFile(fileName, comboBox1.Text);
                        break;

                }
                label7.Text = "Conversion completed Successfully";
            }
            catch (Exception exp)
            {
                label7.Text = exp.Message;
            }

        }

        /// <summary>
        /// Convert Excel file to desired format
        /// </summary>
        /// <param name="Filename"></param>
        /// <param name="ConvertFormat"></param>
        public void ExcelFile(string Filename, string ConvertFormat)
        {
            Aspose.Cloud.Cells.Converter converter = new Aspose.Cloud.Cells.Converter();

            Aspose.Cloud.Cells.SaveFormat format;

            excelFormats.TryGetValue(ConvertFormat,out format);            

            converter.ConvertLocalFile(fileName, outputName, format);

        }

        /// <summary>
        /// Convert Word Files to desired format
        /// </summary>
        /// <param name="Filename"></param>
        /// <param name="ConvertFormat"></param>
        public void WordFile(string Filename, string ConvertFormat)
        {

            Aspose.Cloud.Words.Converter converter = new Aspose.Cloud.Words.Converter();

            Aspose.Cloud.Words.SaveFormat format;

            wordsFormats.TryGetValue(ConvertFormat, out format);

            converter.ConvertLocalFile(fileName, outputName, format);

        }

        /// <summary>
        /// Convert Presentation Files to Desired Format
        /// </summary>
        /// <param name="Filename"></param>
        /// <param name="ConvertFormat"></param>
        public void PresentationFile(string Filename, string ConvertFormat)
        {
            Aspose.Cloud.Slides.SaveFormat format;

            presentationFormats.TryGetValue(ConvertFormat, out format);

            using (FileStream stream = new FileStream(fileName, FileMode.Open, FileAccess.Read))
            {
                Aspose.Cloud.Slides.Document document = new Aspose.Cloud.Slides.Document(fileName);
                document.Convert(stream, outputName, format);
            }

        }

        /// <summary>
        /// Convert PDF file to desired format
        /// </summary>
        /// <param name="Filename"></param>
        /// <param name="ConvertFormat"></param>
        public void PdfFile(string Filename, string ConvertFormat)
        {
            Aspose.Cloud.Pdf.Converter converter = new Aspose.Cloud.Pdf.Converter();

            Aspose.Cloud.Pdf.SaveFormat format;

            pdfFormats.TryGetValue(ConvertFormat, out format);

            converter.ConvertLocalFile(fileName, outputName, format);
        }

        private void linkLabel1_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            // Send the URL to the operating system.
            Process.Start(e.Link.LinkData as string);
        }

    }
}
