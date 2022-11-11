package render;

import preprocessor.PreProcessor;

public class RendererErrImpl implements Renderer{
    private PreProcessor preProcessor;
    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    public void print(String initString) {
        System.err.println(preProcessor.process(initString));
    }
}
