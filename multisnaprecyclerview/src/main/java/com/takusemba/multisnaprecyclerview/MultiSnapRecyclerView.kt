package com.takusemba.multisnaprecyclerview

import android.content.Context
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.View
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs
import kotlin.math.max

/**
 * MultiSnapRecyclerView Note that only LinearLayoutManger is supported, and reverse layout is not
 * supported.
 */
class MultiSnapRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

  private var multiSnapHelper: MultiSnapHelper? = null

  init {
    val a = getContext().obtainStyledAttributes(attrs, R.styleable.MultiSnapRecyclerView)
    val gravity = SnapGravity.valueOf(a.getInt(R.styleable.MultiSnapRecyclerView_msrv_gravity, 0))
    val snapCount = a.getInteger(R.styleable.MultiSnapRecyclerView_msrv_snap_count, 1)
    val millisecondsPerInch = a.getFloat(R.styleable.MultiSnapRecyclerView_msrv_ms_per_inch, 100f)
    a.recycle()
    multiSnapHelper = MultiSnapHelper(context, gravity, snapCount, millisecondsPerInch)
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    onFlingListener = null
    multiSnapHelper?.attachToRecyclerView(this@MultiSnapRecyclerView)
  }

  /**
   * sets Snap Listener to RecyclerView
   *
   * @param listener OnSnapListener of MultiSnapRecyclerView
   */
  fun setOnSnapListener(listener: OnSnapListener) {
    multiSnapHelper?.setListener(listener)
  }
}