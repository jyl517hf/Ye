package com.eaotin.ye;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.eaotin.framework.base.BaseActivity;
import com.eaotin.framework.event.EventArgs;
import com.eaotin.framework.event.EventId;
import com.eaotin.framework.event.EventListener;
import com.eaotin.ye.event.DemoEventArgs;
import com.eaotin.ye.logic.DemoLogic;

public class MainActivity extends BaseActivity {
    private Button button;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button_get_public_key);
        textView = (TextView) findViewById(R.id.text_public_key);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPublicKey();
            }
        });
    }

    private void getPublicKey() {
        DemoLogic logic = DemoLogic.getInstance();
        logic.getPublicKey(createUIEventListener(new EventListener() {
            @Override
            public void onEvent(EventId eventId, EventArgs args) {
                DemoEventArgs demoEventArgs = (DemoEventArgs) args;
                if (demoEventArgs.getResultCode() == 0) {
                    textView.setText(demoEventArgs.getPublicKey());
                }
            }
        }));
    }
}
