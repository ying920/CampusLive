/**
 * promise 形式  getSetting
 */
export const getSetting=()=>{
  return new Promise((resolve,reject)=>{
    wx.getSetting({
      success:(res)=>{
        resolve(res);
      },
      fail:(err)=>{
        reject(err);
      }
    });
  })
}

/**
 * promise 形式  chooseAddress
 */
export const chooseAddress=()=>{
  return new Promise((resolve,reject)=>{
    wx.chooseAddress({
      success: (result) => {
        resolve(result);
      },
      fail: (err) => {
        reject(err);
      }
    });
  })
}

/**
 * promise 形式  openSetting
 */
export const openSetting=()=>{
  return new Promise((resolve,reject)=>{
    wx.openSetting({
      success: (result) => {
        resolve(result);
      },
      fail: (err) => {
        reject(err);
      }
    });
  })
}

export const showToast=({title})=>{
  return new Promise((resolve,reject)=>{
    wx.showToast({
      title:title,
      icon:'none',
      success:(res) =>{
        resolve(res);
      },
      fail:(err)=>{
        reject(err);
      }
    })
  })
}