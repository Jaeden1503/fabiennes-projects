using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Vecozo_Game_App.Models;
using Vecozo_Game_App_BLL;
using Vecozo_Game_app_DAL;

namespace Vecozo_Game_App.Controllers
{
    public class HomeController : Controller
    {
        private readonly ILogger<HomeController> _logger;

        public HomeController(ILogger<HomeController> logger)
        {
            _logger = logger;
        }

        public IActionResult Index()
        {
            ChallengeContainer challengeContainer = new ChallengeContainer(new ChallengeDAL());
            ChallengeListViewModel CLVM = new ChallengeListViewModel();
            List<Challenge> challenges = challengeContainer.GetAllActiveChallenges();
            foreach (Challenge item in challenges)
            {
                ChallengeViewModel CVM = new ChallengeViewModel(item);
                CLVM.ChallengeViewModels.Add(CVM);
            }
            return View(CLVM);
        }

        public IActionResult Privacy()
        {
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}
