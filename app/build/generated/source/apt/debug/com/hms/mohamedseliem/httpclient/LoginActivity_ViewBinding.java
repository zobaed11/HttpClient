// Generated code from Butter Knife. Do not modify!
package com.hms.mohamedseliem.httpclient;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view2131296323;

  private View view2131296325;

  private View view2131296425;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    target.tilEmail = Utils.findRequiredViewAsType(source, R.id.til_email, "field 'tilEmail'", TextInputLayout.class);
    target.tilPassword = Utils.findRequiredViewAsType(source, R.id.til_password, "field 'tilPassword'", TextInputLayout.class);
    target.container = Utils.findRequiredViewAsType(source, R.id.container, "field 'container'", RelativeLayout.class);
    target.formContainer = Utils.findRequiredViewAsType(source, R.id.form_container, "field 'formContainer'", LinearLayout.class);
    target.loader = Utils.findRequiredViewAsType(source, R.id.loader, "field 'loader'", ProgressBar.class);
    view = Utils.findRequiredView(source, R.id.btn_facebook, "method 'loginFacebook'");
    view2131296323 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.loginFacebook();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_login, "method 'login'");
    view2131296325 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.login();
      }
    });
    view = Utils.findRequiredView(source, R.id.go_to_register, "method 'goToRegister'");
    view2131296425 = view;
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
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tilEmail = null;
    target.tilPassword = null;
    target.container = null;
    target.formContainer = null;
    target.loader = null;

    view2131296323.setOnClickListener(null);
    view2131296323 = null;
    view2131296325.setOnClickListener(null);
    view2131296325 = null;
    view2131296425.setOnClickListener(null);
    view2131296425 = null;
  }
}
