<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="考生姓名" prop="name">
      <el-input v-model="dataForm.name" placeholder="考生姓名"></el-input>
    </el-form-item>
    <el-form-item label="考生账号" prop="number">
      <el-input v-model="dataForm.number" placeholder="考生账号"></el-input>
    </el-form-item>
    <el-form-item label="所属部门" prop="departId">
      <el-input v-model="dataForm.departId" placeholder="所属部门"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input v-model="dataForm.email" placeholder="邮箱"></el-input>
    </el-form-item>
    <el-form-item label="手机号" prop="mobile">
      <el-input v-model="dataForm.mobile" placeholder="手机号"></el-input>
    </el-form-item>
    <el-form-item label="证件号" prop="idCardNo">
      <el-input v-model="dataForm.idCardNo" placeholder="证件号"></el-input>
    </el-form-item>
      <el-form-item label="状态" size="mini" prop="status">
        <el-radio-group v-model="dataForm.status">
          <el-radio :label="0">禁用</el-radio>
          <el-radio :label="1">正常</el-radio>
        </el-radio-group>
      </el-form-item>
    <el-form-item label="备注" prop="remark">
      <el-input v-model="dataForm.remark" placeholder="备注"></el-input>
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
          name: '',
          number: '',
          departId: '',
          password: '',
          email: '',
          mobile: '',
          idCardNo: '',
          status: '',
          remark: ''
        },
        dataRule: {
          name: [
            { required: true, message: '考生姓名不能为空', trigger: 'blur' }
          ],
          number: [
            { required: true, message: '考生账号不能为空', trigger: 'blur' }
          ],
          departId: [
            { required: true, message: '所属部门不能为空', trigger: 'blur' }
          ],
          email: [
            { required: true, message: '邮箱不能为空', trigger: 'blur' }
          ],
          mobile: [
            { required: true, message: '手机号不能为空', trigger: 'blur' }
          ],
          idCardNo: [
            { required: true, message: '证件号不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '状态必填', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/staff/examinee/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.examinee.name
                this.dataForm.number = data.examinee.number
                this.dataForm.departId = data.examinee.departId
                this.dataForm.password = data.examinee.password
                this.dataForm.email = data.examinee.email
                this.dataForm.mobile = data.examinee.mobile
                this.dataForm.idCardNo = data.examinee.idCardNo
                this.dataForm.status = data.examinee.status
                this.dataForm.remark = data.examinee.remark
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
              url: this.$http.adornUrl(`/staff/examinee/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,
                'number': this.dataForm.number,
                'departId': this.dataForm.departId,
                'password': this.dataForm.password,
                'email': this.dataForm.email,
                'mobile': this.dataForm.mobile,
                'idCardNo': this.dataForm.idCardNo,
                'status': this.dataForm.status,
                'remark': this.dataForm.remark

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
