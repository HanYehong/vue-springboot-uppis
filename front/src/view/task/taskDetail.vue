<template>
  <Card style="margin-top: 20px;">
    <Row style="font-size: 20px; color: #495060;">
      <span>任务详情</span>
    </Row>
    <Row style="font-size: 20px; color: #348EED; margin-top: 10px;">
      <span>{{taskDetail.taskTitle}}</span>
    </Row>
    <Row style="font-size: 17px; color: #33cc00">
      <Col style="float: left">
        <span>负责人：{{taskDetail.name}}</span>
      </Col>
      <Col style="float: left; margin-left: 50px;">
        <span>所属部门：{{taskDetail.deptName}}</span>
      </Col>
    </Row>
    <Row style="margin-top: 20px; color: #515a6e">
      <Col>
        <span>发布日期：{{taskDetail.beginTime}}</span>
      </Col>
      <Col style="margin-top: 3px;">
        <span>截止日期：{{taskDetail.deadline}}</span>
        <span style="color: red; margin-left: 30px;">距离截止日期还剩{{days}}天</span>
      </Col>
      <Col style="margin-top: 3px;">
        <div style="float: left; width: 200px;">最近更新时间：{{taskDetail.updateTime}}</div>
        <Progress style="float: left; margin-left: 30px; width: 100px;" :percent='taskDetail.percentage' :strokeColor="taskDetail.strokeColor"></Progress>
        <div v-if="showBtn && (taskDetail.status === 0 || taskDetail.status === 3)" style="float: left; margin-top: -6px;">
          <Button style="float: left;" @click="add">+</Button>
          <Button style="float: left; margin-left: 5px;" @click="sub">-</Button>
        </div>
      </Col>
    </Row>
    <Row style="margin-top: 20px;">
      <div style="font-weight: bolder">任务详细介绍</div>
      <p>{{taskDetail.statement}}</p>
    </Row>
    <Row style="margin-top: 20px;">
      <div style="font-weight: bolder">任务内容提交</div>
      <i-input type="textarea" :rows="5" v-model="taskDetail.content"></i-input>
    </Row>
    <Row style="margin-top: 20px;">
      <Upload action="//jsonplaceholder.typicode.com/posts/">
        <i-button type="ghost" icon="ios-cloud-upload-outline" style="color: #ccc; border: 1px #ccc solid;">添加附件</i-button>
      </Upload>
    </Row>
    <Row style="margin-top: 20px;">
      <Button :disabled="taskDetail.status !== 0 && taskDetail.status !== 3" style="float: right" type="primary" shape="circle" size="large" @click="commitTask">{{(taskDetail.status === 0 || taskDetail.status === 3) ? '提交' : taskDetail.status === 1 ? '待审核' : '审核通过'}}</Button>
    </Row>
  </Card>
</template>
<script>
import { formatDate } from '../../libs/date'
export default {
  name: 'task_detail_page',
  mounted: function () {
    this.checkUrl()
  },
  watch: {
    'taskDetail.percentage': function () {
      this.taskDetail.strokeColor = this.getProcessColor(this.taskDetail.percentage)
    },
    $route () {
      this.checkUrl()
    }
  },
  methods: {
    getProcessColor (data) {
      if (data >= 0 && data < 50) {
        return '#ff0033'
      }
      if (data >= 50 && data < 80) {
        return '#ffff66'
      }
      if (data >= 80 && data < 100) {
        return '#00ffff'
      }
      return '#00ff66'
    },
    checkUrl () {
      let taskId = this.$route.query.taskId
      if (taskId) {
        this.$ajax.post('/task/getTask', { taskId: taskId }).then(data => {
          console.log('获取任务详情', data)
          data.beginTime = formatDate(data.beginTime, 'yyyy-MM-dd HH:mm:ss')
          data.deadline = formatDate(data.deadline, 'yyyy-MM-dd HH:mm:ss')
          let tempDate = formatDate(data.updateTime, 'yyyy-MM-dd HH:mm:ss')
          data.updateTime = tempDate === '' ? '-' : tempDate
          data.strokeColor = this.getProcessColor(data.percentage)
          this.taskDetail = data
          this.days = parseInt((new Date(this.taskDetail.deadline).getTime() - new Date(this.taskDetail.beginTime)) / 1000 / 60 / 60 / 24)
        })
      }
      let type = this.$route.query.type
      this.showBtn = type === 0
    },
    add () {
      this.taskDetail.percentage = this.taskDetail.percentage + 10
      if (this.taskDetail.percentage > 100) {
        this.taskDetail.percentage = 100
      }
      this.$ajax.post('/task/adjustPercentage', { taskId: this.taskDetail.taskId, percentage: this.taskDetail.percentage }).then(data => {
        console.log('调整进度完成', data)
      })
    },
    sub () {
      this.taskDetail.percentage = this.taskDetail.percentage - 10
      if (this.taskDetail.percentage < 0) {
        this.taskDetail.percentage = 0
      }
    },
    commitTask () {
      if (this.taskDetail.percentage !== 100) {
        this.$Message.error('任务进度未达100%')
        return
      }
      let param = this.taskDetail
      if (param.updateTime === '-') {
        param.updateTime = null
      }
      this.$ajax.post('/task/commit', param).then(data => {
        console.log('提交任务成功', data)
        this.$router.push({ path: 'task_page' })
      })
    }
  },
  data () {
    return {
      taskDetail: {},
      days: 0,
      showBtn: true
    }
  }
}
</script>
