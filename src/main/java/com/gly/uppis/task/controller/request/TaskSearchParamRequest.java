package com.gly.uppis.task.controller.request;

/**
 * @author GuLiyun
 * @date 2019/5/23 3:43
 */
public class TaskSearchParamRequest {
    private Integer status;

    private Integer taskId;

    private Integer userId;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
