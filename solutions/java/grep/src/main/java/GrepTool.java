import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.StringJoiner;

class GrepTool {

    String grep(String pattern, List<String> flags, List<String> files) {
        var sb = new StringJoiner("\n");
        var flagLineNumber = flags.contains("-n");
        var flagCaseInsensitive = flags.contains("-i");
        var flagFileNameOnly = flags.contains("-l");
        var flagEntireLine = flags.contains("-x");
        var flagInverted = flags.contains("-v");
        var flagMultiplyFiles = files.size() > 1;

        for (var file : files) {
            var path = Path.of(file);
            try (var lines = Files.lines(path)) {
                var lineNumber = 0;
                for (var line : lines.toList()) {
                    lineNumber++;
                    var isMatch = flagEntireLine ?
                            flagCaseInsensitive
                                ? line.equalsIgnoreCase(pattern)
                                : line.equals(pattern)
                            : flagCaseInsensitive
                                ? line.toLowerCase(Locale.ROOT).contains(pattern.toLowerCase(Locale.ROOT))
                                : line.contains(pattern);

                    if (flagInverted ^ isMatch) {
                        if (flagFileNameOnly) {
                            sb.add(file);
                            break;
                        }
                        if (flagLineNumber) {
                            line = lineNumber + ":" + line;
                        }
                        if (flagMultiplyFiles) {
                            line = file + ":" + line;
                        }
                        sb.add(line);
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        return sb.toString();
    }

}