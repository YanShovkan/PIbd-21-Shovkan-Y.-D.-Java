public enum EnumerationOfFloats {
    Two,
    Four,
    Six;

    public static EnumerationOfFloats getChosenNumber(int number) {
        switch (number) {
            case 0:
                return EnumerationOfFloats.Two;
            case 1:
                return EnumerationOfFloats.Four;
            case 2:
                return EnumerationOfFloats.Six;
        }
        return null;
    }
}