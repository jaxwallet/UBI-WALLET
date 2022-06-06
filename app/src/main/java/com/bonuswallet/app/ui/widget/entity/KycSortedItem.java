package com.bonuswallet.app.ui.widget.entity;

import com.bonuswallet.app.ui.widget.holder.KycHolder;

public class KycSortedItem extends SortedItem<WarningData> {

    public KycSortedItem(WarningData value, int weight) {
        super(KycHolder.VIEW_TYPE, value, weight);
    }

    @Override
    public int compare(SortedItem other) {
        return weight - other.weight;
    }

    @Override
    public boolean areContentsTheSame(SortedItem newItem)
    {
        return false;
    }

    @Override
    public boolean areItemsTheSame(SortedItem other)
    {
        return other.viewType == viewType;
    }
}
