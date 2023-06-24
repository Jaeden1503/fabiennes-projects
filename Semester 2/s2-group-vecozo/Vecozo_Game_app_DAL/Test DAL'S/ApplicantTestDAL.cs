using System.Collections.Generic;

namespace Vecozo_Game_app_DAL.Test_DAL_S
{
    public class ApplicantTestDAL : IApplicantContainer
    {
        List<ApplicantDTO> applicantDTOs = new()
        {
            new ApplicantDTO("Peter", "peterpeters@gmail.com"),
            new ApplicantDTO("Gijs", "gijs@gijs,nl")
        };
        public int AddApplicant(ApplicantDTO applicantDTO)
        {
            if(applicantDTO.Name != string.Empty && applicantDTO.Email != string.Empty)
            {
                applicantDTOs.Add(applicantDTO);
                return applicantDTOs.IndexOf(applicantDTO);
            }
            return -1;
        }

        public int DoesApplicantExists(ApplicantDTO applicantDTO)
        {
            return 0;
        }
    }
}
