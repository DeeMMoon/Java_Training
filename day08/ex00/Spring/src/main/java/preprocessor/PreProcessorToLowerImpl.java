package preprocessor;

public class PreProcessorToLowerImpl implements PreProcessor{
    public String process(String initString) {
        return initString.toLowerCase();
    }
}
