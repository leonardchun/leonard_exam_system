package io.renren.modules.message.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.message.entity.EmailTemplateEntity;


import java.util.Map;

/**
 * 邮件模板
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-11-26 13:25:05
 */
public interface EmailTemplateService extends IService<EmailTemplateEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

