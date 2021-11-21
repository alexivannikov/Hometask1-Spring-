package root;

public class ExternalInfo {
    private Integer id;
    private String info;

    public ExternalInfo(Integer id, String info){
        this.id = id;
        this.info = info;
    }

    public Integer getId(){
        return this.id;
    }

    public String getInfo(){
        return this.info;
    }
}
