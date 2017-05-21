package home.view.fx;
/**
 * all the style sheet in this contest.
 */
public enum StyleSheet {
    /**
     * the style sheet of answer.
     */
    ANSWERS("/style/answers.css"),
    /**
     * the style sheet of game button.
     */
    GAME_BUTTONS("/style/gameButtons.css"),
    /**
     * the style sheet of progress bar.
     */
    PROGRESS_BAR("/style/progressBar.css"),
    /**
     * the style sheet of question.
     */ 
    QUESTION("/style/question.css");
    private final String file;
    StyleSheet(final String file) {
        this.file = file;
    }
    @Override
    public String toString() {
        return this.file;
    }
}
