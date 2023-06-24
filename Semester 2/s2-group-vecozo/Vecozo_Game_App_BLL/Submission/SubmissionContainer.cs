using System;
using System.Collections.Generic;
using System.Text;
using Vecozo_Game_app_DAL;
using ExtensionMethods;

namespace Vecozo_Game_App_BLL
{
    public class SubmissionContainer
    {
        private ISubmissionContainer isubmissionContainer;

        public SubmissionContainer(ISubmissionContainer isubmissionContainer)
        {
            this.isubmissionContainer = isubmissionContainer;
        }

        public bool AddSubmission(Submission submission)
        {
            return isubmissionContainer.AddSubmission(submission.ToDTO());
        }
    }
}
