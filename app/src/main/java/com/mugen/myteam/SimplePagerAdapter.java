package com.mugen.myteam;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by root on 16/12/14.
 */
public class SimplePagerAdapter extends PagerAdapter {
    Fragment f;
    private int[] imageResId={R.drawable.ic_menu_white_36dp,R.drawable.ic_action_person,R.mipmap.ic_launcher};
    String token;
    public SimplePagerAdapter(Fragment f){
        this.f=f;
    }
        /**
     * @return the number of pages to display
     */
    @Override
    public int getCount() {
        return imageResId.length-1;
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
        Drawable image = f.getResources().getDrawable(imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth()*3/2, image.getIntrinsicHeight()*3/2);
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BASELINE);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return sb;
    }
    // END_INCLUDE (pageradapter_getpagetitle)

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // Inflate a new layout from our resources
        PageBuilder factory = PageBuilder.getFactory();
        View view=factory.getPage(f,container,position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);
        f.onDestroy();
    }/*
    private void loadFragment(Fragment fragment) {
        FragmentManager fManager = f.getActivity().getSupportFragmentManager();
        FragmentTransaction transaction;
        try {
            transaction = fManager.beginTransaction();
            transaction.replace(R.id.container, fragment);
            transaction.commit();
        }catch(IllegalStateException e){
            Log.e("error",e.getMessage());
        }
    }
*/


}
