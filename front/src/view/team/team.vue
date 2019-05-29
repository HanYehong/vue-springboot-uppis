<template>
  <Card style="padding: 10px; width: 100%">
    <Row>
      <Col>
        <Button style="float: right;" type="primary" shape="circle" icon="ios-search" @click="searchData"></Button>
        <Input style="width:200px; float: right; margin-right: 10px;" placeholder="姓名" v-model="searchName" @on-blur="changeName" clearable></Input>
        <Select v-model="dept" placeholder="部门" style="width:200px; float: right; margin-right: 10px;" clearable>
            <Option v-for="item in deptList" v-bind:key="item.value" :value="item.value">{{ item.label }}</Option>
        </Select>
      </Col>
    </Row>
    <Row style="width: 100%; background: #f0f0f0; height: auto; margin-top: 20px; line-height: 40px;">
      <Tag type="dot" v-for="item in labelList" v-bind:key="item.param" closable style="margin-left: 5px;" @on-close="clearParam(item.value, item.param)">{{item.label}}</Tag>
      <span style="color: #348EED; cursor: pointer; margin-left: 10px;" @click="clearAllParam" v-show="labelList.length !== 0">清空搜索条件</span>
    </Row>
    <Row>
      <Table :columns="columns1" :data="data1" height="330"></Table>
    </Row>
  </Card>
</template>

<script>

export default {
  name: 'team_page',
  mounted: function () {
    this.searchData()
  },
  watch: {
    dept: function () {
      if (this.dept > -1) {
        let deptName = ''
        this.deptList.forEach(x => {
          if (x.value === this.dept) {
            deptName = x.label
          }
        })
        let mark = 0
        this.labelList.forEach(x => {
          if (x.param === this.dept) {
            mark = 1
          }
        })
        if (mark === 0) {
          this.labelList.push({
            value: 0,
            label: deptName,
            param: this.dept
          })
        }
      }
    }
  },
  methods: {
    searchData () {
      console.log('部门参数', this.labelList)
      console.log('名字参数', this.searchName)
      let depts = []
      this.labelList.forEach(e => {
        depts.push(e.param)
      })
      this.$ajax.post('/team/search', { deptId: depts, name: this.searchName }).then(data => {
        console.log('搜索全部团队', data)
        this.data1 = data
      })
    },
    clearParam (type, param) {
      console.log(this.labelList)
      switch (type) {
        case 1:
          this.searchName = ''
          for (let item = 0; item < this.labelList.length; item++) {
            if (this.labelList[item].value === 1) {
              this.labelList.splice(item, 1)
              break
            }
          }
          break
        case 0:
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
    changeName () {
      if (this.searchName.trim() === '') {
        return
      }
      let mark = 0
      this.labelList.forEach(x => {
        if (x.value === 1) {
          x.label = this.searchName.trim()
          x.param = this.searchName.trim()
          mark = 1
        }
      })
      if (mark === 0) {
        this.labelList.push({
          value: 1,
          label: this.searchName.trim(),
          param: this.searchName.trim()
        })
      }
    }
  },
  data () {
    return {
      searchName: '',
      dept: -1,
      deptList: [
        {
          value: 1001,
          label: '市场中心'
        },
        {
          value: 1002,
          label: '销售运营部'
        },
        {
          value: 1003,
          label: '财务部'
        },
        {
          value: 1004,
          label: '生产部'
        }
      ],
      labelList: [],
      columns1: [
        {
          title: '姓名',
          key: 'name',
          align: 'center'
        },
        {
          title: '部门',
          key: 'deptName',
          align: 'center'
        },
        {
          title: '职务',
          key: 'position',
          align: 'center'
        },
        {
          title: '性别',
          key: 'gender',
          align: 'center',
          render: (h, params) => {
            return h('span', params.row.gender ? '男' : '女')
          }
        },
        {
          title: '邮箱',
          key: 'email',
          align: 'center',
          width: 200
        }
      ],
      data1: []
    }
  }
}
</script>
