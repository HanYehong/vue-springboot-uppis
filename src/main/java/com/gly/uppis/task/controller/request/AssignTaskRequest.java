package com.gly.uppis.task.controller.request;

import com.gly.uppis.common.entity.Task;

import java.util.List;

/**
 * @author GuLiyun
 * @date 2019/5/24 10:14
 */
public class AssignTaskRequest {
    private List<Task> taskList;

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
