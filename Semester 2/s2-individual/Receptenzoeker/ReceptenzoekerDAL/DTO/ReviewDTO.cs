using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReceptenzoekerDAL
{
    public class ReviewDTO
    {
        public int ID { get; private set; }
        public string Title { get; private set; }
        public string Description { get; private set; } 
        
        public RecipeDTO RecipeDTO { get; private set; }
        public UserDTO UserDTO { get; private set; }

        
        //converting inserting reviews
        public ReviewDTO(string title, string description, UserDTO userDTO, RecipeDTO recipeDTO)
        {
            this.Title = title;
            this.Description = description;
            this.UserDTO = userDTO;
            this.RecipeDTO = recipeDTO;
        }

        //get reviews by recipe id
        public ReviewDTO(int id, string title, string description, UserDTO userDTO)
        {
            this.ID = id;
            this.Title = title;
            this.Description = description;
            this.UserDTO = userDTO;
        }

        //voor ophalen reviews (user)
        public ReviewDTO(string title, string description, UserDTO userDTO)
        {
            this.Title = title;
            this.Description = description;
            this.UserDTO = userDTO;
        }

        //getting all reviews
        public ReviewDTO(int id, string title, string description, UserDTO userDTO, RecipeDTO recipeDTO)
        {
            this.ID = id;
            this.Title = title;
            this.Description = description;
            this.UserDTO = userDTO;
            this.RecipeDTO = recipeDTO;
        }
    }
}
