package render;

import preprocessor.PreProcessor;

public class RendererStandardImpl implements Renderer{

    private PreProcessor preProcessor;
    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    public void print(String initString) {
        System.out.println(preProcessor.process(initString));
    }
}
