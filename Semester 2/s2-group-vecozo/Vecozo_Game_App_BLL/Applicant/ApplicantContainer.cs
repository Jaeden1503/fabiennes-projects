using System;
using System.Collections.Generic;
using System.Text;
using Vecozo_Game_app_DAL;
using ExtensionMethods;

namespace Vecozo_Game_App_BLL
{
    public class ApplicantContainer
    {
        private IApplicantContainer iapplicantContainer;

        public ApplicantContainer(IApplicantContainer iapplicantContainer)
        {
            this.iapplicantContainer = iapplicantContainer;
        }

        public int AddApplicant(string name, string email)
        {
            return iapplicantContainer.AddApplicant(new ApplicantDTO(name, email));
        }

        public int DoesApplicantExists(string name, string email)
        {
            return iapplicantContainer.DoesApplicantExists(new ApplicantDTO(name, email));
        }
    }
}
