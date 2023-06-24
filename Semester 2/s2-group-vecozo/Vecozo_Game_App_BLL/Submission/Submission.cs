using System;
using System.Collections.Generic;
using System.Text;

namespace Vecozo_Game_App_BLL
{
    public class Submission
    {
        public int ChallengeID { get; private set; }
        public int ApplicantID { get; private set; }
        public int Attempts { get; private set; }
        public string Code { get; private set; }
        public DateTime EndTime { get; private set; }
        public long Timespan { get; private set; }
        public bool Validation { get; private set; }

        public Submission(int challengeid, int applicantid, long beginTime, int attempts, string code, bool validation)
        {
            ChallengeID = challengeid;
            ApplicantID = applicantid;
            Attempts = attempts;
            Code = code;
            Validation = validation;
            DateTime BeginTime = new(beginTime);
            EndTime = DateTime.Now;
            Timespan = (EndTime - BeginTime).Ticks;
        }
    }
}
