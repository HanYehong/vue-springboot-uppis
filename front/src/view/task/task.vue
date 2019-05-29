<template>
  <Row>
    <Card style="padding: 10px; width: 100%">
      <Row>
        <Col>
          <i-button style="float: right;" type="primary" shape="circle" icon="ios-search" @on-click="searchData"></i-button>
          <Select placeholder="审批状态" style="width:100px; float: right; margin-right: 10px;" v-model="type" clearable>
            <i-option v-for="item in typeList" v-bind:key="item.value" :value="item.value">{{ item.label }}</i-option>
          </Select>
          <Date-picker type="daterange" @on-change="changeDate" placement="bottom-end" placeholder="选择日期" style="width: 200px; float: right; margin-right: 10px;"></Date-picker>
        </Col>
      </Row>
      <Row style="width: 100%; background: #f0f0f0; height: auto; margin-top: 20px; line-height: 40px;">
        <Tag type="dot" v-for="item in labelList" v-bind:key="item.param" closable style="margin-left: 5px;" @on-close="clearParam(item.value, item.param)">{{item.label}}</Tag>
        <span style="color: #348EED; cursor: pointer; margin-left: 10px;" @click="clearAllParam" v-show="labelList.length !== 0">清空搜索条件</span>
      </Row>
      <Row style="font-weight: bolder; width: 100%">
        <Col span="6" style="text-align: center">任务名称</Col>
        <Col span="2" style="text-align: center">完成度</Col>
        <Col span="2" style="text-align: center">负责人</Col>
        <Col span="1" style="text-align: center">权重</Col>
        <Col span="3" style="text-align: center">发布时间</Col>
        <Col span="3" style="text-align: center">截止时间</Col>
        <Col span="3" style="text-align: center">更新时间</Col>
        <Col span="2" style="text-align: center">审批状态</Col>
        <Col span="2" style="text-align: center">操作</Col>
      </Row>
      <Divider />
      <Row>
        <Tree :data="data5" :render="renderContent"></Tree>
      </Row>
    </Card>
    <Modal
      v-model="modal1"
      title="任务分配"
      @on-ok="modalCommit()">
      <Row style="font-weight: bolder; font-size: 20px; color: forestgreen">{{currentTask.taskTitle}}</Row>
      <Row style="font-weight: bolder; margin-top: 5px;">（负责人：{{currentTask.name}}）</Row>
      <Row style="margin-top: 5px; font-weight: bolder">开始时间：{{currentTask.beginTime}}</Row>
      <Row style="margin-top: 5px; font-weight: bolder">截止时间：{{currentTask.deadline}}</Row>
      <Row style="margin-top: 5px; margin-bottom: 10px; color: red; font-weight: bolder;">剩余可分配比重{{currentWeight}}%</Row>
      <div style="border: 1px solid #ccc; border-radius: 10px; padding: 20px;">
        <Form :model="formItem">
          <FormItem label="任务名称">
            <Input v-model="formItem.taskTitle" style="width: 300px;"></Input>
          </FormItem>
          <FormItem label="任务说明">
            <Input v-model="formItem.statement" style="width: 300px;" type="textarea"></Input>
          </FormItem>
          <FormItem label="起始时间 - 截止时间">
            <Date-picker v-model="formItem.time" type="datetimerange" placement="bottom-end" placeholder="选择日期" style="width: 280px;"></Date-picker>
          </FormItem>
          <FormItem label="占父任务的权重">
            <Input v-model="formItem.weight" style="width: 50px;"></Input> %
          </FormItem>
          <FormItem label="负责人">
            <Select v-model="formItem.userId" style="width: 150px;" clearable filterable>
              <Option v-for="item in deptUsers" :value="item.userId">{{item.name}}（{{item.userId}}）</Option>
            </Select>
          </FormItem>
        </Form>
      </div>
    </Modal>
  </Row>
</template>

