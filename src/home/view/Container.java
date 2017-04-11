package home.view;

public interface Container {
    
    static Container getContainer() {
        return FXContainer.getContainer();
    }
    
    //public void changeDisplay(Enum)
}
