using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Aspose.Cloud;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Aspose.CloudTests
{
    [TestClass()]
    public class SDKServiceTests
    {
        SDKService sdkService = new SDKService(Utils.AppSid, Utils.AppKey);

        [TestMethod()]
        public void SDK_Tests()
        {
            SDKMetaDataResponse sdkMetaDataResponse = sdkService.GetSDKMetaData();
        }
    }
}
