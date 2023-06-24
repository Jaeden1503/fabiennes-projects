using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ReceptenzoekerDAL;

namespace ReceptenzoekerBLL
{
    public class RecipeIngredient
    {
        public int Quantity { get; private set; }
        public string QuantityUnit { get; private set; }

        public Ingredient Ingredient { get; private set; }

        public RecipeIngredient(int quantity, string quantityUnit, Ingredient ingredient)
        {
            this.Quantity = quantity;
            this.QuantityUnit = quantityUnit;
            this.Ingredient = ingredient;
        }

        public RecipeIngredient(RecipeIngredientDTO recipeIngredientDTO)
        {
            this.Quantity = recipeIngredientDTO.Quantity;
            this.QuantityUnit = recipeIngredientDTO.QuantityUnit;
            this.Ingredient = new Ingredient(recipeIngredientDTO.IngredientDTO);
        }
    }
}
