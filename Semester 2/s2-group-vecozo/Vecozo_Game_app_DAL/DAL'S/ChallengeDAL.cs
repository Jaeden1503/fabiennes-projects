using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Vecozo_Game_app_DAL
{
    public class ChallengeDAL : Database, IChallengeContainer
    {
        public ChallengeDTO GetChallengeByID(int id)
        {
            ChallengeDTO challengeDTO = null;
            sql = "SELECT challengeid, name, description, assignment, answer, hint FROM challenge WHERE challengeid = @id";
            CMD = new SqlCommand(sql, SQLCon);
            CMD.Parameters.AddWithValue("@id", id);
            try
            {
                OpenConnection();
                using (DataReader = CMD.ExecuteReader())
                {
                    if (DataReader.HasRows)
                    {
                        while (DataReader.Read())
                        {
                            challengeDTO = new ChallengeDTO(
                                Convert.ToInt32(DataReader["challengeid"]),
                                Convert.ToString(DataReader["name"]),
                                Convert.ToString(DataReader["description"]),
                                Convert.ToString(DataReader["assignment"]),                                
                                Convert.ToString(DataReader["answer"]),
                                Convert.ToString(DataReader["hint"])
                                );
                        }
                    }
                }
                CloseConnection();
                return challengeDTO;
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                CloseConnection();
                return challengeDTO;
            }
        }
        public List<ChallengeDTO> GetAllActiveChallenges()
        {
            List<ChallengeDTO> challenges = new List<ChallengeDTO>();
            sql = "SELECT * FROM Challenge WHERE active = 1";
            CMD = new SqlCommand(sql, SQLCon);
            try
            {
                OpenConnection();
                using (DataReader = CMD.ExecuteReader())
                {
                    if (DataReader.HasRows)
                    {
                        while (DataReader.Read())
                        {
                            long duration = DataReader["duration"] == DBNull.Value ? 0 : Convert.ToInt64(DataReader["duration"]);

                            ChallengeDTO Challenge = new ChallengeDTO(
                                Convert.ToInt32(DataReader["challengeid"]),
                                Convert.ToString(DataReader["name"]),
                                Convert.ToString(DataReader["description"]),
                                Convert.ToString(DataReader["assignment"]),
                                Convert.ToInt32(DataReader["skilllevel"]),
                                (DateTime)DataReader["timeavailable"], 
                                TimeSpan.FromTicks(duration));

                            challenges.Add(Challenge);

                        }
                    }
                }
                CloseConnection();
                return challenges;
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                CloseConnection();
                return challenges;
            }
        }
    }
}
