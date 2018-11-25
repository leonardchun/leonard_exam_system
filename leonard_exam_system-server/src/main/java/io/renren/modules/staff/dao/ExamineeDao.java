package io.renren.modules.staff.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.staff.entity.ExamineeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 考生信息表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-11-25 17:33:08
 */
@Mapper
public interface ExamineeDao extends BaseMapper<ExamineeEntity> {
	
}
