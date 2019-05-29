<template>
  <Card>
    <Row :gutter="20">
      <Row style="text-align: center; margin: 10px;">
        <span style="font-size: 24px; font-weight: bolder; color: cadetblue;">{{perData.periodName}}</span>
      </Row>
      <i-col :xs="12" :md="8" :lg="4" v-for="(infor, i) in inforCardData" :key="`infor-${i}`" style="height: 120px;padding-bottom: 10px;">
        <infor-card :color="infor.color" :icon="infor.icon" :icon-size="36">
          <count-to :end="infor.count" count-class="count-style"/>
          <p>{{ infor.title }}</p>
        </infor-card>
      </i-col>
    </Row>
    <Row :gutter="20" style="margin-top: 10px;">
      <i-col :md="24" :lg="8" style="margin-bottom: 20px;">
        <Card>
          <chart-pie style="height: 300px;" :value="pieData" text="绩效得分占比"></chart-pie>
        </Card>
      </i-col>
      <i-col :md="24" :lg="16" style="margin-bottom: 20px;">
        <Card>
          <Row>
            <Col style="float: right">
              <span>年份</span>
              <Select style="width: 70px; margin-left: 5px;" v-model="yearVal" @on-change="selectData" clearable>
                <Option value="2019">2019</Option>
                <Option value="2018">2018</Option>
                <Option value="2017">2017</Option>
              </Select>
            </Col>
            <Col style="float: right; margin-right: 10px;">
              <span>绩效类型</span>
              <Select style="width: 70px; margin-left: 5px;" v-model="typeVal" @on-change="selectData" clearable>
                <Option value="0">月度</Option>
                <Option value="1">季度</Option>
                <Option value="2">年度</Option>
              </Select>
            </Col>
          </Row>
          <example :x-axis="xAxis" :series="series" style="height: 310px; margin-top: 10px;"/>
        </Card>
      </i-col>
    </Row>
  </Card>
</template>

<script>
  import InforCard from '_c/info-card'
  import CountTo from '_c/count-to'
  // import { ChartPie, ChartBar } from '_c/charts'
  import ChartPie from './pie'
  import Example from '../single-page/home/example'
  export default {
    name: 'performance_page',
    components: {
      InforCard,
      CountTo,
      ChartPie,
      Example
    },
    mounted () {
      let userId = this.$route.query.userId
      this.getLastPer(userId)
      this.selectData()
    },
    watch: {
      $route () {
        let userId = this.$route.query.userId
        this.getLastPer(userId)
        this.selectData(userId)
      }
    },
    methods: {
      async selectData (userId) {
        if (!this.typeVal) {
          return
        }
        if (this.typeVal !== 2 && !this.yearVal) {
          return
        }
        this.$ajax.post('/performance/selectData', { type: this.typeVal, year: this.yearVal, userId: userId }).then(data => {
          console.log('曲线图数据', data)
          this.xAxis = data.xAxis
          this.series = data.data
        })
      },
      async getLastPer(userId) {
        this.$ajax.post('/performance/getLastRecord', { userId: userId }).then(data => {
          console.log('获得当前绩效信息', data)
          this.perData = data
          this.inforCardData[0].count = this.perData.scoreTotal
          this.inforCardData[1].count = this.perData.scoreAttendance
          this.inforCardData[2].count = this.perData.scoreTask
          this.inforCardData[3].count = this.perData.scoreMark
          this.pieData[0].value = (this.perData.scoreAttendance * this.perData.attendanceWeight).toFixed(1)
          this.pieData[1].value = (this.perData.scoreTask * this.perData.taskWeight).toFixed(1)
          this.pieData[2].value = (this.perData.scoreMark * this.perData.markWeight).toFixed(1)
        })
      }
    },
    data () {
      return {
        yearVal: '2019',
        typeVal: '0',
        perData: {},
        xAxis: [],
        series: [],
        inforCardData: [
          { title: '绩效总分', icon: 'md-checkbox-outline', count: 0, color: '#2d8cf0' },
          { title: '考勤得分', icon: 'md-clock', count: 0, color: '#19be6b' },
          { title: '任务得分', icon: 'md-bookmark', count: 0, color: '#ff9900' },
          { title: '评级得分', icon: 'md-create', count: 0, color: '#ed3f14' },
        ],
        pieData: [
          { value: 0, name: '考勤得分' },
          { value: 0, name: '任务得分' },
          { value: 0, name: '评级得分' }
        ]
      }
    }
  }
</script>

<style lang="less">
  .count-style{
    font-size: 50px;
  }
</style>
