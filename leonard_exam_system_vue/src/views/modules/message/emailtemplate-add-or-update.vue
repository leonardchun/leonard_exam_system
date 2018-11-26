<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="邮件名称" prop="emailName">
      <el-input v-model="dataForm.emailName" placeholder="邮件名称"></el-input>
    </el-form-item>
    <el-form-item label="邮件主题" prop="emailTheme">
      <el-input v-model="dataForm.emailTheme" placeholder="邮件主题"></el-input>
    </el-form-item>
    <el-form-item label="邮件内容" prop="emailContent">
      <el-input v-model="dataForm.emailContent" placeholder="邮件内容"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          emailName: '',
          emailTheme: '',
          emailContent: '',
          createUserId: '',
          createTime: '',
          updateTime: ''
        },
        dataRule: {
          emailName: [
            { required: true, message: '邮件名称不能为空', trigger: 'blur' }
          ],
          emailTheme: [
            { required: true, message: '邮件主题不能为空', trigger: 'blur' }
          ],
          emailContent: [
            { required: true, message: '邮件内容不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/message/emailtemplate/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              alert(data.emailtemplate.emailName)
              if (data && data.code === 0) {
                alert(data.emailtemplate.emailName)
                this.dataForm.emailName = data.emailtemplate.emailName
                this.dataForm.emailTheme = data.emailtemplate.emailTheme
                this.dataForm.emailContent = data.emailtemplate.emailContent
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/message/emailtemplate/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'emailName': this.dataForm.emailName,
                'emailTheme': this.dataForm.emailTheme,
                'emailContent': this.dataForm.emailContent,
                'createUserId': this.dataForm.createUserId,
                'createTime': this.dataForm.createTime,
                'updateTime': this.dataForm.updateTime
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
