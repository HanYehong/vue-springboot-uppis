<template>
  <Row>
    <Col span="11">
      <Card shadow >
        <Row>
          <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="100">
            <FormItem label="头像">
              <avatar img-url="https://cloud-minapp-22871.cloud.ifanrusercontent.com/1gZyi2P6x0yplHWi.jpg"></avatar>
            </FormItem>
            <FormItem label="姓名" prop="username">
              <Input v-model="formValidate.username" style="width: 350px" readonly></Input>
            </FormItem>
            <FormItem label="工号" prop="user_id">
              <Input v-model="formValidate.user_id" style="width: 350px" readonly></Input>
            </FormItem>
            <FormItem label="部门" prop="dept_name">
              <Input v-model="formValidate.dept_name" style="width: 350px" readonly></Input>
            </FormItem>
            <FormItem label="职称" prop="position">
              <Input v-model="formValidate.position" style="width: 350px" readonly></Input>
            </FormItem>
            <FormItem label="性别" prop="gender">&emsp;
              <RadioGroup v-model="formValidate.gender">
                <Radio label="男" value="1"></Radio>&emsp;
                <Radio label="女" value="0"></Radio>
              </RadioGroup>
            </FormItem>
            <FormItem label="出生日期" prop="birthday">
              <DatePicker v-model="formValidate.birthday" type="date" placeholder="请选择你的出生日期"></DatePicker>
            </FormItem>
            <FormItem label="身份证号" prop="identity">
              <Input v-model="formValidate.identity" placeholder="请输入你的身份证号" clearable style="width: 350px"></Input>
            </FormItem>
            <FormItem label="政治面貌" prop="politics">
              <Select v-model="formValidate.politics" placeholder="情选择你的政治面貌" style="width: 350px">
                <Option value="0">中共党员含预备党员</Option>
                <Option value="1">共青团员</Option>
                <Option value="2">民革党员</Option>
                <Option value="3">民盟盟员</Option>
                <Option value="4">民建会员</Option>
                <Option value="5">民进会员</Option>
                <Option value="6">农工党党员</Option>
                <Option value="7">致公党党员</Option>
                <Option value="8">九三学社社员</Option>
                <Option value="9">台盟盟员</Option>
                <Option value="10">无党派人士</Option>
                <Option value="11">群众</Option>
              </Select>
            </FormItem>
            <FormItem label="邮箱" prop="mail">
              <Input v-model="formValidate.mail" placeholder="请输入你的邮箱" clearable style="width: 350px"></Input>
            </FormItem>
            <FormItem label="电话" prop="telephone">
              <Input v-model="formValidate.telephone" placeholder="请输入你的电话"  clearable style="width: 350px"></Input>
            </FormItem>
            <FormItem label="籍贯" prop="native_place">
              <Input v-model="formValidate.native_place" placeholder="请输入你的籍贯"  clearable style="width: 350px"></Input>
            </FormItem>
            <FormItem label="家庭住址" prop="address">
              <Input v-model="formValidate.address" placeholder="请输入你的家庭住址" clearable style="width: 350px"></Input>
            </FormItem>
            <FormItem>
              <Button type="primary" @click="handleSubmit('formValidate')">保存</Button>&nbsp;
              <Button @click="handleReset('formValidate')" style="margin-left: 8px">重置</Button>&nbsp;
              <Button type="warning" @click="handleReset('formValidate')" style="margin-left: 8px">修改密码</Button>
            </FormItem>
          </Form>
        </Row>
      </Card>
    </Col>
    <Col span="1"><span>&nbsp;</span></Col>
    <Col span="11">
      <card shadow>
        <Form ref="formCustom" :model="formCustom" :rules="ruleCustom" :label-width="100">
          <FormItem label="输入原密码" prop="old_passwd" >
            <Input type="password" v-model="formCustom.old_passwd" clearable style="width: 350px"></Input>
          </FormItem>
          <FormItem label="输入新密码" prop="new_passwd" >
            <Input type="password" v-model="formCustom.new_passwd" clearable style="width: 350px"></Input>
          </FormItem>
          <FormItem label="再次确认" prop="passwdCheck">
            <Input type="password" v-model="formCustom.passwdCheck" clearable style="width: 350px"></Input>
          </FormItem>
          <FormItem>
            <Button type="primary" @click="handleSubmit('formCustom')">保存</Button>
            <Button @click="handleReset('formCustom')" style="margin-left: 8px">重置</Button>
          </FormItem>
        </Form>
      </card>
    </Col>
  </Row>
