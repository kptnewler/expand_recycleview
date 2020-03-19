package com.newler.expand_recycleview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.newler.expand_recycleview.expand.ExpandActivity;
import com.newler.expand_recycleview.multitype.MultiTypeActivity;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button expand = (Button) findViewById(R.id.expand_button);
    expand.setOnClickListener(navigateTo(ExpandActivity.class));


    Button mixedSelect = (Button) findViewById(R.id.mixedtype_button);
    mixedSelect.setOnClickListener(navigateTo(MultiTypeActivity.class));

  }

  public OnClickListener navigateTo(final Class<?> clazz) {
    return new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, clazz);
        startActivity(intent);
      }
    };
  }
}
