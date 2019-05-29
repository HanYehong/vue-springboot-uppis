package com.gly.uppis.mark.dao;

import com.gly.uppis.common.entity.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author HanYehong
 * @date 2019/5/22 23:29
 */
public interface DeptDao {
    List<Dept> selectByParentDeptId(@Param("parentDeptId")Integer parentDeptId);
}
