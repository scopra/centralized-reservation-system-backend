package com.ontime.crrs.business.table.processor;

import com.ontime.crrs.persistence.table.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TableProcessor {
    private final TableService tableService;

}