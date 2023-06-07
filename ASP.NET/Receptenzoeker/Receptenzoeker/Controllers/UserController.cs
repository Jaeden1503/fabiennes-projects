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
    public class UserController : Controller
    {
        UserContainer userContainer = new UserContainer(new UserDAL());

        public IActionResult LogIn()
        {
            return View();
        }

        [HttpPost]
        public IActionResult LogIn(UserViewModel userVM)
        {
            User user = userContainer.GetUserLogin(new User(userVM.UserName, userVM.Password));

            //if account exists
            if (user.ID != 0)
            {
                //if account can be used
                if (user.IsActive)
                {
                    if (user.IsAdmin)
                    {
                        HttpContext.Session.SetString("Admin", userVM.UserName);
                        TempData["Login"] = "Succesvol ingelogd";
                        return RedirectToAction("Index", "Dashboard"); //redirect naar de view/map/cshtml
                    }
                    else
                    {
                        HttpContext.Session.SetInt32("User", user.ID);
                        TempData["Login"] = "Succesvol ingelogd";
                        return RedirectToAction("FindRecipe", "Recipe");
                    }
                }
                else
                {
                    ViewBag.Message = string.Format("Account is inactief");
                    return View();
                }
            }
            else
            {
                ViewBag.Message = string.Format("Onjuiste gegevens");
                return View();
            }
        }

        public IActionResult Logout()
        {
            if (HttpContext.Session.Get("Admin") != null)
            {
                HttpContext.Session.Remove("Admin");
            }
            else if (HttpContext.Session.Get("User") != null)
            {
                HttpContext.Session.Remove("User");
            }
            TempData["Logout"] = "Succesvol uitgelogd";
            return RedirectToAction("FindRecipe", "Recipe");
        }

        public IActionResult ListAllUsers()
        {
            if (HttpContext.Session.Get("Admin") == null)
            {
                return RedirectToAction("LogIn", "User");
            }

            List<UserViewModel> userVMs = new List<UserViewModel>();

            foreach (User item in userContainer.GetAllUsers())
            {
                userVMs.Add(new UserViewModel(item.ID, item.Name, item.IsActive));
            }
            return View(userVMs);
        }

        public IActionResult ChangeUserStatus(int id, bool isactive)
        {
            if (HttpContext.Session.Get("Admin") == null)
            {
                return RedirectToAction("LogIn", "User");
            }

            if(isactive)
            {
                isactive = false;
            }
            else
            {
                isactive = true;
            }
            userContainer.UpdateUserStatus(new User(id, isactive));
            return RedirectToAction("ListAllUsers", "User");
        }
    }
}
