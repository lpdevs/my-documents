package com.fsoft.layouts;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/** Demonstrates the use of nested layouts. Although there are several important
 *  Layout types that you should know, the single most important strategy is
 *  to build up the screen by nesting layouts inside each other.
 */

public class NestedLayoutsActivity extends Activity {
    /** Initializes the app when it is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nested_layouts);
    }
    
    /** Switches to LayoutsInitialActivity when the associated button is clicked. */
    
    public void showInitialScreen(View clickedButton) {
        ActivityUtils.goToActivity(this, LayoutsInitialActivity.class);
    }
    
    /** Switches to LayoutWeightsActivity when the associated button is clicked. */
    
    public void showLayoutWeights(View clickedButton) {
        ActivityUtils.goToActivity(this, LayoutWeightsActivity.class);
    }
}