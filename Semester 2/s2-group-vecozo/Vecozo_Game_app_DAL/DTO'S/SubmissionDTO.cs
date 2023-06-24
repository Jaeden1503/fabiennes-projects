using System;
using System.Collections.Generic;
using System.Text;

namespace Vecozo_Game_app_DAL
{
    public class SubmissionDTO
    {
        public int ChallengeID { get; set; }
        public int ApplicantID { get; set; }
        public int Attempts { get; set; }
        public string Code { get; set; }
        public DateTime EndTime { get; set; }
        public long Timespan { get; set; }
        public bool Validation { get; set; }

        public SubmissionDTO(int challengeid, int applicantid, int attempts, string code, DateTime endTime, long timespan, bool validation)
        {
            ChallengeID = challengeid;
            ApplicantID = applicantid;
            Attempts = attempts;
            Code = code;
            EndTime = endTime;
            Timespan = timespan;
            Validation = validation;
        }
    }
}
