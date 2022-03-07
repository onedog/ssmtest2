package com.mytest.crud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mytest.crud.bean.Employee;
import com.mytest.crud.bean.Msg;
import com.mytest.crud.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    @ResponseBody
    public Msg getEmp(@PathVariable("id") Integer id){
        Employee employee=employeeService.getEmp(id);
        return Msg.success().add("emp",employee);
    }

    @RequestMapping(value = "/checkUser")
    @ResponseBody
    public Msg checkUser(@RequestParam("empName") String empName){
        String regx="(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";

        if(!empName.matches(regx)){
            return Msg.fail().add("va_msg","用户名需要为6-16位数字或2-5位中文");
        }
        boolean b=employeeService.checkUser(empName);
        if(b){
            return Msg.success();
        }else {
            return Msg.fail().add("va_msg","用户名重复，不可用");
        }
    }


    @PostMapping(value = "/emp")
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String,Object>map=new HashMap<>();
            for(FieldError fieldError : bindingResult.getFieldErrors() ){
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields",map);
        }else{
            employeeService.saveEmp(employee);
            return Msg.success();
        }
    }

    @PutMapping("/emp/{empId}")
    @ResponseBody
    public Msg updataEmp(Employee employee){
        employeeService.updataEmp(employee);
        return Msg.success();
    }


    @ResponseBody
    @DeleteMapping("/emp/{Ids}")
    public Msg deleteEmps(@PathVariable("Ids") String Ids){
        if(Ids.contains("-")){
            List<Integer> del_ids=new ArrayList<>();
            String[] str_ids = Ids.split("-");
            for(String string:str_ids){
                del_ids.add(Integer.parseInt(string));
            }
            employeeService.deleteBatch(del_ids);
        }else {
            Integer id=Integer.parseInt(Ids);
            employeeService.deleteEmp(id);
        }

        return Msg.success();
    }

    @GetMapping("/emps")
    @ResponseBody
    public Msg getEmpsWithJson(
            @RequestParam(value = "pn", defaultValue = "1" ) Integer pn) {
        PageHelper.startPage(pn, 5);
        List<Employee> emps = employeeService.getAll();
        PageInfo pageInfo = new PageInfo(emps, 5);

        return Msg.success().add("pageInfo", pageInfo);
    }
//    @RequestMapping("/emps")
//    public String getEmps(
//            @RequestParam(value = "pn", defaultValue = "1") Integer pn,
//            Model model) {
//        PageHelper.startPage(pn, 5);
//        List<Employee> emps = employeeService.getAll();
//        PageInfo page = new PageInfo(emps, 5);
//        model.addAttribute("pageInfo", page);
//
//        return "list";
//    }

}
