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
          <chart-pie style="height: 300px;" :value="pieData" text="人数占比"></chart-pie>
        </Card>
      </i-col>
      <i-col :md="24" :lg="16" style="margin-bottom: 20px;">
        <Card>
          <Table height="300" :columns="columns1" :data="data1"></Table>
        </Card>
      </i-col>
    </Row>
  </Card>
</template>

<script>
  import InforCard from '_c/info-card'
  import CountTo from '_c/count-to'
  import ChartPie from './pie'
  export default {
    name: 'dept_performance_page',
    components: {
      InforCard,
      CountTo,
      ChartPie
    },
    mounted () {
      this.getLastPer()
    },
    methods: {
      async getLastPer() {
        this.$ajax.get('/performance/getDeptRecord', {}).then(data => {
          console.log('获得当前部门绩效信息', data)
          this.perData = data
          this.inforCardData[0].count = this.perData.deptUsersNum
          this.inforCardData[1].count = this.perData.avgPeriod
          this.inforCardData[2].count = this.perData.a
          this.inforCardData[3].count = this.perData.b
          this.inforCardData[4].count = this.perData.c
          this.inforCardData[5].count = this.perData.d
          this.pieData[0].value = this.perData.a
          this.pieData[1].value = this.perData.b
          this.pieData[2].value = this.perData.c
          this.pieData[3].value = this.perData.d
          this.data1 = this.perData.userPerList
        })
      }
    },
    data () {
      return {
        perData: {},
        inforCardData: [
          { title: '部门总人数', icon: 'md-checkbox-outline', count: 0, color: '#2d8cf0' },
          { title: '平均绩效', icon: 'md-clock', count: 0, color: '#19be6b' },
          { title: 'A级(100~95)', icon: 'md-bookmark', count: 0, color: '#ff9900' },
          { title: 'B级(94~85)', icon: 'md-create', count: 0, color: '#ed3f14' },
          { title: 'C级(84~75)', icon: 'md-bookmark', count: 0, color: '#E46CBB' },
          { title: 'D级(<75)', icon: 'md-create', count: 0, color: '#9A66E4' },
        ],
        pieData: [
          { value: 0, name: 'A级' },
          { value: 0, name: 'B级' },
          { value: 0, name: 'C级' },
          { value: 0, name: 'D级' }
        ],
        columns1: [
          {
            title: '姓名',
            key: 'name'
          },
          {
            title: '工号',
            key: 'userId'
          },
          {
            title: '职务',
            key: 'position'
          },
          {
            title: '绩效',
            key: 'scoreTotal'
          },
          {
            title: '操作',
            key: 'doSth',
            render: (h, params) => {
              return h('span', {
                style: {
                  color: '#19be6b',
                  fontWeight: 'border',
                  cursor: 'pointer'
                },
                on: {
                  click: () => {
                    this.$router.push({ path: 'performance_page', query: { userId: params.row.userId } })
                  }
                }
              } , '详情>>')
            }
          }
        ],
        data1: []
      }
    }
  }
</script>

<style lang="less">
  .count-style{
    font-size: 50px;
  }
</style>
