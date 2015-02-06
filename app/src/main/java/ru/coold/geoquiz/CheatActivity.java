package ru.coold.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by rz on 05.02.2015.
 */
public class CheatActivity extends Activity {
    public static final String EXTRA_ANSWER_IS_TRUE = "ru.coold.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "ru.coold.geoquiz.answer_show";
    public static final String CHEATED = "cheated";
    private boolean mCheated = false;
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswer;
    private TextView mApiLevel;

    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mApiLevel = (TextView)findViewById(R.id.api_level);
        mApiLevel.setText("API Level "+ Build.VERSION.SDK_INT);

        mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
        if (savedInstanceState!=null) mCheated = savedInstanceState.getBoolean(CHEATED, false);
        setAnswerShownResult(mCheated);
        if(mCheated) {
            if(mAnswerIsTrue){
                mAnswerTextView.setText(R.string.true_button);
            } else {
                mAnswerTextView.setText(R.string.false_button);
            }
        }

        mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAnswerIsTrue){
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                mCheated = true;
                setAnswerShownResult(mCheated);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(CHEATED, mCheated);
    }
}
