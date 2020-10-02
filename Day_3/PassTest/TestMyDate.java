public class TestMyDate {
  public static void main(String[] args) {
    MyDate my_birth = new MyDate(22, 7, 1964);
    MyDate the_next_week = my_birth.addDays(7);
    
    // from 22-7-1964
    MyDate yesterday = my_birth.minusDays(1);
    MyDate next_month = my_birth.addMonths(1);
    
    // check whether the current logic break if we addMonths 19 or more.
    MyDate handler_19_next_months = my_birth.addMonths(19);
    MyDate last_month = my_birth.minusMonths(1);

    // check whether the current logic break if we minusMonths 20 or less.
    MyDate handler_20_last_months = my_birth.minusMonths(20);
    MyDate next_year = my_birth.addYears(1);
    MyDate last_year = my_birth.minusYears(1);

    System.out.print(
      "from 22-7-1964" + '\n' +
      "the_next_week: " + the_next_week + '\n' +
      "yesterday: " + yesterday + '\n' +
      "next_month: " + next_month + '\n' +
      "handler_19_next_months: " + handler_19_next_months + '\n' +
      "last_month: " + last_month + '\n' +
      "handler_20_last_months: " + handler_20_last_months + '\n' +
      "next_year: " + next_year + '\n' +
      "last_year: " + last_year + '\n'
    );
  }
}