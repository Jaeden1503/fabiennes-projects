using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Receptenzoeker.Models
{
    public class DashboardViewModel
    {
        public int UserAmount { get; set; }
        public int RecipeAmount { get; set; }
        public int IngredientAmount { get; set; }
        public int ReviewAmount { get; set; }

        public DashboardViewModel(int user, int recipe, int ingredient, int review)
        {
            this.UserAmount = user;
            this.RecipeAmount = recipe;
            this.IngredientAmount = ingredient;
            this.ReviewAmount = review;
        }
    }
}
