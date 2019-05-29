<template>
  <Card style="padding: 10px; width: 100%">
    <Row>
      <Col>
        <i-button style="float: right;" type="primary" shape="circle" icon="ios-search" @on-click="searchData"></i-button>
        <Select placeholder="出勤类型" style="width:100px; float: right; margin-right: 10px;" v-model="type" clearable>
          <i-option v-for="item in typeList" v-bind:key="item.value" :value="item.value">{{ item.label }}</i-option>
        </Select>
        <Date-picker type="daterange" @on-change="changeDate" placement="bottom-end" placeholder="选择日期" style="width: 200px; float: right; margin-right: 10px;"></Date-picker>
      </Col>
    </Row>
    <Row style="width: 100%; background: #f0f0f0; height: auto; margin-top: 20px; line-height: 40px;">
      <Tag type="dot" v-for="item in labelList" v-bind:key="item.param" closable style="margin-left: 5px;" @on-close="clearParam(item.value, item.param)">{{item.label}}</Tag>
      <span style="color: #348EED; cursor: pointer; margin-left: 10px;" @click="clearAllParam" v-show="labelList.length !== 0">清空搜索条件</span>
    </Row>
    <Row>
      <Table :columns="columns1" :data="data1"></Table>
    </Row>
  </Card>
</template>

<script>
export default {
  name: 'attendance_page',
  watch: {
    type: function () {
      let mark = 0
      this.labelList.forEach(x => {
        if (x.param === this.type) {
          mark = 1
        }
      })
      if (mark === 1) {
        return
      }
      this.typeList.forEach(x => {
        if (x.value === this.type) {
          this.labelList.push({
            value: 1,
            label: x.label,
            param: x.value
          })
        }
      })
    },
    chooseDate: function () {
      console.log(this.chooseDate)
      if (this.chooseDate[0] === '' || this.chooseDate[1] === '') {
        return
      }
      let r = 0
      this.labelList.forEach(x => {
        if (x.value === 0) {
          r = 1
          x.label = this.chooseDate[0] + ' ~ ' + this.chooseDate[1]
          x.param = this.chooseDate
        }
      })
      if (r === 0) {
        this.labelList.push({
          value: 0,
          label: this.chooseDate[0] + ' ~ ' + this.chooseDate[1],
          param: this.chooseDate
        })
      }
    }
  },
  methods: {
    searchData () {

    },
    clearParam (type, param) {
      console.log(this.labelList)
      switch (type) {
        case 0:
          this.chooseDate = ['', '']
          for (let item = 0; item < this.labelList.length; item++) {
            if (this.labelList[item].value === 0) {
              this.labelList.splice(item, 1)
              break
            }
          }
          break
        case 1:
          for (let item = 0; item < this.labelList.length; item++) {
            if (this.labelList[item].param === param) {
              this.labelList.splice(item, 1)
              break
            }
          }
          break
      }
    },
    clearAllParam () {
      this.labelList = []
    },
    changeDate (date) {
      this.chooseDate = date
    }
  },
  data () {
    return {
      count: 3,
      chooseDate: [],
      labelList: [],
      type: -1,
      typeList: [
        {
          value: 0,
          label: '正常'
        },
        {
          value: 1,
          label: '请假'
        },
        {
          value: 2,
          label: '迟到'
        },
        {
          value: 3,
          label: '早退'
        },
        {
          value: 4,
          label: '缺勤'
        }
      ],
      options2: {
        shortcuts: [
          {
            text: '最近一周',
            value () {
              const end = new Date()
              const start = new Date()
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
              return [start, end]
            }
          },
          {
            text: '近一个月',
            value () {
              const end = new Date()
              const start = new Date()
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
              return [start, end]
            }
          },
          {
            text: '近三个月',
            value () {
              const end = new Date()
              const start = new Date()
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
              return [start, end]
            }
          }
        ]
      },
      columns1: [
        {
          title: '姓名',
          key: 'name',
          align: 'center'
        },
        {
          title: '部门',
          key: 'dept',
          align: 'center'
        },
        {
          title: '上班打卡',
          key: 'up',
          align: 'center'
        },
        {
          title: '下班打卡',
          key: 'down',
          align: 'center'
        },
        {
          title: '异常情况',
          key: 'exceptionDescription',
          align: 'center'
        },
        {
          title: '说明',
          key: 'description',
          align: 'center'
        }
      ],
      data1: [
        {
          name: '王小明',
          dept: '市场中心',
          up: '9:00',
          down: '19:20',
          exceptionDescription: '正常',
          description: '-'
        },
        {
          name: '顾大宝',
          dept: '机票事业群',
          up: '8:50',
          down: '-',
          exceptionDescription: '早退',
          description: '忘记打卡'
        },
        {
          name: '王小明',
          dept: '市场中心',
          up: '9:00',
          down: '15:20',
          exceptionDescription: '早退',
          description: '-'
        },
        {
          name: '王小明',
          dept: '市场中心',
          up: '10:00',
          down: '18:20',
          exceptionDescription: '迟到',
          description: '迟到一小时'
        }
      ]
    }
  }
}
</script>
