package io.renren.modules.message.controller;

import com.google.gson.Gson;
import io.renren.common.utils.ConfigConstant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.message.config.EmailConfig;
import io.renren.modules.message.entity.EmailTemplateEntity;
import io.renren.modules.message.service.EmailTemplateService;
import io.renren.modules.oss.cloud.CloudStorageConfig;
import io.renren.modules.sys.service.SysConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;


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

    private final static String KEY = ConfigConstant.EMAIL_CONFIG_KEY;

    @Autowired
    private EmailTemplateService emailTemplateService;

    @Autowired
    private SysConfigService sysConfigService;

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

        return R.ok().put("emailtemplate", emailTemplate);
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


    /**
     * 邮件配置信息
     */
    @GetMapping("/config")
    @RequiresPermissions("message:emailtemplate:all")
    public R config() {
        EmailConfig config = sysConfigService.getConfigObject(KEY, EmailConfig.class);

        return R.ok().put("config", config);
    }

    /**
     * 保存邮件配置信息
     */
    @PostMapping("/saveConfig")
    @RequiresPermissions("message:emailtemplate:all")
    public R saveConfig(@RequestBody EmailConfig config) {
       /* //校验类型
        ValidatorUtils.validateEntity(config);

        if (config.getType() == Constant.CloudService.QINIU.getValue()) {
            //校验七牛数据
            ValidatorUtils.validateEntity(config, QiniuGroup.class);
        } else if (config.getType() == Constant.CloudService.ALIYUN.getValue()) {
            //校验阿里云数据
            ValidatorUtils.validateEntity(config, AliyunGroup.class);
        } else if (config.getType() == Constant.CloudService.QCLOUD.getValue()) {
            //校验腾讯云数据
            ValidatorUtils.validateEntity(config, QcloudGroup.class);
        }*/

        sysConfigService.updateValueByKey(KEY, new Gson().toJson(config));

        return R.ok();
    }


}
