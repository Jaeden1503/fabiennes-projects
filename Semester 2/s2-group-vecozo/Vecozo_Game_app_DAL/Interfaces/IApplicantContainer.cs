using System;
using System.Collections.Generic;
using System.Text;

namespace Vecozo_Game_app_DAL
{
    public interface IApplicantContainer
    {
        int AddApplicant(ApplicantDTO applicantDTO);
        int DoesApplicantExists(ApplicantDTO applicantDTO);
    }
}
