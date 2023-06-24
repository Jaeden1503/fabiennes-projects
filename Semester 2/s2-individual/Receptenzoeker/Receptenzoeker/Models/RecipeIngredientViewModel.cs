using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Receptenzoeker.Models
{
    public class RecipeIngredientViewModel
    {
        public int Quantity { get; set; }
        public string QuantityUnit { get; set; }

        public IngredientViewModel IngredientVM { get; set; }

        public RecipeIngredientViewModel()
        {

        }

        public RecipeIngredientViewModel(int quantity, string quantityUnit, IngredientViewModel ingredient)
        {
            this.Quantity = quantity;
            this.QuantityUnit = quantityUnit;
            this.IngredientVM = ingredient;
        }
    }
}
