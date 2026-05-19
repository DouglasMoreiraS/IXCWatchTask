package br.com.planet.ixcwatchtask.integration.ixc;

public class GridParamConstructor {

    public static final String BE = "BE";
    public static final String GREATER_THAN_EQUAL_TO = ">=";
    public static final String LESS_THAN_EQUAL_TO = "<=";
    public static final String EQUAL = "=";
    public static final String DIFFERENT = "!=";


    public static final String SimpleGridParamConstructor (String tableName, String comparator, String data){

        return String.format("{\\\"TB\\\":\\\"%s\\\", \\\"OP\\\":\\\"%s\\\", \\\"P\\\":\\\"%s\\\"}", tableName, comparator, data);
    }

    public static final String DoubleGridParamConstructor (String tableName, String comparator, String data1, String data2){
        return String.format("{\\\"TB\\\":\\\"%s\\\", \\\"OP\\\":\\\"%s\\\", \\\"P\\\":\\\"%s\\\", \\\"P2\\\":\\\"%s\\\"}", tableName, comparator, data1, data2);
    }

}