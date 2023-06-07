using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReceptenzoekerDAL
{
    public class IngredientDAL : Database, IIngredientContainer
    {
        public IngredientDTO GetIngredientByID(int id)
        {
            try
            {
                Command = SqlCon.CreateCommand();

                Command.CommandText = "SELECT * FROM Ingredient WHERE IngredientID = @ID";
                Command.Parameters.AddWithValue("@ID", id);

                OpenConnection();

                Reader = Command.ExecuteReader();

                if (Reader.Read())
                {
                    IngredientDTO ingredientDTO = new IngredientDTO((int)Reader["IngredientID"], (string)Reader["Name"]);

                    CloseConnection();
                    return ingredientDTO;
                }
                CloseConnection();
                return null;
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex);
                return null;
            }
        }

        public List<IngredientDTO> GetAllIngredients()
        {
            try
            {
                Command = SqlCon.CreateCommand();
                Command.CommandText = "SELECT * FROM Ingredient";

                OpenConnection();

                Reader = Command.ExecuteReader();
                List<IngredientDTO> allIngredients = new List<IngredientDTO>();

                while (Reader.Read())
                {
                    IngredientDTO ingredient = new IngredientDTO((int)Reader["IngredientID"], Reader["Name"].ToString());
                    allIngredients.Add(ingredient);
                }

                CloseConnection();
                return allIngredients;
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex);
                CloseConnection();
                return null;
            }
        }

        public bool AddIngredient(IngredientDTO ingredientDTO)
        {
            try
            {
                Command = SqlCon.CreateCommand();

                //add the input data to the column 'Name'
                Command.CommandText = "INSERT INTO Ingredient (Name) VALUES (@ingredientName)";
                Command.Parameters.AddWithValue("@ingredientName", ingredientDTO.IngredientName);

                OpenConnection();

                Command.ExecuteNonQuery();
                Command.Dispose();

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
        /// Method checks in the validity of ingredient the user wants to add
        /// </summary>
        /// <param name="ingredientDTO"></param>
        /// <returns></returns>
        public bool CheckIngredientExistance(IngredientDTO ingredientDTO)
        {
            try
            {
                Command = SqlCon.CreateCommand();
                Command.CommandText = "SELECT * FROM Ingredient WHERE Name=@ingredientName";
                Command.Parameters.AddWithValue("@ingredientName", ingredientDTO.IngredientName);

                OpenConnection();

                Reader = Command.ExecuteReader();
                //checks if ingredient already exists in database, if true (exists) don't add
                if (Reader.HasRows)
                {
                    Command.Dispose();
                    CloseConnection();
                    return false;
                }
                else
                {
                    Command.Dispose();
                    CloseConnection();
                    return true;
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex);
                CloseConnection();
                return true;
            }
        }

        public bool EditIngredientByID(IngredientDTO ingredientDTO)
        {
            try
            {
                Command = SqlCon.CreateCommand();
                Command.CommandText = "UPDATE Ingredient SET Name = @name WHERE IngredientID = @ID";
                Command.Parameters.AddWithValue("@name", ingredientDTO.IngredientName);
                Command.Parameters.AddWithValue("@ID", ingredientDTO.ID);

                OpenConnection();

                Command.ExecuteNonQuery();
                Command.Dispose();

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

        public bool DeleteIngredientByID(int id)
        {
            try
            {
                Command = SqlCon.CreateCommand();

                Command.CommandText = "DELETE FROM Ingredient WHERE IngredientID=@ingredientID";
                Command.Parameters.AddWithValue("@ingredientID", id);

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
    }
}
