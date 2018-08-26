package activity.cn.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,Chronometer.OnChronometerTickListener {
    private Chronometer chronometer;
    private Button btn_start,btn_stop,btn_base,btn_format;

    @Override
    protected void onCreate(Bundle savedInstanceState)/*Android的生命周期中Activity初始化所调用的方法*/ {
        super.onCreate(savedInstanceState);//调用父类onCreate构造函数（保存当前Activity的状态信息）
        setContentView(R.layout.activity_main);//设置该Activity的显示界面，并且使用括号内的布局文件进行界面布局
        initView();
    }

    private void initView()/*初始视图（界面控件等）*/{
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        btn_start = (Button) findViewById(R.id.btnStart);
        btn_stop = (Button) findViewById(R.id.btnStop);
        btn_base = (Button) findViewById(R.id.btnReset);
        btn_format = (Button) findViewById(R.id.btn_format);
        /*在.xml文件中定义的布局控件按钮通过调用findViewById()方法使得.xml文件中的按钮与java文件中的id对应起来*/

        chronometer.setOnChronometerTickListener(this);
        btn_start.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_base.setOnClickListener(this);
        btn_format.setOnClickListener(this);
    }
    /*设置事件处理的监听器，this代表就是所在的类*/
    @Override
    public void  onClick(View v){
        switch (v.getId()){
            case R.id.btnStart:
                chronometer.start();//开始计时
                break;
            case R.id.btnStop:
                chronometer.stop();//停止计时
                break;
            case R.id.btnReset:
                chronometer.setBase(SystemClock.elapsedRealtime());//复位
                break;
            case R.id.btn_format:
                chronometer.setFormat("Time:%s");//更改时间显示格式
                break;
        }
    }
    /*switch case语句是判断语句，用法为判断case后的表达式和sweitch后的表达式是否相匹配，一旦case匹配就会执行后面的
    * 程序代码而不管后面的case是否匹配，直到遇见break*,该段程序调用onClick读取点击的button控件id并与下面switch语句的
    * 条件表达式中的触发事件id进行匹配，一致则开始执行相应事件，直到遇到break*/
    @Override
    public void onChronometerTick(Chronometer chronometer){
        String time = chronometer.getText().toString();
        if(time.equals("00:00")){
            Toast.makeText(MainActivity.this,"时间到了",Toast.LENGTH_SHORT).show();
        }
    }
}
