using System;
using System.Collections.Generic;
using System.Text;
using Aspose.Cloud.Common;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.IO;

namespace Aspose.Cloud
{
    public class StorageService : BaseService
    {
        public StorageService(string appSid, string appKey)
            : base(appSid, appKey)
        {
            AppSid = appSid;
            AppKey = appKey;
            File = new FileClass(AppSid, AppKey);
            Folder = new Folderlass(AppSid, AppKey);
        }

        public FileClass File { get; set; }
        public Folderlass Folder { get; set; }

        public class FileClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal FileClass(string appSid, string appKey)
            {
                AppSid = appSid;
                AppKey = appKey;
            }

            /// <summary>
            /// Upload or copy a specific file.
            /// </summary>
            /// <param name="path">source file path e.g. /file.ext</param>
            /// <param name="newdest">destination file path on Cloud Storage</param>
            /// <param name="versionID">source file's version</param>
            /// <param name="storage">user's source storage name</param>
            /// <param name="destStorage">user's destination storage name</param>
            private void UploadCopyFile(string path, string newdest, string destStorage = "", int versionID = 0, string storage = "")
            {
                // PUT 	storage/file/{path}?appSID={appSID}&newdest={newdest}&versionID={versionID}&storage={storage}&destStorage={destStorage}

                string apiUrl = @"storage/file/{0}?storage={1}&destStorage={2}";

                if (!string.IsNullOrEmpty(storage))
                {
                    apiUrl = apiUrl + string.Format("&versionID={0}", versionID);
                }

                if (!string.IsNullOrEmpty(path) && System.IO.File.Exists(path))
                {
                    apiUrl = string.Format(apiUrl, newdest, storage, destStorage);
                    ServiceController.Put(apiUrl, AppSid, AppKey, System.IO.File.ReadAllBytes(path));
                }
                else
                {
                    apiUrl = apiUrl + string.Format("&newdest={0}", newdest);
                    apiUrl = string.Format(apiUrl, path, storage, destStorage);
                    ServiceController.Put(apiUrl, AppSid, AppKey);
                }
            }

            /// <summary>
            /// Upload file.
            /// </summary>
            /// <param name="path">source file path e.g. c:/file.ext</param>
            /// <param name="newdest">destination file path on Cloud Storage</param>
            /// <param name="versionID">source file's version</param>
            /// <param name="storage">user's source storage name</param>
            /// <param name="destStorage">user's destination storage name</param>
            public void UploadFile(string path, string newdest, string destStorage = "", int versionID = 0, string storage = "")
            {
                UploadCopyFile(path, newdest, destStorage, versionID, storage);
            }

            /// <summary>
            /// Copy a specific file.
            /// </summary>
            /// <param name="path">source file path on Cloud Storage e.g. /file.ext</param>
            /// <param name="newdest">destination file path on Cloud Storage</param>
            /// <param name="versionID">source file's version</param>
            /// <param name="storage">user's source storage name</param>
            /// <param name="destStorage">user's destination storage name</param>
            public void CopyFile(string path, string newdest, string destStorage = "", int versionID = 0, string storage = "")
            {
                UploadCopyFile(path, newdest, destStorage, versionID, storage);
            }

            /// <summary>
            /// Download a specific file.
            /// </summary>
            /// <param name="path">file path to download e.g. /file.ext</param>
            /// <param name="versionID">file's version</param>
            /// <param name="destinationFilePath">Local destination path to save the downloaded file</param>            
            /// <param name="storage">user's storage name</param>
            public void DownloadFile(string path, string destinationPath, int versionID = 0, string storage = "")
            {
                // GET 	storage/file/{path}?appSID={appSID}&versionID={versionID}&storage={storage}

                string apiUrl = string.Format(@"storage/file/{0}?storage={1}", path, storage);
                if (!string.IsNullOrEmpty(storage))
                {
                    apiUrl = apiUrl + string.Format("&versionID={0}", versionID);
                }

                if (!string.IsNullOrEmpty(destinationPath) && Directory.Exists(Path.GetDirectoryName(destinationPath)))
                {
                    using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
                    using (Stream file = System.IO.File.OpenWrite(destinationPath))
                    {
                        ServiceController.CopyStream(responseStream, file);
                    }
                }
            }

