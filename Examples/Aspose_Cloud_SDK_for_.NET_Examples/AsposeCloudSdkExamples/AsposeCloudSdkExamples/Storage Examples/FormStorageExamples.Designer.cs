namespace AsposeCloudSdkExamples
{
    partial class FormStorageExamples
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.panel1 = new System.Windows.Forms.Panel();
            this.label5 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.txtPath = new System.Windows.Forms.TextBox();
            this.txtFileName = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.buttonSelectFile = new System.Windows.Forms.Button();
            this.txtSelectFile = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.panel2 = new System.Windows.Forms.Panel();
            this.linkDeleteFile = new System.Windows.Forms.LinkLabel();
            this.linkDownloadFile = new System.Windows.Forms.LinkLabel();
            this.linkUploadFile = new System.Windows.Forms.LinkLabel();
            this.openFileDialog1 = new System.Windows.Forms.OpenFileDialog();
            this.linkFileExist = new System.Windows.Forms.LinkLabel();
            this.linkDeleteFolder = new System.Windows.Forms.LinkLabel();
            this.linkCreateFolder = new System.Windows.Forms.LinkLabel();
            this.linkFilesList = new System.Windows.Forms.LinkLabel();
            this.linkDiskUsage = new System.Windows.Forms.LinkLabel();
            this.tableLayoutPanel1.SuspendLayout();
            this.panel1.SuspendLayout();
            this.panel2.SuspendLayout();
            this.SuspendLayout();
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 1;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Absolute, 20F));
            this.tableLayoutPanel1.Controls.Add(this.textBox1, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.panel1, 0, 1);
            this.tableLayoutPanel1.Controls.Add(this.panel2, 0, 2);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 3;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 16.66667F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 23.77049F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 59.28962F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(973, 447);
            this.tableLayoutPanel1.TabIndex = 0;
            // 
            // textBox1
            // 
            this.textBox1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.textBox1.Location = new System.Drawing.Point(3, 3);
            this.textBox1.Multiline = true;
            this.textBox1.Name = "textBox1";
            this.textBox1.ReadOnly = true;
            this.textBox1.Size = new System.Drawing.Size(967, 68);
            this.textBox1.TabIndex = 0;
            // 
            // panel1
            // 
            this.panel1.Controls.Add(this.label5);
            this.panel1.Controls.Add(this.label4);
            this.panel1.Controls.Add(this.txtPath);
            this.panel1.Controls.Add(this.txtFileName);
            this.panel1.Controls.Add(this.label3);
            this.panel1.Controls.Add(this.label2);
            this.panel1.Controls.Add(this.buttonSelectFile);
            this.panel1.Controls.Add(this.txtSelectFile);
            this.panel1.Controls.Add(this.label1);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel1.Location = new System.Drawing.Point(3, 77);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(967, 100);
            this.panel1.TabIndex = 3;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(510, 69);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(452, 13);
            this.label5.TabIndex = 10;
            this.label5.Text = "Path of the cloud strage folder e.g. Folder1 or Folder1/SubFolder1. Leave empty f" +
                "or root folder.";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(510, 43);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(225, 13);
            this.label4.TabIndex = 9;
            this.label4.Text = "Enter name of the file if file is already uploaded";
            // 
            // txtPath
            // 
            this.txtPath.Location = new System.Drawing.Point(96, 66);
            this.txtPath.Name = "txtPath";
            this.txtPath.Size = new System.Drawing.Size(408, 20);
            this.txtPath.TabIndex = 8;
            // 
            // txtFileName
            // 
            this.txtFileName.Location = new System.Drawing.Point(96, 40);
            this.txtFileName.Name = "txtFileName";
            this.txtFileName.Size = new System.Drawing.Size(408, 20);
            this.txtFileName.TabIndex = 7;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(23, 43);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(54, 13);
            this.label3.TabIndex = 6;
            this.label3.Text = "File Name";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(23, 69);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(69, 13);
            this.label2.TabIndex = 5;
            this.label2.Text = "Storage Path";
            // 
            // buttonSelectFile
            // 
            this.buttonSelectFile.Location = new System.Drawing.Point(510, 12);
            this.buttonSelectFile.Name = "buttonSelectFile";
            this.buttonSelectFile.Size = new System.Drawing.Size(105, 23);
            this.buttonSelectFile.TabIndex = 4;
            this.buttonSelectFile.Text = "Browse";
            this.buttonSelectFile.UseVisualStyleBackColor = true;
            this.buttonSelectFile.Click += new System.EventHandler(this.buttonSelectFile_Click);
            // 
            // txtSelectFile
            // 
            this.txtSelectFile.BackColor = System.Drawing.SystemColors.Window;
            this.txtSelectFile.Location = new System.Drawing.Point(96, 14);
            this.txtSelectFile.Name = "txtSelectFile";
            this.txtSelectFile.ReadOnly = true;
            this.txtSelectFile.Size = new System.Drawing.Size(408, 20);
            this.txtSelectFile.TabIndex = 3;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(23, 17);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(56, 13);
            this.label1.TabIndex = 2;
            this.label1.Text = "Select File";
            // 
            // panel2
            // 
            this.panel2.Controls.Add(this.linkDiskUsage);
            this.panel2.Controls.Add(this.linkFilesList);
            this.panel2.Controls.Add(this.linkCreateFolder);
            this.panel2.Controls.Add(this.linkDeleteFolder);
            this.panel2.Controls.Add(this.linkFileExist);
            this.panel2.Controls.Add(this.linkDeleteFile);
            this.panel2.Controls.Add(this.linkDownloadFile);
            this.panel2.Controls.Add(this.linkUploadFile);
            this.panel2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel2.Location = new System.Drawing.Point(3, 183);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(967, 261);
            this.panel2.TabIndex = 4;
            // 
            // linkDeleteFile
            // 
            this.linkDeleteFile.AutoSize = true;
            this.linkDeleteFile.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.linkDeleteFile.Location = new System.Drawing.Point(23, 94);
            this.linkDeleteFile.Name = "linkDeleteFile";
            this.linkDeleteFile.Size = new System.Drawing.Size(241, 16);
            this.linkDeleteFile.TabIndex = 3;
            this.linkDeleteFile.TabStop = true;
            this.linkDeleteFile.Text = "Delete File from Aspose Cloud Storage";
            this.linkDeleteFile.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.linkDeleteFile_LinkClicked);
            // 
            // linkDownloadFile
            // 
            this.linkDownloadFile.AutoSize = true;
            this.linkDownloadFile.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.linkDownloadFile.Location = new System.Drawing.Point(23, 64);
            this.linkDownloadFile.Name = "linkDownloadFile";
            this.linkDownloadFile.Size = new System.Drawing.Size(262, 16);
            this.linkDownloadFile.TabIndex = 2;
            this.linkDownloadFile.TabStop = true;
            this.linkDownloadFile.Text = "Download File from Aspose Cloud Storage";
            this.linkDownloadFile.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.linkDownloadFile_LinkClicked);
            // 
            // linkUploadFile
            // 
            this.linkUploadFile.AutoSize = true;
            this.linkUploadFile.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.linkUploadFile.Location = new System.Drawing.Point(23, 32);
            this.linkUploadFile.Name = "linkUploadFile";
            this.linkUploadFile.Size = new System.Drawing.Size(227, 16);
            this.linkUploadFile.TabIndex = 1;
            this.linkUploadFile.TabStop = true;
            this.linkUploadFile.Text = "Upload File to Aspose Clour Storage";
            this.linkUploadFile.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.linkUploadFile_LinkClicked);
            // 
            // openFileDialog1
            // 
            this.openFileDialog1.FileName = "openFileDialog1";
            this.openFileDialog1.FileOk += new System.ComponentModel.CancelEventHandler(this.openFileDialog1_FileOk);
            // 
            // linkFileExist
            // 
            this.linkFileExist.AutoSize = true;
            this.linkFileExist.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.linkFileExist.Location = new System.Drawing.Point(23, 127);
            this.linkFileExist.Name = "linkFileExist";
            this.linkFileExist.Size = new System.Drawing.Size(275, 16);
            this.linkFileExist.TabIndex = 4;
            this.linkFileExist.TabStop = true;
            this.linkFileExist.Text = "Check if File Exists on Aspose Cloud Storage";
            this.linkFileExist.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.linkFileExist_LinkClicked);
            // 
            // linkDeleteFolder
            // 
            this.linkDeleteFolder.AutoSize = true;
            this.linkDeleteFolder.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.linkDeleteFolder.Location = new System.Drawing.Point(394, 127);
            this.linkDeleteFolder.Name = "linkDeleteFolder";
            this.linkDeleteFolder.Size = new System.Drawing.Size(258, 16);
            this.linkDeleteFolder.TabIndex = 5;
            this.linkDeleteFolder.TabStop = true;
            this.linkDeleteFolder.Text = "Delete Folder from Aspose Cloud Storage";
            this.linkDeleteFolder.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.linkDeleteFolder_LinkClicked);
            // 
            // linkCreateFolder
            // 
            this.linkCreateFolder.AutoSize = true;
            this.linkCreateFolder.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.linkCreateFolder.Location = new System.Drawing.Point(394, 94);
            this.linkCreateFolder.Name = "linkCreateFolder";
            this.linkCreateFolder.Size = new System.Drawing.Size(274, 16);
            this.linkCreateFolder.TabIndex = 6;
            this.linkCreateFolder.TabStop = true;
            this.linkCreateFolder.Text = "Create new Folder on Aspose Cloud Storage";
            this.linkCreateFolder.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.linkCreateFolder_LinkClicked);
            // 
            // linkFilesList
            // 
            this.linkFilesList.AutoSize = true;
            this.linkFilesList.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.linkFilesList.Location = new System.Drawing.Point(394, 64);
            this.linkFilesList.Name = "linkFilesList";
            this.linkFilesList.Size = new System.Drawing.Size(266, 16);
            this.linkFilesList.TabIndex = 7;
            this.linkFilesList.TabStop = true;
            this.linkFilesList.Text = "Get List of Files from Aspose Cloud Storage";
            this.linkFilesList.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.linkFilesList_LinkClicked);
            // 
            // linkDiskUsage
            // 
            this.linkDiskUsage.AutoSize = true;
            this.linkDiskUsage.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.linkDiskUsage.Location = new System.Drawing.Point(394, 32);
            this.linkDiskUsage.Name = "linkDiskUsage";
            this.linkDiskUsage.Size = new System.Drawing.Size(243, 16);
            this.linkDiskUsage.TabIndex = 8;
            this.linkDiskUsage.TabStop = true;
            this.linkDiskUsage.Text = "Check Usage of Aspose Cloud Storage";
            this.linkDiskUsage.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.linkDiskUsage_LinkClicked);
            // 
            // FormStorageExamples
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(973, 447);
            this.Controls.Add(this.tableLayoutPanel1);
            this.Name = "FormStorageExamples";
            this.Text = "Storage Examples";
            this.Load += new System.EventHandler(this.FormStorageExamples_Load);
            this.tableLayoutPanel1.ResumeLayout(false);
            this.tableLayoutPanel1.PerformLayout();
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            this.panel2.ResumeLayout(false);
            this.panel2.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private System.Windows.Forms.TextBox textBox1;
        private System.Windows.Forms.OpenFileDialog openFileDialog1;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Button buttonSelectFile;
        private System.Windows.Forms.TextBox txtSelectFile;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox txtPath;
        private System.Windows.Forms.TextBox txtFileName;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.LinkLabel linkDeleteFile;
        private System.Windows.Forms.LinkLabel linkDownloadFile;
        private System.Windows.Forms.LinkLabel linkUploadFile;
        private System.Windows.Forms.LinkLabel linkFileExist;
        private System.Windows.Forms.LinkLabel linkDiskUsage;
        private System.Windows.Forms.LinkLabel linkFilesList;
        private System.Windows.Forms.LinkLabel linkCreateFolder;
        private System.Windows.Forms.LinkLabel linkDeleteFolder;
    }
}