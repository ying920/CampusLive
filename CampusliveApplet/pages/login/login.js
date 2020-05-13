// miniprogram/pages/login/login.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

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
  formSubmit:function(e){
    console.log(e.detail.value.account, e.detail.value.pwd)
    if (e.detail.value.account == 'abc' && e.detail.value.pwd == 'abc') {
    wx.showToast({
    title: '登录成功',
    duration:2000,
    })
    wx.redirectTo({
    
    url: '../mypage/mypage'
    
    })
    }
    else{
    wx.showToast({
    title: '用户名或密码错',
    duration: 2000,
    })
    }
    }
})