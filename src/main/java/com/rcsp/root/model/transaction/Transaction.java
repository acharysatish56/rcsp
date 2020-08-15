package com.rcsp.root.model.transaction;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by satish on 08/08/20
 */
@Data
public class Transaction {

    @ApiModelProperty(notes = "transaction number of the customer which needs to be unique", required = true)
    private Long transNumber;

    @ApiModelProperty(notes = "customer account number", required = true)
    private String accNumber;

    @ApiModelProperty(notes = "initial amount of the customer", required = true)
    private BigDecimal startBalance;

    @ApiModelProperty(notes = "mutation amount (positive value not following with + symbol)", required = true, example = "either 12 or -12 ")
    private BigDecimal mutation;

    @ApiModelProperty(notes = "description of the transaction", required = true)
    private String description;

    @ApiModelProperty(notes = "after adding start balance with mutation result amount", required = true)
    private BigDecimal endBalance;

}
