package com.gly.uppis.task.service.impl;

import com.gly.uppis.common.dao.TaskMapper;
import com.gly.uppis.common.entity.Task;
import com.gly.uppis.common.entity.User;
import com.gly.uppis.common.exception.ValidException;
import com.gly.uppis.common.util.UserUtil;
import com.gly.uppis.mark.dao.UserDao;
import com.gly.uppis.task.controller.request.TaskSearchParamRequest;
import com.gly.uppis.task.dao.TaskDao;
import com.gly.uppis.task.dao.result.TaskResult;
import com.gly.uppis.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author GuLiyun
 * @date 2019/5/23 2:40
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskDao taskDao;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private UserDao userDao;

    @Override
    public List<TaskResult> listMyTask() {
        TaskSearchParamRequest param = new TaskSearchParamRequest();
        param.setUserId(UserUtil.getCurrentUserId());
        return taskDao.selectByParam(param);
    }

    @Override
    public void commitTask(Task task) {
        task.setStatus(1);
        task.setUpdateTime(new Date());
        taskMapper.updateByPrimaryKeySelective(task);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteTask(Integer taskId) {
        // 删除大任务时 小任务也默认删除
        Task task = taskMapper.selectByPrimaryKey(taskId);
        task.setIsDelete(1);
        taskMapper.updateByPrimaryKeySelective(task);
        List<Task> subTask = taskDao.selectAllSubTask(taskId);
        subTask.forEach(x -> {
            x.setIsDelete(1);
            taskMapper.updateByPrimaryKeySelective(x);
        });
    }

    @Override
    public void updateTask(Task task) {
        taskMapper.updateByPrimaryKeySelective(task);
    }

    @Override
    public void publishTask(Task task) {
        task.setStatus(0);
        task.setCreator(UserUtil.getCurrentUserId());
        taskMapper.insertSelective(task);
    }

    @Override
    public List<TaskResult> searchTask(TaskSearchParamRequest param) {
        return taskDao.selectByParam(param);
    }

    @Override
    public TaskResult getOneTask(TaskSearchParamRequest param) {
        List<TaskResult> taskResultList = taskDao.selectByParam(param);
        if (CollectionUtils.isEmpty(taskResultList)) {
            throw new ValidException("没有相关任务");
        }
        return taskResultList.get(0);
    }

    @Override
    public List<User> listSameDeptUsers() {
        User user = userDao.selectByUserId(UserUtil.getCurrentUserId());
        if (user == null) {
            throw new ValidException("用户不存在");
        }
        return userDao.selectByDeptId(user.getDeptId());
    }

    @Override
    public void adjustPercentage(Integer taskId, Double percentage) {
        Task task = taskMapper.selectByPrimaryKey(taskId);
        task.setPercentage(percentage);
        taskMapper.updateByPrimaryKeySelective(task);
        String[] parentTaskIdsStr = task.getHighTaskId().split(",");
        List<Integer> parentTaskIds = new ArrayList<>();
        for (String s : parentTaskIdsStr) {
            if (!"".equals(s)) {
                parentTaskIds.add(Integer.parseInt(s));
            }
        }
        List<Task> parentTasks = taskDao.selectAllParentTask(parentTaskIds);
        parentTasks.forEach(x -> {
           List<Task> childTasks = taskDao.selectChildTasks(x.getTaskId());
           double per = childTasks.stream()
                   .mapToDouble(data -> data.getPercentage() * (data.getWeight() / 100.0)).sum();
           x.setPercentage(per);
           taskMapper.updateByPrimaryKeySelective(x);
        });
    }

}
