package com.dawil.testapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import org.jetbrains.annotations.NotNull;

public class ViewPagerAdapter extends PagerAdapter {
    Context context;

    int images[] = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4};
    int headers[] = {R.string.heading_one,R.string.heading_two,R.string.heading_three,R.string.heading_fourth};
    int descs[] = {R.string.desc_one,R.string.desc_two,R.string.desc_three,R.string.desc_fourth};

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return headers.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @NotNull
    @Override
    public Object instantiateItem(@NonNull @NotNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout,container,false);

        ImageView slideImage = view.findViewById(R.id.image);
        TextView slideHeader = view.findViewById(R.id.header);
        TextView slideDesc = view.findViewById(R.id.description);

        slideImage.setImageResource(images[position]);
        slideHeader.setText(headers[position]);
        slideDesc.setText(descs[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
