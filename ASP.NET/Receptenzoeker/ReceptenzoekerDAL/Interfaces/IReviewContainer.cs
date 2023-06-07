using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReceptenzoekerDAL
{
    public interface IReviewContainer
    {
        List<ReviewDTO> GetAllReviews(); 
        List<ReviewDTO> GetReviewsByID(int id);
        bool Addreview(ReviewDTO reviewDTO);
        bool DeleteReviewByID(int id);
    }
}