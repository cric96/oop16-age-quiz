package home.view.debug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import home.controller.QuizController;
import home.view.quiz.QuizView;

class ConsoleQuizViewImpl extends AbstractConsoleView<QuizController> implements QuizView {
    private String question;
    private List<String> answers;
    private BufferedReader scan;
    private boolean finish;
    @Override
    public void show() {

        scan = new BufferedReader(new InputStreamReader((System.in)));
        this.finish = false;
        System.out.flush();
        System.out.println(question);
        IntStream.range(0, answers.size()).forEach(x -> System.out.println(x + " : " + answers.get(x)));
        System.out.println("choose an answer ..");
        try {
            final int choice = Integer.parseInt(this.scan.readLine());
            if (choice > this.answers.size() - 1) {
                System.out.println("THE ANSWER IS NOT PRESENT! THE APPLICATION SHUTDOWM...");
            }
            if (!finish) {
                this.getInternalController().hitAnswer(this.answers.get(choice));
            }
        } catch (NumberFormatException e) {
            //IF IS NOT FINISH THERE IS SOME ERROR..
            if (!finish) {
                System.out.println("ERROR IN SELECTION! THE APPLICATION SHUTDOWN..");
                System.exit(0);
            }
        } catch (IOException e) { 
            System.out.println("END TO READ QUESTION...");
        }
    }

    @Override
    public void showQuestion(final String question) {
        Objects.requireNonNull(question);
        this.question = question;
    }

    @Override
    public void showAnswers(final List<String> answers) {
        Objects.requireNonNull(answers);
        this.answers = answers;
    }

    @Override
    public void showTime(final int time) {
    }

    @Override
    public void showFinalScore(final int exp, final Map<String, Integer> score) {
        Objects.requireNonNull(score);
        System.out.flush();
        System.out.println("EXP = " + exp);
        score.forEach((x, y) -> System.out.println(x + " = " + y));
        System.out.println("PREMI QUALCOSA PER TORNARE INDIETRO...");
        try {
            System.in.read();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finish = true;
        this.getInternalController().quizFinished();
    }

    @Override
    public void isCorrect(final boolean isAnswerCorrect) {
        System.out.println("CORRECT = " + isAnswerCorrect);
        System.out.println("click something to go on ");
        try {
            this.scan.readLine();
        } catch (IOException e) {
            System.out.println("FINE QUIZ...");
        }
        if (!this.finish) {
            this.getInternalController().next();
            this.show();
        }
    }

    private QuizController getInternalController() {
        return this.getController().orElseThrow(() -> new IllegalStateException());
    }

    @Override
    public void showBackground(final URL image) { }
}
