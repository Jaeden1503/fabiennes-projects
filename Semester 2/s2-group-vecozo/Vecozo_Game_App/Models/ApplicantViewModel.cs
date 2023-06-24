using Vecozo_Game_App_BLL;

namespace Vecozo_Game_App.Models
{
    public class ApplicantViewModel
    {
        public string Name { get; set; }
        public string Email { get; set; }
        public bool Validation { get; set; }
        public string GivenAnswer { get; set; }
        public string Answer { get; set; }
        public ApplicantViewModel(string name, string email, bool validation, string givenanswer, string answer)
        {
            this.Name = name;
            this.Email = email;
            this.Validation = validation;
            this.GivenAnswer = givenanswer;
            this.Answer = answer;
        }
    }
}
