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
    public class HerokuServiceTests
    {
        HerokuService herokuService = new HerokuService(Utils.AppSid, Utils.AppKey);

        [TestMethod()]
        public void Heroku_Tests()
        {
            try
            {
                //herokuService.CreateHerokuUser("test_heroku@aspose.com", "Test Heroku");
                //herokuService.SubscribeHerokuUserToStartupPlan("herokuAppSID");
                //herokuService.UnsubscribeHerokuUser("herokuAppSID");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }
    }
}
