//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    showPayPwdInput: false,  //是否展示密码输入层
    rightpwd:'110150',//正确密码
    pwdVal: '',  //输入的密码
    payFocus: true, //文本框焦点
    money:500.00, //钱包余额
    money1:0,//上界面传来的订单费用
  },
  onLoad: function (option) {
    this.showInputLayer();
    this.setData({
      money1:option.money1,
    });
  },
  /**
   * 显示支付密码输入层
   */
  showInputLayer: function(){
    this.setData({ showPayPwdInput: true, payFocus: true });
  },
  /**
   * 隐藏支付密码输入层
   */
  hidePayLayer: function(){
    /**获取输入的密码**/
    var val = this.data.pwdVal;
	/**在这调用支付接口**/
    this.setData({ 
      showPayPwdInput: false, 
      payFocus: false, 
      pwdVal: '',
      money:money-money1,
    }, 
    function(){
      /**弹框**/
     wx.showToast({
       title: '未完成支付，请支付！',
     })

    });

  },
  /**
   * 获取焦点
   */
  getFocus: function(){
    this.setData({ payFocus: true });
  },
  /**
   * 输入密码监听
   */
  inputPwd: function(e){
      this.setData({
         pwdVal: e.detail.value ,
      });
      if (e.detail.value.length == 6){

        if(e.detail.value==this.data.rightpwd){
          wx.showToast({
            title: '支付成功！',
            icon:'success',
            duration:1600,
            mask:true,
            success:function(){
              setTimeout(function(){
                //this.hidePayLayer();
                wx.switchTab({
                  url: '../xiadan/index',
                });
              },1600)
            }
          });
   
        }else{
          wx.showToast({
            title: '密码错误！请重新输入！',
          })
        }
      }
  }
})
