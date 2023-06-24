using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Receptenzoeker.Models
{
    public class ReviewViewModel
    {
        public int ID { get; set; }

        [DisplayName("Titel:")]
        [Required(AllowEmptyStrings = false, ErrorMessage = "Geen titel ingegeven")]
        public string Title { get; set; }

        [DisplayName("Beschrijving:")]
        [Required(AllowEmptyStrings = false, ErrorMessage = "Geen beschrijving ingegeven")]
        public string Description { get; set; }

        public RecipeViewModel RecipeViewModel { get; set; }

        public UserViewModel UserViewModel { get; set; }
        

        public ReviewViewModel(int id, string title, string description, UserViewModel userViewModel)
        {
            this.ID = id;
            this.Title = title;
            this.Description = description;
            this.UserViewModel = userViewModel;
        }

        public ReviewViewModel(RecipeViewModel recipeViewModel)
        {
            this.RecipeViewModel = recipeViewModel;
        }

        public ReviewViewModel(int id, string title, string description, UserViewModel userViewModel, RecipeViewModel recipeViewModel)
        {
            this.ID = id;            
            this.Title = title;
            this.Description = description;
            this.UserViewModel = userViewModel;
            this.RecipeViewModel = recipeViewModel;
        }

        public ReviewViewModel()
        {

        }
    }
}
