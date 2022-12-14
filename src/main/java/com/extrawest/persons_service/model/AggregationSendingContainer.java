package com.extrawest.persons_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AggregationSendingContainer {
    private AggregationSendingOperation aggregationSendingOperation;
    private Object object;
}
