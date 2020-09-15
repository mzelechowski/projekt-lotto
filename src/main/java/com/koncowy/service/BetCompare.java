package com.koncowy.service;

import com.koncowy.model.BetApp;
import com.koncowy.model.DuzyLotekModel;
import com.koncowy.model.MiniLotekModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BetCompare {
  public int checkBet(BetApp betApp, DuzyLotekModel duzyLotekModel) {
//    int hits = 0;
//    for (Integer i : betApp.getBet()) {
//      for (int j = 0; j < duzyLotekModel.getResult().length; j++) {
//        if (duzyLotekModel.getResult()[j] == i) hits++;
//      }
//    }
    return checkBet(betApp, duzyLotekModel.getResult());
  }

  private int checkBet(BetApp betApp, int[] integerList) {
    int hits = 0;
    for (Integer i : betApp.getBet()) {
      for (int j = 0; j < integerList.length; j++) {
        if (integerList[j] == i) hits++;
      }
    }
    return hits;
  }

  public int checkBet(BetApp betApp, MiniLotekModel miniLotekModel) {
    return checkBet(betApp, miniLotekModel.getResult());
  }

}
