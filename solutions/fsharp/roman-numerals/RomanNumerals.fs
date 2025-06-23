module RomanNumerals

let roman arabicNumeral =
    [ ""; "M"; "MM"; "MMM" ].[arabicNumeral / 1000]
    + [ ""; "C"; "CC"; "CCC"; "CD"; "D"; "DC"; "DCC"; "DCCC"; "CM" ].[arabicNumeral % 1000 / 100]
    + [ ""; "X"; "XX"; "XXX"; "XL"; "L"; "LX"; "LXX"; "LXXX"; "XC" ].[arabicNumeral % 100 / 10]
    + [ ""; "I"; "II"; "III"; "IV"; "V"; "VI"; "VII"; "VIII"; "IX" ].[arabicNumeral % 10]
