package com.dawil.testapp;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.getkeepsafe.taptargetview.TapTargetView;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class OnBoardingFragment extends Fragment {
    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private Button backBtn,nextBtn,skipBtn;
    private TextView[] textViews;
    private ViewPagerAdapter viewPagerAdapter;
    private ImageView ellipse1,ellipse2;
    private NavController navController;
    public OnBoardingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_on_boarding, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        backBtn = view.findViewById(R.id.btnBack);
        skipBtn = view.findViewById(R.id.btnSkip);
        nextBtn = view.findViewById(R.id.btnNext);
        ellipse1 = view.findViewById(R.id.ellipse1);
        ellipse2 = view.findViewById(R.id.ellipse2);
        navController = Navigation.findNavController(view);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getItem(0) > 0){
                    viewPager.setCurrentItem(getItem(-1),true);

                }
            }
        });

        new TapTargetSequence(getActivity()).targets(
                TapTarget.forView(nextBtn,"Suivant","Cliquer pour avancer")
                        .outerCircleColor(R.color.teal_200)
                        .outerCircleAlpha(0.2f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(20)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(15)
                        .descriptionTextColor(R.color.black)
                        .textColor(R.color.black)
                        .textTypeface(Typeface.MONOSPACE)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(false)
                        .tintTarget(true)
                        .transparentTarget(true)
                        .targetRadius(40),
                TapTarget.forView(skipBtn,"Passer","Cliquer pour passer")
                        .outerCircleColor(R.color.teal_200)
                        .outerCircleAlpha(0.2f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(20)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(15)
                        .descriptionTextColor(R.color.black)
                        .textColor(R.color.black)
                        .textTypeface(Typeface.MONOSPACE)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(false)
                        .tintTarget(true)
                        .transparentTarget(true)
                        .targetRadius(40),
                TapTarget.forView(backBtn,"Précédent","Cliquer pour revenir en arrière")
                        .outerCircleColor(R.color.blue)
                        .outerCircleAlpha(0.26f)
                        .targetCircleColor(R.color.teal_200)
                        .titleTextSize(20)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(15)
                        .descriptionTextColor(R.color.black)
                        .textColor(R.color.black)
                        .textTypeface(Typeface.MONOSPACE)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(false)
                        .tintTarget(true)
                        .transparentTarget(true)
                        .targetRadius(40)

        ).listener(new TapTargetSequence.Listener() {
            @Override
            public void onSequenceFinish() {

            }

            @Override
            public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {

            }

            @Override
            public void onSequenceCanceled(TapTarget lastTarget) {

            }
        }).start();

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_onBoarding_to_loginFragment);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getItem(0) < 3){
                    viewPager.setCurrentItem(getItem(1),true);
                }else{
                    navController.navigate(R.id.action_onBoarding_to_loginFragment);
                }
            }
        });

        viewPager = view.findViewById(R.id.slideViewPager);
        linearLayout = view.findViewById(R.id.indicator_layout);

         viewPagerAdapter = new ViewPagerAdapter(getContext());

         viewPager.setAdapter(viewPagerAdapter);

         setUpIndicator(0);
         viewPager.addOnPageChangeListener(viewListener);

    }

    @SuppressLint("NewApi")
    public void setUpIndicator(int position){
        textViews = new TextView[4];
        linearLayout.removeAllViews();

        for (int i = 0; i < textViews.length; i++) {
            textViews[i] = new TextView(getContext());
            textViews[i].setText(Html.fromHtml("&#8226"));
            textViews[i].setTextSize(35);
            textViews[i].setTextColor(getResources().getColor(R.color.inactive, Objects.requireNonNull(getContext()).getTheme()));
            linearLayout.addView(textViews[i]);

        }

        textViews[position].setTextColor(getResources().getColor(R.color.active,Objects.requireNonNull(getContext()).getTheme()));
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setUpIndicator(position);

            if (position > 0){
                backBtn.setVisibility(View.VISIBLE);
                ellipse1.setVisibility(View.VISIBLE);
            }else {
                backBtn.setVisibility(View.INVISIBLE);
                ellipse1.setVisibility(View.INVISIBLE);
            }

            if (position == 3){
                nextBtn.setText("Finish");
            }else{
                nextBtn.setText(getResources().getString(R.string.next));
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getItem(int i){
        return viewPager.getCurrentItem() + i;
    }
}