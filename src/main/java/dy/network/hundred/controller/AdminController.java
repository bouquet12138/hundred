package dy.network.hundred.controller;

import dy.network.hundred.java_bean.AdminBean;
import dy.network.hundred.java_bean.BaseBean;

import dy.network.hundred.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping({"/admin_login"})
    public BaseBean adminLogin(@RequestBody AdminBean adminBean){
       return adminService.adminLogin(adminBean);
    }

}
