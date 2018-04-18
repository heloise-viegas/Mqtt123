package com.example.heloise.mqtt123;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MainActivity extends AppCompatActivity {
    private Button subscribe;
    private EditText subscribeTopic;
    private MqttAndroidClient client;
    private PahoMqttClient pahoMqttClient;
    private Button vasco_button;
    private Button ponda_button;
    private Button margao_button;
    private Button panjim_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.pahoMqttClient = new PahoMqttClient();


//        subscribe = findViewById(R.id.subscribe);
//        subscribeTopic = (EditText) findViewById(R.id.subscribeTopic);

        vasco_button = findViewById(R.id.vasco_button);
        ponda_button = findViewById(R.id.ponda_button);
        margao_button = findViewById(R.id.margao_button);
        panjim_button = findViewById(R.id.panjim_button);


        // Move this callback code to MapsActivity
        this.client = pahoMqttClient.getMqttClient(getApplicationContext(), Constants.MQTT_BROKER_URL, Constants.CLIENT_ID);
        this.client.setCallback(new MqttCallbackExtended() {
                                    @Override
                                    public void connectComplete(boolean b, String s) {

                                    }

                                    @Override
                                    public void connectionLost(Throwable throwable) {

                                    }

                                    @Override
                                    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                                        String topic = subscribeTopic.getText().toString().trim();
                                        Log.d("receiving message from", topic);
                                        JsonParser parser = new JsonParser();
                                        String payload = new String(mqttMessage.getPayload());

                                        JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
                                        Log.d("message", String.valueOf((jsonObject)));
                                        Log.d("I GET CALLED MULTPLE TIMES", "YO");
                                        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                                        intent.putExtra("message", String.valueOf((jsonObject)));
//                startActivity(intent);
                                    }


                                    @Override
                                    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

                                    }
                                }
        );

    }

    @Override
    protected void onStart() {
        super.onStart();

//        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
//        startActivity(intent);
//
//        subscribe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String topic = subscribeTopic.getText().toString().trim();
//
//                if (!topic.isEmpty()) {
//                    try {
//                        pahoMqttClient.subscribe(client, topic, 0);//originaly qos=1
//                    } catch (MqttException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });

        vasco_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Vasco Button", "Hello from Vasco");
                Double vLat = 15.3929768, vLong = 73.7820748;
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("lat", vLat);
                intent.putExtra("long", vLong);
                startActivity(intent);
            }
        });

        ponda_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Ponda Button", "Hello from Ponda");
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        margao_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Margoa Button", "Hello from margao");
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        panjim_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Panjim Button", "Hello from Panjim");
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });


    }

}

