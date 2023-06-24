using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReceptenzoekerDAL
{
    public class TestIngredientDAL : IIngredientContainer
    {
        //fake list with existing ingredients.
        List<IngredientDTO> fakeIngredients = new List<IngredientDTO>()
        {
            new IngredientDTO(1, "boter"),
            new IngredientDTO(2, "kaas"),
            new IngredientDTO(3, "ei"),
            new IngredientDTO(4, "mosterd")
        };

        public IngredientDTO GetIngredientByID(int id)
        {
            foreach (IngredientDTO item in fakeIngredients)
            {
                if (item.ID == id)
                {
                    return item;
                }
            }
            return null;
        }

        public List<IngredientDTO> GetAllIngredients()
        {
            return fakeIngredients;
        }

        public bool AddIngredient(IngredientDTO ingredientDTO)
        {
            fakeIngredients.Add(ingredientDTO);
            return true;
        }

        //needs to check if ingredient exists and isnt double/already in the list
        public bool CheckIngredientExistance(IngredientDTO ingredientDTO)
        {
            for (int i = 0; i < fakeIngredients.Count; i++)
            {
                if (ingredientDTO.IngredientName == fakeIngredients[i].IngredientName)
                {
                    return false;
                }
            }
            return true;
        }

        public bool EditIngredientByID(IngredientDTO ingredientDTO)
        {
            for (int i = 0; i < fakeIngredients.Count; i++)
            {
                if (fakeIngredients[i].ID == ingredientDTO.ID)
                {
                    fakeIngredients[i] = ingredientDTO;
                    return true;
                }
            }
            return false;
        }

        public bool DeleteIngredientByID(int id)
        {
            fakeIngredients.RemoveAt(id - 1);
            return true;
        }
    }
}
