package io.renren.modules.message.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 邮件模板
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-11-26 13:25:05
 */
@TableName("email_template")
public class EmailTemplateEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 邮件名称
	 */
	private String emailName;
	/**
	 * 邮件主题
	 */
	private String emailTheme;
	/**
	 * 邮件内容
	 */
	private String emailContent;
	/**
	 * 创建者ID
	 */
	private Long createUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：邮件名称
	 */
	public void setEmailName(String emailName) {
		this.emailName = emailName;
	}
	/**
	 * 获取：邮件名称
	 */
	public String getEmailName() {
		return emailName;
	}
	/**
	 * 设置：邮件主题
	 */
	public void setEmailTheme(String emailTheme) {
		this.emailTheme = emailTheme;
	}
	/**
	 * 获取：邮件主题
	 */
	public String getEmailTheme() {
		return emailTheme;
	}
	/**
	 * 设置：邮件内容
	 */
	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}
	/**
	 * 获取：邮件内容
	 */
	public String getEmailContent() {
		return emailContent;
	}
	/**
	 * 设置：创建者ID
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：创建者ID
	 */
	public Long getCreateUserId() {
		return createUserId;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
