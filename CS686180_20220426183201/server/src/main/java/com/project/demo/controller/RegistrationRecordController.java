package com.project.demo.controller;

import com.project.demo.entity.RegistrationRecord;
import com.project.demo.service.RegistrationRecordService;
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
 *报名记录：(RegistrationRecord)表控制层
 *
 */
@RestController
@RequestMapping("/registration_record")
public class RegistrationRecordController extends BaseController<RegistrationRecord,RegistrationRecordService> {

    /**
     *报名记录对象
     */
    @Autowired
    public RegistrationRecordController(RegistrationRecordService service) {
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
