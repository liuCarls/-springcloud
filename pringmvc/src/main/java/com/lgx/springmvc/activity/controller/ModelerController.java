package com.lgx.springmvc.activity.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lgx.springmvc.activity.reqentity.DeploymentResponse;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/31.
 */
//@Controller
//@RequestMapping("models")
public class ModelerController {

    @Autowired
    RepositoryService repositoryService;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/newModel")
    public String createFlow() throws UnsupportedEncodingException{

        //初始化一个空模型
        Model model = repositoryService.newModel();
        //设置一些默认信息
        String name = "new-process";
        String description = "";
        int revision = 1;
        String key = "process";

        ObjectNode modelNode = objectMapper.createObjectNode();
//        modelNode.put(ModelDataJsonConstants.MODEL_NAME,name);
//        modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
//        modelNode.put(ModelDataJsonConstants.MODEL_REVISION, revision);

        model.setName(name);
        model.setKey(key);
        model.setMetaInfo(modelNode.toString());

        repositoryService.saveModel(model);
        String id = model.getId();

        //完善ModelEditorSource
        ObjectNode editorNode = objectMapper.createObjectNode();
        editorNode.put("id", "canvas");
        editorNode.put("resourceId", "canvas");
        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace",
                "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.put("stencilset", stencilSetNode);

        repositoryService.addModelEditorSource(id,editorNode.toString().getBytes("utf-8"));
        return "redirect:/editor?modelId="+id;
    }


    @ResponseBody
    @RequestMapping(value = "/flowList")
    public List<Model> listFlow(){
        List<Model> flowList = repositoryService.createModelQuery().list();
        return flowList;
    }

    @ResponseBody
    @RequestMapping(value = "/deployList")
    public List<DeploymentResponse> deployList(){
        List<Deployment> deployments = repositoryService.createDeploymentQuery().list();
        List<DeploymentResponse> deployList = new ArrayList<>();
        for (Deployment deployment : deployments){
            deployList.add(new DeploymentResponse(deployment));
        }
        return deployList;
    }


    @ResponseBody
    @RequestMapping(value = "/flowDelete")
    public void flowDelete(@RequestParam(name = "id") String id){
        System.out.println("id = "+id);
        repositoryService.deleteModel(id);
    }

    @ResponseBody
    @RequestMapping(value = "/deploy")
    public Object deploy(@RequestParam(name = "id") String id) throws Exception{
        //获取模型
        Model modelData = repositoryService.getModel(id);
        byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());
        if (null == bytes){
            return "模型数据为空，请先设计流程并成功保存，再进行发布。";
        }
        JsonNode modelNode = new ObjectMapper().readTree(bytes);
//        BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
//        if (model.getProcesses().size() == 0){
//            return "数据模型不符合要求，请至少设计一条主线程流。";
//        }
//        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);

        //发布流程
        String processName = modelData.getName() + ".bpmn20.xml";
//        Deployment deployment = repositoryService.createDeployment()
//                .name(modelData.getName())
//                .addString(processName, new String(bpmnBytes, "UTF-8"))
//                .deploy();
//        modelData.setDeploymentId(deployment.getId());
        repositoryService.saveModel(modelData);
        return "success";
    }


    @ResponseBody
    @RequestMapping(value = "/deleteDeploy")
    public void deleteDeploy(@RequestParam(name = "id") String id){
        repositoryService.deleteDeployment(id);
    }
}
