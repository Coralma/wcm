package com.coral.biz.action;

import com.coral.biz.model.CodeTableModel;
import com.coral.biz.service.CodeTableService;
import com.coral.biz.service.UserService;
import com.coral.core.action.BaseAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/codeTable")
public class CodeTableAction extends BaseAction {

    @Resource(name = CodeTableService.SPRING_BEAN_NAME)
    private CodeTableService userService;

    @RequestMapping(value = "/loadCodeTables", method = RequestMethod.POST)
    public @ResponseBody
    List<CodeTableModel> loadCodeTable() {
        return userService.loadCodeTable();
    }

}
