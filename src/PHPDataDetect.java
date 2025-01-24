public class PHPDataDetect {

    private int beginLine;
    private int endLine;
    private int beginColumn;
    private int endColumn;
    private String name;
    private int type;

    public PHPDataDetect(String name, int beginLine, int endLine, int beginColumn, int endColumn, int type){
        this.beginColumn = beginColumn;
        this.endColumn = endColumn;
        this.beginLine = beginLine;
        this.endLine = endLine;
        this.name = name;
        this.type = type;
    }

    public int getBeginLine() {
        return beginLine;
    }

    public int getEndLine() {
        return endLine;
    }

    public int getBeginColumn() {
        return beginColumn;
    }

    public int getEndColumn() {
        return endColumn;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }
}
