package tool.wayx.com.xwidget;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
 private ViewPager mPager;
 private IAdapter mAdapter;
 private Lining line;
 private SwipeRefreshLayout s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPager=(ViewPager)findViewById(R.id.viewpager);
        line=(Lining)findViewById(R.id.line);

        mAdapter=
                new IAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                  for(int j=0;j<line.infos.size();j++){
                      if(j==i){
                          line.infos.get(j).color=Color.BLUE;

                      }else{
                          line.infos.get(j).color=Color.RED;
                      }
                  }
                 line.invalidate();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
    private float cx=30,cy=30,startx=40,stopx=70,radius=10;

    public void onAdd(View view){
        mAdapter.update(new IFragment());
        Toast.makeText(this,"n: ",Toast.LENGTH_SHORT).show();
        //30,30,10-(40,30,70,30)-80,30,10-(90,30,120,30)-130,30,10

        line.infos.add(new Lining.LiningInfo(new Line(new Point(startx+=50,cy),new Point(stopx+=50,cy)),new Point(cx+=50,30)));

        line.invalidate();

    }
public static class IFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
     View rootView=inflater.inflate(R.layout.fragment_main,container,false);
        return rootView;
    }

}
public class IAdapter extends FragmentStatePagerAdapter{
   private List<IFragment> fs;
    public IAdapter(FragmentManager fm) {
        super(fm);
        fs=new ArrayList<IFragment>();
        fs.add(new IFragment());
    }
     public void update(IFragment f){
         fs.add(f);
         notifyDataSetChanged();
     }
    @Override
    public Fragment getItem(int i) {
        return fs.get(i);
    }

    @Override
    public int getCount() {
        return fs.size();
    }
}
}
