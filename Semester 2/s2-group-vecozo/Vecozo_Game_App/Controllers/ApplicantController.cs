using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Vecozo_Game_App.Models;
using Vecozo_Game_App_BLL;
using Vecozo_Game_app_DAL;
using System.Text.RegularExpressions;

namespace Vecozo_Game_App.Controllers
{
    public class ApplicantController : Controller
    {
        ApplicantContainer applicantContainer = new ApplicantContainer(new ApplicantDAL());
        SubmissionContainer submissionContainer = new(new SubmissionDAL());
        EmailSignal emailSignal = new EmailSignal();

        public IActionResult Index()
        {
            return View();
        }

        public IActionResult AddApplicant(string name, string email)
        {            
            Regex regex = new Regex(@"^([\w.-]+)@([\w-]+)((.(\w){2,3})+)$");
            Match match = regex.Match(email);

            if ((name != "" && email != "") && match.Success)
            {
                int applicantID = applicantContainer.DoesApplicantExists(name, email);
                if (applicantID > 0)
                {
                    return RedirectToAction("AddSubmission", new { applicantid = applicantID, name, email });
                }
                applicantID = applicantContainer.AddApplicant(name, email);

                if(applicantID > 0)
                {
                    return RedirectToAction("AddSubmission", new { applicantid = applicantID, name, email });
                }
                //If applicant could not be added.
                return View("Index");
            }
            //If name and/or email is empty, and/or email doesn't match.
            return View("Index");
        }

        public IActionResult AddSubmission(int applicantid, string name, string email)
        {
            Submission submission = new Submission(HttpContext.Session.GetInt32("ChallengeID").Value, applicantid, (long)Convert.ToDouble(HttpContext.Session.GetInt32("BeginTime").Value), HttpContext.Session.GetInt32("Attempts").Value, HttpContext.Session.GetString("Code"), Convert.ToBoolean(HttpContext.Session.GetInt32("Validation").Value));
            if (submissionContainer.AddSubmission(submission))
            {
                //If everything goes right when adding the submission.
                emailSignal.Send(name, email, HttpContext.Session.GetInt32("ChallengeID").Value, HttpContext.Session.GetString("ChallengeName"), Convert.ToBoolean(HttpContext.Session.GetInt32("Validation").Value));
                ApplicantViewModel avm = new ApplicantViewModel(name, email, Convert.ToBoolean(HttpContext.Session.GetInt32("Validation").Value), HttpContext.Session.GetString("GivenAnswer"), HttpContext.Session.GetString("Answer"));
                return View("ChallengeEndPage", avm);
            }
            //If adding submission goes wrong.
            return View("Index");
        }
    }
}
