package io.renren.modules.staff.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.staff.entity.ExamineeEntity;

import java.util.Map;

/**
 * 考生信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-11-25 17:33:08
 */
public interface ExamineeService extends IService<ExamineeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

