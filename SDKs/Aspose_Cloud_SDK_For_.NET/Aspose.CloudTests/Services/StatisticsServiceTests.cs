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
    public class StatisticsServiceTests
    {
        StatisticsService statisticsService = new StatisticsService(Utils.AppSid, Utils.AppKey);

        [TestMethod()]
        public void Statistics_Tests()
        {
            try
            {
                //statisticsService.GetServiceStatisticsInHtmlForm(Local_Output_Path + "\\ServiceStatisticsInHtmlForm.html");
                //statisticsService.GetStatisticsForSpecificDatesByHours(DateTime.Now.AddMonths(-1), DateTime.Now, Utils.Local_Output_Path + "\\StatisticsForSpecificDatesByHours.html");
                //statisticsService.GetStatisticsForLast30DaysByHours(Local_Output_Path + "\\StatisticsForLast30DaysByHours.html");
                //statisticsService.GetServiceStatisticsInHTMLFormLikeGroupDocs(Local_Output_Path + "\\ServiceStatisticsInHTMLFormLikeGroupDocs.html");
                //statisticsService.GetUsersWithPlansStatistics(Local_Output_Path + "\\UsersWithPlansStatistics.html");
                //statisticsService.GetLastErrorsStatistics(Local_Output_Path + "\\LastErrorsStatistics.html");
                //statisticsService.GetPaidClientsStatistics(Local_Output_Path + "\\PaidClientsStatistics.html");
                //statisticsService.GetFreeUsersStatistics(Local_Output_Path + "\\FreeUsersStatistics.html");
                //statisticsService.GetAPICallsStatistics(Local_Output_Path + "\\APICallsStatistics.html");
                //statisticsService.GetClientsWithOverDraftStatistics(Local_Output_Path + "\\ClientsWithOverDraftStatistics.html");
                //statisticsService.GetCurrentUsersStatistics(Local_Output_Path + "\\CurrentUsersStatistics.html");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }
    }
}
