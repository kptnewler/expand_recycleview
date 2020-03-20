package com.newler.expand_recycleview.expand;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.newler.expand_recycleview.Genre;
import com.newler.expand_recycleview.R;
import com.newler.expandablerecyclerview.listeners.OnGroupClickListener;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.newler.expand_recycleview.GenreDataFactory.makeClassicGenre;
import static com.newler.expand_recycleview.GenreDataFactory.makeGenres;

public class ExpandActivity extends AppCompatActivity {

  public GenreAdapter adapter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_expand);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle(getClass().getSimpleName());

    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);

    // RecyclerView has some built in animations to it, using the DefaultItemAnimator.
    // Specifically when you call notifyItemChanged() it does a fade animation for the changing
    // of the data in the ViewHolder. If you would like to disable this you can use the following:
    RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
    if (animator instanceof DefaultItemAnimator) {
      ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
    }

    final List<Genre> genres = makeGenres();
    adapter = new GenreAdapter(genres);

    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);

    Button clear = (Button) findViewById(R.id.toggle_button);
    clear.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        adapter.toggleGroup(makeClassicGenre());
      }
    });

    adapter.setOnGroupClickListener(new OnGroupClickListener() {
      @Override
      public boolean onGroupClick(int flatPos) {
        boolean expand = !genres.get(flatPos).isExpand();
        genres.get(flatPos).setExpand(!expand);
        return !genres.get(flatPos).isExpand();
      }
    });
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    adapter.onSaveInstanceState(outState);
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    adapter.onRestoreInstanceState(savedInstanceState);
  }
}
