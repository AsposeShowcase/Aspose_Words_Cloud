using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Aspose.Cloud;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Configuration;

namespace Aspose.CloudTests
{
    [TestClass()]
    public class StorageServiceTests
    {
        StorageService storageService = new StorageService(Utils.AppSid, Utils.AppKey);

        [TestMethod()]
        public void Storage_File_Tests()
        {
            try
            {
                FileExistResponse fileExistResponse = storageService.File.CheckFileExistance(Utils.CloudStorage_Input_Folder + "/cells-sample.xlsx");
                storageService.File.DownloadFile(Utils.CloudStorage_Input_Folder + "/cells-sample.xlsx", Utils.Local_Output_Path + "test-cells-doc-downloaded.xlsx", 0, string.Empty);

                storageService.File.UploadFile(Utils.Local_Input_Path + "bmp-sample.bmp", Utils.CloudStorage_Output_Folder + "/test-bitmap-copied-from-local.bmp");
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/bmp-sample.bmp", Utils.CloudStorage_Output_Folder + "/test-bitmap-copied.bmp");

                storageService.File.MoveFile(Utils.CloudStorage_Output_Folder + "/test-bitmap-copied-from-local.bmp", Utils.CloudStorage_Output_Folder + "/test-bitmap-moved.bmp");

                storageService.File.RemoveFile(Utils.CloudStorage_Output_Folder + "/test-bitmap-moved.bmp");
                storageService.File.RemoveFile(Utils.CloudStorage_Output_Folder + "/test-bitmap-copied-from-local.bmp");

                storageService.File.CheckDiskUsageOfCurrentAccount(string.Empty);

                FileVersionResponse fileVersionResponse = storageService.File.GetFileVersionsList(Utils.CloudStorage_Input_Folder + "/cells-sample.xlsx", string.Empty);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }


        [TestMethod()]
        public void Storage_Folder_Tests()
        {
            try
            {
                //List<FileResponse> filesList = storageService.Folder.GetFilesListOfRootFolder();
                List<FileResponse> filesListSubFolder = storageService.Folder.GetFilesList(Utils.CloudStorage_Input_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
                storageService.Folder.CreateFolder("TestFolderCreate");
                System.Threading.Thread.Sleep(3000); // Just for testing
                storageService.Folder.CopyFolder("TestFolderCreate", "TestFolder_Copied");
                System.Threading.Thread.Sleep(3000); // Just for testing
                storageService.Folder.MoveFolder("TestFolderCreate", "TestFolder_Copied/TestFolderCreate");
                System.Threading.Thread.Sleep(3000); // Just for testing
                storageService.Folder.RemoveFolder("TestFolder_Copied", true);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }
    }
}
