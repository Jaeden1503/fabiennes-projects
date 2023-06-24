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
    public class UnitTestReview
    {
        private ReviewContainer reviewContainerTest = new ReviewContainer(new TestReviewDAL());

        [TestMethod]
        public void TestReviewConstructor()
        {
            //Arrange
            int recipeid = 1;
            string title = "titel";
            string description = "beschrijving";
            int userid = 1;

            //Act
            Review result = new Review(title, description, new User(userid), new Recipe(recipeid));

            //Assert
            Assert.AreEqual(recipeid, result.Recipe.ID);
            Assert.AreEqual(title, result.Title);
            Assert.AreEqual(description, result.Description);
            Assert.AreEqual(userid, result.User.ID);
        }

        [TestMethod]
        public void TestGetAllReviewCount()
        {
            //Arrange
            List<Review> fakeReviews = new List<Review>()
            {
                new Review("titel", "beschrijving", new User(1), new Recipe(2)),
                new Review("titel2", "beschrijving2", new User(2), new Recipe(4)),
                new Review("titel3", "beschrijving3", new User(1), new Recipe(4))
            };

            //Act
            int result = reviewContainerTest.ReviewCount();

            //Assert
            Assert.AreEqual(fakeReviews.Count, result);
        }

        [TestMethod]
        public void TestGetReviewsByID()
        {
            //Arrange
            int recipeid = 4;

            List<Review> fakeReviews = new List<Review>()
            {
                new Review(new ReviewDTO(2, "titel2", "beschrijving2", new UserDTO(2))),
                new Review(new ReviewDTO(3, "titel3", "beschrijving3", new UserDTO(1)))
            };

            //Act
            var result = reviewContainerTest.GetReviewsByID(recipeid);

            //Assert
            Assert.AreEqual(2, result.Count);
            for (int i = 0; i < fakeReviews.Count; i++)
            {
                Assert.AreEqual(fakeReviews[i].ID, result[i].ID);
                Assert.AreEqual(fakeReviews[i].Title, result[i].Title);
                Assert.AreEqual(fakeReviews[i].Description, result[i].Description);
                Assert.AreEqual(fakeReviews[i].User.ID, result[i].User.ID);
            }
        }

        [TestMethod]
        public void TestCreateReview()
        {
            //Arrange
            Review review = new Review("yass", "yassss", new User(2), new Recipe(2));

            //Act
            var result = reviewContainerTest.CreateReview(review);
            var otherResult = reviewContainerTest.GetReviewsByID(review.Recipe.ID);

            //Assert
            Assert.AreEqual(true, result);
            Assert.AreEqual(2, otherResult.Count); //all the reviews that belong to recipe with id 2
            for (int i = 0; i < otherResult.Count; i++)
            {
                if (review.Title == otherResult[i].Title)
                {
                    Assert.AreEqual(review.Title, otherResult[i].Title);
                    Assert.AreEqual(review.Description, otherResult[i].Description);
                    Assert.AreEqual(review.User.ID, otherResult[i].User.ID);
                }
            }
        }

        [TestMethod]
        public void TestDeleteReview()
        {
            //Arrange
            Review review = new Review(1);
            Recipe recipe = new Recipe(2);

            //Act
            bool result = reviewContainerTest.DeleteReview(review.ID);
            var otherresult = reviewContainerTest.GetReviewsByID(recipe.ID);

            //Assert
            Assert.AreEqual(true, result);
            Assert.AreEqual(0, otherresult.Count);
            Assert.AreEqual(false, otherresult.Any(item => item.ID == review.ID));
        }
    }
}
