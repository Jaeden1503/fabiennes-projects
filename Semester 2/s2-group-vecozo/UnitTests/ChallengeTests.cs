using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Vecozo_Game_App_BLL;
using Vecozo_Game_app_DAL.Test_DAL_S;

namespace UnitTests
{
    [TestClass]
    public class ChallengeTests
    {
        ChallengeContainer challengeContainer = new ChallengeContainer(new ChallengeTestDAL());
        [TestMethod]
        public void GetAllChallengeTest()
        {
            Assert.AreEqual(3, challengeContainer.GetAllActiveChallenges().Count);
        }
        [TestMethod]
        public void GetChallengeByIDTest()
        {
            Assert.AreEqual(1, challengeContainer.GetChallengeByID(1).ID);
        }
    }
}
