object PigLatin:
  private val WordPattern =
    """(?x)                         # Enable verbose mode for comments
       (?<consonants>               # Define possible consonants
          (?!xr|yt)                 # Consonants should not start with "xr" or "yt"
          y?(qu|[\w&&[^aeiouy]])*   # The main definition of a consonant group
       )?                           # The consonants group is optional
       (?<base>\w+)                 # The definition of a base, unmoved groups of letters
    """.r

  private val PigLatinFormat = "${base}${consonants}ay"

  def translate(sentence: String): String =
    WordPattern.replaceAllIn(sentence, PigLatinFormat)
