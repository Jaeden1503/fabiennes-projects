using System;
using System.Collections.Generic;
using System.Text;
using System.Data.SqlClient;

namespace Vecozo_Game_app_DAL
{
    public class ApplicantDAL : Database, IApplicantContainer
    {

        public int AddApplicant(ApplicantDTO applicantDTO)
        {
            try
            {
                int applicantid = 0;
                OpenConnection();
                sql = "INSERT INTO Applicant (name, email) VALUES (@name, @email); SELECT applicantid FROM Applicant WHERE applicantid = @@IDENTITY" ;
                CMD = new SqlCommand(sql, SQLCon);
                CMD.Parameters.AddWithValue("@name", applicantDTO.Name);
                CMD.Parameters.AddWithValue("@email", applicantDTO.Email);

                SqlDataReader sqlDataReader = CMD.ExecuteReader();
                sqlDataReader.Read();
                applicantid = sqlDataReader["applicantid"] == DBNull.Value ? 0 : (int)sqlDataReader["applicantid"]; 
                CloseConnection();
                return applicantid;
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
                CloseConnection();
                return 0;
            }
        }

        public int DoesApplicantExists(ApplicantDTO applicantDTO)
        {
            try
            {
                int applicantid = 0;
                OpenConnection();
                sql = "SELECT applicantid FROM Applicant WHERE name = @name AND email = @email";
                CMD = new SqlCommand(sql, SQLCon);
                CMD.Parameters.AddWithValue("@name", applicantDTO.Name);
                CMD.Parameters.AddWithValue("@email", applicantDTO.Email);

                SqlDataReader sqlDataReader = CMD.ExecuteReader();
                sqlDataReader.Read();
                applicantid = sqlDataReader["applicantid"] == DBNull.Value ? 0 : (int)sqlDataReader["applicantid"];
                CloseConnection();
                return applicantid;
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
                CloseConnection();
                return 0;
            }
        }
    }
}
