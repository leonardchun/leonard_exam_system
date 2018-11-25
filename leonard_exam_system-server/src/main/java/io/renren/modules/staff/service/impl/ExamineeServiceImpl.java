package io.renren.modules.staff.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.staff.dao.ExamineeDao;
import io.renren.modules.staff.entity.ExamineeEntity;
import io.renren.modules.staff.service.ExamineeService;
import org.springframework.stereotype.Service;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;


@Service("examineeService")
public class ExamineeServiceImpl extends ServiceImpl<ExamineeDao, ExamineeEntity> implements ExamineeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ExamineeEntity> page = this.selectPage(
                new Query<ExamineeEntity>(params).getPage(),
                new EntityWrapper<ExamineeEntity>()
        );

        return new PageUtils(page);
    }

}
