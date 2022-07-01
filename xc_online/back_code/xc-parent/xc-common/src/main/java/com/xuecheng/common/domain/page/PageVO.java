package com.xuecheng.common.domain.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PageVO<T> implements Iterable<T>, Serializable {
    /**
     * 当前页的集合数据
     */
    private List<T> items = new ArrayList<T>();
    /**
     * 总条数
     */
    private long counts;
    /**
     * 当前页
     */
    private long page;
    /**
     * 每页条数
     */
    private long pageSize;

    public PageVO() {
    }


    public PageVO(long total, long page, long pageSize) {
        this.counts = total;
		this.page = page;
		this.pageSize = pageSize;
    }

    public PageVO(List<T> items, long counts, long page, long pageSize) {
        this.items = items;
        this.counts = counts;
        this.page = page;
        this.pageSize = pageSize;
    }

    /**
     * Returns if there is a previous page.
     *
     * @return if there is a previous page.
     */
    public boolean hasPrevious() {
        return getPage() > 0;
    }

    /**
     * Returns if there is a next page.
     *
     * @return if there is a next page.
     */
    public boolean hasNext() {
        return getPage() + 1 < getPages();
    }

    /**
     * Returns whether the current page is the first one.
     *
     * @return whether the current page is the first one.
     */
    public boolean isFirst() {
        return !hasPrevious();
    }

    /**
     * Returns whether the current  page is the last one.
     *
     * @return whether the current  page is the last one.
     */
    boolean isLast() {
        return !hasNext();
    }

    /**
     * Returns the total amount of elements of all pages.
     *
     * @return the total amount of elements of all pages.
     */
    public long getCounts() {
        return counts;
    }

    public void setCounts(long counts) {
        this.counts = counts;
    }

    /**
     * Returns the number of total pages.
     *
     * @return the number of total pages
     */
    public long getPages() {
        return getPageSize() == 0 ? 1 : (int) Math.ceil((double) counts / (double) getPageSize());
    }

    /**
     * Returns the page content as unmodifiable {@link List}.
     *
     * @return Returns the page content as unmodifiable {@link List}
     */
    public List<T> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    /**
     * Returns whether the current page has content.
     *
     * @return whether the current page has content.
     */
    public boolean hasItems() {
        return getItemsSize() > 0;
    }

    /**
     * Returns the number of elements on current page.
     *
     * @return the number of elements on current page.
     */
    public int getItemsSize() {
        return items.size();
    }

    /**
     * Returns the number of items of each page.
     *
     * @return the number of items of each page
     */
    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Returns the number of current page. (Zero-based numbering.)
     *
     * @return the number of current page.
     */
    public long getPage() {
        return page;
    }

    /**
     * Set the number of current page. (Zero-based numbering.)
     */
    public void setPage(int page) {
        this.page = page;
    }

    public Iterator<T> iterator() {
        return getItems().iterator();
    }
}