<template>
  <Card style="padding: 10px; width: 100%">
    <Row style="margin: 10px;">
      <span style="float: left; color: slategray; font-weight: bolder; font-size: 20px;">评分系统指标设置</span>
      <i-switch size="large" style="float: right; margin-top: 5px;">
        <span slot="open">ON</span>
        <span slot="close">OFF</span>
      </i-switch>
    </Row>
    <Row style="margin: 20px;">
      <Collapse simple>
        <Panel name="1">
          <span>高层领导</span>
          <Row slot="content">
            <Row v-for="(item, index) in allData.highLeader" style="margin-top: 10px; margin-bottom: 10px;">
              <Input v-model="allData.highLeader[index]" style="width: 850px; float: left" placeholder="请输入考核指标..." :value="item"></Input>
              <Button type="error" style="float: right" ghost @click="remove(2, index)">删除</Button>
            </Row>
            <Button type="info" ghost style="margin-top: 10px;" @click="add(2)">新增</Button>
          </Row>
        </Panel>
        <Panel name="2">
          中层主管
          <Row slot="content">
            <Row v-for="(item, index) in allData.middleLeader" style="margin-top: 10px; margin-bottom: 10px;">
              <Input v-model="allData.middleLeader[index]" style="width: 850px; float: left" placeholder="请输入考核指标..." :value="item"></Input>
              <Button type="error" style="float: right" ghost @click="remove(1, index)">删除</Button>
            </Row>
            <Button type="info" ghost style="margin-top: 10px;" @click="add(1)">新增</Button>
          </Row>
        </Panel>
        <Panel name="3">
          普通员工
          <Row slot="content">
            <Row v-for="(item, index) in allData.user" style="margin-top: 10px; margin-bottom: 10px;">
              <Input v-model="allData.user[index]" style="width: 850px; float: left" placeholder="请输入考核指标..." :value="item"></Input>
              <Button type="error" style="float: right" ghost @click="remove(0, index)">删除</Button>
            </Row>
            <Button type="info" ghost style="margin-top: 10px;" @click="add(0)">新增</Button>
          </Row>
        </Panel>
      </Collapse>
      <Button style="float: right; margin-top: 10px;" type="primary" @click="saveTarget">
        保存
      </Button>
    </Row>
  </Card>
</template>

<script>
  export default {
    name: 'setting_page',
    mounted: function () {
      this.getTargetData()
    },
    methods: {
      add (type) {
        switch (type) {
          case 0:
            this.allData.user.push('')
            break
          case 1:
            this.allData.middleLeader.push('')
            break
          case 2:
            this.allData.highLeader.push('')
            break
        }
      },
      remove (type, index) {
        switch (type) {
          case 0:
            this.allData.user.splice(index, 1)
            break
          case 1:
            this.allData.middleLeader.splice(index, 1)
            break
          case 2:
            this.allData.highLeader.splice(index, 1)
            break
        }
      },
      getTargetData() {
        this.$ajax.get('/setting/getTargets', {}).then(data => {
          console.log('获得已设置的指标数据', data)
          this.allData = data
        })
      },
      saveTarget() {
        this.allData.highLeader.forEach(x => {
          if (x === '') {
            this.$Message.error('有未填写完成的指标')
            return
          }
        })
        this.allData.middleLeader.forEach(x => {
          if (x === '') {
            this.$Message.error('有未填写完成的指标')
            return
          }
        })
        this.allData.user.forEach(x => {
          if (x === '') {
            this.$Message.error('有未填写完成的指标')
            return
          }
        })
        this.$ajax.post('/setting/saveTargets', this.allData).then(data => {
          console.log('保存指标数据', data)
          this.$Message.success('保存成功')
        })
      }
    },
    data () {
      return {
        allData: {
          highLeader: [],
          middleLeader:[],
          user: []
        }
      }
    }
  }
</script>
