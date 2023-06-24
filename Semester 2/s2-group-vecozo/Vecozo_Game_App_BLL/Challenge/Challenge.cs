using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Vecozo_Game_app_DAL;

namespace Vecozo_Game_App_BLL
{
    public class Challenge
    {
        public int ID { get; private set; }
        public string Name { get; private set; }
        public string Description { get; private set; }
        public string Assignment { get; private set; }
        public int Skilllvl { get; private set; }
        public DateTime TimeAvailable { get; private set; }
        public TimeSpan Duration { get; private set; }
        public string Answer { get; private set; }
        public string Hint { get; private set; }

        public Challenge(ChallengeDTO challengeDTO)
        {
            ID = challengeDTO.ID;
            Name = challengeDTO.Name;
            Description = challengeDTO.Description;
            Assignment = challengeDTO.Assignment;
            Skilllvl = challengeDTO.Skilllvl;
            TimeAvailable = challengeDTO.TimeAvailable;
            Duration = challengeDTO.Duration;
            Answer = challengeDTO.Answer;
            Hint = challengeDTO.Hint;
        }
    }
}