<script>
import { formatDate } from '../../libs/date'
export default {
  name: 'task_page',
  mounted: function () {
    this.getTaskList()
  },
  watch: {
    $route () {
      this.getTaskList()
    },
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
    modalCommit () {
      this.formItem.beginTime = formatDate(this.formItem.time[0], 'yyyy-MM-dd HH:mm:ss')
      this.formItem.deadline = formatDate(this.formItem.time[1], 'yyyy-MM-dd HH:mm:ss')
      this.formItem.parentTaskId = this.currentTask.taskId
      this.formItem.highTaskId = this.currentTask.highTaskId + this.currentTask.taskId + ','
      console.log(this.formItem)
      this.$ajax.post('/task/publish', this.formItem).then(data => {
        console.log('分配任务成功', data)
        this.$Message.success('分配成功')
        this.$router.go(0)
      })
    },
    getChildWeight () {
      let weight = 0
      this.currentTask.children.forEach(x => {
        weight += x.weight
      })
      this.currentWeight = 100 - weight
    },
    getTaskList () {
      this.$ajax.get('/task/listMyTask', {}).then(data => {
        console.log('获取与自己相关的任务列表', data)
        this.data5 = []
        data.forEach(x => {
          x.render = this.componentData.render
          x.percentage = parseFloat(x.percentage).toFixed(1)
          x.strokeColor = this.getProcessColor(x.percentage)
          x.beginTime = formatDate(x.beginTime, 'yyyy-MM-dd HH:mm:ss')
          x.deadline = formatDate(x.deadline, 'yyyy-MM-dd HH:mm:ss')
          x.updateTime = formatDate(x.updateTime, 'yyyy-MM-dd HH:mm:ss')
          x.children = []
          if (x.highTaskId !== '') {
            let highTaskIdArray = x.highTaskId.split(',')
            this.treeMap[highTaskIdArray[highTaskIdArray.length - 2]].children.push(x)
          } else {
            this.data5.push(x)
          }
          this.treeMap[x.taskId] = x
        })
      })
    },
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
    renderContent (h, { root, node, data }) {
      return h('span', {
        style: {
          display: 'inline-block',
          width: '100%'
        }
      }, [
        h('span', [
          h('Icon', {
            props: {
              type: 'ios-paper-outline'
            },
            style: {
              marginRight: '8px'
            }
          }),
          h('span', data.title)
        ]),
        h('span', {
          style: {
            display: 'inline-block',
            float: 'right',
            marginRight: '32px'
          }
        }, [
          h('Button', {
            props: Object.assign({}, this.buttonProps, {
              icon: 'ios-add'
            }),
            style: {
              marginRight: '8px'
            },
            on: {
              click: () => { this.append(data) }
            }
          }),
          h('Button', {
            props: Object.assign({}, this.buttonProps, {
              icon: 'ios-remove'
            }),
            on: {
              click: () => { this.remove(root, node, data) }
            }
          })
        ])
      ])
    },
    append (data) {
      const children = data.children || []
      children.push({
        title: 'appended node',
        expand: true
      })
      this.$set(data, 'children', children)
    },
    remove (root, node, data) {
      const parentKey = root.find(el => el === node).parent
      const parent = root.find(el => el.nodeKey === parentKey).node
      const index = parent.children.indexOf(data)
      parent.children.splice(index, 1)
    },
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
      formItem: {
        taskTitle: '',
        statement: '',
        time: [],
        weight: '',
        userId: ''
      },
      modal1: false,
      currentTask: {},
      currentWeight: 0,
      deptUsers: [],
      treeMap: {},
      componentData: {
        taskId: 1001,
        taskTitle: '优化平台体验，促进用户注册产品',
        percentage: 90,
        name: '韩业红',
        weight: 50,
        beginTime: '2019-5-4 15:00',
        deadline: '2019-5-8 15:00',
        updateTime: '2019-5-5 12:42',
        status: 0,
        strokeColor: '',
        expand: false,
        doSth: false,
        render: (h, { root, node, data }) => {
          return h('div', {
            style: {
              display: 'inline-block',
              width: '100%'
            }
          }, [
            h('span', [
              h('Icon', {
                props: {
                  type: 'ios-bookmark-outline'
                },
                style: {
                  marginRight: '8px',
                  fontSize: '20px'
                }
              }),
              h('span', {
                style: {
                  cursor: 'pointer',
                  color: '#348EED',
                  width: '20%',
                  display: 'inline-block',
                  whiteSpace: 'normal'
                },
                on: {
                  click: () => {
                    let type = 0
                    if (data.children.length > 0) {
                      type = 1
                    }
                    this.$router.push({ path: 'task_detail_page', query: { taskId: data.taskId, type: type } })
                  }
                }
              }, data.taskTitle)
            ]),
            h('span', {
              style: {
                marginLeft: '10px',
                width: '10%',
                display: 'inline-block'
              }
            }, [
              h('Progress', {
                props: {
                  percent: data.percentage,
                  strokeColor: data.strokeColor
                }
              })
            ]),
            h('span', {
              style: {
                marginLeft: '5px',
                width: '4%',
                display: 'inline-block'
              }
            }, data.name),
            h('span', {
              style: {
                marginLeft: '30px',
                width: '3%',
                display: 'inline-block'
              }
            }, data.weight + '%'),
            h('span', {
              style: {
                marginLeft: '2.5%',
                width: '10%',
                display: 'inline-block'
              }
            }, data.beginTime),
            h('span', {
              style: {
                marginLeft: '2.5%',
                width: '10%',
                display: 'inline-block'
              }
            }, data.deadline),
            h('span', {
              style: {
                marginLeft: '2.5%',
                width: '10%',
                display: 'inline-block',
                textAlign: 'center'
              }
            }, data.updateTime === '' ? '-' : data.updateTime),
            h('span', {
              style: {
                marginLeft: '2.5%',
                width: '5%',
                display: 'inline-block'
              }
            }, data.status === 0 ? '未提交' : data.status === 1 ? '未审核' : data.status === 2 ? '审核通过' : '驳回'),
            h('span', {
              style: {
                float: 'right',
                marginRight: '1.5%',
                marginTop: '-10px',
                width: '5%',
                color: '#00CC00',
                fontSize: '20px',
                cursor: 'pointer',
                display: 'inline-block'
              }
            }, [
              h('Dropdown',
                [
                  h('Icon', {
                    props: {
                      type: 'md-arrow-dropdown'
                    }
                  }), h('DropdownMenu', {
                    slot: 'list'
                  }, [
                    h('DropdownItem', {
                      nativeOn: {
                        click: () => {
                          // 删除操作
                          this.$ajax.post('/task/delete', { taskId: data.taskId }).then(data => {
                            console.log('删除成功', data)
                            this.$router.go(0)
                          })
                        }
                      }
                    }, '删除'),
                    h('DropdownItem', {
                      nativeOn: {
                        click: () => {
                          // 提交操作
                          let type = 0
                          if (data.children.length > 0) {
                            type = 1
                          }
                          this.$router.push({ path: 'task_detail_page', query: { taskId: data.taskId, type: type } })
                        }
                      }
                    }, '提交'),
                    h('DropdownItem', {
                      nativeOn: {
                        click: () => {
                          // 分配操作
                          this.modal1 = true
                          this.currentTask = data
                          this.getChildWeight()
                          this.$ajax.get('/task/listSameDeptUsers', {}).then(data => {
                            console.log('获取同部门所有人员信息', data)
                            this.deptUsers = data
                          })
                        }
                      }
                    }, '分配')
                  ])
                ], {
                  props: {
                    placement: 'bottom'
                  }
                })
            ]),
            h('Divider')
          ])
        },
        children: []
      },
      data5: [],
      buttonProps: {
        type: 'default',
        size: 'small'
      },
      count: 3,
      chooseDate: [],
      labelList: [],
      type: -1,
      typeList: [
        {
          value: 0,
          label: '未提交'
        },
        {
          value: 1,
          label: '正在审核'
        },
        {
          value: 2,
          label: '审核通过'
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
          title: '任务名称',
          key: 'taskName',
          align: 'center',
          fixed: 'left',
          width: 300,
          render: (h, params) => {
            return h('div', [
              h('Icon', {
                props: {
                  type: 'person'
                }
              }),
              h('strong', params.row.taskName)
            ])
          }
        },
        {
          title: '完成度',
          key: 'completeRate',
          align: 'center',
          width: 150
        },
        {
          title: '负责人',
          key: 'contactMan',
          align: 'center',
          width: 150
        },
        {
          title: '权重',
          key: 'rate',
          align: 'center',
          width: 150
        },
        {
          title: '发布时间',
          key: 'createTime',
          align: 'center',
          width: 150
        },
        {
          title: '截止时间',
          key: 'endTime',
          align: 'center',
          width: 150
        },
        {
          title: '更新时间',
          key: 'updateTime',
          align: 'center',
          width: 150
        },
        {
          title: '审批状态',
          key: 'auditStatus',
          align: 'center',
          fixed: 'right',
          width: 100
        },
        {
          title: '操作',
          key: 'doSth',
          align: 'center',
          fixed: 'right',
          width: 150
        }
      ]
    }
  }
}
</script>
