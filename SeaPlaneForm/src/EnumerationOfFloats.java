public enum EnumerationOfFloats {
    Two,
    Four,
    Six;

    public static EnumerationOfFloats getChosenNumber(int number) {
        switch (number) {
            case 2:
                return EnumerationOfFloats.Two;
            case 4:
                return EnumerationOfFloats.Four;
            case 6:
                return EnumerationOfFloats.Six;
        }
        return null;
    }
}