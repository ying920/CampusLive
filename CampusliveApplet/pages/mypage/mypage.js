// pages/mypage/mypage.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    menuitems: [ 
      { text: '我的钱包', url: '../wallet/wallet', icon: '/images/user/qianbao.png', tips: '', arrows: '/images/user/arrows.png' },
      { text: '我的订单', url: '../myorder/myorder', icon: '/images/user/dingdan.png', tips: '', arrows: '/images/user/arrows.png' },
      { text: '我的评分', url: '../credit/credit', icon: '/images/user/xing.png', tips: '', arrows: '/images/user/arrows.png' },
      { text: '我的地址', url: '../myaddress/myaddress', icon: '/images/user/dizhi.png', tips: '', arrows: '/images/user/arrows.png' },
      { text: '实名认证', url: '../authenForm/authcommit', icon: '/images/user/shimingrenzheng.png', tips: '', arrows: '/images/user/arrows.png' }
    ]
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

  }
})