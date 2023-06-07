using Microsoft.AspNetCore.Mvc;
using Receptenzoeker.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using ReceptenzoekerBLL;
using ReceptenzoekerDAL;
using Microsoft.AspNetCore.Http;

namespace Receptenzoeker.Controllers
{
    public class ReviewController : Controller
    {
        ReviewContainer reviewContainer = new ReviewContainer(new ReviewDAL());

        public IActionResult ListRecipeReviews(int id)
        {
            if (HttpContext.Session.Get("Admin") == null)
            {
                return RedirectToAction("LogIn", "User");
            }

            List<ReviewViewModel> reviewVMs = new List<ReviewViewModel>();

            foreach (Review review in reviewContainer.GetReviewsByID(id))
            {
                reviewVMs.Add(new ReviewViewModel(review.ID, review.Title, review.Description, new UserViewModel(review.User.Name)));
            }
            return View(reviewVMs);
        }

        [HttpGet]
        public IActionResult CreateReview(int recipeid, string recipename)
        {
            //check if user is logged in
            if (HttpContext.Session.Get("User") == null)
            {
                TempData["Review"] = "Log in om een review te kunnen schrijven";

                RecipeViewModel recipeViewModel = new RecipeViewModel(recipeid);
                return RedirectToAction("DetailsUserRecipe", "Recipe", recipeViewModel);
            }

            ReviewViewModel reviewViewModel = new ReviewViewModel(new RecipeViewModel(recipeid, recipename));
            return View(reviewViewModel);
        }

        [HttpPost]
        public IActionResult CreateReview(ReviewViewModel reviewVM)
        {
            Review review = new Review(reviewVM.Title, reviewVM.Description, new User(HttpContext.Session.GetInt32("User").Value), new Recipe(reviewVM.RecipeViewModel.ID));
            reviewContainer.CreateReview(review);
            RecipeViewModel recipeViewModel = new RecipeViewModel(reviewVM.RecipeViewModel.ID);

            return RedirectToAction("DetailsUserRecipe", "Recipe", recipeViewModel);
        }

        public IActionResult DeleteReview(ReviewViewModel reviewVM)
        {
            if (HttpContext.Session.Get("Admin") == null)
            {
                return RedirectToAction("LogIn", "Account");
            }

            reviewContainer.DeleteReview(reviewVM.ID);
            TempData["DeleteReview"] = "Review succesvol verwijderd";

            return RedirectToAction("ListAllRecipes", "Recipe");
        }
    }
}