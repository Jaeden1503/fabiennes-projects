using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Receptenzoeker.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using ReceptenzoekerBLL;
using ReceptenzoekerDAL;

namespace Receptenzoeker.Controllers
{
    public class DashboardController : Controller
    {
        UserContainer userContainer = new UserContainer(new UserDAL());
        RecipeContainer recipeContainer = new RecipeContainer(new RecipeDAL());
        IngredientContainer ingredientContainer = new IngredientContainer(new IngredientDAL());
        ReviewContainer reviewContainer = new ReviewContainer(new ReviewDAL());

        public IActionResult Index()
        {
            if (HttpContext.Session.Get("Admin") == null)
            {
                return RedirectToAction("LogIn", "User");
            }

            if (TempData.ContainsKey("Login"))
            {
                ViewBag.Message = TempData["Login"].ToString();
            }

            int userCount = userContainer.UserCount();
            int recipeCount = recipeContainer.RecipeCount();
            int ingredientCount = ingredientContainer.IngredientCount();
            int reviewCount = reviewContainer.ReviewCount();

            DashboardViewModel dashboardVM = new DashboardViewModel(userCount, recipeCount, ingredientCount, reviewCount);

            return View("Index", dashboardVM);
        }
    }
}
