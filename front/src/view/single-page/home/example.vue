<template>
    <div ref="dom"></div>
</template>

<script>
import echarts from 'echarts'
import { on, off } from '@/libs/tools'
export default {
  name: 'serviceRequests',
  props: ['xAxis', 'series'],
  watch: {
    xAxis: {
      handler (newVal, oldVal) {
        if (this.dom) {
          this.option = {
            title: {
              text: '绩效曲线图'
            },
            tooltip : {
              trigger: 'axis',
              axisPointer: {
                type: 'cross'
              }
            },
            legend: {
              data:['绩效总分','考勤得分','任务得分','评级得分']
            },
            grid: {
              left: '3%',
              right: '4%',
              bottom: '3%',
              containLabel: true
            },
            xAxis : [
              {
                type : 'category',
                boundaryGap : false,
                data : this.xAxis
              }
            ],
            yAxis : [
              {
                type : 'value'
              }
            ],
            series : [
              {
                name:'绩效总分',
                type:'line',
                smooth: true,
                data: this.series[0]
              },
              {
                name:'考勤得分',
                type:'line',
                smooth: true,
                data: this.series[1]
              },
              {
                name:'任务得分',
                type:'line',
                smooth: true,
                data: this.series[2]
              },
              {
                name:'评级得分',
                type:'line',
                smooth: true,
                data: this.series[3]
              }
            ]
          }
          this.dom.setOption(this.option)
        } else {
          this.init()
        }
      },
      deep: true //对象内部属性的监听，关键。
    }
  },
  data () {
    return {
      dom: null,
      option: {
        title: {
          text: '绩效曲线图'
        },
        tooltip : {
          trigger: 'axis',
          axisPointer: {
            type: 'cross'
          }
        },
        legend: {
          data:['绩效总分','考勤得分','任务得分','评级得分']
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis : [
          {
            type : 'category',
            boundaryGap : false,
            data : this.xAxis
          }
        ],
        yAxis : [
          {
            type : 'value'
          }
        ],
        series : [
          {
            name:'绩效总分',
            type:'line',
            smooth: true,
            data: this.series[0]
          },
          {
            name:'考勤得分',
            type:'line',
            smooth: true,
            data: this.series[1]
          },
          {
            name:'任务得分',
            type:'line',
            smooth: true,
            data: this.series[2]
          },
          {
            name:'评级得分',
            type:'line',
            smooth: true,
            data: this.series[3]
          }
        ]
      }
    }
  },
  methods: {
    resize () {
      this.dom.resize()
    }
  },
  mounted () {
    this.$nextTick(() => {
      this.dom = echarts.init(this.$refs.dom)
      this.dom.setOption(this.option)
      on(window, 'resize', this.resize)
    })
  },
  beforeDestroy () {
    off(window, 'resize', this.resize)
  }
}
</script>
