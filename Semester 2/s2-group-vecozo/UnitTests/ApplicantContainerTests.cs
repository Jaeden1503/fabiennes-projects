using Microsoft.VisualStudio.TestTools.UnitTesting;
using Vecozo_Game_App_BLL;
using Vecozo_Game_app_DAL;
using Vecozo_Game_app_DAL.Test_DAL_S;

namespace UnitTests
{
    [TestClass]
    public class ApplicantContainerTests
    {
        ApplicantContainer applicantContainer = new(new ApplicantTestDAL());
        [TestMethod]
        public void AddApplicantTest()
        {
            Assert.AreEqual(2, applicantContainer.AddApplicant("Unit", "Test"));
        }
        [TestMethod]
        public void AddApplicantFailureTest()
        {
            Assert.AreEqual(-1, applicantContainer.AddApplicant("Unit Test", ""));
        }
    }
}
