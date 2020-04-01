package com.lgx.springmvc.activity.service;

import com.lgx.springmvc.activity.entity.Comp;
import com.lgx.springmvc.activity.entity.Person;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class JoinService {

    //加入公司操作，可从DelegateExecution获取流程中的变量
    public void joinGroup(DelegateExecution execution){
        Boolean bool = (Boolean) execution.getVariable("joinApproved");
        if(bool){
            Long personId = (Long) execution.getVariable("personId");
            Long compId = (Long) execution.getVariable("compId");
            Comp comp = new Comp("XXIt公司");
            Person person = new Person("Tom");
            person.setComp(comp);
            System.out.println("加入组织成功");
        } else {
            System.out.println("加入组织失败");
        }
    }
    //获取符合条件的审批人，演示这里写死，使用应用使用实际代码
    public List<String> findUsers(DelegateExecution execution){
        return Arrays.asList("admin","wtr");
    }
}
