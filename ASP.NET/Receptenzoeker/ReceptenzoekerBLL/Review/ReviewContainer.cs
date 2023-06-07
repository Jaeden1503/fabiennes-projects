using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ReceptenzoekerDAL;

namespace ReceptenzoekerBLL
{
    public class ReviewContainer
    {
        private IReviewContainer ireviewContainer;

        public ReviewContainer(IReviewContainer iReviewContainer)
        {
            this.ireviewContainer = iReviewContainer;
        }

        public int ReviewCount()
        {
            List<ReviewDTO> reviewDTOs = ireviewContainer.GetAllReviews();
            return reviewDTOs.Count;
        }

        public List<Review> GetReviewsByID(int id)
        {
            List<Review> reviews = new List<Review>();
            List<ReviewDTO> reviewDTOs = ireviewContainer.GetReviewsByID(id);

            foreach (ReviewDTO reviewDTO in reviewDTOs)
            {
                reviews.Add(new Review(reviewDTO));
            }
            return reviews;
        }

        public bool CreateReview(Review review)
        {
            return ireviewContainer.Addreview(ConvertToDTO(review));
        }

        public bool DeleteReview(int id)
        {
            return ireviewContainer.DeleteReviewByID(id);
        }

        ////////// METHODS FOR CONVERTING TO DTO /////////
        
        public ReviewDTO ConvertToDTO(Review review)
        {
            return new ReviewDTO(review.Title, review.Description, new UserDTO(review.User.ID), new RecipeDTO(review.Recipe.ID));
        }
    }
}
