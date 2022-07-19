package mp.crud.enums;

public enum VariablesGlobales {
    BD_URL(System.getenv("CLEARDB_DATABASE_URL") == null ? 
            "jdbc:mysql://root:admin@localhost:3306/cac_crud_biblioteca?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true" 
            : "jdbc:" + System.getenv("CLEARDB_DATABASE_URL") + "&useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true")    
    ;

    private final String value;

    private VariablesGlobales(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
