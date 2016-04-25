package se.hayumi.dressfortheweather.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import se.hayumi.dressfortheweather.R;
import se.hayumi.dressfortheweather.model.WeatherData;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private final Context context;
    private final List<WeatherData> data;

    public WeatherAdapter(Context context, List<WeatherData> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_list, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {

        holder.dateTimeView.setText(data.get(position).getDateTime());
        holder.suitableClothesView.setText(data.get(position).getSuitableClothes().toString());
        holder.conditionView.setText(data.get(position).getCondition());
        holder.temperatureView.setText(data.get(position).getTemperature());
        holder.feelsLikeTemperatureView.setText(data.get(position).getFeelsLikeTemperature());

        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static final class WeatherViewHolder extends RecyclerView.ViewHolder {

        public final TextView dateTimeView;
        public final TextView suitableClothesView;
        public final TextView conditionView;
        public final TextView temperatureView;
        public final TextView feelsLikeTemperatureView;

        public WeatherViewHolder(View view) {
            super(view);
            this.dateTimeView = (TextView) view.findViewById(R.id.date_time_text);
            this.suitableClothesView = (TextView) view.findViewById(R.id.suitable_clothes_text);
            this.conditionView = (TextView) view.findViewById(R.id.condition_text);
            this.temperatureView = (TextView) view.findViewById(R.id.temperature_text);
            this.feelsLikeTemperatureView = (TextView) view.findViewById(R.id.feels_like_temperature_text);
        }
    }

}

