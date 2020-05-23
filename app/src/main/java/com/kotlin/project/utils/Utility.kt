package com.kotlin.project.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.kotlin.project.R
import org.jetbrains.anko.dip
import java.util.regex.Pattern

object Utility {

    fun call(context: Context, number: String) {
        val intent = Intent()
        intent.action = Intent.ACTION_DIAL
        intent.data = Uri.parse("tel:$number")
        context.startActivity(intent)
    }

    fun sms(context: Context, number: String) {
        val intent = Intent()
        intent.action = Intent.ACTION_SENDTO
        intent.data = Uri.parse("smsto:$number")
        context.startActivity(intent)
    }

    fun googleMap(context: Context, url: String) {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse(url)
        context.startActivity(intent)
    }

    fun setLayoutParams(view: View, width: Int, height: Int) {
        val lp = view.layoutParams
        lp.width = width
        lp.height = height
        view.layoutParams = lp
    }

    fun getColorFromRes(activity: Activity, resId: Int): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getColor(resId)
        } else {
            activity.resources.getColor(resId)
        }
    }

    fun setRLMarginTop(view: View, top: Int) {
        var lp = view.layoutParams as RelativeLayout.LayoutParams
        lp.topMargin = top
        view.layoutParams = lp
    }

    fun setLLMarginH(view: View, left: Int, right: Int) {
        var lp = view.layoutParams as LinearLayout.LayoutParams
        lp.leftMargin = left
        lp.rightMargin = right
        view.layoutParams = lp
    }

    fun setLLMarginV(view: View, top: Int, bottom: Int) {
        var lp = view.layoutParams as LinearLayout.LayoutParams
        lp.topMargin = top
        lp.bottomMargin = bottom
        view.layoutParams = lp
    }

    fun setLLMarginRight(view: View, right: Int) {
        var lp = view.layoutParams as LinearLayout.LayoutParams
        lp.rightMargin = right
        view.layoutParams = lp
    }

    fun setHtmlText(tv: TextView, content: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv.text = Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY)
        } else {
            tv.text = Html.fromHtml(content)
        }
    }

    /**
     * <figure></figure>(.*?)>|
     * <a></a>(.*?)>|
     * <pre></pre>(.*?)>|
     *
     * @param rgexholder
     * @return
     */
    fun subHtmlFormat(htmlString: String, vararg rgexholder: String): String {
        var contentStr = htmlString

        if (rgexholder.isNotEmpty() && rgexholder[0].isNullOrEmpty()) {
            for (i in rgexholder.indices) {
                val rgexE = "<" + rgexholder[i] + "(.*?)>" + "|" + "</" + rgexholder[i] + "(.*?)>"
                val p = Pattern.compile(rgexE)
                val m = p.matcher(contentStr)
                contentStr = m.replaceAll("")
            }
        }
        return contentStr
    }

}