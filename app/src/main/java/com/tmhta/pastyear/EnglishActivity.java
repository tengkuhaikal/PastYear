package com.tmhta.pastyear;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class EnglishActivity extends AppCompatActivity {
    TextView questionNumber, questionTV;
    ImageView imageViewQuestion;
    RadioGroup optionsRadioGroup;
    RadioButton option1, option2;
    Button nextSubmitButton, previousCancelButton;
    ArrayList<English> questionList = new ArrayList<>();
    int currentQuestionIndex = 0;
    int score = 0;
    boolean isDone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english);

        questionNumber = findViewById(R.id.questionNumber);
        questionTV = findViewById(R.id.questionTV);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        optionsRadioGroup = findViewById(R.id.radioGroup);
        nextSubmitButton = findViewById(R.id.nextSubmitButton);
        previousCancelButton = findViewById(R.id.previousCancelButton);
        imageViewQuestion = findViewById(R.id.imageView);

        populateQuestions();

        if (currentQuestionIndex == 0) {
            previousCancelButton.setEnabled(false);
        } else {
            previousCancelButton.setEnabled(true);
        }

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
        builder.setView(R.layout.custom_dialog); // Set your custom layout
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
        builder.setView(R.layout.custom_dialog); // Set your custom layout
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

        English[] questions = new English[]{
                new English("What color is this", new String[]{"Red", "Blue"}, 1, R.color.red),
                new English("What color is the sky?", new String[]{"Blue", "Green"}, 0, R.color.blue),
                new English("What color are bananas?", new String[]{"Yellow", "Purple"}, 0, R.color.yellow),
                new English("What color is grass?", new String[]{"Green", "Orange"}, 0, R.color.green),
                new English("What color is coal?", new String[]{"Black", "White"}, 0, R.color.black),
                new English("What color is snow?", new String[]{"White", "Brown"}, 0, R.color.white),
                new English("What color are strawberries?", new String[]{"Red", "Pink"}, 0, R.color.red),
                new English("What color is the sun?", new String[]{"Yellow", "Orange"}, 1, R.color.orange),
                new English("What color is the ocean?", new String[]{"Blue", "Cyan"}, 1, R.color.cyan),
                new English("What color are apples?", new String[]{"Green", "Red"}, 1, R.color.red),
                new English("What color is chocolate?", new String[]{"Brown", "Black"}, 0, R.color.brown),
        };


        questionList.addAll(Arrays.asList(questions));

        Collections.shuffle(questionList);

        displayQuestions();
    }

    private void displayQuestions() {
        English current = questionList.get(currentQuestionIndex);
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
            imageViewQuestion.setImageTintList(ContextCompat.getColorStateList(this, current.getColorID()));
        }
    }

    public void checkAnswer(int index) {
        if (index == questionList.get(currentQuestionIndex).getAnswerIndex()) {
            score++;
        }
    }
}