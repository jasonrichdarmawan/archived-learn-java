package codeassignment.bankaccount.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMybatis
class AccountControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void getCurrentBalanceAccountNumberNotFound() throws Exception {
    mockMvc.perform(get("/account/55503"))
            .andExpect(status().isNotFound());
  }

  @Test
  void getCurrentBalancePathVariableContainAlphabet() throws Exception {
    mockMvc.perform(get("/account/a"))
            .andExpect(status().isBadRequest());
  }

  @Test
  void getCurrentBalanceAccountNumberFound() throws Exception {
    mockMvc.perform(get("/account/555001"))
            .andExpect(status().isOk());
  }

  @Test
  void postTransferFrom_And_To_Account_Number_IsEquals() throws Exception {
    HashMap<String, Object> requestBody = new HashMap<>();
    requestBody.put("to_account_number", "555001");
    requestBody.put("amount", 15000);
    mockMvc.perform(
            post("/account/555001/transfer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(requestBody)))
            .andExpect(status().isBadRequest());
  }

  @Test
  void postTransferTo_Account_NumberNotFound() throws Exception {
    HashMap<String, Object> requestBody = new HashMap<>();
    requestBody.put("to_account_number", "555003");
    requestBody.put("amount", 15000);
    mockMvc.perform(
            post("/account/555001/transfer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(requestBody)))
            .andExpect(status().isNotFound());
  }

  @Test
  void postTransferFrom_Account_NumberNotFound() throws Exception {
    HashMap<String, Object> requestBody = new HashMap<>();
    requestBody.put("to_account_number", "555001");
    requestBody.put("amount", 15000);
    mockMvc.perform(
            post("/account/555003/transfer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(requestBody)))
            .andExpect(status().isNotFound());
  }

  @Test
  void postTransferTo_Account_NumberContainAlphabet() throws Exception {
    HashMap<String, Object> requestBody = new HashMap<>();
    requestBody.put("to_account_number", "A");
    requestBody.put("amount", 15000);
    mockMvc.perform(
            post("/account/555002/transfer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(requestBody)))
            .andExpect(status().isBadRequest());
  }

  @Test
  void postTransferFrom_Account_NumberContainAlphabet() throws Exception {
    HashMap<String, Object> requestBody = new HashMap<>();
    requestBody.put("to_account_number", "555001");
    requestBody.put("amount", 15000);
    mockMvc.perform(
            post("/account/A/transfer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(requestBody)))
            .andExpect(status().isBadRequest());
  }

  @Test
  void postTransferInsufficientBalance() throws Exception {
    HashMap<String, Object> requestBody = new HashMap<>();
    requestBody.put("to_account_number", "555002");
    requestBody.put("amount", 1000000);
    mockMvc.perform(
            post("/account/555001/transfer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(requestBody)))
            .andExpect(status().isPaymentRequired());
  }

  @Test
  @Transactional
  void postTransferCreated() throws Exception {
    HashMap<String, Object> requestBody = new HashMap<>();
    requestBody.put("to_account_number", "555002");
    requestBody.put("amount", 10000);
    mockMvc.perform(
            post("/account/555001/transfer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(requestBody)))
            .andExpect(status().isCreated());
  }
}