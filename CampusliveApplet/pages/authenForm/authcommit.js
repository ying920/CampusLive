var app = require('../../utils/util.js');
Page({
  /**
   * 页面的初始数据
   */
  data: {
    name:'',//姓名
    school:'',//学校
    stuid:'',//学号
    idnum:'',//身份证号
    phone:'',//手机号
    code:'',//验证码
    iscode:null,//用于存放验证码接口里获取到的code
    codename:'获取验证码',
    disabled:true
  },

  formReset: function () {
    console.log('form发生了reset事件')
  },
  //获取input输入框的值
  //获取姓名
  getNameValue:function(e){
    this.setData({
      name:e.detail.value
    })
  },
  //获取学校
  getSchValue:function(e){
    this.setData({
      school:e.detail.value
    })
  },
  //获取学号
  getSIDValue:function(e){
    this.setData({
      stuid:e.detail.value
    })
  },
  //获取身份证号
  getIDValue:function(e){
    this.setData({
      idnum:e.detail.value
    })
  },
  //获取手机号
  getPhoneValue:function(e){
    this.setData({
      phone:e.detail.value
    })
  },
  //获取验证码
  getCodeValue: function (e) {
    this.setData({
      code: e.detail.value
    })
  },
  getCode:function(){
    var a = this.data.phone;
    var _this = this;
    var myreg = /^(14[0-9]|13[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$$/;
    //手机号为空
    if (this.data.phone == "") {
      wx.showToast({
        title: '手机号不能为空',
        icon: 'none',
        duration: 1000
      })
      return false;
    } else if (!myreg.test(this.data.phone)) {//手机号格式不正确
      wx.showToast({
        title: '请输入正确的手机号',
        icon: 'none',
        duration: 1000
      })
      return false;
    }else{//手机号格式正确
      wx.request({
        data: {},
        'url': 接口地址,
        success(res) {
          console.log(res.data.data)
          _this.setData({
            iscode: res.data.data
          })
          var num = 61;//倒计时61秒
          var timer = setInterval(function () {
            num--;
            if (num <= 0) {//倒计时结束
              clearInterval(timer);
              _this.setData({
                codename: '重新发送',
                disabled: false
              })
 
            } else {//显示倒计时
              _this.setData({
                codename: num + "s"
              })
            }
          }, 1000)
        }
      })
      
    }
    
    
  },
  //获取验证码
  getVerificationCode() {
    this.getCode();
    var _this = this
    _this.setData({
      disabled: true
    })
  },
  //提交表单信息
  formSubmit:function(e){
    console.log('form发生了submit事件，携带数据为：', e.detail.value)
    var myreg = /^(14[0-9]|13[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$$/;
    if(this.data.name == ""){//姓名为空
      wx.showToast({
        title: '姓名不能为空',
        icon: 'none',
        duration: 1000
      })
      return false;
    }
    if(this.data.phone == ""){//手机号为空
      wx.showToast({
        title: '手机号不能为空',
        icon: 'none',
        duration: 1000
      })
      return false;
    }else if(!myreg.test(this.data.phone)){//手机号格式不正确
      wx.showToast({
        title: '请输入正确的手机号',
        icon: 'none',
        duration: 1000
      })
      return false;
    }
    if(this.data.code == ""){//验证码为空
      wx.showToast({
        title: '验证码不能为空',
        icon: 'none',
        duration: 1000
      })
      return false;
    }else if(this.data.code != this.data.iscode){//验证码错误
      wx.showToast({
        title: '验证码错误',
        icon: 'none',
        duration: 1000
      })
      return false;
    }else{
      wx.setStorageSync('name', this.data.name);
      wx.setStorageSync('phone', this.data.phone);
      wx.redirectTo({
        url: '../add/add',
      })
    }
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