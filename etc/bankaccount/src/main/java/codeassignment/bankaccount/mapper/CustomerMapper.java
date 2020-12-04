package codeassignment.bankaccount.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CustomerMapper {

  @Select("SELECT name FROM customer WHERE customer_number=#{customerNumber}")
  String getName(String customerNumber);
}
