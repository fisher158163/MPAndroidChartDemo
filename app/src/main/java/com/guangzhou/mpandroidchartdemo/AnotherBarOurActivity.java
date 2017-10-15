package com.guangzhou.mpandroidchartdemo;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.guangzhou.mpandroidchartdemo.R.id.seekBar1;
import static com.guangzhou.mpandroidchartdemo.R.id.seekBar2;

public class AnotherBarOurActivity extends DemoBase implements SeekBar.OnSeekBarChangeListener {

    @InjectView(R.id.spread_pie_chart)
    BarChart mChart;
    @InjectView(seekBar1)
    SeekBar mSeekBarX;
    @InjectView(R.id.tvX)
    TextView tvX;
    @InjectView(seekBar2)
    SeekBar mSeekBarY;
    @InjectView(R.id.tvY)
    TextView tvY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_another_bar);
        ButterKnife.inject(this);

        mChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawBarShadow(false);
        mChart.setDrawGridBackground(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        mChart.getAxisLeft().setDrawGridLines(false);
        mSeekBarX.setOnSeekBarChangeListener(this);
        mSeekBarY.setOnSeekBarChangeListener(this);
        // setting data
        mSeekBarX.setProgress(10);
        mSeekBarY.setProgress(100);

        // add a nice and smooth animation
        mChart.animateY(2500);

        mChart.getLegend().setEnabled(false);

    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        tvX.setText("" + (mSeekBarX.getProgress() + 1));
        tvY.setText("" + (mSeekBarY.getProgress()));

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = 0; i < mSeekBarX.getProgress() + 1; i++) {
            float mult = (mSeekBarY.getProgress() + 1);
            float val = (float) (Math.random() * mult) + mult / 3;
            yVals1.add(new BarEntry(i, val));
        }

        BarDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "Data Set");
            set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
            set1.setDrawValues(false);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            mChart.setData(data);
            mChart.setFitBars(true);
        }

        mChart.invalidate();
        //Toast.makeText(getApplicationContext(),"这个被调用了",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
