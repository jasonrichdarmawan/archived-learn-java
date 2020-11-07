package com.example.bankaccount.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class HistoryValidatorService {

  public boolean validate(LocalDate Start, LocalDate End) {
    /**
     * expensive query prevention:
     * 1. return false
     *    a. Today === Start || Today === End
     *    b. Today < Start || Today < End
     *    c. Start > End
     *    d. Today - Start < 30 days.
     *    e. End - Start < 30 days
     * 4. verified
     */
    LocalDate Today = LocalDate.now();

    if (Today.equals(Start) || Today.equals(End)) {
      return false;
    }

    if (Today.compareTo(Start) < 0 || Today.compareTo(End) < 0) {
      return false;
    }

    if (Start.compareTo(End) > 0) {
      return false;
    }

    if (DAYS.between(Start, Today) > 30) {
      return false;
    }

    if (DAYS.between(Start, End) > 30) {
      return false;
    }

    return true;
  }
}
