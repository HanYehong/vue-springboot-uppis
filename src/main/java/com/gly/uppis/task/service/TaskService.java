package com.gly.uppis.task.service;

import com.gly.uppis.common.entity.Task;
import com.gly.uppis.common.entity.User;
import com.gly.uppis.task.controller.request.AssignTaskRequest;
import com.gly.uppis.task.controller.request.TaskSearchParamRequest;
import com.gly.uppis.task.dao.result.TaskResult;

import java.util.List;

/**
 * @author GuLiyun
 * @date 2019/5/23 2:40
 */
public interface TaskService {
    List<TaskResult> listMyTask();

    void commitTask(Task task);

    void deleteTask(Integer taskId);

    void updateTask(Task task);

    void publishTask(Task task);

    List<TaskResult> searchTask(TaskSearchParamRequest param);

    TaskResult getOneTask(TaskSearchParamRequest param);

    List<User> listSameDeptUsers();

    void adjustPercentage(Integer taskId, Double percentage);
}
