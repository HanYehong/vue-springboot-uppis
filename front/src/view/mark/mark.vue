<template>
  <Card style="padding: 40px;">
    <Row>
      <Row style="text-align: center;">
        <span style="font-size: 24px; font-weight: bolder; color: cadetblue;">{{perInfo.periodName}}</span>
      </Row>
      <Row style="margin: 10px;">
        <Row class="label">自评</Row>
        <hr class="drive" />
        <Row style="padding: 10px;">
          <Row class="child-label" v-if="sel.name">
            <div style="float: left">{{sel.name}}</div>
            <div style="float: right">
              <Button type="primary" shape="circle" @click="entryDetail(0, sel.userId)">进入</Button>
            </div>
          </Row>
        </Row>
      </Row>
      <Row style="margin: 10px;">
        <Row class="label">同级互评</Row>
        <hr class="drive" />
        <Row style="padding: 10px;">
          <Row class="child-label" v-for="item in sameLevelUsers">
            <div style="float: left">{{item.name}}</div>
            <div style="float: right">
              <Button type="primary" shape="circle" @click="entryDetail(1, item.userId)">进入</Button>
            </div>
          </Row>
        </Row>
      </Row>
      <Row style="margin: 10px;">
        <Row class="label">你的下级</Row>
        <hr class="drive" />
        <Row style="padding: 10px;">
          <Row class="child-label" v-for="item in subLevelUsers">
            <div style="float: left">{{item.name}}</div>
            <div style="float: right">
              <Button type="primary" shape="circle" @click="entryDetail(2, item.userId)">进入</Button>
            </div>
          </Row>
        </Row>
      </Row>
    </Row>
  </Card>
</template>

<script>
export default {
  name: 'mark_page',
  mounted: function () {
    this.getCuurentPerInfo()
    this.getMarkPersonList()
  },
  data () {
    return {
      perInfo: {},
      sameLevelUsers: [],
      subLevelUsers: [],
      sel: {}
    }
  },
  methods: {
    entryDetail (type, userId) {
      this.$router.push({ path: 'mark_detail_page', query: { type: type, userId: userId, perId: this.perInfo.periodId, perTitle: this.perInfo.periodName } })
    },
    async getCuurentPerInfo () {
      this.$ajax.get('/mark/getPeriodInfo', {}).then(data => {
        console.log('获得当前绩效信息', data)
        this.perInfo = data
      })
    },
    async getMarkPersonList () {
      this.$ajax.get('/mark/listPerson', {}).then(data => {
        console.log('获取所有需要评级人员列表', data)
        this.sameLevelUsers = data.sameLevelUsers
        this.subLevelUsers = data.subLevelUsers
        if (data.sel) {
          this.sel = data.sel
        }
      })
    }
  }
}
</script>

<style scoped>
  .label {
     color: #ccc;
     font-size: 15px;
   }

  .drive {
    width: 100%;
    margin-top: 10px;
    margin-bottom: 10px;
    background-color: #ccc;
    height: 1px;
    border: none;
  }

  .child-label {
    font-size: 17px;
    margin-bottom: 10px;
    width: 100%;
  }
</style>
