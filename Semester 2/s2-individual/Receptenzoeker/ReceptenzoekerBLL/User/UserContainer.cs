using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ReceptenzoekerDAL;

namespace ReceptenzoekerBLL
{
    public class UserContainer
    {
        private IUserContainer iuserContainer;

        public UserContainer(IUserContainer iUserContainer)
        {
            this.iuserContainer = iUserContainer;
        }

        public User GetUserLogin(User user)
        {            
            return new User(iuserContainer.GetUserLogin(ConvertToDTO(user)));
        }

        public List<User> GetAllUsers()
        {
            List<User> users = new List<User>();
            List<UserDTO> userDTOs = iuserContainer.GetAllUsers();

            foreach (UserDTO item in userDTOs)
            {
                users.Add(new User(item));
            }
            return users;
        }

        public bool UpdateUserStatus(User user)
        {
            return iuserContainer.UpdateUserStatus(ConvertToDTOUpdate(user));
        }

        public int UserCount()
        {
            List<UserDTO> userDTOs = iuserContainer.GetAllUsers();
            return userDTOs.Count;
        }

        ////////// METHODS FOR CONVERTING TO DTO /////////

        public UserDTO ConvertToDTO(User user)
        {
            return new UserDTO(user.Name, user.Password);
        }

        public UserDTO ConvertToDTOUpdate(User user)
        {
            return new UserDTO(user.ID, user.IsActive);
        }
    }
}
