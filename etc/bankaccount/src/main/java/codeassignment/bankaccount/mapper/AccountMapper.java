package codeassignment.bankaccount.mapper;

import codeassignment.bankaccount.model.AccountModel;
import codeassignment.bankaccount.model.PostTransferModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

@Mapper
public interface AccountMapper {

  @Select("SELECT customer_number, balance FROM account WHERE account_number=#{accountNumber}")
  AccountModel getCustomerNumberAndBalance(String accountNumber);

  @Select("SELECT balance FROM account WHERE account_number=#{accountNumber}")
  BigDecimal getBalance(String accountNumber);

  @Select("SELECT EXISTS(SELECT 1 FROM account WHERE account_number=#{accountNumber})")
  boolean exists(String accountNumber);

  @Update("UPDATE account SET balance=#{balance} WHERE account_number=#{accountNumber}")
  int updateBalance(BigDecimal balance, String accountNumber);

  @Update("UPDATE account SET balance=balance+#{amount} WHERE account_number=#{to_account_number}")
  int addBalance(PostTransferModel requestBody);
}
