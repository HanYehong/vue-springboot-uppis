<template>
  <Card style="padding: 40px;">
    <Row>
      <span style="color: #0099ff; font-size: 28px; font-weight: bolder">{{perTitle}}</span>
    </Row>
    <Row>
      <avatar style="float: left;" :img-url="imgUrl"></avatar>
      <Col style="float: left; margin-left: 10px;">
        <Row style="color: #58cc00; font-size: 28px; margin: 10px;">
          <span>{{markUserInfo.name}}</span>
        </Row>
        <Row style="margin: 10px; color: #797979;">
          <span>{{markUserInfo.deptName}} | {{markUserInfo.position}}</span>
        </Row>
        <Row style="margin: 10px; color: #797979;">
          <span>{{markUserInfo.email}}</span>
        </Row>
      </Col>
    </Row>
    <Card style="margin-top: 10px; padding: 10px; height: 90px;">
      <Col style="float: left; width: 10%">
        <span style="font-size: 20px;">评分流程</span>
      </Col>
      <Col style="float: left; width: 90%">
        <Steps :current="markInfo.type" style="float: left; margin-left: 10%; width: 90%;">
          <Step title="自评"></Step>
          <Step title="同级评分"></Step>
          <Step title="上级评分"></Step>
          <Step title="审核"></Step>
          <Step title="结束"></Step>
        </Steps>
      </Col>
    </Card>
    <Row style="margin-top: 20px;">
      <hr class="drive" />
      <Row>
        <Row  style="margin-top: 5px; margin-bottom: 5px;" v-for="item in target">
          <Col style="width: 60%; float: left">{{item.targetContent}}</Col>
          <Select ref="score" style="width: 200px; float: right;" clearable>
            <Option v-for="item in selectOptions" :key="item.value" :value="item.value">{{item.label}}</Option>
          </Select>
        </Row>
      </Row>
      <hr class="drive" />
    </Row>
    <Row style="margin-top: 10px;">
      <Button style="float: right" type="primary" shape="circle" size="large" @click="commit">提交</Button>
    </Row>
  </Card>
</template>

<script>
import avatar from '../../components/avatar/avatar.vue'
export default {
  name: 'mark_detail_page',
  components: { avatar },
  mounted: function () {
    this.checkUrlParam()
  },
  methods: {
    checkUrlParam () {
      let type = this.$route.query.type
      let userId = this.$route.query.userId
      let perId = this.$route.query.perId
      let perTitle = this.$route.query.perTitle
      if (type === 0 || type === 1 || type === 2) {
        this.markInfo.type = type
        this.markInfo.periodId = perId
        this.markInfo.markUserId = userId
        this.perTitle = perTitle
        this.getTargetList(userId)
        this.getUserDetail(userId)
      }
    },
    async getTargetList (userId) {
      this.$ajax.post('/mark/listTarget', { markUserId: userId }).then(data => {
        console.log('获取当前人员对应的评级指标', data)
        this.target = data
      })
    },
    async getUserDetail(userId) {
      this.$ajax.post('/mark/getUserDetail', { markUserId: userId }).then(data => {
        console.log('获得当前人员信息', data)
        this.markUserInfo = data
      })
    },
    commit() {
      console.log('提交表单，所有分数数组：', this.$refs.score)
      let score = 0
      this.$refs.score.forEach(x => {
        score += x.publicValue
      })
      score = score / this.$refs.score.length
      console.log('计算得到平均分为：', score)
      this.markInfo.assessment = score
      this.$ajax.post('/mark/commit', this.markInfo).then(data => {
        console.log('评分完成', data)
        this.$Message.success('评分成功')
        this.$router.push({ path: 'mark_page' })
      })
    }
  },
  watch: {
    $route () {
      this.checkUrlParam()
    }
  },
  data () {
    return {
      markInfo: {
        periodId: -1,
        markUserId: -1,
        assessment: 0,
        type: -1
      },
      perTitle: '',
      markUserInfo: {},
      target: [],
      imgUrl: '../../assets/images/tx.jpg',
      selectOptions: [
        {
          value: 95,
          label: '非常优秀'
        },
        {
          value: 85,
          label: '比较优秀'
        },
        {
          value: 75,
          label: '一般水平'
        },
        {
          value: 60,
          label: '需要提高'
        }
      ]
    }
  }
}
</script>

<style scoped>
  .drive {
    width: 100%;
    margin-top: 10px;
    margin-bottom: 10px;
    background-color: #ccc;
    height: 1px;
    border: none;
  }
</style>
