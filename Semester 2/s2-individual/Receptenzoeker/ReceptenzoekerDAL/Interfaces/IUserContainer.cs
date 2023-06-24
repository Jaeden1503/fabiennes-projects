using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReceptenzoekerDAL
{
    public interface IUserContainer
    {
        UserDTO GetUserLogin(UserDTO userDTO);
        List<UserDTO> GetAllUsers();
        bool UpdateUserStatus(UserDTO userDTO);
    }
}
