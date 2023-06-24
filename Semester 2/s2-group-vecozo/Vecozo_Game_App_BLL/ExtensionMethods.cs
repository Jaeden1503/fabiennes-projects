using System;
using System.Collections.Generic;
using System.Text;
using Vecozo_Game_App_BLL;
using Vecozo_Game_app_DAL;

namespace ExtensionMethods
{
    public static class ExtensionMethods
    {
        public static SubmissionDTO ToDTO(this Submission submission)
        {
            return new SubmissionDTO(submission.ChallengeID, submission.ApplicantID, submission.Attempts, submission.Code, submission.EndTime, submission.Timespan, submission.Validation);
        }
    }
}
