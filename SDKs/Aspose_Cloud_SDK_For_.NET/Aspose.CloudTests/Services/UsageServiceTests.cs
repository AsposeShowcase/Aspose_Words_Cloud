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
    public class UsageServiceTests
    {
        UsageService usageService = new UsageService(Utils.AppSid, Utils.AppKey);

        [TestMethod()]
        public void Usage_Tests()
        {
            try
            {
                usageService.GetUsageLogsForSomePeriod(DateTime.Now.AddMonths(-1), DateTime.Now, Utils.Local_Output_Path + "UsageLogsForSomePeriod.xlsx");
                usageService.GetUsageForCurrentSubscriptionPeriod(Utils.Local_Output_Path + "UsageForCurrentSubscriptionPeriod.xlsx");
                usageService.GetUsageForPreviousSubscriptionPeriod(Utils.Local_Output_Path + "UsageForPreviousSubscriptionPeriod.xlsx");
                usageService.GetSummaryUsageForCurrentSubscriptionPeriod(Utils.Local_Output_Path + "SummaryUsageForCurrentSubscriptionPeriod.xlsx");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }
    }
}
