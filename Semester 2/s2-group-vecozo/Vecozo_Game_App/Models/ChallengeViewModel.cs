using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Vecozo_Game_App_BLL;

namespace Vecozo_Game_App
{
    public class ChallengeViewModel
    {
        public int ID { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public string Assignment { get; set; }
        public int Skilllvl { get; set; }
        public DateTime TimeAvailable { get; set; }
        public TimeSpan Duration { get; set; }
        public string Hint { get; set; }

        public ChallengeViewModel(Challenge challengeDTO)
        {
            ID = challengeDTO.ID;
            Name = challengeDTO.Name;
            Description = challengeDTO.Description;
            Assignment = challengeDTO.Assignment;
            Skilllvl = challengeDTO.Skilllvl;
            TimeAvailable = challengeDTO.TimeAvailable;
            Duration = challengeDTO.Duration;
            Hint = challengeDTO.Hint;
        }

        public ChallengeViewModel()
        {

        }
    }
}
