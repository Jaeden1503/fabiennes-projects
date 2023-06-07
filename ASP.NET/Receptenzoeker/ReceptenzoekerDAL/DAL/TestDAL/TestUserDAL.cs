using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReceptenzoekerDAL
{
    public class TestUserDAL : IUserContainer
    {
        List<UserDTO> fakeUsers = new List<UserDTO>()
        {
            new UserDTO(1, "piet", "pietje", false, true),
            new UserDTO(2, "jan", "jansen", false, false),
            new UserDTO(3, "alex", "alexander", true, true)
        };

        public UserDTO GetUserLogin(UserDTO userDTO)
        {
            foreach (UserDTO item in fakeUsers)
            {
                if(item.Name == userDTO.Name && item.Password == userDTO.Password)
                {
                    userDTO = new UserDTO(item.ID, userDTO.Name, userDTO.Password, item.IsAdmin, item.IsActive);
                    return userDTO;
                }
            }
            userDTO = new UserDTO(0, "", "", false, false);
            return userDTO;
        }

        public List<UserDTO> GetAllUsers()
        {
            List<UserDTO> users = new List<UserDTO>();

            foreach (UserDTO item in fakeUsers)
            {
                if (!item.IsAdmin)
                {
                    users.Add(item);
                }
            }
            return users;
        }

        public bool UpdateUserStatus(UserDTO userDTO)
        {
            fakeUsers[userDTO.ID - 1] = userDTO;
            return true;       
        }
    }
}
