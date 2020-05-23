package com.kotlin.project.utils

import java.text.DecimalFormat
import java.text.NumberFormat

/**
 * @author Finn
 * @date 2020
 */
object FormatUtils {
    fun getInt(any: Any?): Int {
        try {
            return if (any == null) {
                0
            } else if (any is String) {
                any.toInt()
            } else if (any is Boolean) {
                if (any) {
                    1
                } else {
                    0
                }
            } else if (any is Int) {
                any
            } else if (any is Double) {
                any as Int
            } else if (any is Float) {
                any as Int
            } else {
                0
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return 0
    }

    fun getFloat(any: Any?): Float {
        try {
            return if (any == null) {
                0f
            } else if (any is String) {
                any.toFloat()
            } else if (any is Boolean) {
                if (any) {
                    1f
                } else {
                    0f
                }
            } else if (any is Int) {
                any as Float
            } else if (any is Double) {
                any as Float
            } else if (any is Float) {
                any
            } else {
                0f
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return 0f
    }

    fun getLong(any: Any?): Long {
        try {
            return if (any == null) {
                0L
            } else if (any is String) {
                any.toLong()
            } else if (any is Boolean) {
                if (any) {
                    1L
                } else {
                    0L
                }
            } else if (any is Int) {
                any as Long
            } else if (any is Double) {
                any as Long
            } else if (any is Float) {
                any as Long
            } else {
                0L
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return 0L
    }

    fun getOnePointsStr(value: Float): String {
        val numberFormat = DecimalFormat("0.0")
        return numberFormat.format(value.toDouble())
    }

    fun getOnePointsStr(value: Double): String {
        val numberFormat = DecimalFormat("0.0")
        return numberFormat.format(value)
    }

    fun getThreePointsStr(value: Float): String {
        val numberFormat = DecimalFormat("0.000")
        return numberFormat.format(value.toDouble())
    }

    fun getPointNoZeroStr(value: Float): String {
        val numberFormat = NumberFormat.getInstance()
        return numberFormat.format(value.toDouble())
    }
}