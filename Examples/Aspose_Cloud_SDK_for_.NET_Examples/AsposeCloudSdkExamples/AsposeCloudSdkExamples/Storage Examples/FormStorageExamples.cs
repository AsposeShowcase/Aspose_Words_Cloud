using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using Aspose.Cloud.Common;
using Aspose.Cloud.Storage;
using System.IO;

namespace AsposeCloudSdkExamples
{
    public partial class FormStorageExamples : Form
    {
        readonly string outputPath = "./../../Output";
        public FormStorageExamples()
        {
            InitializeComponent();
        }

        private void openFileDialog1_FileOk(object sender, CancelEventArgs e)
        {
            txtSelectFile.Text = openFileDialog1.FileName;
            txtFileName.Text = openFileDialog1.SafeFileName;
        }

        private void buttonSelectFile_Click(object sender, EventArgs e)
        {
            openFileDialog1.ShowDialog();
        }

        private void linkUploadFile_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            try
            {
                if (string.IsNullOrEmpty(txtSelectFile.Text))
                {
                    MessageBox.Show("Please select a file to upload");
                }
                else
                {
                    Folder folder = new Folder();
                    folder.UploadFile(txtSelectFile.Text, txtPath.Text);
                    MessageBox.Show("File successfully uploaded");
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void FormStorageExamples_Load(object sender, EventArgs e)
        {
            SetBaseValues();
        }

        private void SetBaseValues()
        {
            Product.BaseProductUri = System.Configuration.ConfigurationSettings.AppSettings["BaseURI"];
            AsposeApp.AppSID = System.Configuration.ConfigurationSettings.AppSettings["AppSid"];
            AsposeApp.AppKey = System.Configuration.ConfigurationSettings.AppSettings["AppKey"];
        }

        private void linkDownloadFile_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            try
            {
                if (string.IsNullOrEmpty(txtFileName.Text))
                {
                    MessageBox.Show("Please enter a file name to download");
                }
                else
                {
                    Folder folder = new Folder();
                    string fileName = (string.IsNullOrEmpty(txtPath.Text) ? txtFileName.Text : txtPath.Text + "/" + txtFileName.Text);
                    using (Stream downloadedStream = folder.GetFile(fileName))
                    {
                        using (Stream fileStream = System.IO.File.OpenWrite(outputPath + "/" + txtFileName.Text))
                        {
                            Utils.CopyStream(downloadedStream, fileStream);
                        }
                    }

                    MessageBox.Show("File successfully downloaded and saved as " + outputPath + "/" + txtFileName.Text);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }

        }

        private void linkDeleteFile_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            try
            {
                if (string.IsNullOrEmpty(txtFileName.Text))
                {
                    MessageBox.Show("Please enter a file name to delete");
                }
                else
                {
                    Folder folder = new Folder();
                    string fileName = (string.IsNullOrEmpty(txtPath.Text) ? txtFileName.Text : txtPath.Text + "/" + txtFileName.Text);
                    folder.DeleteFile(fileName);

                    MessageBox.Show("File has been deleted successfully.");
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void linkFileExist_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            try
            {
                if (string.IsNullOrEmpty(txtFileName.Text) && string.IsNullOrEmpty(txtPath.Text))
                {
                    MessageBox.Show("Please enter a file name or folder path to check if it exists");
                }
                else
                {
                    Folder folder = new Folder();
                    string fileName = (string.IsNullOrEmpty(txtPath.Text) ? txtFileName.Text : txtPath.Text + "/" + txtFileName.Text);
                    FileExist fileExist = folder.FileExist(fileName);
                    string messageText = (fileExist.IsFolder ? "Folder " : "File ") +
                        (fileExist.IsExist ? "is present " : "is not present ") + "on Aspose Cloud Storage";

                    MessageBox.Show(messageText);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void linkDiskUsage_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            try
            {
                Folder folder = new Folder();
                DiscUsage usage = folder.GetDiscUsage();
                StringBuilder sb = new StringBuilder();
                sb.Append("Total Size: " + (usage.TotalSize / (1024 * 1024)) + " MB");
                sb.Append("\nUsed Size: " + usage.UsedSize / 1024 * 1024 + " MB");
                MessageBox.Show(sb.ToString());
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void linkFilesList_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            try
            {
                Folder folder = new Folder();
                List<Aspose.Cloud.Storage.File> files = folder.GetFilesList(txtPath.Text);
                StringBuilder sb = new StringBuilder();
                int count = 1;
                foreach (Aspose.Cloud.Storage.File file in files)
                {
                    sb.Append(count + "\nName: " + file.Name);
                    sb.Append("\nIs Folder: " + file.IsFolder);
                    sb.Append("\nSize: " + file.Size);
                    sb.Append("\nDate Modified: " + file.ModifiedDate);
                    count++;
                }
                    MessageBox.Show(sb.ToString());
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void linkCreateFolder_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            try
            {
                if (string.IsNullOrEmpty(txtPath.Text))
                {
                    MessageBox.Show("Please enter a folder name to create");
                }
                else
                {
                    Folder folder = new Folder();
                    folder.CreateFolder(txtPath.Text);

                    MessageBox.Show("Folder has been created successfully.");
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void linkDeleteFolder_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            try
            {
                if (string.IsNullOrEmpty(txtPath.Text))
                {
                    MessageBox.Show("Please enter a folder name to delete");
                }
                else
                {
                    Folder folder = new Folder();
                    folder.DeleteFolder(txtPath.Text);

                    MessageBox.Show("Folder has been Deleted successfully.");
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
    }
}