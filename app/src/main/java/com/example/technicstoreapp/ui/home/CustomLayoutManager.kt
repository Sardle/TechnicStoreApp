package com.example.technicstoreapp.ui.home

import androidx.recyclerview.widget.RecyclerView

class CustomLayoutManager(private val context: HomeFragment) : RecyclerView.LayoutManager() {

    private var attachedRecyclerView: RecyclerView? = null
    private var attachedLayoutManager: CustomLayoutManager? = null
    private var maxScrollOffset = 0
    private var scrollOffset = 0

    fun attachRecyclerView(recyclerView: RecyclerView, layoutManager: CustomLayoutManager) {
        attachedRecyclerView = recyclerView
        attachedLayoutManager = layoutManager
    }

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        if (itemCount == 0) {
            removeAndRecycleAllViews(recycler!!)
            return
        }

        if (childCount == 0 && state!!.isPreLayout.not()) {
            scrollOffset = 0
        }

        val child = recycler!!.getViewForPosition(0)
        addView(child)

        measureChildWithMargins(child, 0, 0)

        val width = getDecoratedMeasuredWidth(child)
        val height = getDecoratedMeasuredHeight(child)

        val left = paddingLeft
        val top = paddingTop + scrollOffset

        layoutDecorated(child, left, top, left + width, top + height)

        maxScrollOffset = (itemCount - 1) * height

        if (attachedRecyclerView != null) {
            attachedRecyclerView!!.setPadding(
                paddingLeft,
                paddingTop + maxScrollOffset,
                paddingRight,
                paddingBottom
            )
            attachedRecyclerView!!.scrollToPosition(itemCount - 1)
        }

    }

    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        if (childCount == 0 || dy == 0) {
            return 0
        }

        var currentScrollOffset = scrollOffset
        currentScrollOffset += dy

        if (currentScrollOffset < 0) {
            attachedLayoutManager?.scrollVerticallyBy(currentScrollOffset, recycler, state)
            scrollOffset = 0
            return -scrollOffset
        } else if (currentScrollOffset > maxScrollOffset - getHeight()) {
            attachedLayoutManager?.scrollVerticallyBy(currentScrollOffset - (maxScrollOffset - getHeight()), recycler, state)
            scrollOffset = maxScrollOffset - getHeight()
            return maxScrollOffset - currentScrollOffset
        } else {
            attachedRecyclerView?.scrollBy(0, dy)
            scrollOffset = currentScrollOffset
            offsetChildrenVertical(-dy)
            return dy
        }
    }

    override fun canScrollVertically(): Boolean {
        return true
    }

    override fun computeVerticalScrollRange(state: RecyclerView.State): Int {
        return super.computeVerticalScrollRange(state)
    }
}
