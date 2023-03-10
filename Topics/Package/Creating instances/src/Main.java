class BigIntegerConverter {

    /**
     * @param number string representing the number
     * @return BigInteger instance
     */
    public static Number getBigInteger(String number) {
        java.math.BigInteger cheem =  new java.math.BigInteger(number);
        return cheem;// create BigInteger instance here
    }
}