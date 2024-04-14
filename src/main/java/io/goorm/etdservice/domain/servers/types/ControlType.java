package io.goorm.etdservice.domain.servers.types;

public enum ControlType {

    CREATE("create"),
    RESTART("restart"),
    STOP("stop"),
    DELETE("delete");

    private final String label;

    ControlType(String label){
        this.label = label;
    }

    public String label(){
        return label;
    }

}
