using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using ReceptenzoekerBLL;
using ReceptenzoekerDAL;

namespace UnitTestReceptenzoeker
{
    [TestClass]
    public class UnitTestUser
    {
        private UserContainer userContainerTest = new UserContainer(new TestUserDAL());

        [TestMethod]
        public void TestUserConstructor()
        {
            //Arrange
            string name = "piet";
            string password = "pietje";

            //Act
            User result = new User(name, password);

            //Assert
            Assert.AreEqual(name, result.Name);
            Assert.AreEqual(password, result.Password);

        }

        [TestMethod]
        public void TestGetUserLogin()
        {
            //Arrange            
            User user = new User("piet", "pietje");
            User user1 = new User("jan", "jansen");

            //Act
            var result = userContainerTest.GetUserLogin(user);
            var result2 = userContainerTest.GetUserLogin(user1);

            //Assert
            Assert.AreEqual(user.Name, result.Name);
            Assert.AreEqual(user.Password, result.Password);
            Assert.AreEqual(1, result.ID);
            Assert.AreEqual(false, result.IsAdmin);
            Assert.AreEqual(true, result.IsActive);

            Assert.AreEqual(user1.Name, result2.Name);
            Assert.AreEqual(user1.Password, result2.Password);
            Assert.AreEqual(2, result2.ID);
            Assert.AreEqual(false, result2.IsAdmin);
            Assert.AreEqual(false, result2.IsActive);
        }

        [TestMethod]
        public void TestGetUserLoginFail()
        {
            //Arrange
            User user = new User("piet", "jan");

            //Act
            var result = userContainerTest.GetUserLogin(user);

            //Assert
            Assert.AreEqual(string.Empty, result.Name);
            Assert.AreEqual(string.Empty, result.Password);
            Assert.AreEqual(0, result.ID);
            Assert.AreEqual(false, result.IsAdmin);
            Assert.AreEqual(false, result.IsActive);
        }

        [TestMethod]
        public void TestGetAllUsers()
        {
            //Arrange
            List<User> users = new List<User>()
            {
                new User(new UserDTO(1, "piet", true)),
                new User(new UserDTO(2, "jan", false)),
            };

            //Act
            var result = userContainerTest.GetAllUsers();

            //Assert
            Assert.AreEqual(users.Count, result.Count);
            for (int i = 0; i < users.Count; i++)
            {
                Assert.AreEqual(users[i].ID, result[i].ID);
                Assert.AreEqual(users[i].Name, result[i].Name);
                Assert.AreEqual(users[i].IsActive, result[i].IsActive);
            }
        }

        [TestMethod]
        public void TestUpdateUserStatus()
        {
            //Arrange
            User user = new User(1, true);
            User user2 = new User(2, false);

            //Act
            var result = userContainerTest.UpdateUserStatus(user);
            var result2 = userContainerTest.UpdateUserStatus(user2);

            var otherresult = userContainerTest.GetAllUsers();

            //Assert
            Assert.AreEqual(true, result);
            Assert.AreEqual(true, otherresult[user.ID - 1].IsActive);

            Assert.AreEqual(true, result2);
            Assert.AreEqual(false, otherresult[user2.ID - 1].IsActive);
        }
    }
}
