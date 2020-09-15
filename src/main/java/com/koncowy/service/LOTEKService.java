package com.koncowy.service;

import com.koncowy.model.DuzyLotekModel;
import com.koncowy.model.MiniLotekModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LOTEKService {
  public LOTEKService() {
  }
  public List<DuzyLotekModel> getLotekResult(String drawDate, int quantity) {
    RestTemplate restTemplate = new RestTemplate();

    //////////////////////////////
    URL httpPath = null;
    List<DuzyLotekModel> zaklad = new ArrayList<>();
    try {
      if (StringUtils.isNotBlank(drawDate) && quantity!=0 ) {

        httpPath = new URL("https://www.lotto.pl/lotto/wyniki-i-wygrane/date,"
            + drawDate +
            ","
            + quantity);
      } else {
        httpPath = new URL("https://www.lotto.pl/lotto/wyniki-i-wygrane");
      }
      System.out.println(httpPath);
      BufferedReader in = new BufferedReader(new InputStreamReader(httpPath.openStream()));
      String inputLine;
      Pattern pLotto = Pattern.compile("<div class=\"scoreline-item circle\" data-v-([a-zA-Z0-9]+)>");
      Pattern pName = Pattern.compile("<p class=\"result-item__name\" data-v-([a-zA-Z0-9]+)>(Lot*[a-zA-Z\\s]+)</p>");
      Pattern pLosNumber = Pattern.compile("<p class=\"result-item__number\" data-v-([a-zA-Z0-9]+)>\\d{4}</p>");
      Pattern pEnd = Pattern.compile("</div> </div> <!----> <!----></div> <!----></div>");
      Pattern pData = Pattern.compile("\\d{1,2}\\.\\d{1,2}\\.\\d{4}");
      //PrintWriter writer = new PrintWriter(new FileWriter(pathName + fileName4));
      int linia = 0;
      String outputLine = "";
      String dataLos = "";
      String pomName = "";
      String helpNum = "";
      int[] result = new int[6];
      int resultCount = 0;
      while ((inputLine = in.readLine()) != null) {
        Matcher mData = pData.matcher(inputLine);
        if (mData.find()) {
          dataLos = mData.group(0);
          outputLine = dataLos;
        }
        /// znajdywanie nazwy
        Matcher mName = pName.matcher(inputLine);
        if (mName.find()) {
          if (mName.group(0).contains("Lotto Plus")) {
            //pomName = System.getProperty("line.separator") + dataLos + "#Lotto Plus#";
            pomName = "Lotto Plus";
          } else if (mName.group(0).contains("Lotto")) {
            // writer.println("Lotto");
            pomName = "Lotto";
          }
          outputLine = outputLine + pomName;
        }
        ////Znajdowanie numeru losowania
        Matcher mNumber = pLosNumber.matcher(inputLine);
        if (mNumber.find()) {
          helpNum = mNumber.group(0);
          helpNum = helpNum.replaceAll("<p class=\"result-item__number\" data-v-[a-zA-Z0-9]+>", "");
          helpNum = helpNum.replaceAll("</p>", "");
          //writer.println(pom);
          if (outputLine.endsWith("#"))
            outputLine = outputLine + helpNum + "#";
        }
        ////znajdowanie liczb wylosowanych
        Matcher mLotto = pLotto.matcher(inputLine);
        if (mLotto.find()) {
          inputLine = in.readLine();
          for (int i = 0; i < 50; i++) inputLine = inputLine.replace(" ", "");
//          if (!outputLine.endsWith("#"))
//            outputLine = outputLine + ",";
//          outputLine = outputLine + inputLine;
          //System.out.println(inputLine);

          result[resultCount] = Integer.parseInt(inputLine);
          resultCount++;
          if (resultCount >= 6) {
            zaklad.add(new DuzyLotekModel(dataLos, pomName, helpNum, result.clone()));
            resultCount = 0;
          }
        }
      }
      System.out.println(zaklad);
      in.close();
      //writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    //////////////////////////////////////////////////////////////////////////////////////////
    return zaklad;
  }

  public List<MiniLotekModel> getMiniResult(String drawDate, int quantity) {
    RestTemplate restTemplate = new RestTemplate();

    //////////////////////////////
    URL httpPath = null;
    List<MiniLotekModel> zaklad = new ArrayList<>();
    try {
      if (StringUtils.isNotBlank(drawDate) && quantity!=0) {

        httpPath = new URL("https://www.lotto.pl/mini-lotto/wyniki-i-wygrane/date,"
            + drawDate +
            ","
            + quantity);
      } else {
        httpPath = new URL("https://www.lotto.pl/mini-lotto/wyniki-i-wygrane");
      }
      System.out.println(httpPath);
      BufferedReader in = new BufferedReader(new InputStreamReader(httpPath.openStream()));
      String inputLine;
      Pattern pLotto = Pattern.compile("<div class=\"scoreline-item circle\" data-v-([a-zA-Z0-9]+)>");
      Pattern pName = Pattern.compile("<p class=\"result-item__name\" data-v-([a-zA-Z0-9]+)>(Mini*[a-zA-Z\\s]+)</p>");
      Pattern pLosNumber = Pattern.compile("<p class=\"result-item__number\" data-v-([a-zA-Z0-9]+)>\\d{4}</p>");
      Pattern pEnd = Pattern.compile("</div> </div> <!----> <!----></div> <!----></div>");
      Pattern pData = Pattern.compile("\\d{1,2}\\.\\d{1,2}\\.\\d{4}");
      //PrintWriter writer = new PrintWriter(new FileWriter(pathName + fileName4));
      int linia = 0;
      String outputLine = "";
      String dataLos = "";
      String pomName = "";
      String helpNum = "";
      int[] result = new int[5];
      int resultCount = 0;
      while ((inputLine = in.readLine()) != null) {
        Matcher mData = pData.matcher(inputLine);
        if (mData.find()) {
          dataLos = mData.group(0);
          outputLine = dataLos;
        }
        /// znajdywanie nazwy
        Matcher mName = pName.matcher(inputLine);
        if (mName.find()) {
          if (mName.group(0).contains("Mini Lotto")) {

            pomName = "Mini Lotto";
          }
          outputLine = outputLine + pomName;
        }
        ////Znajdowanie numeru losowania
        Matcher mNumber = pLosNumber.matcher(inputLine);
        if (mNumber.find()) {
          helpNum = mNumber.group(0);
          helpNum = helpNum.replaceAll("<p class=\"result-item__number\" data-v-[a-zA-Z0-9]+>", "");
          helpNum = helpNum.replaceAll("</p>", "");
          //writer.println(pom);
          if (outputLine.endsWith("#"))
            outputLine = outputLine + helpNum + "#";
        }
        ////znajdowanie liczb wylosowanych
        Matcher mLotto = pLotto.matcher(inputLine);
        if (mLotto.find()) {
          inputLine = in.readLine();
          for (int i = 0; i < 50; i++) inputLine = inputLine.replace(" ", "");
//          if (!outputLine.endsWith("#"))
//            outputLine = outputLine + ",";
//          outputLine = outputLine + inputLine;
          //System.out.println(inputLine);

          result[resultCount] = Integer.parseInt(inputLine);
          resultCount++;
          if (resultCount >= 5) {
            zaklad.add(new MiniLotekModel(dataLos, pomName, helpNum, result.clone()));
            resultCount = 0;
          }
        }
      }
      System.out.println(zaklad);
      in.close();
      //writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    //////////////////////////////////////////////////////////////////////////////////////////
    return zaklad;
  }

}
