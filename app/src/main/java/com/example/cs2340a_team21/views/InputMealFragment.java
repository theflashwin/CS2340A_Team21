package com.example.cs2340a_team21.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.cs2340a_team21.R;
import com.example.cs2340a_team21.viewmodels.InputMealViewModel;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.google.protobuf.Any;

import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InputMealFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InputMealFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    // Layout Widgets

    private EditText nameInput;
    private EditText caloriesInput;
    private Button submitButton;

    public InputMealFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InputMealFragment.
     */
    public static InputMealFragment newInstance(String param1, String param2) {
        InputMealFragment fragment = new InputMealFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input_meal, container, false);

        this.nameInput = (EditText) view.findViewById(R.id.mealNameInput);
        this.caloriesInput = (EditText) view.findViewById(R.id.mealCaloriesInput);
        this.submitButton = (Button) view.findViewById(R.id.mealSubmit);

        this.submitButton.setOnClickListener(v -> {
            InputMealViewModel.sendMeal(this.nameInput.getText().toString(),
                    this.caloriesInput.getText().toString());
            TextView textViewCalories = view.findViewById(R.id.textViewCalories);
            textViewCalories.setText("Total Calories: " + InputMealViewModel.sumCurrentCalories());
        });

        //Update Personal Info
        int userHeight = InputMealViewModel.getUserHeight();
        int userWeight = InputMealViewModel.getUserWeight();
        String userGender = InputMealViewModel.getUserGender();

        // Set the text of the TextViews to display the data
        TextView textViewDataHeight = view.findViewById(R.id.textViewHeight);
        if (userHeight != -1 && userHeight!= 0) {
            textViewDataHeight.setText("Height: " + userHeight + " inches");
        } else {
            textViewDataHeight.setText("Height: Please update your height!");
        }

        TextView textViewDataWeight = view.findViewById(R.id.textViewWeight);
        if (userWeight != -1 && userWeight!= 0) {
            textViewDataWeight.setText("Weight: " + userWeight + " pounds");
        } else {
            textViewDataWeight.setText("Weight: Please update your weight!");
        }

        TextView textViewDataGender = view.findViewById(R.id.textViewGender);
        if (userGender != "") {
            textViewDataGender.setText("Gender: " + userGender);
        } else {
            textViewDataGender.setText("Gender: Please update your gender!");
        }

        TextView textViewDataCalories = view.findViewById(R.id.textViewCalories);
        textViewDataCalories.setText("Total Calories: " + InputMealViewModel.sumCurrentCalories());

        //Add visualization 1
        Button vis1Button = view.findViewById(R.id.visualization1Button);
        vis1Button.setOnClickListener(v -> {
            AnyChartView vis1 = view.findViewById(R.id.visualization1);

            Cartesian cartesian = AnyChart.column();

            List<DataEntry> data = new ArrayList<>();
            data.add(new ValueDataEntry("Rouge", 80540));
            data.add(new ValueDataEntry("Foundation", 94190));

            Column column = cartesian.column(data);

            cartesian.yScale().minimum(0d);

            cartesian.yAxis(0).labels().format("${%Value}{groupsSeparator: }");

            cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
            cartesian.interactivity().hoverMode(HoverMode.BY_X);

            cartesian.xAxis(0).title("Product");
            cartesian.yAxis(0).title("Revenue");

            vis1.setChart(cartesian);
        });



        // Add visualization 2
        Button vis2Button = view.findViewById(R.id.visualization2Button);
        vis2Button.setOnClickListener(v -> {
            AnyChartView vis2 = view.findViewById(R.id.visualization2);

            Pie pie2 = AnyChart.pie();
            List<DataEntry> pieData2 = new ArrayList<>();
            pieData2.add(new ValueDataEntry("Red", 10000));
            pieData2.add(new ValueDataEntry("Blue", 10000));

            pie2.data(pieData2);

            vis2.setChart(pie2);

        });


        return view;
    }

}