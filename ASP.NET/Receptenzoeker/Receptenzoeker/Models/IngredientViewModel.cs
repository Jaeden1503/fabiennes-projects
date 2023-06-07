using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Receptenzoeker.Models
{
    public class IngredientViewModel
    {
        [DisplayName("ID:")]
        public int ID { get; set; }

        [Required(AllowEmptyStrings = false, ErrorMessage = "Geen ingrediënt ingevuld")]
        [DisplayName("Ingrediënt(en):")]
        public string IngredientName { get; set; }

        public IngredientViewModel(string name)
        {
            this.IngredientName = name;
        }

        public IngredientViewModel(int id, string name)
        {
            this.ID = id;
            this.IngredientName = name;
        }

        public IngredientViewModel()
        {

        }
    }
}
