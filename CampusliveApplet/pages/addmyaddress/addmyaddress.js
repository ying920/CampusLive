// miniprogram/pages/addmyaddress/addmyaddress.js
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
    console.log(e.detail.value.newaddress)
   
   wx.request({
      url: 'http://littleeyes.cn:8080/add-address', 
      header: {
        "content-Type": "application/json" 
      },
      data: {
          data:{
            "userID":"19990523",
            "userAddress": e.detail.value.newaddress
          },
          check:0
      },

      method: "POST",
      success: function (res) { 
        console.info(res.data);
      } 
    })
      
    wx.showToast({
    title: '添加成功',
    duration:2000,
    })
    wx.redirectTo({    
    url: '../myaddress/myaddress'   
    })
      
}
})