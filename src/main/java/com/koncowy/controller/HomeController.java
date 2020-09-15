package com.koncowy.controller;

import com.koncowy.model.UserApp;
import com.koncowy.service.LOTEKService;
import com.koncowy.service.UserAppService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
  private final LOTEKService lotekService;
  private final UserAppService userAppService;

  public HomeController(LOTEKService lotekService, UserAppService userAppService) {
    this.lotekService = lotekService;
    this.userAppService = userAppService;
  }

  @GetMapping("/")
  public String getHomePage(){
    return "index";
  }

  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  @GetMapping("/duzyLotek")
  public String getDuzyLotekPage(
      @RequestParam (required = false) String drawDate,
      @RequestParam(required = false) Integer quantity,
      Model model){
    if (StringUtils.isNotBlank(drawDate) && quantity!=0) {
      model.addAttribute("seriers", lotekService.getLotekResult(drawDate, quantity));
    } else {
      model.addAttribute("seriers", lotekService.getLotekResult("",0));
    }
    return "duzylotek";
  }

  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  @GetMapping("/miniLotto")
  public String getMiniLottoPage(
        @RequestParam (required = false) String drawDate,
        @RequestParam(required = false) Integer quantity,
        Model model){
      if (StringUtils.isNotBlank(drawDate) && quantity!=0) {
        model.addAttribute("seriers", lotekService.getMiniResult(drawDate, quantity));
      } else {
        model.addAttribute("seriers", lotekService.getMiniResult("",0));
      }
    return "minilotto";
  }
  @GetMapping("/login")
  public String getLoginPage() {
    return "/login";
  }

  @GetMapping("/register")
  public String getRegisterPage() {
    return "register";
  }

  @PostMapping("/register-new-user")
  public String getRegisterNewUser(@ModelAttribute UserApp userApp) {
    String result = userAppService.registerNewUser(userApp);
    System.out.println(result);
    return "redirect:/?info=" + result;
  }




}
