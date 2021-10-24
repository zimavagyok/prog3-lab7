public class Caesar {
    public static String caesarCode(String input, char offset) {
        String s = "";
        input=input.toUpperCase();
        offset=Character.toUpperCase(offset);
        int Offset=(int)offset - 65;
        for(int i = 0; i < input.length(); i++){
            char c = (char)(input.charAt(i) + Offset);
            if (c > 90)
                s += (char)(input.charAt(i) - (26-Offset));
            else
                s += (char)(input.charAt(i) + Offset);
        }
        return s;
    }

    public static String caesarDecode(String input, char offset)
    {
        String s = "";
        input=input.toUpperCase();
        offset=Character.toUpperCase(offset);
        int Offset=(int)offset - 65;
        for(int i = 0; i < input.length(); i++){
            char c = (char)(input.charAt(i) - Offset);
            if (c < 65)
                s += (char)(input.charAt(i) + (26-Offset));
            else
                s += (char)(input.charAt(i) - Offset);
        }
        return s;
    }

}
