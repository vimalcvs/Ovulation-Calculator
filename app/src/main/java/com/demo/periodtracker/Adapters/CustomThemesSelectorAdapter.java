package com.demo.periodtracker.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.periodtracker.R;
import com.demo.periodtracker.ThemesFiles.MyCustomTheme;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;


public abstract class CustomThemesSelectorAdapter extends RecyclerView.Adapter<CustomThemesSelectorAdapter.ViewHolder> {
    private final Activity context;
    private int checkedPosition;
    private ArrayList<MyCustomTheme> customThemes;
    private int lastPosition = -1;

    public CustomThemesSelectorAdapter(Activity activity, ArrayList<MyCustomTheme> arrayList, int i) {
        this.context = activity;
        this.customThemes = arrayList;
        this.checkedPosition = i;
    }

    public abstract void onThemeItemSelected(MyCustomTheme myCustomTheme);

    public void setThemes(ArrayList<MyCustomTheme> arrayList) {
        new ArrayList();
        this.customThemes = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.theme_selector_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.bind(this.customThemes.get(i));
        setAnimation(viewHolder.itemView, i);
    }

    @Override
    public int getItemCount() {
        return this.customThemes.size();
    }

    private void setAnimation(View view, int i) {
        if (i > this.lastPosition) {
            view.startAnimation(AnimationUtils.loadAnimation(this.context, R.anim.from_right_slider_animation));
            this.lastPosition = i;
        }
    }

    public MyCustomTheme getSelectedTheme() {
        int i = this.checkedPosition;
        if (i != -1) {
            return this.customThemes.get(i);
        }
        return null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView cardView;
        ImageView introImg;

        public ViewHolder(View view) {
            super(view);
            this.cardView = view.findViewById(R.id.cardView);
            this.introImg = view.findViewById(R.id.introImg);
        }

        void bind(final MyCustomTheme myCustomTheme) {
            if (CustomThemesSelectorAdapter.this.checkedPosition != -1) {
                if (CustomThemesSelectorAdapter.this.checkedPosition == getAdapterPosition()) {
                    this.cardView.setStrokeWidth(5);
                    this.cardView.setStrokeColor(ContextCompat.getColor(CustomThemesSelectorAdapter.this.context, myCustomTheme.getThemeColor()));
                } else {
                    this.cardView.setStrokeWidth(0);
                }
            } else {
                this.cardView.setStrokeWidth(0);
            }
            this.introImg.setImageResource(myCustomTheme.getBgImg());
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CustomThemesSelectorAdapter.ViewHolder.this.m124x72856343(getAdapterPosition(), myCustomTheme, view);
                }
            });
        }


        public void m124x72856343(int i, MyCustomTheme myCustomTheme, View view) {
            this.cardView.setStrokeWidth(i);
            if (CustomThemesSelectorAdapter.this.checkedPosition != getAdapterPosition()) {
                CustomThemesSelectorAdapter customThemesSelectorAdapter = CustomThemesSelectorAdapter.this;
                customThemesSelectorAdapter.notifyItemChanged(customThemesSelectorAdapter.checkedPosition);
                CustomThemesSelectorAdapter.this.checkedPosition = getAdapterPosition();
            }
            CustomThemesSelectorAdapter.this.onThemeItemSelected(myCustomTheme);
        }
    }
}
