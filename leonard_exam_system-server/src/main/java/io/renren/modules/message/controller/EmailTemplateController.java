package io.renren.modules.message.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.renren.modules.message.entity.EmailTemplateEntity;
import io.renren.modules.message.service.EmailTemplateService;
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
 * 邮件模板
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-11-26 13:25:05
 */
@RestController
@RequestMapping("message/emailtemplate")
public class EmailTemplateController {
    @Autowired
    private EmailTemplateService emailTemplateService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("message:emailtemplate:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = emailTemplateService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("message:emailtemplate:info")
    public R info(@PathVariable("id") Long id) {
        EmailTemplateEntity emailTemplate = emailTemplateService.selectById(id);

        return R.ok().put("emailTemplate", emailTemplate);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("message:emailtemplate:save")
    public R save(@RequestBody EmailTemplateEntity emailTemplate) {
        emailTemplate.setCreateTime(new Date());
        emailTemplate.setUpdateTime(new Date());
        emailTemplateService.insert(emailTemplate);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("message:emailtemplate:update")
    public R update(@RequestBody EmailTemplateEntity emailTemplate) {
        emailTemplate.setUpdateTime(new Date());
        emailTemplateService.updateById(emailTemplate);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("message:emailtemplate:delete")
    public R delete(@RequestBody Long[] ids) {
        emailTemplateService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
