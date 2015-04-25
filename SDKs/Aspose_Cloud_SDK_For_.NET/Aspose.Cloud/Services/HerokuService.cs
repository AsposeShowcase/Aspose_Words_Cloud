using System;
using System.Collections.Generic;
using System.Text;
using Aspose.Cloud.Common;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

namespace Aspose.Cloud
{
    public class HerokuService : BaseService
    {
        public HerokuService(string appSid, string appKey)
            : base(appSid, appKey)
        {
            AppSid = appSid;
            AppKey = appKey;
        }

        /// <summary>
        /// Create Heroku user.
        /// </summary>
        /// <param name="email">Heroku user's email</param>
        /// <param name="fullName">Heroku user's full name</param>
        public void CreateHerokuUser(string email, string fullName = "")
        {
            // PUT 	heroku/user?appSID={appSID}&fullName={fullName}&email={email}

            string apiUrl = string.Format(@"heroku/user?fullName={0}&email={1}", fullName, email);
            ServiceController.Put(apiUrl, AppSid, AppKey);
        }

        /// <summary>
        /// Subscribe Heroku user to Startup Plan For Heroku.
        /// </summary>
        /// <param name="herokuAppSID">Heroku user AppSID.</param>
        public void SubscribeHerokuUserToStartupPlan(string herokuAppSID)
        {
            // PUT 	heroku/subscription?appSID={appSID}&herokuAppSID={herokuAppSID}

            string apiUrl = string.Format(@"heroku/subscription?herokuAppSID={0}", herokuAppSID);
            ServiceController.Put(apiUrl, AppSid, AppKey);
        }

        /// <summary>
        /// Unsubscribe Heroku user
        /// </summary>
        /// <param name="herokuAppSID">Heroku user AppSID</param>
        public void UnsubscribeHerokuUser(string herokuAppSID)
        {
            // DELETE 	heroku/subscription?appSID={appSID}&herokuAppSID={herokuAppSID} 

            string apiUrl = string.Format(@"heroku/subscription?herokuAppSID={0}", herokuAppSID);
            ServiceController.Delete(apiUrl, AppSid, AppKey);
        }
    }
}
