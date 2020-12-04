package codeassignment.bankaccount.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.math.BigDecimal;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountMapperTest {

  @Autowired
  private AccountMapper accountMapper;

  @Test
  void updateBalance() {
    int rowsAffected = accountMapper.updateBalance(new BigDecimal(15000),"555001");
    BigDecimal balance = accountMapper.getBalance("555001");
    Assertions.assertThat(rowsAffected).isEqualTo(1);
    Assertions.assertThat(balance).isEqualByComparingTo(new BigDecimal(15000));
  }
}