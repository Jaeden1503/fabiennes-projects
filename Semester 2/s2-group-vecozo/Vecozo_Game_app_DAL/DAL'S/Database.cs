using System;
using System.Collections.Generic;
using System.Text;
using System.Data.SqlClient;

namespace Vecozo_Game_app_DAL
{
    public class Database
    {
        public SqlConnection SQLCon { get; private set; } = new SqlConnection("Server=mssql.fhict.local;Database=dbi460690_Vecozo;User Id=dbi460690_Vecozo;Password=vecozo;");
        public SqlCommand CMD { get; set; }
        public SqlDataReader DataReader { get; set; }
        public string sql { get; set; }

        public void OpenConnection()
        {   
            SQLCon.Open();
        }

        public void CloseConnection()
        {
            SQLCon.Close();
        }
    }
}
