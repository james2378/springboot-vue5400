package com.project.demo.controller;

import com.project.demo.entity.SchoolLabor;
import com.project.demo.service.SchoolLaborService;
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
 *学校劳动：(SchoolLabor)表控制层
 *
 */
@RestController
@RequestMapping("/school_labor")
public class SchoolLaborController extends BaseController<SchoolLabor,SchoolLaborService> {

    /**
     *学校劳动对象
     */
    @Autowired
    public SchoolLaborController(SchoolLaborService service) {
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
