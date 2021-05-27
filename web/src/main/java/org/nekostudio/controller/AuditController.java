package org.nekostudio.controller;

import io.swagger.annotations.ApiOperation;
import org.nekostudio.common.JsonResult;
import org.nekostudio.es.repository.MyPage;
import org.nekostudio.es.repository.Record;
import org.nekostudio.es.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author neko
 */
@RestController
public class AuditController {

    @Autowired
    private RecordRepository recordRepository;

    @ApiOperation("获取审计记录接口")
    @GetMapping(value = "audits",params = {"page_size","current"})
    public JsonResult getPage(@RequestParam("page_size") int pageSize,@RequestParam("current") int current) {
        PageRequest pageRequest = PageRequest.of(current, pageSize);
        Page<Record> all = recordRepository.findAll(pageRequest);
        MyPage<Record> records = new MyPage<>(all);
        return JsonResult.ok(records);
    }
}
