using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReceptenzoekerDAL
{
    public class RecipeDTO
    {
        public int ID { get; private set; }
        public string Name { get; private set; }
        public string Category { get; private set; }
        public int PreparationTime { get; private set; }
        public string PreparationMethod { get; private set; }
        public int PersonAmount { get; private set; }
        public string Type { get; private set; }

        public List<RecipeIngredientDTO> RecipeIngredientDTO { get; private set; }
        public IngredientDTO IngredientDTO { get; private set; }

        public RecipeDTO(int id, string name, string category, int preparationTime, string preparationMethod, int personAmount, string type, List<RecipeIngredientDTO> recipeIngredientDTO)
        {
            this.ID = id;
            this.Name = name;
            this.Category = category;
            this.PreparationTime = preparationTime;
            this.PreparationMethod = preparationMethod;
            this.PersonAmount = personAmount;
            this.Type = type;
            this.RecipeIngredientDTO = recipeIngredientDTO;
        }

        //converting for finding recipes
        public RecipeDTO(string category, int preparationTime, int personAmount, string type, IngredientDTO ingredientDTO)
        {     
            this.Category = category;
            this.PreparationTime = preparationTime;
            this.PersonAmount = personAmount;
            this.Type = type;
            this.IngredientDTO = ingredientDTO;
        }

        public RecipeDTO(int id, string name)
        {
            this.ID = id;
            this.Name = name;
        }

        public RecipeDTO(int id)
        {
            this.ID = id;
        }
    }
}
