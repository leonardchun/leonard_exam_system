package io.renren.modules.message.service.impl;

import io.renren.modules.message.dao.EmailTemplateDao;
import io.renren.modules.message.entity.EmailTemplateEntity;
import io.renren.modules.message.service.EmailTemplateService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;



@Service("emailTemplateService")
public class EmailTemplateServiceImpl extends ServiceImpl<EmailTemplateDao, EmailTemplateEntity> implements EmailTemplateService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<EmailTemplateEntity> page = this.selectPage(
                new Query<EmailTemplateEntity>(params).getPage(),
                new EntityWrapper<EmailTemplateEntity>()
        );

        return new PageUtils(page);
    }

}
