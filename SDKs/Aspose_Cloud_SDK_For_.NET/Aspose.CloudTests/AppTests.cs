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
    public class AppTests
    {
        App app = new App(Utils.AppSid, Utils.AppKey);

        [TestMethod()]
        public void App_Tests()
        {
            try
            {
                List<App> appList = app.GetListOfApplications();

                string appName = "testApp" + DateTime.Now.ToShortDateString().Replace(":", string.Empty).Replace("/", string.Empty).Replace(" ", string.Empty);

                App newApp = new App(Utils.AppSid, Utils.AppKey);
                newApp.Name = appName;
                newApp.Created = DateTime.Now;

                app.CreateNewApplication(newApp);

                App createdApp = app.GetApplicationByName(appName);

                string updatedAppName = appName + "-Updated";

                App existingApp = app.GetApplicationByName(appName);
                existingApp.Name = updatedAppName;
                app.UpdateApplicationName(appName, existingApp);

                appName = updatedAppName;

                ApplicationUsageResponse applicationUsageResponse = app.GetApplicationUsageByPeriod(appName, DateTime.Now.AddDays(-30), DateTime.Now);

                app.DeleteApplication(appName);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }
    }
}
