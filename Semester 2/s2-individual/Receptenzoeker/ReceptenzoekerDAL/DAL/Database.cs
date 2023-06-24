using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SqlClient;

namespace ReceptenzoekerDAL
{
    public class Database
    {
        public SqlConnection SqlCon { get; private set; } = new SqlConnection("Server=mssql.fhict.local;Database=dbi481376;User Id=dbi481376;Password=doemaarwat1;");
        public SqlCommand Command { get; set; }
        public SqlCommand Command2 { get; set; }
        public SqlCommand Command3 { get; set; }
        public SqlDataReader Reader { get; set; }

        public void OpenConnection()
        {
            SqlCon.Open();
        }

        public void CloseConnection()
        {
            SqlCon.Close();
        }
    }
}
