using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReceptenzoekerDAL
{
    public class UserDAL : Database, IUserContainer
    {
        public UserDTO GetUserLogin(UserDTO userDTO)
        {
            try
            {
                Command = SqlCon.CreateCommand();

                Command.CommandText = "SELECT * FROM [User] WHERE Name=@username AND Password=@password";
                Command.Parameters.AddWithValue("@username", userDTO.Name);
                Command.Parameters.AddWithValue("@password", userDTO.Password);     

                OpenConnection();

                Reader = Command.ExecuteReader();                

                if (Reader.HasRows)
                {
                    while (Reader.Read())
                    {
                        userDTO =  new UserDTO(Convert.ToInt32(Reader["UserID"]), Reader["Name"].ToString(), Reader["Password"].ToString(), Convert.ToBoolean(Reader["IsAdmin"]), Convert.ToBoolean(Reader["IsActive"]));                 
                    }

                    Command.Dispose();
                    CloseConnection();
                    return userDTO;
                }
                else
                {
                    Command.Dispose();
                    CloseConnection();
                    return userDTO = new UserDTO(0, "", "", false, false);
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
                CloseConnection();
                return null;  
            }
        }

        /// <summary>
        /// Gets only the users not the admin(s)
        /// </summary>
        /// <returns></returns>
        public List<UserDTO> GetAllUsers()
        {
            try
            {
                Command = SqlCon.CreateCommand();
                Command.CommandText = "SELECT * FROM [User] WHERE IsAdmin=0";

                OpenConnection();

                Reader = Command.ExecuteReader();
                List<UserDTO> userDTOs = new List<UserDTO>();

                while (Reader.Read())
                {
                    UserDTO userDTO = new UserDTO(Convert.ToInt32(Reader["UserID"]), Reader["Name"].ToString(), Reader.GetBoolean(Reader.GetOrdinal("IsActive")));                  
                    userDTOs.Add(userDTO);
                }

                CloseConnection();
                return userDTOs;
            }
            catch(Exception ex)
            {
                Console.WriteLine(ex.Message);
                CloseConnection();
                return null;
            }
        }

        public bool UpdateUserStatus(UserDTO userDTO)
        {
            try
            {
                Command = SqlCon.CreateCommand();               
                Command.CommandText = "UPDATE [User] SET IsActive=@isactive WHERE UserID = @id";
                Command.Parameters.AddWithValue("@id", userDTO.ID);
                Command.Parameters.AddWithValue("@isactive", userDTO.IsActive);

                OpenConnection();
                Command.ExecuteNonQuery();
                Command.Dispose();

                CloseConnection();
                return true;
            }
            catch(Exception ex)
            {
                Console.WriteLine(ex.Message);
                CloseConnection();
                return false;
            }
        }
    }
}
