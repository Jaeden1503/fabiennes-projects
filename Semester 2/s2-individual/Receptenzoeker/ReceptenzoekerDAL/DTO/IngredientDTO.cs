using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReceptenzoekerDAL
{
    public class IngredientDTO
    {
        public int ID { get; private set; }
        public string IngredientName { get; private set; }

        public IngredientDTO(string name)
        {
            this.IngredientName = name;
        }

        public IngredientDTO(int id)
        {
            this.ID = id;
        }

        public IngredientDTO(int id, string name)
        {
            this.ID = id;
            this.IngredientName = name;
        }
    }
}
