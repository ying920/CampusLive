

import { getSetting, chooseAddress, openSetting, showModal ,showToast} from "../../utils/asyncWx.js";
import regeneratorRuntime from '../../lib/runtime/runtime';

var QQMapWX = require('../../lib/qqmap-wx-jssdk.js');
var qqmapsdk = new QQMapWX({
  key: '7IYBZ-URYWX-V4D4G-TAHG3-FTCCV-BSFBV'
});
var sliderWidth = 96; // 要设置slider的宽度，用于计算中间位置
var showBusy = text => wx.showToast({
  title: text,
  icon: 'loading',
  duration: 500
});

var showSuccess = text => wx.showToast({
  title: text,
  icon: 'success'
});

// 显示失败提示
var showModel = (title, content) => {
  wx.hideToast();
  wx.showModal({
    title,
    content: JSON.stringify(content),
    showCancel: false
  });
};
var lat;
var lng;

Page({
  data:{
    dz: {},
    tabs: ["代送件", "代取件","代办事" ],
    activeIndex: 0,
    sliderOffset: 0,
    sliderLeft: 0,
    dizhi1:'',
    useraddress:'',
    information1:'从哪里出发',
    information11:'从哪取',
    dizhi2:'收货地址',
    information2:'送到哪里去',
    information22:'从哪收',
    information222:'要办理的事情所在地点',
    thingType:'',
    weight:'请选择要配送的物品类型，重量',
    thingType2:'',
    thingType3:'',
    typeforthing2:'请选择要取件的物品类型，重量',
    typeforthing3:'请选择要代办事情的类型以及所需时间',
  
    latitude:'',
    longitude:'',
    localCity:'',
    realAddress:{},

    markers: [{

      id: 0,
      iconPath: '../../icons/location.png',
      latitude:28.727340,
      longitude:115.816720,
      width: 25,
      height: 25,
      callout: {
        content: '最快5分钟内接单',
        fontSize: 16,
        color: '#ffffff',
        bgColor: '#319ED3',
        padding: 8,
        borderRadius:15,
        boxShadow: '4px 8px 16px 0 rgba(0)',
        display:'ALWAYS'
      },
      
    }],

    controls: [{
      id: 1,
      iconPath: '../../icons/refresh.png',
      position: {
        left: 30,
        width: 330,
        height: 330
      }
    }],

  
  },

  tabClick: function (e) {
    this.setData({
      sliderOffset: e.currentTarget.offsetLeft,
      activeIndex: e.currentTarget.id
    });
  },
  async gotopay(){

    const {dizhi1,dizhi2,thingType,thingType2,thingType3,activeIndex}=this.data;

    if(activeIndex==0){
      //判断是否填了出发地址
      if(!dizhi1){
        await showToast({title:"您还没选择出发地址！"});
        return ;
      }
      //判断用户是否选择送达地址
      if(!dizhi2.cityName){
        await showToast({title:"您还没选择送达地址！"});
        return ;
      }
      //判断是否选择商品类型
      if(!thingType){
        await showToast({title:"您还没选择商品类型及重量！"});
        return ;
      }
      wx.setStorageSync('activeindex', activeIndex);
        //否则跳转到支付页面
      wx.navigateTo({
        url: '../pay/index?orderAddress='+ dizhi1+'&thingtype='+thingType+'&orderContent='+dizhi2.all,
      });
      // var that = this
      // wx.request({
      //   url: 'http://littleeyes.cn:8080/get-all-my-order/'+'19990523',
      //   headers:{
      //     'Content-Type': 'application/json'
      //   },
      //   method: 'GET',
      //   success:function(res){
      //     //console.log("返回成功的数据:" + res.data.msg ) //返回的会是对象，可以用JSON转字符串打印出来方便查看数据  
      //     console.log("返回成功的数据:"+ JSON.stringify(res.data)) //这样就可以愉快的看到后台的数据
      //   },
      //   fail:function(fail){
      //     console.log("获取数据失败")
      //   },
      //   complete:function(arr){

      //   }
      // })
    }
    if(activeIndex==1){
       //判断是否填了出发地址
      if(!dizhi1){
        await showToast({title:"您还没选择从哪收件！"});
        return ;
      }
      //判断用户是否选择送达地址
      if(!dizhi2.cityName){
        await showToast({title:"您还没选择从哪取件！"});
        return ;
      }
      //判断是否选择商品类型
      if(!thingType2){
        await showToast({title:"您还没选择商品类型及重量！"});
        return ;
      }
      wx.setStorageSync('activeindex', activeIndex);
        //否则跳转到支付页面
      wx.navigateTo({
        url: '../pay/index?orderAddress='+ dizhi1+'&thingtype2='+thingType2+'&orderContent='+dizhi2.all,
      }); 
    }
    if(activeIndex==2){

     //判断用户是否选择送达地址
     if(!dizhi2.cityName){
       await showToast({title:"您还没选择需要代办事的地点！"});
       return ;
     }
     //判断是否选择商品类型
     if(!thingType3){
       await showToast({title:"您还没选择需要办理的事情类型！"});
       return ;
     }

     wx.setStorageSync('activeindex', activeIndex);
       //否则跳转到支付页面
     wx.navigateTo({
      url: '../pay/index?orderAddress='+ dizhi1+'&thingtype3='+thingType3+'&orderContent='+dizhi2.all,
     }); 
   }
   var that = this
    wx.request({
      url: 'http://littleeyes.cn:8080/add-address',
      method: 'POST',
      data:{
        data:{
            "userID": "19990523",
            "userAddress": that.data.dizhi1
        },
      check:0
      },
      header: {  
        'content-type': 'application/json'  //这里注意POST请求content-type是小写，大写会报错  
      },
      success:function(res){

        console.log(res.data)
      },
      fail:function(err){
        console.log("post失败了 小老弟!"+err.errMsg)
      }
    })
  

  },
  onShow() {
    // 1 获取缓存中的收货地址信息
    const dizhi2= wx.getStorageSync("address1");
 
    this.setData({ dizhi2 });
  },
  // 点击 收货地址
  async handleChooseAddress() {
    try {
      // 1 获取 权限状态
      const res1 = await getSetting();
      const scopeAddress = res1.authSetting["scope.address1"];
      // 2 判断 权限状态
      if (scopeAddress === false) {
        await openSetting();
      }
      // 4 调用获取收货地址的 api
      let address1 = await chooseAddress();
      address1.all = address1.provinceName + address1.cityName + address1.countyName + address1.detailInfo;
      // 5 存入到缓存中
      wx.setStorageSync("address1", address1);

    } catch (error) {
      console.log(error);
    }
  },

  fahuodizhi:function(){
    wx.navigateTo({
      url: '/pages/dizhi/index',
    })
    // const session = qcloud.Session.get()
    // if(session){
    //   wx.navigateTo({
    //     url: '/pages/dizhi/index',
    //   })
    // }else{
    //   showBusy('加载中');
    //   setTimeout(function(){
    //     wx.showModal({
    //       title: '提示',
    //       content: '请登录，不然可能会影响部分功能使用，请前往登陆',
    //       confirmText: '去登陆',
    //       success: res => {
    //         if (res.confirm) {
    //           wx.navigateTo({
    //             url: '/pages/login/index',
    //           })
    //         }
    //       }
    //     })
    //   },500)
    // }
    },

 

  show:function(){
    wx.navigateTo({
      url: '/pages/rule/index',
    })
  },

  onLoad: function (options) {
    
    var that = this;
   
    wx.getSystemInfo({
      success: function (res) {
        that.setData({
          sliderLeft: (res.windowWidth / that.data.tabs.length - sliderWidth) / 2,
          sliderOffset: res.windowWidth / that.data.tabs.length * that.data.activeIndex
        });
      }
    });
    if(options.thingType != null && options.weight != null) {
      that.setData({
        thingType: options.thingType + ',' + options.weight + '公斤',
        weight: ''
      })
    }
    wx.getLocation({
      type: 'gcj02',
      success: function (res) {
        var key = '7IYBZ-URYWX-V4D4G-TAHG3-FTCCV-BSFBV'
        that.setData({
          latitude: res.latitude,
          longitude: res.longitude
        })

        wx.setStorage({
          key: 'location',
          data: res,
        })
        wx.request({
          url: 'https://apis.map.qq.com/ws/coord/v1/translate?locations=' + res.latitude + ',' + res.longitude + '&type=' + 5 + '&key=' + key,
          header: {
            'content-type': 'application/json' // 默认值
          },
          success: function (res) {
            console.log(res.data.locations[0])
            that.setData({
              realAddress: res.data.locations[0],
              latitude: res.data.locations[0].lat,
              longitude: res.data.locations[0].lng,
             
            })
          }
        })

        qqmapsdk.reverseGeocoder({

          location: {

            latitude: that.data.latitude,

            longitude: that.data.longitude

          },

          success: function (res) {

            console.log(res);

            let province = res.result.address_component.province;//省份

            let city = res.result.address_component.city;//城市
            let district = res.result.address_component.district;  //县
            let town = res.result.address_reference.town.title; //镇
            let street = res.result.address_component.street;
            let street_number = res.result.address_component.street_number;

            that.setData({
              dizhi1:city+district+town+street+(street===street_number?'':street_number)
            })
          },
          fail: function (res) {
            console.log(res);
          }
        });

      
      }
    });
  },

    /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    this.mapCtx = wx.createMapContext('myMap', this)
  },

  regionchange:function(e){
    this.mapCtx.getCenterLocation({
      success: function (res) {
        lat = res.latitude,
        lng = res.longitude
        
      }
    })
    this.mapCtx.translateMarker({
      markerId: 0,
     
      duration: 500,
      destination: {
        latitude: lat,
        longitude:lng,
      }
    })
   

  },
refresh:function(){
  var that = this
  this.mapCtx.moveToLocation()
wx.getStorage({
  key: 'location',
  success: function(res) {
    
  that.setData({
    latitude:res.data.latitude,
    longitude:res.data.longitude
  })
  },
}),
lat = that.data.latitude;
lng = that.data.longitude
  this.mapCtx.translateMarker({
    markerId: 0,

    duration: 500,
    destination: {
      latitude: lat,
      longitude: lng
    }
   
  })
 console.log(that.data.latitude)
},
onTabItemTap() {
  let self = this
  if (wx.getStorageSync('auth')==0) {
    wx.showModal({
      title: '提示',
      content: '账号尚未实名认证,请实名认证',
      success: res => {
        if (res.confirm) {
          wx.navigateTo({
            url: '/pages/authenForm/authcommit'
          })
        } else if (res.cancel) {
          wx.reLaunch({
            url: '/pages/homepage/home'
          })
        }
      }
    })
  }
}

});