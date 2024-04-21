package com.demo.periodtracker.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.periodtracker.Activities.ReadBlogActivity;
import com.demo.periodtracker.Model.FeaturedBlog;
import com.demo.periodtracker.R;
import com.demo.periodtracker.Utils.Utils;

import java.util.List;


public class FeaturedBlogAdapter extends RecyclerView.Adapter<FeaturedBlogAdapter.ViewHolder> {
    Activity activity;
    List<FeaturedBlog> blogList;
    private int lastPosition = -1;

    public FeaturedBlogAdapter(List<FeaturedBlog> list, Activity activity) {
        this.blogList = list;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.featured_blog_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        FeaturedBlog featuredBlog = this.blogList.get(i);
        viewHolder.headingTv.setText(featuredBlog.getDetail());
        viewHolder.questionTv.setText(featuredBlog.getHeading());


        viewHolder.imageView.setImageResource(Utils.setImage(featuredBlog.getImgPath()));
        if (!featuredBlog.isDark()) {
            viewHolder.headingTv.setTextColor(this.activity.getResources().getColor(R.color.black));
            viewHolder.opacityView.setVisibility(View.GONE);
        } else {
            viewHolder.headingTv.setTextColor(this.activity.getResources().getColor(R.color.white));
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FeaturedBlogAdapter.this.m127x1667552c(i, view);
            }
        });
    }


    public void m127x1667552c(int i, View view) {
        Intent intent = new Intent(this.activity, ReadBlogActivity.class);
        intent.putExtra("heading", this.blogList.get(i).getHeading());
        intent.putExtra("imgRes", this.blogList.get(i).getImgPath());
        intent.putExtra("body", this.blogList.get(i).getBody());
        intent.putExtra("title", this.blogList.get(i).getDetail());
        intent.putExtra("color", this.blogList.get(i).getColor());
        intent.putExtra("dark", this.blogList.get(i).isDark());
        this.activity.startActivity(intent);
        Activity activity = this.activity;
        if (activity instanceof ReadBlogActivity) {
            activity.finish();
        }
    }

    private void setAnimation(View view, int i) {
        if (i > this.lastPosition) {
            view.startAnimation(AnimationUtils.loadAnimation(this.activity, R.anim.from_right_slider_animation));
            this.lastPosition = i;
        }
    }

    @Override
    public int getItemCount() {
        return this.blogList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView headingTv;
        ImageView imageView;
        View opacityView;
        TextView questionTv;

        public ViewHolder(View view) {
            super(view);
            this.imageView = view.findViewById(R.id.imageView);
            this.headingTv = view.findViewById(R.id.headingTv);
            this.cardView = view.findViewById(R.id.cardView);
            this.opacityView = view.findViewById(R.id.opacityView);
            this.questionTv = view.findViewById(R.id.questionTv);
        }
    }
}
