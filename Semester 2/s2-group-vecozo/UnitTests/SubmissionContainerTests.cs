using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Vecozo_Game_App_BLL;
using Vecozo_Game_app_DAL.Test_DAL_S;

namespace UnitTests
{
    [TestClass]
    public class SubmissionContainerTests
    {
        SubmissionContainer submissionContainer = new(new SubmissionTestDAL());
        [TestMethod]
        public void AddSubmissionTest()
        {
            Submission submission = new(1, 1, DateTime.Now.Ticks, 1, "hey im a code too :D", true);
            Assert.AreEqual(true, submissionContainer.AddSubmission(submission));
        }
        [TestMethod]
        public void AddSubmissionFailureTest()
        {
            Submission submission = new(1, 1, DateTime.Now.Ticks, 1, "", true);
            Assert.AreEqual(false, submissionContainer.AddSubmission(submission));
        }
    }
}
