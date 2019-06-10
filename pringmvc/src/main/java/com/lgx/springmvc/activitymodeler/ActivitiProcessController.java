package com.lgx.springmvc.activitymodeler;

import com.lgx.springmvc.activity.service.ActivitiService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.annotation.Resource;

//@Controller
//@RequestMapping("/activiti")
public class ActivitiProcessController {
    @Resource
    private ActivitiService activitiServiceImpl;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
//    @Autowired
//    private ActivitiVariableService activitiVariableService;

    //启动流程---215001
    @RequestMapping("/start/{key}")
    public String startProcess(@PathVariable("key") String key
            , RedirectAttributesModelMap modelMap) {
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("userId",userIdSl);
//        map.put("businessKey",business_key);
//        String business_key = key + ":" + key;
        //
//        ActivitiVariable activitiVariable = new ActivitiVariable();
//        int resul=activitiVariableService.insert(activitiVariable);
        int resul=1;
        if(resul==1){
            modelMap.addFlashAttribute("info","流程启动成功！");
//            String business_key = key+":"+activitiVariable.getId();
            String business_key = key+":"+2;
            activitiServiceImpl.startProcesses(key,business_key);
        }else{
            modelMap.addFlashAttribute("info","流程启动失败！");
        }
        return "redirect:/models/modelList";
    }

    //获取受理员任务列表
//    @RequestMapping("/queryTaskSl")
//    public String findTasksForSL(ModelMap modelMap, FormData formData) {
//        List<Task> lists = activitiServiceImpl.findTasksByUserId(formData.getUserId());
//        modelMap.addAttribute("tasks",lists);
//        modelMap.addAttribute("userId",formData.getUserId());
//        return "model/taskList";
//    }

//    @RequestMapping("/form")
//    public String form(FormData formData,ModelMap modelMap){
//        Task task=activitiServiceImpl.findTaskById(formData.getId());
//        modelMap.addAttribute("data",formData);
//        modelMap.addAttribute("task",task);
//        if(StringUtils.isNotEmpty(task.getFormKey())){
//            return "activitiForm/"+task.getFormKey();
//        }
//        return "model/form";
//    }


    //受理员受理数据
//    @RequestMapping("/completeTaskSl")
//    public String completeTasksForSL(ModelMap modelMap,FormData formData) {
//        activitiServiceImpl.completeTask(formData.getId(), formData.getUserId(), formData.getAttr1());//受理后，任务列表数据减少
//        return findTasksForSL(modelMap,formData);
//    }
}
