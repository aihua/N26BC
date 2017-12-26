package com.apps911.brunotrovo.n26bc.presentation;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.apps911.brunotrovo.n26bc.R;
import com.apps911.brunotrovo.n26bc.data.bitcoin.BitCoinMapper;
import com.apps911.brunotrovo.n26bc.data.bitcoin.BitCoinRepository;
import com.apps911.brunotrovo.n26bc.data.bitcoin.IBitCoinPriceService;
import com.apps911.brunotrovo.n26bc.data.remote.ServiceGenerator;
import com.apps911.brunotrovo.n26bc.data.store.FakeReactiveStore;
import com.apps911.brunotrovo.n26bc.data.store.FakeStore;
import com.apps911.brunotrovo.n26bc.domain.RetrieveBitCoinPriceItemList;
import com.apps911.brunotrovo.n26bc.utils.CurrencyUtils;
import com.apps911.brunotrovo.n26bc.utils.TimeUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import static com.apps911.brunotrovo.n26bc.presentation.BitCoinPricePresentationConstants.ChartProperties.ANIMATION_DURATION_MILLIS;
import static com.apps911.brunotrovo.n26bc.presentation.BitCoinPricePresentationConstants.ChartProperties.CIRCLE_RADIUS;
import static com.apps911.brunotrovo.n26bc.presentation.BitCoinPricePresentationConstants.ChartProperties.CUBIC_INTENSITY;
import static com.apps911.brunotrovo.n26bc.presentation.BitCoinPricePresentationConstants.ChartProperties.LABEL_COUNT;
import static com.apps911.brunotrovo.n26bc.presentation.BitCoinPricePresentationConstants.ChartProperties.LINE_WIDTH;
import static com.apps911.brunotrovo.n26bc.presentation.BitCoinPricePresentationConstants.ChartProperties.MAX_HIGHLIGHT_DISTANCE;
import static com.apps911.brunotrovo.n26bc.presentation.BitCoinPricePresentationConstants.ChartProperties.VALUE_TEXT_SIZE;
import static com.apps911.brunotrovo.n26bc.presentation.BitCoinPricePresentationConstants.ChartProperties.VIEW_PORT_OFFSET_VALUE;
import static com.apps911.brunotrovo.n26bc.presentation.BitCoinPricePresentationConstants.ChartProperties.X_AXIS_TEXT_SIZE;

public class BitCoinChartActivity extends AppCompatActivity {

    private static final String TAG = BitCoinChartActivity.class.getSimpleName();

    private LineChart mChart;

    @NonNull
    private final CurrencyUtils currencyUtils = new CurrencyUtils();

    @NonNull
    private final TimeUtils timeUtils = new TimeUtils();

    private BitCoinPriceListItemViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupToolbar();
        setupChart();

        final BitCoinRepository repository = new BitCoinRepository(new FakeReactiveStore<>(new FakeStore<>()),
                ServiceGenerator.create(IBitCoinPriceService.class),
                new BitCoinMapper());

        final BitCoinPriceListItemViewModelFactory viewModelFactory =
                new BitCoinPriceListItemViewModelFactory(new RetrieveBitCoinPriceItemList(repository),
                new BitCoinPriceListItemViewEntityMapper());


        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(BitCoinPriceListItemViewModel.class);

        viewModel.getBitCoinListLiveDate().observe(this, this::updateView);
    }

    private void setupToolbar() {
        final Toolbar toolbar = findViewById(R.id.act_home_toolbar);
        setSupportActionBar(toolbar);
    }

    private void updateView(List<BitCoinPriceItemViewEntity> bitCoinPriceItemViewEntities) {
        if (!bitCoinPriceItemViewEntities.isEmpty()){
            setData(bitCoinPriceItemViewEntities);
        }
    }

    private void setupChart() {
        mChart = findViewById(R.id.act_home_line_chart);
        mChart.setViewPortOffsets(VIEW_PORT_OFFSET_VALUE,
                VIEW_PORT_OFFSET_VALUE,
                VIEW_PORT_OFFSET_VALUE,
                VIEW_PORT_OFFSET_VALUE);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.getDescription().setEnabled(false);
        mChart.setTouchEnabled(true);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setPinchZoom(false);
        mChart.setDrawGridBackground(false);
        mChart.setMaxHighlightDistance(MAX_HIGHLIGHT_DISTANCE);
        mChart.setNoDataText(getString(R.string.no_data_text));
        mChart.setNoDataTextColor(ContextCompat.getColor(this, R.color.mint_dark));

        XAxis x = mChart.getXAxis();
        x.setDrawGridLines(false);
        x.setLabelCount(LABEL_COUNT, false);
        x.setAvoidFirstLastClipping(true);
        x.setTextColor(Color.WHITE);
        x.setTextSize(X_AXIS_TEXT_SIZE);
        x.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        x.setValueFormatter(new DateAxisValueFormatter(this.timeUtils));

        YAxis y = mChart.getAxisLeft();
        y.setLabelCount(LABEL_COUNT, false);
        y.setTextColor(Color.GRAY);
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        y.setDrawGridLines(false);
        y.setAxisLineColor(ContextCompat.getColor(this, R.color.mint_dark));
        y.setValueFormatter(new CurrencyAxisValueFormatter(this.currencyUtils));

        mChart.getAxisRight().setEnabled(false);
        mChart.getLegend().setEnabled(false);
        mChart.animateXY(ANIMATION_DURATION_MILLIS, ANIMATION_DURATION_MILLIS);
        mChart.invalidate();
    }

    private void setData(@NonNull final List<BitCoinPriceItemViewEntity> bitCoinPriceItemViewEntities) {
        ArrayList<Entry> valueList = new ArrayList<>();

        for (BitCoinPriceItemViewEntity item :
                bitCoinPriceItemViewEntities) {
            valueList.add(new Entry(item.getDate(), item.getPrice()));
        }

        LineDataSet lineDataSetPrices;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            lineDataSetPrices = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            lineDataSetPrices.setValues(valueList);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            lineDataSetPrices = new LineDataSet(valueList,
                    getString(R.string.bit_coin_market_price_label));
            lineDataSetPrices.setMode(LineDataSet.Mode.LINEAR);
            lineDataSetPrices.setCubicIntensity(CUBIC_INTENSITY);
            lineDataSetPrices.setDrawFilled(true);
            lineDataSetPrices.setDrawCircles(false);
            lineDataSetPrices.setLineWidth(LINE_WIDTH);
            lineDataSetPrices.setCircleRadius(CIRCLE_RADIUS);
            lineDataSetPrices.setCircleColor(Color.WHITE);
            lineDataSetPrices.setHighLightColor(ContextCompat.getColor(this, R.color.mint));
            lineDataSetPrices.setColor(Color.WHITE);
            lineDataSetPrices.setFillColor(ContextCompat.getColor(this, R.color.mint));
            lineDataSetPrices.setDrawHorizontalHighlightIndicator(false);
            LineData data = new LineData(lineDataSetPrices);
            data.setValueTextSize(VALUE_TEXT_SIZE);
            data.setDrawValues(false);
            mChart.setData(data);
        }
        mChart.animateXY(ANIMATION_DURATION_MILLIS, ANIMATION_DURATION_MILLIS);
        mChart.invalidate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bit_coin_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_action_refresh) {
            viewModel.requestDataUpdate();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}