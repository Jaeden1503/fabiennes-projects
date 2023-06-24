using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.CompilerServices;
using System.IO;
using System.Diagnostics;

namespace Vecozo_Game_App_BLL
{
    public class Runner
    {
        public CallBack RunCode(string FileToRun)
        {
            Process process = new Process();
            process.StartInfo.CreateNoWindow = true;
            process.StartInfo.RedirectStandardOutput = true;
            process.StartInfo.RedirectStandardError = true;

            //process.StartInfo.FileName = FileToRun; // De File Locatie van de code die moet draaien
            process.StartInfo.FileName = @"C:\Program Files\dotnet\dotnet.exe"; // start de cmd
            process.StartInfo.Arguments = $"{FileToRun}";
            process.Start();

            StreamReader Output = process.StandardOutput;
            StreamReader Error = process.StandardError;
            CallBack callBack = new CallBack();
            if (!Error.EndOfStream)
            {
                callBack.IsError = true;
                do
                {
                    callBack.Messages.Add(Error.ReadLine());
                } while (!Error.EndOfStream);
            }
            else
            {
                do
                {
                    callBack.Messages.Add(Output.ReadLine()); //breakpoint: wanneer je geen fouten hebt en add bv 'Hello world' in de message
                } while (!Output.EndOfStream);
            }
            process.WaitForExit();
            return callBack;
        }

        public void CreateConfig(string applicationName)
        {
            File.Copy("template.runtimeconfig.json", $"{applicationName}.runtimeconfig.json", true);
        }

        public void DeleteCompilerFiles(string applicationName)
        {
            File.Delete(@""+applicationName+".exe");
            File.Delete(@""+applicationName+".runtimeconfig.json");
        }
    }


}