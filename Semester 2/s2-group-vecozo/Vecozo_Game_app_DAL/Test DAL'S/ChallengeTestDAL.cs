using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Vecozo_Game_app_DAL.Test_DAL_S
{
    public class ChallengeTestDAL : IChallengeContainer
    {
        List<ChallengeDTO> ActiveChallenges = new()
        {
            //int id, string name, string description, string assignment, string answer, string hint
            new ChallengeDTO(1, "1", "1", "1", "1", "1"),
            new ChallengeDTO(2, "2", "2", "2", "2", "2"),
            new ChallengeDTO(3, "3", "3", "3", "3", "3")
        };
        public List<ChallengeDTO> GetAllActiveChallenges()
        {
            try
            {
                return ActiveChallenges;
            }
            catch
            {
                return null;
            }
        }
        public ChallengeDTO GetChallengeByID(int id)
        {
            try 
            {
                foreach(var item in ActiveChallenges)
                {
                    if(item.ID == id) { return item; }
                }
                return null;
            }
            catch
            {
                return null;
            }
        }
    }
}
