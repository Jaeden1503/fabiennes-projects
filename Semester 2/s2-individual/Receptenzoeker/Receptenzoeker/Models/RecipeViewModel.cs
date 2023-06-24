using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Receptenzoeker.Models
{
    public class RecipeViewModel
    {
        [DisplayName("Recept ID:")]
        public int ID { get; set; }

        [DisplayName("Recept naam:")]
        [Required(AllowEmptyStrings = false, ErrorMessage = "Geen naam ingegeven")]
        public string Name { get; set; }

        [DisplayName("Categorie:")]
        [Required(AllowEmptyStrings = false, ErrorMessage = "Geen categorie ingegeven")]
        public string Category { get; set; }

        [DisplayName("Bereidingstijd:")]
        [Required(AllowEmptyStrings = false, ErrorMessage = "Geen bereidingstijd ingegeven")]
        public int PreparationTime { get; set; }

        [DisplayName("Bereidingswijze:")]
        [Required(AllowEmptyStrings = false, ErrorMessage = "Geen bereidingswijze ingegeven")]
        public string PreparationMethod { get; set; }

        [DisplayName("Aantal personen:")]
        [Required(AllowEmptyStrings = false, ErrorMessage = "Geen aantal personen ingegeven")]
        public int PersonAmount { get; set; }

        [DisplayName("Type:")]
        [Required(AllowEmptyStrings = false, ErrorMessage = "Geen type ingegeven")]
        public string Type { get; set; }

        [DisplayName("Ingrediënten:")]
        [Required(AllowEmptyStrings = false, ErrorMessage = "Geen ingrediënten ingegeven")]
        public List<RecipeIngredientViewModel> RecipeIngredientVMs { get; set; }

        [DisplayName("Review(s):")]
        public List<ReviewViewModel> ReviewViewModels { get; set; }

        public List<IngredientViewModel> IngredientViewModels { get; set; }
      
        public IngredientViewModel IngredientViewModel { get; set; }

        public string image { get; set; }

        //voor recept details ophalen (admin side)
        public RecipeViewModel(int id, string name, string category, int preparationTime, string preparationMethod, int personAmount, string type, List<RecipeIngredientViewModel> recipeIngredientViewModel)
        {
            this.ID = id;
            this.Name = name;
            this.Category = category;
            this.PreparationTime = preparationTime;
            this.PreparationMethod = preparationMethod;
            this.PersonAmount = personAmount;
            this.Type = type;
            this.RecipeIngredientVMs = recipeIngredientViewModel;
        }

        //voor details met reviews ophalen (user side)
        public RecipeViewModel(int id, string name, string category, int preparationTime, string preparationMethod, int personAmount, string type, List<RecipeIngredientViewModel> recipeIngredientViewModel, List<ReviewViewModel> reviewViewModels)
        {
            this.ID = id;
            this.Name = name;
            this.Category = category;
            this.PreparationTime = preparationTime;
            this.PreparationMethod = preparationMethod;
            this.PersonAmount = personAmount;
            this.Type = type;
            this.RecipeIngredientVMs = recipeIngredientViewModel;
            this.ReviewViewModels = reviewViewModels;
        }

        //voor updaten
        public RecipeViewModel(int id, string name, string category, int preparationTime, string preparationMethod, int personAmount, string type, List<RecipeIngredientViewModel> recipeIngredientViewModel, List<IngredientViewModel> ingredientViewModels)
        {
            this.ID = id;
            this.Name = name;
            this.Category = category;
            this.PreparationTime = preparationTime;
            this.PreparationMethod = preparationMethod;
            this.PersonAmount = personAmount;
            this.Type = type;
            this.RecipeIngredientVMs = recipeIngredientViewModel;
            this.IngredientViewModels = ingredientViewModels;
        }

        //all recipes (admin)
        public RecipeViewModel(int id, string name)
        {
            this.ID = id;
            this.Name = name;
        }

        //recipes + image (user)
        public RecipeViewModel(int id, string name, string image)
        {
            this.ID = id;
            this.Name = name;
            this.image = image;
        }

        public RecipeViewModel(int id)
        {
            this.ID = id;
        }

        public RecipeViewModel()
        {

        }
    }
}
