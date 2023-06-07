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
    public class UnitTestRecipe
    {
        private RecipeContainer recipeContainerTest = new RecipeContainer(new TestRecipeDAL());

        [TestMethod]
        public void TestRecipeConstructor()
        {
            //Arrange
            string category = "Hoofdgerecht";
            int preparationTime = 35;
            int personAmount = 2;
            string type = "Vegetarisch";

            Ingredient ingredient = new Ingredient(1, "kaas");

            //Act
            Recipe recipe = new Recipe(category, preparationTime, personAmount, type, ingredient);

            //Assert
            Assert.AreEqual(category, recipe.Category);
            Assert.AreEqual(preparationTime, recipe.PreparationTime);
            Assert.AreEqual(personAmount, recipe.PersonAmount);
            Assert.AreEqual(type, recipe.Type);

            Assert.AreEqual(ingredient.ID, recipe.Ingredient.ID);
            Assert.AreEqual(ingredient.IngredientName, recipe.Ingredient.IngredientName);
        }

        [TestMethod]
        public void TestGetAllRecipes()
        {
            //Arrange            
            List<RecipeDTO> recipeDTOs = new List<RecipeDTO>()
            {
                new RecipeDTO(1, "pizza"),
                new RecipeDTO(2, "tomaten soep"),
                new RecipeDTO(3, "hamburger"),
                new RecipeDTO(4, "macaroni")
            };

            //Act
            var result = recipeContainerTest.GetAllRecipes();

            //Assert
            Assert.AreEqual(recipeDTOs.Count, result.Count);
            for (int i = 0; i < result.Count; i++)
            {
                Assert.AreEqual(recipeDTOs[i].Name, result[i].Name);
                Assert.AreEqual(recipeDTOs[i].ID, result[i].ID);
            }
        }

        [TestMethod]
        public void TestGetRecipeDetails()
        {
            //Arrange
            List<RecipeIngredient> recipeIngredients = new List<RecipeIngredient>();
            recipeIngredients.Add(new RecipeIngredient(5, "stukken", new Ingredient(3, "kaas")));
            recipeIngredients.Add(new RecipeIngredient(4, "grote", new Ingredient(4, "tomaten")));

            Recipe recipe = new Recipe(2, "tomaten soep", "Hoofdgerecht", 30, "yass", 3, "Vegetarisch", recipeIngredients);

            //Act
            var result = recipeContainerTest.GetRecipeDetails(recipe.ID);

            //Assert
            Assert.AreEqual(recipe.Name, result.Name);
            Assert.AreEqual(recipe.Category, result.Category);
            Assert.AreEqual(recipe.PreparationTime, result.PreparationTime);
            Assert.AreEqual(recipe.PreparationMethod, result.PreparationMethod);
            Assert.AreEqual(recipe.PersonAmount, result.PersonAmount);
            Assert.AreEqual(recipe.Type, result.Type);

            for (int i = 0; i < recipe.RecipeIngredients.Count; i++)
            {
                Assert.AreEqual(recipe.RecipeIngredients[i].Quantity, result.RecipeIngredients[i].Quantity);
                Assert.AreEqual(recipe.RecipeIngredients[i].QuantityUnit, result.RecipeIngredients[i].QuantityUnit);
                Assert.AreEqual(recipe.RecipeIngredients[i].Ingredient.ID, result.RecipeIngredients[i].Ingredient.ID);
                Assert.AreEqual(recipe.RecipeIngredients[i].Ingredient.IngredientName, result.RecipeIngredients[i].Ingredient.IngredientName);
            }
        }

        [TestMethod]
        public void TestCreateRecipe()
        {
            //Arrange
            List<RecipeIngredient> recipeIngredients = new List<RecipeIngredient>();
            recipeIngredients.Add(new RecipeIngredient(5, "stukken", new Ingredient(3, "kaas")));
            recipeIngredients.Add(new RecipeIngredient(4, "grote", new Ingredient(4, "tomaten")));

            Recipe recipe = new Recipe("nieuw recept", "Nagerecht", 5, "coole beschrijving", 3, "Vlees", recipeIngredients);

            //Act
            var result = recipeContainerTest.CreateRecipe(recipe);
            var otherresult = recipeContainerTest.GetAllRecipes();

            //Assert
            Assert.AreEqual(true, result);
            Assert.AreEqual(5, otherresult.Count);

            for (int i = 0; i < otherresult.Count; i++)
            {
                if (recipe.Name == otherresult[i].Name)
                {
                    Assert.AreEqual(recipe.Name, otherresult[i].Name);
                    Assert.AreEqual(recipe.Category, otherresult[i].Category);
                    Assert.AreEqual(recipe.PreparationTime, otherresult[i].PreparationTime);
                    Assert.AreEqual(recipe.PreparationMethod, otherresult[i].PreparationMethod);
                    Assert.AreEqual(recipe.PersonAmount, otherresult[i].PersonAmount);
                    Assert.AreEqual(recipe.Type, otherresult[i].Type);

                    for (int k = 0; k < otherresult[i].RecipeIngredients.Count; k++)
                    {
                        Assert.AreEqual(recipe.RecipeIngredients[k].Quantity, otherresult[i].RecipeIngredients[k].Quantity);
                        Assert.AreEqual(recipe.RecipeIngredients[k].QuantityUnit, otherresult[i].RecipeIngredients[k].QuantityUnit);
                        Assert.AreEqual(recipe.RecipeIngredients[k].Ingredient.ID, otherresult[i].RecipeIngredients[k].Ingredient.ID);
                        Assert.AreEqual(recipe.RecipeIngredients[k].Ingredient.IngredientName, otherresult[i].RecipeIngredients[k].Ingredient.IngredientName);
                    }
                }
            }
        }

        [TestMethod]
        public void TestEditRecipe()
        {
            //Arrange
            List<RecipeIngredient> recipeIngredients = new List<RecipeIngredient>();
            recipeIngredients.Add(new RecipeIngredient(5, "stukken", new Ingredient(3, "kaas")));
            recipeIngredients.Add(new RecipeIngredient(4, "grote", new Ingredient(4, "tomaten")));

            Recipe recipe = new Recipe(4, "nieuw recept", "Nagerecht", 80, "coole beschrijving", 3, "Vlees", recipeIngredients);

            //Act
            bool result = recipeContainerTest.UpdateRecipe(recipe);
            var otherresult = recipeContainerTest.GetAllRecipes();

            //Assert
            Assert.AreEqual(true, result);

            Assert.AreEqual(recipe.Name, otherresult[3].Name);
            Assert.AreEqual(recipe.Category, otherresult[3].Category);
            Assert.AreEqual(recipe.PreparationTime, otherresult[3].PreparationTime);
            Assert.AreEqual(recipe.PreparationMethod, otherresult[3].PreparationMethod);
            Assert.AreEqual(recipe.PersonAmount, otherresult[3].PersonAmount);
            Assert.AreEqual(recipe.Type, otherresult[3].Type);

            for (int k = 0; k < otherresult[3].RecipeIngredients.Count; k++)
            {
                Assert.AreEqual(recipe.RecipeIngredients[k].Quantity, otherresult[3].RecipeIngredients[k].Quantity);
                Assert.AreEqual(recipe.RecipeIngredients[k].QuantityUnit, otherresult[3].RecipeIngredients[k].QuantityUnit);
                Assert.AreEqual(recipe.RecipeIngredients[k].Ingredient.ID, otherresult[3].RecipeIngredients[k].Ingredient.ID);
                Assert.AreEqual(recipe.RecipeIngredients[k].Ingredient.IngredientName, otherresult[3].RecipeIngredients[k].Ingredient.IngredientName);
            }
        }

        [TestMethod]
        public void TestEditRecipeFail()
        {
            //Arrange
            List<RecipeIngredient> recipeIngredients = new List<RecipeIngredient>();
            recipeIngredients.Add(new RecipeIngredient(5, "stukken", new Ingredient(3, "kaas")));
            recipeIngredients.Add(new RecipeIngredient(4, "grote", new Ingredient(4, "tomaten")));

            Recipe recipe = new Recipe(5, "nieuw recept", "Nagerecht", 0, "coole beschrijving", 3, "Vlees", recipeIngredients);

            //Act
            bool result = recipeContainerTest.UpdateRecipe(recipe);

            //Assert
            Assert.AreEqual(false, result);           
        }

        [TestMethod]
        public void TestDeleteRecipe()
        {
            //Arrange
            Recipe recipe = new Recipe(2);

            //Act
            var result = recipeContainerTest.DeleteRecipe(recipe.ID);
            var otherresult = recipeContainerTest.GetAllRecipes();

            //Assert
            Assert.AreEqual(true, result);
            Assert.AreEqual(3, otherresult.Count);
            Assert.AreEqual(false, otherresult.Any(item => item.ID == recipe.ID));
        }

        [TestMethod]
        public void TestFindRecipe()
        {
            //Arrange
            Recipe recipe = new Recipe("Hoofdgerecht", 30, 3, "Vegetarisch", new Ingredient("tomaten"));

            //Act
            var result = recipeContainerTest.FindRecipe(recipe);

            //Assert
            Assert.AreEqual(1, result.Count);
            Assert.AreEqual(2, result[0].ID);
            Assert.AreEqual("tomaten soep", result[0].Name);
        }

        [TestMethod]
        public void TestFindRecipeByCategory()
        {
            //Arrange
            string category = "Voorgerecht";
            string category2 = "Hoofdgerecht";

            //Act
            var result = recipeContainerTest.RecipesByCategory(category);
            var result2 = recipeContainerTest.RecipesByCategory(category2);

            //Assert
            Assert.AreEqual(0, result.Count);
            Assert.AreEqual(4, result2.Count);
        }

        [TestMethod]
        public void TestFindRecipeByType()
        {
            //Arrange
            string type = "Vegetarisch";
            string type2 = "Vlees";

            //Act
            var result = recipeContainerTest.RecipesByType(type);
            var result2 = recipeContainerTest.RecipesByType(type2);

            //Assert
            Assert.AreEqual(3, result.Count);
            Assert.AreEqual(1, result2.Count);
            Assert.AreEqual(4, result2[0].ID);
            Assert.AreEqual("macaroni", result2[0].Name);
        }

        [TestMethod]
        public void TestFindRecipeByTime()
        {
            //Arrange
            int time = 30;
            int time2 = 60;

            //Act
            var result = recipeContainerTest.RecipesByTime(time);
            var result2 = recipeContainerTest.RecipesByTime(time2);

            //Assert
            Assert.AreEqual(2, result.Count);
            Assert.AreEqual(4, result2.Count);
        }
    }
}
