using System;
using System.Collections.Generic;
using System.Text;

namespace Vecozo_Game_app_DAL
{
    public interface ISubmissionContainer
    {
        bool AddSubmission(SubmissionDTO submissionDTO);
    }
}
