package com.project.demo.controller;

import com.project.demo.entity.ParticipationRecord;
import com.project.demo.service.ParticipationRecordService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *参加记录：(ParticipationRecord)表控制层
 *
 */
@RestController
@RequestMapping("/participation_record")
public class ParticipationRecordController extends BaseController<ParticipationRecord,ParticipationRecordService> {

    /**
     *参加记录对象
     */
    @Autowired
    public ParticipationRecordController(ParticipationRecordService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
