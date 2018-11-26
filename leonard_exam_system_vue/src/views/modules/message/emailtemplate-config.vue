<template>
  <el-dialog
    title="邮件配置"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="120px">
      <template >
        <el-form-item label="发件服务器" prop="emailSendServer">
          <el-input v-model="dataForm.emailSendServer" placeholder="smtp.163.com"></el-input>
        </el-form-item>
        <el-form-item label="端口号" prop="emailPort">
          <el-input v-model="dataForm.emailPort" placeholder="端口号"></el-input>
        </el-form-item>
        <el-form-item label="邮箱账号" prop="emailNumber">
          <el-input v-model="dataForm.emailNumber" placeholder="邮箱账号"></el-input>
        </el-form-item>
        <el-form-item label="邮箱密码" prop="emailPassword">
          <el-input v-model="dataForm.emailPassword" placeholder="邮箱密码"></el-input>
        </el-form-item>
      </template>
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
          emailSendServer: '',
          emailPort: '',
          emailNumber: '',
          emailPassword: ''
        },
        dataRule: {
          emailSendServer: [
            { required: true, message: '发件服务器不能为空', trigger: 'blur' }
          ],
          emailPort: [
            { required: true, message: '端口号不能为空', trigger: 'blur' }
          ],
          emailNumber: [
            { required: true, message: '邮箱账号不能为空', trigger: 'blur' }
          ],
          emailPassword: [
            { required: true, message: '邮箱密码不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.visible = true
        this.$http({
          url: this.$http.adornUrl('/message/emailtemplate/config'),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          this.dataForm = data && data.code === 0 ? data.config : []
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl('/message/emailtemplate/saveConfig'),
              method: 'post',
              data: this.$http.adornData(this.dataForm)
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
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

