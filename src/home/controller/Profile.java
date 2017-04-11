package home.controller;

import java.util.Optional;

public class Profile {
    private Optional<String> name;
    private boolean enabled;

    Profile(){
        this.name = Optional.empty();
        this.enabled = false;
    }

    public Optional<String> getName(){
        return name;
    }

    public void setName(Optional<String> name){
        this.name = name;
    }

    public boolean isEnabled(){
        return enabled;
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }
    
}