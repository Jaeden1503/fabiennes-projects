using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Vecozo_Game_app_DAL
{
    public class ChallengeDTO
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

        public ChallengeDTO(int id, string name, string description, string assignment, int skilllvl, DateTime timeavailable, TimeSpan duration)
        {
            ID = id;
            Name = name;
            Description = description;
            Assignment = assignment;
            Skilllvl = skilllvl;
            TimeAvailable = timeavailable;
            Duration = duration;
        }

        public ChallengeDTO(int id, string name, string description, string assignment, string answer, string hint)
        {
            ID = id;
            Name = name;
            Description = description;
            Assignment = assignment;            
            Answer = answer;
            Hint = hint;
        }
    }
}
