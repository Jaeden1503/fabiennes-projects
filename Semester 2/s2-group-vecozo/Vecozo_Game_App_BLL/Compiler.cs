using System;
using System.Collections.Generic;
using System.Text;
using System.CodeDom.Compiler;
using System.Diagnostics;
using Microsoft.CSharp;
using System.Drawing;
using System.IO;
using System.Linq;
using Microsoft.CodeAnalysis;
using Microsoft.CodeAnalysis.CSharp;
using Microsoft.CodeAnalysis.Text;
using System.Runtime.CompilerServices;

namespace Vecozo_Game_App_BLL
{
    public class Compiler
    {
        public CallBack Compile(string sourcecode, string FileName)
        {
            CallBack callBack = new CallBack();

            using (var peStream = new FileStream(FileName, FileMode.Create))
            {
                var result = GenerateCode(sourcecode, FileName).Emit(peStream);

                if (!result.Success)
                {
                    Console.WriteLine("Compilation done with error.");

                    var failures = result.Diagnostics.Where(diagnostic => diagnostic.IsWarningAsError || diagnostic.Severity == DiagnosticSeverity.Error);

                    foreach (var diagnostic in failures)
                    {
                        //StackFrame callStack = new StackFrame(true);
                       
                        callBack.IsError = true;
                        callBack.Messages.Add(TraceMessage(diagnostic)); //breakpoint: wanneer je bv een ; vergeet
                        Console.Error.WriteLine("{0}: {1}", diagnostic.Location, diagnostic.Id, diagnostic.GetMessage());
                    }
                }
                return callBack;
                //Console.WriteLine("Compilation done without any error.");
            }
        }

        public string TraceMessage(Diagnostic diagnostic)
        {
            return "Er is een error gevonden: " + diagnostic.GetMessage() + "\n";
        }

        private static CSharpCompilation GenerateCode(string sourceCode, string FileName)
        {
            var assemblyPath = Path.GetDirectoryName(typeof(object).Assembly.Location);
            var codeString = SourceText.From(sourceCode);
            var options = CSharpParseOptions.Default.WithLanguageVersion(LanguageVersion.CSharp7_3);

            var parsedSyntaxTree = SyntaxFactory.ParseSyntaxTree(codeString, options);

            var references = new MetadataReference[]
            {
                //MetadataReference.CreateFromFile(typeof(object).Assembly.Location),
                //MetadataReference.CreateFromFile(typeof(Console).Assembly.Location),
                //MetadataReference.CreateFromFile(typeof(System.Runtime.AssemblyTargetedPatchBandAttribute).Assembly.Location),
                //MetadataReference.CreateFromFile(typeof(Microsoft.CSharp.RuntimeBinder.CSharpArgumentInfo).Assembly.Location),
                MetadataReference.CreateFromFile(Path.Combine(assemblyPath, "System.Private.CoreLib.dll")),
                MetadataReference.CreateFromFile(Path.Combine(assemblyPath, "System.dll")),
                MetadataReference.CreateFromFile(Path.Combine(assemblyPath, "System.Core.dll")),
                MetadataReference.CreateFromFile(Path.Combine(assemblyPath, "System.Runtime.dll")),
                MetadataReference.CreateFromFile(Path.Combine(assemblyPath, "System.Console.dll")),
                MetadataReference.CreateFromFile(Path.Combine(assemblyPath, "System.Linq.dll"))
            };

            return CSharpCompilation.Create(FileName,
                new[] { parsedSyntaxTree },
                references: references,
                options: new CSharpCompilationOptions(OutputKind.ConsoleApplication,
                    optimizationLevel: OptimizationLevel.Release,
                    assemblyIdentityComparer: DesktopAssemblyIdentityComparer.Default));
        }
    }
}
