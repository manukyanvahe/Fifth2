package am.fifth.android.fifth.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;

import am.fifth.android.fifth.Fragments.Profile;
import am.fifth.android.fifth.Fragments.Threads;
import am.fifth.android.fifth.R;

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "MAIN PAGER ADAPTER";
    Context context;

    public static final int TAB_THREADS = 0;
    public static final int TAB_PROFILES = 1;

    private int[] imageResId = {
            R.drawable.ic_conversation,
            R.drawable.ic_partner
    };

    public MainPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case TAB_THREADS:
                return new Threads();
            case TAB_PROFILES:
                return new Profile();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable image = context.getResources().getDrawable(imageResId[position]);
        image.setBounds(0, 0, 60, 60);
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }
}
