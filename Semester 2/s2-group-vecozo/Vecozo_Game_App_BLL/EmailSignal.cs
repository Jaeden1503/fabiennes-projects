using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Net;
using System.Net.Mail;

namespace Vecozo_Game_App_BLL
{
    public class EmailSignal
    {
        //Email code https://www.c-sharpcorner.com/UploadFile/2a6dc5/how-to-send-a-email-using-Asp-Net-C-Sharp/

        public bool Send(string name, string email, int challengeid, string challengename, bool validation)
        {
            string to = "jarno.vriens1234@gmail.com"; //To address  
            string from = "peterpeters.norep@outlook.com"; //From address    
            MailMessage message = new MailMessage(from, to);

            string validationString = " ";
            if (validation)
            {
                validationString = " De sollicitant heeft het antwoord goed.";
            }
            else
                validationString = " De sollicitant heeft het antwoord fout.";

            string mailbody = "Er is een nieuwe sollicitatie binnengekomen. De sollicitant heet " + name + " en heeft gesolliciteerd met het volgende emailadres: " + email + ". \n" + name + " heeft challenge " + challengeid + " '" + challengename + "' gemaakt." + validationString  ;
            message.Subject = "Er is een nieuwe sollicitatie binnengekomen";
            message.Body = mailbody;
            message.BodyEncoding = Encoding.UTF8;
            message.IsBodyHtml = true;
            SmtpClient client = new SmtpClient("smtp.live.com", 587); //Outlook smtp
            NetworkCredential basicCredential1 = new
            NetworkCredential("peterpeters.norep@outlook.com", "PeterPeters1");
            client.EnableSsl = true;
            client.UseDefaultCredentials = false;
            client.Credentials = basicCredential1;
            try
            {
                client.Send(message);
                return true;
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
                return false;
            }
        }
    }
}
