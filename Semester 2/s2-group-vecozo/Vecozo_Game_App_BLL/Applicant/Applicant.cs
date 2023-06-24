using System;
using System.Collections.Generic;
using System.Text;

namespace Vecozo_Game_App_BLL
{
    public class Applicant
    {
        public string Name { get; private set; }
        public string Email { get; private set; }

        public Applicant(string name, string email)
        {
            this.Name = name;
            this.Email = email;
        }

    }
}
