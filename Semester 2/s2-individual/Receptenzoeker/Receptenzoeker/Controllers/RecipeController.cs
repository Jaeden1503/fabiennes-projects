using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using ReceptenzoekerDAL;
using ReceptenzoekerBLL;
using Receptenzoeker.Models;
using Newtonsoft.Json;
using System.Text.RegularExpressions;

namespace Receptenzoeker.Controllers
{
    public class RecipeController : Controller
    {
        RecipeContainer recipeContainer = new RecipeContainer(new RecipeDAL());
        IngredientContainer ingredientContainer = new IngredientContainer(new IngredientDAL());
        ReviewContainer reviewContainer = new ReviewContainer(new ReviewDAL());
        Random r = new Random();

        List<string> imageList = new List<string>
        {
            "https://i0.wp.com/jennyalvares.com/wp-content/uploads/2020/06/passievrucht-cheesecake.jpg?resize=1200%2C800&ssl=1",
            "https://soulfood.nl/wp-content/uploads/2021/12/pizza1.jpg",
            "https://www.culy.nl/wp-content/uploads/2019/11/4_pompoensoep-met-miso.jpg",
            "https://images-1.schellywood.be/thumbnail/full/108235/pasta-met-spicy-chorizosaus-3.jpg",
            "https://iamafoodblog.b-cdn.net/wp-content/uploads/2020/05/homemade-birria-tacos-recipe-3273w.jpg",
            "https://www.inspiredtaste.net/wp-content/uploads/2019/07/Crispy-Falafel-Recipe-1200.jpg",
            "https://www.inspiredtaste.net/wp-content/uploads/2020/07/Bean-Salad-Recipe-2-1200.jpg",
            "https://www.loveandoliveoil.com/wp-content/uploads/2022/01/latke-eggs-benedict-FEAT-1200x800.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS68OacqRhkfp3g-fm4zSiRuJF_5wPKcr0mVw&usqp=CAU",
            "https://debestewijnbij.nl/wp-content/uploads/2019/04/chocolate-2047248_1280-1200x800.jpg"

        };

        public IActionResult ListUserNewRecipes()
        {
            List<RecipeViewModel> recipeVMs = new List<RecipeViewModel>();

            foreach (Recipe recipe in recipeContainer.GetAllRecipes())
            {
                int index = r.Next(imageList.Count);
                string randomString = imageList[index];
                recipeVMs.Add(new RecipeViewModel(recipe.ID, recipe.Name, randomString));
            }
            var LstItems = recipeVMs.Skip(Math.Max(0, recipeVMs.Count - 5));

            return View("ListUserNewRecipes", LstItems);
        }

        public IActionResult ListUserRecipes()
        {
            List<RecipeViewModel> recipeVMs = new List<RecipeViewModel>();          

            foreach (Recipe recipe in recipeContainer.GetAllRecipes())
            {
                int index = r.Next(imageList.Count);
                string randomString = imageList[index];
                recipeVMs.Add(new RecipeViewModel(recipe.ID, recipe.Name, randomString));
            }
            return View("ListUserRecipes", recipeVMs);
        }

        public IActionResult DetailsUserRecipe(RecipeViewModel recipeVM)
        {
            List<RecipeIngredientViewModel> recipeIngredientViewModels = new List<RecipeIngredientViewModel>();
            RecipeViewModel rvm = new RecipeViewModel();
            rvm.ReviewViewModels = new List<ReviewViewModel>();

            if (TempData.ContainsKey("Review"))
            {
                ViewBag.Message = TempData["Review"].ToString();
            }

            Recipe recipes = recipeContainer.GetRecipeDetails(recipeVM.ID);

            foreach (Review review in reviewContainer.GetReviewsByID(recipeVM.ID))
            {
                rvm.ReviewViewModels.Add(new ReviewViewModel(review.ID, review.Title, review.Description, new UserViewModel(review.User.Name)));
            }

            foreach (RecipeIngredient item in recipes.RecipeIngredients)
            {
                recipeIngredientViewModels.Add(new RecipeIngredientViewModel(item.Quantity, item.QuantityUnit, new IngredientViewModel(item.Ingredient.IngredientName)));
            }
            RecipeViewModel recipeViewModel = new RecipeViewModel(recipes.ID, recipes.Name, recipes.Category, recipes.PreparationTime, recipes.PreparationMethod, recipes.PersonAmount, recipes.Type, recipeIngredientViewModels, rvm.ReviewViewModels);

            return View("DetailsUserRecipe", recipeViewModel);
        }

