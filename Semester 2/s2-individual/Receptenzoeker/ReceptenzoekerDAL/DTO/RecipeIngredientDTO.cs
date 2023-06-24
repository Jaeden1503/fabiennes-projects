using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReceptenzoekerDAL
{
    public class RecipeIngredientDTO
    {
        public int Quantity { get; private set; }
        public string QuantityUnit { get; private set; }

        public IngredientDTO IngredientDTO { get; private set; }

        public RecipeIngredientDTO(int quantity, string quantityUnit, IngredientDTO ingredientDTO)
        {
            this.Quantity = quantity;
            this.QuantityUnit = quantityUnit;
            this.IngredientDTO = ingredientDTO;
        }
    }
}
