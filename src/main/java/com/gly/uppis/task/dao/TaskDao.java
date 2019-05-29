package com.gly.uppis.task.dao;

import com.gly.uppis.common.entity.Task;
import com.gly.uppis.task.controller.request.TaskSearchParamRequest;
import com.gly.uppis.task.dao.result.TaskResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author GuLiyun
 * @date 2019/5/23 2:57
 */
public interface TaskDao {
    List<TaskResult> selectByUserIdAndCreator(@Param("userId")Integer userId);

    List<TaskResult> selectByParam(TaskSearchParamRequest param);

    List<Task> selectAllSubTask(@Param("taskId")Integer taskId);

    List<Task> selectAllParentTask(List<Integer> taskIds);

    List<Task> selectChildTasks(@Param("taskId")Integer taskId);
}