        public IActionResult FindRecipe()
        {
            RecipeViewModel recipeViewModel = new RecipeViewModel();
            recipeViewModel.IngredientViewModels = new List<IngredientViewModel>();

            foreach (Ingredient ingredient in ingredientContainer.GetAllIngredients())
            {
                recipeViewModel.IngredientViewModels.Add(new IngredientViewModel(ingredient.ID, ingredient.IngredientName));
            }

            if (TempData.ContainsKey("Login"))
            {
                ViewBag.Message = TempData["Login"].ToString();
            }

            if (TempData.ContainsKey("Search"))
            {
                ViewBag.Message = TempData["Search"].ToString();
            }

            if (TempData.ContainsKey("Logout"))
            {
                ViewBag.Message = TempData["Logout"].ToString();
            }
            return View(recipeViewModel);
        }

        [HttpPost]
        public IActionResult FindRecipe(RecipeViewModel recipeVM)
        {
            if (recipeVM.Category == null || recipeVM.PreparationTime == 0 || recipeVM.PersonAmount == 0 || recipeVM.Type == null || recipeVM.IngredientViewModel == null)
            {
                TempData["Search"] = "Niet alle velden zijn ingevuld";
                return RedirectToAction("FindRecipe");
            }
            Recipe recipe = new Recipe(recipeVM.Category, recipeVM.PreparationTime, recipeVM.PersonAmount, recipeVM.Type, new Ingredient(recipeVM.IngredientViewModel.IngredientName));

            List<Recipe> recipes = recipeContainer.FindRecipe(recipe);
            List<RecipeViewModel> recipeViewModels = new List<RecipeViewModel>();
            
            foreach (Recipe item in recipes)
            {
                int index = r.Next(imageList.Count);
                string randomString = imageList[index];
                recipeViewModels.Add(new RecipeViewModel(item.ID, item.Name, randomString));
            }

            if (recipeViewModels.Count == 0)
            {
                TempData["Search"] = "Geen recept gevonden, verander zoek gegevens";
                return RedirectToAction("FindRecipe");
            }
            return View("ListUserRecipes", recipeViewModels);
        }

        public IActionResult FindRecipeByCat(string category)
        {
            List<Recipe> recipes = recipeContainer.RecipesByCategory(category);
            List<RecipeViewModel> recipeViewModels = new List<RecipeViewModel>();

            foreach (Recipe item in recipes)
            {
                int index = r.Next(imageList.Count);
                string randomString = imageList[index];
                recipeViewModels.Add(new RecipeViewModel(item.ID, item.Name, randomString));
            }

            if (recipeViewModels.Count == 0)
            {
                TempData["Search"] = "Helaas geen recepten gevonden met de gekozen categorie";
                return RedirectToAction("FindRecipe");
            }
            return View("ListUserRecipes", recipeViewModels);
        }

        public IActionResult FindRecipeByType(string type)
        {
            List<Recipe> recipes = recipeContainer.RecipesByType(type);
            List<RecipeViewModel> recipeViewModels = new List<RecipeViewModel>();

            foreach (Recipe item in recipes)
            {
                int index = r.Next(imageList.Count);
                string randomString = imageList[index];
                recipeViewModels.Add(new RecipeViewModel(item.ID, item.Name, randomString));
            }

            if (recipeViewModels.Count == 0)
            {
                TempData["Search"] = "Helaas geen recepten gevonden met het gekozen type";
                return RedirectToAction("FindRecipe");
            }
            return View("ListUserRecipes", recipeViewModels);
        }

        public IActionResult FindRecipeByTime(int time)
        {
            List<Recipe> recipes = recipeContainer.RecipesByTime(time);
            List<RecipeViewModel> recipeViewModels = new List<RecipeViewModel>();

            foreach (Recipe item in recipes)
            {
                int index = r.Next(imageList.Count);
                string randomString = imageList[index];
                recipeViewModels.Add(new RecipeViewModel(item.ID, item.Name, randomString));
            }

            if (recipeViewModels.Count == 0)
            {
                TempData["Search"] = "Helaas geen recepten gevonden met de gekozen tijd";
                return RedirectToAction("FindRecipe");
            }
            return View("ListUserRecipes", recipeViewModels);
        }


