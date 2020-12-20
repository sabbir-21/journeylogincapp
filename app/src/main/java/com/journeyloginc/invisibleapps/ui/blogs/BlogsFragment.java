package com.journeyloginc.invisibleapps.ui.blogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.journeyloginc.invisibleapps.R;
import com.journeyloginc.invisibleapps.ui.blogs.BlogsViewModel;

public class BlogsFragment extends Fragment {

    private BlogsViewModel blogsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        blogsViewModel =
                ViewModelProviders.of(this).get(BlogsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_blogs, container, false);
        final TextView textView = root.findViewById(R.id.text_blogs);
        blogsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}