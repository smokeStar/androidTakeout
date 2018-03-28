package group.wenjian.takeout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class LaunchActivity extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(LaunchActivity.this, MainActivity.class);
                startActivity(intent);
                LaunchActivity.this.finish();
            }
        },1000);

//        handler = new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                String res = (String) msg.obj;
//                if(res.equals("success")){
//                    Test.test = (String) msg.obj;
//                    Intent intent = new Intent();
//                    intent.setClass(LaunchActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    LaunchActivity.this.finish();
//                }else{
//                    Toast.makeText(LaunchActivity.this, "失败", Toast.LENGTH_SHORT).show();
//                }
//            }
//        };

//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                Utils.writeFile("teteteteqtwqrfdssafteewqewrqt", "test.txt", LaunchActivity.this, new Utils.Callback() {
//                    @Override
//                    public void success() {
//                        Utils.readFile("test.txt", LaunchActivity.this, new Utils.Callback() {
//                            @Override
//                            public void success() {
//                                Message msg = handler.obtainMessage();
//                                msg.obj = "success";
//                                handler.sendMessage(msg);
//                            }
//                            @Override
//                            public void failed() {
//                                Message msg = handler.obtainMessage();
//                                msg.obj = "failed";
//                                handler.sendMessage(msg);
//                            }
//                        });
//                    }
//                    @Override
//                    public void failed() {
//                        Message msg = handler.obtainMessage();
//                        msg.obj = "failed";
//                        handler.sendMessage(msg);
//                    }
//                });
//            }
//        }.start();
    }

}
