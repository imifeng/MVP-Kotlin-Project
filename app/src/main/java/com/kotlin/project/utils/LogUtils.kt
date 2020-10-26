import android.util.Log
import com.kotlin.project.BuildConfig
import java.lang.Exception

object LogUtils {

    var TAG = "LogUtil"

    fun d(tag: String = TAG, msg: String) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg)
        }
    }

    fun d(msg: String) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, msg)
        }
    }

    fun e(tag: String = TAG, e: Exception) {
        if (BuildConfig.DEBUG) {
            e.message?.let {
                Log.e(tag, it)
            }
        }
    }

    fun e(e: Exception) {
        if (BuildConfig.DEBUG) {
            e.message?.let {
                Log.e(TAG, it)
            }
        }
    }

    fun e(msg: String) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, msg)
        }
    }
}