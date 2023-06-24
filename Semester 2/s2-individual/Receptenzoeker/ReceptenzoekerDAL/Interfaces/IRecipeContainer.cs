using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReceptenzoekerDAL
{
    public interface IRecipeContainer
    {
        List<RecipeDTO> GetAllRecipes();
        RecipeDTO GetRecipeDetailsByID(int id);
        bool CreateRecipe(RecipeDTO recipeDTO);
        bool UpdateRecipe(RecipeDTO recipeDTO);
        bool DeleteRecipe(int id);
        List<RecipeDTO> FindRecipe(RecipeDTO recipeDTO);
        List<RecipeDTO> RecipesByCategory(string category);
        List<RecipeDTO> RecipesByType(string type);
        List<RecipeDTO> RecipesByTime(int time);
    }
}
