using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ReceptenzoekerDAL;

namespace ReceptenzoekerBLL
{
    public class IngredientContainer
    {
        private IIngredientContainer iigredientContainer;

        public IngredientContainer(IIngredientContainer iIngredientContainer)
        {
            this.iigredientContainer = iIngredientContainer;
        }


        public Ingredient GetIngredient(int id)
        {
            return new Ingredient(iigredientContainer.GetIngredientByID(id));
        }

        public List<Ingredient> GetAllIngredients()
        {
            List<Ingredient> ingredients = new List<Ingredient>();
            List<IngredientDTO> ingredientDTOs = iigredientContainer.GetAllIngredients();

            foreach (IngredientDTO ingredientDTO in ingredientDTOs)
            {
                ingredients.Add(new Ingredient(ingredientDTO));
            }
            return ingredients;
        }

        public int IngredientCount()
        {
            List<IngredientDTO> ingredientDTOs = iigredientContainer.GetAllIngredients();
            return ingredientDTOs.Count;
        }


        public bool AddIngredient(Ingredient ingredient)
        {
            return iigredientContainer.AddIngredient(ConvertToDTOName(ingredient));
        }

        public bool CheckIngredientValidity(Ingredient ingredient)
        {
            return iigredientContainer.CheckIngredientExistance(ConvertToDTOName(ingredient));
        }

        public bool EditIngredient(Ingredient ingredient)
        {
            return iigredientContainer.EditIngredientByID(ConvertToDTO(ingredient));
        }

        public bool DeleteIngredient(int id)
        {
            return iigredientContainer.DeleteIngredientByID(id);
        }    

        ////////// METHODS FOR CONVERTING TO DTO /////////

        public IngredientDTO ConvertToDTOName(Ingredient ingredient)
        {
            return new IngredientDTO(ingredient.IngredientName);
        }

        public IngredientDTO ConvertToDTO(Ingredient ingredient)
        {
            return new IngredientDTO(ingredient.ID, ingredient.IngredientName);
        }
    }
}
