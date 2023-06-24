using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Vecozo_Game_app_DAL.Test_DAL_S
{
    public class SubmissionTestDAL : ISubmissionContainer
    {
        List<SubmissionDTO> submissionDTOs = new()
        {
            new SubmissionDTO(1, 1, 1, "im a code :D", DateTime.Now, (DateTime.Now - DateTime.UtcNow).Ticks, true)
        };
        public bool AddSubmission(SubmissionDTO submissionDTO)
        {
            try 
            {
                if(submissionDTO.ApplicantID != -1 && submissionDTO.Attempts != -1 && submissionDTO.ChallengeID != -1 && submissionDTO.Code != string.Empty)
                { submissionDTOs.Add(submissionDTO); return true; }
                return false;
            }
            catch
            {
                return false;
            }
        }
    }
}
