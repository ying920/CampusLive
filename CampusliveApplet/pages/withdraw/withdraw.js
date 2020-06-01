// miniprogram/pages/withdraw/withdraw.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    un_click1:'money_box',
    un_click2: 'money_box',
    un_click3: 'money_box',
    un_click4: 'money_box',
    un_click5: 'money_box',
    un_click6: 'money_box'
  },
  lv1:function(){
    this.setData({
      un_click2: 'money_box',
      un_click3: 'money_box',
      un_click4: 'money_box',
      un_click5: 'money_box',
      un_click6: 'money_box',
      un_click1:'money_box1'
    })
},
lv2: function () {
  this.setData({
    un_click2: 'money_box1',
    un_click3: 'money_box',
    un_click4: 'money_box',
    un_click5: 'money_box',
    un_click6: 'money_box',
    un_click1: 'money_box'
  })
},
lv3: function () {
  this.setData({
    un_click2: 'money_box',
    un_click3: 'money_box1',
    un_click4: 'money_box',
    un_click5: 'money_box',
    un_click6: 'money_box',
    un_click1: 'money_box'
  })
},
lv4: function () {
  this.setData({
    un_click2: 'money_box',
    un_click3: 'money_box',
    un_click4: 'money_box1',
    un_click5: 'money_box',
    un_click6: 'money_box',
    un_click1: 'money_box'
  })
},
lv5: function () {
  this.setData({
    un_click2: 'money_box',
    un_click3: 'money_box',
    un_click4: 'money_box',
    un_click5: 'money_box1',
    un_click6: 'money_box',
    un_click1: 'money_box'
  })
},
lv6: function () {
  this.setData({
    un_click2: 'money_box',
    un_click3: 'money_box',
    un_click4: 'money_box',
    un_click5: 'money_box',
    un_click6: 'money_box1',
    un_click1: 'money_box'
  })
},

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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

  },
  submit:function(e){
    wx.showToast({
      title: '提取成功',
      icon: '',     //默认值是success,就算没有icon这个值，就算有其他值最终也显示success
      duration: 2000,      //停留时间
    })
    wx.redirectTo({
      url: '../wallet/wallet' 
      })
  }
})