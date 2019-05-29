package com.gly.uppis.task.controller;

import com.gly.uppis.common.entity.Task;
import com.gly.uppis.common.response.Response;
import com.gly.uppis.mark.service.UserService;
import com.gly.uppis.task.controller.request.AssignTaskRequest;
import com.gly.uppis.task.controller.request.TaskPercentageRequest;
import com.gly.uppis.task.controller.request.TaskSearchParamRequest;
import com.gly.uppis.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author HanYehong
 * @date 2019/5/21 11:18
 */
@CrossOrigin
@RestController
@RequestMapping("task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/search")
    public Response searchTask(@RequestBody TaskSearchParamRequest param) {
        return Response.ok(taskService.searchTask(param));
    }

    @GetMapping("/listMyTask")
    public Response listMyTask() {
        return Response.ok(taskService.listMyTask());
    }

    @PostMapping("/commit")
    public Response commit(@RequestBody Task task) {
        taskService.commitTask(task);
        return Response.ok();
    }

    @PostMapping("/delete")
    public Response deleteTask(@RequestBody TaskSearchParamRequest param) {
        taskService.deleteTask(param.getTaskId());
        return Response.ok();
    }

    @PostMapping("/update")
    public Response updateTask(@RequestBody Task task) {
        taskService.updateTask(task);
        return Response.ok();
    }

    @PostMapping("/publish")
    public Response publishTask(@RequestBody Task task) {
        taskService.publishTask(task);
        return Response.ok();
    }

    @PostMapping("/getTask")
    public Response getTask(@RequestBody TaskSearchParamRequest param) {
        return Response.ok(taskService.getOneTask(param));
    }

    @GetMapping("/listSameDeptUsers")
    public Response listSameDeptUsers() {
        return Response.ok(taskService.listSameDeptUsers());
    }

    @PostMapping("/adjustPercentage")
    public Response adjustPercentage(@RequestBody TaskPercentageRequest param) {
        taskService.adjustPercentage(param.getTaskId(), param.getPercentage());
        return Response.ok();
    }

}
