package com.rcsp.root.model.response;

import com.rcsp.root.common.ResponseType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 * Created by satish on 08/08/20.
 */
@Data
@AllArgsConstructor
public class Result {
    private ResponseType result;
    private Set<ErrorRecord> errorRecords;
}
