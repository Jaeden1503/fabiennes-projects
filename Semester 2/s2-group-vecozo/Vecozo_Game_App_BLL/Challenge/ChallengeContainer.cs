using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Vecozo_Game_app_DAL;

namespace Vecozo_Game_App_BLL
{
    public class ChallengeContainer
    {
        IChallengeContainer IChallengeContainer;

        public ChallengeContainer(IChallengeContainer ichallengecontainer)
        {
            IChallengeContainer = ichallengecontainer;
        }

        public Challenge GetChallengeByID(int id)
        {
            Challenge challenge = new Challenge(IChallengeContainer.GetChallengeByID(id));
            return challenge;
        }

        public List<Challenge> GetAllActiveChallenges()
        {
            List<Challenge> Challenges = new List<Challenge>();
            foreach (ChallengeDTO item in IChallengeContainer.GetAllActiveChallenges())
            {
                Challenges.Add(new Challenge(item));
            }
            return Challenges;
        }
    }
}
