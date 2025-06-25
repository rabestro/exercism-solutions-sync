import java.util.Arrays;
import java.util.List;

public class DiamondPrinter {

    public static List<String> printToList(char a) {
        if (a < 'A' || a > 'Z')
            throw new IllegalArgumentException("Input must be A-Z");
        final int mid = a - 'A';
        final int side = (mid << 1) + 1;
        final var buffer = new char[side];
        Arrays.fill(buffer, ' ');
        char ch = 'A';
        var index = 0;
        final var result = new String[side];
        for (int front = mid, back = mid; front >= 0; --front, ++back, ++ch) {
            buffer[front] = buffer[back] = ch;
            result[index++] = new String(buffer);
            buffer[front] = buffer[back] = ' ';
        }
        for (int i = index--; index --> 0; ++i)
            result[i] = result[index];
        return Arrays.asList(result);
    }
}