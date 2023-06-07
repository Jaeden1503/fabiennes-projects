using Microsoft.AspNetCore.Mvc;
using Receptenzoeker.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using ReceptenzoekerBLL;
using ReceptenzoekerDAL;

namespace Receptenzoeker.Controllers
{
    public class IngredientController : Controller
    {
        IngredientContainer ingredientContainer = new IngredientContainer(new IngredientDAL());

        public IActionResult ListAllIngredients()
        {
            if (HttpContext.Session.Get("Admin") == null)
            {
                return RedirectToAction("LogIn", "Account");
            }

            if (TempData.ContainsKey("Delete"))
            {
                ViewBag.Message = TempData["Delete"].ToString();
            }

            List<IngredientViewModel> ingredientVMs = new List<IngredientViewModel>();

            foreach (Ingredient ingredient in ingredientContainer.GetAllIngredients())
            {
                ingredientVMs.Add(new IngredientViewModel(ingredient.ID, ingredient.IngredientName));
            }
            return View("ListAllIngredients", ingredientVMs);
        }

        public IActionResult AddIngredient()
        {
            if (HttpContext.Session.Get("Admin") == null)
            {
                return RedirectToAction("LogIn", "Account");
            }

            return View();
        }

        [HttpPost]
        public IActionResult AddIngredient(IngredientViewModel ingredientVM)
        {
            if (ingredientContainer.CheckIngredientValidity(new Ingredient(ingredientVM.IngredientName)))
            {
                ingredientContainer.AddIngredient(new Ingredient(ingredientVM.IngredientName));
                return RedirectToAction("ListAllIngredients", "Ingredient");
            }
            else
                ViewBag.Message = string.Format("Ingredient bestaat al");
            return View();
        }

        public IActionResult EditIngredient(int id)
        {
            if (HttpContext.Session.Get("Admin") == null)
            {
                return RedirectToAction("LogIn", "Account");
            }

            Ingredient ingredient = ingredientContainer.GetIngredient(id);
            IngredientViewModel ingredientViewModel = new IngredientViewModel(ingredient.ID, ingredient.IngredientName);
            return View(ingredientViewModel);
        }


        [HttpPost]
        public IActionResult EditIngredient(IngredientViewModel ingredientVM)
        {
            Ingredient ingredient = new Ingredient(ingredientVM.ID, ingredientVM.IngredientName);

            if (ingredientContainer.CheckIngredientValidity(ingredient))
            {
                if (ingredientContainer.EditIngredient(ingredient))
                {
                    return RedirectToAction("ListAllIngredients", "Ingredient"); //wanneer je op save drukt
                }
            }
            else
                ViewBag.Message = string.Format("Ingredient bestaat al");
            return View();
        }

        public IActionResult DeleteIngredient(IngredientViewModel ingredientViewModel)
        {
            if (HttpContext.Session.Get("Admin") == null)
            {
                return RedirectToAction("LogIn", "Account");
            }

            if (ingredientContainer.DeleteIngredient(ingredientViewModel.ID))
            {
                TempData["Delete"] = "Ingredient is succesvol verwijdert";
            }
            else
                TempData["Delete"] = "Ingredient wordt in een recept gebruikt, dus kon niet worden verwijderd";

            return RedirectToAction("ListAllIngredients", "Ingredient");
        }
    }
}
