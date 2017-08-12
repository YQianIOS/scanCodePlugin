# scanCodePlugin

ionic3 二维码扫描插件,安卓及iOS平台皆可用.

```ts

     cordova.plugins.ScanCodePlugin.scan("扫描二维码", function (msg) {
            if (!msg) {
                return
            }
            if (that.homeModel.mobile === msg) {
                that.alert.showAlert('不能添加自己为好友');
                return;
            }

            that.searchWorker(msg);
        }, function (msg) {
            that.alert.showAlert("扫描二维码失败");
        });


```