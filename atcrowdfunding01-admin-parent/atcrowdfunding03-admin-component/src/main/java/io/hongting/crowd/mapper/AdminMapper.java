package io.hongting.crowd.mapper;

import io.hongting.crowd.entity.Admin;
import io.hongting.crowd.entity.AdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    List<Admin> selectAdminByKeyword(@Param ("keyword") String keyword);

    void clearRelationship(Integer adminId);

    void assignRoles(@Param("adminId")Integer adminId, @Param("roleIdList") List<Integer> roleIdList);
}