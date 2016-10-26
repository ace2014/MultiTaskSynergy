package pzl.com.multitasksynergy;

import android.content.Context;
import android.widget.Toast;

/**
 * @author zl.peng
 * @version [1.0, 2016-10-26]
 */
public class ToastUtil {
    static Toast toast;

    public static void show(String msg, Context context) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        }
        toast.setText(msg);
        toast.show();
    }
}
