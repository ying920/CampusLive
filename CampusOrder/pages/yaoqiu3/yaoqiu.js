// pages/yaoqiu/yaoqiu.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
  
    grids: [{
      id:0,
        flag:0,
        image: "/icons/paidui.png",
        name: "排队"
      },
      {
        id: 1,
        flag: 0,
        image:'/icons/chongwu3.png',
        name: "照看宠物"
      },

      {
        id: 2,
        flag: 0,
        image:'/icons/lingshi.png',
        name: "买东西"
      }, 
     
      {
        id: 3,
        flag: 0,
        image:'/icons/diannao.png',
        name: "修理电脑"
      }, 
      {
        id: 4,
        flag: 0,
        image: '/icons/fanzhanzuo.png',
        name: "占座"
      }, 
      {
        id: 5,
        flag: 0,
        image:'/icons/zuozuoye.png',
        name: "做作业"
      }, 
      {
        id:6,
        flag: 0,
        image:'/icons/jiaofei.png',
        name: "代缴费"
      },
       {
         id: 7,
         flag: 0,
         image:'/icons/shangke.png',
        name: "代上课"
      }, 
      {
        id: 8,
        flag: 0,
        image:'/icons/other.png',
        name: "其他，详情请联系"
      }
    ],
    weight: '小于1'
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },
  slider: function(e) {
    var that = this;
    if (e.detail.value == 1) {
      that.setData({
        weight: "小于1"
      })
    } else {
      that.setData({
        weight: e.detail.value
      })
    }
  },
  slider2: function(e) {
    var that = this;
    if (e.detail.value == 1) {
      that.setData({
        weight: "小于1"
      })
    } else {
      that.setData({
        weight: e.detail.value
      })
    }
  },

  select:function(event){
    var item = event.currentTarget.dataset.item;
    item.flag = 1;
    var id = parseInt(item.id);
    let grids = this.data.grids;
    grids[id].flag = item.flag;
    this.setData({
      grids:grids
    })
  console.log(this.data.grids);
   
  },
  submit:function(){
    let grids = this.data.grids;
    var detail ;
    for( var i = 0; i < grids.length; i++) {
      if(grids[i].flag == 1) {
        detail = grids[i].name;
      }
    }
    console.log(detail);
  //   wx.reLaunch({

  //    url: '/pages/xiadan/index?thingType3=' + detail + "&weight=" + this.data.weight,
  //  })
  let pages = getCurrentPages();
  let prevPage = pages[ pages.length - 2 ];  

  prevPage.setData({  // 将我们想要传递的参数在这里直接setData。上个页面就会执行这里的操作。
  
    thingType3:detail+', '+this.data.weight+'小时'

  })
  wx.navigateBack({

    delta: 1  // 返回上一级页面。

})


  }
})