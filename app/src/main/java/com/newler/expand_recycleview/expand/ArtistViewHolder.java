package com.newler.expand_recycleview.expand;

import android.view.View;
import android.widget.TextView;

import com.newler.expand_recycleview.R;
import com.newler.expandablerecyclerview.viewholders.ChildViewHolder;

public class ArtistViewHolder extends ChildViewHolder {

  private TextView childTextView;

  public ArtistViewHolder(View itemView) {
    super(itemView);
    childTextView = (TextView) itemView.findViewById(R.id.list_item_artist_name);
  }

  public void setArtistName(String name) {
    childTextView.setText(name);
  }
}
