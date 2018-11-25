package io.renren.modules.staff.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.modules.staff.entity.ExamineeEntity;
import io.renren.modules.staff.service.ExamineeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 考生信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-11-25 17:33:08
 */
@RestController
@RequestMapping("staff/examinee")
public class ExamineeController {
    @Autowired
    private ExamineeService examineeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("staff:examinee:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = examineeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("staff:examinee:info")
    public R info(@PathVariable("id") Long id){
			ExamineeEntity examinee = examineeService.selectById(id);

        return R.ok().put("examinee", examinee);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:examinee:save")
    public R save(@RequestBody ExamineeEntity examinee){
			examineeService.insert(examinee);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("staff:examinee:update")
    public R update(@RequestBody ExamineeEntity examinee){
			examineeService.updateById(examinee);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("staff:examinee:delete")
    public R delete(@RequestBody Long[] ids){
			examineeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
