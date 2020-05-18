// miniprogram/pages/order/order.js
Page({ 
    /**
   * 页面的初始数据
   */
  data: {
    currtab: 0,
    swipertab: [{ name: '已完成', index: 0 }, { name: '待付款', index: 1 },{ name: '待接单', index: 2 },{ name: '已接单', index: 3 },{ name: '待收货', index: 4 }, { name: '已取消', index: 5 }],

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var _this = this
    wx.request({
      url: 'http://littleeyes.cn:8080/get-all-my-order/19990523',
      headers: {
        'Content-Type': 'application/json'
      },
      method: 'GET',
      success: function (res) {
        console.log(res.data.msg)
        _this.setData({
          // alreadyOrder:res.data.data.FinishedOrder,
          // waitPayOrder:res.data.data.PaidOrder,
          // waittakeOrder:res.data.data.MissedOrder,
          // waitreceiveOrder:res.data.data.CanceledOrder,
          // lostOrder:res.data.data.CanceledOrder

          alreadyOrder:res.data.data.MissedOrder,
          waitPayOrder:res.data.data.MissedOrder,
          waittakeOrder:res.data.data.MissedOrder,
          waitreceiveOrder:res.data.data.MissedOrder,
          lostOrder:res.data.data.MissedOrder

        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */

  onReady: function () {
    // 页面渲染完成
    this.getDeviceInfo()
    this.orderShow()
  },

  getDeviceInfo: function () {
    let that = this
    wx.getSystemInfo({
      success: function (res) {
        that.setData({
          deviceW: res.windowWidth,
          deviceH: res.windowHeight
        })
      }
    })
  },

  /**
  * @Explain：选项卡点击切换
  */

  tabSwitch: function (e) {
    var that = this
    if (this.data.currtab === e.target.dataset.current) {
      return false
    } else {
      that.setData({
        currtab: e.target.dataset.current
      })
    }
  },

  tabChange: function (e) {
    this.setData({ currtab: e.detail.current })
    this.orderShow()
  },

  orderShow: function () {
    let that = this
    switch (this.data.currtab) {
      case 0:
        that.alreadyShow()
        break

      case 1:
        that.waitPayShow()
        break

      case 2:
        that.waittakeShow()
        break

      case 3:
        that.waitreceiveShow()
        break  

      case 4:
        that.lostShow()
        break

    }

  },

  alreadyShow: function(){

    // this.setData({

    //   alreadyOrder:res.data.data.FinishedOrder

    // })

  },

 

  waitPayShow:function(){

    // this.setData({

    //   waitPayOrder:res.data.data.PaidOrder
    // })

  },

  waittakeShow: function(){
    // this.setData({

    //   waittakeOrder:res.data.data.MissedOrder
    // }) 
  },

  waitreceiveShow: function(){
    // this.setData({

    //   waitreceiveOrder:res.data.data.CanceledOrder
    // }) 
  },

  lostShow: function () {

    // this.setData({

    //   lostOrder: res.data.data.CanceledOrderitems
    // })

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

  evaluate:function(e){
    console.log("success")  
    wx.redirectTo({  
    url: '../evaluate/evaluate' 
    })
    },

  complain:function(e){
    console.log("success")  
    wx.redirectTo({  
    url: '../complain/complain' 
    })
    },

    topay:function(e){
      console.log("success")  
      wx.redirectTo({  
        /**
         * 填写邓智的付款界面
         */
      url: '../evaluate/evaluate' 
      })
      },

  receive:function(e){
    wx.showModal({
      title: '收货',
      content: '确认收货吗？',
      success: function (res) {
        if (res.confirm) {
          console.log('点击确认回调')
        }
        else { 
         console.log('点击取消回调')
        }
      }    
    })
  },

  complete:function(e){
    wx.showModal({
      title: '完成',
      content: '确认完成送货吗？',
      success: function (res) {
        if (res.confirm) {
          console.log('点击确认回调')
        }
        else { 
         console.log('点击取消回调')
        }
      }    
    })
  },

  cancel:function(e){
    wx.showModal({
      title: '取消',
      content: '确认取消订单吗？',
      success: function (res) {
        if (res.confirm) {
          console.log('点击确认回调')
        }
        else { 
         console.log('点击取消回调')
        }
      }    
    })
  }
  
})