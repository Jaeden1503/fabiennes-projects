using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ReceptenzoekerDAL;

namespace ReceptenzoekerBLL
{
    public class Review
    {
        public int ID { get; private set; }
        public string Title { get; private set; }
        public string Description { get; private set; }

        public Recipe Recipe { get; private set; }
        public User User { get; private set; }

        //inserting reviews
        public Review(string title, string description, User user, Recipe recipe)
        {
            this.Title = title;
            this.Description = description;
            this.User= user;
            this.Recipe = recipe;
        }

        public Review(int id)
        {
            this.ID = id;
        }

        //for getting all reviews
        public Review(ReviewDTO reviewDTO)
        {
            this.ID = reviewDTO.ID;
            this.Title = reviewDTO.Title;
            this.Description = reviewDTO.Description;

            if(reviewDTO.UserDTO != null)
            {
                this.User = new User(reviewDTO.UserDTO);
            }
            if(reviewDTO.RecipeDTO != null)
            {
                this.Recipe = new Recipe(reviewDTO.RecipeDTO);
            }
        }
    }
}
