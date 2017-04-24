package home.controller.dialog;
/**
 * an interface that define what to show in view.
 */
public interface Dialog {
    /**
     * get the name of the node in the view.
     * @return
     *  the name of associated node
     */
    String getName();
    /**
     * get the level of the node in the view.
     * @return
     *  an incremental value to define the level
     */
    int getLevel();
    /**
     * get the experience necessary to level up.
     * @return
     *  the experience amount
     */
    int getExperience();
    /**
     * tell if the node can level up or not.
     * @return
     *  true if you can level up the node false otherwise
     */
    boolean levelUpEnabled();
    /**
     * tell if the node have level blocked.
     * @return
     *  true if the level is blocked false otherwise
     */
    boolean isLevelBlocked();
    /**
     * a builder of Dialog.
     * you can create a single dialog with a builder
     * if you try to create another @throws IllegalStateException
     */
    interface Builder {
        /**
         * create a dialog builder.
         */
        static Dialog.Builder createBuilder() {
            return new DialogImpl.DialogBuilderImpl();
        }
        /**
         * 
         * @param name
         *      the name of dialog
         * @return
         *      the builder
         */
        Builder setName(String name);
        /**
         * 
         * @param level
         *      the level of dialog
         * @return
         *      the builder
         */
        Builder setLevel(int level);
        /**
         * 
         * @param experience
         * @return
         *      the builder
         */
        Builder setExperience(int experience);
        /**
         * 
         * @param enable
         *      tell if the level can be upgrade or not
         * @return
         *      the builder
         */
        Builder setLevelEnable(boolean enable);
        /**
         * 
         * @param blocked
         *      tells if the level is blocked or not
         * @return
         *      the builder
         */
        Builder setLevelBlocked(boolean blocked);
        /**
         * build the dialog.
         * @return
         *      the dialog created
         */
        public Dialog build();
    }
}
