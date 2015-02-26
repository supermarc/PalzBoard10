package SlidingTabs;

import android.view.View;

/**
 * Created by mnaegler on 26.02.2015.
 */
public class SlidingTabItem {
    public int layoutRes;
    public String titel;
    public InstantiateListener listener;

    public SlidingTabItem() {

    }

    public SlidingTabItem(int layoutRes, String title, InstantiateListener listener) {
        this.layoutRes = layoutRes;
        this.titel = titel;
        this.listener = listener;
    }

    public static interface InstantiateListener {
        public void afterInstantiate(View view);
    }
}
