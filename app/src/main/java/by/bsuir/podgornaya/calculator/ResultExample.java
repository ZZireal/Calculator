package by.bsuir.podgornaya.calculator;

public class ResultExample {

    private String result;
    private String example;

    public ResultExample(String result, String example) {
        this.result = result;
        this.example = example;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}