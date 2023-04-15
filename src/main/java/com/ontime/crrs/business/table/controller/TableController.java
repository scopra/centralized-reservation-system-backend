package com.ontime.crrs.business.table.controller;

import com.ontime.crrs.persistence.table.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tables")
public class TableController {
    private final TableService tableService;


}