</template>

<script>
import avatar from '../../../components/avatar/avatar.vue'
export default {
  name: 'information_page',
  components: { avatar },
  data () {
    const validateOldPass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入你的原密码'))
      } else {
        if (value !== '123456') {
          callback(new Error('请输入正确的密码'))
        }
        callback()
      }
    }
    const validateNewPass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入你的新密码'))
      } else if (value === this.formCustom.old_passwd) {
        callback(new Error('新密码不能与原密码相同'))
      } else {
        if (this.formCustom.passwdCheck !== '') {
          // 对第二个密码框单独验证
          this.$refs.formCustom.validateField('passwdCheck')
        }
        callback()
      }
    }
    const validatePassCheck = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入新密码'))
      } else if (value !== this.formCustom.new_passwd) {
        callback(new Error('两次输入的密码不一致'))
      } else {
        callback()
      }
    }
    return {
      formValidate: {
        username: '张三',
        user_id: '20191001',
        dept_name: '销售部',
        position: '初级顾问',
        gender: '',
        birthday: '',
        identity: '',
        politics: '',
        mail: '',
        telephone: '',
        native_place: '',
        address: '',
        imgUrl: '../../assets/images/tx.jpg'
      },
      ruleValidate: {
        gender: [
          { required: true, message: '请选择性别', trigger: 'change' }
        ],
        birthday: [
          { required: true, type: 'date', message: '请选择你的出生日期', trigger: 'change' }
        ],
        identity: [
          { required: true, type: 'string', message: '身份证号不能为空', trigger: 'blur' },
          {
            validator (rule, value, callback) {
              let reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/
              const errors = []
              if (reg.test(value) === false) {
                errors.push('身份证输入不合法')
              }
              callback(errors)
            },
            trigger: 'blur'
          }
        ],
        politics: [
          { required: true, message: '请选择你的政治面貌', trigger: 'change' }
        ],
        mail: [
          { required: true, message: '邮箱不能为空', trigger: 'blur' },
          { type: 'email', message: '请填写正确的邮箱号', trigger: 'blur' }
        ],
        telephone: [
          { required: true, message: '手机号不能为空', trigger: 'blur' },
          {
            validator (rule, value, callback) {
              let reg = /^1[3456789]\d{9}$/
              const errors = []
              if (reg.test(value) === false) {
                errors.push('手机号输入不合法')
              }
              callback(errors)
            },
            trigger: 'blur'
          }
        ],
        native_place: [
          { required: true, message: '籍贯不能为空', trigger: 'blur' }
        ],
        address: [
          { required: false, type: 'string', trigger: 'blur' }
        ]
      },
      formCustom: {
        old_passwd: '',
        new_passwd: '',
        passwdCheck: ''
      },
      ruleCustom: {
        old_passwd: [
          { validator: validateOldPass, trigger: 'blur' }
        ],
        new_passwd: [
          { validator: validateNewPass, trigger: 'blur' }
        ],
        passwdCheck: [
          { validator: validatePassCheck, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    handleSubmit (name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          this.$Message.success('保存成功!')
        } else {
          this.$Message.error('保存失败!')
        }
      })
    },
    handleReset (name) {
      this.$refs[name].resetFields()
    }
  }
}
</script>
