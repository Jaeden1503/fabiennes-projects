using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReceptenzoekerDAL
{
    public interface IIngredientContainer
    {
        IngredientDTO GetIngredientByID(int id);
        List<IngredientDTO> GetAllIngredients();
        bool AddIngredient(IngredientDTO ingredientDTO);
        bool CheckIngredientExistance(IngredientDTO ingredientDTO);
        bool EditIngredientByID(IngredientDTO ingredientDTO);
        bool DeleteIngredientByID(int id);
    }
}