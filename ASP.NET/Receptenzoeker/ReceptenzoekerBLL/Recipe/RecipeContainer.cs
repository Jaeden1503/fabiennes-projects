using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ReceptenzoekerDAL;

namespace ReceptenzoekerBLL
{
    public class  RecipeContainer
    {
        private IRecipeContainer irecipeContainer;

        public RecipeContainer(IRecipeContainer iRecipeContainer)
        {
            this.irecipeContainer = iRecipeContainer;
        }

        public List<Recipe> GetAllRecipes()
        {
            List<Recipe> recipes = new List<Recipe>();
            List<RecipeDTO> recipeDTOs = irecipeContainer.GetAllRecipes();

            foreach (RecipeDTO recipeDTO in recipeDTOs)
            {
                recipes.Add(new Recipe(recipeDTO));
            }
            return recipes;
        }

        public int RecipeCount()
        {
            List<RecipeDTO> recipeDTOs = irecipeContainer.GetAllRecipes();
            return recipeDTOs.Count;
        }


        public Recipe GetRecipeDetails(int id)
        {
            return new Recipe(irecipeContainer.GetRecipeDetailsByID(id));
        }

        public bool CreateRecipe(Recipe recipe)
        {
            return irecipeContainer.CreateRecipe(ConvertToDTO(recipe));
        }

        public bool UpdateRecipe(Recipe recipe)
        {
            return irecipeContainer.UpdateRecipe(ConvertToDTO(recipe));
        }

        public bool DeleteRecipe(int id)
        {
            return irecipeContainer.DeleteRecipe(id);
        }

        public List<Recipe> FindRecipe(Recipe recipe)
        {
            List<Recipe> recipes = new List<Recipe>();
            List<RecipeDTO> recipeDTOs = irecipeContainer.FindRecipe(ConvertToDTOFind(recipe));

            foreach (RecipeDTO recipeDTO in recipeDTOs)
            {
                recipes.Add(new Recipe(recipeDTO));
            }
            return recipes;
        }

        public List<Recipe> RecipesByCategory(string category)
        {
            List<Recipe> recipes = new List<Recipe>();
            List<RecipeDTO> recipeDTOs = irecipeContainer.RecipesByCategory(category);

            foreach (RecipeDTO recipeDTO in recipeDTOs)
            {
                recipes.Add(new Recipe(recipeDTO));
            }
            return recipes;
        }

        public List<Recipe> RecipesByType(string type)
        {
            List<Recipe> recipes = new List<Recipe>();
            List<RecipeDTO> recipeDTOs = irecipeContainer.RecipesByType(type);

            foreach (RecipeDTO recipeDTO in recipeDTOs)
            {
                recipes.Add(new Recipe(recipeDTO));
            }
            return recipes;
        }

        public List<Recipe> RecipesByTime(int time)
        {
            List<Recipe> recipes = new List<Recipe>();
            List<RecipeDTO> recipeDTOs = irecipeContainer.RecipesByTime(time);

            foreach (RecipeDTO recipeDTO in recipeDTOs)
            {
                recipes.Add(new Recipe(recipeDTO));
            }
            return recipes;
        }


        ////////// METHODS FOR CONVERTING TO DTO /////////

        public RecipeDTO ConvertToDTO(Recipe recipe)
        {
            List<RecipeIngredientDTO> recipeIngredients = new List<RecipeIngredientDTO>();
            foreach (RecipeIngredient recipeIngredient in recipe.RecipeIngredients)
            {
                recipeIngredients.Add(new RecipeIngredientDTO(recipeIngredient.Quantity, recipeIngredient.QuantityUnit, 
                    new IngredientDTO(recipeIngredient.Ingredient.ID, recipeIngredient.Ingredient.IngredientName)));
            }

            return new RecipeDTO(recipe.ID, recipe.Name, recipe.Category, recipe.PreparationTime, recipe.PreparationMethod, recipe.PersonAmount, recipe.Type, recipeIngredients);
        }

        public RecipeDTO ConvertToDTOFind(Recipe recipe)
        {
            return new RecipeDTO(recipe.Category, recipe.PreparationTime, recipe.PersonAmount, recipe.Type, new IngredientDTO(recipe.Ingredient.IngredientName));
        }
    }
}
