import {showToast} from "../../utils/asyncWx.js";
var util = require('../../utils/util.js')
var util1 = require('../../utils/util1.js')
var util2 = require('../../utils/util2.js')
var util3 = require('../../utils/util3.js')

var hours = [];
var minuts = [];
var date = new Date();
var mt = date.getMinutes();
var nowh = date.getHours();

var timedate = util.formatTime(new Date());
for (let i = nowh; i <= 23; i++) {
  hours.push(i)
}
for (let i = mt; i <= 59; i++) {
  minuts.push(i)
}
const fixhours=[];
const fixminuts=[];
for (let i = 0; i <= 23; i++) {
  fixhours.push(i)
}
for (let i = 0; i <= 59; i++) {
  fixminuts.push(i)
}

const gratuities = [
  { id: 0, value: 2 },
  { id: 1, value: 5 },
  { id: 2, value: 10 },
  { id: 3, value: 15 },
  { id: 4, value: 20 },
  { id: 5, value: 50 }
];


Page({
  data: {

      orderactive: true,//控制下单按钮可用与否的开关

      totalmoney: 0.00,//总价

      days: ["今天", "明天","后天"],//送货时间选择器中的送货日

      hours: hours,//送货时间选择器中的送货时

      minuts: fixminuts,//送货时间选择器中的送货分

      gratuities: gratuities,//费用的数额定义

      showcustompicker: false,//控制送货时间选择器显示与隐藏

      hidegratuityinput: true,//控制费用输入框的隐藏与显示

      timepickervalue: [0, 1, 0],//默认选中发货时间选择器中的每一列的第一项

      sendtime: "立即",//发货时间

      Sensitivewords:[],//敏感词汇

      gratuity: 0,//费用

      selectgratuity:0,//选中的费用

      selectedGratuityindex: 99,//被选中的费用样式控制

      pickertype: "time",//自定义选择器类别

      selectedservicetype:1,//默认的服务类型

      ticketid:null,//选中的优惠券id

      ticketprice:0,//选中的优惠券价格

      leavemessage:'',//备注

      needmessage:null,//需求留言

      discounttip:"请选择优惠券",//优惠券选择的提示信息,没有可用优惠券时变成没有可用优惠券
      
      errrmsg:"请填写完相关信息再下单",
      orderContent:'',
      orderAddress:'',
      thingtype:'',
      thingtype2:'',
      thingtype3:'',
      sendtime1:timedate,
      orderid:0,

  },
  onLoad: function (options) {
 
    this.setData({
      orderContent:options.orderContent,
      orderAddress:options.orderAddress,
      thingtype:options.thingtype,
      thingtype2:options.thingtype2,
      thingtype3:options.thingtype3,
    })
  },
  onShow:function(){
    
    var ticketprice = parseFloat(this.data.ticketprice);
    if (ticketprice != null) {
      this.setData({
        ticketprice: ticketprice,
      })
    }else{
      var oldticketprice = this.data.ticketprice;
      if (oldticketprice != 0 && this.data.ticketid!=null) {
        var totalm = parseFloat(this.data.totalmoney);
        totalm += parseFloat(oldticketprice);
        this.setData({
          ticketprice: 0,
          ticketid: null,
          discounttip: "请选择优惠券",
          totalmoney: totalm.toFixed(2)
        })
      }
    }
  },

 

  activecustompicker: function (e) {
      var pickertype = e.currentTarget.dataset.picktype;
      if (pickertype == "time") {
          this.setData({
            pickertype: "time"
          })
      } else if (pickertype == "gratuity") {
          this.setData({
            pickertype: "gratuity"
          })
      }
      this.setData({
          showcustompicker: true
      })
  },//显示发货时间选择器
  hidecustompicker: function () {
      this.setData({
        showcustompicker: false,
        selectgratuity:0,
        hidegratuityinput: true,
        selectedGratuityindex: 99
      })
  },//隐藏发货时间选择器
  bindtimepickerChange: function (e) {
      var val = e.detail.value
      if (val[0] == 0)
      {
        this.setData({
          hours: hours,
        })
        if(val[1]!=0)
        {
          this.setData({
            minuts: fixminuts,
          })
        }else{
          this.setData({
            minuts: minuts,
          })
        }
      }else{
        this.setData({
          hours: fixhours,
          minuts: fixminuts,
        })
      }
      var newvalue = [val[0], val[1], val[2]];
      this.setData({
          timepickervalue: newvalue,
      })
  },//发货时间选择器的值选择
  specialtimeselect: function () {
      this.setData({
          sendtime: "立即",
          showcustompicker: false,
      })
  },//立即发货的选择
  activegratuityinput: function () {
      this.setData({
          hidegratuityinput: false,
          selectedGratuityindex: 99
      })
  },//激活费用输入框
  gratuityselect: function (e) {
      var val = e.currentTarget.dataset.value;
      var gratuity1 = parseFloat(this.data.gratuities[val].value);
      gratuity1 = parseFloat(gratuity1);
      this.setData({
          selectgratuity: gratuity1,
          selectedGratuityindex: val,
          hidegratuityinput: true,
      })
  },//费用选择

  gratuityinput: function (e) {
      var money = e.detail.value.trim();
      money=parseFloat(money);
      if(money.toString()=="NaN")
      {
        money=0;
      }
      this.setData({
          selectgratuity: money,
      })
  },//费用输入
  confrimgratuity:function(){
    if (this.data.pickertype =="time")
    {
      var time = {};
      var time1 = {};
      time1.day='';
      time1.seconds=0;
      time.day = this.data.days[this.data.timepickervalue[0]];
      time.hour = this.data.hours[this.data.timepickervalue[1]];
      time.minut = this.data.minuts[this.data.timepickervalue[2]];
      var date = new Date();
      var hour=date.getHours();
      var mt = date.getMinutes();

      if(time.day=="今天")
      {
        if (time.hour < hour) {
          wx.showToast({
            title: '预约时间不能小于当前时间',
            icon: "none"
          })
          return false;
        }
        if (time.hour == hour && time.minut < mt) {
          wx.showToast({
            title: '预约时间不能小于当前时间',
            icon: "none"
          })
          return false;
        }
        time1.day=util1.formatTime(new Date());
        time1.hour = this.data.hours[this.data.timepickervalue[1]];
        time1.minut = this.data.minuts[this.data.timepickervalue[2]];
        time1.seconds=date.getSeconds();
      }
      if(time.day=="明天"){
        time1.day=util2.formatTime(new Date());
        time1.hour = this.data.hours[this.data.timepickervalue[1]];
        time1.minut = this.data.minuts[this.data.timepickervalue[2]];
        time1.seconds=date.getSeconds();
      }
      if(time.day=="后天"){
        time1.day=util3.formatTime(new Date());
        time1.hour = this.data.hours[this.data.timepickervalue[1]];
        time1.minut = this.data.minuts[this.data.timepickervalue[2]];
        time1.seconds=date.getSeconds();
      }

      this.setData({
        sendtime: time,
        sendtime1:time1.day + time1.hour + ':' + time1.minut + ':' + time1.seconds,
      })
      this.hidecustompicker();
    }else{     
      var total = parseFloat(this.data.totalmoney);
      var gratuity = parseFloat(this.data.gratuity);
      var selectgratuity = parseFloat(this.data.selectgratuity);
      var youhuiprice = parseFloat(this.data.ticketprice);
      
      var newtotal = total - gratuity ;
      var totalmoney =  selectgratuity + newtotal ;
      var youhuitotal = selectgratuity - youhuiprice;
      this.setData({
        selectgratuity: 0,
        gratuity: selectgratuity,
        showcustompicker: false,
        hidegratuityinput: true,
        totalmoney: (totalmoney===youhuitotal)?totalmoney.toFixed(2):youhuitotal,
        selectedGratuityindex: 99
      })
    }
  },//确认选好的费用


  messageinput:function(e){
    var value = e.detail.value;
    var arrmg = this.data.Sensitivewords;
    for (var i = 0; i < arrmg.length;i++)
    {
      var r = new RegExp(arrmg[i],"ig");
      value=value.replace(r,"*");
    }
    this.setData({
      leavemessage: value
    })
  },//备注输入


xiadanzhifu:function(){
    var that = this
    const {totalmoney}=this.data;

    if(totalmoney===0.00){
      showToast({title:"您还没有选择费用"});
      return ;
    }
    var activeindex = wx.getStorageSync("activeindex")

    if(activeindex=='0'){
      wx.request({
        url: 'http://littleeyes.cn:8080/add-order',
        method: 'POST',
        data:{
          data:{
            "orderMoney": that.data.totalmoney,
            "orderType": "1",
            "clientID": "19990523",
            "orderContent":that.data.thingtype+' '+'送到'+ ' '+that.data.orderContent,
            "orderAddress": that.data.orderAddress,
            "orderReserveTime": that.data.sendtime1
          },
          check: 0
        },
        header: {  
          'content-type': 'application/json'  //这里注意POST请求content-type是小写，大写会报错  
        },
        success:function(res){
          console.log(res.data)
          console.log(res.data.data.orderID)
          that.setData({
            orderid:res.data.data.orderID,
          })
          wx.setStorageSync('orderid', res.data.data.orderID);
        }
      })
    }
    if(activeindex=='1'){
      wx.request({
        url: 'http://littleeyes.cn:8080/add-order',
        method: 'POST',
        data:{
          data:{
            "orderMoney": that.data.totalmoney,
            "orderType": "1",
            "clientID": "19990523",
            "orderContent":that.data.thingtype2+' '+'从 '+ ' '+that.data.orderContent+ ' 取',
            "orderAddress": that.data.orderAddress,
            "orderReserveTime": that.data.sendtime1
          },
          check: 0
        },
        header: {  
          'content-type': 'application/json'  //这里注意POST请求content-type是小写，大写会报错  
        },
        success:function(res){
          console.log(res.data)
          console.log(res.data.data.orderID)
          that.setData({
            orderid:res.data.data.orderID,
          })
          wx.setStorageSync('orderid', res.data.data.orderID);
        }
      })
    }
    if(activeindex=='2'){
      wx.request({
        url: 'http://littleeyes.cn:8080/add-order',
        method: 'POST',
        data:{
          data:{
            "orderMoney": that.data.totalmoney,
            "orderType": "1",
            "clientID": "19990523",
            "orderContent": '去 ' +that.data.orderContent + ' '+ that.data.thingtype3,
            "orderAddress": that.data.orderAddress,
            "orderReserveTime": that.data.sendtime1
          },
          check: 0
        },
        header: {  
          'content-type': 'application/json'  //这里注意POST请求content-type是小写，大写会报错  
        },
        success:function(res){
          console.log(res.data)
          console.log(res.data.data.orderID)
          that.setData({
            orderid:res.data.data.orderID,
          })
          wx.setStorageSync('orderid', res.data.data.orderID);
        }
      })
    }

   

    

    wx.navigateTo({
      url: '../paymethod/index?money1=' + totalmoney,
    })

  },//点击下单并判断能否跳转到支付


})