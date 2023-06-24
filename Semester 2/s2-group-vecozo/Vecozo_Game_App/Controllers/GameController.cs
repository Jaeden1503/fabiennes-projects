using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Vecozo_Game_App.Models;
using Vecozo_Game_App_BLL;
using Vecozo_Game_app_DAL;

namespace Vecozo_Game_App.Controllers
{
    public class GameController : Controller
    {
        Compiler compiler = new Compiler();
        Runner runner = new Runner();
        ChallengeContainer challengeContainer = new ChallengeContainer(new ChallengeDAL());

        public IActionResult Index(int id)
        {
            Challenge challenge = challengeContainer.GetChallengeByID(id);
            ChallengeViewModel challengeViewModel = new ChallengeViewModel();         
            challengeViewModel.Name = challenge.Name;
            challengeViewModel.Description = challenge.Description;
            challengeViewModel.Assignment = challenge.Assignment;
            challengeViewModel.Hint = challenge.Hint;

            long dateTime = DateTime.Now.Ticks;

            HttpContext.Session.SetString("ChallengeName", challenge.Name);
            HttpContext.Session.SetString("BeginTime", dateTime.ToString());
            HttpContext.Session.SetInt32("Attempts", 0);
            HttpContext.Session.SetInt32("ChallengeID", challenge.ID);
            HttpContext.Session.SetString("Answer", challenge.Answer);
            return View(challengeViewModel);
        }

        [HttpPost]
        public IActionResult GameFunction(string[] code)
        {
            int i = HttpContext.Session.GetInt32("Attempts").Value;
            HttpContext.Session.SetInt32("Attempts", i + 1);

            string CodeName = HttpContext.Session.Id;
            var a = compiler.Compile(String.Join(" ", code), $"{CodeName}.exe"); //naam van de file waar we de code IN willen gaan zetten
            if (!a.IsError)
            {
                runner.CreateConfig(CodeName);
                a = runner.RunCode($"{CodeName}.exe");
            }
            runner.DeleteCompilerFiles(CodeName);

            return Json(a.Messages);
        }

        public IActionResult Submit(string[] code, string givenanswer)
        {
            if(givenanswer == null) { givenanswer = "Null"; }
            HttpContext.Session.SetInt32("Validation", 0);
            HttpContext.Session.SetString("GivenAnswer", givenanswer);
            if (givenanswer != null && givenanswer == HttpContext.Session.GetString("Answer"))
            {
                HttpContext.Session.SetInt32("Validation", 1);
            }

            HttpContext.Session.SetString("Code", string.Join("#$%", code));
            return Json("done");
        }
    }
}
