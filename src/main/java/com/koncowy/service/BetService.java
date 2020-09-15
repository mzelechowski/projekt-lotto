package com.koncowy.service;

import com.koncowy.model.BetApp;
import com.koncowy.model.UserApp;
import com.koncowy.repository.BetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BetService {
  private BetRepository betRepository;

  public BetService(BetRepository betRepository) {
    this.betRepository = betRepository;
  }

  public Page<BetApp> getAllBets(UserApp user, Pageable pageable) {
    return betRepository.findAllByUser(user, pageable);
  }

  public void addBet(BetApp betApp) {
    betRepository.save(betApp);
  }

  public void deleteBet(BetApp betApp) {
    betRepository.delete(betApp);
  }

  public BetApp getBetById(Long id) {
    return betRepository.getOne(id);
  }

}
