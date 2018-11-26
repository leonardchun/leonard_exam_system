package io.renren.modules.message.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.message.entity.EmailTemplateEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 邮件模板
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-11-26 13:25:05
 */
@Mapper
public interface EmailTemplateDao extends BaseMapper<EmailTemplateEntity> {
	
}
