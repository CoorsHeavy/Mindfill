package com.hudson.mindfill;

public class ShoppingHelper {

    private static final String ACCESS_KEY = "AKIAINQV2YRNNYOUHCOQ";
    private static final String SECRET = "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";
    private static final String ASSOCIATE_TAG = "mind05e-20";

    private static ShoppingHelper instance = new ShoppingHelper();
    public static ShoppingHelper getInstance() {
        return instance;
    }

    private ShoppingHelper() {

    }


}
