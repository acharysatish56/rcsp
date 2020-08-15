package com.rcsp.root.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rcsp.root.common.ResponseType;
import com.rcsp.root.model.TestInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by satish on 09/08/20.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionValidationRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Description("checking the status of the 200")
    void validateTransactionWithStatusOk() throws Exception {
        mockMvc.perform(post("/validate").contentType("application/json")
                .content(objectMapper.writeValueAsString(TestInput.getTransactions())))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    @Description("validating Transactions with result SUCCESS ")
    void validateTransactionWithSucess() throws Exception {
        mockMvc.perform(post("/validate").contentType("application/json")
                .content(objectMapper.writeValueAsString(TestInput.getTransactions())))
                .andExpect(status().isOk())
				.andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result").value(ResponseType.SUCCESSFUL.name()));
    }

    @Test
    @Description("validating Transactions with result SUCCESS with negative mutation")
    void validateTransactionWithSucessWithNegativeMutation() throws Exception {
        mockMvc.perform(post("/validate").contentType("application/json")
                .content(objectMapper.writeValueAsString(TestInput.getTransactionsWithSuccessWithNegativeMutation())))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result").value(ResponseType.SUCCESSFUL.name()));
    }
    @Test
    @Description("validating Transactions with result DUPLICATE_REFERENCE ")
    void validateTransactionWithDuplicateReference() throws Exception {
        mockMvc.perform(post("/validate").contentType("application/json")
                .content(objectMapper.writeValueAsString(TestInput.getTransactionsWithDuplicateResult())))
                .andExpect(status().isOk())
				.andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result").value(ResponseType.DUPLICATE_REFERENCE.name()));
    }

    @Test
    @Description("validating Transactions with result INCORRECT_END_BALANCE ")
    void validateTransactionWithIncorrectEndBalance() throws Exception {
        mockMvc.perform(post("/validate").contentType("application/json")
                .content(objectMapper.writeValueAsString(TestInput.getTransactionsWithIncorrectEndBalance())))
                .andExpect(status().isOk())
				.andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result").value(ResponseType.INCORRECT_END_BALANCE.name()));
    }

    @Test
    @Description("validating Transactions with result INCORRECT_END_BALANCE with negative mutation")
    void validateTransactionWithIncorrectEndBalanceWithNegativeMutation() throws Exception {
        mockMvc.perform(post("/validate").contentType("application/json")
                .content(objectMapper.writeValueAsString(TestInput.getTransactionsWithIncorrectEndBalanceWithNegativeMutation())))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result").value(ResponseType.INCORRECT_END_BALANCE.name()));
    }


    @Test
    @Description("validating Transactions with result DUPLICATE_REFERENCE_INCORRECT_END_BALANCE ")
    void validateTransactionWithDuplicateReferenceIncorrectEndBalance() throws Exception {
        mockMvc.perform(post("/validate").contentType("application/json")
                .content(objectMapper.writeValueAsString(TestInput.getTransactionsWithDuplicateReferenceIncorrectBalance())))
                .andExpect(status().isOk())
				.andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result").value(ResponseType.DUPLICATE_REFERENCE_INCORRECT_END_BALANCE.name()));
    }

    @Test
    @Description("validating Transactions with result INTERNAL_SERVER_ERROR ")
    void validateTransactionWithInternalServerError() throws Exception {
        mockMvc.perform(post("/validate").contentType("application/json")
                .content(objectMapper.writeValueAsString(TestInput.getTransactionsWithInternalServerError())))
				.andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result").value(ResponseType.INTERNAL_SERVER_ERROR.name()));
    }

    @Test
    @Description("validating Transactions with result BAD_REQUEST ")
    void validateTransactionWithInternalBadRequest() throws Exception {
        mockMvc.perform(post("/validate").contentType("application/json")
                .content("[{}{}]"))
				.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result").value(ResponseType.BAD_REQUEST.name()));
    }
}
