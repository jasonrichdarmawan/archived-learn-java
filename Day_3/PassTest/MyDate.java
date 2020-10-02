public class MyDate {
  private int day = 1;
  private int month = 1;
  private int year = 2000;

  public MyDate(int day, int month, int year) {
    // this.day reference to the instance attribute.
    this.day = day;
    this.month = month;
    this.year = year;
  }

  public MyDate(MyDate date) {
    this.day = date.day;
    this.month = date.month;
    this.year = date.year;
  }

  public void setDay(int newValue) {
    day = newValue;
  }

  public MyDate addDays(int moreDays) {
    // reference to the caller Object / does not create new Object.
    MyDate newDate = new MyDate(this);
    newDate.day = newDate.day + moreDays;
    // Not Yet Implemented: wrap around code...
    return newDate;
  }

  public MyDate minusDays(int lessDays) {
    // similar to the method addMonths.
    MyDate newDate = new MyDate(this);
    newDate.day = newDate.day - lessDays;
    return newDate;
  }

  public MyDate addMonths(int moreMonths) {
    // similar to the method addMonths.
    MyDate newDate = new MyDate(this);

    // handler
    // check whether handler is necessary
    // if newDate.month + moreMonths is not more than 12
    // then just calculate it normally.
    if (newDate.month + moreMonths > 12) {
      // always return newDate.month below 12.
      newDate.month = (newDate.month + moreMonths - 12) % 12;

      // TODO: quick fix, otherwise refactor to use the double data type
      // with Math.ceil();
      if (moreMonths < 12) {
        newDate.year = newDate.year + 1;
      } else {
        newDate.year = newDate.year + 1 + (moreMonths / 12);
      }

    } else {
      newDate.month = newDate.month + moreMonths;
    }

    return newDate;
  }

  public MyDate minusMonths(int lessMonths) {
    // similar to the method addMonths.
    MyDate newDate = new MyDate(this);

    // handler
    // similar to handler on method addMonths.
    if (newDate.month - lessMonths < 0) {
      // similar to the handler on method addMonths.
      newDate.month = 12 - (lessMonths - newDate.month) % 12;
      
      // similar to the handler on method addMonths.
      if (lessMonths < 12) {
        newDate.year = newDate.year - 1;
      } else {
        newDate.year = newDate.year - 1 - (lessMonths / 12);
      }

    } else {
      newDate.month = newDate.month - lessMonths;
    }

    return newDate;
  }

  public MyDate addYears(int moreYears) {
    // similar to the method addMonths.
    MyDate newDate = new MyDate(this);
    newDate.year = newDate.year + moreYears;
    return newDate;
  }

  public MyDate minusYears(int lessYears) {
    // similar to the method addMonths.
    MyDate newDate = new MyDate(this);
    newDate.year = newDate.year - lessYears;
    return newDate;
  }

  public String toString() {
    String retString = "" + day + "-" + month + "-" + year;
    return retString;
  }
}