package com.lgx.springmvc.activity.controller;

import com.lgx.springmvc.activity.service.ActivitiService;
import com.lgx.springmvc.activity.service.MyService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MyRestController {
    @Autowired
    private MyService myService;

    @RequestMapping(value="/process", method= RequestMethod.GET)
    public void startProcessInstance() {
        myService.startProcess();
    }
    //获取当前人的任务
    @RequestMapping(value="/tasks", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<TaskRepresentation> getTasks(@RequestParam String assignee) {
        List<Task> tasks = myService.getTasks(assignee);
        List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }
        return dtos;
    }

    @Autowired
    private ActivitiService activitiService;

    //开启流程实例
    @RequestMapping(value="/process/{personId}/{compId}", method= RequestMethod.GET)
    public void startProcessInstance(@PathVariable Long personId,
                                     @PathVariable Long compId){
        activitiService.startProcess(personId,compId);
    }

    //获取当前人的任务
    @RequestMapping(value="/tasks1", method= RequestMethod.GET)
    public List<TaskRepresentation> getTasks1(@RequestParam String assignee){
        List<Task> tasks = activitiService.getTasks(assignee);
        List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
        for(Task task : tasks){
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }
        return dtos;
    }

    //完成任务
    @RequestMapping(value="/complete/{joinApproved}/{taskId}", method= RequestMethod.GET)
    public String complete(@PathVariable Boolean joinApproved,@PathVariable String taskId){
        activitiService.completeTasks(joinApproved,taskId);
        return"ok";
    }

    //Task的dto
    static class TaskRepresentation {
        private String id;
        private String name;

        public TaskRepresentation(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

    }
}
