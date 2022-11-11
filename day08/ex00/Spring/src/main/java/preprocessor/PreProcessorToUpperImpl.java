package preprocessor;

public class PreProcessorToUpperImpl implements PreProcessor{
    public String process(String initString) {
        return initString.toUpperCase();
    }
}
