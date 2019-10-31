package com.cdph.smsprank;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;

import com.cdph.smsprank.adapter.VictimItemAdapter;
import com.cdph.smsprank.model.VictimItem;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, VictimItemAdapter.OnVictimListChangedListener
{
	private Button addVictim, commJS1, commJS2, commPSI, commSS, commSL, commSSI;
	private EditText inputVictim;
	private ListView listVictim;
	private CardView listVictimView;
	private ArrayList<VictimItem> recepients;
	private VictimItemAdapter adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		setTheme(R.style.Theme_AppCompat);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		this.initViews();
    }
	
	private void initViews()
	{
		this.addVictim = (Button) findViewById(R.id.btn_add);
		this.commJS1 = (Button) findViewById(R.id.btn_jumpscare_1);
		this.commJS2 = (Button) findViewById(R.id.btn_jumpscare_2);
		this.commPSI = (Button) findViewById(R.id.btn_popup_img);
		this.commSSI = (Button) findViewById(R.id.btn_special_prank);
		this.commSL = (Button) findViewById(R.id.btn_sound_laugh);
		this.commSS = (Button) findViewById(R.id.btn_sound_shout);
		
		this.inputVictim = (EditText) findViewById(R.id.victim_number);
		this.listVictim = (ListView) findViewById(R.id.victim_list);
		this.listVictimView = (CardView) findViewById(R.id.cardview_victim_list);
		this.recepients = new ArrayList<>();
		this.adapter = new VictimItemAdapter(this, recepients);
		this.adapter.setOnVictimListChangedListener(this);
		
		this.addVictim.setOnClickListener(this);
		this.commJS1.setOnClickListener(this);
		this.commJS2.setOnClickListener(this);
		this.commPSI.setOnClickListener(this);
		this.commSSI.setOnClickListener(this);
		this.commSL.setOnClickListener(this);
		this.commSS.setOnClickListener(this);
	}

	@Override
	public void onClick(View view)
	{
		int id = view.getId();
		
		switch(id)
		{
			case R.id.btn_add:
				String number = inputVictim.getText().toString();
				
				if(number.equals("") || !TextUtils.isDigitsOnly(number))
				{
					inputVictim.setError("Invalid phone number");
					return;
				}
				
				this.inputVictim.setText("");
				this.recepients.add(VictimItem.create(number));
				this.listVictim.setAdapter(adapter);
				
				if(listVictimView.getVisibility() == View.GONE)
					listVictimView.setVisibility(View.VISIBLE);
			break;
		}
	}

	@Override
	public void onVictimListChanged(ArrayList<VictimItem> newVictimList)
	{
		this.recepients = newVictimList;
		this.adapter.updateVictimList(listVictim, recepients);
		this.listVictimView.setVisibility(((recepients.size() > 0) ? View.VISIBLE : View.GONE));
	}
}
