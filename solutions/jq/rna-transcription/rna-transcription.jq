

def toRna:
  {G: "C", C: "G", T: "A", A: "U"} as $transcription |
  split("") | map($transcription[.]) | join("")
;
