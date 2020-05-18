// pages/orderDetail/orderDetail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    orderScore: '',
    orderAddress: '',
    orderTime: '',
    clientID: '',
    orderMoney: '',
    orderID: '',
    orderContent: ''
  },

  confirm:function(){
		wx.navigateTo({
      url: '/pages/MessageDetail/MessageDetail',
    })
	},

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
    var orderID = wx.getStorageSync('orderID');
    var self = this
    wx.request({
      date:{
        orderID:orderID
      },
      'url': 'http://littleeyes.cn:8080/get-order/'+orderID,
      success(res) {
        console.log(res.data.data)
        var data=res.data.data;
        self.setData({
          orderScore: data.orderScore,
          orderAddress: data.orderAddress,
          orderTime: data.orderTime,
          clientID: data.clientID,
          orderMoney: data.orderMoney,
          orderID: data.orderID,
          orderContent: data.orderContent
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