using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ReceptenzoekerDAL;

namespace ReceptenzoekerBLL
{
    public class Recipe
    {
        public int ID { get; private set; }
        public string Name { get; private set; }
        public string Category { get; private set; }
        public int PreparationTime { get; private set; }
        public string PreparationMethod { get; private set; }
        public int PersonAmount { get; private set; }
        public string Type { get; private set; }

        public List<RecipeIngredient> RecipeIngredients { get; private set; }

        public Ingredient Ingredient { get; private set; }

        //for getting details and updating
        public Recipe(int id, string name, string category, int preparationTime, string preparationMethod, int personAmount, string type, List<RecipeIngredient> recipeIngredients)
        {
            this.ID = id;
            this.Name = name;
            this.Category = category;
            this.PreparationTime = preparationTime;
            this.PreparationMethod = preparationMethod;
            this.PersonAmount = personAmount;
            this.Type = type;
            this.RecipeIngredients = recipeIngredients;
        }

        //for creating recipes
        public Recipe(string name, string category, int preparationTime, string preparationMethod, int personAmount, string type, List<RecipeIngredient> recipeIngredients)
        {
            this.Name = name;
            this.Category = category;
            this.PreparationTime = preparationTime;
            this.PreparationMethod = preparationMethod;
            this.PersonAmount = personAmount;
            this.Type = type;
            this.RecipeIngredients = recipeIngredients;
        }

        //for finding recipes
        public Recipe(string category, int preparationTime, int personAmount, string type, Ingredient ingredient)
        {
            this.Category = category;
            this.PreparationTime = preparationTime;
            this.PersonAmount = personAmount;
            this.Type = type;
            this.Ingredient = ingredient;
        }

        public Recipe(int id)
        {
            this.ID = id;
        }

        public Recipe(RecipeDTO recipeDTO)
        {
            this.ID = recipeDTO.ID;
            this.Name = recipeDTO.Name;
            this.Category = recipeDTO.Category;
            this.PreparationTime = recipeDTO.PreparationTime;
            this.PreparationMethod = recipeDTO.PreparationMethod;
            this.PersonAmount = recipeDTO.PersonAmount;
            this.Type = recipeDTO.Type;

            if (recipeDTO.IngredientDTO != null)
            {
                this.Ingredient = new Ingredient(recipeDTO.IngredientDTO);
            }
            this.RecipeIngredients = new List<RecipeIngredient>();

            if (recipeDTO.RecipeIngredientDTO != null)
            {
                foreach (RecipeIngredientDTO item in recipeDTO.RecipeIngredientDTO)
                {
                    RecipeIngredients.Add(new RecipeIngredient(item));
                }
            }
        }     
    }
}
