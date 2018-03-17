package com.example.heloise.mqtt123;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private Button subscribe;
    private EditText subscribeTopic;
    private MqttAndroidClient client;
    private PahoMqttClient pahoMqttClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pahoMqttClient = new PahoMqttClient();



        subscribe = findViewById(R.id.subscribe);
        subscribeTopic = (EditText) findViewById(R.id.subscribeTopic);
        client = pahoMqttClient.getMqttClient(getApplicationContext(), Constants.MQTT_BROKER_URL, Constants.CLIENT_ID);
        client.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {

            }

            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
               /* for(int i=0;i<10;i++)
                {
                Log.d("Message Arived MQTT", new String(mqttMessage.getPayload()));
                }
*/
              final byte[] payload=mqttMessage.getPayload();
                Log.d("msg", Arrays.toString(payload));//receives only the first value



                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("message", payload);
                startActivity(intent);







            }


            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }





        }
        );




        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic = subscribeTopic.getText().toString().trim();
               // Log.v("topic", "I REACH HERE " + topic);   testing purpose
                if (!topic.isEmpty()) {
                    try {
                        //Log.v("CLICK_CHECK","CLICKED"); testing purpose
                        pahoMqttClient.subscribe(client, topic, 1);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                }
            }
        });









    }


}

