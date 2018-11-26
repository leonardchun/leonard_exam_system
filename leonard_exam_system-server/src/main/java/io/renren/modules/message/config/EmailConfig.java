package io.renren.modules.message.config;

import io.renren.common.validator.group.QiniuGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Description
 * @Author 王海春
 * @Date 2018/11/26 23:20
 */
@Data
public class EmailConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "发件服务器不能为空", groups = QiniuGroup.class)
    private String emailSendServer;

    @NotBlank(message = "端口号不能为空", groups = QiniuGroup.class)
    private String emailPort;

    @NotBlank(message = "邮箱账号不能为空", groups = QiniuGroup.class)
    private String emailNumber;

    @NotBlank(message = "邮箱密码不能为空", groups = QiniuGroup.class)
    private String emailPassword;

}
