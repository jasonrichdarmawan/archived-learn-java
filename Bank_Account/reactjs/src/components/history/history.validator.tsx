interface historyValidatorModel {
  Start: Date;
  End: Date;
}

export function historyValidator({
  Start,
  End,
}: historyValidatorModel): boolean {
  /**
   * expensive query prevention:
   * 1. return false
   *    a. Today === Start || Today === End
   *    b. Today < Start || Today < End
   *    c. Start > End
   *    d. Today - Start > 30 days.
   *    e. End - Start > 30 days
   * 4. verified
   */
  const Today = new Date(new Date().toISOString().split("T")[0]);

  if (Today.getTime() === Start.getTime() || Today.getTime() === End.getTime()) {
    return false;
  }

  if (Today.getTime() < Start.getTime() || Today.getTime() < End.getTime()) {
    return false;
  }

  if (Start.getTime() > End.getTime()) {
    return false;
  }

  if (Today.getTime() - Start.getTime() / (1000 * 3600 * 24) > 30) {
    return false;
  }

  if (End.getTime() - Start.getTime() / (1000 * 3600 * 24) > 30) {
    return false;
  }

  return true;
}
