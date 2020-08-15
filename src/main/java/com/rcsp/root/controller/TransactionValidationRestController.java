package com.rcsp.root.controller;

import com.rcsp.root.model.response.Result;
import com.rcsp.root.model.transaction.Transaction;
import com.rcsp.root.service.TransactionValidationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Created by satish on 08/08/20.
 */
@RestController
@RequestMapping(value = "/validate")
@Api(value = "transaction validator controller", tags = "controller has transaction validation related api's")
@Slf4j
public class TransactionValidationRestController {

    @Autowired
    private TransactionValidationService validationService;

    @ApiOperation(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            value = "validate customer transaction",
            notes = "validate customer transaction"
    )

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Optional<Result>> search(@ApiParam(value = "Transaction", name = "transactions")
                                                   @RequestBody final List<Transaction> transactions) {
        log.info("validation service started");
        return Optional.ofNullable(validationService.validate(transactions))
                .filter(Optional::isPresent)
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
}
