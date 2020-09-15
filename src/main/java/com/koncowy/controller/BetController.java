package com.koncowy.controller;

import com.koncowy.exception.ResourceNotFoundException;
import com.koncowy.model.BetApp;
import com.koncowy.model.DuzyLotekModel;
import com.koncowy.model.MiniLotekModel;
import com.koncowy.model.UserApp;
import com.koncowy.service.BetCompare;
import com.koncowy.service.BetService;
import com.koncowy.service.LOTEKService;
import com.koncowy.service.UserAppService;
import com.koncowy.viewmodel.BetWithHits;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@PreAuthorize("hasAnyRole('ADMIN','USER')")
@Controller
@RequestMapping("/bets")
public class BetController {

  private UserAppService userAppService;
  private BetService betService;
  private final LOTEKService lotekService;
  private BetCompare betCompare;


  public BetController(UserAppService userAppService, BetService betService, LOTEKService lotekService, BetCompare betCompare) {
    this.userAppService = userAppService;
    this.betService = betService;
    this.lotekService = lotekService;
    this.betCompare = betCompare;
  }

  @GetMapping("/mybets")
  public String getMyBets(Model model, Pageable pageable, @RequestParam(required = false) String drawDate) {
    DuzyLotekModel bigResult = lotekService.getLotekResult(drawDate, 1).get(0);
    MiniLotekModel miniResult = lotekService.getMiniResult(drawDate, 1).get(0);
    UserApp userApp = userAppService.getCurrentUser();
    Page<BetApp> page = betService.getAllBets(userApp, pageable);

    List<BetApp> betAppList = page.getContent();
    List<Integer> hitList = new ArrayList<>();
    for (BetApp b : betAppList) {
      if (b.getGame().equalsIgnoreCase("lotto") || b.getGame().equalsIgnoreCase("lotto plus")) {
        hitList.add(betCompare.checkBet(b, bigResult));
      } else {

        hitList.add(betCompare.checkBet(b, miniResult));
      }
    }
    model.addAttribute("bets", new BetWithHits(page, hitList));
    model.addAttribute("formData", new BetApp());
    return "mybets";
  }


  @PostMapping("/add")
  public String addBet(@ModelAttribute BetApp formData, BindingResult result) {
    if (result.hasErrors()) {
      return "error";
    }
    UserApp userApp = userAppService.getCurrentUser();
    formData.setUser(userApp);
    betService.addBet(formData);
    return "redirect:/bets/mybets";
  }

  @GetMapping("/delete")
  public String deleteBet(@RequestParam(required = true) Long id) {
    UserApp userApp = userAppService.getCurrentUser();
    BetApp betApp = betService.getBetById(id);
    if (betApp.getUser() == userApp) {
      betService.deleteBet(betApp);
    } else {
      throw new ResourceNotFoundException();
    }
    return "redirect:/bets/mybets";
  }
}
