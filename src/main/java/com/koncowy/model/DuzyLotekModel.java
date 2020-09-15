package com.koncowy.model;

import java.util.Arrays;

public class DuzyLotekModel {
  private String date;
  private String game;
  private String number;
  private int[] result = new int[6];


  public DuzyLotekModel() {
  }

  public DuzyLotekModel(String date, String game, String number, int[] result) {
    this.date = date;
    this.game = game;
    this.number = number;
    this.result = result;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getGame() {
    return game;
  }

  public void setGame(String game) {
    this.game = game;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public int[] getResult() {
    return result;
  }

  public void setResult(int[] result) {
    this.result = result;
  }

  @Override
  public String toString() {
    return "DuzyLotekModel{" +
        "date='" + date + '\'' +
        ", game='" + game + '\'' +
        ", number='" + number + '\'' +
        ", result=" + Arrays.toString(result) +
        '}';
  }
}
