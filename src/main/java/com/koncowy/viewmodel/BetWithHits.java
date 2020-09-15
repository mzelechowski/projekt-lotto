package com.koncowy.viewmodel;

import com.koncowy.model.BetApp;
import org.springframework.data.domain.Page;

import java.util.List;

public class BetWithHits {
  private Page<BetApp> page;
  private List<Integer> hits;

  public BetWithHits(Page<BetApp> page, List<Integer> hits) {
    this.page = page;
    this.hits = hits;
  }

  public Page<BetApp> getPage() {
    return page;
  }

  public void setPage(Page<BetApp> page) {
    this.page = page;
  }

  public List<Integer> getHits() {
    return hits;
  }

  public void setHits(List<Integer> hits) {
    this.hits = hits;
  }
}
