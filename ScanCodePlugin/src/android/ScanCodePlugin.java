package cordova.plugin.ScanCodePlugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.yle.borcodescan.app.CaptureActivity;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

/**
 * This class echoes a string called from JavaScript.
 */
public class ScanCodePlugin extends CordovaPlugin {
private CallbackContext callbackContext;
    private static final int REQUEST_QRCODE = 0x01;

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        if (action.equals("scan")) {
            Intent intent = new Intent(cordova.getActivity(), CaptureActivity.class);
            cordova.startActivityForResult(this, intent, REQUEST_QRCODE);
            return true;
        }
        return false;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_QRCODE:
                if (resultCode == Activity.RESULT_OK) {
                    //扫描后的业务逻辑
                    String code = data.getStringExtra("SCAN_RESULT");
                    if (code.contains("http") || code.contains("https")) {
                        //打开链接
                        /*Intent intent = new Intent(this, AdBrowserActivity.class);
                        intent.putExtra(AdBrowserActivity.KEY_URL, code);
                        startActivity(intent);*/
                        // Toast.makeText(cordova.getActivity(), code, Toast.LENGTH_SHORT).show();
                        callbackContext.success(code);

                    } else {
                        // Toast.makeText(cordova.getActivity(), code, Toast.LENGTH_SHORT).show();
                        callbackContext.success(code);
                    }
                }else if(resultCode == 300){
                    //从本地相册扫描后的业务逻辑
                    String code = data.getStringExtra("LOCAL_PHOTO_RESULT");
                    // Toast.makeText(cordova.getActivity(), code, Toast.LENGTH_SHORT).show();
                    callbackContext.success(code);
                }
                break;
        }
    }

}
