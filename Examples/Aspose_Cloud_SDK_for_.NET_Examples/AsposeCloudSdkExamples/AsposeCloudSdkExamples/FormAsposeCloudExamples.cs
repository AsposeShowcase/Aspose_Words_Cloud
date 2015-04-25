using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace AsposeCloudSdkExamples
{
    public partial class FormAsposeCloudExamples : Form
    {
        public FormAsposeCloudExamples()
        {
            InitializeComponent();
        }

        private void linkStorage_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            Form storageForm = new FormStorageExamples();
            storageForm.Show();
        }
    }
}