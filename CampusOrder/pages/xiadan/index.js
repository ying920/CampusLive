

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
    tabs: ["取送件", "代排队","代办事" ],
    activeIndex: 0,
    sliderOffset: 0,
    sliderLeft: 0,
    dizhi1:'',
    information1:'从哪里出发',
    information11:'排队地点',
    dizhi2:'收货地址',
    information2:'送到哪里去',
    information22:'要办理的事情所在地点',
    thingType:'',
    weight:'请选择要配送的物品类型，重量',
    thingType2:'',
    thingType3:'',
    typeforthing2:'请选择要代排队事情的类型',
    typeforthing3:'请选择要代办事情的类型',
  
    latitude:'',
    longitude:'',
    localCity:'',
    realAddress:{},

    // markers: [{

    //   id: 0,
    //   iconPath: '../../icons/location.png',
    //   latitude:28.727340,
    //   longitude:115.816720,
    //   width: 25,
    //   height: 25,
    //   callout: {
    //     content: '最快5分钟内接单',
    //     fontSize: 16,
    //     color: '#ffffff',
    //     bgColor: '#319ED3',
    //     padding: 8,
    //     borderRadius:15,
    //     boxShadow: '4px 8px 16px 0 rgba(0)',
    //     display:'ALWAYS'
    //   },
      
    // }],

    // controls: [{
    //   id: 1,
    //   iconPath: '../../icons/refresh.png',
    //   position: {
    //     left: 30,
    //     width: 330,
    //     height: 330
    //   }
    // }],

   
  
  },
  async gotopay(){

    const {dizhi1,dizhi2,thingType}=this.data;
    //判断是否填了出发地址
    if(!dizhi1){
      await showToast({title:"您还没选择出发地址"});
      return ;
    }
    //判断用户是否选择送达地址
    if(!dizhi2.cityName){
      await showToast({title:"您还没选择送达地址"});
      return ;
    }
    //判断是否选择商品类型
    if(!thingType){
      await showToast({title:"您还没选择商品类型及重量"});
      return ;
    }
    //否则跳转到支付页面
    wx.navigateTo({
      url: '../pay/index',
    });


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

  tabClick: function (e) {
    this.setData({
      sliderOffset: e.currentTarget.offsetLeft,
      activeIndex: e.currentTarget.id
    });
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
//   regionchange:function(e){
//     this.mapCtx.getCenterLocation({
//       success: function (res) {
//         lat = res.latitude,
//         lng = res.longitude
        
//       }
//     })
//     this.mapCtx.translateMarker({
//       markerId: 0,
     
//       duration: 500,
//       destination: {
//         latitude: lat,
//         longitude:lng,
//       }
//     })
   

//   },
// refresh:function(){
//   var that = this
//   this.mapCtx.moveToLocation()
// wx.getStorage({
//   key: 'location',
//   success: function(res) {
    
//   that.setData({
//     latitude:res.data.latitude,
//     longitude:res.data.longitude
//   })
//   },
// }),
// lat = that.data.latitude;
// lng = that.data.longitude
//   this.mapCtx.translateMarker({
//     markerId: 0,

//     duration: 500,
//     destination: {
//       latitude: lat,
//       longitude: lng
//     }
   
//   })
//  console.log(that.data.latitude)
// }

});