// Generated code from Butter Knife. Do not modify!
package com.hms.mohamedseliem.httpclient;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegisterActivity_ViewBinding implements Unbinder {
  private RegisterActivity target;

  private View view2131296325;

  private View view2131296417;

  @UiThread
  public RegisterActivity_ViewBinding(RegisterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegisterActivity_ViewBinding(final RegisterActivity target, View source) {
    this.target = target;

    View view;
    target.tilName = Utils.findRequiredViewAsType(source, R.id.til_name, "field 'tilName'", TextInputLayout.class);
    target.tilEmail = Utils.findRequiredViewAsType(source, R.id.til_email, "field 'tilEmail'", TextInputLayout.class);
    target.tilPassword = Utils.findRequiredViewAsType(source, R.id.til_password, "field 'tilPassword'", TextInputLayout.class);
    view = Utils.findRequiredView(source, R.id.btn_register, "method 'register'");
    view2131296325 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.register();
      }
    });
    view = Utils.findRequiredView(source, R.id.go_to_login, "method 'goToRegister'");
    view2131296417 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goToRegister();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    RegisterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tilName = null;
    target.tilEmail = null;
    target.tilPassword = null;

    view2131296325.setOnClickListener(null);
    view2131296325 = null;
    view2131296417.setOnClickListener(null);
    view2131296417 = null;
  }
}
