// pages/index/register.js
Page({

  // 返回上一页面
  toBack: function(){
    wx.navigateBack({
      delta:1
    })
  },
  // 注册
  formSubmit: function(e){
    // console.log(e);
    // 发起请求
    wx.request({
      // 请求地址
      url: 'http://localhost:8090/xcx/user/reg',
      // 请求参数
      data: e.detail.value,
      // 请求方式
      method:"POST",
      // 请求头
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      // 请求成功回调
      success:function(res){
        // console.log(res);
        wx.redirectTo({
          url: res.data,
        })
      }
    })
  }
})