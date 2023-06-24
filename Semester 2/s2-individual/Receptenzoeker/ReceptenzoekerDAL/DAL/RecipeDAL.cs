using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReceptenzoekerDAL
{
    public class RecipeDAL : Database, IRecipeContainer
    {
        /// <summary>
        /// Getting all existing recipes
        /// </summary>
        /// <returns></returns>
        public List<RecipeDTO> GetAllRecipes()
        {
            try
            {
                Command = SqlCon.CreateCommand();
                //SELECT* FROM[MyTable] WHERE[id] > (SELECT MAX([id]) -5 FROM[MyTable])
                Command.CommandText = "SELECT * FROM Recipe";

                OpenConnection();
                Reader = Command.ExecuteReader();
                List<RecipeDTO> recipes = new List<RecipeDTO>();

                while (Reader.Read())
                {
                    RecipeDTO recipeDTO = new RecipeDTO(Convert.ToInt32(Reader["RecipeID"]), Reader["RecipeName"].ToString());
                    recipes.Add(recipeDTO);
                }
                CloseConnection();
                return recipes;
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex);
                CloseConnection();
                return null;
            }
        }

        /// <summary>
        /// Getting all details that belong to specific recipe id
        /// </summary>
        /// <param name="recipeDTO"></param>
        /// <returns></returns>
        public RecipeDTO GetRecipeDetailsByID(int id)
        {
            try
            {
                Command = SqlCon.CreateCommand();
                //select from recipe the selected recipe and show the details from that recipe
                Command.CommandText = "SELECT * FROM Recipe r JOIN RecipeIngredient ri ON (r.RecipeID = ri.RecipeID) " +
                    "JOIN Ingredient i ON (ri.IngredientID = i.IngredientID) WHERE r.RecipeID= @selectedRecipe";
                Command.Parameters.AddWithValue("@selectedRecipe", id);

                OpenConnection();
                Reader = Command.ExecuteReader();

                List<RecipeIngredientDTO> recipeIngredientDTOs = new List<RecipeIngredientDTO>();

                if (Reader.Read())
                {
                    RecipeDTO listrecipeDTO = new RecipeDTO((int)Reader["RecipeID"], (string)Reader["RecipeName"], (string)Reader["Category"], (int)Reader["PreparationTime"], (string)Reader["PreparationMethod"], (int)Reader["PersonAmount"], (string)Reader["TypeRecipe"], recipeIngredientDTOs);

                    do
                    {
                        recipeIngredientDTOs.Add(
                            new RecipeIngredientDTO((int)Reader["Quantity"], (string)Reader["QuantityUnit"], new IngredientDTO((int)Reader["IngredientID"], (string)Reader["Name"])));
                    }
                    while (Reader.Read());

                    CloseConnection();
                    return listrecipeDTO;
                }
                CloseConnection();
                return null;
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex);
                CloseConnection();
                return null;
            }
        }

        /// <summary>
        /// Creating recipe
        /// </summary>
        /// <param name="recipeDTO"></param>
        /// <returns></returns>
        public bool CreateRecipe(RecipeDTO recipeDTO)
        {
            try
            {
                Command = SqlCon.CreateCommand();
                Command.CommandText = "INSERT INTO Recipe (RecipeName, Category, PreparationTime, PreparationMethod, PersonAmount, TypeRecipe)" +
                    " VALUES (@name, @category, @prepTime, @prepMethod, @personAmount, @type); SELECT  @@IDENTITY id; ";

                Command.Parameters.AddWithValue("@name", recipeDTO.Name);
                Command.Parameters.AddWithValue("@category", recipeDTO.Category);
                Command.Parameters.AddWithValue("@prepTime", recipeDTO.PreparationTime);
                Command.Parameters.AddWithValue("@prepMethod", recipeDTO.PreparationMethod);
                Command.Parameters.AddWithValue("@personAmount", recipeDTO.PersonAmount);
                Command.Parameters.AddWithValue("@type", recipeDTO.Type);

                OpenConnection();

                Reader = Command.ExecuteReader();
                Reader.Read();
                int AddedId = Convert.ToInt32(Reader["id"]);

                Command.Dispose();
                Reader.Close();
                foreach (RecipeIngredientDTO item in recipeDTO.RecipeIngredientDTO)
                {
                    Command2 = SqlCon.CreateCommand();

                    Command2.CommandText = "INSERT INTO RecipeIngredient(RecipeID, IngredientID, Quantity, QuantityUnit) " +
                        "VALUES(@id, @ingredientid, @quantity, @unit);";
                    Command2.Parameters.AddWithValue("@id", AddedId);
                    Command2.Parameters.AddWithValue("@ingredientid", item.IngredientDTO.ID);
                    Command2.Parameters.AddWithValue("@quantity", item.Quantity);
                    Command2.Parameters.AddWithValue("@unit", item.QuantityUnit);

                    Command2.ExecuteNonQuery();
                }

                CloseConnection();
                return true;
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
                CloseConnection();
                return false;
            }
        }

        public bool UpdateRecipe(RecipeDTO recipeDTO)
        {
            try
            {
                List<IngredientDTO> ingredientDTOs = new List<IngredientDTO>();

                Command = SqlCon.CreateCommand();
                Command.CommandText = "UPDATE Recipe SET RecipeName = @name, Category = @category, PreparationTime = @prepTime, " +
                    "PreparationMethod = @prepMethod, PersonAmount = @personAmount, TypeRecipe = @type WHERE RecipeID = @id";

                Command.Parameters.AddWithValue("@id", recipeDTO.ID);
                Command.Parameters.AddWithValue("@name", recipeDTO.Name);
                Command.Parameters.AddWithValue("@category", recipeDTO.Category);
                Command.Parameters.AddWithValue("@prepTime", recipeDTO.PreparationTime);
                Command.Parameters.AddWithValue("@prepMethod", recipeDTO.PreparationMethod);
                Command.Parameters.AddWithValue("@personAmount", recipeDTO.PersonAmount);
                Command.Parameters.AddWithValue("@type", recipeDTO.Type);


                Command2 = SqlCon.CreateCommand();
                Command2.CommandText = "SELECT * FROM RecipeIngredient WHERE RecipeID = @id";
                Command2.Parameters.AddWithValue("@id", recipeDTO.ID);

                OpenConnection();
                Command.ExecuteNonQuery();
                Command.Dispose();

                Reader = Command2.ExecuteReader();

                while (Reader.Read())
                {
                    ingredientDTOs.Add(new IngredientDTO((int)Reader["IngredientID"]));
                }
                Reader.Close();

                for (int i = 0; i < ingredientDTOs.Count; i++)
                {
                    Command3 = SqlCon.CreateCommand();

                    Command3.CommandText = "UPDATE RecipeIngredient SET IngredientID = @ingredientid, Quantity = @quantity, QuantityUnit = @quantityunit WHERE RecipeID = @id AND IngredientID = @oldIngredientid";

                    Command3.Parameters.AddWithValue("@oldIngredientid", ingredientDTOs[i].ID);

                    Command3.Parameters.AddWithValue("@id", recipeDTO.ID);
                    Command3.Parameters.AddWithValue("@ingredientid", recipeDTO.RecipeIngredientDTO[i].IngredientDTO.ID);
                    Command3.Parameters.AddWithValue("@quantity", recipeDTO.RecipeIngredientDTO[i].Quantity);
                    Command3.Parameters.AddWithValue("@quantityunit", recipeDTO.RecipeIngredientDTO[i].QuantityUnit);

                    Command3.ExecuteNonQuery();
                    Command3.Dispose();
                }
                CloseConnection();
                return true;
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
                CloseConnection();
                return false;
            }
        }

        /// <summary>
        /// Deleting specific recipe by id
        /// </summary>
        /// <param name="recipeDTO"></param>
        /// <returns></returns>
        public bool DeleteRecipe(int id)
        {
            try
            {
                Command = SqlCon.CreateCommand();

                Command.CommandText = "DELETE FROM RecipeIngredient WHERE RecipeID = @recipeID; DELETE FROM Review WHERE RecipeID = @recipeID; DELETE FROM Recipe WHERE RecipeID = @recipeID;";
                Command.Parameters.AddWithValue("@recipeID", id);

                OpenConnection();

                Command.ExecuteNonQuery();

                CloseConnection();
                return true;
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
                CloseConnection();
                return false;
            }
        }

        public List<RecipeDTO> FindRecipe(RecipeDTO recipeDTO)
        {
            try
            {
                Command = SqlCon.CreateCommand();
                Command.CommandText = "SELECT r.RecipeID, RecipeName FROM Recipe r JOIN RecipeIngredient ri ON " +
                    "(r.RecipeID = ri.RecipeID) JOIN Ingredient i ON (ri.IngredientID = i.IngredientID) WHERE Category = @category " +
                    "AND PreparationTime = @preptime AND PersonAmount = @personamount AND TypeRecipe = @type AND i.Name = @ingredientname";

                Command.Parameters.AddWithValue("@category", recipeDTO.Category);
                Command.Parameters.AddWithValue("@preptime", recipeDTO.PreparationTime);
                Command.Parameters.AddWithValue("@personamount", recipeDTO.PersonAmount);
                Command.Parameters.AddWithValue("@type", recipeDTO.Type);
                Command.Parameters.AddWithValue("@ingredientname", recipeDTO.IngredientDTO.IngredientName);

                OpenConnection();

                Reader = Command.ExecuteReader();
                List<RecipeDTO> recipes = new List<RecipeDTO>();

                while (Reader.Read())
                {
                    RecipeDTO recipeDTOs = new RecipeDTO(Convert.ToInt32(Reader["RecipeID"]), Reader["RecipeName"].ToString());
                    recipes.Add(recipeDTOs);
                }

                CloseConnection();
                return recipes;
            }
            catch (Exception ex)
            {
                Console.Write(ex.Message);
                CloseConnection();
                return null;
            }
        }

        public List<RecipeDTO> RecipesByCategory(string category)
        {
            try
            {
                Command = SqlCon.CreateCommand();
                Command.CommandText = "SELECT RecipeID, RecipeName FROM Recipe WHERE Category = @category";
                Command.Parameters.AddWithValue("@category", category);

                OpenConnection();

                Reader = Command.ExecuteReader();
                List<RecipeDTO> recipes = new List<RecipeDTO>();

                while(Reader.Read())
                {
                    RecipeDTO recipeDTO = new RecipeDTO(Convert.ToInt32(Reader["RecipeID"]), Reader["RecipeName"].ToString());
                    recipes.Add(recipeDTO);
                }
                CloseConnection();
                return recipes;
            }
            catch(Exception ex)
            {
                Console.WriteLine(ex.Message);
                CloseConnection();
                return null;
            }
        }

        public List<RecipeDTO> RecipesByType(string type)
        {
            try
            {
                Command = SqlCon.CreateCommand();
                Command.CommandText = "SELECT RecipeID, RecipeName FROM Recipe WHERE TypeRecipe = @type";
                Command.Parameters.AddWithValue("@type", type);

                OpenConnection();

                Reader = Command.ExecuteReader();
                List<RecipeDTO> recipes = new List<RecipeDTO>();

                while (Reader.Read())
                {
                    RecipeDTO recipeDTO = new RecipeDTO(Convert.ToInt32(Reader["RecipeID"]), Reader["RecipeName"].ToString());
                    recipes.Add(recipeDTO);
                }
                CloseConnection();
                return recipes;
            }
            catch(Exception ex)
            {
                Console.WriteLine(ex.Message);
                CloseConnection();
                return null;
            }
        }

        public List<RecipeDTO> RecipesByTime(int time)
        {
            try
            {
                Command = SqlCon.CreateCommand();
                Command.CommandText = "SELECT RecipeID, RecipeName FROM Recipe WHERE PreparationTime <= @time";
                Command.Parameters.AddWithValue("@time", time);

                OpenConnection();

                Reader = Command.ExecuteReader();
                List<RecipeDTO> recipes = new List<RecipeDTO>();

                while (Reader.Read())
                {
                    RecipeDTO recipeDTO = new RecipeDTO(Convert.ToInt32(Reader["RecipeID"]), Reader["RecipeName"].ToString());
                    recipes.Add(recipeDTO);
                }
                CloseConnection();
                return recipes;
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
                CloseConnection();
                return null;
            }
        }
    }
}