            /// <summary>
            /// Remove a specific file.
            /// </summary>
            /// <param name="path">file path e.g. /file.ext</param>
            /// <param name="versionID">file's version</param>
            /// <param name="storage">user's storage name</param>
            public void RemoveFile(string path, int versionID = 0, string storage = "")
            {
                // DELETE 	storage/file/{path}?appSID={appSID}&versionID={versionID}&storage={storage}

                string apiUrl = string.Format(@"storage/file/{0}?storage={1}", path, storage);

                if (!string.IsNullOrEmpty(storage))
                {
                    apiUrl = apiUrl + string.Format("&versionID={0}", versionID);
                }

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Move a specific file.
            /// </summary>
            /// <param name="src">source file path e.g. /file.ext</param>
            /// <param name="dest">destination file path</param>
            /// <param name="versionID">source file's version</param>
            /// <param name="storage">user's source storage</param>
            /// <param name="destStorage">user's destination storage name</param>
            public void MoveFile(string src, string dest, string destStorage = "", int versionID = 0, string storage = "")
            {
                // POST  storage/file/{src}?dest={dest}&appSID={appSID}&versionID={versionID}&storage={storage}&destStorage={destStorage}

                string apiUrl = string.Format(@"storage/file/{0}?dest={1}&storage={2}&destStorage={3}", src, dest, storage, destStorage);

                if (!string.IsNullOrEmpty(storage))
                {
                    apiUrl = apiUrl + string.Format("&versionID={0}", versionID);
                }

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Check if a specific file or folder exists.
            /// </summary>
            /// <param name="path">file or folder path e.g. /file.ext</param>
            /// <param name="versionID">file's version</param>
            /// <param name="storage">user's storage nam</param>
            public FileExistResponse CheckFileExistance(string path, int versionID = 0, string storage = "")
            {
                // GET 	storage/exist/{path}?versionID={versionID}&storage={storage}

                string apiUrl = string.Format(@"storage/exist/{0}?storage={1}", path, storage);

                if (!string.IsNullOrEmpty(storage))
                {
                    apiUrl = apiUrl + string.Format("&versionID={0}", versionID);
                }

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                FileExistResponse fileExistResponse = jObject.ToObject<FileExistResponse>();
                return fileExistResponse;
            }

            /// <summary>
            /// Check the disk usage of the current account.
            /// </summary>
            /// <param name="storage">user's storage name.</param>
            public DiscUsageResponse CheckDiskUsageOfCurrentAccount(string storage)
            {
                // GET 	storage/disc?appSID={appSID}&storage={storage}

                string apiUrl = string.Format(@"storage/disc?storage={0}", storage);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                DiscUsageResponse discUsageResponse = jObject.ToObject<DiscUsageResponse>();
                return discUsageResponse;
            }

            /// <summary>
            /// Get the file's versions list.
            /// </summary>
            /// <param name="path">file path e.g. /file.ext or /Folder1/file.ext</param>
            /// <param name="storage">user's storage name.</param>
            public FileVersionResponse GetFileVersionsList(string path, string storage)
            {
                // GET 	storage/version/{path}?appSID={appSID}&storage={storage}

                string apiUrl = string.Format(@"storage/version/{0}?storage={1}", path, storage);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                FileVersionResponse fileVersionResponse = jObject.ToObject<FileVersionResponse>();
                return fileVersionResponse;
            }
        }

        public class Folderlass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal Folderlass(string appSid, string appKey)
            {
                AppSid = appSid;
                AppKey = appKey;
            }

            /// <summary>
            /// Get the file listing of root folder.
            /// </summary>
            /// <param name="storage">user's storage name.	</param>
            public List<FileResponse> GetFilesListOfRootFolder(string storage = "")
            {
                // GET 	storage/folder?appSID={appSID}&storage={storage}

                string apiUrl = string.Format(@"storage/folder?storage={0}", storage);
                
                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                FolderResponseRootObject folderResponseRootObject = jObject.ToObject<FolderResponseRootObject>();
                if (folderResponseRootObject != null)
                    return folderResponseRootObject.Files;
                return null;
            }

            /// <summary>
            /// Get the file listing of a specific folder.
            /// </summary>
            /// <param name="path">start with name of storage e.g. root folder '/'or some folder '/folder1/..</param>
            /// <param name="storage">user's storage name.	</param>
            public List<FileResponse> GetFilesList(string path, string storage = "")
            {
                // GET 	storage/folder/{path}?appSID={appSID}&storage={storage}

                string apiUrl = string.Format(@"storage/folder/{0}?storage={1}", path, storage);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                FolderResponseRootObject folderResponseRootObject = jObject.ToObject<FolderResponseRootObject>();
                if (folderResponseRootObject != null)
                    return folderResponseRootObject.Files;
                return null;
            }

            /// <summary>
            /// Create or copy a folder.
            /// </summary>
            /// <param name="path">source folder path e.g. /Folder1</param>
            /// <param name="newdest">destination folder path e.g. /Folder2</param>
            /// <param name="storage">user's source storage name</param>
            /// <param name="destStorage">user's destination storage name.</param>
            private void CreateOrCopyFolder(string path, string newdest, string storage = "", string destStorage = "")
            {
                // PUT 	storage/folder/{path}?appSID={appSID}&storage={storage}&newdest={newdest}&destStorage={destStorage}

                string apiUrl = string.Format(@"storage/folder/{0}?storage={1}", path, storage);

                if (!string.IsNullOrEmpty(newdest)) apiUrl += string.Format("&newdest={0}", newdest);
                if (!string.IsNullOrEmpty(destStorage)) apiUrl += string.Format("&destStorage={0}", destStorage);

                ServiceController.Put(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Create a folder.
            /// </summary>
            /// <param name="path">source folder path e.g. /Folder1</param>
            /// <param name="storage">user's source storage name</param>
            public void CreateFolder(string path, string storage = "")
            {
                CreateOrCopyFolder(path, string.Empty, storage, string.Empty);
            }

            /// <summary>
            /// Copy a folder.
            /// </summary>
            /// <param name="path">source folder path e.g. /Folder1</param>
            /// <param name="newdest">destination folder path e.g. /Folder2</param>
            /// <param name="storage">user's source storage name</param>
            /// <param name="destStorage">user's destination storage name.</param>
            public void CopyFolder(string path, string newdest, string storage = "", string destStorage = "")
            {
                CreateOrCopyFolder(path, newdest, storage, destStorage);
            }

            /// <summary>
            /// Remove a specific folder.
            /// </summary>
            /// <param name="path">folder path e.g. /Folder1</param>
            /// <param name="recursive">is subfolders and files must be deleted for specified path</param>
            /// <param name="storage">user's storage name</param>
            public void RemoveFolder(string path, bool recursive, string storage = "")
            {
                // DELETE  storage/folder/{path}?appSID={appSID}&storage={storage}&recursive={recursive}

                string apiUrl = string.Format(@"storage/folder/{0}?storage={1}&recursive={2}", path, storage, recursive);
                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Move a specific folder.
            /// </summary>
            /// <param name="src">source folder path e.g. /Folder1</param>
            /// <param name="dest">destination folder path e.g. /Folder2</param>
            /// <param name="storage">user's source storage name</param>
            /// <param name="destStorage">user's destination storage name.</param>
            public void MoveFolder(string src, string dest, string storage = "", string destStorage = "")
            {
                // POST  storage/folder/{src}?dest={dest}&appSID={appSID}&storage={storage}&destStorage={destStorage}

                string apiUrl = string.Format(@"storage/folder/{0}?dest={1}&storage={2}&destStorage={3}", src, dest, storage, destStorage);
                ServiceController.Post(apiUrl, AppSid, AppKey);
            }
        }
    }

    public class FileExist
    {
        public bool IsExist { get; set; }
        public bool IsFolder { get; set; }
    }

    public class FileExistResponse
    {
        public FileExist FileExist { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class DiscUsage
    {
        public long UsedSize { get; set; }
        public long TotalSize { get; set; }
    }

    public class DiscUsageResponse
    {
        public DiscUsage DiscUsage { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class FileVersion
    {
        public string VersionId { get; set; }
        public bool IsLatest { get; set; }
        public string Name { get; set; }
        public bool IsFolder { get; set; }
        public string ModifiedDate { get; set; }
        public int Size { get; set; }
        public string Path { get; set; }
    }

    public class FileVersionResponse
    {
        public List<FileVersion> FileVersions { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class FileResponse
    {
        public bool IsFolder { get; set; }
        public DateTime ModifiedDate { get; set; }
        public string Name { get; set; }
        public int Size { get; set; }
    }

    public class FolderResponseRootObject
    {
        public int Code { get; set; }
        public string Status { get; set; }
        public List<FileResponse> Files { get; set; }
    }
}