        //////////////// Admin kant ////////////////

        public IActionResult DetailsRecipe(RecipeViewModel recipeVM)
        {
            if (HttpContext.Session.Get("Admin") == null)
            {
                return RedirectToAction("LogIn", "User");
            }

            Recipe recipes = recipeContainer.GetRecipeDetails(recipeVM.ID);

            List<RecipeIngredientViewModel> recipeIngredientViewModels = new List<RecipeIngredientViewModel>();

            foreach (RecipeIngredient item in recipes.RecipeIngredients)
            {
                recipeIngredientViewModels.Add(new RecipeIngredientViewModel(item.Quantity, item.QuantityUnit, new IngredientViewModel(item.Ingredient.IngredientName)));
            }

            RecipeViewModel recipeViewModel = new RecipeViewModel(recipes.ID, recipes.Name, recipes.Category, recipes.PreparationTime, recipes.PreparationMethod, recipes.PersonAmount,recipes.Type, recipeIngredientViewModels);

            return View("DetailsRecipe", recipeViewModel);
        }

        public IActionResult ListAllRecipes()
        {
            if (HttpContext.Session.Get("Admin") == null)
            {
                return RedirectToAction("LogIn", "User");
            }

            if (TempData.ContainsKey("DeleteReview"))
            {
                ViewBag.Message = TempData["DeleteReview"].ToString();
            }

            if (TempData.ContainsKey("DeleteRecipe"))
            {
                ViewBag.Message = TempData["DeleteRecipe"].ToString();
            }

            if (TempData.ContainsKey("EditRecipe"))
            {
                ViewBag.Message = TempData["EditRecipe"].ToString();
            }

            List<RecipeViewModel> recipeVMs = new List<RecipeViewModel>();

            foreach (Recipe recipe in recipeContainer.GetAllRecipes())
            {
                recipeVMs.Add(new RecipeViewModel(recipe.ID, recipe.Name));
            }
            return View("ListAllRecipes", recipeVMs);
        }

        public IActionResult DeleteRecipe(RecipeViewModel recipeVM)
        {
            if (HttpContext.Session.Get("Admin") == null)
            {
                return RedirectToAction("LogIn", "User");
            }

            recipeContainer.DeleteRecipe(recipeVM.ID);
            TempData["DeleteRecipe"] = "Recept is verwijderd";
            return RedirectToAction("ListAllRecipes", "Recipe");
        }


        public IActionResult CreateRecipe()
        {
            if (HttpContext.Session.Get("Admin") == null)
            {
                return RedirectToAction("LogIn", "User");
            }
            RecipeViewModel recipeViewModel = new RecipeViewModel();
            recipeViewModel.IngredientViewModels = new List<IngredientViewModel>();

            foreach (Ingredient ingredient in ingredientContainer.GetAllIngredients())
            {
                recipeViewModel.IngredientViewModels.Add(new IngredientViewModel(ingredient.ID, ingredient.IngredientName));
            }

            if (TempData.ContainsKey("Create"))
            {
                ViewBag.Message = TempData["Create"].ToString();
            }
            return View(recipeViewModel);
        }

