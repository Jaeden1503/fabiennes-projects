#pragma checksum "C:\Users\fabie\Documents\Tilburg HBO-ICT\Semester 2\Individueel project webapp\s2-individueel-project-webapp\Receptenzoeker\Receptenzoeker\Views\User\LogIn.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "e8ab9286e85c9e3ae1481024814973ec62862ddb"
// <auto-generated/>
#pragma warning disable 1591
[assembly: global::Microsoft.AspNetCore.Razor.Hosting.RazorCompiledItemAttribute(typeof(AspNetCore.Views_User_LogIn), @"mvc.1.0.view", @"/Views/User/LogIn.cshtml")]
namespace AspNetCore
{
    #line hidden
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Threading.Tasks;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.AspNetCore.Mvc.Rendering;
    using Microsoft.AspNetCore.Mvc.ViewFeatures;
#nullable restore
#line 1 "C:\Users\fabie\Documents\Tilburg HBO-ICT\Semester 2\Individueel project webapp\s2-individueel-project-webapp\Receptenzoeker\Receptenzoeker\Views\_ViewImports.cshtml"
using Receptenzoeker;

#line default
#line hidden
#nullable disable
#nullable restore
#line 2 "C:\Users\fabie\Documents\Tilburg HBO-ICT\Semester 2\Individueel project webapp\s2-individueel-project-webapp\Receptenzoeker\Receptenzoeker\Views\_ViewImports.cshtml"
using Receptenzoeker.Models;

#line default
#line hidden
#nullable disable
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"e8ab9286e85c9e3ae1481024814973ec62862ddb", @"/Views/User/LogIn.cshtml")]
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"bd15e8b1fe975b9e7437610c4f5aa58cf7f96067", @"/Views/_ViewImports.cshtml")]
    public class Views_User_LogIn : global::Microsoft.AspNetCore.Mvc.Razor.RazorPage<UserViewModel>
    {
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_0 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("href", new global::Microsoft.AspNetCore.Html.HtmlString("~/css/login.css"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_1 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("rel", new global::Microsoft.AspNetCore.Html.HtmlString("stylesheet"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_2 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("id", new global::Microsoft.AspNetCore.Html.HtmlString("bootstrap-css"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_3 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("placeholder", new global::Microsoft.AspNetCore.Html.HtmlString("naam"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_4 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("value", "", global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_5 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("class", new global::Microsoft.AspNetCore.Html.HtmlString("text-danger"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_6 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("id", new global::Microsoft.AspNetCore.Html.HtmlString("password"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_7 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("placeholder", new global::Microsoft.AspNetCore.Html.HtmlString("wachtwoord"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_8 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("type", "password", global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_9 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("asp-action", "LogIn", global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_10 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("type", new global::Microsoft.AspNetCore.Html.HtmlString("submit"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_11 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("value", new global::Microsoft.AspNetCore.Html.HtmlString("inloggen"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_12 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("asp-controller", "User", global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_13 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("enctype", new global::Microsoft.AspNetCore.Html.HtmlString("multipart/form-data"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        #line hidden
        #pragma warning disable 0649
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperExecutionContext __tagHelperExecutionContext;
        #pragma warning restore 0649
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperRunner __tagHelperRunner = new global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperRunner();
        #pragma warning disable 0169
        private string __tagHelperStringValueBuffer;
        #pragma warning restore 0169
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperScopeManager __backed__tagHelperScopeManager = null;
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperScopeManager __tagHelperScopeManager
        {
            get
            {
                if (__backed__tagHelperScopeManager == null)
                {
                    __backed__tagHelperScopeManager = new global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperScopeManager(StartTagHelperWritingScope, EndTagHelperWritingScope);
                }
                return __backed__tagHelperScopeManager;
            }
        }
        private global::Microsoft.AspNetCore.Mvc.Razor.TagHelpers.UrlResolutionTagHelper __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper;
        private global::Microsoft.AspNetCore.Mvc.TagHelpers.FormTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper;
        private global::Microsoft.AspNetCore.Mvc.TagHelpers.RenderAtEndOfFormTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper;
        private global::Microsoft.AspNetCore.Mvc.TagHelpers.InputTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_InputTagHelper;
        private global::Microsoft.AspNetCore.Mvc.TagHelpers.ValidationMessageTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_ValidationMessageTagHelper;
        private global::Microsoft.AspNetCore.Mvc.TagHelpers.FormActionTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_FormActionTagHelper;
        #pragma warning disable 1998
        public async override global::System.Threading.Tasks.Task ExecuteAsync()
        {
#nullable restore
#line 1 "C:\Users\fabie\Documents\Tilburg HBO-ICT\Semester 2\Individueel project webapp\s2-individueel-project-webapp\Receptenzoeker\Receptenzoeker\Views\User\LogIn.cshtml"
  
    ViewData["Title"] = "Login";

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n");
            WriteLiteral("\r\n");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("link", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagOnly, "e8ab9286e85c9e3ae1481024814973ec62862ddb9080", async() => {
            }
            );
            __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.Razor.TagHelpers.UrlResolutionTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper);
            __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_0);
            __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_1);
            __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_2);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            WriteLiteral(@"
<script src=""//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js""></script>
<script src=""//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js""></script>
<!------ Include the above in your HEAD tag ---------->

<div class=""wrapper fadeInDown"">
    <div id=""formContent"">
        <div ");
            WriteLiteral(@">
            <img src=""data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBw0NDQ0NDQ0PDQ0NDw4PDQ0NDw8NDQ0PFREWFhURFRUYHSggGBolHRUVIT0hJSorLi4vFx8zRDMtNygtLi0BCgoKBQUFDgUFDisZExkrKysrKysrKysrKysrKysrKysrKysrKysrKysrKysrKysrKysrKysrKysrKysrKysrK//AABEIAOEA4QMBIgACEQEDEQH/xAAcAAEAAwADAQEAAAAAAAAAAAAAAQcIBAUGAwL/xABFEAACAgECAgYFCAcFCQEAAAAAAQIDBAURITEGBxJBUWETcYGRlAgUFyIyVKHSFSNCQ2JygjNSorHBNURTdJOjsrPhNP/EABQBAQAAAAAAAAAAAAAAAAAAAAD/xAAUEQEAAAAAAAAAAAAAAAAAAAAA/9oADAMBAAIRAxEAPwC8QAAIAAAAAAABJAAkEAASQAJIAAEkAAAABJAAAACSAAAAAkEEgCCSAAAAAAAAAAPhnZtGNW7ci6uiqP2rLpxrgvW5PY8PqvXFoGM5RjkWZUo81i1Skm/KUuzF+xge/BUz6+9K3/8Ax523j2cff3ekO20vrn0HIaU7rsVvhtk0y2T85Q7SXtAsMHF03UsbLrVuLfVkVv8AbpsjZHfw3T5nKAAAAAAAAAAAAAAAAAAAAAAJIAAAAAAABWfWX1sUaU5YmFGGVnrdTbe9GK/49vtT/hW23e1yc9c3T96Tjxw8Se2flRb7a54tPL0n8z2aXqb7lvmqc3JuUm5Sk222922+bbA7LXukGdqVruzcmzInvwU5fq6/KEF9WK8kkdWAAAAHP0fWcvAtV+HkWY9q/aqk49peElykvJ7ovrq264as6VeFqnYx8uTUasiP1cfIl3Rkv3c37m/DgjOwA3ECo+o/rAnmw/RWdY55VMXLGum95ZFMVxhJ984+PevU27cAAAAAAAAAAAAAAAAAAACSAAAA");
            WriteLiteral(@"AHxzcqvHptvtl2Kqa522SfKMIxcpP3Jn2PCdduovG6P5ai9pZEqsdPynNOS9sYyXtAzb0p1u3U87Jzrm+1fY5Ri3v6OvlCC8lFJew6oAAAAAAAAADl6TqN2Hk0ZVEuxdj2Rsrlx23i99n4p8mu9NmydC1SvOxMbMq/s8mqFsV3x7S4xfmnuvYYrNKfJ81F3aJKmXPEyra4/yTUbF+M5+4CzgAAAAAAAAAAAAAAACSABJBJAAAACqflHb/ofF232+f17/APQu5lrHguvDTnkaBlOK3ljTpyEl4Rn2ZP2RlJ+wDLQAAAAAAAAAAF+/Jq3+aal/d9PTt6+xLf8A0KCNJfJ60906LO+X+95Vs4/yQjGtf4ozAtAAAAAAAAAAAAAAAAAAASQSQAAAA4+oYdeTRdj2x7VV9c6rI+MJxcWvczkADF/SLR7dOzcnCvX6zHslDfbZTjzjNLwlFp+0600j12dAJanQtQw4OWdiw7M6or62VQnvsvGcd214pteBm4AAAAAAAADk6bg25V9ONRBzuvsjXXFd8pPZezzNkdHtJr0/CxcKrjDGqhWntt2ml9ab82937Sreozq/ljJavnV9m+yDWFTNfWprkuNz35SkuC8E348LjAAAAAAAAAAAAAAAAAAAACSAAAAAAAVV1l9UVWoynm6b2MfNlvK2l7Roypc3Lh9ib8eT79uLLVAGLNZ0bLwLnRmY9mPav2bI7brxi+Ul5rdHANr6npeLmVunLx6smt/sXVxsin4rfk/NHgdU6k9CvblVHIxG3vtRd2oe6xS/ADMwNBPqBwN/9oZW3h2Kt/fsdppnUfolLUrXlZf8N1qhD3Vxi/xAznpunZGXbGjFpsvun9muqLnJ+fDkvPki9urXqcjiyrzdXULb47SqwltOqmXjY+U5LwXBefdaej6Jh4EPR4eLTjQf2lTXGDm/GTXGT82c8AAAAAAABvbj4AeI1jrW0PCybsW7IsdtE3Xb6OmycYzXOO+3HZ8Dh/TR0f8A+Pf8PYZu13L+cZuX");
            WriteLiteral(@"kb7+nyL7d/Ht2Sl/qcEDXXRTp/pWsXWY+FdOV1dfpXCyqdbcFJRck2tns5R956gy91E5voekGPF8sirIp/7bmvxrRqEAAAAAAAACSCSAAAAAAAAAABxcnU8Wnf02TTVtz9JbCG3vYHKB0dnTLRovaWq4Cfg8uj8x9sfpRpdr2r1LCsfhDKok/wAJAdsD503wsW9c4zXjCSkvej6AAAAKU65usvLw8yvT9LyPQzx9p5dsYwm3ZJJxqXaTWyT3fm0u5lk9PulFejadfmS2dm3o8at/vb5J9mPqWzk/KLMiZWRO6yy62TnZbOVlk5c5zk25Sfm22Bc3Rjr5sj2a9VxFZHk8jE+rP1uqT2fsa9R8+nPXdZb28fR6/RVSTjLLvjvbLdcfRwfCK83u/JFMAAAAOZpGp34WRVl40/R30S7dc9oy2e23J8HwbLv6M9e+O6JrVcecMiuO8ZYkVKvI8uzJ/Ul7duD5cihABa3Sfrx1LJ7Ven1QwK3yse1+S1v4yXZj7E35lk9TXTeesYU6cqfbz8NpWyaUXdVJvsWbLhvwcXt4J95mA77oR0kt0jUcfNr3cYS7N9a/e0Sa7cPXtxXmkwNiA+GDmVZFNWRTNTquhCyua5ShJbp/ifcAAAAJIAAHkem3WHpuirs3zd2U1vHEo2lbx5OfdBeb4+CYHrjz3SLptpOmbrMzaoWL9xBu2/y/Vw3a9b2Rnvpb1s6vqXahC35jjP8Ac4rcZtfx2/al7Nk/A8G2223xb4tvmwL41vr9ojvHT8CdnPa3Kmqo+vsQ3bXtR4bVeuLX8nhHJrxYvnHFpjH/ABT7Ul7yvwB2ef0h1DJ3+cZ2VenzVt9s4+5vY6wAAAAPpTdOuSlXOUJLlKEnGS9qO/03p5reL/Y6nlJd0bLHfBf02bo84ALW0jr21WrZZVGNlxXNpSx7X/VHeP8AhLd6ven1GvQvdONdjzxvR+lVnYlXvPtbKE0+P2XzS7jJhof5N9CWl5tvfPNcH6oU1tf+bA9R1odBv07iQrhd");
            WriteLiteral(@"6HJxpTnjuW7pnKSScLEu57L6y4rwfIzBrmjZWnZE8XMplTdDnGXKUe6UXylF+KNpFU/KMqh+iMax1wlYs2uuNjinOEJU2yklLmk3CPD1AZzAOx6N4sL9QwaLP7O7Kxq7N+XYlbGMvwbAtjqy6na8qivP1ft9i5KdGHCTrcq3ynbJcVvzUVtw249xZF/Vd0fsr9G9NrittlKE7YWLz7Slv7z2EUkkktkuCS5JEgZi60+rOeitZWNOV+n2T7O8l+txpvlCbXBp90uHh4b10bB6xMSu/RNUrtScVh32Jy5RnXBzhL2Sin7DHwA9L0I6E52t3+jxodimDXp8qxP0NK9f7UtuUV+C4nmjX/VzRXXomlKuuFalhY05KEVFSnKuMpTe3NtttvvbA53RbQ69LwcbAqnOyGPFxU7HvKTcnKT8uMnw7uR2oAAAASQSee6f9IP0VpWZmx29JXX2aE+Kd02ow3Xek2n6kwPC9bvWi9Pc9N02SebttkZHCSxU19mPjZxXq9fLPV1s7JSnOUpzm3Kc5tylKTe7k2+LYvunZOdlknOc5SnOcm3KUm93JvvbZ+AAAAAAAAAAAAAAAaL+ThNfofLj3rULG/U8elL/ACZnQvD5NWo8dSw2+LVORBd/DtQm/wAawLm1fVcbBosycu6FFFa3lZN7LySXNt9yXFmbetLrMs1p/NaIeh06uxTgppenumk0rJP9lcXtFePFvutbr16N26hpSvocpWafKV7qXFWVOO03t/eil2vUpLvMygD902yrnGcG4zhJShJc4yT3TR+ABrjq96a42t4cLITjHLrjFZeNulOuzbjJLvg+afs5po9UYkw8u7HsjbRbZTbH7NlM5V2R9UovdHf5HWDrttbqnqmV2Gtn2bHXJr+aOz/EC5OvLpzRjYdulY9kbMvKXYyOw0/m1HOSl/FJcOz4Nvhw3zqTKTk22222223u23zbZAAuzqr63YU10abqzUKq4wpxsxJKMIJdmMLku5LZdv397KTPri49l1ldNUXO22ca");
            WriteLiteral(@"64R+1OcmlGK822gNt1zjOKlGSlGSTjKLTjJPk01zR+jo+hOhy0zTMPBlY7J0V7Tm3v8AXlJzkl/CnJpeSR3gAkgASVf8oibWiVJcpZ1CfmvR2v8A0RaBVvyiv9i0/wDPU/8AqtAzcAAAAAAAAAAAAAAAAex6pdeWna3iWzfZpvbxr2+Shbsk35KSg/YeOAG4mk001unwafFNGUetjog9H1OyFcdsPJ3uxH3Ri39er+mT29Ti+8vfqj6XR1fTK/ST7WZiKNOUm/rS2W0LX/Ml71I9Rq+iYefGEMzGqyY1y7cI3QU1GXLdbgZE6PdGNR1OfYwcS3I2e0pxXZqg/CVktox597J6T9F8/SbvQ51EqnLjXNfWqtXjCa4P1c0bGpphXGMK4RrhFbRhCKjGK8ElyPhqen05dNlGRVC6qyLThZGM48uez7wMTg+uVRKqyyqX2q5yhL1xbT/yPkAPS9G+ger6pTZkYeJKymCe1k5QqjbJPZwrcmu0+fLhw578Dk9U+mQzNdwKba421duyyyE4qUJRhVKX1k+DW6XA1nCKilGKSikkkkkkl3JAYp1PTMnDtdOVRbj2rnC6EoS235rfmvNFtfJ96H+munrGRD9XQ3XhqS4Su2+vZ/SnsvOT8C8tT0vGzK3Tl49WRU/2LoRsjv4rfk/M/eBg041MKMeqFNNS7NdVcVGEVvvwS8237QOQAAAAAHhOuXo5m6rplWNg1K66OXXbKLnCpKCrsTe8mlzkj3YAy39DvSL7lD4nG/OR9DvSL7lD4nG/OalAGWvod6RfcofE435yfod6RfcofE435zUgAy19DvSL7lD4nG/OPod6RfcofE435zUoAy19DvSL7lD4nG/OPod6RfcofE435zUoAy19DvSL7lD4nG/OPod6RfcofE435zUoAy19DvSL7lD4nG/OT9DvSL7lD4nG/OakAGWvod6RfcofE435yfod6RfcofE435zUgAz10H6EdKtFzq8unCjKH2Mij51jKN9Lf1ofb4PvT7ml5o0J");
            WriteLiteral(@"XJuKbi4tpNxe28X4PZtb+okAAABnvpV1NavfqObfjfNpUX5F11Tlb2JKNk3LstbcNt9vYdV9COvf3cX4j/4aZAFO9UnVnqOk6jPNzvQKEceyuuNVnpJuc5R48uC2Uvei4gAAAAAACQQAJIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAJIJAEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAP/9k="" id=""icon"" alt=""User Icon"" width=""30"" height=""220"" />
        </div>

        ");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("form", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "e8ab9286e85c9e3ae1481024814973ec62862ddb16263", async() => {
                WriteLiteral("\r\n            <div>");
                __tagHelperExecutionContext = __tagHelperScopeManager.Begin("input", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagOnly, "e8ab9286e85c9e3ae1481024814973ec62862ddb16539", async() => {
                }
                );
                __Microsoft_AspNetCore_Mvc_TagHelpers_InputTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.InputTagHelper>();
                __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_InputTagHelper);
#nullable restore
#line 20 "C:\Users\fabie\Documents\Tilburg HBO-ICT\Semester 2\Individueel project webapp\s2-individueel-project-webapp\Receptenzoeker\Receptenzoeker\Views\User\LogIn.cshtml"
__Microsoft_AspNetCore_Mvc_TagHelpers_InputTagHelper.For = ModelExpressionProvider.CreateModelExpression(ViewData, __model => Model.UserName);

#line default
#line hidden
#nullable disable
                __tagHelperExecutionContext.AddTagHelperAttribute("asp-for", __Microsoft_AspNetCore_Mvc_TagHelpers_InputTagHelper.For, global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
                __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_3);
                __Microsoft_AspNetCore_Mvc_TagHelpers_InputTagHelper.Value = (string)__tagHelperAttribute_4.Value;
                __tagHelperExecutionContext.AddTagHelperAttribute(__tagHelperAttribute_4);
                await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
                if (!__tagHelperExecutionContext.Output.IsContentModified)
                {
                    await __tagHelperExecutionContext.SetOutputContentAsync();
                }
                Write(__tagHelperExecutionContext.Output);
                __tagHelperExecutionContext = __tagHelperScopeManager.End();
                WriteLiteral("</div>\r\n            <div>");
                __tagHelperExecutionContext = __tagHelperScopeManager.Begin("span", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "e8ab9286e85c9e3ae1481024814973ec62862ddb18427", async() => {
                }
                );
                __Microsoft_AspNetCore_Mvc_TagHelpers_ValidationMessageTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.ValidationMessageTagHelper>();
                __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_ValidationMessageTagHelper);
#nullable restore
#line 21 "C:\Users\fabie\Documents\Tilburg HBO-ICT\Semester 2\Individueel project webapp\s2-individueel-project-webapp\Receptenzoeker\Receptenzoeker\Views\User\LogIn.cshtml"
__Microsoft_AspNetCore_Mvc_TagHelpers_ValidationMessageTagHelper.For = ModelExpressionProvider.CreateModelExpression(ViewData, __model => __model.UserName);

#line default
#line hidden
#nullable disable
                __tagHelperExecutionContext.AddTagHelperAttribute("asp-validation-for", __Microsoft_AspNetCore_Mvc_TagHelpers_ValidationMessageTagHelper.For, global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
                __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_5);
                await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
                if (!__tagHelperExecutionContext.Output.IsContentModified)
                {
                    await __tagHelperExecutionContext.SetOutputContentAsync();
                }
                Write(__tagHelperExecutionContext.Output);
                __tagHelperExecutionContext = __tagHelperScopeManager.End();
                WriteLiteral("</div>\r\n            <div>");
                __tagHelperExecutionContext = __tagHelperScopeManager.Begin("input", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagOnly, "e8ab9286e85c9e3ae1481024814973ec62862ddb20184", async() => {
                }
                );
                __Microsoft_AspNetCore_Mvc_TagHelpers_InputTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.InputTagHelper>();
                __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_InputTagHelper);
#nullable restore
#line 22 "C:\Users\fabie\Documents\Tilburg HBO-ICT\Semester 2\Individueel project webapp\s2-individueel-project-webapp\Receptenzoeker\Receptenzoeker\Views\User\LogIn.cshtml"
__Microsoft_AspNetCore_Mvc_TagHelpers_InputTagHelper.For = ModelExpressionProvider.CreateModelExpression(ViewData, __model => Model.Password);

#line default
#line hidden
#nullable disable
                __tagHelperExecutionContext.AddTagHelperAttribute("asp-for", __Microsoft_AspNetCore_Mvc_TagHelpers_InputTagHelper.For, global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
                __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_6);
                __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_7);
                __Microsoft_AspNetCore_Mvc_TagHelpers_InputTagHelper.InputTypeName = (string)__tagHelperAttribute_8.Value;
                __tagHelperExecutionContext.AddTagHelperAttribute(__tagHelperAttribute_8);
                __Microsoft_AspNetCore_Mvc_TagHelpers_InputTagHelper.Value = (string)__tagHelperAttribute_4.Value;
                __tagHelperExecutionContext.AddTagHelperAttribute(__tagHelperAttribute_4);
                await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
                if (!__tagHelperExecutionContext.Output.IsContentModified)
                {
                    await __tagHelperExecutionContext.SetOutputContentAsync();
                }
                Write(__tagHelperExecutionContext.Output);
                __tagHelperExecutionContext = __tagHelperScopeManager.End();
                WriteLiteral("</div>\r\n            <div>");
                __tagHelperExecutionContext = __tagHelperScopeManager.Begin("span", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "e8ab9286e85c9e3ae1481024814973ec62862ddb22375", async() => {
                }
                );
                __Microsoft_AspNetCore_Mvc_TagHelpers_ValidationMessageTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.ValidationMessageTagHelper>();
                __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_ValidationMessageTagHelper);
#nullable restore
#line 23 "C:\Users\fabie\Documents\Tilburg HBO-ICT\Semester 2\Individueel project webapp\s2-individueel-project-webapp\Receptenzoeker\Receptenzoeker\Views\User\LogIn.cshtml"
__Microsoft_AspNetCore_Mvc_TagHelpers_ValidationMessageTagHelper.For = ModelExpressionProvider.CreateModelExpression(ViewData, __model => __model.Password);

#line default
#line hidden
#nullable disable
                __tagHelperExecutionContext.AddTagHelperAttribute("asp-validation-for", __Microsoft_AspNetCore_Mvc_TagHelpers_ValidationMessageTagHelper.For, global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
                __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_5);
                await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
                if (!__tagHelperExecutionContext.Output.IsContentModified)
                {
                    await __tagHelperExecutionContext.SetOutputContentAsync();
                }
                Write(__tagHelperExecutionContext.Output);
                __tagHelperExecutionContext = __tagHelperScopeManager.End();
                WriteLiteral("</div>\r\n\r\n            ");
                __tagHelperExecutionContext = __tagHelperScopeManager.Begin("input", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagOnly, "e8ab9286e85c9e3ae1481024814973ec62862ddb24131", async() => {
                }
                );
                __Microsoft_AspNetCore_Mvc_TagHelpers_FormActionTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.FormActionTagHelper>();
                __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_FormActionTagHelper);
                __Microsoft_AspNetCore_Mvc_TagHelpers_FormActionTagHelper.Action = (string)__tagHelperAttribute_9.Value;
                __tagHelperExecutionContext.AddTagHelperAttribute(__tagHelperAttribute_9);
                __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_10);
                __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_11);
                await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
                if (!__tagHelperExecutionContext.Output.IsContentModified)
                {
                    await __tagHelperExecutionContext.SetOutputContentAsync();
                }
                Write(__tagHelperExecutionContext.Output);
                __tagHelperExecutionContext = __tagHelperScopeManager.End();
                WriteLiteral("\r\n        ");
            }
            );
            __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.FormTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper);
            __Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.RenderAtEndOfFormTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper);
            __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper.Controller = (string)__tagHelperAttribute_12.Value;
            __tagHelperExecutionContext.AddTagHelperAttribute(__tagHelperAttribute_12);
            __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_13);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            WriteLiteral("\r\n\r\n");
#nullable restore
#line 28 "C:\Users\fabie\Documents\Tilburg HBO-ICT\Semester 2\Individueel project webapp\s2-individueel-project-webapp\Receptenzoeker\Receptenzoeker\Views\User\LogIn.cshtml"
         if (ViewBag.Message != null)
        {

#line default
#line hidden
#nullable disable
            WriteLiteral("            <script type=\"text/javascript\">\r\n\r\n                window.onload = function () {\r\n                    alert(\"");
#nullable restore
#line 33 "C:\Users\fabie\Documents\Tilburg HBO-ICT\Semester 2\Individueel project webapp\s2-individueel-project-webapp\Receptenzoeker\Receptenzoeker\Views\User\LogIn.cshtml"
                      Write(ViewBag.Message);

#line default
#line hidden
#nullable disable
            WriteLiteral("\");\r\n                };\r\n            </script>\r\n");
#nullable restore
#line 36 "C:\Users\fabie\Documents\Tilburg HBO-ICT\Semester 2\Individueel project webapp\s2-individueel-project-webapp\Receptenzoeker\Receptenzoeker\Views\User\LogIn.cshtml"
        }

#line default
#line hidden
#nullable disable
            WriteLiteral("    </div>\r\n</div>\r\n\r\n");
            DefineSection("Scripts", async() => {
                WriteLiteral("\r\n");
#nullable restore
#line 41 "C:\Users\fabie\Documents\Tilburg HBO-ICT\Semester 2\Individueel project webapp\s2-individueel-project-webapp\Receptenzoeker\Receptenzoeker\Views\User\LogIn.cshtml"
       await Html.RenderPartialAsync("_ValidationScriptsPartial");

#line default
#line hidden
#nullable disable
            }
            );
            WriteLiteral("\r\n\r\n");
        }
        #pragma warning restore 1998
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.ViewFeatures.IModelExpressionProvider ModelExpressionProvider { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IUrlHelper Url { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IViewComponentHelper Component { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IJsonHelper Json { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IHtmlHelper<UserViewModel> Html { get; private set; }
    }
}
#pragma warning restore 1591
