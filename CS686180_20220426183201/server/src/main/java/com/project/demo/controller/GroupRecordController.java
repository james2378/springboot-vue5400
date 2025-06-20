package com.project.demo.controller;

import com.project.demo.entity.GroupRecord;
import com.project.demo.service.GroupRecordService;
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
 *加团记录：(GroupRecord)表控制层
 *
 */
@RestController
@RequestMapping("/group_record")
public class GroupRecordController extends BaseController<GroupRecord,GroupRecordService> {

    /**
     *加团记录对象
     */
    @Autowired
    public GroupRecordController(GroupRecordService service) {
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