        [HttpPost]
        public IActionResult CreateRecipe(RecipeViewModel recipeVM, string recipeIngredient)
        {
            List<RecipeIngredient> recipeIngredients = new List<RecipeIngredient>();
            Console.WriteLine(recipeIngredient);

            if (recipeVM.Name == null || recipeVM.Category == null || recipeVM.PreparationTime == 0 || recipeVM.PreparationMethod == null || recipeVM.PersonAmount == 0 || recipeVM.Type == null || recipeIngredient == null)
            {
                TempData["Create"] = "Niet alle velden zijn ingevuld";
                return RedirectToAction("CreateRecipe");
            }

            JsonConvert.PopulateObject(recipeIngredient, recipeVM);

            foreach (RecipeIngredientViewModel item in recipeVM.RecipeIngredientVMs)
            {
                if (item.IngredientVM.IngredientName == null)
                {
                    TempData["Create"] = "Niet alle velden zijn ingevuld";
                    return RedirectToAction("CreateRecipe");
                }

                recipeIngredients.Add(new RecipeIngredient(item.Quantity, item.QuantityUnit, new Ingredient(item.IngredientVM.ID, item.IngredientVM.IngredientName)));
            }
            //recipeIngredients.Add(new RecipeIngredient(350, "gram", new Ingredient(5))); //testing purposes
            
          
            recipeContainer.CreateRecipe(new Recipe(recipeVM.Name, recipeVM.Category, recipeVM.PreparationTime, recipeVM.PreparationMethod, recipeVM.PersonAmount, recipeVM.Type, recipeIngredients));
            //recipeContainer.CreateRecipe(new Recipe("p", "p", 4, "p", 4, "Vegetarisch", recipeIngredients)); //testing purposes

            return RedirectToAction("ListAllRecipes", "Recipe");
        }


        public IActionResult EditRecipe(int id)
        {
            if (HttpContext.Session.Get("Admin") == null)
            {
                return RedirectToAction("LogIn", "User");
            }

            if (TempData.ContainsKey("EditRecipe"))
            {
                ViewBag.Message = TempData["EditRecipe"].ToString();
            }

            Recipe recipes = recipeContainer.GetRecipeDetails(id);
            List<RecipeIngredientViewModel> recipeIngredientViewModels = new List<RecipeIngredientViewModel>();

            foreach (RecipeIngredient item in recipes.RecipeIngredients)
            {
                recipeIngredientViewModels.Add(new RecipeIngredientViewModel(item.Quantity, item.QuantityUnit, new IngredientViewModel(item.Ingredient.ID, item.Ingredient.IngredientName)));
            }

            List<IngredientViewModel> ingredientVMs = new List<IngredientViewModel>();

            foreach (Ingredient ingredient in ingredientContainer.GetAllIngredients())
            {
                ingredientVMs.Add(new IngredientViewModel(ingredient.ID, ingredient.IngredientName));
            }

            RecipeViewModel recipeViewModel = new RecipeViewModel(recipes.ID, recipes.Name, recipes.Category, recipes.PreparationTime, recipes.PreparationMethod, recipes.PersonAmount, recipes.Type, recipeIngredientViewModels, ingredientVMs);

            return View(recipeViewModel);
        }

        [HttpPost]
        public IActionResult EditRecipe(RecipeViewModel recipeVM, string recipeIngredient)
        {
            List<RecipeIngredient> recipeIngredients = new List<RecipeIngredient>();
            Console.WriteLine(recipeIngredient); //for testing purposes

            if (recipeVM.Name == null || recipeVM.Category == null || recipeVM.PreparationTime == 0 || recipeVM.PreparationMethod == null || recipeVM.PersonAmount == 0 || recipeVM.Type == null || recipeIngredient == null)
            {
                TempData["EditRecipe"] = "Niet alle velden zijn ingevuld";
                return RedirectToAction("EditRecipe");
            }

            JsonConvert.PopulateObject(recipeIngredient, recipeVM);
            
            foreach (RecipeIngredientViewModel item in recipeVM.RecipeIngredientVMs)
            {
                recipeIngredients.Add(new RecipeIngredient(item.Quantity, item.QuantityUnit, new Ingredient(item.IngredientVM.ID, item.IngredientVM.IngredientName)));
            }

            //recipeIngredients.Add(new RecipeIngredient(350, "gram", new Ingredient(5))); //testing purposes
            //recipeIngredients.Add(new RecipeIngredient(500, "milliliter", new Ingredient(6)));
            //recipeContainer.UpdateRecipe(new Recipe(31, "piet", "piet", 10, "piet", 10, "piet", recipeIngredients)); //testing purposes

            if (!recipeContainer.UpdateRecipe(new Recipe(recipeVM.ID, recipeVM.Name, recipeVM.Category, recipeVM.PreparationTime, recipeVM.PreparationMethod, recipeVM.PersonAmount, recipeVM.Type, recipeIngredients)))
            {
                TempData["EditRecipe"] = "Niet gelukt om te updaten";
            }
            return RedirectToAction("ListAllRecipes", "Recipe");
        }
    }
}
