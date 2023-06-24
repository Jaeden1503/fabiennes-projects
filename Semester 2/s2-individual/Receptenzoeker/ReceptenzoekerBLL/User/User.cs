using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ReceptenzoekerDAL;

namespace ReceptenzoekerBLL
{
    public class User
    {
        public int ID { get; private set; }
        public string Name { get; private set; }
        public string Password { get; private set; }
        public bool IsAdmin { get; private set; }
        public bool IsActive { get; private set; }

        public User(string name, string password)
        {
            this.Name = name;
            this.Password = password;
        }

        public User(int id, bool isactive)
        {
            this.ID = id;
            this.IsActive = isactive;
        }

        public User(UserDTO userDTO)
        {
            this.ID = userDTO.ID;
            this.Name = userDTO.Name;
            this.Password = userDTO.Password;
            this.IsAdmin = userDTO.IsAdmin;
            this.IsActive = userDTO.IsActive;
        }

        public User(int id)
        {
            this.ID = id;
        }
    }
}
