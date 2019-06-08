package com.lgx.springmvc.activity.service;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivitiService {
    //注入为我们自动配置好的服务
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    //开始流程，可以传入申请者的id以及公司的id
    public void startProcess( Long personId, Long compId){
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("personId", personId);
        variables.put("compId", compId);
        runtimeService.startProcessInstanceByKey("joinProcess", variables);
    }

    @Transactional
    public void startProcess() {
        runtimeService.startProcessInstanceByKey("oneTaskProcess");
    }
    //获得某个人的任务别表
    @Transactional
    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskCandidateUser(assignee).list();
//        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }

    //完成任务
    public void completeTasks(Boolean joinApproved, String taskId){
        Map<String, Object> taskVariables = new HashMap<String, Object>();
        taskVariables.put("joinApproved", joinApproved);
        taskService.complete(taskId, taskVariables);
    }

    public void startProcesses(String key, String bKey) {

    }
}
