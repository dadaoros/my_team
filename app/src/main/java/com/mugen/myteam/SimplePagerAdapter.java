package com.mugen.myteam;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by root on 16/12/14.
 */
public class SimplePagerAdapter extends PagerAdapter {
    Fragment f;
    public static final String[] TABS={"NOTICIAS","MI EQUIPO","POSICIONES","CALENDARIO"};
    public SimplePagerAdapter(Fragment f){
        this.f=f;
    }
        /**
     * @return the number of pages to display
     */
    @Override
    public int getCount() {
        return TABS.length;
    }

    /**
     * @return true if the value returned from {@link #instantiateItem(ViewGroup, int)} is the
     * same object as the {@link View} added to the {@link android.support.v4.view.ViewPager}.
     */
    @Override
    public boolean isViewFromObject(View view, Object o) {
        return o == view;
    }

    // BEGIN_INCLUDE (pageradapter_getpagetitle)

    @Override
    public CharSequence getPageTitle(int position) {

        return TABS[position];
    }
    // END_INCLUDE (pageradapter_getpagetitle)

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // Inflate a new layout from our resources
        PageBuilder builder = PageBuilder.getBuilder();
        View view=builder.getPage(f,container,position);



        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);
        f.onDestroy();
    }


}
