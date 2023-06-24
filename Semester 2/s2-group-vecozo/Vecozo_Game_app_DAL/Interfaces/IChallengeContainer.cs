using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Vecozo_Game_app_DAL
{
    public interface IChallengeContainer
    {
        public List<ChallengeDTO> GetAllActiveChallenges();
        public ChallengeDTO GetChallengeByID(int id);
    }
}
