using System;
using System.Collections.Generic;
using System.Text;
using System.Data.SqlClient;

namespace Vecozo_Game_app_DAL
{
    public class SubmissionDAL : Database, ISubmissionContainer
    {
        public bool AddSubmission(SubmissionDTO submissionDTO)
        {
            try
            {
                OpenConnection();
                sql = "INSERT INTO Submission (challengeid, applicantid, attempts, code, timestamp, timespan, validation) VALUES (@challengeid, @applicantid, @attempts, @code, @timestamp, @timespan, @validation)";
                CMD = new SqlCommand(sql, SQLCon);
                CMD.Parameters.AddWithValue("@challengeid", submissionDTO.ChallengeID);
                CMD.Parameters.AddWithValue("@applicantid", submissionDTO.ApplicantID);
                CMD.Parameters.AddWithValue("@attempts", submissionDTO.Attempts);
                CMD.Parameters.AddWithValue("@code", submissionDTO.Code);
                CMD.Parameters.AddWithValue("@timestamp", submissionDTO.EndTime);
                CMD.Parameters.AddWithValue("@timespan", submissionDTO.Timespan);
                CMD.Parameters.AddWithValue("@validation", submissionDTO.Validation);

                CMD.ExecuteNonQuery();

                CloseConnection();
                return true;
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
                CloseConnection();
                return false;
            }
        }
    }
}
