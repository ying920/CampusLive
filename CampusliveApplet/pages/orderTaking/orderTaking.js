// pages/orderTaking/orderTaking.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    
  },

  oneBtnClk:function(e){
    const arg = e.currentTarget.dataset.arg;
    wx.setStorage({
      key: "orderID",
      data: arg
    })
		wx.navigateTo({
      url: '/pages/orderDetail/orderDetail',
    })
  },
  
  change:function(){
    var orderID = wx.getStorageSync('orderID');
    var Util = require( '../../utils/util.js' );
    wx.request({
      'url': 'http://littleeyes.cn:8080/change-order-state/',
      method: "POST",
      header: {
         "content-type": "application/json" 
      },
      data: {
        data:{
          orderID:1000000001,
          orderState:"1"
        },
        check:0
      },
      
      success:function(res) {
        console.log(res.data.data)
        
        wx.showToast({
          title: '修改订单状态成功！',
          icon: 'success',
          duration: 2000
        })
      }
    })
	},

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
    // var items = wx.getStorageSync("items")//将items首先要获取到
    var self = this
    wx.request({
      
      method: 'GET',
      'url': 'http://littleeyes.cn:8080/get-all-missed-order',
      success(res) {
        console.log(res.data.data),
        self.setData({
              list: res.data.data,
            })
        
      }
    })
      
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})