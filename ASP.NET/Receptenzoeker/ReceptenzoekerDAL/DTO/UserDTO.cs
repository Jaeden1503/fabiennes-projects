using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReceptenzoekerDAL
{
    public class UserDTO
    {
        public int ID { get; private set; }
        public string Name { get; private set; }
        public string Password { get; private set; }
        public bool IsAdmin { get; private set; }
        public bool IsActive { get; private set; }

        public UserDTO(string name, string password)
        {
            this.Name = name;
            this.Password = password;
        }

        public UserDTO(int id, string name, string password, bool isadmin, bool isactive)
        {
            this.ID = id;
            this.Name = name;
            this.Password = password;
            this.IsAdmin = isadmin;
            this.IsActive = isactive;
        }

        public UserDTO(int id, string name, bool isactive)
        {
            this.ID = id;
            this.Name = name;
            this.IsActive = isactive;
        }

        public UserDTO(int id, bool isactive)
        {
            this.ID = id;
            this.IsActive = isactive;
        }

        public UserDTO(int id)
        {
            this.ID = id;
        }

        public UserDTO(string name)
        {
            this.Name = name;
        }
    }
}
