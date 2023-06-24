using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Receptenzoeker.Models
{
    public class UserViewModel
    {
        [DisplayName("ID:")]
        public int ID { get; set; }

        [DisplayName("Gebruikersnaam:")]
        [Required(AllowEmptyStrings = false, ErrorMessage = "Geen naam ingegeven")]
        public string UserName { get; set; }

        [Required(AllowEmptyStrings = false, ErrorMessage = "Geen wachtwoord ingegeven")]
        public string Password { get; set; }
        public bool IsAdmin { get; set; }

        [DisplayName("Account status:")]
        public bool IsActive { get; set; }


        public UserViewModel(string name)
        {
            this.UserName = name;
        }

        public UserViewModel()
        {

        }

        public UserViewModel(int id, string name, bool isactive)
        {
            this.ID = id;
            this.UserName = name;
            this.IsActive = isactive;
        }
    }
}
