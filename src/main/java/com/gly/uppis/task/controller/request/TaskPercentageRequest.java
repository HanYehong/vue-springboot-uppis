package com.gly.uppis.task.controller.request;

/**
 * @author GuLiyun
 * @date 2019/5/25 15:28
 */
public class TaskPercentageRequest {
    private Integer taskId;

    private Double percentage;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
