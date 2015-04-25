using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Aspose.CloudTests
{
    public class Utils
    {
        public static string AppSid = "Put your AppSid here";
        public static string AppKey = "Put your AppKey here";

        private static string RootAppPath = System.IO.Path.GetDirectoryName(System.Reflection.Assembly.GetExecutingAssembly().CodeBase).Replace("bin\\Debug", string.Empty).Replace("bin\\Release", string.Empty).Replace("bin", string.Empty).Replace("file:\\", string.Empty).Replace("\\Aspose.CloudTestWebApp", string.Empty).Replace("\\Aspose.CloudTests", string.Empty);

        public static string Local_Input_Path = RootAppPath + "Aspose.CloudTests\\TestData\\";
        public static string Local_Output_Path = Local_Input_Path + "Output\\";
        
        public static string CloudStorage_Input_Folder = "SDKTest";
        public static string CloudStorage_Output_Folder = "SDKTestOutput";
    }
}
