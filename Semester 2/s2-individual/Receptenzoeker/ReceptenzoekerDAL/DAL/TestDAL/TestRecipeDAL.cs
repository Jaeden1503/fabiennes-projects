using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReceptenzoekerDAL
{
    public class TestRecipeDAL : IRecipeContainer
    {
        List<RecipeDTO> fakeRecipes = new List<RecipeDTO>()
        {
            new RecipeDTO(1, "pizza", "Hoofdgerecht", 10, "niks", 1, "Vegetarisch", new List<RecipeIngredientDTO>()
                {
                    new RecipeIngredientDTO(10, "bladen", new IngredientDTO(1, "sla")),
                    new RecipeIngredientDTO(1, "sneufje", new IngredientDTO(2, "zout"))
                }),
            new RecipeDTO(2, "tomaten soep", "Hoofdgerecht", 30, "yass", 3, "Vegetarisch", new List<RecipeIngredientDTO>()
                {
                    new RecipeIngredientDTO(5, "stukken", new IngredientDTO(3, "kaas")),
                    new RecipeIngredientDTO(4, "grote", new IngredientDTO(4, "tomaten"))
                }),
             new RecipeDTO(3, "hamburger", "Hoofdgerecht", 45, "yass", 3, "Vegetarisch", new List<RecipeIngredientDTO>()
                {
                    new RecipeIngredientDTO(5, "stukken", new IngredientDTO(3, "kaas")),
                    new RecipeIngredientDTO(4, "grote", new IngredientDTO(5, "appels"))
                }),
             new RecipeDTO(4, "macaroni", "Hoofdgerecht", 60, "yass", 3, "Vlees", new List<RecipeIngredientDTO>()
             {
                new RecipeIngredientDTO(5, "stukken", new IngredientDTO(6, "mosterd")),
                new RecipeIngredientDTO(4, "grote", new IngredientDTO(4, "tomaten"))
             })
        };

        public List<RecipeDTO> GetAllRecipes()
        {
            return fakeRecipes;
        }

        public RecipeDTO GetRecipeDetailsByID(int id)
        {
            foreach (RecipeDTO item in fakeRecipes)
            {
                if (item.ID == id)
                {
                    return item;
                }
            }
            return null;
        }

        public bool CreateRecipe(RecipeDTO recipeDTO)
        {
            fakeRecipes.Add(recipeDTO);
            return true;
        }

        public bool UpdateRecipe(RecipeDTO recipeDTO)
        {
            for (int i = 0; i < fakeRecipes.Count; i++)
            {
                if (fakeRecipes[i].ID == recipeDTO.ID)
                {
                    fakeRecipes[i] = recipeDTO;
                    return true;
                }
            }
            return false;
        }

        public bool DeleteRecipe(int id)
        {
            fakeRecipes.RemoveAt(id - 1);
            return true;
        }

        public List<RecipeDTO> FindRecipe(RecipeDTO recipeDTO)
        {
            List<RecipeDTO> compatibleRecipes = new List<RecipeDTO>();

            foreach (RecipeDTO item in fakeRecipes)
            {
                if (recipeDTO.Category == item.Category && recipeDTO.PreparationTime == item.PreparationTime && recipeDTO.PersonAmount == item.PersonAmount &&
                    recipeDTO.Type == item.Type)
                {
                    for (int i = 0; i < item.RecipeIngredientDTO.Count; i++)
                    {
                        if (recipeDTO.IngredientDTO.IngredientName == item.RecipeIngredientDTO[i].IngredientDTO.IngredientName)
                        {
                            compatibleRecipes.Add(item);
                        }
                    }        
                }
            }
            return compatibleRecipes;
        }

        public List<RecipeDTO> RecipesByCategory(string category)
        {
            List<RecipeDTO> compatibleRecipes = new List<RecipeDTO>();

            foreach (RecipeDTO item in fakeRecipes)
            {
                if (category == item.Category)
                {
                    compatibleRecipes.Add(item);
                }
            }
            return compatibleRecipes;
        }

        public List<RecipeDTO> RecipesByType(string type)
        {
            List<RecipeDTO> compatibleRecipes = new List<RecipeDTO>();

            foreach (RecipeDTO item in fakeRecipes)
            {
                if (type == item.Type)
                {
                    compatibleRecipes.Add(item);
                }
            }
            return compatibleRecipes;
        }

        public List<RecipeDTO> RecipesByTime(int time)
        {
            List<RecipeDTO> compatibleRecipes = new List<RecipeDTO>();

            foreach (RecipeDTO item in fakeRecipes)
            {
                if (item.PreparationTime <= time)
                {
                    compatibleRecipes.Add(item);
                }
            }
            return compatibleRecipes;
        }
    }
}
