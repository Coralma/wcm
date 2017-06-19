package com.coral.user.action;

import com.coral.biz.dao.UserDao;
import com.coral.biz.model.UserModel;
import com.coral.biz.service.UserService;
import com.coral.biz.service.impl.UserServiceImpl;
import com.coral.core.action.BaseAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserAction extends BaseAction {

    @Resource(name = UserService.SPRING_BEAN_NAME)
    private UserService userService;

    @RequestMapping(value = "/loadUsers", method = RequestMethod.POST)
    public @ResponseBody
    List<UserModel> loadUsers() {
        return userService.loadUsers();
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, String> saveUser(@RequestBody UserModel userModel) {
        try {
            userService.saveUser(userModel);
        } catch (Exception e) {
            return getFailedMessage(e.getMessage());
        }
        return SUCCESS;
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, String> deleteUser(@RequestBody String deletedId) {
        try {
            userService.deleteUser(deletedId);
        } catch (Exception e) {
            return getFailedMessage(e.getMessage());
        }
        return SUCCESS;
    }
}
