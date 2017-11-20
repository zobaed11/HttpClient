// Generated code from Butter Knife. Do not modify!
package com.hms.mohamedseliem.httpclient;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PostActivity_ViewBinding implements Unbinder {
  private PostActivity target;

  private View view2131296325;

  @UiThread
  public PostActivity_ViewBinding(PostActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PostActivity_ViewBinding(final PostActivity target, View source) {
    this.target = target;

    View view;
    target.title = Utils.findRequiredViewAsType(source, R.id.post_title, "field 'title'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_posts, "method 'getPosts'");
    view2131296325 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.getPosts();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    PostActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.title = null;

    view2131296325.setOnClickListener(null);
    view2131296325 = null;
  }
}
