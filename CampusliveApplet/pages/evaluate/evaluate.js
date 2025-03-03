// miniprogram/pages/evaluate/evaluate.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    noteMaxLen: 300, // 最多放多少字
    info: "",
    noteNowLen: 0,//备注当前字数
    myorderID:'',
    imgs: [{
            id: 1    
          }, {                 
            id: 2   
          }, {                 
            id: 3    
          }, {      
            id: 4    
          }, {      
            id: 5    
          }],    
          starId: 5,    
          src1: '/images/user/xing2.png',    
          src2: '/images/user/xingbiao.png',

  },
  select:function(e) {
   console.log(e)
   this.data.starId = e.currentTarget.dataset.index;
   this.setData({
     starId: this.data.starId
    })
},

  bindTextAreaChange: function (e) {
    var that = this
    var value = e.detail.value,
      len = parseInt(value.length);
    if (len > that.data.noteMaxLen)
      return;
    that.setData({ info: value, noteNowLen: len })

  },
  // 提交清空当前值
  bindSubmit: function () {
    var that = this;
    console.log(that.data.myorderID); 
    wx.showToast({
      title: '发布成功',
      icon: 'success',
      duration: 1500,
      mask: false,
      success: function () {
        wx.request({
          url: 'http://littleeyes.cn:8080/add-order-remark', 
          data: {
              data:{      
                "orderID": that.data.myorderID,
                 "orderRemarkContent": that.data.info,
                 "orderScore": that.data.starId,
              },
              check:0
          },
          header: {
            "content-Type": "application/json" 
          },
          method: "POST",
          success: function (res) { 
            console.info(res.data);
          } 
        })


        wx.request({
          url: 'http://littleeyes.cn:8080/change-order-state', 
          data: {
              data:{
                "orderID": that.data.myorderID,
                "orderState":"6",
              },
              check:0
          },
          header: {
            "content-Type": "application/json" 
          },
          method: "POST",
          success: function (res) { 
            console.info(res.data);
          } 
        })
        that.setData({ info: '', noteNowLen: 0, flag: 0 })
      }
    })

  },
 
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      myorderID:options.myorderID
    })
    console.log(this.data.myorderID);   
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