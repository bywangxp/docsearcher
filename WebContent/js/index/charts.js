var myChart1 = echarts.init(document.getElementById('chart1'));
// 指定图表的配置项和数据
var option1 = {
    title : {
        text: 'PPT文档分类统计',
        subtext: '统计日期：2016-11-05',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        x : 'center',
        y : 'bottom',
        data:['课件','演示','科技','文学','情感','教程','其他']
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType : {
                show: true,
                type: ['pie', 'funnel']
            },
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    series : [
        {
            name:'总库存：133',
            type:'pie',
            radius : [20, 140],
            roseType : 'area',
            data:[
                {value:10, name:'课件'},
                {value:5, name:'演示'},
                {value:15, name:'科技'},
                {value:25, name:'文学'},
                {value:20, name:'情感'},
                {value:35, name:'教程'},
                {value:30, name:'其他'}
            ]
        }
    ]
};
// 使用刚指定的配置项和数据显示图表
myChart1.setOption(option1);

var myChart2 = echarts.init(document.getElementById('chart2'));
// 指定图表的配置项和数据
var option2 = {
    title : {
        text: '用户数和文档数统计',
        subtext: '统计日期：2016-11-05',
        x:'center'
    },
    grid : {
        top:'100px'
    },
    tooltip: {
        trigger: 'axis'
    },
    toolbox: {
        feature: {
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    legend: {
        top:'50px',
        data:['新用户','新文档','用户增长','文档增长']
    },
    xAxis: [
        {
            type: 'category',
            data: ['11.01','11.02','11.03','11.04','11.05','11.06','11.07','11.08','11.09','11.10','11.11','11.12']
        }
    ],
    yAxis: [
        {
            type: 'value',
            name: '数量',
            min: 0,
            max: 50,
            interval: 5,
            axisLabel: {
                formatter: '{value} '
            }
        },
        {
            type: 'value',
            name: '增长率',
            min: 0,
            max: 100,
            interval: 10,
            axisLabel: {
                formatter: '{value} %'
            }
        }
    ],
    series: [
        {
            name:'新用户',
            type:'bar',
            data:[5,4,3,15,22,42,32,11,22,14,3,5]
        },
        {
            name:'新文档',
            type:'bar',
            data:[15,14,17,28,26,15,14,17,5,41,27,19]
        },
        {
            name:'用户增长',
            type:'line',
            yAxisIndex: 1,
            data:[2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
        },
        {
            name:'文档增长',
            type:'line',
            yAxisIndex: 1,
            data:[1.0, 15, 14,80,15,89,36,15,56, 6.2, 16.5, 12.0]
        }
    ]
};
// 使用刚指定的配置项和数据显示图表
myChart2.setOption(option2);

