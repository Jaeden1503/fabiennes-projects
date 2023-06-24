using System;
using System.Collections.Generic;
using System.Text;

namespace Vecozo_Game_app_DAL
{
    public class ApplicantDTO
    {
        public string Name { get; private set; }
        public string Email { get; private set; }

        public ApplicantDTO(string name, string email)
        {
            this.Name = name;
            this.Email = email;
        }
    }
}
