using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ReceptenzoekerDAL;

namespace ReceptenzoekerBLL
{
    public class Ingredient
    {
        public int ID { get; private set; }
        public string IngredientName { get; private set; }

        public Ingredient(string name)
        {
            this.IngredientName = name;
        }

        public Ingredient(int id)
        {
            this.ID = id;
        }

        public Ingredient(int id, string name)
        {
            this.ID = id;
            this.IngredientName = name;
        }

        public Ingredient(IngredientDTO ingredientDTO)
        {
            this.ID = ingredientDTO.ID;
            this.IngredientName = ingredientDTO.IngredientName;
        }
    }
}
