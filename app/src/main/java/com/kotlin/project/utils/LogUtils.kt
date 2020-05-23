
import android.util.Log
import com.kotlin.project.BuildConfig
import java.lang.Exception

object LogUtils {

    var TAG = "LogUtil"

    fun showLog(tag: String = TAG, msg: String) {
        if(BuildConfig.DEBUG) {
            Log.d(tag, msg)
        }
    }

    fun showLog(msg: String) {
        if(BuildConfig.DEBUG) {
            Log.d(TAG, msg)
        }
    }

    fun showError(tag: String = TAG, e: Exception) {
        if(BuildConfig.DEBUG) {
            Log.e(tag, e.message)
        }
    }

    fun showError(e: Exception) {
        if(BuildConfig.DEBUG) {
            if (!e.message.isNullOrEmpty()) {
                Log.e(TAG, e.message)
            }
        }
    }

    fun showError(msg: String) {
        if(BuildConfig.DEBUG) {
            if (!msg.isNullOrEmpty()) {
                Log.e(TAG, msg)
            }
        }
    }
}