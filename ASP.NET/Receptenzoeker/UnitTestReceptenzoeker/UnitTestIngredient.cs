using Microsoft.VisualStudio.TestTools.UnitTesting;
using ReceptenzoekerBLL;
using ReceptenzoekerDAL;
using System.Collections.Generic;
using System.Linq;

namespace UnitTestReceptenzoeker
{
    [TestClass]
    public class UnitTestIngredient
    {
        private IngredientContainer ingredientContainerTest = new IngredientContainer(new TestIngredientDAL());

        [TestMethod]
        public void TestIngredientConstructor()
        {
            //Arrange
            string name = "kaas";

            //Act
            Ingredient ingredient = new Ingredient(name);

            //Assert
            Assert.AreEqual(name, ingredient.IngredientName);
        }

        [TestMethod]
        public void TestGetIngredient()
        {
            //Arrange
            Ingredient ingredient = new Ingredient(2, "kaas");

            //Act
            var result = ingredientContainerTest.GetIngredient(ingredient.ID);

            //Assert
            Assert.AreEqual(ingredient.ID, result.ID);
            Assert.AreEqual(ingredient.IngredientName, result.IngredientName);
        }

        [TestMethod]
        public void TestGetAllIngredients()
        {
            //Arrange
            List<Ingredient> fakeIngredients = new List<Ingredient>()
            {
                new Ingredient(1, "boter"),
                new Ingredient(2, "kaas"),
                new Ingredient(3, "ei"),
                new Ingredient(4, "mosterd")
            };

            //Act
            var result = ingredientContainerTest.GetAllIngredients();

            //Assert
            Assert.AreEqual(fakeIngredients.Count, result.Count);
            for (int i = 0; i < result.Count; i++)
            {
                Assert.AreEqual(fakeIngredients[i].IngredientName, result[i].IngredientName);
                Assert.AreEqual(fakeIngredients[i].ID, result[i].ID);
            }
        }

        [TestMethod]
        public void TestAddIngredient()
        {
            //Arrange
            Ingredient ingredient = new Ingredient("kaas");

            //Act
            bool result = ingredientContainerTest.AddIngredient(ingredient);
            var otherResult = ingredientContainerTest.GetAllIngredients();

            //Assert
            Assert.AreEqual(true, result);
            Assert.AreEqual(5, otherResult.Count);
            Assert.AreEqual(ingredient.IngredientName, otherResult[4].IngredientName);
        }

        [TestMethod]
        public void TestIngredientValidity()
        {
            //Arrange
            Ingredient ingredient = new Ingredient("ei");

            //Act
            bool result = ingredientContainerTest.CheckIngredientValidity(ingredient);

            //Assert
            Assert.AreEqual(false, result);
        }

        [TestMethod]
        public void TestIngredientValidityFail()
        {
            //Arrange
            Ingredient ingredient = new Ingredient("tomaten");

            //Act
            bool result = ingredientContainerTest.CheckIngredientValidity(ingredient);

            //Assert
            Assert.AreEqual(true, result);
        }

        [TestMethod]
        public void TestEditIngredient()
        {
            //Arrange
            Ingredient ingredient = new Ingredient(1, "kruidenkaas");

            //Act
            bool result = ingredientContainerTest.EditIngredient(ingredient);
            var otherresult = ingredientContainerTest.GetAllIngredients();

            //Assert
            Assert.AreEqual(true, result);
            Assert.AreEqual(ingredient.ID, otherresult[0].ID);
            Assert.AreEqual(ingredient.IngredientName, otherresult[0].IngredientName);
        }

        [TestMethod]
        public void TestEditIngredientFail()
        {
            //Arrange
            Ingredient ingredient = new Ingredient(0, "");

            //Act
            bool result = ingredientContainerTest.EditIngredient(ingredient);

            //Assert
            Assert.AreEqual(false, result);
        }


        [TestMethod]
        public void TestDeleteIngredient()
        {
            //Arrange
            Ingredient ingredient = new Ingredient(1);

            //Act
            bool result = ingredientContainerTest.DeleteIngredient(ingredient.ID);
            var otherresult = ingredientContainerTest.GetAllIngredients();

            //Assert
            Assert.AreEqual(true, result);
            Assert.AreEqual(3, otherresult.Count);
            Assert.AreEqual(false, otherresult.Any(item => item.ID == ingredient.ID));
        }
    }
}
