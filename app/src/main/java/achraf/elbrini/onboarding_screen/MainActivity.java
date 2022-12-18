package achraf.elbrini.onboarding_screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private CardView next;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private SaveState saveState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        next = findViewById(R.id.nextCard);
        dotsLayout = findViewById(R.id.dotsLayout);
        saveState = new SaveState(this,"ob");



        ObAdapter adapter = new ObAdapter(this);
        viewPager.setAdapter(adapter);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1, true);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                dotsFunction(position);
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (position < 3) {
                            viewPager.setCurrentItem(position + 1, true);
                        }else{
                            saveState.setState(1);
                            startActivity(new Intent(MainActivity.this,MainActivity2.class));
                            finish();
                        }
                    }
                });
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        dotsFunction(0);

    }

    private void dotsFunction(int pos) {
        dots = new TextView[4];
        dotsLayout.removeAllViews();

        for (int i = 0 ; i< dots.length ; i++){

            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("-"));
            dots[i].setTextColor(Color.GRAY);  //unselected color
            dots[i].setTextSize(40);    //unselected size

            dotsLayout.addView(dots[i]);

        }

        if (dots.length > 0){
            dots[pos].setTextColor(Color.YELLOW);   //selected dot color
            dots[pos].setTextSize(40);  //selected dots size
        }
    }
}