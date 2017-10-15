package com.guangzhou.mpandroidchartdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MpChartTestActivity extends DemoBase {

    @InjectView(R.id.btnEnterPieChart)
    Button btnEnterPieChart;
    @InjectView(R.id.btnEnterHistogram)
    Button btnEnterHistogram;
    @InjectView(R.id.btnEnterHistogramOur)
    Button btnEnterHistogramOur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp_chart_test);
        ButterKnife.inject(this);


    }

    @OnClick({R.id.btnEnterPieChart, R.id.btnEnterHistogram,R.id.btnEnterHistogramOur})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnEnterPieChart:
                this.startActivity(new Intent(MpChartTestActivity.this,PieChartActivity.class));
                break;
            case R.id.btnEnterHistogram:
                this.startActivity(new Intent(MpChartTestActivity.this,AnotherBarActivity.class));
                break;
            case R.id.btnEnterHistogramOur:
                this.startActivity(new Intent(MpChartTestActivity.this,AnotherBarOurActivity.class));
                break;

        }
    }
}
