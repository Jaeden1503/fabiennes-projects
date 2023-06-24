using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Vecozo_Game_App_BLL;
using Vecozo_Game_app_DAL;
using Vecozo_Game_app_DAL.Test_DAL_S;

namespace UnitTests
{
    [TestClass]
    public class EmailSignalTest
    {
        [TestMethod]
        public void SendTest()
        {
            EmailSignal emailSignal = new();
            Assert.AreEqual(true, emailSignal.Send("Hoi Paul :)", "example@noreply.com", 404, "De email werkt :)", true));
        }
    }
}
