package com.cdph.smsprank.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

import com.cdph.smsprank.R;
import com.cdph.smsprank.model.VictimItem;

public class VictimItemAdapter implements ListAdapter, View.OnClickListener
{
	private OnVictimListChangedListener listener;
	private ArrayList<VictimItem> recepients;
	private Button delete;
	private TextView number;
	private Context ctx;
	
	public VictimItemAdapter(Context ctx, ArrayList<VictimItem> recepients)
	{
		this.ctx = ctx;
		this.recepients = recepients;
	}
	
	public void setOnVictimListChangedListener(OnVictimListChangedListener listener)
	{
		this.listener = listener;
	}
	
	public void updateVictimList(ListView list, ArrayList<VictimItem> recepients)
	{
		this.recepients = recepients;
		list.setAdapter(this);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		String number = recepients.get(position).getVictimNumber();
		View layout = LayoutInflater.from(ctx).inflate(R.layout.victim_list_item, parent, false);
		
		this.delete = (Button) layout.findViewById(R.id.remove_victim);
		this.number = (TextView) layout.findViewById(R.id.victim_number);
		
		this.delete.setOnClickListener(this);
		this.delete.setTag(String.format("%d", position));
		this.number.setText(number);
		
		return layout;
	}

	@Override
	public void onClick(View view)
	{
		if(listener != null)
		{
			int pos = Integer.parseInt((String) view.getTag());
			this.recepients.remove(pos);
			this.listener.onVictimListChanged(recepients);
		}
	}
	
	@Override
	public void registerDataSetObserver(DataSetObserver dataset)
	{}

	@Override
	public void unregisterDataSetObserver(DataSetObserver dataset)
	{}

	@Override
	public int getCount()
	{
		return recepients.size();
	}

	@Override
	public Object getItem(int position)
	{
		return position;
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public boolean hasStableIds()
	{
		return false;
	}

	@Override
	public int getItemViewType(int position)
	{
		return position;
	}

	@Override
	public int getViewTypeCount()
	{
		return recepients.size();
	}

	@Override
	public boolean isEmpty()
	{
		return false;
	}

	@Override
	public boolean areAllItemsEnabled()
	{
		return false;
	}

	@Override
	public boolean isEnabled(int position)
	{
		return true;
	}
	
	public static final interface OnVictimListChangedListener
	{
		public void onVictimListChanged(ArrayList<VictimItem> newVictimList);
	}
}
