package com.rcsp.root.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;

/**
 * Created by satish on 08/08/20.
 */

@Data
@AllArgsConstructor
public class ErrorRecord implements Comparable<ErrorRecord> {
    private Long reference;
    private String accountNumber;


    /**
     * added for removing the duplicate records
     * input - [{reference: 101, accountNumber: "123"}, {reference: 101, accountNumber: "123"}]
     * output - [{reference: 101, accountNumber: "123"}]
     *
     * @param record
     * @return
     */

    @Override
    public int compareTo(ErrorRecord record) {
        return Comparator.comparingLong(ErrorRecord::getReference)
                .thenComparing(ErrorRecord::getAccountNumber)
                .compare(this, record);
    }
}

