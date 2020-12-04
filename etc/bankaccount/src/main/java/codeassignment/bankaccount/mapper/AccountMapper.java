package codeassignment.bankaccount.mapper;

import codeassignment.bankaccount.model.AccountModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper {

  @Select("SELECT customer_number, balance FROM account WHERE account_number=#{accountNumber}")
  AccountModel getCurrentBalance(String accountNumber);
}
