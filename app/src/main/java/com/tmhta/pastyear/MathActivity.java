package com.tmhta.pastyear;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MathActivity extends AppCompatActivity {

    TextView questionNumber, questionTV;
    RadioGroup optionsRadioGroup;
    RadioButton option1, option2;
    Button nextSubmitButton, previousCancelButton;
    ArrayList<Mathematics> questionList = new ArrayList<>();
    int currentQuestionIndex = 0;
    int score = 0;
    boolean isDone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);

        questionNumber = findViewById(R.id.questionNumber);
        questionTV = findViewById(R.id.questionTV);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        nextSubmitButton = findViewById(R.id.nextSubmitButton);
        previousCancelButton = findViewById(R.id.previousCancelButton);

        populateQuestions();

        option1.setOnClickListener(v -> {
            checkAnswer(0);
        });
        option2.setOnClickListener(v -> {
            checkAnswer(1);
        });

        nextSubmitButton.setOnClickListener(v -> {
            if (isDone) {
                showDoneAlertDialog();
            } else {
                currentQuestionIndex++;
                displayQuestions();
                optionsRadioGroup.clearCheck();
            }
        });

        previousCancelButton.setOnClickListener(v -> {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--;
                displayQuestions();
            } else {
                showSureDialog();
            }
        });
    }

    private void showSureDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.custom_dialog);
        builder.setPositiveButton(getString(R.string.sure), (dialog, which) -> {
            startActivity(new Intent(this, MainActivity.class));
        });
        AlertDialog dialog = builder.create();
        dialog.show();

        TextView title = dialog.findViewById(R.id.customDialogTitle);
        assert title != null;
        title.setText(getString(R.string.sureTitle));

        TextView message = dialog.findViewById(R.id.customDialogMessage);
        assert message != null;
        message.setText(getString(R.string.backHome));
    }


    private void showDoneAlertDialog() {
        float percentage = ((float) score / questionList.size()) * 100;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.custom_dialog);
        builder.setPositiveButton(getString(R.string.done), (dialog, which) -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
        });
        AlertDialog dialog = builder.create();
        dialog.show();

        TextView title = dialog.findViewById(R.id.customDialogTitle);
        assert title != null;
        title.setText(getString(R.string.questionEnd));

        TextView message = dialog.findViewById(R.id.customDialogMessage);
        assert message != null;
        String messageText = getString(R.string.scoreOnDialog) + " " + score + "\n" + getString(R.string.percentageOnDialog) + " " + percentage + "%";
        message.setText(messageText);
    }

    private void populateQuestions() {
        Mathematics[] questions = new Mathematics[]{
                new Mathematics("What is 2 + 2?", new String[]{"3", "4"}, 1),
                new Mathematics("What is 5 - 3?", new String[]{"2", "3"}, 0),
                new Mathematics("What is 10 * 5?", new String[]{"50", "55"}, 0),
                new Mathematics("What is 20 / 4?", new String[]{"5", "6"}, 0),
                new Mathematics("What is 7 + 8?", new String[]{"16", "15"}, 1),
                new Mathematics("What is 12 - 4?", new String[]{"8", "9"}, 0),
                new Mathematics("What is 3 * 6?", new String[]{"18", "19"}, 0),
                new Mathematics("What is 15 / 3?", new String[]{"7", "5"}, 1),
                new Mathematics("What is 9 + 7?", new String[]{"16", "17"}, 0),
                new Mathematics("What is 14 - 6?", new String[]{"8", "9"}, 0)
        };

        questionList.addAll(Arrays.asList(questions));

        Collections.shuffle(questionList);

        displayQuestions();
    }

    private void displayQuestions() {
        Mathematics current = questionList.get(currentQuestionIndex);
        if (currentQuestionIndex <= questionList.size() - 1) {

            if (currentQuestionIndex == 0) {
                previousCancelButton.setText(getString(R.string.cancel));
            } else if (currentQuestionIndex == questionList.size() - 1) {
                nextSubmitButton.setText(getString(R.string.submit));
                isDone = true;
            } else {
                previousCancelButton.setText(getString(R.string.previous));
            }

            questionNumber.setText("Question " + (currentQuestionIndex + 1));
            questionTV.setText(current.getQuestions());
            option1.setText(current.getOptions()[0]);
            option2.setText(current.getOptions()[1]);
        }
    }

    public void checkAnswer(int index) {
        if (index == questionList.get(currentQuestionIndex).getAnswerIndex()) {
            score++;
        }
    }
}