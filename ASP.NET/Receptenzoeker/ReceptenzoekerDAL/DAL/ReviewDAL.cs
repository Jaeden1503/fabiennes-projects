using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReceptenzoekerDAL
{
    public class ReviewDAL : Database, IReviewContainer
    {
        public List<ReviewDTO> GetAllReviews()
        {
            try
            {
                ReviewDTO reviewDTO = null;
                Command = SqlCon.CreateCommand();
                Command.CommandText = "SELECT ReviewID, r.RecipeID, re.RecipeName, Title, [Description], u.Name FROM Review r JOIN [User] u ON (r.UserID = u.UserID) JOIN Recipe re ON (r.RecipeID = re.RecipeID)";

                OpenConnection();
                Reader = Command.ExecuteReader();
                List<ReviewDTO> reviews = new List<ReviewDTO>();

                while (Reader.Read())
                {
                    reviewDTO = new ReviewDTO(Convert.ToInt32(Reader["ReviewID"]), Reader["Title"].ToString(), Reader["Description"].ToString(), new UserDTO(Reader["Name"].ToString()), new RecipeDTO(Convert.ToInt32(Reader["RecipeID"]), Reader["RecipeName"].ToString()));
                    reviews.Add(reviewDTO);
                }
                CloseConnection();
                return reviews;
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex);
                CloseConnection();
                return null;
            }
        }


        public List<ReviewDTO> GetReviewsByID(int id)
        {
            try
            {
                ReviewDTO reviewDTO = null;
                Command = SqlCon.CreateCommand();
                Command.CommandText = "SELECT ReviewID, Title, [Description], r.UserID, u.Name FROM Review r JOIN [User] u ON (r.UserID = u.UserID) WHERE RecipeID = @id";
                Command.Parameters.AddWithValue("@id", id);

                OpenConnection();
                Reader = Command.ExecuteReader();
                List<ReviewDTO> reviews = new List<ReviewDTO>();

                while (Reader.Read())
                {
                    reviewDTO = new ReviewDTO(Convert.ToInt32(Reader["ReviewID"]), Reader["Title"].ToString(), Reader["Description"].ToString(), new UserDTO(Reader["Name"].ToString()));
                    reviews.Add(reviewDTO);
                }
                CloseConnection();
                return reviews;
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex);
                CloseConnection();
                return null;
            }
        }

        public bool Addreview(ReviewDTO reviewDTO)
        {
            try
            {
                Command = SqlCon.CreateCommand();

                Command.CommandText = "INSERT INTO Review (RecipeID, Title, Description, UserID) VALUES (@id, @title, @description, @userid)";
                Command.Parameters.AddWithValue("@id", reviewDTO.RecipeDTO.ID);
                Command.Parameters.AddWithValue("@title", reviewDTO.Title);
                Command.Parameters.AddWithValue("@description", reviewDTO.Description);
                Command.Parameters.AddWithValue("@userid", reviewDTO.UserDTO.ID);

                OpenConnection();

                Command.ExecuteNonQuery();
                Command.Dispose();

                CloseConnection();
                return true;
            }
            catch(Exception ex)
            {
                Console.WriteLine(ex);
                CloseConnection();
                return false;
            }
        }

        public bool DeleteReviewByID(int id)
        {
            try
            {
                Command = SqlCon.CreateCommand();

                Command.CommandText = "DELETE FROM Review WHERE ReviewID=@reviewID";
                Command.Parameters.AddWithValue("@reviewID", id);

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
