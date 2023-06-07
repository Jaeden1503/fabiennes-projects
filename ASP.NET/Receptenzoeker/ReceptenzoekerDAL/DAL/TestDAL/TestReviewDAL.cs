using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReceptenzoekerDAL
{
    public class TestReviewDAL : IReviewContainer
    {
        List<ReviewDTO> fakeReviews = new List<ReviewDTO>()
        {
            new ReviewDTO(1, "titel", "beschrijving", new UserDTO(1), new RecipeDTO(2)),
            new ReviewDTO(2, "titel2", "beschrijving2", new UserDTO(2), new RecipeDTO(4)),
            new ReviewDTO(3, "titel3", "beschrijving3", new UserDTO(1), new RecipeDTO(4))
        };

        public List<ReviewDTO> GetAllReviews()
        {
            return fakeReviews;
        }

        public List<ReviewDTO> GetReviewsByID(int recipeid)
        {
            List<ReviewDTO> returnList = new List<ReviewDTO>();

            foreach (ReviewDTO item in fakeReviews)
            {
                if (item.RecipeDTO.ID == recipeid)
                {
                    returnList.Add(item);
                }
            }
            return returnList;
        }

        public bool Addreview(ReviewDTO reviewDTO)
        {
            fakeReviews.Add(reviewDTO);
            return true;
        }

        public bool DeleteReviewByID(int recipeid)
        {
            fakeReviews.RemoveAt(recipeid - 1);
            return true;
        }
    }
}
